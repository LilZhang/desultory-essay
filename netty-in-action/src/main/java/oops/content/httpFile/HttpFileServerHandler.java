package oops.content.httpFile;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.CharsetUtil;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest>
{
    private final String url;

    public HttpFileServerHandler(String url)
    {
        this.url = url;
    }

    private static final Pattern INSECURE_URI = Pattern.compile(".*[<>&\"].*");

    private static final Pattern ALLOWED_FILE_NAME = Pattern.compile("[A-Za-z0-9][-_A-Za-z0-9\\.]*");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception
    {
        if (!request.decoderResult().isSuccess())
        {
            sendError(ctx, HttpResponseStatus.BAD_REQUEST);
            return;
        }

        if (request.method() != HttpMethod.GET)
        {
            sendError(ctx, HttpResponseStatus.METHOD_NOT_ALLOWED);
            return;
        }

        final String uri = request.uri();
        final String path = sanitizeUri(uri);
        if (path == null)
        {
            sendError(ctx, HttpResponseStatus.FORBIDDEN);
            return;
        }

        File file = new File(path);
        if (file.isHidden() || !file.exists())
        {
            sendError(ctx, HttpResponseStatus.NOT_FOUND);
            return;
        }

        if (file.isDirectory())
        {
            if (uri.endsWith("/"))
            {
                sendListing(ctx, file);
            }
            else
            {
                sendRedirect(ctx, uri + '/');
            }
            return;
        }

        if (!file.isFile())
        {
            sendError(ctx, HttpResponseStatus.FORBIDDEN);
            return;
        }

        RandomAccessFile randomAccessFile = null;
        try
        {
            randomAccessFile = new RandomAccessFile(file, "r");  // readonly
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            sendError(ctx, HttpResponseStatus.NOT_FOUND);
            return;
        }
        long fileLength = randomAccessFile.length();
        DefaultHttpResponse response =
                new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);

        HttpUtil.setContentLength(response, fileLength);
        setContentTypeHeader(response, file);

        if (HttpUtil.isKeepAlive(request))
        {
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }
        ctx.write(response);

        ChannelFuture sendFileFuture;
        sendFileFuture = ctx.write(new ChunkedFile(randomAccessFile, 0, fileLength, 8192),
                ctx.newProgressivePromise());

        sendFileFuture.addListener(new ChannelProgressiveFutureListener()
        {
            public void operationProgressed(ChannelProgressiveFuture future, long progress, long total) throws Exception
            {
                if (total < 0)
                {
                    System.err.printf("Transfer progress: %d ...\n", progress);
                }
                else
                {
                    System.err.printf("Transfer progress: %d/%d ...\n", progress, total);
                }
            }

            public void operationComplete(ChannelProgressiveFuture future) throws Exception
            {
                System.out.printf("Transfer complete.\n");
            }
        });

        ChannelFuture lastContentFuture =
                ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
        if (!HttpUtil.isKeepAlive(request))
        {
            lastContentFuture.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        cause.printStackTrace();
        if (ctx.channel().isActive())
        {
            sendError(ctx, HttpResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void sendError(ChannelHandlerContext ctx, HttpResponseStatus status)
    {
        String failure = String.format("Failure: %s\r\n", status.toString());
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, Unpooled.copiedBuffer(failure, CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private String sanitizeUri(String uri)
    {
        try
        {
            uri = URLDecoder.decode(uri, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            try
            {
                uri = URLDecoder.decode(uri, "ISO-8859-1");
            }
            catch (UnsupportedEncodingException e1)
            {
                throw new Error();
            }
        }

        if (!uri.startsWith(url))
        {
            return null;
        }
        if (!uri.startsWith("/"))
        {
            return null;
        }

        uri = uri.replace('/', File.separatorChar);
        if (uri.contains(File.separator + '.')
                || uri.contains('.' + File.separator)
                || uri.startsWith(".")
                || uri.endsWith(".")
                || INSECURE_URI.matcher(uri).matches())
        {
            return null;
        }

        return System.getProperty("user.dir") + /*File.separator +*/ uri;
    }

    private void sendListing(ChannelHandlerContext ctx, File dir)
    {
        DefaultFullHttpResponse response =
                new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=UTF-8");

        StringBuilder sb = new StringBuilder();
        sb.append("<h1>LIST</h1>").append("<br>");
        for (File f : dir.listFiles())
        {
            if (f.isHidden() || !f.canRead())
            {
                continue;
            }
            String name = f.getName();
            if (!ALLOWED_FILE_NAME.matcher(name).matches())
            {
                continue;
            }
            sb.append(name).append("<br>");
        }

        ByteBuf buffer = Unpooled.copiedBuffer(sb, CharsetUtil.UTF_8);
        response.content().writeBytes(buffer);
        buffer.release();
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private void sendRedirect(ChannelHandlerContext ctx, String path)
    {
        FullHttpResponse response =
                new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FOUND);
        response.headers().set(HttpHeaderNames.LOCATION, path);
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private void setContentTypeHeader(HttpResponse response, File file)
    {
        MimetypesFileTypeMap mimetypesMap = new MimetypesFileTypeMap();
        String contentType = mimetypesMap.getContentType(file.getPath());
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, contentType);
    }
}

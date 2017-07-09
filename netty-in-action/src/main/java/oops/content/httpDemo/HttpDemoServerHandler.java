package oops.content.httpDemo;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import oops.content.httpDemo.model.DemoModel;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class HttpDemoServerHandler extends ChannelInboundHandlerAdapter
{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        FullHttpRequest request = (FullHttpRequest) msg;
        ByteBuf content = request.content();
        String json = byteBufferToString(content.nioBuffer());
        DemoModel demoModel = JSON.parseObject(json, DemoModel.class);
        System.out.println();

        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);

        ByteBuf buffer = Unpooled.copiedBuffer("error!", CharsetUtil.UTF_8);
        response.content().writeBytes(buffer);
        buffer.release();

        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
    {
        ctx.flush();
    }

    public String byteBufferToString(ByteBuffer buffer)
    {
        CharBuffer charBuffer = null;
        try
        {
            Charset charset = Charset.forName("UTF-8");
            CharsetDecoder decoder = charset.newDecoder();
            charBuffer = decoder.decode(buffer);
            buffer.flip();
            return charBuffer.toString();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}

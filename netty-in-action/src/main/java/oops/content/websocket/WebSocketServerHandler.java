package oops.content.websocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object>
{
    private static final Logger LOGGER = Logger.getLogger(WebSocketServerHandler.class.getName());

    private WebSocketServerHandshaker handshaker;

    private volatile boolean active;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        if (msg instanceof FullHttpRequest)
        {
            handleHttpRequest(ctx, ((FullHttpRequest) msg));
        }
        else if (msg instanceof WebSocketFrame)
        {
            handleWebSocketFrame(ctx, ((WebSocketFrame) msg));
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
    {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        cause.printStackTrace();
        ctx.close();
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception
    {
        if (!request.decoderResult().isSuccess()
                || !"websocket".equals(request.headers().get("Upgrade")))
        {
            sendHttpResponse(ctx, request, new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }

        WebSocketServerHandshakerFactory wsFactory =
                new WebSocketServerHandshakerFactory("ws://127.0.0.1:8080/websocket", null, false);

        handshaker = wsFactory.newHandshaker(request);
        if (handshaker == null)
        {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }
        else
        {
            handshaker.handshake(ctx.channel(), request);
            activeOn(ctx);
        }
    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception
    {
        // 是否关闭链路
        if (frame instanceof CloseWebSocketFrame)
        {
            handshaker.close(ctx.channel(), ((CloseWebSocketFrame) frame.retain()));
            activeOff(ctx);
            return;
        }

        // 是否为 PING
        if (frame instanceof PingWebSocketFrame)
        {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }

        // 暂时仅支持文本消息, 不支持二进制消息
        if (!(frame instanceof TextWebSocketFrame))
        {
            throw new UnsupportedOperationException(
                    String.format("%s frame types not supported", frame.getClass().getName()));
        }

        String request = ((TextWebSocketFrame) frame).text();
        LOGGER.info(String.format("%s received %s", ctx.channel(), request));

        String resp = String.format("%s, Netty WebSocket Here, now: %s", request, new Date().toString());
        ctx.channel().write(new TextWebSocketFrame(resp));
    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest request, FullHttpResponse response)
    {
        if (response.status().code() != HttpResponseStatus.OK.code())
        {
            ByteBuf buf = Unpooled.copiedBuffer(response.status().toString(), CharsetUtil.UTF_8);
            response.content().writeBytes(buf);
            buf.release();
            HttpUtil.setContentLength(response, response.content().readableBytes());
        }

        ChannelFuture future = ctx.channel().writeAndFlush(response);
        if (!HttpUtil.isKeepAlive(request) || response.status().code() != HttpResponseStatus.OK.code())
        {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    private void activeOn(ChannelHandlerContext ctx)
    {
        active = true;
        new Thread(() ->
        {
            while (active)
            {
                long v = 3000 + ((long) (Math.random() * 5000));
                try
                {
                    TimeUnit.MILLISECONDS.sleep(v);
                }
                catch (InterruptedException e)
                {
                    break;
                }
                String push = "push it! " + new Date().toString();
                System.out.println(push);
                ctx.channel().writeAndFlush(new TextWebSocketFrame(push));
            }
            System.out.println("stoooooop push");
        }).start();
    }

    private void activeOff(ChannelHandlerContext ctx)
    {
        active = false;
    }
}

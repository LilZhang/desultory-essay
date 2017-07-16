package oops.content.customProtocol;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;
import java.util.Map;

public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage>
{
    protected void encode(ChannelHandlerContext channelHandlerContext, NettyMessage msg, List<Object> list) throws Exception
    {
        if (msg == null || msg.getHeader() == null)
        {
            throw new Exception("The encode message is null");
        }

        ByteBuf sendBuf = Unpooled.buffer();

        Header header = msg.getHeader();
        sendBuf.writeInt(header.getCrcCode());
        sendBuf.writeInt(header.getLength());
        sendBuf.writeLong(header.getSessionId());
        sendBuf.writeByte(header.getType());
        sendBuf.writeByte(header.getContentType());
        sendBuf.writeByte(header.getPriority());

        Map<String, Object> attachment = header.getAttachment();
        byte[] attachmentBytes = JSON.toJSONBytes(attachment);
        sendBuf.writeInt(attachmentBytes.length);
        sendBuf.writeBytes(attachmentBytes);

        byte[] content = msg.getContent();
        if (content != null && content.length > 0)
        {
            sendBuf.writeInt(content.length);
            sendBuf.writeBytes(content);
        }
        else
        {
            sendBuf.writeInt(0);
        }
        sendBuf.setInt(4, sendBuf.readableBytes());
        list.add(sendBuf);
    }
}

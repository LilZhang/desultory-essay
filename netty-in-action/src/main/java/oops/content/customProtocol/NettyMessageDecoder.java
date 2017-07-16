package oops.content.customProtocol;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.Map;

public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder
{
    private static final TypeReference<Map<String, Object>> mapper =
            new TypeReference<Map<String, Object>>(){};

    public NettyMessageDecoder()
    {
        super(1024 * 1024, 4, 4, -8, 0);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception
    {
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null)
        {
            return null;
        }

        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(frame.readInt());
        header.setLength(frame.readInt());
        header.setSessionId(frame.readLong());
        header.setType(frame.readByte());
        header.setContentType(frame.readByte());
        header.setPriority(frame.readByte());

        int attachmentJsonLength = frame.readInt();
        byte[] attachBytes = new byte[attachmentJsonLength];
        frame.readBytes(attachBytes);
        header.setAttachment(JSON.parseObject(
                new String(attachBytes, "UTF-8"), mapper));
        message.setHeader(header);

        int contentLen = frame.readInt();
        if (contentLen > 0)
        {
            byte[] bodyBytes = new byte[contentLen];
            frame.readBytes(bodyBytes);
            message.setContent(bodyBytes);
        }
        return message;
    }
}

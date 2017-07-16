package oops.content.customProtocol;

public class NettyMessage
{
    private Header header;

    private byte[] content;

    public Header getHeader()
    {
        return header;
    }

    public void setHeader(Header header)
    {
        this.header = header;
    }

    public byte[] getContent()
    {
        return content;
    }

    public void setContent(byte[] content)
    {
        this.content = content;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("NettyMessage{");
        sb.append("header=").append(header);
        sb.append(", content=");
        MessageContentType contentType = null;
        if (header != null)
        {
            contentType =
                    MessageContentType.get(header.getContentType());
        }
        if (contentType != null)
        {
            switch (contentType)
            {
                case BYTE:
                    sb.append(String.valueOf(content[0]));
                    break;
                case STRING:
                    sb.append(new String(content));
                    break;
                case BYTES:
                    sb.append("byte[]");
                    break;
                default:
                    sb.append("[unknown type]");
                    break;
            }
        }
        else
        {
            sb.append("null");
        }

        sb.append('}');
        return sb.toString();
    }
}

package oops.content.customProtocol;

import java.util.HashMap;
import java.util.Map;

public class Header
{
    // magic number + major version + minor version
    private int crcCode = 0xabef0101;

    // message length
    private int length;

    private long sessionId;

    private byte type;

    private byte contentType;

    private byte priority;

    private Map<String, Object> attachment = new HashMap<>();

    public int getCrcCode()
    {
        return crcCode;
    }

    public void setCrcCode(int crcCode)
    {
        this.crcCode = crcCode;
    }

    public int getLength()
    {
        return length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public long getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(long sessionId)
    {
        this.sessionId = sessionId;
    }

    public byte getType()
    {
        return type;
    }

    public void setType(byte type)
    {
        this.type = type;
    }

    public void setType(MessageType type)
    {
        this.type = type.getType();
    }

    public byte getContentType()
    {
        return contentType;
    }

    public void setContentType(byte contentType)
    {
        this.contentType = contentType;
    }

    public void setContentType(MessageContentType contentType)
    {
        this.contentType = contentType.getType();
    }

    public byte getPriority()
    {
        return priority;
    }

    public void setPriority(byte priority)
    {
        this.priority = priority;
    }

    public Map<String, Object> getAttachment()
    {
        return attachment;
    }

    public void setAttachment(Map<String, Object> attachment)
    {
        this.attachment = attachment;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Header{");
        sb.append("crcCode=").append(crcCode);
        sb.append(", length=").append(length);
        sb.append(", sessionId=").append(sessionId);
        sb.append(", type=").append(type);
        sb.append(", contentType=").append(contentType);
        sb.append(", priority=").append(priority);
        sb.append(", attachment=").append(attachment);
        sb.append('}');
        return sb.toString();
    }
}

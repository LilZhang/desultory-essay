package oops.content.customProtocol;

public enum MessageContentType
{
    BYTE(1, "字节"),

    STRING(2, "字符串"),

    BYTES(3, "字节数组")

    ;

    private final byte type;

    private final String message;

    MessageContentType(int type, String message)
    {
        this.type = ((byte) type);
        this.message = message;
    }

    public byte getType()
    {
        return type;
    }

    public String getMessage()
    {
        return message;
    }

    public static MessageContentType get(byte type)
    {
        for (MessageContentType anEnum : MessageContentType.values())
        {
            if (type == anEnum.type)
            {
                return anEnum;
            }
        }
        return null;
    }
}

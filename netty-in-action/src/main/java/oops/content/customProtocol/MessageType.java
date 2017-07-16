package oops.content.customProtocol;

public enum MessageType
{
    BUSINESS_REQ(0, "业务请求消息"),

    BUSINESS_RESP(1, "业务响应消息"),

    BUSINESS_ONEWAY(2, "业务 ONEWAY 消息"),

    HANDSHAKE_REQ(3, "握手请求消息"),

    HANDSHAKE_RESP(4, "握手应答消息"),

    HEARTBEAT_REQ(5, "心跳请求消息"),

    HEARTBEAT_RESP(6, "心跳应答消息"),

    ;

    private byte type;

    private String message;

    MessageType(int type, String message)
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

    public static MessageType get(byte type)
    {
        for (MessageType anEnum : MessageType.values())
        {
            if (type == anEnum.type)
            {
                return anEnum;
            }
        }
        return null;
    }
}

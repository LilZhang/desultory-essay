package oops.content.customProtocol;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NodeCheck
{
    private static final NodeCheck INSTANCE = new NodeCheck();

    private Map<String, Boolean> nodeCheck = new ConcurrentHashMap<>();

    private NodeCheck()
    {
    }

    public Map<String, Boolean> getMap()
    {
        return nodeCheck;
    }

    public static NodeCheck getInstance()
    {
        return INSTANCE;
    }
}

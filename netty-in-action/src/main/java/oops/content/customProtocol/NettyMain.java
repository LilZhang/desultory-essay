package oops.content.customProtocol;

public class NettyMain
{
    public static void main(String[] args)
    {
        try
        {
            switch (args[0])
            {
                case "client":
                    NettyClient.main(args);
                    break;

                case "server":
                    NettyServer.main(args);
                    break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}

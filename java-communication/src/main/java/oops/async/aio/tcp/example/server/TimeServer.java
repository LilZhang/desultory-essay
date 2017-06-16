package oops.async.aio.tcp.example.server;

public class TimeServer
{
    public static void main(String[] args)
    {
        int port = 8088;

        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        new Thread(timeServer, "AIO-AsyncTimeServerHandler-001").start();
    }
}

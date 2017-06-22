package oops.async.aio.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

public class TcpAioClient
{
    public void start(String content, InetSocketAddress address)
    {
        try
        {
            AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
            client.connect(address, null, new CompletionHandler<Void, Void>()
            {
                @Override
                public void completed(Void result, Void attachment)
                {
                    try
                    {
                        System.out.println("ready to send data: " + content);
                        client.write(ByteBuffer.wrap(content.getBytes())).get();
                        System.out.println("data sent!");
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    catch (ExecutionException e)
                    {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failed(Throwable exc, Void attachment)
                {
                    exc.printStackTrace();
                }
            });


            final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            client.read(byteBuffer, null, new CompletionHandler<Integer, Object>()
            {
                @Override
                public void completed(Integer result, Object attachment)
                {
                    System.out.println(result);
                    System.out.println("response: " + new String(byteBuffer.array()));
                }

                @Override
                public void failed(Throwable exc, Object attachment)
                {
                    exc.printStackTrace();
                }
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new TcpAioClient().start("test_content_for_aio", new InetSocketAddress("127.0.0.1", ));
    }
}

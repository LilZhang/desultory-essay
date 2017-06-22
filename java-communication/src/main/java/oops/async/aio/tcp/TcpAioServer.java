package oops.async.aio.tcp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpAioServer
{
    private final int port;

    private volatile boolean started = false;

    private final CountDownLatch latch = new CountDownLatch(1);

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public TcpAioServer(int port)
    {
        this.port = port;
//        startup();
    }

    public void startup()
    {
        if (started)
        {
            return;
        }

        new Thread(() ->
        {
            try
            {
                AsynchronousChannelGroup threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);
                try (AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(threadGroup))
                {
                    server.bind(new InetSocketAddress(port));

                    System.out.printf("TcpAioServer now listen on %d\n", port);

                    server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>()
                    {
                        final ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

                        @Override
                        public void completed(AsynchronousSocketChannel result, Object attachment)
                        {
                            System.out.println("waiting...");

                            try
                            {
                                byteBuffer.clear();
                                result.read(byteBuffer).get();
                                // echo
                                byteBuffer.flip();

                                System.out.println("received: " + new String(byteBuffer.array()));
                                System.out.println("echo it!");

                                result.write(byteBuffer);
                                byteBuffer.flip();
                            }
                            catch (InterruptedException | ExecutionException e)
                            {
                                e.printStackTrace();
                            }
                            finally
                            {
                                try
                                {
                                    result.close();
                                    server.accept(null, this);
                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }

                            System.out.println("done...");
                        }

                        @Override
                        public void failed(Throwable exc, Object attachment)
                        {
                            exc.printStackTrace();
                        }
                    });

                    try
                    {
                        // keep running
                        latch.await();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }, "AIO_AIO").start();
    }

    public void shutdown()
    {
        latch.countDown();
        started = false;
    }

    public static void main(String[] args)
    {

    }
}

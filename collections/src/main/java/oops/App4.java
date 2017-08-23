package oops;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App4
{
    private volatile boolean flag;

    public static void main(String[] args)
    {
        App4 app = new App4();
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() ->
        {
            synchronized (app)
            {
                try
                {
                    while (!Thread.interrupted())
                    {
                        while (app.flag == false)
                        {
                            app.wait();
                        }
                        System.out.println("turning off...");
                        TimeUnit.SECONDS.sleep(1);
                        app.flag = false;
                        app.notify();
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });

        executorService.submit(() ->
        {
            synchronized (app)
            {
                try
                {
                    while (!Thread.interrupted())
                    {
                        while (app.flag == true)
                        {
                            app.wait();
                        }
                        System.out.println("turning on...");
                        TimeUnit.SECONDS.sleep(1);
                        app.flag = true;
                        app.notify();
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}

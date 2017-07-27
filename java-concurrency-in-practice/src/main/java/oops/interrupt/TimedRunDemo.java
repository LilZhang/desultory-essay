package oops.interrupt;

import java.util.concurrent.*;

public class TimedRunDemo
{
    private static final ExecutorService executorService =
            Executors.newCachedThreadPool();

    public static void timedRun(final Runnable r, long timeout, TimeUnit unit)
            throws InterruptedException // do not swallow it
    {
        Future<?> future = executorService.submit(r);
        try
        {
            future.get(timeout, unit);
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
            // 重新抛出异常
            // FIXME: 2017/7/25 fix it
            throw ((InterruptedException) e.getCause());
        }
        catch (TimeoutException e)
        {
            e.printStackTrace();
        }
        finally
        {
            future.cancel(true);    // interrupt if running
        }
    }
}

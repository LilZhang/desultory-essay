package oops.interrupt;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CancelledDemo
{
}

// 这个例子用来获取线程池中, 被取消的任务
// 未被正确运行的任务
abstract class Tracking extends AbstractExecutorService
{
    private final ExecutorService exec = Executors.newCachedThreadPool();

    private final Set<Runnable> tasksCancelledAtShutdown =
            Collections.synchronizedSet(new HashSet<>());

    public List<Runnable> getCancelledTasks()
    {
        if (!exec.isTerminated())
        {
            throw new IllegalStateException();
        }
        return new ArrayList<>(tasksCancelledAtShutdown);
    }

    @Override
    public void execute(Runnable runnable)
    {
        exec.execute(() ->
        {
            try
            {
                runnable.run();
            }
            finally
            {
                if (isShutdown()
                        && Thread.currentThread().isInterrupted())
                {
                    tasksCancelledAtShutdown.add(runnable);
                }
            }
        });
    }
}

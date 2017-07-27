package oops.completionService;

import java.util.List;
import java.util.concurrent.*;


// CompletionService 整合了 Executor 和 BlockingQueue 功能
// 既能向其中提交 callable
// 也能像 BlockingQueue 那样从队列中先 take 出已执行好的结果

// 以下为 一个使用例子
// 向 CompletionService 中提交了若干耗时长短不一的任务
// 从 CompletionService 中获取(作为队列)时, 先执行完的结果被先获取出来
// 查看输出即可
public class CompletionServiceDemo
{
    private static final int TASK_CNT = 5;

    public static void main(String[] args)
    {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(threadPool);

        for (int i = 0; i < TASK_CNT; i++)
        {
            int j = i;
            completionService.submit(() ->
            {
                int sleepSec = (int) (3 + 10 * Math.random());
                System.out.printf("now start task %d, ready to sleep for %d seconds\n", j, sleepSec);

                TimeUnit.SECONDS.sleep(sleepSec);

//                System.out.printf("task %d return, sleep for %d seconds\n", j, sleepSec);
                return j;
            });
        }

        // submit 顺序如下
        // now start task 0, ready to sleep for 5 seconds
        // now start task 1, ready to sleep for 12 seconds
        // now start task 2, ready to sleep for 8 seconds
        // now start task 3, ready to sleep for 3 seconds
        // now start task 4, ready to sleep for 7 seconds

        long millis = System.currentTimeMillis();
        for (int i = 0; i < TASK_CNT; i++)
        {
            try
            {
                Future<Integer> future = completionService.take();
                System.out.println("completionService get the future...");
                Integer integer = future.get();
                System.out.printf("completionService get the result of future, result: %d, time elasped: %d\n",
                        integer, System.currentTimeMillis() - millis);
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

        // 从队列中获取的顺序如下, 先执行完成的先被获取
        // completionService get the future...
        // completionService get the result of future, result: 3, time elasped: 3033
        // completionService get the future...
        // completionService get the result of future, result: 0, time elasped: 5029
        // completionService get the future...
        // completionService get the result of future, result: 4, time elasped: 7029
        // completionService get the future...
        // completionService get the result of future, result: 2, time elasped: 8033
        // completionService get the future...
        // completionService get the result of future, result: 1, time elasped: 12034

        threadPool.shutdownNow();
    }
}

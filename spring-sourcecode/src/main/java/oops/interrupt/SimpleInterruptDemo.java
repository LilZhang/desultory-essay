package oops.interrupt;

import java.util.concurrent.TimeUnit;

// 正确的检测中断的方式
// 不清除中断状态, 可能是顾及上层获取该线程中断状态并处理的需求
public class SimpleInterruptDemo extends Thread
{
    @Override
    public void run()
    {
        try
        {
            // 正确的检测中断的方式
            // 当然, 除非确实需要清除中断状态除外
            while (!Thread.currentThread().isInterrupted())
            // while (!Thread.interrupted())
            {
                // TODO: 2017/7/25 do something
                TimeUnit.SECONDS.sleep(5);
                System.out.println("loop");
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }


        System.out.println("thread ternimated.");
    }

    public void cancel()
    {
        interrupt();
    }

    public static void main(String[] args)
    {
        SimpleInterruptDemo task = new SimpleInterruptDemo();
        task.start();
        try
        {
            TimeUnit.SECONDS.sleep(10);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        task.cancel();
    }
}

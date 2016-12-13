package oops;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class CheckGC
{
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args)
    {
        byte[] allocate1, allocate2, allocate3, allocate4;
        for (int i = 0; i < 50; i++)
        {
            System.out.format("--------------> start allocate %d <--------------\n", i + 1);
            allocate1 = new byte[ 2 * _1MB ];
            allocate2 = new byte[ 2 * _1MB ];
            allocate3 = new byte[ 2 * _1MB ];
            allocate4 = new byte[ 4 * _1MB ];
            try
            {
                TimeUnit.SECONDS.sleep(10);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            System.out.println();
        }
    }
}
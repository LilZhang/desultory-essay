/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-08-24
 * Project        : desultory-essay
 * File Name      : TestSync.java
 */
public class TestSync
{
    private static class IncreInt implements Callable<List<Integer>>
    {
        int begin = 0;

        List<Integer> result = new ArrayList<Integer>();

        public List<Integer> call() throws Exception
        {
            for (int i = 0; i < 16; i++)
            {
                result.add(++begin);
            }
            return result;
        }
    }

    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newCachedThreadPool();

        List<Future<List<Integer>>> result = new ArrayList<Future<List<Integer>>>();
        for (int i = 0; i < 800; i++)
        {
            /*Future<List<Integer>> submit = executorService.submit(new Callable<List<Integer>>()
            {
                int begin = 0;

                List<Integer> result = new ArrayList<Integer>();

                public List<Integer> call() throws Exception
                {
                    for (int i = 0; i < 8; i++)
                    {
                        result.add(++begin);
                    }
                    return result;
                }
            });*/

            Future<List<Integer>> submit = executorService.submit(new IncreInt());

            result.add(submit);
        }

        for (Future<List<Integer>> future : result)
        {
            try
            {
                List<Integer> list = future.get();
                System.out.println(Arrays.toString(list.toArray()));
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
    }
}

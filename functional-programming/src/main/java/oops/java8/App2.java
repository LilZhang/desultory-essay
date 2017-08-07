package oops.java8;

import oops.ResourceUtil;

public class App2
{
    public static void main(String[] args)
    {
        System.out.println("runnable.test.data1: " + ResourceUtil.readProperty("runnable.test.data1"));
        System.out.println("runnable.test.data2: " + ResourceUtil.readProperty("runnable.test.data2"));
        System.out.println("runnable.test.data3: " + ResourceUtil.readProperty("runnable.test.data3"));
    }
}

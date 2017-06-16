package oops;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
        System.out.println("runnable.test.data1: " + ResourceUtil.readProperty("runnable.test.data1"));
        System.out.println("runnable.test.data2: " + ResourceUtil.readProperty("runnable.test.data2"));
        System.out.println("runnable.test.data3: " + ResourceUtil.readProperty("runnable.test.data3"));
    }
}

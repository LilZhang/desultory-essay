package oops;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Map<String, String> getenv = System.getenv();
        System.out.println( "Hello World!" );
    }

    private <T> List<T> list()
    {
        return Collections.emptyList();
    }

    public void m()
    {
        List<String> list = list();
        m2(this.<String>list());
    }

    private void m2(List<String> list)
    {

    }

    private <T, E> void f1(List<T> list, List<E> list2)
    {

    }
}

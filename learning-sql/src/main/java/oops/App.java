package oops;

import java.util.Date;
import java.util.Iterator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        System.out.println( new Date().getTime());

        IterableObj<String> iterableObj =
                new IterableObj<String>(new String[]{"aaa", "bbb", "ccc", "ddd"});

        for (String s : iterableObj)
        {
            System.out.println(s);
        }

        IterableObj<Integer> iterableObj1 =
                new IterableObj<Integer>(new Integer[]{1,2,666,888,999,3});

        for (Integer integer : iterableObj1)
        {
            System.out.println(integer);
        }
    }
}

class IterableObj<T> implements Iterable<T>
{
    private Itr<T> itr;

    public IterableObj(T[] elementData)
    {
        this.itr = new Itr<T>(elementData);
    }

    public Iterator<T> iterator()
    {
        return itr;
    }
}

class Itr<T> implements Iterator<T>
{
    private int lo = 0;

    private int size;

    private T[] elementData;

    public Itr(T[] elementData)
    {
        this.elementData = elementData;
        this.size = elementData.length;
    }

    public boolean hasNext()
    {
        return lo < size;
    }

    public T next()
    {
        return elementData[lo++];
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }
}
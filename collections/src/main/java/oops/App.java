package oops;

import java.util.Iterator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int i = Integer.MAX_VALUE;
        String s = Integer.toBinaryString(i);
        System.out.println("i: " + s);
        System.out.println("bit len: " + s.length());

        int ip = i + 345654;

        System.out.println("i > ip: " + (i > ip));
        System.out.println("ip - i: " + (ip - i));
    }

    private static int get1s(int i)
    {
        int cnt = 0, n = 1;

        while (i >= n)
        {
            if ((i & n) == n)
            {
                cnt++;
            }
            n <<= 1;
        }

        return cnt;
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
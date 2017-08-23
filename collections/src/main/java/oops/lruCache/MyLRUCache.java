package oops.lruCache;

import java.util.LinkedHashMap;
import java.util.Map;

public class MyLRUCache<K, V> extends LinkedHashMap<K, V>
{
    private int capacity;

    public MyLRUCache(int capacity)
    {
        super(16, 0.75f, true);     // access order 为 true
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest)
    {
        return super.size() > capacity;
    }
}

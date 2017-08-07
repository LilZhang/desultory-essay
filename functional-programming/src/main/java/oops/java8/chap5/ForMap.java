package oops.java8.chap5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForMap
{
    // 请注意三种情况
    // 1. key-value 都存在，且 value 不为 null
    // 2. key-value 存在，但 value 为 null
    // 3. key-value 不存在

    // try getOrDefault
    private static void defaultMapValue()
    {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "something");
        map.put("key2", null);
        // 没有 key3

        System.out.println(map.getOrDefault("key1", "default"));    // something
        System.out.println(map.getOrDefault("key2", "default"));    // null
        System.out.println(map.getOrDefault("key3", "default"));    // default
    }

    // try computeIfAbsent
    private static Map<String, String> putValueIfAbsent()
    {
        List<String> keyList = Arrays.asList("key1", "key2", "key3");

        Map<String, String> map = new HashMap<>();
        map.put("key1", "already present");
        map.put("key2", null);
        // 没有 key3

        keyList.forEach((keyStr) -> map.computeIfAbsent(keyStr, (key) -> "value of " + key));
//        keyList.forEach((keyStr) -> map.putIfAbsent(keyStr, "value of " + keyStr));

        System.out.println(map);
        // {key1=already present, key2=value of key2, key3=value of key3}

        return map;
    }

    // try computeIfPresent
    private static Map<String, String> putValueIfPresent()
    {
        Map<String, String> map = putValueIfAbsent();
        map.put("key4", null);
        map.put("key5", null);

        // 键 "key4" 与 "key5" 的 value 为 null, 不受影响
        // 键 "key6" 不存在, 不受影响
        // computeIfPresent 不会对其有影响
        List<String> keyList = Arrays.asList("key1", "key2", "key3", "key4", "key5", "key6");
        keyList.forEach((keyStr) -> map.computeIfPresent(keyStr, (key, value) -> value + " is present"));

        System.out.println(map);
        // {key1=already present is present, key2=value of key2 is present, key5=null, key3=value of key3 is present, key4=null}

        return map;
    }

    private static void tryMerge()
    {
        Map<String, String> strMap = new HashMap<>();
        strMap.put("sKey1", "str1");
//        strMap.put("sKey2", "str2");
//        strMap.put("sKey3", "str3");

        strMap.merge("sKey1", " value to merge", (oldValue, valueToMerge) -> new StringBuilder(oldValue).append(valueToMerge).toString());
        System.out.println(strMap.get("sKey1"));

        Map<String, Integer> intMap = new HashMap<>();
        intMap.put("iKey1", 465);
//        intMap.put("iKey2", 233);
//        intMap.put("iKey3", 187);

        intMap.merge("iKey1", 666, (oldValue, valueToMerge) -> oldValue + valueToMerge);
        System.out.println(intMap.get("iKey1"));
    }

    public static void main(String[] args)
    {
//        defaultMapValue();
//        putValueIfAbsent();
//        putValueIfPresent();
        tryMerge();
    }
}

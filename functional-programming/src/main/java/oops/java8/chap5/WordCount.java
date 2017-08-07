package oops.java8.chap5;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCount
{
    private static Map<String, Long> wordCount()
    {
        Map<String, Long> stat = Stream.of("John", "Paul", "George", "John", "Paul", "John", "John", "Paul", "George", "John", "Paul", "John")
                .collect(Collectors.groupingBy((str) -> str, Collectors.counting()));
        return stat;
    }

    // 这尼玛
    private static Map<String, Integer> myWordCount()
    {
        Map<String, Integer> stat = Stream.of("John", "Paul", "George", "John", "Paul", "John", "John", "Paul", "George", "John", "Paul", "John")
                .reduce(new HashMap<String, Integer>(),
                        (map, str) ->
                        {
                            map.merge(str, 1, (oldValue, value) -> oldValue + value);
                            return map;
                        },
                        (map1, map2) ->
                        {
                            /*Set<String> stringSet = new HashSet<>(map1.keySet());
                            stringSet.addAll(map2.keySet());
                            HashMap<String, Integer> sumMap = new HashMap<>();
                            stringSet.forEach((str) -> sumMap.put(str, map1.getOrDefault(str, 0) + map2.getOrDefault(str, 0)));
                            return sumMap;*/

                            // 将 map2 中有的键值合并至 map1 中
                            map2.keySet().forEach((m2key) -> map1.merge(m2key, map2.get(m2key), (oldValue, value) -> oldValue + map2.get(m2key)));
                            return map1;
                        }
                );

        return stat;
    }

    public static void main(String[] args)
    {
//        System.out.println(wordCount());
        System.out.println(myWordCount());

        // {George=2, John=6, Paul=4}
    }
}

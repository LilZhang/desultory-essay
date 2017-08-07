package oops.java8.chap3.mergestr;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

public class MergeString
{
    public static void main(String[] args)
    {
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");

        String string1 = list.stream()
                .reduce(new StringCombiner(",", "[", "]"),                          // 初始字符串容器
                        (stringCombiner, str) -> stringCombiner.append(str),                            // 将字符串添加至容器中
                        (stringCombiner1, stringCombiner2) -> stringCombiner1.append(stringCombiner2))  // 合并容器
                .toString();

        System.out.println("reduce: " + string1);
        // reduce: [aa,bb,cc,dd]


        String string2 = list.stream().collect(Collector.of(
                () -> new StringCombiner(",", "[", "]"),                        // 容器工厂
                (stringCombiner, str) -> stringCombiner.append(str),                            // 将字符串添加至容器
                (stringCombiner1, stringCombiner2) -> stringCombiner1.append(stringCombiner2),  // 容器间合并
                (stringCombiner) -> stringCombiner.toString()                                   // 从最后的容器取出字符串
        ));

        System.out.println("collector: " + string2);
        // collector: [aa,bb,cc,dd]
    }
}

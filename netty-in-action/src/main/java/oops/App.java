package oops;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // [0, 36)
        List<String> keyList = IntStream.range(0, 49)
                .boxed()
                .map((i) -> String.valueOf(i))
                .collect(Collectors.toList());

        System.out.println();

        int threshold = 16;
        int from = 0;
        while (from < keyList.size())
        {
            int to = from + threshold >= keyList.size() ? keyList.size() : from + threshold;
            List<String> keySubList = keyList.subList(from, to);

            System.out.println(keySubList.stream()
                    .collect(Collectors.joining("\t")));

            from = to;
        }

    }


}

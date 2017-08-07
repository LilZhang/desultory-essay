package oops.java8.chap3;

import oops.java8.model.Album;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App
{
    private static final List<Track> TRACK_LIST = Stream.of(new Track(3, "aaa"), new Track(7, "bbb"), new Track(5, "ccc"), new Track(2, "ddd"), new Track(0, "eee"))
            .collect(Collectors.toList());

    private static long streamCount()
    {
        return Stream.of("aa", "bb", "cc", "dd")
                .filter((str) -> !str.startsWith("aa"))
                .count();
    }

    private static List<String> genList()
    {
        return Stream.of("aaa", "bbbb", "cccc", "dddd")
                .filter((str) -> str.length() > 3)  // 符合条件的留下
                .collect(Collectors.toList());
        // .collect(Collectors.toSet())
    }

    private static List<String> transLowerToUpper()
    {
        return Stream.of("aaa", "bbb", "ccc", "ddd")
                .filter((str) -> !"ddd".equals(str))
                .map((str) -> str.toUpperCase())
                .collect(Collectors.toList());
    }

    private static List<Integer> listList()
    {
        return Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap((intList) -> intList.stream())     // 展开
                .collect(Collectors.toList());
    }

    private static Track getMax()
    {
        return TRACK_LIST.stream()
                .max(Comparator.comparing((track) -> track.getValue()))
                .get();
    }

    // not recommended
    private static int reduceSum()
    {
        return Stream.of(1, 3, 5)
                .reduce(0, (acc, ele) -> acc + ele);
    }

    private static int mySum()
    {
        OptionalDouble average = TRACK_LIST.stream()
                .mapToInt((track) -> track.getValue())
                .average();

        return TRACK_LIST.stream()
                .mapToInt((track) -> track.getValue())
                .sum();
        // .min() .max() .average()
    }

    private static int countMembers()
    {
        List<Album> list = Collections.emptyList();
        return list.stream()
                .map((album) -> album.getMusicians().size())  // npe
                .reduce(0, Integer::sum);
    }

    private static List<String> findMusicianOrigins()
    {
        Album album = new Album();
        return album.getMusicians().stream()
                .filter((musician) -> musician.getOrigin().startsWith("The"))   // 符合条件的留下
                .map((musician) -> musician.getOrigin())
                .distinct()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());
    }

    private static int countLower()
    {
        String str = "sssssDDDDDsssss";

        return ((int) str.chars()
                .filter((i) -> (Character.isLowerCase((char) i)))
                .count());
    }

    // 。。。。。
    private static String findMostLower(List<String> list)
    {
        return list.stream()
                .max(Comparator.comparing((str) ->
                {
                    return (int) str.chars()
                            .filter((i) -> Character.isLowerCase(((char) i)))
                            .count();
                })).get();
    }

    public static void main(String[] args)
    {
        System.out.println(findMostLower(Arrays.asList("sss", "sssDZ", "sssRTY")));
    }

    private static class Track
    {
        private String content;

        private int value;

        public Track(int value, String content)
        {
            this.content = content;
            this.value = value;
        }

        public String getContent()
        {
            return content;
        }

        public int getValue()
        {
            return value;
        }
    }
}

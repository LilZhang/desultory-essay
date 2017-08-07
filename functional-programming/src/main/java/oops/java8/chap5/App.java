package oops.java8.chap5;

import oops.java8.model.Album;
import oops.java8.model.Artist;

import java.util.*;
import java.util.stream.Collectors;

public class App
{
    // 获取成员数最多的乐队
    private static Artist getMemberMaxArtist()
    {

        List<Artist> list = Collections.emptyList();
        return list.stream()
                .collect(Collectors.maxBy(Comparator.comparing((artist) -> artist.getMembers().size())))
                .get();

        // see also Collectors.minBy()

        // 这样可以么
         /*list.stream()
                 .max(Comparator.comparing((artist) -> artist.getMembers().size()))
                 .orElse(null);*/
    }

    // 获取专辑中单曲的平均数
    private static double getAvgTrackNum()
    {
        List<Album> list = Collections.emptyList();
        return list.stream()
                .collect(Collectors.averagingInt((album) -> album.getTracks().size()));

        // see also Collectors.summingInt()

        /*list.stream()
                .mapToInt((album) -> album.getTracks().size())
                .average()
                .orElse(0d);*/
    }

    // 根据条件分组 (true/false)
    private static Map<Boolean, List<Artist>> partByOriginPrefix()
    {
        List<Artist> list = Collections.emptyList();
        return list.stream()
                .collect(Collectors.partitioningBy((artist) -> artist.getOrigin().startsWith("aa")));

        // see also Collectors.groupingBy()
    }

    // 根据条件分组 (origin name)
    private static Map<String, List<Artist>> groupBy()
    {
        List<Artist> list = Collections.emptyList();
        return list.stream()
                .collect(Collectors.groupingBy((artist) -> artist.getOrigin()));
    }

    // 组合字符串
    private static String genArtistNames()
    {
        List<Artist> list = Collections.emptyList();
        return list.stream()
                .map((artist) -> artist.getName())
                .collect(Collectors.joining(", ", "[", "]"));

        // [name1, name2, name3]
    }

    // 通过一堆专辑统计每个艺术家有多少专辑
    private static Map<Artist, Long> statArtistAlbumNum()
    {
        List<Album> list = Collections.emptyList();
        return list.stream()
                .collect(Collectors.groupingBy((album) -> album.getMainMusician(), Collectors.counting()));
    }

    // 获取一堆专辑中每个艺术家的专辑名列表
    private static Map<Artist, List<String>> getArtistAlbumName()
    {
        List<Album> list = Collections.emptyList();
        return list.stream()
                .collect(Collectors.groupingBy((album) -> album.getMainMusician(),
                        Collectors.mapping((album) -> album.getName(), Collectors.toList())));
    }



    public static void main(String[] args)
    {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        String appends = list.stream()
                .filter((i) -> i != 3)
                .map((i) -> String.valueOf(i))
                .collect(Collectors.joining(", ", "{", "}"));

        System.out.println(appends);
    }
}

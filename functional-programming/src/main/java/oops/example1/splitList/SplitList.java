package oops.example1.splitList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SplitList
{
    public static void main(String[] args)
    {
//        List<Integer> collect = IntStream.rangeClosed(1, 135).boxed().collect(Collectors.toList());
//
//        List<Integer> bufList = new ArrayList<>();
//        collect.forEach((map) ->
//        {
//            bufList.add(map);
//            if (bufList.size() == 25)
//            {
//                System.out.println(bufList.stream().map((i) -> String.valueOf(i)).collect(Collectors.joining("\t")));
//                bufList.clear();
//            }
//        });

        // finaaaaa
//        System.out.println(bufList.stream().map((i) -> String.valueOf(i)).collect(Collectors.joining("\t")));
//        bufList.clear();

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 10);
        List<Integer> list2 = Arrays.asList(4, 5, 6, 7, 8, 9, 10);

        ArrayList<Integer> cross = new ArrayList<>(list1);
        cross.retainAll(list2);
        printList(cross);

        ArrayList<Integer> list1Only = new ArrayList<>(list1);
        list1Only.removeAll(list2);
        printList(list1Only);

        ArrayList<Integer> list2Only = new ArrayList<>(list2);
        list2Only.removeAll(list1);
        printList(list2Only);

//        IntStream.rangeClosed(1, 134).boxed().reduce(new ArrayList<Integer>(), (baseList, i) ->
//        {
//
//        }, (list1, list2) -> {container.add(list1);container.add(list2)});

    }

    private static <T> void printList(List<T> list)
    {
        System.out.println(list.stream().map((i) -> String.valueOf(i)).collect(Collectors.joining("\t")));
    }
}

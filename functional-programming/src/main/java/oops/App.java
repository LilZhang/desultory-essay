package oops;

import java.util.ArrayList;
import java.util.List;

public class App
{
    private static final List<Integer> LIST = new ArrayList<>();

    static
    {
        LIST.add(3);
        LIST.add(2);
        LIST.add(7);
        LIST.add(6);
        LIST.add(8);
    }

    public static void main(String[] args)
    {
        LIST.stream()
                .filter((i) -> i >= 6)
                .forEach((i) -> System.out.println(">= 6: " + i));
    }
}

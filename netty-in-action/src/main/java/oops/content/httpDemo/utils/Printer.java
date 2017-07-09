package oops.content.httpDemo.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Printer
{
    public static void print(String className, String method, Object... args)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("method invoked, Class: ");
        sb.append(className);
        sb.append(", method: ");
        sb.append(method);
        sb.append(", args: ");
        String argStr = Arrays.stream(args)
                .map((o) -> o.toString())
                .collect(Collectors.joining(", "));
        sb.append(argStr);
        System.out.println(sb.toString());
    }
}

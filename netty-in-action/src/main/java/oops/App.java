package oops;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        String[] phoneNumbers = new String[]{"12345", "45678", "56789"};
        String phStr = Arrays.stream(phoneNumbers)
                .collect(Collectors.joining(","));

        System.out.println(phStr);
    }
}

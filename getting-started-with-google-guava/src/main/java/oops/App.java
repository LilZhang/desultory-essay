package oops;

import org.apache.commons.lang.StringUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        if (args.length > 0 && StringUtils.isNotBlank(args[0]))
        {
            System.out.printf("echo: %s\n", args[0]);
        }
        else
        {
            System.out.println("no args");
        }
    }
}

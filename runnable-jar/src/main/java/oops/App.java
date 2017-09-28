package oops;

import java.security.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
//        System.out.println("runnable.test.data1: " + ResourceUtil.readProperty("runnable.test.data1"));
//        System.out.println("runnable.test.data2: " + ResourceUtil.readProperty("runnable.test.data2"));
//        System.out.println("runnable.test.data3: " + ResourceUtil.readProperty("runnable.test.data3"));


//        getProviders

        try
        {
            KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA", "BC");
            Provider[] providers = Security.getProviders();

            System.out.println();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchProviderException e)
        {
            e.printStackTrace();
        }


    }
}

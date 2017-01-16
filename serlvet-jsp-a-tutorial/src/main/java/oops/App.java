package oops;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final String FILE_PATH = "/your/file/path/here.avi";

    public static void main( String[] args )
    {
        InputStream in = null;
        try
        {
            in = new BufferedInputStream(new FileInputStream(FILE_PATH));
            int i;
            while ((i = (in.read())) != -1)
            {
                char c = ((char) i);
                char decoded = decode(c);
                if (decoded == '/')
                {
                    // do something
                }
            }

            System.out.println();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    // ignore
                }
            }
        }
    }

    private static char decode(char c)
    {
        // your decode rule here
        return c;
    }
}
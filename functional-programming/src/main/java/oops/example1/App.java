package oops.example1;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class App
{
    private static final String PATH = "D:\\work\\scel\\scel_txt\\stock.txt";
//    private static final String PATH_DIC = "D:\\myown\\myproj1\\my-proj-1\\elasticsearch-analysis-ik\\config\\preposition.dic";

    public static void main(String[] args)
    {
        InputStream in = null;

        try
        {
            in = new BufferedInputStream(new FileInputStream(PATH));
            int s;
            byte[] buffer = new byte[4096];
            StringBuilder sb = new StringBuilder();
            while ((s = in.read(buffer)) != -1)
            {
                sb.append(new String(buffer, "UTF-8"));
                System.out.println();
            }
        }
        catch (IOException e)
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e1)
                {
                    // ignore
                }
            }
        }
        finally
        {

        }
    }
}

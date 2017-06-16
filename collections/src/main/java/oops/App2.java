package oops;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * Description:
 * <p/>
 * Create Author  : Administrator
 * Create Date    : 2016-09-19
 * Project        : desultory-essay
 * File Name      : App2.java
 */
public class App2
{
    // 168641447
    private static final String PATH = "C:\\Users\\Administrator\\Desktop\\test_files\\Personas_zhuzy-20170605141716-part-00001.csv.gz";

    public static void main(String[] args)
    {
        m2();
//        m1();
    }


    private static void m1()
    {
        BufferedReader reader = null;
        try
        {
            GZIPInputStream in = new GZIPInputStream(new FileInputStream(PATH));
            System.out.println("size: " + in.available());
            reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            String line;

            int cnt = 0;
            int acc = 0;
            while ((line = reader.readLine()) != null)
            {
                int length = (line + "\n").getBytes().length;
                acc += length;

                if (++cnt % 10000 == 0)
                {
                    System.out.printf("line num: %d, byte len: %d, acc: %d\n", cnt, length, acc);
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (IOException e)
            {
                // ignore
            }
        }
    }

    private static void m2()
    {
        InputStream in = null;
        try
        {
            in = new BufferedInputStream(new GZIPInputStream(new FileInputStream(PATH)));

            byte[] buffer = new byte[4096];
            int cnt = 0;
            int s;
            while ((s = in.read(buffer, 0, buffer.length)) != -1)
            {
//                String ss = new String(buffer);
//                System.out.println(ss);
                cnt += s;
            }

            System.out.println("size: " + cnt);
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
                    e.printStackTrace();
                }
            }
        }
    }
}


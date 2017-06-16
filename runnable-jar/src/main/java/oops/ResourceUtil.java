package oops;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceUtil
{
    public static String readProperty(String key)
    {
        String result = null;
        InputStream inputStream = ResourceUtil.class.getClassLoader().getResourceAsStream("oops/data.properties");
        Properties properties = new Properties();
        try
        {
            properties.load(inputStream);
            result = properties.getProperty(key);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return result;
    }
}

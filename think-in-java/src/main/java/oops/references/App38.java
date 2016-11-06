/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.references;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-04
 * Project        : desultory-essay
 * File Name      : App38.java
 */
public class App38
{
    private static final String UTF_8 = "UTF-8";

    private static final String PAGE_URL = "http://www.baidu.com";

    private static final HashMap<String, String> HEADER = new HashMap<String, String>();

    static
    {
        HEADER.put("Accept", "*/*");
        HEADER.put("Connection", "Keep-Alive");
        HEADER.put("User-Agent",
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
    }

    public static void main(String[] args)
    {
        try
        {
            URL url = new URL(PAGE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            for (Map.Entry<String, String> entry : HEADER.entrySet())
            {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            connection.connect();

            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), UTF_8));
            String line;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line).append("\n");
            }

            System.out.println(sb.toString());

            connection.disconnect();
            reader.close();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

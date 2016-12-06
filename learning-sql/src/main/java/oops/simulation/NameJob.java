/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops.simulation;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2016-11-24
 * Project        : desultory-essay
 * File Name      : NameJob.java
 */
public class NameJob
{
    private static final String[] LANGUAGES = new String[]
            {
                    /*"spanish",
                    "english",
                    "portuguese",
                    "chinese",
                    "french",
                    "germanic",
                    "hindi",
                    "italian",
                    "japanese",
                    "korean",
                    "russian",
                    "arabic",
                    "turkish",
                    "danish",
                    "swedish",
                    "dutch",*/
                    "polish"
                    /*,
                    "persian",
                    "vietnamese",
                    "hebrew"*/
            };

    private static final String URL = "http://babynames.net/all/";

    private static final File FILE = new File("/home/lilzhang/dev/names.txt");

    public static void main(String[] args)
    {
//        System.out.println(getHTML("http://babynames.net/all/spanish?page=11"));
        outter: for (String language : LANGUAGES)
        {
            int i = 1;
            for (;;)
            {
                StringBuilder sb = new StringBuilder(URL);
                sb.append(language);
                if (i != 1)
                {
                    sb.append("?page=");
                    sb.append(i);
                }
                ++i;

                String link = sb.toString();
                System.out.println("-> " + link);

                String html = getHTML(link);
                if (html == null)
                {
                    System.out.println("get html failed, try again...");
                    --i;
                    continue;
                }
                Document doc = Jsoup.parse(html);
                Elements elements = doc.select("ul.names-results.listing-view li a");
                if (elements == null || elements.size() == 0)
                {
                    continue outter;
                }
                for (Element element : elements)
                {
                    NameObj name = new NameObj();
                    name.setLanguage(language);

                    Elements gender = element.select("span.result-gender");
                    if (gender != null && gender.size() > 0)
                    {
                        if (gender.get(0).hasClass("boy"))
                        {
                            name.setGender("boy");
                        }
                        else if (gender.get(0).hasClass("girl"))
                        {
                            name.setGender("girl");
                        }
                        else if (gender.get(0).hasClass("boygirl"))
                        {
                            name.setGender("boygirl");
                        }
                    }

                    Elements nameEle = element.select("span.result-name");
                    if (nameEle != null && nameEle.size() > 0)
                    {
                        String s = nameEle.get(0).html();
                        if (s != null)
                        {
                            name.setName(s.trim());
                        }
                    }
                    try
                    {
                        FileUtils.write(FILE, name.format(), true);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static String getHTML(String url)
    {
        InputStream in = null;
        StringBuilder sb = null;
        try
        {
            URL u = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            connection.connect();
            in = connection.getInputStream();
            sb = new StringBuilder();
            int s;
            byte[] buffer = new byte[1024];
            while ((s = in.read(buffer)) != -1)
            {
                sb.append(new String(buffer, 0, s, "UTF-8"));
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
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

        if (sb != null)
        {
            return sb.toString();
        }
        else
        {
            return null;
        }
    }

    private static class NameObj
    {
        private String name;

        private String gender;

        private String language;

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getGender()
        {
            return gender;
        }

        public void setGender(String gender)
        {
            this.gender = gender;
        }

        public String getLanguage()
        {
            return language;
        }

        public void setLanguage(String language)
        {
            this.language = language;
        }

        public String format()
        {
            final StringBuilder sb = new StringBuilder();
            sb.append(language).append(",")
                    .append(name).append(",")
                    .append(gender).append(";\n");
            return sb.toString();
        }
    }
}

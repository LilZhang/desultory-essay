/*
 *
 * Copyright (c) 2010-2015 by Shanghai HanTao Information Co., Ltd.
 * All rights reserved.
 *
 */

package oops;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Description:
 * <p>
 * Create Author  : lilzhang
 * Create Date    : 2017-02-05
 * Project        : desultory-essay
 * File Name      : App.java
 */
public class App
{



    public static void main(String[] args) throws Exception
    {
//        doIt("https://www.baidu.com/");
//        doIt("http://61.129.248.162:8051");
    }

    private static void doIt() throws Exception
    {
//        String url = String.format(GETFILE_URL_FORMAT, INTERFACE_NAME, token, file.getFileName(), ROLE_ID);

        // post请求返回结果
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("");
        CloseableHttpResponse response = null;

        try
        {
            // 设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
            httpPost.setConfig(requestConfig);

            response = httpClient.execute(httpPost);
            //请求发送成功，并得到响应
            int status = response.getStatusLine().getStatusCode();
            if (true)
            {
                InputStream inputStream = response.getEntity().getContent();
                handleInputStream(inputStream);
                inputStream.close();                    // xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
            }
            else
            {
                System.err.println("Post请求提交失败:" + "");
                throw new Exception("Post请求提交失败，Status code:" + status);
            }
        }
        finally
        {
            if (response != null)
            {
                response.close();
            }
            httpPost.releaseConnection();
            httpClient.close();
        }
    }

    public static void handleInputStream(InputStream inputStream) throws IOException
    {
        if (inputStream == null)
        {
            throw new NullPointerException("File 对应的输入流为空.");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(/*new GZIPInputStream(inputStream)*/inputStream, "UTF-8"));

        String content;
        while ((content = reader.readLine()) != null)
        {
            System.out.println(content);
        }
    }
}

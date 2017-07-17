/*
package oops.content.httpUtilTest;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

*/
/**
 * http工具类
 * Created by liruipeng on 2017/3/16.
 *//*

public class HttpUtil
{
    private static MultiThreadedHttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();

    private HttpClient client = new HttpClient(httpConnectionManager);

    public static HttpClient getHttpClient()
    {
    if (null== customerHttpClient)
    {
//        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
//        requestConfigBuilder

        HttpParams params =new BasicHttpParams();
        // 设置一些基本参数
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params,
                CHARSET);
        HttpProtocolParams.setUseExpectContinue(params, true);
        HttpProtocolParams
                .setUserAgent(
                        params,
                        "Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) "
                                +"AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1");
        // 超时设置
*/
/* 从连接池中取连接的超时时间 *//*

        ConnManagerParams.setTimeout(params, 1000);
            */
/* 连接超时 *//*

        HttpConnectionParams.setConnectionTimeout(params, 2000);
            */
/* 请求超时 *//*

        HttpConnectionParams.setSoTimeout(params, 4000);

        // 设置我们的HttpClient支持HTTP和HTTPS两种模式
        SchemeRegistry schReg =new SchemeRegistry();
        schReg.register(new Scheme("http", PlainSocketFactory
                .getSocketFactory(), 80));
        schReg.register(new Scheme("https", SSLSocketFactory
                .getSocketFactory(), 443));

        // 使用线程安全的连接管理来创建HttpClient
        HttpClientConnectionManager connectionManager =new PoolingHttpClientConnectionManager();
        client = HttpClients.createMinimal(connectionManager);
        CloseableHttpClient aDefault = HttpClientBuilder
                .create()
                .setConnectionManager(connectionManager)
                .build();
    }
    return client;
}


//    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    //SOCKET连接获取时间（毫秒）
    private static final int CONNECTION_REQUEST_TIMEOUT = 30 * 1000;
    //SOCKET连接超时时间（毫秒）
    private static final int CONNECT_TIMEOUT = 30 * 1000;
    //数据请求超时时间（毫秒）
    private static final int SOCKET_TIMEOUT = 300 * 1000;
    private static final String CHARSET = "UTF-8";
    private static RequestConfig requestConfig;

    static {
        requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();
    }

    */
/**
     * 通过url请求数据，返回HttpResponseInfo
     *
     * @param id      默认传null
     * @param urlPath 请求url
     * @param method  Method枚举值，get或post
     * @param params  post请求参数,get方式传null
     * @return HttpResponseInfo
     *//*

    public static HttpResponseInfo send(String id, String urlPath, MethodType method, Map<String, Object> params) throws Exception {
        HttpResponseInfo info = new HttpResponseInfo();
        info.setId(id);
        if (method.equals(MethodType.POST)) {
            info.setData(sendPostRequest(info, urlPath, params == null ? new HashMap<String, Object>() : params));
        } else {
            info.setData(sendGetRequest(info, urlPath));
        }
        return info;
    }

    */
/**
     * 处理get请求
     *
     * @param info    http请求返回对象
     * @param urlPath 请求地址
     * @return
     *//*

    private static String sendGetRequest(HttpResponseInfo info, String urlPath) throws Exception {
        String strResult;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            // 发送get请求
            HttpGet request = new HttpGet(urlPath);
            request.setConfig(requestConfig);
            CloseableHttpResponse response = httpClient.execute(request);
            try {
                //请求发送成功，并得到响应
                int status = response.getStatusLine().getStatusCode();
                info.setStatus(status);

                if (status == HttpStatus.SC_OK) {
                    //读取服务器返回过来的json字符串数据
                    HttpEntity entity = response.getEntity();
                    strResult = EntityUtils.toString(entity, CHARSET);
                } else {
//                    LOGGER.error("Get请求提交失败:" + urlPath);
                    throw new Exception("Get请求提交失败，Status code:" + status);
                }
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }

        return strResult;
    }

    */
/**
     * 处理post请求
     *
     * @param info    http请求返回对象
     * @param urlPath 请求地址
     * @param params  post请求参数
     * @return
     *//*

    private static String sendPostRequest(HttpResponseInfo info, String urlPath, Map<String, Object> params) throws Exception {
        String strResult;
        // post请求返回结果
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(urlPath);
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setConfig(requestConfig);
            StringEntity entity = new StringEntity(JSON.toJSONString(params), CHARSET);
            entity.setContentEncoding(CHARSET);
            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                //请求发送成功，并得到响应
                int status = response.getStatusLine().getStatusCode();
                info.setStatus(status);
                if (status == HttpStatus.SC_OK) {
                    //读取服务器返回过来的json字符串数据
                    strResult = EntityUtils.toString(response.getEntity(), CHARSET);
                } else {
//                    LOGGER.error("Post请求提交失败:" + urlPath);
                    throw new Exception("Post请求提交失败，Status code:" + status);
                }
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }

        return strResult;
    }

    */
/**
     * 短信发送
     *
     * @param jobName
     *//*

    */
/*public static void sendMsg(*//*
*/
/*Logger LOG,*//*
*/
/* String jobName, Date errorDate) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(SyncConfig.COMMON_URL_MSG_SEND);
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setConfig(requestConfig);
            StringBuilder content = new StringBuilder("同步任务").append(jobName).append("出错！出错时间：")
                    .append(DateFormatUtils.format(errorDate, DATE_PATTERN)).append("！");

            JSONObject jsonParam = new JSONObject();
            jsonParam.put("Token", SyncConfig.COMMON_MSG_TOKEN);
            jsonParam.put("PhoneNumbers", SyncConfig.COMMON_MSG_PHONENUMBERS);
            jsonParam.put("Content", content.toString());
            jsonParam.put("BfId", String.valueOf(System.currentTimeMillis()));
            StringEntity entity = new StringEntity(jsonParam.toString(), CHARSET);
            entity.setContentEncoding(CHARSET);
            httpPost.setEntity(entity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            try {
                //请求发送成功，并得到响应
                int status = response.getStatusLine().getStatusCode();
                if (status == HttpStatus.SC_OK) {
//                    LOG.info("结果：{}！", EntityUtils.toString(response.getEntity(), "UTF-8"));
//                    LOG.info("短信发送成功!发送内容：{}", content.toString());
                } else {
//                    LOG.error("短信发送失败!");
                    throw new Exception("Post请求提交失败，Status code:" + status);
                }
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }

    }*//*


}*/

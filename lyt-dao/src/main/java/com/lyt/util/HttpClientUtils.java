package com.lyt.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.util.Args;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpClientUtils工具
 */
public class HttpClientUtils {
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final int DEFAULT_MAX_TOTLE = 400;
    /**
     * 使用连接池管理器
     */
    private static PoolingHttpClientConnectionManager connectionManager;

    static {
        init();
    }

    /**
     * 初始化
     */
    public static void init() {
        // 初始化SSL上下文信息
        SSLContext sslContext = null;
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 关掉了主机名验证
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

        // 构建注册中心
        Registry<ConnectionSocketFactory> r = RegistryBuilder
                .<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslsf).build();
        connectionManager = new PoolingHttpClientConnectionManager(r);
        connectionManager.setMaxTotal(DEFAULT_MAX_TOTLE);
        connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_TOTLE - 100);//为其他路由预留100个连接
    }

    /**
     * 以POST方式发送请求
     *
     * @param url
     * @param param
     * @param timeout (精确到毫秒ms)
     * @throws Exception
     */
    public static String post(String url, Map<String, Object> param,
                              Integer timeout) throws Exception {
        Args.notBlank(url, "url产生不能为空: " + url);
        String result = "";
        //关闭失效连接
        connectionManager.closeExpiredConnections();
        // 创建httpClient实例
        HttpClient client = HttpClients.custom()
                .setConnectionManager(connectionManager).build();
        HttpPost post = new HttpPost(url);
        // 填充参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        if (null != param) {
            param.forEach((k, v) -> {
                String value = param.get(k) + "";
                if (StringUtils.isBlank(value) || value.equals("null")) {
                    value = "";
                }
                NameValuePair nvPair = new BasicNameValuePair(k.trim(), value);
                params.add(nvPair);
            });
            post.setEntity(new UrlEncodedFormEntity(params, DEFAULT_CHARSET));
        }
        HttpResponse response = client.execute(post, getConfig(timeout));
        if (null != response.getEntity()) {
            result = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        }
        post.releaseConnection();
        return result;
    }

    /**
     * 以POST方式发送请求
     *
     * @param url
     * @param param
     * @param timeout (精确到毫秒ms)
     * @throws Exception
     */
    public static String post(String url, Map<String, Object> param,
                              Integer timeout, Integer resTimeout) throws Exception {
        Args.notBlank(url, "url产生不能为空: " + url);
        String result = "";
        //关闭失效连接
        connectionManager.closeExpiredConnections();
        // 创建httpClient实例
        HttpClient client = HttpClients.custom().setConnectionManager(connectionManager).build();
        HttpPost post = new HttpPost(url);
        // 填充参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        if (null != param) {
            param.forEach((k, v) -> {
                String value = param.get(k) + "";
                if (StringUtils.isBlank(value) || value.equals("null")) {
                    value = "";
                }
                NameValuePair nvPair = new BasicNameValuePair(k.trim(), value);
                params.add(nvPair);
            });
            post.setEntity(new UrlEncodedFormEntity(params, DEFAULT_CHARSET));
        }
        HttpResponse response = null;
        if (resTimeout == null) {
            response = client.execute(post, getConfig(timeout));
        } else {
            response = client.execute(post, getConfig(timeout, resTimeout));
        }
        if (null != response.getEntity()) {
            result = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        }
        post.releaseConnection();
        return result;
    }

    /**
     * 以GET方式发送请求
     *
     * @param url
     * @param timeout (精确到毫秒ms)
     * @return
     * @throws Exception
     */
    public static String get(String url, Integer timeout) throws Exception {
        Args.notBlank(url, "url产生不能为空: " + url);
        String result = "";
        // 创建httpClient实例
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get, getConfig(timeout));
        if (null != response.getEntity()) {
            result = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        }
        get.releaseConnection();
        return result;
    }

    /**
     * 获取url产生的输出流
     *
     * @param url
     * @param timeout
     * @return
     * @throws Exception
     */
    public static InputStream getInputStream(String url, Integer timeout) throws Exception {
        Args.notBlank(url, "url产生不能为空: " + url);
        InputStream result = null;
        //关闭失效连接
        connectionManager.closeExpiredConnections();
        // 创建httpClient实例
        HttpClient client = HttpClients.custom()
                .setConnectionManager(connectionManager).build();
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get, getConfig(timeout));
        if (null != response.getEntity()) {
            result = response.getEntity().getContent();
        }
        get.releaseConnection();
        return result;
    }

    /**
     * 获取带有超时设定的context
     *
     * @param timeout (精确到毫秒ms)
     * @return
     */
    private static HttpContext getConfig(Integer timeout) {
        // 设置定制上下文
        HttpContext context = new HttpCoreContext();
        if (null != timeout) {
            // 设置请求超时和连接超时
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(timeout)
                    .setConnectionRequestTimeout(timeout)
                    .build();
            context.setAttribute(HttpClientContext.REQUEST_CONFIG, config);
        }
        return context;
    }

    /**
     * 获取带有超时设定的context
     *
     * @param timeout    (精确到毫秒ms)
     * @param resTimeout (精确到毫秒ms)
     * @return
     */
    private static HttpContext getConfig(Integer timeout, Integer resTimeout) {
        // 设置定制上下文
        HttpContext context = new HttpCoreContext();
        if (null != timeout) {
            // 设置请求超时和连接超时
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(timeout)
                    .setSocketTimeout(resTimeout)
                    .build();
            context.setAttribute(HttpClientContext.REQUEST_CONFIG, config);
        }
        return context;
    }

    /**
     * 以POST方式发送请求, 并以接口回调方式获取对象返回参数
     *
     * @param <T>
     * @param url
     * @param timeout (精确到毫秒ms)
     * @param handler
     * @return T
     * @throws Exception
     */
    public static <T> T post(String url, Map<String, Object> param, Integer timeout, ResponseHandler<? extends T> handler) throws Exception {
        Args.notBlank(url, "url产生不能为空: " + url);
        //关闭失效连接
        connectionManager.closeExpiredConnections();
        // 创建httpClient实例
        HttpClient client = HttpClients.custom()
                .setConnectionManager(connectionManager).build();
        HttpPost post = new HttpPost(url);
        // 填充参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        if (null != param) {
            param.forEach((k, v) -> {
                NameValuePair nvPair = new BasicNameValuePair(k.trim(), param.get(k).toString());
                params.add(nvPair);
            });
            post.setEntity(new UrlEncodedFormEntity(params, DEFAULT_CHARSET));
        }
        T t = client.execute(post, handler, getConfig(timeout));
        post.releaseConnection();
        return t;
    }

    /**
     * 以GET方式发送请求, 并以接口回调方式获取对象返回参数
     *
     * @param <T>
     * @param url
     * @param timeout (精确到毫秒ms)
     * @param handler
     * @return T
     * @throws Exception
     */
    public static <T> T get(String url, Integer timeout, ResponseHandler<? extends T> handler) throws Exception {
        Args.notBlank(url, "url产生不能为空: " + url);
        // 创建httpClient实例
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        T t = client.execute(get, handler, getConfig(timeout));
        get.releaseConnection();
        return t;
    }

}
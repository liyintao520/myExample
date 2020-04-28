package com.lyt.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.Map.Entry;

@Slf4j
public class HttpUtil {

    private static final String tongji = "";
    private static final String METHOD_POST = "POST";
    private static final String DEFAULT_CHARSET = "utf-8";

    /**
     * 发送xml 格式数据用http post请求
     *
     * @param url
     * @param params
     * @param encoding
     * @return
     * @throws IOException
     */

    public static String sendXMLData(String url, String params, String encoding)
            throws IOException {
        Map<String, String> mapHeader = new LinkedHashMap<String, String>();

        mapHeader.put("Content-Type", "text/xml;charset=" + encoding);

        return sendRequest(url, params, "post", mapHeader, encoding);
    }

    /**
     * 发送http请求
     *
     * @param url      请求地址
     * @param params   请求参数，get可以直接跟在url后面
     * @param method   post get
     * @param encoding 编码
     * @return
     * @throws IOException
     */

    public static String sendRequest(String url, String params, String method,
                                     Map<String, String> mapHeader, String encoding) throws IOException {

        if (url == null || "".equals(url)) {
            log.error("request url is null,please check it !");
            return "";
        }

        if (params == null || "".equals(params)) {
            params = "";
        }

        if (method == null || "".equals(method)) {
            method = "GET";
        }
        method = method.toUpperCase();

        if (encoding == null || "".equals(encoding)) {
            encoding = "utf-8";
        }

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }

        // 判断url后面有没有接参数，且是get请求就自动加上
        if (url.indexOf("?") == -1 && "get".equalsIgnoreCase(method)) {
            url += "?" + params;
        }

        URL objUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) objUrl.openConnection();

        conn.setDoInput(true);
        conn.setConnectTimeout(15 * 1000);
        conn.setReadTimeout(45 * 1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("Connection", "Keep-Alive");

        if (method.equalsIgnoreCase("POST")) {
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            for (Iterator<Entry<String, String>> it = mapHeader.entrySet()
                    .iterator(); it.hasNext(); ) {
                Entry<String, String> header = it.next();
                conn.setRequestProperty(header.getKey(), header.getValue());
            }

            conn.setRequestProperty("Content-Length", String.valueOf(params
                    .getBytes(encoding).length));

            OutputStream os = conn.getOutputStream();
            os.write(params.getBytes(encoding));
            os.flush();
            os.close();
        }
        conn.connect();

        if (conn.getResponseCode() == 200 || conn.getResponseCode() == 304) {
            InputStream is = conn.getInputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();

            while ((len = is.read(buffer)) > -1) {
                bout.write(buffer, 0, len);
            }
            return bout.toString(encoding);
        } else {
            log.info("connect url " + url + " response code is "
                    + conn.getResponseCode() + " messag is "
                    + conn.getResponseMessage());
            return "connect url " + url + " response code is "
                    + conn.getResponseCode() + " messag is "
                    + conn.getResponseMessage();
        }

        // return "";
    }

    /**
     * 发送http请求
     *
     * @param url       请求地址
     * @param mapParams 请求参数，get可以直接跟在url后面
     * @param method    post get
     * @param encoding  编码
     * @return
     * @throws IOException
     */

    public static String sendRequest(String url, Map mapParams, String method,
                                     Map<String, String> mapHeader, String encoding) throws IOException {

        if (url == null || "".equals(url)) {
            log.error("request url is null,please check it !");
            return "";
        }

        if (mapParams == null) {
            mapParams = Collections.EMPTY_MAP;
        }

        if (method == null || "".equals(method)) {
            method = "GET";
        }
        method = method.toUpperCase();

        if (encoding == null || "".equals(encoding)) {
            encoding = "utf-8";
        }

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }

        String params = "";
        for (Iterator<Entry<String, String>> it = mapParams.entrySet()
                .iterator(); it.hasNext(); ) {
            Entry<String, String> me = it.next();
            params += me.getKey() + "=" + me.getValue() + "&";
        }

        //如果为空的情况下，会出现问题，故修改
        if (StringUtils.isNotBlank(params)) {
            params = params.substring(0, params.lastIndexOf("&"));
        }

        // 判断url后面有没有接参数，且是get请求就自动加上

        if (url.indexOf("?") == -1 && "get".equalsIgnoreCase(method)) {
            url += "?" + params;
        }

        URL objUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) objUrl.openConnection();

        conn.setDoInput(true);
        conn.setConnectTimeout(15 * 1000);
        conn.setReadTimeout(45 * 1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("Connection", "Keep-Alive");

        if (method.equalsIgnoreCase("POST")) {
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            for (Iterator<Entry<String, String>> it = mapHeader.entrySet()
                    .iterator(); it.hasNext(); ) {
                Entry<String, String> header = it.next();
                conn.setRequestProperty(header.getKey(), header.getValue());
            }

            conn.setRequestProperty("Content-Length", String.valueOf(params
                    .getBytes(encoding).length));

            OutputStream os = conn.getOutputStream();
            os.write(params.getBytes(encoding));
            os.flush();
            os.close();
        }
        conn.connect();

        if (conn.getResponseCode() == 200 || conn.getResponseCode() == 304) {
            InputStream is = conn.getInputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            ByteArrayOutputStream bout = new ByteArrayOutputStream();

            while ((len = is.read(buffer)) > -1) {
                bout.write(buffer, 0, len);
            }
            return bout.toString(encoding);
        } else {
            log.info("connect url " + url + " response code is "
                    + conn.getResponseCode() + " messag is "
                    + conn.getResponseMessage());
            return "connect url " + url + " response code is "
                    + conn.getResponseCode() + " messag is "
                    + conn.getResponseMessage();
        }

        // return "";
    }

    public static String getRequestsPost(String surl, String encoding) {
        String sresult = "";
        HttpURLConnection conn = null;
        surl = surl.replace("?&", "?");
        try {
            if (!surl.contains("?")) {
                surl = surl + "?";
            }
            String urlAddr = surl.substring(0, surl.indexOf("?"));
            String params = surl.substring(surl.indexOf("?") + 1);
            URL url = new URL(urlAddr + tongji);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(params
                    .length()));

            conn.setDoInput(true);
            conn.connect();

            OutputStreamWriter out = new OutputStreamWriter(conn
                    .getOutputStream(), encoding);
            out.write(params);
            out.flush();
            out.close();
            InputStream in = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(in, encoding);
            char[] b = new char[255];
            StringBuffer sb = new StringBuffer();
            int len = 0;
            while ((len = isr.read(b)) > 0) {
                sb.append(b, 0, len);
            }
            sresult = sb.toString();
            isr.close();
            in.close();
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return sresult;
    }

    public static String sendRequest(String url, String method, String encoding)
            throws IOException {

        String params = "";
        Map<String, String> mapHeader = new HashMap<String, String>();

        if (url == null || "".equals(url)) {
            return "";
        }

        if (url.indexOf("?") != -1) {
            params = url.substring(url.indexOf("?") + 1);
            url = url.substring(0, url.indexOf("?"));
        }

        if (method == null || "".equals(method)) {
            method = "POST";
        }
        method = method.toUpperCase();

        if (encoding == null || "".equals(encoding)) {
            encoding = "UTF-8";
        }

        mapHeader.put("Content-Type",
                "application/x-www-form-urlencoded;charset=" + encoding);

        return sendRequest(url, params, method, mapHeader, encoding);
    }

    public static String doPost(String url, String params, String charset,
                                int connectTimeout, int readTimeout) throws Exception {
        log.info("come in doPost");
        String ctype = "application/json;charset=" + charset;
        byte[] content = {};
        if (params != null) {
            content = params.getBytes(charset);
        }

        return doPost(url, ctype, content, connectTimeout, readTimeout);
    }

    public static String doPost(String url, String ctype, byte[] content,
                                int connectTimeout, int readTimeout) throws Exception {
        HttpsURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;
        try {
            try {
                log.info("Come in doPost url=" + url + "ctype=" + ctype);
                SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
                        .getDefault();
                log.info("11111111111111111111111111111111111111");
                SSLContext ctx = SSLContext.getInstance("TLS");
                log.info("22222222222222222222222222222222222222");
                ctx
                        .init(
                                new KeyManager[0],
                                new TrustManager[]{(TrustManager) new DefaultTrustManager()},
                                new SecureRandom());
                log.info("333333333333333333333333333333333");
                SSLContext.setDefault(ctx);
                conn = getConnection(new URL(url), "POST", ctype);
                // conn.setSSLSocketFactory(sslsocketfactory);
                // InputStream inputstream = conn.getInputStream();
                conn.setHostnameVerifier(new HostnameVerifier() {

                    @Override
                    public boolean verify(String arg0, SSLSession arg1) {
                        // TODO Auto-generated method stub
                        return true;
                    }
                });
                conn.setConnectTimeout(connectTimeout);
                conn.setReadTimeout(readTimeout);
            } catch (Exception e) {
                log.error("GET_CONNECTOIN_ERROR, URL = " + url, e);

                throw e;
            }
            try {
                out = conn.getOutputStream();
                out.write(content);
                rsp = getResponseAsString(conn);
            } catch (IOException e) {
                log.error("REQUEST_RESPONSE_ERROR, URL = " + url, e);
                throw e;
            }

        } finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }

    private static HttpsURLConnection getConnection(URL url, String method,
                                                    String ctype) throws IOException {
        log.info("com in getConnection");
        SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory
                .getDefault();

        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        // InputStream inputstream = conn.getInputStream();
        // conn.setSSLSocketFactory(sslsocketfactory);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html");
        conn.setRequestProperty("User-Agent", "stargate");
        conn.setRequestProperty("Content-Type", ctype);
        return conn;
    }

    protected static String getResponseAsString(HttpURLConnection conn)
            throws IOException {
        String charset = getResponseCharset(conn.getContentType());
        InputStream es = conn.getErrorStream();
        if (es == null) {
            return getStreamAsString(conn.getInputStream(), charset);
        } else {
            String msg = getStreamAsString(es, charset);
            if (null == msg) {
                throw new IOException(conn.getResponseCode() + ":"
                        + conn.getResponseMessage());
            } else {
                throw new IOException(msg);
            }
        }
    }

    private static String getResponseCharset(String ctype) {
        String charset = DEFAULT_CHARSET;

        if (null != (ctype)) {
            String[] params = ctype.split(";");
            for (String param : params) {
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2) {
                        if (null != (pair[1])) {
                            charset = pair[1].trim();
                        }
                    }
                    break;
                }
            }
        }

        return charset;
    }

    private static String getStreamAsString(InputStream stream, String charset)
            throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    stream, charset));
            StringWriter writer = new StringWriter();

            char[] chars = new char[256];
            int count = 0;
            while ((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }

            return writer.toString();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    private static class DefaultTrustManager implements X509TrustManager {


        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            // TODO Auto-generated method stub

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
            // TODO Auto-generated method stub

        }

    }

    public static String sendUrl(String url, String params) throws IOException {
        String uri = url + "?showapi_appid=5658&showapi_timestamp=" + System.currentTimeMillis() + "&showapi_sign=9786d1080358472b878b8f3eef6d15ab" + params;
        URL u = new URL(uri);
//		URL u=new URL("http://route.showapi.com/25-3?showapi_appid=5658&showapi_timestamp=20150810103154&id=130283198910171915&showapi_sign=9786d1080358472b878b8f3eef6d15ab");
        InputStream in = u.openStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            byte buf[] = new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        byte b[] = out.toByteArray();
        return new String(b, "utf-8");
    }

    // get请求
    public static JSONObject doGet(String requestUrl) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String responseContent = null;
        JSONObject result = null;
        try {
            // 创建Get请求，
            HttpGet httpGet = new HttpGet(requestUrl);
            // 执行Get请求，
            response = httpClient.execute(httpGet);
            // 得到响应体
            HttpEntity entity = response.getEntity();
            // 获取响应内容
            responseContent = EntityUtils.toString(entity, "UTF-8");
            // 转换为map
            result = JSON.parseObject(responseContent);
        } catch (IOException e) {
            log.error("HttpUtil=====Start");
            log.error(e.getMessage(), e);
            log.error("HttpUtil=====End");
        }
        return result;
    }

    /**
     * dohttpPost方法
     *
     * @param url
     * @param vp
     * @return
     */
    public static JSONObject dohttpPost(String url, Map<String, String> vp) {
        JSONObject res = null;
        try {
            HttpPost request = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(5000).setConnectTimeout(5000).build();
            request.setConfig(requestConfig);

            List<NameValuePair> nvps = new ArrayList<>();
            for (Entry<String, String> entry : vp.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry
                        .getValue()));
            }
            request.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            CloseableHttpClient httpclient = HttpClients.createDefault();
            try (CloseableHttpResponse response = httpclient.execute(request)) {
                String line = null;
                StringBuilder sb = new StringBuilder();
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(content, "utf-8"));
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                String result = sb.toString();
                if (response.getStatusLine().getStatusCode() != 200) {
                    log.error("请求失败" + result);
                    return null;
                }
                res = JSONObject.parseObject(result, JSONObject.class);

            }

        } catch (Exception e) {
            log.error("请求异常：", e);
            e.printStackTrace();
        }

        return res;
    }

    /**
     * doHttpsPost方法
     *
     * @param url
     * @param map
     * @param charset
     * @return
     */
    public static JSONObject doHttpsPost(String url, Map<String, String> map, String charset) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = createSSLClientDefault();
            httpPost = new HttpPost(url);
            // 设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            if (map != null) {
                Iterator iterator = map.entrySet().iterator();
                while (iterator.hasNext()) {
                    Entry<String, String> elem = (Entry<String, String>) iterator.next();
                    list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
                }
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JSONObject res = JSONObject.parseObject(result, JSONObject.class);
        return res;
    }

    public static CloseableHttpClient createSSLClientDefault() {
        try {
            // 使用 loadTrustMaterial() 方法实现一个信任策略，信任所有证书
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            // NoopHostnameVerifier类: 作为主机名验证工具，实质上关闭了主机名验证，它接受任何
            // 有效的SSL会话并匹配到目标主机。
            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    public static void main(String[] args) {
        JSONObject result = doHttpsPost("https://api.douban.com/v2/book/1220562", null, null);
        System.out.println(JsonUtil.toJson(result));
    }

}

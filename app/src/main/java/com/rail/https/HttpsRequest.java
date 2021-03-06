package com.rail.https;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 网络层请求访问类
 */
public  class HttpsRequest {
    private String cookie;
    private String resultString;
    private List<String> cookieList;

    public HttpsRequest() {

    }

    /**
     * 对外提供设置cookie的方法
     *
     * @param cookie 传入要设置的cookie字符串
     */
    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    /**
     * 对外提供请求结果字符串
     *
     * @return 请求结果
     */
    public String getResultString() {
        return this.resultString;
    }

    /**
     * 对外提供cookie列表
     * @return cookie列表
     */
    public List<String> getCookieList() {
        return cookieList;

    }


    /**
     * 对外提供网络层的访问方法
     * @param urlString url的请求链接
     * @param params  传递的参数 格式如下：aa=bb&cc=dd&ee=ff
     * @param method  传递请求的方式，只能取值为“POST”或者“GET”
     * @return 结果字符串
     * @throws Exception no such method to request
     */
    public String request(String urlString,String params,String method)throws Exception{
        clearAll();
        if("POST".equals(method)){
            return doPost(urlString,params);
        }else if("GET".equals(method)){
            return doGet(urlString+"?"+params);
        }else{
            throw new NoSuchFieldException("请求方式必须为POST或者为GET");

        }

    }
    /**
     * 提供GET请求服务
     *
     * @param urlString url字符串链接
     * @return result 请求结果
     * @throws Exception
     */
    private String doGet(String urlString) throws Exception {
    System.out.println(urlString);
        InputStream in = null;
        this.resultString =null;
        try {
            URL url = new URL(urlString);
            HttpsURLConnection conn = (HttpsURLConnection) url
                    .openConnection();
            setSSL(conn);//对请求设置证书
            setCookie(conn);//设置cookie
            conn.setDoInput(true);
            conn.connect();
            getCookie(conn);//获取服务端cookie
            in  = conn.getInputStream();
            this.resultString = InputStreamToString(in);//从输入流得到字符串
            in.close();
            conn.disconnect();
        } catch (ConnectException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                in.close();
            } catch (Exception e) {
            }
        }
        return this.resultString;
    }

    /**
     * 提供POST请求服务
     * @param urlString url字符串链接
     * @param params 请求结果
     * @return
     * @throws Exception
     */
    private String doPost(String urlString,String params) throws Exception{
        this.resultString =null;
        InputStream in = null;
        PrintWriter writer =null;
        try {
            URL url = new URL(urlString);
            HttpsURLConnection conn = (HttpsURLConnection) url
                    .openConnection();
            setSSL(conn);//对请求设置证书
            setCookie(conn);//设置cookie
            conn.setDoInput(true);
            conn.setDoOutput(true);
            getCookie(conn);//获取服务端cookie
//            conn.connect();
             writer = new PrintWriter(conn.getOutputStream());
            writer.print(params);
            writer.flush();
            in  = conn.getInputStream();
            this.resultString = InputStreamToString(in);//从输入流得到字符串
            in.close();
            conn.disconnect();
        } catch (ConnectException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                in.close();
            } catch (Exception e) {
            }
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
        return this.resultString;
    }
    /**
     * 清除所有记录的方法
     */
    private void clearAll() {
        this.cookie = null;
        this.resultString = null;
        this.cookieList = null;
    }
    /**
     * 把输入流转换为编码为UTF-8的字符串
     *
     * @param is 输入流
     * @return 字符串
     * @throws IOException
     */
    private String InputStreamToString(InputStream is) throws IOException {
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "UTF-8"));
            String ret = "";
            StringBuffer sb = new StringBuffer();
            while (ret != null) {
                ret = reader.readLine();
                if (ret != null && !ret.trim().equals("")) {
                    sb.append(ret + "\n");
                }
            }
            return sb.toString();
        }
        return null;
    }

    /**
     * HTTPS对SSL证书的设置
     *
     * @param conn url的连接
     * @throws Exception
     */
    private void setSSL(HttpsURLConnection conn) throws Exception {
        if (conn != null) {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()},
                    new java.security.SecureRandom());
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
        }
    }


    /**
     * 若有对cookie的设置则在请求的时候带上cookie
     *
     * @param conn url的连接
     */
    private void setCookie(HttpsURLConnection conn) {
        if (conn != null && cookie != null && cookie.length() > 0) {
            conn.setRequestProperty("Cookie", cookie);
        }
    }

    /**
     * 保存获取的cookie列表
     *
     * @return 保存cookie
     */
    private void getCookie(HttpsURLConnection conn) {
        if (conn != null) {
            cookieList = conn.getHeaderFields().get("Set-Cookie");
        }

    }


    /********************************
     * 以下是证书的相关设置
     ******************************/
    private static class TrustAnyTrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

}

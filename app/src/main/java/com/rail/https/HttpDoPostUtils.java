package com.rail.https;

import android.os.AsyncTask;

import com.rail.interfaces.InterfaceToUseData;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpDoPostUtils extends AsyncTask<Object,Integer,Object>
{
    static String JSESSIONID = null;
    static String BIGipServerotn = null;
    private InterfaceToUseData interfaceToUseData=null;
    private HttpDoPostUtils(){

    }
    public static HttpDoPostUtils getHttpPost(){
        return new HttpDoPostUtils();
    }
    private static class TrustAnyTrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    @Override
    protected void onPostExecute(Object s) {
        if(interfaceToUseData!=null)
             interfaceToUseData.useData(s);
        super.onPostExecute(s);
    }


    @Override
    protected Object doInBackground(Object... params) {
        StringBuffer str_return = new StringBuffer();
        interfaceToUseData = (InterfaceToUseData) params[1];
        String urlString = (String) params[0];
        System.out.println(urlString);
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustAnyTrustManager()},
                    new java.security.SecureRandom());
            URL console = new URL(urlString);
            HttpsURLConnection conn = (HttpsURLConnection) console
                    .openConnection();
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
            if(urlString!=Login.url1){
                conn.setRequestProperty("Cookie", "BIGipServerotn="+BIGipServerotn+";JSESSIONID="+JSESSIONID);
            }
            conn.setDoInput(true);
            // conn.connect();
            // 获取URLConnection对象对应的输出流
            if(params.length>=3) {
                conn.setDoOutput(true);
                PrintWriter writer = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                writer.print(params[2]);
                // flush输出流的缓冲
                writer.flush();
            }

            if(urlString==Login.url1){
                String reg = "=(.+?);";
                Pattern p = Pattern.compile(reg);
                List<String> cookieList = conn.getHeaderFields().get("Set-Cookie");
                for (int i = 0; i < cookieList.size(); i++) {
                    Matcher m = p.matcher(cookieList.get(i));
                    while(m.find()) {
                        if(m.group(1).length()>1&&m.group(1).contains(".")){
                            BIGipServerotn = m.group(1);
                        }
                        else{
                            JSESSIONID = m.group(1);
                        }
                    }
                }
            }
            InputStream is = conn.getInputStream();
            if(urlString==Loginthree.url3||urlString==BookTicketFive.burl5){
                System.out.println("return is");
                ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
                byte[]buff=new byte[1024];
                int rc = 0;
                while ((rc = is.read(buff, 0, 1024)) > 0) {
                    swapStream.write(buff, 0, rc);
                }
                byte[] in_b = swapStream.toByteArray();
                return in_b;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "UTF-8"));
            String ret = "";
                while (ret != null) {
                    ret = reader.readLine();
                    if (ret != null && !ret.trim().equals("")) {
                        str_return.append(ret + "\n");
                    }
                }
            reader.close();
            conn.disconnect();
            if(str_return!=null){
                return str_return;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return "请求数据出错";
    }

}
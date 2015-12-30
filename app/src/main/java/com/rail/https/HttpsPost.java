package com.rail.https;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
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

public class HttpsPost {
	static String dynamicJs = null;
	static String dynamicJs2 = null;
	static String JSESSIONID = null;
	static String BIGipServerotn = null;
	static String globalRepeatSubmitToken = null;
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
	public static String url(String urlString) throws Exception {
		InputStream in = null;
		OutputStream out = null;
		StringBuffer str_return = new StringBuffer();
		try {
		//	Proxy proxy = new Proxy(java.net.Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1", 8888));  
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(urlString);
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection();
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setDoInput(true);
			 conn.connect();
			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));
			// DataInputStream indata = new DataInputStream(is);
			String ret = "";
			while (ret != null) {
				ret = reader.readLine();
				if (ret != null && !ret.trim().equals("")) {
					str_return.append(ret + "\n");
				}
			}
			reader.close();
			conn.disconnect();
		} catch (ConnectException e) {
			System.out.println("ConnectException");
			System.out.println(e);
			throw e;
		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e);
			throw e;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
			try {
				out.close();
			} catch (Exception e) {
			}
		}
		System.out.println(str_return.toString());
		return str_return.toString();
	}
	//init
	public static void url1() throws Exception {
		InputStream in = null;
		OutputStream out = null;
		byte[] buffer = new byte[4096];
		StringBuffer str_return = new StringBuffer();
//		String urlString = "https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn";
		String urlString = "https://kyfw.12306.cn/otn/login/init";
		
		// String urlString =
		// "https://kyfw.12306.cn/otn/leftTicket/queryT?leftTicketDTO.train_date=2015-12-24&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=SHH&purpose_codes=ADULT";
		try {
			Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1", 8888));
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(urlString);
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection(proxy);
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// conn.connect();
			// 鑾峰彇URLConnection瀵硅薄瀵瑰簲鐨勮緭鍑烘祦
			PrintWriter writer = new PrintWriter(conn.getOutputStream());
			// 鍙戦�佽姹傚弬鏁�
			writer.print("");
			// flush杈撳嚭娴佺殑缂撳啿
			writer.flush();
			String reg = "=(.+?);";
			 Pattern p = Pattern.compile(reg);
			
			 List<String> cookieList = conn.getHeaderFields().get("Set-Cookie");
	            for (int i = 0; i < cookieList.size(); i++) {
	            	Matcher m = p.matcher(cookieList.get(i));
	    	 	    while(m.find()) {
	    	 	    	if(m.group(1).length()>1&&m.group(1).contains("."))BIGipServerotn = m.group(1);
	    	 	    	else{
	    	 	    		JSESSIONID = m.group(1);
	    	 	    	}
	    	 	    	//System.out.println(m.group(1));
	    	 	    }
	    			//System.out.println(cookieList.get(i));
	    			
	    		}
			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));
			// DataInputStream indata = new DataInputStream(is);
			String ret = "";
			while (ret != null) {
				ret = reader.readLine();
				if (ret != null && !ret.trim().equals("")) {
					str_return.append(ret + "\n");
//					str_return = str_return
//					// + new String(ret.getBytes("ISO-8859-1"), "gbk");
//							+ (ret + "\n");

				}
			}
			reader.close();
			conn.disconnect();
		} catch (ConnectException e) {
			System.out.println("ConnectException");
			System.out.println(e);
			throw e;
		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e);
			throw e;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
			try {
				out.close();
			} catch (Exception e) {
			}
		}
		String reg = "src=\"(/otn/dynamicJs/.+?)\"";
		 Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str_return);
 	    while(m.find()) {
 	    	if(m.group(1).length()>1)dynamicJs = m.group(1);
 	    //	System.out.println(m.group(1));
 	    }
	//	System.out.println(str_return);
	}
	public static void url2() throws Exception {
		InputStream in = null;
		OutputStream out = null;
		StringBuffer str_return = new StringBuffer();
//		String urlString = "https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn";
		String urlString = "https://kyfw.12306.cn"+dynamicJs;
		try {
			Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1", 8888));
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(urlString);
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection(proxy);
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setRequestProperty("Cookie", "BIGipServerotn="+BIGipServerotn+";JSESSIONID="+JSESSIONID);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// conn.connect();
			// 鑾峰彇URLConnection瀵硅薄瀵瑰簲鐨勮緭鍑烘祦
			PrintWriter writer = new PrintWriter(conn.getOutputStream());
			// 鍙戦�佽姹傚弬鏁�
			writer.print("");
			// flush杈撳嚭娴佺殑缂撳啿
			writer.flush();
			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));
			// DataInputStream indata = new DataInputStream(is);
			String ret = "";
			while (ret != null) {
				ret = reader.readLine();
				if (ret != null && !ret.trim().equals("")) {
					str_return.append(ret + "\n");
				}
			}
			reader.close();
			conn.disconnect();
		} catch (ConnectException e) {
			System.out.println("ConnectException");
			System.out.println(e);
			throw e;
		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e);
			throw e;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
			try {
				out.close();
			} catch (Exception e) {
			}
		}
	}
	//鑾峰彇楠岃瘉鐮�
	public static void url3() throws Exception {
		InputStream in = null;
		OutputStream out = null;
		StringBuffer str_return = new StringBuffer();
//		String urlString = "https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn";
		String urlString = "https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=login&rand=sjrand";
		try {
			Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1", 8888));
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(urlString);
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection(proxy);
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setRequestProperty("Cookie", "BIGipServerotn="+BIGipServerotn+";JSESSIONID="+JSESSIONID);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// conn.connect();
			// 鑾峰彇URLConnection瀵硅薄瀵瑰簲鐨勮緭鍑烘祦
			PrintWriter writer = new PrintWriter(conn.getOutputStream());
			// 鍙戦�佽姹傚弬鏁�
			writer.print("");
			// flush杈撳嚭娴佺殑缂撳啿
			writer.flush();
			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));
			// DataInputStream indata = new DataInputStream(is);
			String ret = "";
			while (ret != null) {
				ret = reader.readLine();
				if (ret != null && !ret.trim().equals("")) {
					str_return.append(ret + "\n");
				}
			}
			reader.close();
			conn.disconnect();
		} catch (ConnectException e) {
			System.out.println("ConnectException");
			System.out.println(e);
			throw e;
		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e);
			throw e;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
			try {
				out.close();
			} catch (Exception e) {
			}
		}
	}
	public static void url4() throws Exception {
		InputStream in = null;
		OutputStream out = null;
		StringBuffer str_return = new StringBuffer();
		String urlString = "https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn";
		try {
			Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1", 8888));
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(urlString);
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection(proxy);
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setRequestProperty("Cookie", "BIGipServerotn="+BIGipServerotn+";JSESSIONID="+JSESSIONID);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// conn.connect();
			// 鑾峰彇URLConnection瀵硅薄瀵瑰簲鐨勮緭鍑烘祦
			PrintWriter writer = new PrintWriter(conn.getOutputStream());
			// 鍙戦�佽姹傚弬鏁�
			writer.print("rand=sjrand&randCode=87,50,171,56");
			// flush杈撳嚭娴佺殑缂撳啿
			writer.flush();
			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));
			// DataInputStream indata = new DataInputStream(is);
			String ret = "";
			while (ret != null) {
				ret = reader.readLine();
				if (ret != null && !ret.trim().equals("")) {
					str_return.append(ret + "\n");
				}
			}
			reader.close();
			conn.disconnect();
		} catch (ConnectException e) {
			System.out.println("ConnectException");
			System.out.println(e);
			throw e;
		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e);
			throw e;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
			try {
				out.close();
			} catch (Exception e) {
			}
		}
//		System.out.println(str_return.toString());
	}
	public static void url5() throws Exception {
		InputStream in = null;
		OutputStream out = null;
		StringBuffer str_return = new StringBuffer();
		String urlString = "https://kyfw.12306.cn/otn/login/loginAysnSuggest";
		try {
			Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1", 8888));
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(urlString);
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection(proxy);
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setRequestProperty("Cookie", "BIGipServerotn="+BIGipServerotn+";JSESSIONID="+JSESSIONID);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// conn.connect();
			// 鑾峰彇URLConnection瀵硅薄瀵瑰簲鐨勮緭鍑烘祦
			PrintWriter writer = new PrintWriter(conn.getOutputStream());
			// 鍙戦�佽姹傚弬鏁�
			writer.print("loginUserDTO.user_name=onesound&userDTO.password=512512&randCode=87,50,171,56");
			// flush杈撳嚭娴佺殑缂撳啿
			writer.flush();
			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));
			// DataInputStream indata = new DataInputStream(is);
			String ret = "";
			while (ret != null) {
				ret = reader.readLine();
				if (ret != null && !ret.trim().equals("")) {
					str_return.append(ret + "\n");
				}
			}
			reader.close();
			conn.disconnect();
		} catch (ConnectException e) {
			System.out.println("ConnectException");
			System.out.println(e);
			throw e;
		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e);
			throw e;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
			try {
				out.close();
			} catch (Exception e) {
			}
		}
		System.out.println(str_return.toString());
	}
	public static void url6() throws Exception {
		InputStream in = null;
		OutputStream out = null;
		StringBuffer str_return = new StringBuffer();
		String urlString = "https://kyfw.12306.cn/otn/login/userLogin";
		try {
			Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1", 8888));
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(urlString);
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection(proxy);
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setRequestProperty("Cookie", "BIGipServerotn="+BIGipServerotn+";JSESSIONID="+JSESSIONID);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// conn.connect();
			// 鑾峰彇URLConnection瀵硅薄瀵瑰簲鐨勮緭鍑烘祦
			PrintWriter writer = new PrintWriter(conn.getOutputStream());
			// 鍙戦�佽姹傚弬鏁�
			writer.print("_json_att=");
			// flush杈撳嚭娴佺殑缂撳啿
			writer.flush();
			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));
			// DataInputStream indata = new DataInputStream(is);
			String ret = "";
			while (ret != null) {
				ret = reader.readLine();
				if (ret != null && !ret.trim().equals("")) {
					str_return.append(ret + "\n");
				}
			}
			reader.close();
			conn.disconnect();
		} catch (ConnectException e) {
			System.out.println("ConnectException");
			System.out.println(e);
			throw e;
		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e);
			throw e;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
			try {
				out.close();
			} catch (Exception e) {
			}
		}
		System.out.println(str_return.toString());
	}
	public static void url7() throws Exception {
		InputStream in = null;
		OutputStream out = null;
		StringBuffer str_return = new StringBuffer();
		String urlString = "https://kyfw.12306.cn/otn/login/checkUser";
		try {
			Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1", 8888));
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(urlString);
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection(proxy);
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setRequestProperty("Cookie", "BIGipServerotn="+BIGipServerotn+";JSESSIONID="+JSESSIONID);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// conn.connect();
			PrintWriter writer = new PrintWriter(conn.getOutputStream());
			// 鍙戦�佽姹傚弬鏁�
			writer.print("_json_att=");
			// flush杈撳嚭娴佺殑缂撳啿
			writer.flush();
			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));
			// DataInputStream indata = new DataInputStream(is);
			String ret = "";
			while (ret != null) {
				ret = reader.readLine();
				if (ret != null && !ret.trim().equals("")) {
					str_return.append(ret + "\n");
				}
			}
			reader.close();
			conn.disconnect();
		} catch (ConnectException e) {
			System.out.println("ConnectException");
			System.out.println(e);
			throw e;
		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e);
			throw e;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
			try {
				out.close();
			} catch (Exception e) {
			}
		}
		System.out.println(str_return.toString());
	}
	public static void url8() throws Exception {
		InputStream in = null;
		OutputStream out = null;
		StringBuffer str_return = new StringBuffer();
		String urlString = "https://kyfw.12306.cn/otn/leftTicket/submitOrderRequest";
		try {
			Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1", 8888));
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(urlString);
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection(proxy);
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setRequestProperty("Cookie", "BIGipServerotn="+BIGipServerotn+";JSESSIONID="+JSESSIONID);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// conn.connect();
			PrintWriter writer = new PrintWriter(conn.getOutputStream());
			// 鍙戦�佽姹傚弬鏁�
			writer.print("secretStr=MjAxNS0xMi0wNCMwMCNHMTAxIzA1OjM3IzA3OjAwIzI0MDAwMEcxMDEwQSNWTlAjQU9IIzEyOjM3I+WMl+S6rOWNlyPkuIrmtbfombnmoaUjMDEjMTAjTzAwMDAwMDg5OE0wMDAwMDAwNDQ5MDAwMDAwMDE1I1AyIzE0NDg3MTUyODk2MDIjMTQ0NDEwNTgwMDAwMCM5MUJBMkVGODJERjU1NjFDQUU2MkMwRDVERDgyMkNCQUI3MDI4Mjg4RjA5NkY0MjU3N0RDMEVGRQ=="
					+"&train_date=2015-12-04&back_train_date=2015-11-28&tour_flag=dc"
					+"purpose_codes=ADULT&query_from_station_name=北京南"
					+"&query_to_station_name=上海虹桥&undefined="
					);
			
			// flush杈撳嚭娴佺殑缂撳啿
			writer.flush();
			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));
			// DataInputStream indata = new DataInputStream(is);
			String ret = "";
			while (ret != null) {
				ret = reader.readLine();
				if (ret != null && !ret.trim().equals("")) {
					str_return.append(ret + "\n");
				}
			}
			reader.close();
			conn.disconnect();
		} catch (ConnectException e) {
			System.out.println("ConnectException");
			System.out.println(e);
			throw e;
		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e);
			throw e;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
			try {
				out.close();
			} catch (Exception e) {
			}
		}
		System.out.println(str_return.toString());
	}
	public static void url9() throws Exception {
		InputStream in = null;
		OutputStream out = null;
		StringBuffer str_return = new StringBuffer();
		String urlString = "https://kyfw.12306.cn/otn/confirmPassenger/initDc";
		try {
			Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1", 8888));
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(urlString);
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection(proxy);
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setRequestProperty("Cookie", "BIGipServerotn="+BIGipServerotn+";JSESSIONID="+JSESSIONID);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// conn.connect();
			// 鑾峰彇URLConnection瀵硅薄瀵瑰簲鐨勮緭鍑烘祦
						PrintWriter writer = new PrintWriter(conn.getOutputStream());
						// 鍙戦�佽姹傚弬鏁�
						writer.print("_json_att=");
						// flush杈撳嚭娴佺殑缂撳啿
						writer.flush();
			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));
			// DataInputStream indata = new DataInputStream(is);
			String ret = "";
			while (ret != null) {
				ret = reader.readLine();
				if (ret != null && !ret.trim().equals("")) {
					str_return.append(ret + "\n");
				}
			}
			reader.close();
			conn.disconnect();
		} catch (ConnectException e) {
			System.out.println("ConnectException");
			System.out.println(e);
			throw e;
		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e);
			throw e;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
			try {
				out.close();
			} catch (Exception e) {
			}
		}
		String reg = "src=\"(/otn/dynamicJs/.+?)\"";
		String reg2 = "globalRepeatSubmitToken = \'(.+?)\'";
		 Pattern p = Pattern.compile(reg);
		 Pattern p2 = Pattern.compile(reg2);
		Matcher m = p.matcher(str_return);
		Matcher m2 = p2.matcher(str_return);
	    while(m.find()) {
	    	if(m.group(1).length()>1)dynamicJs2 = m.group(1);
	    //	System.out.println(m.group(1));
	    }
	    while(m2.find()) {
	    	if(m2.group(1).length()>1)globalRepeatSubmitToken = m2.group(1);
	    //	System.out.println(m.group(1));
	    }
		System.out.println(str_return.toString());
	}
	public static void url10() throws Exception {
		InputStream in = null;
		OutputStream out = null;
		StringBuffer str_return = new StringBuffer();
//		String urlString = "https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn";
		String urlString = "https://kyfw.12306.cn"+dynamicJs2;
		try {
			Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1", 8888));
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(urlString);
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection(proxy);
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setRequestProperty("Cookie", "BIGipServerotn="+BIGipServerotn+";JSESSIONID="+JSESSIONID);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// conn.connect();
			// 鑾峰彇URLConnection瀵硅薄瀵瑰簲鐨勮緭鍑烘祦
			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));
			// DataInputStream indata = new DataInputStream(is);
			String ret = "";
			while (ret != null) {
				ret = reader.readLine();
				if (ret != null && !ret.trim().equals("")) {
					str_return.append(ret + "\n");
				}
			}
			reader.close();
			conn.disconnect();
		} catch (ConnectException e) {
			System.out.println("ConnectException");
			System.out.println(e);
			throw e;
		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e);
			throw e;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
			try {
				out.close();
			} catch (Exception e) {
			}
		}
	}
	public static void url11() throws Exception {
		InputStream in = null;
		OutputStream out = null;
		StringBuffer str_return = new StringBuffer();
//		String urlString = "https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn";
		String urlString = "https://kyfw.12306.cn/otn/confirmPassenger/getPassengerDTOs";
		try {
			Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1", 8888));
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(urlString);
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection(proxy);
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setRequestProperty("Cookie", "BIGipServerotn="+BIGipServerotn+";JSESSIONID="+JSESSIONID);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// conn.connect();
			// 鑾峰彇URLConnection瀵硅薄瀵瑰簲鐨勮緭鍑烘祦
			PrintWriter writer = new PrintWriter(conn.getOutputStream());
			// 鍙戦�佽姹傚弬鏁�
			writer.print("REPEAT_SUBMIT_TOKEN="+globalRepeatSubmitToken+"&_json_att=");
			// flush杈撳嚭娴佺殑缂撳啿
			writer.flush();
			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));
			// DataInputStream indata = new DataInputStream(is);
			String ret = "";
			while (ret != null) {
				ret = reader.readLine();
				if (ret != null && !ret.trim().equals("")) {
					str_return.append(ret + "\n");
				}
			}
			reader.close();
			conn.disconnect();
		} catch (ConnectException e) {
			System.out.println("ConnectException");
			System.out.println(e);
			throw e;
		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e);
			throw e;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
			try {
				out.close();
			} catch (Exception e) {
			}
		}
		
	}
public static void url12() throws Exception {
		InputStream in = null;
		OutputStream out = null;
		StringBuffer str_return = new StringBuffer();
//		String urlString = "https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn";
		String urlString = "https://kyfw.12306.cn/otn/passcodeNew/getPassCodeNew?module=passenger&rand=randp";
		try {
			Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1", 8888));
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(urlString);
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection(proxy);
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setRequestProperty("Cookie", "BIGipServerotn="+BIGipServerotn+";JSESSIONID="+JSESSIONID);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// conn.connect();
			// 鑾峰彇URLConnection瀵硅薄瀵瑰簲鐨勮緭鍑烘祦
			PrintWriter writer = new PrintWriter(conn.getOutputStream());
			// 鍙戦�佽姹傚弬鏁�
			writer.print("");
			// flush杈撳嚭娴佺殑缂撳啿
			writer.flush();
			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));
			// DataInputStream indata = new DataInputStream(is);
			String ret = "";
			while (ret != null) {
				ret = reader.readLine();
				if (ret != null && !ret.trim().equals("")) {
					str_return.append(ret + "\n");
				}
			}
			reader.close();
			conn.disconnect();
		} catch (ConnectException e) {
			System.out.println("ConnectException");
			System.out.println(e);
			throw e;
		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e);
			throw e;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
			try {
				out.close();
			} catch (Exception e) {
			}
		}
	}
	public static void url13() throws Exception {
		InputStream in = null;
		OutputStream out = null;
		StringBuffer str_return = new StringBuffer();
		String urlString = "https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn";
		try {
			Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1", 8888));
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL console = new URL(urlString);
			HttpsURLConnection conn = (HttpsURLConnection) console
					.openConnection(proxy);
			conn.setSSLSocketFactory(sc.getSocketFactory());
			conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			conn.setRequestProperty("Cookie", "BIGipServerotn="+BIGipServerotn+";JSESSIONID="+JSESSIONID);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// conn.connect();
			// 鑾峰彇URLConnection瀵硅薄瀵瑰簲鐨勮緭鍑烘祦
			PrintWriter writer = new PrintWriter(conn.getOutputStream());
			// 鍙戦�佽姹傚弬鏁�
			writer.print("rand=randp&randCode=87,50,171,56REPEAT_SUBMIT_TOKEN="+globalRepeatSubmitToken+"&_json_att=");
			// flush杈撳嚭娴佺殑缂撳啿
			writer.flush();
			InputStream is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));
			// DataInputStream indata = new DataInputStream(is);
			String ret = "";
			while (ret != null) {
				ret = reader.readLine();
				if (ret != null && !ret.trim().equals("")) {
					str_return.append(ret + "\n");
				}
			}
			reader.close();
			conn.disconnect();
		} catch (ConnectException e) {
			System.out.println("ConnectException");
			System.out.println(e);
			throw e;
		} catch (IOException e) {
			System.out.println("IOException");
			System.out.println(e);
			throw e;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
			try {
				out.close();
			} catch (Exception e) {
			}
		}
//		System.out.println(str_return.toString());
	}
	public static void main(String[] args) throws Exception {
//		long start = System.currentTimeMillis();
//		url1();
//		System.out.println(BIGipServerotn);
//		System.out.println(JSESSIONID);
//		System.out.println(dynamicJs);
//		long end = System.currentTimeMillis();
//		System.out.println(end-start);
//		url2();
//		url3();
//		url4();
//		url5();
//		url6();
//		url7();
//		url8();
//		url9();
//		url10();
//		url11();
//		url12();
//		url13();
		url("https://kyfw.12306.cn/otn/leftTicket/queryT?leftTicketDTO.train_date=2015-12-24&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=SHH&purpose_codes=ADULT");
	}
	}

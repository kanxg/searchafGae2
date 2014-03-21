package kxg.searchaf.url.amazon;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.StringTokenizer;

import kxg.searchaf.url.Constant;
import kxg.searchaf.util.ProxyUtli;

public class AmazonLogin {
	public static String cookies = "";

	public static String getCookies() {
		if ("".equals(cookies)) {
			try {
				login();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cookies;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProxyUtli.setProxy(true);
		try {
			login();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void login() throws Exception {

		try {
			URL url = new URL("http://www.amazon.com");

			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			// urlConnection.setRequestProperty("Cookie", cookies);

			urlConnection.setConnectTimeout(Constant.connect_timeout);

			urlConnection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (compatible; MSIE 6.0; Windows NT)");
			urlConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			urlConnection.connect();

			InputStream is = urlConnection.getInputStream();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));

			String s;
			StringBuilder result = new StringBuilder();
			while (((s = reader.readLine()) != null)) {
				result.append(s);
			}

			System.out.println("result= " + result.toString());

			String key;
			String sessionId = "";
			for (int i = 1; (key = urlConnection.getHeaderFieldKey(i)) != null; i++) {
				if (key.equalsIgnoreCase("set-cookie")) {
					String cookieVal = urlConnection.getHeaderField(i);
					cookieVal = cookieVal.substring(0, cookieVal.indexOf(";"));
					sessionId = sessionId + cookieVal + ";";
				}
			}
			cookies = sessionId;

			// System.out.println("cookies= " + cookies);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String parseCookies(HttpURLConnection conn) {
		StringBuffer cookies = new StringBuffer();
		String headName;
		for (int i = 7; (headName = conn.getHeaderField(i)) != null; i++) {
			StringTokenizer st = new StringTokenizer(headName, "; ");
			while (st.hasMoreTokens()) {
				cookies.append(st.nextToken() + "; ");
			}
		}
		return cookies.toString();
	}
}

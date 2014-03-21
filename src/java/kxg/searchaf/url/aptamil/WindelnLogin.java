package kxg.searchaf.url.aptamil;

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

public class WindelnLogin {
	public static String email = "aptamil_kan1@163.com";
	public static String password = "!234qwda";
	public static String loginaction = "https://www.windeln.de/customer/account/loginPost/referer/aHR0cHM6Ly93d3cud2luZGVsbi5kZS9jdXN0b21lci9hY2NvdW50L2luZGV4Lw,,/";
	public static String success_url = "https://www.windeln.de/customer/account/index/";
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

		try {
			login();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void login() throws Exception {
		int chByte = 0;

		URL url = null;

		HttpURLConnection httpConn = null;

		InputStream in = null;
		try {
			String post = "login[username]="
					+ URLEncoder.encode(email, "UTF-8") + "&login[password]="
					+ password + "&success_url="
					+ URLEncoder.encode(success_url, "UTF-8");
			url = new URL(loginaction);

			httpConn = (HttpURLConnection) url.openConnection();

			// setInstanceFollowRedirects can then be used to set if
			// redirects should be followed or not and this should be used
			// before the
			// connection is established (via getInputStream, getResponseCode,
			// and other
			// methods that result in the connection being established).

			httpConn.setFollowRedirects(false);

			// inorder to disable the redirects
			httpConn.setInstanceFollowRedirects(false);
			httpConn.addRequestProperty("Cookie", "...");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (compatible; MSIE 6.0; Windows NT)");
			httpConn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			// ok now, we can post it
			PrintStream send = new PrintStream(httpConn.getOutputStream());
			send.print(post);
			send.close();
			// URL newURL = new URL(httpConn.getHeaderField("Location"));

			// System.out.println("the URL has move to " + newURL);
			httpConn.disconnect();

			// OK, now we are ready to get the cookies out of the URLConnection
			cookies = parseCookies(httpConn);
			// System.out.println(cookies);

			// String key;
			// String sessionId = "";
			// for (int i = 1; (key = httpConn.getHeaderFieldKey(i)) != null;
			// i++) {
			// if (key.equalsIgnoreCase("set-cookie")) {
			// String cookieVal = httpConn.getHeaderField(i);
			// cookieVal = cookieVal.substring(0, cookieVal.indexOf(";"));
			// sessionId = sessionId + cookieVal + ";";
			// }
			// }
			// cookies = sessionId;

			// get clearance page

			// url = new URL("http://www.windeln.de/aptamil-milchnahrung.html");
			//
			// HttpURLConnection urlConnection = (HttpURLConnection) url
			// .openConnection();
			// urlConnection.setRequestProperty("Cookie", cookies);
			//
			// urlConnection.setConnectTimeout(Constant.connect_timeout);
			//
			// urlConnection.setRequestProperty("User-Agent",
			// "Mozilla/5.0 (compatible; MSIE 6.0; Windows NT)");
			// urlConnection.setRequestProperty("Content-Type",
			// "application/x-www-form-urlencoded");
			//
			// urlConnection.connect();
			//
			// InputStream is = urlConnection.getInputStream();
			//
			// BufferedReader reader = new BufferedReader(
			// new InputStreamReader(is));
			//
			// String s;
			// StringBuilder result = new StringBuilder();
			// while (((s = reader.readLine()) != null)) {
			// result.append(s);
			// }
			//
			// System.out.println("result= " + result.toString());

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

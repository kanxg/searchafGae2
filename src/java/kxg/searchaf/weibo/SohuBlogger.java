package kxg.searchaf.weibo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

//import oauth.signpost.OAuth;
//import oauth.signpost.OAuthConsumer;
//import oauth.signpost.OAuthProvider;
//import oauth.signpost.basic.DefaultOAuthConsumer;
//import oauth.signpost.basic.DefaultOAuthProvider;
//import oauth.signpost.http.HttpParameters;

public class SohuBlogger {

//	public static String your_passport = "kanxg@sohu.com";
//	public static String your_password = "fsm1021";
//	public static String consumer_key = "ssW7uU2xdnTepRszorI1";
//	public static String consumer_secret = "LxOimETuKOP*s2QKDBXLWY$S8I=lVl26ai!WS%51";
//
//	public static String access_token = "3be019901d219c9cdbd34e5ee9a83ab9";
//	public static String access_token_secret = "dbaef6b67fb14c4e42a1caef58a73d43";
//
//	// oauth_token=3be019901d219c9cdbd34e5ee9a83ab9&oauth_token_secret=dbaef6b67fb14c4e42a1caef58a73d43&user_id=152180517&screen_name=af�<����&x_auth_expires=0
//
//	public static void main(String[] args) throws Exception {
//		try {
//			// Auth1getToken();
//			// XAuth();
//			send("test:"
//					+ new Date()
//					+ "http://ww1.sinaimg.cn/bmiddle/8d327ba6jw1e26tp993t0j.jpg");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void sendWeibo() {
//
//	}
//
//	public static void send(String text) throws Exception {
//		OAuthConsumer consumer = new DefaultOAuthConsumer(consumer_key,
//				consumer_secret);
//		consumer.setTokenWithSecret(access_token, access_token_secret);
//		URL url = new URL("http://api.t.sohu.com/statuses/update.json");
//		HttpURLConnection request = (HttpURLConnection) url.openConnection();
//		request.setDoOutput(true);
//		request.setRequestMethod("POST");
//		HttpParameters para = new HttpParameters();
//		para.put("status",
//				URLEncoder.encode(text, "utf-8").replaceAll("\\+", "%20"));
//		consumer.setAdditionalParameters(para);
//		consumer.sign(request);
//		OutputStream ot = request.getOutputStream();
//		ot.write(("status=" + URLEncoder.encode(text, "utf-8")).replaceAll(
//				"\\+", "%20").getBytes());
//		ot.flush();
//		ot.close();
//		System.out.println("Sending request...");
//		request.connect();
//		System.out.println("Response: " + request.getResponseCode() + " "
//				+ request.getResponseMessage());
//		BufferedReader reader = new BufferedReader(new InputStreamReader(
//				request.getInputStream()));
//		String b = null;
//		while ((b = reader.readLine()) != null) {
//			System.out.println(b);
//		}
//	}
//	/**
//	 * 
//	 * @param text
//	 * @param imageUrl
//	 * @throws Exception
//
//	public static void send(String text, String imageUrl) throws Exception {
//		System.out.println(System.getProperty("debug"));
//		OAuthConsumer consumer = new DefaultOAuthConsumer(consumer_key,
//				consumer_secret);
//		consumer.setTokenWithSecret(access_token, access_token_secret);
//		URL url = new URL("http://api.t.sohu.com/statuses/upload.json");
//		HttpURLConnection request = (HttpURLConnection) url.openConnection();
//		request.setDoOutput(true);
//		request.setRequestMethod("POST");
//		HttpParameters para = new HttpParameters();
//		String status = URLEncoder.encode(text, "utf-8").replaceAll("\\+",
//				"%20");
//		para.put("status", status);
//		String boundary = "---------------------------37531613912423";
//		String content = "--" + boundary
//				+ "\r\nContent-Disposition: form-data; name=\"status\"\r\n\r\n";
//		String pic = "\r\n--"
//				+ boundary
//				+ "\r\nContent-Disposition: form-data; name=\"pic\"; filename=\"postpic.jpg\"\r\nContent-Type: image/jpeg\r\n\r\n";
//		byte[] end_data = ("\r\n--" + boundary + "--\r\n").getBytes();
//		File f = new File("c:\\s.jpg");
//		FileInputStream stream = new FileInputStream(f);
//		byte[] file = new byte[(int) f.length()];
//		stream.read(file);
//		request.setRequestProperty("Content-Type",
//				"multipart/form-data; boundary=" + boundary); // 设置表单类型和分隔符
//		request.setRequestProperty(
//				"Content-Length",
//				String.valueOf(content.getBytes().length
//						+ "test".getBytes().length + pic.getBytes().length
//						+ f.length() + end_data.length)); // 设置内容长度
//		consumer.setAdditionalParameters(para);
//		consumer.sign(request);
//		OutputStream ot = request.getOutputStream();
//		ot.write(content.getBytes());
//		ot.write(status.getBytes());
//		ot.write(pic.getBytes());
//		ot.write(file);
//		ot.write(end_data);
//		ot.flush();
//		ot.close();
//		System.out.println("Sending request...");
//		request.connect();
//		System.out.println("Response: " + request.getResponseCode() + " "
//				+ request.getResponseMessage());
//		BufferedReader reader = new BufferedReader(new InputStreamReader(
//				request.getInputStream()));
//		String b = null;
//		while ((b = reader.readLine()) != null) {
//			System.out.println(b);
//		}
//	}
//*/
//	
//	public static void XAuth() throws Exception {
//		OAuthConsumer consumer = new DefaultOAuthConsumer(consumer_key,
//				consumer_secret);
//		URL url = new URL("http://api.t.sohu.com/oauth/access_token");
//		HttpURLConnection request = (HttpURLConnection) url.openConnection();
//		request.setDoOutput(true);
//		request.setRequestMethod("POST");
//		HttpParameters para = new HttpParameters();
//		para.put("x_auth_username", URLEncoder.encode(your_passport, "utf-8"));
//		para.put("x_auth_password", your_password);
//		para.put("x_auth_mode", "client_auth");
//		consumer.setAdditionalParameters(para);
//		consumer.sign(request);
//		OutputStream ot = request.getOutputStream();
//		ot.write(("x_auth_username="
//				+ URLEncoder.encode(your_passport, "utf-8")
//				+ "&x_auth_password=" + your_password + "&x_auth_mode=client_auth")
//				.getBytes());
//		ot.flush();
//		ot.close();
//		System.out.println("Sending request...");
//		request.connect();
//		System.out.println("Response: " + request.getResponseCode() + " "
//				+ request.getResponseMessage());
//		BufferedReader reader = new BufferedReader(new InputStreamReader(
//				request.getInputStream()));
//		String b = null;
//		StringBuffer sb = new StringBuffer();
//		while ((b = reader.readLine()) != null) {
//			// System.out.println(b);
//			sb.append(b);
//		}
//		request.disconnect();
//		parseToken(sb.toString());
//	}
//
//	private static void parseToken(String token) {
//		System.out.println(token);
//		String[] arrSplit = token.split("&");
//		String access_tokenStr = arrSplit[0];
//		String access_token_secretStr = arrSplit[1];
//		System.out.println(access_tokenStr);
//		System.out.println(access_token_secretStr);
//		access_token = access_tokenStr.substring(
//				access_tokenStr.indexOf("=") + 1, access_tokenStr.length());
//		access_token_secret = access_token_secretStr.substring(
//				access_token_secretStr.indexOf("=") + 1,
//				access_token_secretStr.length());
//		System.out.println(access_token);
//		System.out.println(access_token_secret);
//	}
//
//	public static void Auth1getToken() throws Exception {
//
//		OAuthConsumer consumer = new DefaultOAuthConsumer(consumer_key,
//				consumer_secret);
//		OAuthProvider provider = new DefaultOAuthProvider(
//				"http://api.t.sohu.com/oauth/request_token",
//				"http://api.t.sohu.com/oauth/access_token",
//				"http://api.t.sohu.com/oauth/authorize?hd=default");
//		System.out.println("Fetching request token...");
//
//		String authUrl = provider.retrieveRequestToken(consumer,
//				OAuth.OUT_OF_BAND);
//
//		System.out.println("Request token: " + consumer.getToken());
//		System.out.println("Token secret: " + consumer.getTokenSecret());
//
//		System.out.println("Now visit:\n" + authUrl
//				+ "\n... and grant this app authorization");
//		System.out
//				.println("Enter the verification code and hit ENTER when you're done:");
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String verificationCode = br.readLine();
//		System.out.println("verificationCode=" + verificationCode);
//		System.out.println("Fetching access token...");
//		provider.retrieveAccessToken(consumer, verificationCode.trim());
//		System.out.println("Access token: " + consumer.getToken());
//		System.out.println("Token secret: " + consumer.getTokenSecret());
//
//		URL url = new URL(
//				"http://api.t.sohu.com/statuses/friends_timeline.json");
//		HttpURLConnection request = (HttpURLConnection) url.openConnection();
//
//		consumer.sign(request);
//
//		System.out.println("Sending request...");
//		request.connect();
//
//		System.out.println("Response: " + request.getResponseCode() + " "
//				+ request.getResponseMessage());
//
//	}
}

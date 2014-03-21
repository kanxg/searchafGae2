package kxg.searchaf.url;

import java.net.URLEncoder;

public class Constant {

	public static String ip = "127.0.0.1";
	public static String port = "8087";

	public static String ENCODE = "UTF-8";

	public static long sleeptime = 10L; // minutes

	public static int connect_timeout = 1000 * 5; // set timeout to 5 seconds

	// no use
	public static int maxMailCCcount = 1;

	public static String serverName;

	public static String viglinkTag = "http://redirect.viglink.com/?key=1fd42f1d7997ed7abc43c8b7486548c4&out=";

	public static String mergeViglink(String url) {
		try {
			url = URLEncoder.encode(url, "utf-8");
		} catch (Exception e) {
		}
		return viglinkTag + url;
	}

	public static void main(String[] args) throws Exception {
		String url = "http://www.sephora.com/dp/B000HZEQSU/ref=as_li_ss_til?tag=helmut-20";

		System.out.println(Constant.mergeViglink(url));
	}
}

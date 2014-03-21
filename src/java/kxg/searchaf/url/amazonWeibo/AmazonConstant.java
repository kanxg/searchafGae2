package kxg.searchaf.url.amazonWeibo;

public class AmazonConstant {

	public static String singletag = "?t=ebuybot-20";
	public static String andtag = "&t=ebuybot-20";

	private static String addSalesTag(String url) {
		int i = url.indexOf("&");
		if (i > 0) {
			url = url.substring(0, i) + andtag + url.substring(i + 1);
		} else {
			url = url + singletag;
		}
		return url;
	}
}

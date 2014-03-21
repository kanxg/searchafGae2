package kxg.searchaf.util;

import java.util.Properties;

import kxg.searchaf.url.Constant;

public class ProxyUtli {

	public static void setProxy(boolean usingProxy) {
		Properties prop = System.getProperties();
		if (usingProxy) {
			prop.setProperty("http.proxyHost", Constant.ip);
			prop.setProperty("http.proxyPort", Constant.port);
		} else {
			prop.setProperty("http.proxyHost", "");
			prop.setProperty("http.proxyPort", "");
		}
	}
}

package kxg.searchaf.url.victoriassecret;

import java.util.ArrayList;
import java.util.List;

public class VictoriassecretPage {

	public String url;

	public VictoriassecretPage(String url) {
		this.url = url;
	}

	public static List<VictoriassecretPage> getWomenSale() {
		ArrayList<VictoriassecretPage> urllist = new ArrayList<VictoriassecretPage>();
		// String type = "sale";
		// String category = "";
		// page 1
		// String url =
		// "http://www.victoriassecret.com/clothing-sale/clothing-sale,default,sc.html";
		String url = "http://www.victoriassecret.com/sale/bras?pageAt=all";
		VictoriassecretPage page = new VictoriassecretPage(url);
		urllist.add(page);

		// url = "http://www.victoriassecret.com/sale/panties?pageAt=all";
		// page = new VictoriassecretPage(url);
		// urllist.add(page);

		return urllist;
	}

}

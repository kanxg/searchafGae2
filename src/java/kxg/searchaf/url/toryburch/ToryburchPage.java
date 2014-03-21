package kxg.searchaf.url.toryburch;

import java.util.ArrayList;
import java.util.List;

public class ToryburchPage {

	public String url;

	public ToryburchPage(String url) {
		this.url = url;
	}

	public static List<ToryburchPage> getWomenSale() {
		ArrayList<ToryburchPage> urllist = new ArrayList<ToryburchPage>();
		// String type = "sale";
		// String category = "";
		// page 1
		// String url =
		// "http://www.toryburch.com/clothing-sale/clothing-sale,default,sc.html";
		String url = "http://www.toryburch.com/clothing-sale/clothing-sale,default,sc.html?sz=99&start=0&format=ajax";
		ToryburchPage page = new ToryburchPage(url);
		urllist.add(page);

		// shoes
		url = "http://www.toryburch.com/shoes-sale/shoes-sale,default,sc.html?sz=99&start=0&format=ajax";
		page = new ToryburchPage(url);
		urllist.add(page);

		// handbags
		url = "http://www.toryburch.com/handbags-accessories-jewelry-sale/handbags-sale,default,sc.html?sz=99&start=0&format=ajax";
		page = new ToryburchPage(url);
		urllist.add(page);

		return urllist;
	}

}

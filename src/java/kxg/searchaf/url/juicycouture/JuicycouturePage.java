package kxg.searchaf.url.juicycouture;

import java.util.ArrayList;
import java.util.List;

public class JuicycouturePage {

	public String url;

	public JuicycouturePage(String url) {
		this.url = url;
	}

	public static List<JuicycouturePage> getWomenSale() {
		ArrayList<JuicycouturePage> urllist = new ArrayList<JuicycouturePage>();
		// String type = "sale";
		// String category = "";
		// page 1
		String url = "http://www.juicycouture.com/on/demandware.store/Sites-JuicyCouture-Site/en_US/Search-Products?cgid=sale-womens&sz=24&ajax=true";
		JuicycouturePage page = null;

		for (int i = 0; i < 12; i++) {
			String url1 = url + "&start=" + i * 24;
			page = new JuicycouturePage(url1);
			urllist.add(page);
		}
		return urllist;
	}

}

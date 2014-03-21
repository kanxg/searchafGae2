package kxg.searchaf.url.saks_cat;

import java.util.ArrayList;
import java.util.List;

public class SaksPage {

	public String url;

	public SaksPage(String url) {
		this.url = url;
	}

	public static List<SaksPage> getWomenSale() {
		ArrayList<SaksPage> urllist = new ArrayList<SaksPage>();
		// String type = "sale";
		// String category = "";
		// page 1
		String url = "http://www.saksfifthavenue.com/search/EndecaSearch.jsp?N=306418048+1553";
		SaksPage page = new SaksPage(url);
		urllist.add(page);

		for (int i = 1; i < 10; i++) {
			String url1 = url + "&Nao=" + i * 60;
			page = new SaksPage(url1);
			urllist.add(page);
		}

		// page 2
		// url =
		// "http://www.saksfifthavenue.com/search/EndecaSearch.jsp?Nao=180&N=306418048+1553";
		// page = new SaksPage(url);
		// urllist.add(page);

		return urllist;
	}

	public static List<SaksPage> getMakeUPSale() {
		ArrayList<SaksPage> urllist = new ArrayList<SaksPage>();
		// String type = "sale";
		// String category = "";
		// page 1

		String url = "http://www.saksfifthavenue.com/Beauty-and-Fragrance/For-Her/shop/_/N-52flrm?FOLDER%3C%3Efolder_id=2534374306418162";
		SaksPage page = new SaksPage(url);
		urllist.add(page);

		for (int i = 1; i < 100; i++) {
			String url1 = url + "&Nao=" + i * 60;
			page = new SaksPage(url1);
			urllist.add(page);
		}

		// for (int i = 1; i < 42; i++) {
		// String url1 = url + "&Nao=" + i * 60;
		// page = new SaksPage(url1);
		// urllist.add(page);
		// }

		// page 2
		// url =
		// "http://www.saksfifthavenue.com/search/EndecaSearch.jsp?Nao=180&N=306418048+1553";
		// page = new SaksPage(url);
		// urllist.add(page);

		return urllist;
	}

}

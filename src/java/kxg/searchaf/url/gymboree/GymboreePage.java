package kxg.searchaf.url.gymboree;

import java.util.ArrayList;
import java.util.List;

public class GymboreePage {

	public String url;

	public GymboreePage(String url) {
		this.url = url;
	}

	public static List<GymboreePage> getBabyGirlSale() {
		ArrayList<GymboreePage> urllist = new ArrayList<GymboreePage>();
		// String type = "sale";
		// String category = "";
		// page 1
		String url = "http://www.gymboree.com/shop/dept_category.jsp?FOLDER%3C%3Efolder_id=2534374304777285&pageClicked=0";
		GymboreePage page = new GymboreePage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<GymboreePage> getGirlSale() {
		ArrayList<GymboreePage> urllist = new ArrayList<GymboreePage>();
		// String type = "sale";
		// String category = "";
		// page 1
		String url = "http://www.gymboree.com/shop/dept_category.jsp?FOLDER%3C%3Efolder_id=2534374304777283&pageClicked=0";
		GymboreePage page = new GymboreePage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<GymboreePage> getBoySale() {
		ArrayList<GymboreePage> urllist = new ArrayList<GymboreePage>();
		// String type = "sale";
		// String category = "";
		// page 1
		String url = "http://www.gymboree.com/shop/dept_category.jsp?FOLDER%3C%3Efolder_id=2534374306251404&pageClicked=0";
		GymboreePage page = new GymboreePage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<GymboreePage> getBabyBoySale() {
		ArrayList<GymboreePage> urllist = new ArrayList<GymboreePage>();
		// String type = "sale";
		// String category = "";
		// page 1
		String url = "http://www.gymboree.com/shop/dept_category.jsp?FOLDER%3C%3Efolder_id=2534374306251394&pageClicked=0";
		GymboreePage page = new GymboreePage(url);
		urllist.add(page);

		return urllist;
	}
}

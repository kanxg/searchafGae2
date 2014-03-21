package kxg.searchaf.url.juicycouture;

import java.util.*;

import kxg.searchaf.util.ProxyUtli;

public class SearchJuicycoutureJSP {

	public HashMap<String, JuicycoutureProduct> allnewprolist = new HashMap<String, JuicycoutureProduct>();

	public ArrayList<JuicycoutureProduct> matchprolist = new ArrayList<JuicycoutureProduct>();

	public static void main(String[] args) {
		ProxyUtli.setProxy(true);
		SearchJuicycoutureJSP af = new SearchJuicycoutureJSP();

		// while (true) {
		try {

			// System.out.println("start checking...");
			af.getwomendiscountproductforJsp("0.9", "", "");
			// System.out.println(new Date() + "done...");
			// Thread.sleep(1000 * 60 * Constant.sleeptime); // sleep 10 mines
		} catch (Exception e) {
			e.printStackTrace();
		}
		// }
	}

	private float parsrDiscount(String discountStr) {
		if (discountStr != null) {
			try {
				return Float.parseFloat(discountStr);
			} catch (NumberFormatException e) {

			}
		}
		return JuicycoutureConstant.warningdiscount;
	}

	public ArrayList<JuicycoutureProduct> getcustomerdiscountproductforJsp(
			String searchUrl, String discountStr, String email,
			String searchtext) {
		boolean haslicense = JuicycoutureMailList.HasLicense(email);
		float discount = parsrDiscount(discountStr);
		// if (haslicense) {
		// discount = parsrDiscount(discountStr);
		// } else {
		// discount = AfConstant.warningdiscount;
		// }

		checkcustomerdiscountproduct(searchUrl, discount, haslicense,
				searchtext.trim());

		Collections.sort(matchprolist);
		return matchprolist;
	}

	public ArrayList<JuicycoutureProduct> getwomendiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = JuicycoutureMailList.HasLicense(email);
		float discount = parsrDiscount(discountStr);
		// if (haslicense) {
		// discount = parsrDiscount(discountStr);
		// } else {
		// discount = AfConstant.warningdiscount;
		// }
		checkWoMendiscountproduct(discount, haslicense, searchtext.trim());
		Collections.sort(matchprolist);
		return matchprolist;
	}

	private void checkcustomerdiscountproduct(String searchUrl, Float discount,
			boolean hasLicense, String searchtext) {

		try {
			JuicycouturePage JuicycouturePage = new JuicycouturePage(searchUrl);
			ParserJuicycouturePage ParserJuicycouturePage = new ParserJuicycouturePage(
					JuicycouturePage, allnewprolist);
			ParserJuicycouturePage.checkprice();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<String> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			JuicycoutureProduct product = allnewprolist.get(key);

			if (product.realdiscount <= discount)
				if (searchtext == null
						|| searchtext.equals("")
						|| product.name.toLowerCase().indexOf(
								searchtext.toLowerCase()) >= 0)
					// check ignore product
					matchprolist.add(product);

		}
		System.out.println("found meet price products:" + matchprolist.size());

	}

	private void checkWoMendiscountproduct(Float discount, boolean hasLicense,
			String searchtext) {

		List<JuicycouturePage> urllist = JuicycouturePage.getWomenSale();
		for (int i = 0; i < urllist.size(); i++) {
			JuicycouturePage JuicycouturePage = urllist.get(i);
			ParserJuicycouturePage ParserJuicycouturePage = new ParserJuicycouturePage(
					JuicycouturePage, allnewprolist);
			int currentSize = allnewprolist.size();
			try {
				ParserJuicycouturePage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (currentSize == allnewprolist.size())
				break;
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<String> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			JuicycoutureProduct product = allnewprolist.get(key);
			if (product.realdiscount <= discount)
				if (searchtext == null
						|| searchtext.equals("")
						|| product.name.toLowerCase().indexOf(
								searchtext.toLowerCase()) >= 0)
					// check ignore product
					matchprolist.add(product);

		}
		System.out.println("found meet price products:" + matchprolist.size());

	}

}

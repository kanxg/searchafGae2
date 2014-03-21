package kxg.searchaf.url.hollisterco;

import java.util.*;

import kxg.searchaf.util.ProxyUtli;

public class SearchHollistercoJSP {

	public HashMap<Integer, HollistercoProduct> allnewprolist = new HashMap<Integer, HollistercoProduct>();

	public List<HollistercoProduct> matchprolist = new ArrayList<HollistercoProduct>();

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);

		SearchHollistercoJSP hollisterco = new SearchHollistercoJSP();
		// hollisterco.getwomendiscountproductforJsp("0.9", "", "");
		hollisterco.getmendiscountproductforJsp("0.9", "", "");
		// for (int i = 0; i < hollisterco.matchprolist.size(); i++) {
		// HollistercoProduct product = hollisterco.matchprolist.get(i);
		// System.out.println(product);
		// }

	}

	private float parsrDiscount(String discountStr) {
		if (discountStr != null) {
			try {
				return Float.parseFloat(discountStr);
			} catch (NumberFormatException e) {

			}
		}
		return HollistercoConstant.warningdiscount;
	}

	public List<HollistercoProduct> getcustomerdiscountproductforJsp(
			String searchurl, String discountStr, String email,
			String searchtext) {
		boolean haslicense = HollistercoMailList.HasLicense(email);
		float discount = parsrDiscount(discountStr);
		// if (haslicense) {
		// discount = parsrDiscount(discountStr);
		// } else {
		// discount = HollistercoConstant.warningdiscount;
		// }

		getcustomerdiscountproductforJsp(searchurl, discount, haslicense,
				searchtext.trim());

		Collections.sort(matchprolist);
		return matchprolist;
	}

	public List<HollistercoProduct> getmendiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = HollistercoMailList.HasLicense(email);
		float discount = parsrDiscount(discountStr);
		// if (haslicense) {
		// discount = parsrDiscount(discountStr);
		// } else {
		// discount = HollistercoConstant.warningdiscount;
		// }

		checkMendiscountproduct(discount, haslicense, searchtext.trim());

		Collections.sort(matchprolist);
		return matchprolist;
	}

	public List<HollistercoProduct> getwomendiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = HollistercoMailList.HasLicense(email);
		float discount = parsrDiscount(discountStr);
		// if (haslicense) {
		// discount = parsrDiscount(discountStr);
		// } else {
		// discount = HollistercoConstant.warningdiscount;
		// }
		checkWoMendiscountproduct(discount, haslicense, searchtext.trim());
		Collections.sort(matchprolist);
		return matchprolist;
	}

	private void checkMendiscountproduct(Float discount, boolean hasLicense,
			String searchtext) {

		List<HollistercoPage> urllist = HollistercoPage
				.getMenclearanceBySinglePageInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			HollistercoPage hollistercopage = urllist.get(i);
			ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(
					hollistercopage, allnewprolist);
			try {
				parserHollistercopage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		urllist = HollistercoPage.getMenSaleBySinglePageInstance(); // getSaleInstance();getSaleBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			HollistercoPage hollistercopage = urllist.get(i);
			ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(
					hollistercopage, allnewprolist);
			try {
				parserHollistercopage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		urllist = HollistercoPage.getMenSecretInstance();
		for (int i = 0; i < urllist.size(); i++) {
			HollistercoPage hollistercopage = urllist.get(i);
			ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(
					hollistercopage, allnewprolist);
			try {
				parserHollistercopage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			HollistercoProduct product = allnewprolist.get(key);
			if (product.realdiscount <= discount)
				if (searchtext == null
						|| searchtext.equals("")
						|| product.name.toLowerCase().indexOf(
								searchtext.toLowerCase()) >= 0) {
					if (hasLicense)
						checkInventory(product, false);
					// check ignore product
					matchprolist.add(product);
				}
		}
		System.out.println("found meet price products:" + matchprolist.size());

	}

	private void getcustomerdiscountproductforJsp(String searchurl,
			Float discount, boolean hasLicense, String searchtext) {

		HollistercoPage hollistercopage = new HollistercoPage(searchurl);
		ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(
				hollistercopage, allnewprolist);
		try {
			parserHollistercopage.checkprice();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			HollistercoProduct product = allnewprolist.get(key);
			if (product.realdiscount <= discount)
				if (searchtext == null
						|| searchtext.equals("")
						|| product.name.toLowerCase().indexOf(
								searchtext.toLowerCase()) >= 0) {
					if (hasLicense)
						checkInventory(product, false);
					// check ignore product
					matchprolist.add(product);
				}
		}
		System.out.println("found meet price products:" + matchprolist.size());

	}

	private void checkWoMendiscountproduct(Float discount, boolean hasLicense,
			String searchtext) {

		List<HollistercoPage> urllist = HollistercoPage
				.getWomenclearanceBySinglePageInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			HollistercoPage hollistercopage = urllist.get(i);
			ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(
					hollistercopage, allnewprolist);

			try {
				parserHollistercopage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// if (hasLicense) {
		urllist = HollistercoPage.getWomenSaleBySinglePageInstance(); // getSaleInstance();getSaleBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			HollistercoPage hollistercopage = urllist.get(i);
			ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(
					hollistercopage, allnewprolist);

			try {
				parserHollistercopage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		urllist = HollistercoPage.getWomenSecretInstance();
		for (int i = 0; i < urllist.size(); i++) {
			HollistercoPage hollistercopage = urllist.get(i);
			ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(
					hollistercopage, allnewprolist);

			try {
				parserHollistercopage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// }

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			HollistercoProduct product = allnewprolist.get(key);
			if (product.realdiscount <= discount)
				if (searchtext == null
						|| searchtext.equals("")
						|| product.name.toLowerCase().indexOf(
								searchtext.toLowerCase()) >= 0) {
					if (hasLicense)
						checkInventory(product, false);
					// check ignore product
					matchprolist.add(product);
				}
		}
		System.out.println("found meet price products:" + matchprolist.size());

	}

	// TODO not finish
	private void checkInventory(HollistercoProduct product, boolean realtime) {
		realtime = true;
		if (realtime) {
			ParserHollistercoProduct ParserHollistercoProduct = new ParserHollistercoProduct(
					product);
			ParserHollistercoProduct.checkColorItemInventory(true);
		} else {

		}
	}
}

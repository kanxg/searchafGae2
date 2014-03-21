package kxg.searchaf.url.saks_cat;

import java.util.*;

import kxg.searchaf.url.Constant;

public class SearchSaksJSP {

	public HashMap<String, SaksProduct> allnewprolist = new HashMap<String, SaksProduct>();

	public ArrayList<SaksProduct> matchprolist = new ArrayList<SaksProduct>();

	public static void main(String[] args) {
		SearchSaksJSP af = new SearchSaksJSP();

		// while (true) {
		try {

			// System.out.println("start checking...");
			af.getwomendiscountproductforJsp("0.3", "", "");
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
		return SaksConstant.warningdiscount;
	}

	public ArrayList<SaksProduct> getcustomerdiscountproductforJsp(
			String searchUrl, String discountStr, String email,
			String searchtext) {
		boolean haslicense = SaksMailList.HasLicense(email);
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

	public ArrayList<SaksProduct> getwomendiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = SaksMailList.HasLicense(email);
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

	public ArrayList<SaksProduct> getmakeupdiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = SaksMailList.HasLicense(email);
		float discount = parsrDiscount(discountStr);
		// if (haslicense) {
		// discount = parsrDiscount(discountStr);
		// } else {
		// discount = AfConstant.warningdiscount;
		// }
		checkmakeupdiscountproduct(discount, haslicense, searchtext.trim());
		Collections.sort(matchprolist);
		return matchprolist;
	}

	private void checkcustomerdiscountproduct(String searchUrl, Float discount,
			boolean hasLicense, String searchtext) {

		try {
			SaksPage SaksPage = new SaksPage(searchUrl);
			ParserSaksPage ParserSaksPage = new ParserSaksPage(SaksPage,
					allnewprolist);
			ParserSaksPage.checkprice();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<String> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			SaksProduct product = allnewprolist.get(key);

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

		try {
			List<SaksPage> urllist = SaksPage.getWomenSale();
			for (int i = 0; i < urllist.size(); i++) {
				SaksPage SaksPage = urllist.get(i);
				int currentSize = allnewprolist.size();
				ParserSaksPage ParserSaksPage = new ParserSaksPage(SaksPage,
						allnewprolist);
				ParserSaksPage.checkprice();
				if (currentSize == allnewprolist.size())
					break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<String> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			SaksProduct product = allnewprolist.get(key);
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

	private void checkmakeupdiscountproduct(Float discount, boolean hasLicense,
			String searchtext) {

		try {
			List<SaksPage> urllist = SaksPage.getMakeUPSale();
			for (int i = 0; i < urllist.size(); i++) {
				SaksPage SaksPage = urllist.get(i);
				ParserSaksPage ParserSaksPage = new ParserSaksPage(SaksPage,
						allnewprolist);
				ParserSaksPage.checkprice();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<String> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			SaksProduct product = allnewprolist.get(key);
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

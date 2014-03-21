package kxg.searchaf.url.zara;

import java.util.*;

public class SearchZaraJSP {

	public HashMap<Integer, ZaraProduct> allnewprolist = new HashMap<Integer, ZaraProduct>();

	public ArrayList<ZaraProduct> matchprolist = new ArrayList<ZaraProduct>();

	public static void main(String[] args) {
		SearchZaraJSP af = new SearchZaraJSP();

		// while (true) {
		try {

			// System.out.println("start checking...");
			af.getgirldiscountproductforJsp("0.3", "", "");
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
		return ZaraConstant.warningdiscount;
	}

	public ArrayList<ZaraProduct> getcustomerdiscountproductforJsp(
			String searchUrl, String discountStr, String email,
			String searchtext) {
		boolean haslicense = ZaraMailList.HasLicense(email);
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

	public ArrayList<ZaraProduct> getmendiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = ZaraMailList.HasLicense(email);
		float discount = parsrDiscount(discountStr);
		// if (haslicense) {
		// discount = parsrDiscount(discountStr);
		// } else {
		// discount = AfConstant.warningdiscount;
		// }

		checkMendiscountproduct(discount, haslicense, searchtext.trim());

		Collections.sort(matchprolist);
		return matchprolist;
	}

	public ArrayList<ZaraProduct> getboydiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = ZaraMailList.HasLicense(email);
		float discount = parsrDiscount(discountStr);
		// if (haslicense) {
		// discount = parsrDiscount(discountStr);
		// } else {
		// discount = AfConstant.warningdiscount;
		// }

		checkBoydiscountproduct(discount, haslicense, searchtext.trim());

		Collections.sort(matchprolist);
		return matchprolist;
	}

	public ArrayList<ZaraProduct> getwomendiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = ZaraMailList.HasLicense(email);
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

	public ArrayList<ZaraProduct> getgirldiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = ZaraMailList.HasLicense(email);
		float discount = parsrDiscount(discountStr);
		// if (haslicense) {
		// discount = parsrDiscount(discountStr);
		// } else {
		// discount = AfConstant.warningdiscount;
		// }
		checkGirldiscountproduct(discount, haslicense, searchtext.trim());
		Collections.sort(matchprolist);
		return matchprolist;
	}

	private void checkcustomerdiscountproduct(String searchUrl, Float discount,
			boolean hasLicense, String searchtext) {

		try {
			ZaraPage ZaraPage = new ZaraPage(searchUrl);
			ParserZaraPage ParserZaraPage = new ParserZaraPage(ZaraPage,
					allnewprolist);
			ParserZaraPage.checkprice();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			ZaraProduct product = allnewprolist.get(key);

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

	private void checkMendiscountproduct(Float discount, boolean hasLicense,
			String searchtext) {

		List<ZaraPage> urllist = ZaraPage.getMenSale();
		for (int i = 0; i < urllist.size(); i++) {
			ZaraPage ZaraPage = urllist.get(i);
			ParserZaraPage ParserZaraPage = new ParserZaraPage(ZaraPage,
					allnewprolist);
			try {
				ParserZaraPage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			ZaraProduct product = allnewprolist.get(key);
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

	private void checkBoydiscountproduct(Float discount, boolean hasLicense,
			String searchtext) {

		List<ZaraPage> urllist = ZaraPage.getBoysInstance();
		for (int i = 0; i < urllist.size(); i++) {
			ZaraPage ZaraPage = urllist.get(i);
			ParserZaraPage ParserZaraPage = new ParserZaraPage(ZaraPage,
					allnewprolist);
			try {
				ParserZaraPage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			ZaraProduct product = allnewprolist.get(key);
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

		List<ZaraPage> urllist = ZaraPage.getWomenSale();
		for (int i = 0; i < urllist.size(); i++) {
			ZaraPage ZaraPage = urllist.get(i);
			ParserZaraPage ParserZaraPage = new ParserZaraPage(ZaraPage,
					allnewprolist);
			try {
				ParserZaraPage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			ZaraProduct product = allnewprolist.get(key);
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

	private void checkGirldiscountproduct(Float discount, boolean hasLicense,
			String searchtext) {

		List<ZaraPage> urllist = ZaraPage.getGirlsInstance();
		for (int i = 0; i < urllist.size(); i++) {
			ZaraPage ZaraPage = urllist.get(i);
			ParserZaraPage ParserZaraPage = new ParserZaraPage(ZaraPage,
					allnewprolist);
			try {
				ParserZaraPage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			ZaraProduct product = allnewprolist.get(key);
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

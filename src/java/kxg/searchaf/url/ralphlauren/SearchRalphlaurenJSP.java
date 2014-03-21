package kxg.searchaf.url.ralphlauren;

import java.util.*;

import kxg.searchaf.url.Constant;
import kxg.searchaf.util.ProxyUtli;

public class SearchRalphlaurenJSP {

	public HashMap<Integer, RalphlaurenProduct> allnewprolist = new HashMap<Integer, RalphlaurenProduct>();

	public ArrayList<RalphlaurenProduct> matchprolist = new ArrayList<RalphlaurenProduct>();

	public static void main(String[] args) {
		ProxyUtli.setProxy(true);

		SearchRalphlaurenJSP af = new SearchRalphlaurenJSP();

		// while (true) {
		try {

			// System.out.println("start checking...");
			af.getwomendiscountproductforJsp("0.3", "", "");
			af.getmendiscountproductforJsp("0.3", "", "");
			af.getboydiscountproductforJsp("0.3", "", "");
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
		return RalphlaurenConstant.warningdiscount;
	}

	public ArrayList<RalphlaurenProduct> getcustomerdiscountproductforJsp(
			String searchUrl, String discountStr, String email,
			String searchtext) {
		boolean haslicense = RalphlaurenMailList.HasLicense(email);
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

	public ArrayList<RalphlaurenProduct> getmendiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = RalphlaurenMailList.HasLicense(email);
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

	public ArrayList<RalphlaurenProduct> getboydiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = RalphlaurenMailList.HasLicense(email);
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

	public ArrayList<RalphlaurenProduct> getwomendiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = RalphlaurenMailList.HasLicense(email);
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

	public ArrayList<RalphlaurenProduct> getgirldiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = RalphlaurenMailList.HasLicense(email);
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
			RalphlaurenPage RalphlaurenPage = new RalphlaurenPage("", "",
					searchUrl);
			ParserRalphlaurenPage ParserRalphlaurenPage = new ParserRalphlaurenPage(
					RalphlaurenPage, allnewprolist);
			ParserRalphlaurenPage.checkprice();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			RalphlaurenProduct product = allnewprolist.get(key);

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

		List<RalphlaurenPage> urllist = RalphlaurenPage.getMenSaleByBrand();
		for (int i = 0; i < urllist.size(); i++) {
			RalphlaurenPage RalphlaurenPage = urllist.get(i);
			ParserRalphlaurenPage ParserRalphlaurenPage = new ParserRalphlaurenPage(
					RalphlaurenPage, allnewprolist);
			try {
				ParserRalphlaurenPage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			RalphlaurenProduct product = allnewprolist.get(key);
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

		List<RalphlaurenPage> urllist = RalphlaurenPage.getBoysInstance();
		for (int i = 0; i < urllist.size(); i++) {
			RalphlaurenPage RalphlaurenPage = urllist.get(i);
			ParserRalphlaurenPage ParserRalphlaurenPage = new ParserRalphlaurenPage(
					RalphlaurenPage, allnewprolist);
			try {
				ParserRalphlaurenPage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			RalphlaurenProduct product = allnewprolist.get(key);
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

		List<RalphlaurenPage> urllist = RalphlaurenPage.getWomenSaleByBrand();
		for (int i = 0; i < urllist.size(); i++) {
			RalphlaurenPage RalphlaurenPage = urllist.get(i);
			ParserRalphlaurenPage ParserRalphlaurenPage = new ParserRalphlaurenPage(
					RalphlaurenPage, allnewprolist);
			try {
				ParserRalphlaurenPage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			RalphlaurenProduct product = allnewprolist.get(key);
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

		List<RalphlaurenPage> urllist = RalphlaurenPage.getGirlsInstance();
		for (int i = 0; i < urllist.size(); i++) {
			RalphlaurenPage RalphlaurenPage = urllist.get(i);
			ParserRalphlaurenPage ParserRalphlaurenPage = new ParserRalphlaurenPage(
					RalphlaurenPage, allnewprolist);
			try {
				ParserRalphlaurenPage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			RalphlaurenProduct product = allnewprolist.get(key);
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

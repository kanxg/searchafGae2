package kxg.searchaf.url.neiman_cat;

import java.util.*;

import kxg.searchaf.url.Constant;

public class SearchNeimanJSP {

	public HashMap<Long, NeimanProduct> allnewprolist = new HashMap<Long, NeimanProduct>();

	public ArrayList<NeimanProduct> matchprolist = new ArrayList<NeimanProduct>();

	public static void main(String[] args) {
		SearchNeimanJSP af = new SearchNeimanJSP();

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
		return NeimanConstant.warningdiscount;
	}

	public ArrayList<NeimanProduct> getcustomerdiscountproductforJsp(
			String searchUrl, String discountStr, String email,
			String searchtext) {
		boolean haslicense = NeimanMailList.HasLicense(email);
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

	public ArrayList<NeimanProduct> getmakeupdiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = NeimanMailList.HasLicense(email);
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

	public ArrayList<NeimanProduct> getwomendiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = NeimanMailList.HasLicense(email);
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
			NeimanPage NeimanPage = new NeimanPage(searchUrl);
			ParserNeimanPage ParserNeimanPage = new ParserNeimanPage(
					NeimanPage, allnewprolist);
			ParserNeimanPage.checkprice();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Long> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Long key = entryKeyIterator.next();
			NeimanProduct product = allnewprolist.get(key);

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
			List<NeimanPage> urllist = NeimanPage.getMakeupSale();
			for (int i = 0; i < urllist.size(); i++) {
				NeimanPage NeimanPage = urllist.get(i);
				ParserNeimanPage ParserNeimanPage = new ParserNeimanPage(
						NeimanPage, allnewprolist);
				ParserNeimanPage.checkprice();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Long> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Long key = entryKeyIterator.next();
			NeimanProduct product = allnewprolist.get(key);
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
			List<NeimanPage> urllist = NeimanPage.getWomenSale();
			for (int i = 0; i < urllist.size(); i++) {
				NeimanPage NeimanPage = urllist.get(i);
				ParserNeimanPage ParserNeimanPage = new ParserNeimanPage(
						NeimanPage, allnewprolist);
				ParserNeimanPage.checkprice();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Long> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Long key = entryKeyIterator.next();
			NeimanProduct product = allnewprolist.get(key);
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

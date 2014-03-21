package kxg.searchaf.url.sephora_search;

import java.util.*;

import kxg.searchaf.url.Constant;

public class SearchSephoraJSP {

	public HashMap<String, SephoraProduct> allnewprolist = new HashMap<String, SephoraProduct>();

	public ArrayList<SephoraProduct> matchprolist = new ArrayList<SephoraProduct>();

	public static void main(String[] args) {
		SearchSephoraJSP af = new SearchSephoraJSP();
		SearchSephora sp = new SearchSephora();
		// while (true) {
		try {

			// System.out.println("start checking...");
			ArrayList<SephoraProduct> matchprolist = af
					.getdiscountproductforJsp("0.4", "", "");
			sp.sendmail(matchprolist);
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
		return SephoraConstant.warningdiscount;
	}

	public ArrayList<SephoraProduct> getcustomerdiscountproductforJsp(
			String searchUrl, String discountStr, String email,
			String searchtext) {
		boolean haslicense = SephoraMailList.HasLicense(email);
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

	public ArrayList<SephoraProduct> getdiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = SephoraMailList.HasLicense(email);
		float discount = parsrDiscount(discountStr);
		// if (haslicense) {
		// discount = parsrDiscount(discountStr);
		// } else {
		// discount = AfConstant.warningdiscount;
		// }

		checkdiscountproduct(discount, haslicense, searchtext.trim());

		Collections.sort(matchprolist);
		return matchprolist;
	}

	private void checkcustomerdiscountproduct(String searchUrl, Float discount,
			boolean hasLicense, String searchtext) {

		try {
			SephoraPage SephoraPage = new SephoraPage(searchUrl);
			ParserSephoraPage ParserSephoraPage = new ParserSephoraPage(
					SephoraPage, allnewprolist);
			ParserSephoraPage.checkprice();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<String> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			SephoraProduct product = allnewprolist.get(key);

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

	private void checkdiscountproduct(Float discount, boolean hasLicense,
			String searchtext) {
		try {
			List<SephoraPage> urllist = SephoraPage.getSale();
			for (int i = 0; i < urllist.size(); i++) {
				SephoraPage SephoraPage = urllist.get(i);
				ParserSephoraPage ParserSephoraPage = new ParserSephoraPage(
						SephoraPage, allnewprolist);
				ParserSephoraPage.checkprice();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<String> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			SephoraProduct product = allnewprolist.get(key);
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

package kxg.searchaf.url.jcrew;

import java.util.*;

import kxg.searchaf.util.ProxyUtli;

public class SearchJcrewJSP {

	public HashMap<String, JcrewProduct> allnewprolist = new HashMap<String, JcrewProduct>();

	public ArrayList<JcrewProduct> matchprolist = new ArrayList<JcrewProduct>();

	public static void main(String[] args) {
		ProxyUtli.setProxy(true);

		SearchJcrewJSP af = new SearchJcrewJSP();

		// while (true) {
		try {

			// System.out.println("start checking...");
			// af.getmendiscountproductforJsp("0.3", "", "");
			// af.getwomendiscountproductforJsp("0.3", "", "");
			// af.getboydiscountproductforJsp("0.3", "", "");
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
		return JcrewConstant.warningdiscount;
	}

	public ArrayList<JcrewProduct> getcustomerdiscountproductforJsp(
			String searchUrl, String discountStr, String email,
			String searchtext) {
		boolean haslicense = JcrewMailList.HasLicense(email);
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

	public ArrayList<JcrewProduct> getmendiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = JcrewMailList.HasLicense(email);
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

	public ArrayList<JcrewProduct> getboydiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = JcrewMailList.HasLicense(email);
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

	public ArrayList<JcrewProduct> getwomendiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = JcrewMailList.HasLicense(email);
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

	public ArrayList<JcrewProduct> getgirldiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = JcrewMailList.HasLicense(email);
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
			JcrewPage JcrewPage = new JcrewPage(searchUrl);
			ParserJcrewPage ParserJcrewPage = new ParserJcrewPage(JcrewPage,
					allnewprolist);
			ParserJcrewPage.checkprice();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<String> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			JcrewProduct product = allnewprolist.get(key);

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

		List<JcrewPage> urllist = JcrewPage.getMenSale();
		for (int i = 0; i < urllist.size(); i++) {
			JcrewPage JcrewPage = urllist.get(i);
			ParserJcrewPage ParserJcrewPage = new ParserJcrewPage(JcrewPage,
					allnewprolist);
			try {
				ParserJcrewPage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<String> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			JcrewProduct product = allnewprolist.get(key);
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

		List<JcrewPage> urllist = JcrewPage.getBoysInstance();
		for (int i = 0; i < urllist.size(); i++) {
			JcrewPage JcrewPage = urllist.get(i);
			ParserJcrewPage ParserJcrewPage = new ParserJcrewPage(JcrewPage,
					allnewprolist);
			try {
				ParserJcrewPage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<String> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			JcrewProduct product = allnewprolist.get(key);
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

		List<JcrewPage> urllist = JcrewPage.getWomenSale();
		for (int i = 0; i < urllist.size(); i++) {
			JcrewPage JcrewPage = urllist.get(i);
			ParserJcrewPage ParserJcrewPage = new ParserJcrewPage(JcrewPage,
					allnewprolist);
			try {
				ParserJcrewPage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<String> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			JcrewProduct product = allnewprolist.get(key);
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

		List<JcrewPage> urllist = JcrewPage.getGirlsInstance();
		for (int i = 0; i < urllist.size(); i++) {
			JcrewPage JcrewPage = urllist.get(i);
			ParserJcrewPage ParserJcrewPage = new ParserJcrewPage(JcrewPage,
					allnewprolist);
			try {
				ParserJcrewPage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<String> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			JcrewProduct product = allnewprolist.get(key);
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

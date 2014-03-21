package kxg.searchaf.url.victoriassecret;

import java.util.*;

import kxg.searchaf.util.ProxyUtli;

public class SearchVictoriassecretJSP {

	public HashMap<String, VictoriassecretProduct> allnewprolist = new HashMap<String, VictoriassecretProduct>();

	public ArrayList<VictoriassecretProduct> matchprolist = new ArrayList<VictoriassecretProduct>();

	public static void main(String[] args) {
		ProxyUtli.setProxy(true);
		SearchVictoriassecretJSP af = new SearchVictoriassecretJSP();

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
		return VictoriassecretConstant.warningdiscount;
	}

	public ArrayList<VictoriassecretProduct> getcustomerdiscountproductforJsp(
			String searchUrl, String discountStr, String email,
			String searchtext) {
		boolean haslicense = VictoriassecretMailList.HasLicense(email);
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

	public ArrayList<VictoriassecretProduct> getwomendiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = VictoriassecretMailList.HasLicense(email);
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
			VictoriassecretPage VictoriassecretPage = new VictoriassecretPage(
					searchUrl);
			ParserVictoriassecretPage ParserVictoriassecretPage = new ParserVictoriassecretPage(
					VictoriassecretPage, allnewprolist);
			ParserVictoriassecretPage.checkprice();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<String> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			VictoriassecretProduct product = allnewprolist.get(key);

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

		List<VictoriassecretPage> urllist = VictoriassecretPage.getWomenSale();
		for (int i = 0; i < urllist.size(); i++) {
			VictoriassecretPage VictoriassecretPage = urllist.get(i);
			ParserVictoriassecretPage ParserVictoriassecretPage = new ParserVictoriassecretPage(
					VictoriassecretPage, allnewprolist);
			try {
				ParserVictoriassecretPage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<String> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			VictoriassecretProduct product = allnewprolist.get(key);
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

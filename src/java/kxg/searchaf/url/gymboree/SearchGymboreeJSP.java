package kxg.searchaf.url.gymboree;

import java.util.*;

public class SearchGymboreeJSP {

	public HashMap<Integer, GymboreeProduct> allnewprolist = new HashMap<Integer, GymboreeProduct>();

	public List<GymboreeProduct> matchprolist = new ArrayList<GymboreeProduct>();

	public static void main(String[] args) {
		SearchGymboreeJSP af = new SearchGymboreeJSP();

		// while (true) {
		try {
			// System.out.println("start checking...");
			af.getcustomerdiscountproductforJsp(
					"http://www.gymboree.com/shop/dept_category.jsp?productSizeSelected=0&pageClicked=0&FOLDER%3C%3Efolder_id=2534374304777283&bmUID=1363677847298",
					"0.3", "", "");
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
		return GymboreeConstant.warningdiscount;
	}

	public List<GymboreeProduct> getcustomerdiscountproductforJsp(
			String searchurl, String discountStr, String email,
			String searchtext) {

		if (!"".equals(searchurl)) {

			boolean haslicense = GymboreeMailList.HasLicense(email);
			float discount = parsrDiscount(discountStr);
			// if (haslicense) {
			// discount = parsrDiscount(discountStr);
			// } else {
			// discount = AfConstant.warningdiscount;
			// }

			checkcustomerdiscountproduct(searchurl, discount, haslicense,
					searchtext.trim());
		}

		Collections.sort(matchprolist);
		return matchprolist;
	}

	public List<GymboreeProduct> getgirldiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = GymboreeMailList.HasLicense(email);
		float discount = parsrDiscount(discountStr);
		// if (haslicense) {
		// discount = parsrDiscount(discountStr);
		// } else {
		// discount = AfConstant.warningdiscount;
		// }

		checkgirldiscountproduct(discount, haslicense, searchtext.trim());

		Collections.sort(matchprolist);
		return matchprolist;
	}

	public List<GymboreeProduct> getboydiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = GymboreeMailList.HasLicense(email);
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

	public List<GymboreeProduct> getbabygirldiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = GymboreeMailList.HasLicense(email);
		float discount = parsrDiscount(discountStr);
		// if (haslicense) {
		// discount = parsrDiscount(discountStr);
		// } else {
		// discount = AfConstant.warningdiscount;
		// }
		checkbabygirldiscountproduct(discount, haslicense, searchtext.trim());
		Collections.sort(matchprolist);
		return matchprolist;
	}

	public List<GymboreeProduct> getbabyboydiscountproductforJsp(
			String discountStr, String email, String searchtext) {
		boolean haslicense = GymboreeMailList.HasLicense(email);
		float discount = parsrDiscount(discountStr);
		// if (haslicense) {
		// discount = parsrDiscount(discountStr);
		// } else {
		// discount = AfConstant.warningdiscount;
		// }
		checkbabyboydiscountproduct(discount, haslicense, searchtext.trim());
		Collections.sort(matchprolist);
		return matchprolist;
	}

	private void checkcustomerdiscountproduct(String searchUrl, Float discount,
			boolean hasLicense, String searchtext) {

		try {
			GymboreePage GymboreePage = new GymboreePage(searchUrl);
			ParserGymboreePage ParserGymboreePage = new ParserGymboreePage(
					GymboreePage, allnewprolist);
			ParserGymboreePage.checkprice();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			GymboreeProduct product = allnewprolist.get(key);
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

	private void checkgirldiscountproduct(Float discount, boolean hasLicense,
			String searchtext) {

		List<GymboreePage> urllist = GymboreePage.getGirlSale();
		for (int i = 0; i < urllist.size(); i++) {
			GymboreePage GymboreePage = urllist.get(i);
			ParserGymboreePage ParserGymboreePage = new ParserGymboreePage(
					GymboreePage, allnewprolist);
			try {
				ParserGymboreePage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			GymboreeProduct product = allnewprolist.get(key);
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

		List<GymboreePage> urllist = GymboreePage.getBoySale();
		for (int i = 0; i < urllist.size(); i++) {
			GymboreePage GymboreePage = urllist.get(i);
			ParserGymboreePage ParserGymboreePage = new ParserGymboreePage(
					GymboreePage, allnewprolist);
			try {
				ParserGymboreePage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			GymboreeProduct product = allnewprolist.get(key);
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

	private void checkbabygirldiscountproduct(Float discount,
			boolean hasLicense, String searchtext) {

		List<GymboreePage> urllist = GymboreePage.getBabyGirlSale();
		for (int i = 0; i < urllist.size(); i++) {
			GymboreePage GymboreePage = urllist.get(i);
			ParserGymboreePage ParserGymboreePage = new ParserGymboreePage(
					GymboreePage, allnewprolist);
			try {
				ParserGymboreePage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			GymboreeProduct product = allnewprolist.get(key);
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

	private void checkbabyboydiscountproduct(Float discount,
			boolean hasLicense, String searchtext) {

		List<GymboreePage> urllist = GymboreePage.getBabyBoySale();
		for (int i = 0; i < urllist.size(); i++) {
			GymboreePage GymboreePage = urllist.get(i);
			ParserGymboreePage ParserGymboreePage = new ParserGymboreePage(
					GymboreePage, allnewprolist);
			try {
				ParserGymboreePage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			GymboreeProduct product = allnewprolist.get(key);
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

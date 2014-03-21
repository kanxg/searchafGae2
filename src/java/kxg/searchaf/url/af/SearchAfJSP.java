package kxg.searchaf.url.af;

import java.util.*;

import kxg.searchaf.url.SearchTaskFactory;
import kxg.searchaf.util.ProxyUtli;

public class SearchAfJSP {

	public HashMap<Integer, AfProduct> allnewprolist = new HashMap<Integer, AfProduct>();

	public List<AfProduct> matchprolist = new ArrayList<AfProduct>();

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);

		SearchAfJSP af = new SearchAfJSP();
		// af.getwomendiscountproductforJsp("0.9", "", "");
		// af.getmendiscountproductforJsp("0.9", "", "");
		for (int i = 0; i < af.matchprolist.size(); i++) {
			AfProduct product = af.matchprolist.get(i);
			System.out.println(product);
		}

	}

	private float parsrDiscount(String discountStr) {
		if (discountStr != null) {
			try {
				return Float.parseFloat(discountStr);
			} catch (NumberFormatException e) {

			}
		}
		return AfConstant.warningdiscount;
	}

	public List<AfProduct> getcustomerdiscountproductforJsp(String searchurl,
			String discountStr, String email, String searchtext) {
		boolean haslicense = AfMailList.HasLicense(email);
		float discount = parsrDiscount(discountStr);
		// if (haslicense) {
		// discount = parsrDiscount(discountStr);
		// } else {
		// discount = AfConstant.warningdiscount;
		// }

		getcustomerdiscountproductforJsp(searchurl, discount, haslicense,
				searchtext.trim());

		Collections.sort(matchprolist);
		return matchprolist;
	}

	public List<AfProduct> getmendiscountproductforJsp(String Category,
			String showdiscount, String searchtext, boolean showinventory,
			boolean fromcache) {

		float discount = parsrDiscount(showdiscount);
		if ("all".equalsIgnoreCase(Category)) {
			// check too many product ,so from cache.
			fromcache = true;
		}
		if (fromcache) {
			allnewprolist = SearchTaskFactory.getInstance().af.allmennewprolist;
		} else {
			checkMendiscountproduct(Category);
		}

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			AfProduct product = allnewprolist.get(key);
			if (product.realdiscount <= discount)
				if (searchtext == null
						|| searchtext.equals("")
						|| product.name.toLowerCase().indexOf(
								searchtext.toLowerCase()) >= 0) {
					matchprolist.add(product);
				}
		}
		if (showinventory && !fromcache && matchprolist.size() < 50) {
			for (AfProduct product : matchprolist) {
				checkInventory(product);
			}
		}
		System.out.println("found meet price products:" + matchprolist.size());

		Collections.sort(matchprolist);
		return matchprolist;
	}

	public List<AfProduct> getwomendiscountproductforJsp(String Category,
			String showdiscount, String searchtext, boolean showinventory,
			boolean fromcache) {

		float discount = parsrDiscount(showdiscount);
		if ("all".equalsIgnoreCase(Category)) {
			// check too many product ,so from cache.
			fromcache = true;
		}
		if (fromcache) {
			allnewprolist = SearchTaskFactory.getInstance().af.allwomennewprolist;
		} else {
			checkWoMendiscountproduct(Category);
		}

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			AfProduct product = allnewprolist.get(key);
			if (product.realdiscount <= discount)
				if (searchtext == null
						|| searchtext.equals("")
						|| product.name.toLowerCase().indexOf(
								searchtext.toLowerCase()) >= 0) {
					matchprolist.add(product);
				}
		}
		if (showinventory && !fromcache && matchprolist.size() < 50) {
			for (AfProduct product : matchprolist) {
				checkInventory(product);
			}
		}
		System.out.println("found meet price products:" + matchprolist.size());

		Collections.sort(matchprolist);
		return matchprolist;
	}

	private void checkMendiscountproduct(String Category) {
		boolean checkclearance = true;
		boolean checksale = true;
		boolean checksecret = true;
		if (Category.indexOf("sale") < 0) {
			checksale = false;
		}
		if (Category.indexOf("clearance") < 0) {
			checkclearance = false;
		}
		if (Category.indexOf("secret") < 0) {
			checksecret = false;
		}

		List<AfPage> urllist = null;
		if (checkclearance) {
			urllist = AfPage.getMenclearanceBySinglePageInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
			for (int i = 0; i < urllist.size(); i++) {
				AfPage afpage = urllist.get(i);
				ParserAfPage parserAfpage = new ParserAfPage(afpage,
						allnewprolist);
				try {
					parserAfpage.checkprice();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		if (checksale) {
			urllist = AfPage.getMenSaleBySinglePageInstance(); // getSaleInstance();getSaleBySinglePageInstance
			for (int i = 0; i < urllist.size(); i++) {
				AfPage afpage = urllist.get(i);
				ParserAfPage parserAfpage = new ParserAfPage(afpage,
						allnewprolist);
				try {
					parserAfpage.checkprice();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (checksecret) {
			urllist = AfPage.getMenSecretInstance();
			for (int i = 0; i < urllist.size(); i++) {
				AfPage afpage = urllist.get(i);
				ParserAfPage parserAfpage = new ParserAfPage(afpage,
						allnewprolist);
				try {
					parserAfpage.checkprice();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		System.out.println("found all of products:" + allnewprolist.size());

	}

	private void getcustomerdiscountproductforJsp(String searchurl,
			Float discount, boolean hasLicense, String searchtext) {

		AfPage afpage = new AfPage(searchurl);
		ParserAfPage parserAfpage = new ParserAfPage(afpage, allnewprolist);
		try {
			parserAfpage.checkprice();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			AfProduct product = allnewprolist.get(key);
			if (product.realdiscount <= discount)
				if (searchtext == null
						|| searchtext.equals("")
						|| product.name.toLowerCase().indexOf(
								searchtext.toLowerCase()) >= 0) {
					if (hasLicense)
						checkInventory(product);
					// check ignore product
					matchprolist.add(product);
				}
		}
		System.out.println("found meet price products:" + matchprolist.size());

	}

	private void checkWoMendiscountproduct(String Category) {

		boolean checkclearance = true;
		boolean checksale = true;
		boolean checksecret = true;
		if (Category.indexOf("sale") < 0) {
			checksale = false;
		}
		if (Category.indexOf("clearance") < 0) {
			checkclearance = false;
		}
		if (Category.indexOf("secret") < 0) {
			checksecret = false;
		}

		List<AfPage> urllist = null;
		if (checkclearance) {
			urllist = AfPage.getWomenclearanceBySinglePageInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
			for (int i = 0; i < urllist.size(); i++) {
				AfPage afpage = urllist.get(i);
				ParserAfPage parserAfpage = new ParserAfPage(afpage,
						allnewprolist);
				try {
					parserAfpage.checkprice();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		if (checksale) {
			urllist = AfPage.getWomenSecretInstance(); // getSaleInstance();getSaleBySinglePageInstance
			for (int i = 0; i < urllist.size(); i++) {
				AfPage afpage = urllist.get(i);
				ParserAfPage parserAfpage = new ParserAfPage(afpage,
						allnewprolist);
				try {
					parserAfpage.checkprice();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (checksecret) {
			urllist = AfPage.getMenSecretInstance();
			for (int i = 0; i < urllist.size(); i++) {
				AfPage afpage = urllist.get(i);
				ParserAfPage parserAfpage = new ParserAfPage(afpage,
						allnewprolist);
				try {
					parserAfpage.checkprice();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		System.out.println("found all of products:" + allnewprolist.size());

	}

	// TODO not finish
	private void checkInventory(AfProduct product) {
		ParserAfProduct ParserAfProduct = new ParserAfProduct(product);
		ParserAfProduct.checkColorItemInventory(true);

	}
}

package kxg.searchaf.url.af;

import java.util.*;
import kxg.searchaf.mail.Mail;
import kxg.searchaf.url.Constant;
import kxg.searchaf.util.ProxyUtli;

public class StoreInventoryDb {

	public HashMap<Integer, AfProduct> allmennewprolist = new HashMap<Integer, AfProduct>();

	public HashMap<Integer, AfProduct> allwomennewprolist = new HashMap<Integer, AfProduct>();

	public static void main(String[] args) throws Exception {
 		ProxyUtli.setProxy(true);

		StoreInventoryDb af = new StoreInventoryDb();
		try {
			af.checkMennewproduct();
			af.checkWoMennewproduct();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void checkMennewproduct() {
		try {
			List<AfPage> urllist = AfPage.getMenclearanceBySinglePageInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
			for (int i = 0; i < urllist.size(); i++) {
				AfPage afpage = urllist.get(i);
				ParserAfPage parserAfpage = new ParserAfPage(afpage,
						allmennewprolist);
				parserAfpage.checkprice();
			}

			urllist = AfPage.getMenSaleBySinglePageInstance(); // getSaleInstance();getSaleBySinglePageInstance
			for (int i = 0; i < urllist.size(); i++) {
				AfPage afpage = urllist.get(i);
				ParserAfPage parserAfpage = new ParserAfPage(afpage,
						allmennewprolist);
				parserAfpage.checkprice();
			}

			urllist = AfPage.getMenSecretInstance();
			for (int i = 0; i < urllist.size(); i++) {
				AfPage afpage = urllist.get(i);
				ParserAfPage parserAfpage = new ParserAfPage(afpage,
						allmennewprolist);
				parserAfpage.checkprice();
			}

			urllist = AfPage.getMenAllInstance();
			for (int i = 0; i < urllist.size(); i++) {
				AfPage afpage = urllist.get(i);
				ParserAfPage parserAfpage = new ParserAfPage(afpage,
						allmennewprolist);
				parserAfpage.checkprice();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error when get Af Men page." + e.getMessage());
		}

		System.out.println("found products:" + allmennewprolist.size());

		if (allmennewprolist.size() == 0) {
			// didn't get new product
			return;
		}

		// check new product
		Iterator<Integer> entryKeyIterator = allmennewprolist.keySet()
				.iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			AfProduct product = allmennewprolist.get(key);
			AfMysqlDao dao = new AfMysqlDao();
			if (!dao.existIndb(product.productid)) {
				checkInventory(product);
				dao.save(product);
				System.out.println("insert products:" + product.productid);
			}
		}

	}

	private void checkWoMennewproduct() {
		try {
			List<AfPage> urllist = AfPage
					.getWomenclearanceBySinglePageInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
			for (int i = 0; i < urllist.size(); i++) {
				AfPage afpage = urllist.get(i);
				ParserAfPage parserAfpage = new ParserAfPage(afpage,
						allwomennewprolist);
				parserAfpage.checkprice();
			}

			urllist = AfPage.getWomenSaleBySinglePageInstance(); // getSaleInstance();getSaleBySinglePageInstance
			for (int i = 0; i < urllist.size(); i++) {
				AfPage afpage = urllist.get(i);
				ParserAfPage parserAfpage = new ParserAfPage(afpage,
						allwomennewprolist);
				parserAfpage.checkprice();
			}

			urllist = AfPage.getWomenSecretInstance();
			for (int i = 0; i < urllist.size(); i++) {
				AfPage afpage = urllist.get(i);
				ParserAfPage parserAfpage = new ParserAfPage(afpage,
						allwomennewprolist);
				parserAfpage.checkprice();
			}

			urllist = AfPage.getWomenAllInstance();
			for (int i = 0; i < urllist.size(); i++) {
				AfPage afpage = urllist.get(i);
				ParserAfPage parserAfpage = new ParserAfPage(afpage,
						allmennewprolist);
				parserAfpage.checkprice();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out
					.println("Error when get Af WoMen page." + e.getMessage());
		}

		System.out.println("found products:" + allwomennewprolist.size());

		if (allwomennewprolist.size() == 0) {
			// didn't get new product
			return;
		}

		// check new product
		Iterator<Integer> entryKeyIterator = allwomennewprolist.keySet()
				.iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			AfProduct product = allwomennewprolist.get(key);
			AfMysqlDao dao = new AfMysqlDao();
			if (!dao.existIndb(product.productid)) {
				checkInventory(product);
				dao.save(product);
				System.out.println("insert products:" + product.productid);
			}
		}
	}

	public void checkInventory(AfProduct product) {
		ParserAfProduct ParserAfProduct = new ParserAfProduct(product);
		ParserAfProduct.checkColorItemInventory(false);
	}
}

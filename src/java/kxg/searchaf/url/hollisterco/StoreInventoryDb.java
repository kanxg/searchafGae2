package kxg.searchaf.url.hollisterco;

import java.util.*;
import kxg.searchaf.mail.Mail;
import kxg.searchaf.url.Constant;
import kxg.searchaf.util.ProxyUtli;

public class StoreInventoryDb {

	public HashMap<Integer, HollistercoProduct> allmennewprolist = new HashMap<Integer, HollistercoProduct>();

	public HashMap<Integer, HollistercoProduct> allwomennewprolist = new HashMap<Integer, HollistercoProduct>();

	public static void main(String[] args) throws Exception {
 		ProxyUtli.setProxy(true);

		StoreInventoryDb hollisterco = new StoreInventoryDb();
		try {
			hollisterco.checkMennewproduct();
			hollisterco.checkWoMennewproduct();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void checkMennewproduct() {
		try {
			List<HollistercoPage> urllist = HollistercoPage.getMenclearanceBySinglePageInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
			for (int i = 0; i < urllist.size(); i++) {
				HollistercoPage hollistercopage = urllist.get(i);
				ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(hollistercopage,
						allmennewprolist);
				parserHollistercopage.checkprice();
			}

			urllist = HollistercoPage.getMenSaleBySinglePageInstance(); // getSaleInstance();getSaleBySinglePageInstance
			for (int i = 0; i < urllist.size(); i++) {
				HollistercoPage hollistercopage = urllist.get(i);
				ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(hollistercopage,
						allmennewprolist);
				parserHollistercopage.checkprice();
			}

			urllist = HollistercoPage.getMenSecretInstance();
			for (int i = 0; i < urllist.size(); i++) {
				HollistercoPage hollistercopage = urllist.get(i);
				ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(hollistercopage,
						allmennewprolist);
				parserHollistercopage.checkprice();
			}

			urllist = HollistercoPage.getMenAllInstance();
			for (int i = 0; i < urllist.size(); i++) {
				HollistercoPage hollistercopage = urllist.get(i);
				ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(hollistercopage,
						allmennewprolist);
				parserHollistercopage.checkprice();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error when get Hollisterco Men page." + e.getMessage());
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
			HollistercoProduct product = allmennewprolist.get(key);
			HollistercoMysqlDao dao = new HollistercoMysqlDao();
			if (!dao.existIndb(product.productid)) {
				checkInventory(product);
				dao.save(product);
				System.out.println("insert products:" + product.productid);
			}
		}

	}

	private void checkWoMennewproduct() {
		try {
			List<HollistercoPage> urllist = HollistercoPage
					.getWomenclearanceBySinglePageInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
			for (int i = 0; i < urllist.size(); i++) {
				HollistercoPage hollistercopage = urllist.get(i);
				ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(hollistercopage,
						allwomennewprolist);
				parserHollistercopage.checkprice();
			}

			urllist = HollistercoPage.getWomenSaleBySinglePageInstance(); // getSaleInstance();getSaleBySinglePageInstance
			for (int i = 0; i < urllist.size(); i++) {
				HollistercoPage hollistercopage = urllist.get(i);
				ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(hollistercopage,
						allwomennewprolist);
				parserHollistercopage.checkprice();
			}

			urllist = HollistercoPage.getWomenSecretInstance();
			for (int i = 0; i < urllist.size(); i++) {
				HollistercoPage hollistercopage = urllist.get(i);
				ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(hollistercopage,
						allwomennewprolist);
				parserHollistercopage.checkprice();
			}

			urllist = HollistercoPage.getWomenAllInstance();
			for (int i = 0; i < urllist.size(); i++) {
				HollistercoPage hollistercopage = urllist.get(i);
				ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(hollistercopage,
						allmennewprolist);
				parserHollistercopage.checkprice();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out
					.println("Error when get Hollisterco WoMen page." + e.getMessage());
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
			HollistercoProduct product = allwomennewprolist.get(key);
			HollistercoMysqlDao dao = new HollistercoMysqlDao();
			if (!dao.existIndb(product.productid)) {
				checkInventory(product);
				dao.save(product);
				System.out.println("insert products:" + product.productid);
			}
		}
	}

	public void checkInventory(HollistercoProduct product) {
		ParserHollistercoProduct ParserHollistercoProduct = new ParserHollistercoProduct(product);
		ParserHollistercoProduct.checkColorItemInventory(false);
	}
}

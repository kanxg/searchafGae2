package kxg.searchaf.url.keepa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kxg.searchaf.mail.SystemMail;
import kxg.searchaf.util.ProxyUtli;

public class SearchKeepa {
	// public List<AmazonProduct> productMap = new ArrayList<AmazonProduct>();
	public Map<String, AmazonProduct> productMap = new HashMap<String, AmazonProduct>();

	public void getnewproduct(int i) {
		System.out.println("Checking Keepa Amazon BestSeller");
		if (i % (1) == 0) {
			// JuicycoutureMailList.sync();
		}
		getUsAllBestSellersProduct();
		getCnAllBestSellersProduct();
	}

	public void getUsAllBestSellersProduct() {

		boolean parsewithErr = false;
		List<AmazonBestSellers> urllist = AmazonBestSellers.getUsAll();
		for (int i = 0; i < urllist.size(); i++) {
			AmazonBestSellers page = urllist.get(i);
			ParserAmazonBestSellerPage parserAmazonBestSellerPage = new ParserAmazonBestSellerPage(
					page, productMap);
			try {
				parserAmazonBestSellerPage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				e.printStackTrace();
				SystemMail.sendSystemMail("Error when get Keepa  BestSeller."
						+ e.toString());
			}
		}

		Iterator<String> entryKeyIterator = productMap.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			AmazonProduct product = productMap.get(key);
			ParserDeepa parserDeepa = new ParserDeepa(product);
			 parserDeepa.checkAmazonPriceList();
			// System.out.println(product);
			if (product.isMinPrice(30)) {
				// System.out.println(product);
				System.out.println("product " + product.name
						+ "  min price in three month:ASIN:" + product.ASIN);
			}
		}

	}

	public void getCnAllBestSellersProduct() {

		boolean parsewithErr = false;
		List<AmazonBestSellers> urllist = AmazonBestSellers.getCnAll();
		for (int i = 0; i < urllist.size(); i++) {
			AmazonBestSellers page = urllist.get(i);
			ParserAmazonBestSellerPage parserAmazonBestSellerPage = new ParserAmazonBestSellerPage(
					page, productMap);
			try {
				parserAmazonBestSellerPage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				e.printStackTrace();
				SystemMail.sendSystemMail("Error when get Keepa  BestSeller."
						+ e.toString());
			}
		}

		Iterator<String> entryKeyIterator = productMap.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			AmazonProduct product = productMap.get(key);
			ParserDeepa parserDeepa = new ParserDeepa(product);
			 parserDeepa.checkAmazonPriceList();
			// System.out.println(product);
			if (product.isMinPrice(30)) {
				// System.out.println(product);
				System.out.println("product " + product.name
						+ "  min price in three month:ASIN:" + product.ASIN);
			}
		}

	}

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);
		SearchKeepa searchKeepa = new SearchKeepa();
		searchKeepa.getnewproduct(0);
	}
}

package kxg.searchaf.url.amazonWeibo;

import java.util.*;

import kxg.searchaf.url.Constant;

public class SearchAmazonWeibo {

	public HashMap<String, AmazonProduct> alloldprolist = new HashMap<String, AmazonProduct>();

	public HashMap<String, AmazonProduct> allnewprolist = new HashMap<String, AmazonProduct>();

	public static void main(String[] args) {
		SearchAmazonWeibo af = new SearchAmazonWeibo();

		// while (true) {
		try {
			// System.out.println("start checking...");
			af.getnewproduct();
			// System.out.println(new Date() + "done...");
			// Thread.sleep(1000 * 60 * Constant.sleeptime); // sleep 10
			// mines
		} catch (Exception e) {
			e.printStackTrace();
		}
		// }
	}

	public void getnewproduct() {
		System.out.println("Checking ...");
		checkMennewproduct();

	}

	private void send2Weibo(AmazonProduct product) {
		System.out.println("send weibo:" + product);
	}

	private void checkMennewproduct() {
		List<AmazonSearchPage> urllist = AmazonSearchPage.getInstance();
		for (int i = 0; i < urllist.size(); i++) {
			this.allnewprolist.clear();
			AmazonSearchPage afpage = urllist.get(i);
			ParserAmazonPage parserAfpage = new ParserAmazonPage(afpage,
					allnewprolist);
			parserAfpage.checkprice();

			System.out.println("found products:" + allnewprolist.size());
			// check new product
			Iterator<String> entryKeyIterator = allnewprolist.keySet()
					.iterator();
			while (entryKeyIterator.hasNext()) {
				String key = entryKeyIterator.next();
				AmazonProduct product = allnewprolist.get(key);
				AmazonProduct oldproduct = alloldprolist.get(key);
				if (oldproduct != null) {
					// have found this
					if (oldproduct.price > product.price) {
						// price dis
						send2Weibo(product);
					}
				}
				alloldprolist.put(key, product);
			}

		}

	}

}

package kxg.searchaf.url.keepa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kxg.searchaf.url.amazon.AmazonPage;
import kxg.searchaf.util.ProxyUtli;

public class SearchKeepaJSP {

	public AmazonProduct getProductPriceList(AmazonPage apage) {

		AmazonProduct product = new AmazonProduct();
		product.name = apage.prodname;
		product.ASIN = apage.ASIN;
		if (apage.url.toLowerCase().contains("amazon.cn")) {
			product.amazonZone = "amazon.cn";
		} else {
			product.amazonZone = "amazon.com";
		}
		ParserDeepa parserDeepa = new ParserDeepa(product);
		parserDeepa.checkAmazonPriceList();

		if (product.pricelist == null || product.pricelist.length == 0)
			return null;
		return product;

	}

	public List<AmazonProduct> getUsAllBestSellersProduct(String category,
			String howlong) {
		List<AmazonProduct> productlist = new ArrayList<AmazonProduct>();
		Map<String, AmazonProduct> productMap = new HashMap<String, AmazonProduct>();

		AmazonBestSellers bestseller=new AmazonBestSellers();
		List<AmazonBestSellers> list=bestseller.getCategory(category) ;
		for(AmazonBestSellers page:list){
 			ParserAmazonBestSellerPage parserAmazonBestSellerPage = new ParserAmazonBestSellerPage(
					page, productMap);
			try {
				parserAmazonBestSellerPage.checkprice();
			} catch (Exception e) {
				e.printStackTrace();

			}

			System.out.println("found amazon products:" + productMap.size());

			Iterator<String> entryKeyIterator = productMap.keySet().iterator();
			while (entryKeyIterator.hasNext()) {
				String key = entryKeyIterator.next();
				AmazonProduct product = productMap.get(key);
				ParserDeepa parserDeepa = new ParserDeepa(product);
				parserDeepa.checkAmazonPriceList();

				if (product.isMinPrice(Integer.valueOf(howlong))) {
					System.out.println("product " + product.name
							+ "  min price in three month:ASIN:" + product.ASIN);
					productlist.add(product);

				}
			}
		}

		
		return productlist;

	}

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);
		SearchKeepaJSP jsp = new SearchKeepaJSP();
		// List<AmazonProduct> productlist = jsp
		// .getUsAllBestSellersProduct(
		// "http://www.amazon.cn/s/ref=amb_link_30552132_1?ie=UTF8&bbn=665002051&page=1&rh=n%3A665002051%2Cp_89%3AApple%2Cn%3A664978051%2Cn%3A!2016117051%2Cn%3A2016116051&sort=-launch-date&pf_rd_m=A1AJ19PSB66TGU&pf_rd_s=left-5&pf_rd_r=09F2MSM2YJ437F9WX530&pf_rd_t=101&pf_rd_p=89976472&pf_rd_i=664978051",
		// "30");

		// for (AmazonProduct product : productlist) {
		// // System.out.println(product);
		// }
		AmazonPage apage = new AmazonPage();
		apage.ASIN = "B0010LR812";
		apage.url = "http://www.amazon.cn/dp/B0010LR812";
//		apage.ASIN = "B004ND7FOY";
//		apage.url = "http://www.amazon.com/dp/B004ND7FOY";

		
		AmazonProduct product = jsp.getProductPriceList(apage);
		for (String p : product.pricelist) {
			System.out.print(p + ",");
		}
		System.out.println("");
		System.out.println(product.getChartXLineDate());
		System.out.println(product.getChartYLineDate());
	}
}

package kxg.searchaf.url.zara;

import java.util.ArrayList;
import java.util.List;

public class ZaraPage {

	public String url;

	public ZaraPage(String url) {
		this.url = url;
	}

	public static List<ZaraPage> getMenSale() {
		ArrayList<ZaraPage> urllist = new ArrayList<ZaraPage>();
		// String type = "sale";
		// String category = "";

		// all will throw exception
		// String url =
		// "http://usa.zara.com/webapp/wcs/stores/servlet/SearchDisplay?searchType=1000&orderBy=3&showResultsPage=true&langId=-1&sType=SimpleSearch&pageSize=16&catalogId=10551&pageView=image&categoryId=18103&storeId=10151&beginIndex=0";
		// ZaraPage page = new ZaraPage(url);
		// urllist.add(page);

		// POLOS & T-SHIRTS
		String url = "http://usa.zara.com/webapp/wcs/stores/servlet/AjaxCatalogSearchResultView?searchType=1000&showResultsPage=true&langId=-1&beginIndex=0&sType=SimpleSearch&pageSize=200&catalogId=10551&pageView=image&categoryId=18103&viewAll=Y&storeId=10151&requesttype=ajax&searchResultsPageNum=0&viewAll=Y";
		ZaraPage page = new ZaraPage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<ZaraPage> getWomenSale() {
		ArrayList<ZaraPage> urllist = new ArrayList<ZaraPage>();
		// String type = "sale";
		// String category = "";
		// page 1
		String url = "http://www.zara.cn/cn/zh/%E5%84%BF%E7%AB%A5/%E5%A5%B3%E5%AD%A9/%E5%A4%96%E5%A5%97%E5%92%8C%E6%AF%9B%E8%A1%A3-c540331.html";
		ZaraPage page = new ZaraPage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<ZaraPage> getBoysInstance() {
		ArrayList<ZaraPage> urllist = new ArrayList<ZaraPage>();
		// String type = "sale";
		// String category = "";
		// page 1
		// big boy
		String url = "http://usa.zara.com/webapp/wcs/stores/servlet/AjaxCatalogSearchResultView?searchType=1000&showResultsPage=true&langId=-1&beginIndex=0&sType=SimpleSearch&pageSize=200&catalogId=10551&pageView=image&categoryId=19625&viewAll=Y&storeId=10151&requesttype=ajax&searchResultsPageNum=0&viewAll=Y";
		ZaraPage page = new ZaraPage(url);
		urllist.add(page);

		// little boy
		url = "http://usa.zara.com/webapp/wcs/stores/servlet/AjaxCatalogSearchResultView?searchType=1000&showResultsPage=true&langId=-1&beginIndex=0&sType=SimpleSearch&pageSize=200&catalogId=10551&pageView=image&categoryId=19626&viewAll=Y&storeId=10151&requesttype=ajax&searchResultsPageNum=0&viewAll=Y";
		page = new ZaraPage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<ZaraPage> getGirlsInstance() {
		ArrayList<ZaraPage> urllist = new ArrayList<ZaraPage>();
		// String type = "sale";
		// String category = "";
		// page 1
		// big gril
		String url = "http://usa.zara.com/webapp/wcs/stores/servlet/AjaxCatalogSearchResultView?searchType=1000&showResultsPage=true&langId=-1&beginIndex=0&sType=SimpleSearch&pageSize=200&catalogId=10551&pageView=image&categoryId=19627&viewAll=Y&storeId=10151&requesttype=ajax&searchResultsPageNum=0&viewAll=Y";
		ZaraPage page = new ZaraPage(url);
		urllist.add(page);

		// Little girl
		url = "http://usa.zara.com/webapp/wcs/stores/servlet/AjaxCatalogSearchResultView?searchType=1000&showResultsPage=true&langId=-1&beginIndex=0&sType=SimpleSearch&pageSize=200&catalogId=10551&pageView=image&categoryId=19628&viewAll=Y&storeId=10151&requesttype=ajax&searchResultsPageNum=0&viewAll=Y";
		page = new ZaraPage(url);
		urllist.add(page);

		return urllist;
	}

}

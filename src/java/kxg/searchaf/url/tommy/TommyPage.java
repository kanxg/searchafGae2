package kxg.searchaf.url.tommy;

import java.util.ArrayList;
import java.util.List;

public class TommyPage {

	public String url;

	public TommyPage(String url) {
		this.url = url;
	}

	public static List<TommyPage> getMenSale() {
		ArrayList<TommyPage> urllist = new ArrayList<TommyPage>();
		// String type = "sale";
		// String category = "";

		// all will throw exception
		// String url =
		// "http://usa.tommy.com/webapp/wcs/stores/servlet/SearchDisplay?searchType=1000&orderBy=3&showResultsPage=true&langId=-1&sType=SimpleSearch&pageSize=16&catalogId=10551&pageView=image&categoryId=18103&storeId=10151&beginIndex=0";
		// TommyPage page = new TommyPage(url);
		// urllist.add(page);

		// POLOS & T-SHIRTS
		String url = "http://usa.tommy.com/webapp/wcs/stores/servlet/AjaxCatalogSearchResultView?searchType=1000&showResultsPage=true&langId=-1&beginIndex=0&sType=SimpleSearch&pageSize=200&catalogId=10551&pageView=image&categoryId=18103&viewAll=Y&storeId=10151&requesttype=ajax&searchResultsPageNum=0&viewAll=Y";
		TommyPage page = new TommyPage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<TommyPage> getWomenSale() {
		ArrayList<TommyPage> urllist = new ArrayList<TommyPage>();
		// String type = "sale";
		// String category = "";
		// page 1
		String url = "http://usa.tommy.com/webapp/wcs/stores/servlet/AjaxCatalogSearchResultView?searchType=1000&showResultsPage=true&langId=-1&beginIndex=0&sType=SimpleSearch&pageSize=200&catalogId=10551&pageView=image&categoryId=19601&viewAll=Y&storeId=10151&requesttype=ajax&searchResultsPageNum=0&viewAll=Y";
		TommyPage page = new TommyPage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<TommyPage> getBoysInstance() {
		ArrayList<TommyPage> urllist = new ArrayList<TommyPage>();
		// String type = "sale";
		// String category = "";
		// page 1
		// big boy
		String url = "http://usa.tommy.com/webapp/wcs/stores/servlet/AjaxCatalogSearchResultView?searchType=1000&showResultsPage=true&langId=-1&beginIndex=0&sType=SimpleSearch&pageSize=200&catalogId=10551&pageView=image&categoryId=19625&viewAll=Y&storeId=10151&requesttype=ajax&searchResultsPageNum=0&viewAll=Y";
		TommyPage page = new TommyPage(url);
		urllist.add(page);

		// little boy
		url = "http://usa.tommy.com/webapp/wcs/stores/servlet/AjaxCatalogSearchResultView?searchType=1000&showResultsPage=true&langId=-1&beginIndex=0&sType=SimpleSearch&pageSize=200&catalogId=10551&pageView=image&categoryId=19626&viewAll=Y&storeId=10151&requesttype=ajax&searchResultsPageNum=0&viewAll=Y";
		page = new TommyPage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<TommyPage> getGirlsInstance() {
		ArrayList<TommyPage> urllist = new ArrayList<TommyPage>();
		// String type = "sale";
		// String category = "";
		// page 1
		// big gril
		String url = "http://usa.tommy.com/webapp/wcs/stores/servlet/AjaxCatalogSearchResultView?searchType=1000&showResultsPage=true&langId=-1&beginIndex=0&sType=SimpleSearch&pageSize=200&catalogId=10551&pageView=image&categoryId=19627&viewAll=Y&storeId=10151&requesttype=ajax&searchResultsPageNum=0&viewAll=Y";
		TommyPage page = new TommyPage(url);
		urllist.add(page);

		// Little girl
		url = "http://usa.tommy.com/webapp/wcs/stores/servlet/AjaxCatalogSearchResultView?searchType=1000&showResultsPage=true&langId=-1&beginIndex=0&sType=SimpleSearch&pageSize=200&catalogId=10551&pageView=image&categoryId=19628&viewAll=Y&storeId=10151&requesttype=ajax&searchResultsPageNum=0&viewAll=Y";
		page = new TommyPage(url);
		urllist.add(page);

		return urllist;
	}

}

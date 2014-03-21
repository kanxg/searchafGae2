package kxg.searchaf.url.sephora_search;

import java.util.ArrayList;
import java.util.List;

public class SephoraPage {

	public String url;

	public SephoraPage(String url) {
		this.url = url;
	}

	public static List<SephoraPage> getSale() {
		ArrayList<SephoraPage> urllist = new ArrayList<SephoraPage>();
		// String type = "sale";
		// String category = "";
		// SALE
		String url = "http://www.sephora.com/rest/products?keyword=Sale&int_cid=hp-ql-sale-22813&sale=true&pageSize=-1&defaultPageSize=60&sortBy=-1&defaultSortBy=-1&meta=true";
		SephoraPage page = new SephoraPage(url);
		urllist.add(page);

		// FRESH
		url = "http://www.sephora.com/rest/products?ref=900055&pageSize=-1&defaultPageSize=60&sortBy=-1&defaultSortBy=-1&brandId=4348&include_categories=true&include_refinements=true";
		page = new SephoraPage(url);
		urllist.add(page);

		// Clinique
		url = "http://www.sephora.com/rest/products?ref=900162&pageSize=-1&defaultPageSize=60&sortBy=-1&defaultSortBy=-1&brandId=1254&include_categories=true&include_refinements=true";
		page = new SephoraPage(url);
		urllist.add(page);

		// Philosophy
		url = "http://www.sephora.com/rest/products?ref=900005&pageSize=-1&defaultPageSize=60&sortBy=-1&defaultSortBy=-1&brandId=3866&include_categories=true&include_refinements=true";
		page = new SephoraPage(url);
		urllist.add(page);

		// Jurlique
		url = "http://www.sephora.com/rest/products?ref=900059&pageSize=-1&defaultPageSize=60&sortBy=-1&defaultSortBy=-1&brandId=5927&include_categories=true&include_refinements=true";
		page = new SephoraPage(url);
		urllist.add(page);

		// Estée Lauder
		// url = "";
		// page = new SephoraPage(url);
		// urllist.add(page);

		// Kiehls
		// url = "";
		// page = new SephoraPage(url);
		// urllist.add(page);

		// Lancôme
		url = "http://www.sephora.com/rest/products?ref=900207&pageSize=-1&defaultPageSize=60&sortBy=-1&defaultSortBy=-1&brandId=1741&include_categories=true&include_refinements=true";
		page = new SephoraPage(url);
		urllist.add(page);

		return urllist;
	}

}

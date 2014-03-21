package kxg.searchaf.url.freepeople;

import java.util.ArrayList;
import java.util.List;

public class FreepeoplePage {

	public String url;

	public FreepeoplePage(String url) {
		this.url = url;
	}

	public static List<FreepeoplePage> getWomenSale() {
		ArrayList<FreepeoplePage> urllist = new ArrayList<FreepeoplePage>();
		// String type = "sale";
		// String category = "";
		// page 1
		String url = "http://www.freepeople.com/index.cfm/fuseaction/products.browse/hasLeftNav/YES/startResult/1/navigationItemID/821810e9-ada4-4eb8-b739-651fe26e1a56/showAll/1/categoryID/1e191eff-7e31-412a-a60b-41f2586d3252/sizes/lt";
		FreepeoplePage page = new FreepeoplePage(url);
		urllist.add(page);

		return urllist;
	}

}

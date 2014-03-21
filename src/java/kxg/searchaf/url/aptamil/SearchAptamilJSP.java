package kxg.searchaf.url.aptamil;

import java.util.*;

public class SearchAptamilJSP {

	public HashMap<String, AptamilProduct> allnewprolist = new HashMap<String, AptamilProduct>();

	public ArrayList<AptamilProduct> matchprolist = new ArrayList<AptamilProduct>();

	public void chechInventory() {

		List<AptamilPage> aptamilPageList = AptamilPage.getInstance();
		for (int i = 0; i < aptamilPageList.size(); i++) {
			AptamilPage aptamilPage = aptamilPageList.get(i);
			ParserAptamilPage parse = null;
			if (aptamilPage.website.equals("Boots")) {
				parse = new ParserBootsPage(aptamilPage, allnewprolist);
			} else if (aptamilPage.website.equals("Ross")) {
				parse = new ParserRossPage(aptamilPage, allnewprolist);
			} else if (aptamilPage.website.equals("babyneo")) {
				parse = new ParserBabyneoPage(aptamilPage, allnewprolist);
			} else if (aptamilPage.website.equals("Windeln")) {
				parse = new ParserWindelnPage(aptamilPage, allnewprolist);
			} else if (aptamilPage.website.equals("Amazon De")) {
				parse = new ParserAmazonDePage(aptamilPage, allnewprolist);
			}
			try {
				parse.checkprice();

			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println("ERROR when get " + aptamilPage.url + ":"
						+ e.getMessage());
			}
		}

		System.out.println("found products:" + allnewprolist.size());

		matchprolist = new ArrayList<AptamilProduct>(allnewprolist.values());
		Collections.sort(matchprolist);
	}
}

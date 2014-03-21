package kxg.searchaf.url.coachfactory;

import java.util.ArrayList;
import java.util.List;

public class coachfactoryPage {

	public String url;

	public coachfactoryPage(String url) {
		this.url = url;
	}

	public static List<coachfactoryPage> getSale() {
		ArrayList<coachfactoryPage> urllist = new ArrayList<coachfactoryPage>();

		String url = "http://www.coachfactory.com/store/default/the-may-17-event/clearance.html?LOC=TN";
		coachfactoryPage page = new coachfactoryPage(url);
		urllist.add(page);

		return urllist;
	}
}

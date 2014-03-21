package kxg.searchaf.url.aptamil;

import java.util.ArrayList;
import java.util.List;

public class AptamilPage {

	public String url;
	public String productName;
	public String website;
	public boolean hasInventory = true;

	public AptamilPage(String url, String productName, String website) {
		this.url = url;
		this.productName = productName;
		this.website = website;
	}

	public static List<AptamilPage> getInstance() {
		ArrayList<AptamilPage> urllist = new ArrayList<AptamilPage>();

		String url = "";
		String productName = "";
		String website = "";
		AptamilPage page = null;

		url = "http://www.windeln.de/aptamil-milchnahrung.html";
		productName = "Aptamil";
		website = "Windeln";
		page = new AptamilPage(url, productName, website);
		urllist.add(page);

		url = "http://www.windeln.de/hipp-milchnahrung-combiotik.html";
		productName = "Hipp";
		website = "Windeln";
		page = new AptamilPage(url, productName, website);
		// urllist.add(page);

		url = "http://www.windeln.de/hipp-milchnahrung.html";
		productName = "Hipp";
		website = "Windeln";
		page = new AptamilPage(url, productName, website);
		// urllist.add(page);

		// amazon de
		url = "http://www.amazon.de/Aptamil-Pre-Anfangsmilch-4er-Pack/dp/B00D8V08UK/ref=dp_ob_title_grocery";
		productName = "Aptamil Pre";
		website = "Amazon De";
		page = new AptamilPage(url, productName, website);
		// urllist.add(page);

		url = "http://www.amazon.de/Aptamil-Anfangsmilch-4er-Pack-800/dp/B00D8V0972/ref=dp_ob_title_grocery";
		productName = "Aptamil 1";
		website = "Amazon De";
		page = new AptamilPage(url, productName, website);
		// urllist.add(page);

		url = "http://www.amazon.de/Aptamil-Folgemilch-4er-Pack-800/dp/B00D8V092W/ref=dp_ob_title_grocery";
		productName = "Aptamil 2";
		website = "Amazon De";
		page = new AptamilPage(url, productName, website);
		// urllist.add(page);

		url = "http://www.amazon.de/Aptamil-Folgemilch-4er-Pack-800/dp/B00D8V09FE/ref=dp_ob_title_grocery";
		productName = "Aptamil 3";
		website = "Amazon De";
		page = new AptamilPage(url, productName, website);
		// urllist.add(page);

		url = "";
		productName = "Aptamil 1+";
		website = "Amazon De";
		page = new AptamilPage(url, productName, website);
		// urllist.add(page);

		url = "";
		productName = "Aptamil 2+";
		website = "Amazon De";
		page = new AptamilPage(url, productName, website);
		// urllist.add(page);

		// url =
		// "http://www.boots.com/en/Aptamil-Growing-up-milk-powder-900g_847056";
		// productName = "Aptamil Pre";
		// website = "Boots";
		// page = new AptamilPage(url, productName, website);
		// urllist.add(page);
		//
		// // boots
		// url =
		// "http://www.boots.com/en/Aptamil-First-Milk-From-Birth-900g_17693";
		// productName = "Aptamil 1";
		// website = "Boots";
		// page = new AptamilPage(url, productName, website);
		// urllist.add(page);
		//
		// // boots
		// url =
		// "http://www.boots.com/en/Aptamil-Hungry-Infant-Milk-900g_17695";
		// productName = "Aptamil 2";
		// website = "Boots";
		// page = new AptamilPage(url, productName, website);
		// urllist.add(page);
		//
		// // boots
		// url = "http://www.boots.com/en/Aptamil-Follow-On-Milk-900g_21647";
		// productName = "Aptamil 3";
		// website = "Boots";
		// page = new AptamilPage(url, productName, website);
		// urllist.add(page);
		//
		// // boots
		// url =
		// "http://www.boots.com/en/Aptamil-Growing-up-milk-powder-900g_847056";
		// productName = "Aptamil 1+";
		// website = "Boots";
		// page = new AptamilPage(url, productName, website);
		// urllist.add(page);
		//
		// url =
		// "http://www.boots.com/en/Aptamil-Growing-Up-Milk-2-Years-800g_1218033";
		// productName = "Aptamil 2+";
		// website = "Boots";
		// page = new AptamilPage(url, productName, website);
		// urllist.add(page);
		//
		// // url = "";
		// // productName = "Aptamil Pre";
		// // website = "Ross";
		// // page = new AptamilPage(url, productName, website);
		// // urllist.add(page);
		//
		// url =
		// "http://www.rossmannversand.de/produkt/4853/milupa-aptamil-anfangsmilch-1.aspx?pos=9&componentid=1";
		// productName = "Aptamil 1";
		// website = "Ross";
		// page = new AptamilPage(url, productName, website);
		// urllist.add(page);
		//
		// url =
		// "http://www.rossmannversand.de/produkt/31104/milupa-aptamil-folgemilch-2.aspx?pos=13&componentid=1";
		// productName = "Aptamil 2";
		// website = "Ross";
		// page = new AptamilPage(url, productName, website);
		// urllist.add(page);
		//
		// url =
		// "http://www.rossmannversand.de/produkt/31105/milupa-aptamil-folgemilch-3.aspx?pos=15&componentid=1";
		// productName = "Aptamil 3";
		// website = "Ross";
		// page = new AptamilPage(url, productName, website);
		// urllist.add(page);
		//
		// url =
		// "http://www.rossmannversand.de/produkt/345415/milupa-aptamil-kindermilch-1und.aspx?pos=11&componentid=1";
		// productName = "Aptamil 1+";
		// website = "Ross";
		// page = new AptamilPage(url, productName, website);
		// urllist.add(page);
		//
		// // url = "";
		// // productName = "Aptamil 2+";
		// // website = "Ross";
		// // page = new AptamilPage(url, productName, website);
		// // urllist.add(page);
		//
		// url =
		// "http://www.babyneo.de/Babymilch--Aptamil-Nestle-Hipp--39/Aptamil--Milupa--Produkte/Milupa-Aptamil-Pre-800g.html";
		// productName = "Aptamil Pre";
		// website = "babyneo";
		// page = new AptamilPage(url, productName, website);
		// urllist.add(page);
		//
		// url =
		// "http://www.babyneo.de/Babymilch--Aptamil-Nestle-Hipp--39/Aptamil--Milupa--Produkte/Milupa-Aptamil-1-800g.html";
		// productName = "Aptamil 1";
		// website = "babyneo";
		// page = new AptamilPage(url, productName, website);
		// urllist.add(page);
		//
		// url =
		// "http://www.babyneo.de/Babymilch--Aptamil-Nestle-Hipp--39/Aptamil--Milupa--Produkte/Milupa-Aptamil-2-800g.html";
		// productName = "Aptamil 2";
		// website = "babyneo";
		// page = new AptamilPage(url, productName, website);
		// urllist.add(page);
		//
		// url =
		// "http://www.babyneo.de/Babymilch--Aptamil-Nestle-Hipp--39/Aptamil--Milupa--Produkte/Milupa-Aptamil-3-800g.html";
		// productName = "Aptamil 3";
		// website = "babyneo";
		// page = new AptamilPage(url, productName, website);
		// urllist.add(page);
		//
		// // url = "";
		// // productName = "Aptamil 1+";
		// // website = "Ross";
		// // page = new AptamilPage(url, productName, website);
		// // urllist.add(page);
		//
		// url =
		// "http://www.babyneo.de/Babymilch--Aptamil-Nestle-Hipp--39/Aptamil--Milupa--Produkte/Milupa-Aptamil-Kindermilch-plus-600g-Packung-924.html";
		// productName = "Aptamil 2+";
		// website = "babyneo";
		// page = new AptamilPage(url, productName, website);
		// urllist.add(page);

		return urllist;
	}

	public String toJSPString() {
		String returnkey = "网站：" + this.website;
		returnkey = returnkey + " 产品：" + this.productName;
		if (hasInventory) {
			returnkey = returnkey + "  有货！";
			returnkey = returnkey + " <a href=\"javascript:openUrl('"
					+ this.url + "')\">购买链接</a>";
		} else {
			returnkey = returnkey + "  无货！";
		}

		return returnkey;
	}
}

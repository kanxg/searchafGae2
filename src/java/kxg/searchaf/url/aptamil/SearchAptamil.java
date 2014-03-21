package kxg.searchaf.url.aptamil;

import java.util.*;

import kxg.searchaf.mail.Mail;
import kxg.searchaf.url.Constant;

public class SearchAptamil {

	public HashMap<String, AptamilProduct> alloldprolist = new HashMap<String, AptamilProduct>();

	public HashMap<String, AptamilProduct> allnewprolist = new HashMap<String, AptamilProduct>();

	public ArrayList<AptamilProduct> matchprolist = new ArrayList<AptamilProduct>();

	public static void main(String[] args) throws Exception {
		SearchAptamil search = new SearchAptamil();
		search.getnewproduct();

	}

	public void getnewproduct() {
		System.out.println("Checking Aptamil ");

		this.allnewprolist.clear();
		this.matchprolist.clear();

		chechInventory();

 		 sendmail(matchprolist);
	}

	public void getInventory4Weixin(){
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
				//e.printStackTrace();
				System.out.println("ERROR when get " + aptamilPage.url + ":"
						+ e.getMessage());
			}
		}

		System.out.println("found products:" + allnewprolist.size());
	}

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
				//e.printStackTrace();
				System.out.println("ERROR when get " + aptamilPage.url + ":"
						+ e.getMessage());
			}
		}

		System.out.println("found products:" + allnewprolist.size());

		if (allnewprolist.size() == 0) {
			// didn't get new product
			return;
		}

		if (alloldprolist.size() == 0) {
			// first time
			this.alloldprolist.putAll(allnewprolist);
			return;
		}

		// check new product
		Iterator<String> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			AptamilProduct product = allnewprolist.get(key);
			AptamilProduct oldproduct = alloldprolist.get(key);
			if (oldproduct != null && product != null) {
				if (!oldproduct.hasInventory && product.hasInventory) {
					matchprolist.add(product);
				}
			}
		}

		// put new to old list
		this.alloldprolist.clear();
		this.alloldprolist.putAll(allnewprolist);

	}

	private void sendmail(List<AptamilProduct> mailProduct) {
		if (matchprolist.size() == 0)
			return;
		Collections.sort(mailProduct);

		Mail m = new Mail();

		m.mail_subject = AptamilMailList.mail_subject;
		m.mail_to = AptamilMailList.mailto;

		for (int i = 0; i < mailProduct.size(); i++) {
			AptamilProduct product = mailProduct.get(i);
			m.tagcontext.append(product.toString());
			m.tagcontext.append("<br/>");

		}

		Date currentDate = new Date();
		List<AptamilMailList> afmailist = AptamilMailList.getinstance();
		List<String> cc_to = new ArrayList<String>();
		for (int i = 0; i < afmailist.size(); i++) {
			AptamilMailList amail = afmailist.get(i);

			if (amail.valideTime.after(currentDate)) {
				// if (amail.warningAptamil) {
				cc_to.add(amail.mailaddress);
				// }
			}
		}
		m.cc_to = cc_to;
		try {
			m.sendMail();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}

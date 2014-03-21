package kxg.searchaf.url.saks_cat;

import java.util.*;
import kxg.searchaf.mail.Mail;
import kxg.searchaf.url.Constant;

public class SearchSaks {

	public HashMap<String, SaksProduct> allmakeupoldprolist = new HashMap<String, SaksProduct>();

	public HashMap<String, SaksProduct> allmakeupnewprolist = new HashMap<String, SaksProduct>();

	public ArrayList<SaksProduct> makeupmatchprolist = new ArrayList<SaksProduct>();

	public HashMap<String, SaksProduct> allwomenoldprolist = new HashMap<String, SaksProduct>();

	public HashMap<String, SaksProduct> allwomennewprolist = new HashMap<String, SaksProduct>();

	public ArrayList<SaksProduct> womenmatchprolist = new ArrayList<SaksProduct>();

	public void getnewproduct() {
		// System.out.println("Checking Saks Mens");
		// this.allmennewprolist.clear();
		// this.menmatchprolist.clear();
		// checkMennewproduct();

		// 
		// sendmail(menmatchprolist, true);

		System.out.println("Checking Saks Womens");
		this.allwomennewprolist.clear();
		this.womenmatchprolist.clear();
		checkWoMennewproduct();

		sendmail(womenmatchprolist, false);
	}

	private void sendmail(ArrayList<SaksProduct> matchprolist, boolean isMen) {
		if (matchprolist.size() > 0) {
			Collections.sort(matchprolist);
			Mail m = new Mail();

			m.mail_subject = SaksMailList.mail_subject;
			m.mail_to = SaksMailList.mailto;
			for (int i = 0; i < matchprolist.size(); i++) {
				SaksProduct matchpro = matchprolist.get(i);
				// attach html file
				m.tagcontext
						.append(matchpro
								+ "<br/>"
								+ matchpro.tagcontext
								+ "<br/>************************************************<br/>");
			}
			m.tagcontext.append("</body></html>");

			Date currentDate = new Date();
			List<SaksMailList> afmailist = SaksMailList.getinstance();
			List<String> cc_to = new ArrayList<String>();
			for (int i = 0; i < afmailist.size(); i++) {
				SaksMailList amail = afmailist.get(i);
				if (amail.valideTime.after(currentDate)) {
					cc_to.add(amail.mailaddress);

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

	private void checkWoMennewproduct() {
		try {
			List<SaksPage> urllist = SaksPage.getWomenSale(); // getClearanceInstance();getclearanceBySinglePageInstance
			for (int i = 0; i < urllist.size(); i++) {
				SaksPage SaksPage = urllist.get(i);
				int currentSize = allwomennewprolist.size();
				ParserSaksPage ParserSaksPage = new ParserSaksPage(SaksPage,
						allwomennewprolist);
				ParserSaksPage.checkprice();
				if (currentSize == allwomennewprolist.size())
					break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error when get Saks page." + e.getMessage());
		}

		System.out.println("found products:" + allwomennewprolist.size());

		if (allwomennewprolist.size() == 0) {
			// didn't get new product
			return;
		}

		if (allwomenoldprolist.size() == 0) {
			// first time
			this.allwomenoldprolist.putAll(allwomennewprolist);
			return;
		}

		// check new product
		Iterator<String> entryKeyIterator = allwomennewprolist.keySet()
				.iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			SaksProduct product = allwomennewprolist.get(key);
			SaksProduct oldproduct = allwomenoldprolist.get(key);
			if (oldproduct != null) {
				// have found this
				if (oldproduct.price > product.price) {
					// price dis
					product.addReason = "价格变化，旧价格:" + oldproduct.price;
					womenmatchprolist.add(product);
				}
			} else {
				// new one
				product.addReason = "新产品";
				womenmatchprolist.add(product);
			}
		}

		this.allwomenoldprolist.clear();
		this.allwomenoldprolist.putAll(allwomennewprolist);

	}

	private void checkMakeupnewproduct() {
		try {
			List<SaksPage> urllist = SaksPage.getMakeUPSale(); // getClearanceInstance();getclearanceBySinglePageInstance
			for (int i = 0; i < urllist.size(); i++) {
				SaksPage SaksPage = urllist.get(i);
				ParserSaksPage ParserSaksPage = new ParserSaksPage(SaksPage,
						allmakeupnewprolist);
				ParserSaksPage.checkprice();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error when get Saks page." + e.getMessage());
		}

		System.out.println("found products:" + allmakeupnewprolist.size());

		if (allmakeupnewprolist.size() == 0) {
			// didn't get new product
			return;
		}

		if (allmakeupoldprolist.size() == 0) {
			// first time
			this.allmakeupoldprolist.putAll(allmakeupnewprolist);
			return;
		}

		// check new product
		Iterator<String> entryKeyIterator = allmakeupnewprolist.keySet()
				.iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			SaksProduct product = allmakeupnewprolist.get(key);
			SaksProduct oldproduct = allmakeupoldprolist.get(key);
			if (oldproduct != null) {
				// have found this
				if (oldproduct.price > product.price) {
					// price dis
					product.addReason = "价格变化，旧价格:" + oldproduct.price;
					makeupmatchprolist.add(product);
				}
			} else {
				// new one
				product.addReason = "新产品";
				makeupmatchprolist.add(product);
			}
		}

		this.allmakeupoldprolist.clear();
		this.allmakeupoldprolist.putAll(allmakeupnewprolist);

	}

}

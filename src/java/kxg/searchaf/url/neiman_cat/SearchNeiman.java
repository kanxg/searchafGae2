package kxg.searchaf.url.neiman_cat;

import java.util.*;
import kxg.searchaf.mail.Mail;
import kxg.searchaf.url.Constant;

public class SearchNeiman {

	public HashMap<Long, NeimanProduct> allmakeupoldprolist = new HashMap<Long, NeimanProduct>();

	public HashMap<Long, NeimanProduct> allmakeupnewprolist = new HashMap<Long, NeimanProduct>();

	public ArrayList<NeimanProduct> makeupmatchprolist = new ArrayList<NeimanProduct>();

	public HashMap<Long, NeimanProduct> allwomenoldprolist = new HashMap<Long, NeimanProduct>();

	public HashMap<Long, NeimanProduct> allwomennewprolist = new HashMap<Long, NeimanProduct>();

	public ArrayList<NeimanProduct> womakeupmatchprolist = new ArrayList<NeimanProduct>();

	public void getnewproduct() {
		System.out.println("Checking Neiman Mens");
		this.allmakeupnewprolist.clear();
		this.makeupmatchprolist.clear();
		checkmakeupnewproduct();

		sendmail(makeupmatchprolist, true);

		System.out.println("Checking Neiman Womens");
		this.allwomennewprolist.clear();
		this.womakeupmatchprolist.clear();
		checkWoMennewproduct();
		sendmail(womakeupmatchprolist, false);
	}

	private void sendmail(ArrayList<NeimanProduct> matchprolist, boolean isMen) {
		if (matchprolist.size() > 0) {
			Collections.sort(matchprolist);
			Mail m = new Mail();

			m.mail_subject = NeimanMailList.mail_subject;
			m.mail_to = NeimanMailList.mailto;

			for (int i = 0; i < matchprolist.size(); i++) {
				NeimanProduct matchpro = matchprolist.get(i);
				// attach html file
				m.tagcontext
						.append(matchpro
								+ "<br/>"
								+ matchpro.tagcontext
								+ "<br/>************************************************<br/>");
			}
			m.tagcontext.append("</body></html>");

			Date currentDate = new Date();
			List<NeimanMailList> afmailist = NeimanMailList.getinstance();
			List<String> cc_to = new ArrayList<String>();
			for (int i = 0; i < afmailist.size(); i++) {
				NeimanMailList amail = afmailist.get(i);
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

	private void checkmakeupnewproduct() {
		try {
			List<NeimanPage> urllist = NeimanPage.getMakeupSale(); // getClearanceInstance();getclearanceBySinglePageInstance
			for (int i = 0; i < urllist.size(); i++) {
				NeimanPage NeimanPage = urllist.get(i);
				ParserNeimanPage ParserNeimanPage = new ParserNeimanPage(
						NeimanPage, allmakeupnewprolist);
				ParserNeimanPage.checkprice();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error when get Neiman page." + e.getMessage());
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
		Iterator<Long> entryKeyIterator = allmakeupnewprolist.keySet()
				.iterator();
		while (entryKeyIterator.hasNext()) {
			Long key = entryKeyIterator.next();
			NeimanProduct product = allmakeupnewprolist.get(key);
			NeimanProduct oldproduct = allmakeupoldprolist.get(key);
			if (oldproduct != null) {
				// have found this
				if (oldproduct.price > product.price) {
					// price dis
					product.addReason = "价格变化，旧价格：" + oldproduct.price;
					makeupmatchprolist.add(product);
				}
			} else {
				// new one
				product.addReason = "新产品";
				makeupmatchprolist.add(product);
			}
		}

		// put new to old list
		this.allmakeupoldprolist.clear();
		this.allmakeupoldprolist.putAll(allmakeupnewprolist);

	}

	private void checkWoMennewproduct() {
		try {
			List<NeimanPage> urllist = NeimanPage.getWomenSale(); // getClearanceInstance();getclearanceBySinglePageInstance
			for (int i = 0; i < urllist.size(); i++) {
				NeimanPage NeimanPage = urllist.get(i);
				ParserNeimanPage ParserNeimanPage = new ParserNeimanPage(
						NeimanPage, allwomennewprolist);
				ParserNeimanPage.checkprice();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error when get Neiman page." + e.getMessage());
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
		Iterator<Long> entryKeyIterator = allwomennewprolist.keySet()
				.iterator();
		while (entryKeyIterator.hasNext()) {
			Long key = entryKeyIterator.next();
			NeimanProduct product = allwomennewprolist.get(key);
			NeimanProduct oldproduct = allwomenoldprolist.get(key);
			if (oldproduct != null) {
				// have found this
				if (oldproduct.price > product.price) {
					// price dis
					product.addReason = "价格变化，旧价格:" + oldproduct.price;
					womakeupmatchprolist.add(product);
				}
			} else {
				// new one
				product.addReason = "新产品";
				womakeupmatchprolist.add(product);
			}
		}

		this.allwomenoldprolist.clear();
		this.allwomenoldprolist.putAll(allwomennewprolist);

	}

}

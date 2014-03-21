package kxg.searchaf.url.hollisterco;

import java.util.*;
import kxg.searchaf.mail.Mail;
import kxg.searchaf.mail.SystemMail;
import kxg.searchaf.url.Constant;
import kxg.searchaf.util.ProxyUtli;

public class SearchHollisterco {
	public HashMap<Integer, HollistercoProduct> allmenoldprolist = new HashMap<Integer, HollistercoProduct>();

	public HashMap<Integer, HollistercoProduct> allmennewprolist = new HashMap<Integer, HollistercoProduct>();

	public ArrayList<HollistercoProduct> menmatchprolist = new ArrayList<HollistercoProduct>();

	public HashMap<Integer, HollistercoProduct> allwomenoldprolist = new HashMap<Integer, HollistercoProduct>();

	public HashMap<Integer, HollistercoProduct> allwomennewprolist = new HashMap<Integer, HollistercoProduct>();

	public ArrayList<HollistercoProduct> womenmatchprolist = new ArrayList<HollistercoProduct>();

	public HollistercoCode hollistercocode;

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);

		SearchHollisterco hollisterco = new SearchHollisterco();
		try {
			// hollisterco.gethollistercocode();
			// Thread.sleep(1000);
			// hollisterco.gethollistercocode();
			hollisterco.getnewproduct(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getnewproduct(int i) {

		// if (i % (6 * 24) == 0) {
		// this.allmenoldprolist.clear();
		// this.allwomenoldprolist.clear();
		// }
		if (i % (1) == 0) {
			HollistercoMailList.sync();
		}
		gethollistercocode();
		getnewMenproduct();
		getnewWomenproduct();

	}

	public void gethollistercocode() {
		System.out.println("Checking Hollister Code");
		ParserHollistercoCode parserHollistercoCode = new ParserHollistercoCode();
		try {
			HollistercoCode newhollistercocode = parserHollistercoCode
					.checkcode();
			if (newhollistercocode != null) {
				if (hollistercocode != null
						&& !newhollistercocode.equals(hollistercocode)) {
					if (!newhollistercocode.name.startsWith("Last Day"))
						sendmail(newhollistercocode);

					hollistercocode = newhollistercocode;
				}
			}
		} catch (Exception e) {
			SystemMail.sendSystemMail("error when get Hollister code,err:"
					+ e.toString());
		}

	}

	public void getnewMenproduct() {
		System.out.println("Checking Hollister Mens");
		this.allmennewprolist.clear();
		this.menmatchprolist.clear();

		checkMennewproduct();

		sendmail(menmatchprolist, true);
	}

	public void getnewWomenproduct() {
		System.out.println("Checking Hollister Womens");
		this.allwomennewprolist.clear();
		this.womenmatchprolist.clear();

		checkWoMennewproduct();

		sendmail(womenmatchprolist, false);

	}

	private void sendmail(HollistercoCode hollistercoCode) {
		Date currentDate = new Date();
		List<HollistercoMailList> hollistercomailist = HollistercoMailList
				.getinstance();
		for (int i = 0; i < hollistercomailist.size(); i++) {
			HollistercoMailList amail = hollistercomailist.get(i);
			if (amail.valideTime.after(currentDate) && amail.warningCode) {
				send2one(amail, hollistercoCode);
			}
		}
	}

	private void send2one(HollistercoMailList amail,
			HollistercoCode hollistercoCode) {
		Mail m = new Mail();
		m.mail_subject = HollistercoMailList.mail_subject + " 新折扣码";
		m.mail_to = amail.mailaddress;
		m.tagcontext.append(hollistercoCode.tagcontext);

		m.sendMail(amail.getSendingMailRetryTimes());

	}

	private void sendmail(ArrayList<HollistercoProduct> matchprolist,
			boolean isMen) {
		if (matchprolist.size() == 0)
			return;

		Collections.sort(matchprolist);

		Date currentDate = new Date();
		List<HollistercoMailList> hollistercomailist = HollistercoMailList
				.getinstance();
		for (int i = 0; i < hollistercomailist.size(); i++) {
			HollistercoMailList amail = hollistercomailist.get(i);
			if (amail.valideTime.after(currentDate)) {
				if (isMen && amail.warningMen) {
					HollistercoUtil HollistercoUtil = new HollistercoUtil();
					ArrayList<HollistercoProduct> usermatch = HollistercoUtil
							.checkuser(matchprolist, amail, isMen);
					send2one(usermatch, amail, isMen);
				}
				if (!isMen && amail.warningWomen) {
					HollistercoUtil HollistercoUtil = new HollistercoUtil();
					ArrayList<HollistercoProduct> usermatch = HollistercoUtil
							.checkuser(matchprolist, amail, isMen);
					send2one(usermatch, amail, isMen);
				}
			}
		}

	}

	private void send2one(ArrayList<HollistercoProduct> matchprolist,
			HollistercoMailList amail, boolean isMen) {
		if (matchprolist.size() == 0)
			return;

		Mail m = new Mail();
		if (isMen) {
			m.mail_subject = HollistercoMailList.mail_subject + " 男士";
		} else {
			m.mail_subject = HollistercoMailList.mail_subject + " 女士";
		}
		m.mail_to = amail.mailaddress;
		m.tagcontext
				.append("<html><head><link rel=\"stylesheet\" href=\"http://www.hollisterco.com/hol/56603/css/global/site.css\"/></head><body>");

		for (int i = 0; i < matchprolist.size(); i++) {
			HollistercoProduct matchpro = matchprolist.get(i);
			// attach html file
			m.tagcontext
					.append(matchpro
							+ "<br/>"
							+ matchpro.tagcontext
							+ "<br/>************************************************<br/>");
		}
		m.tagcontext.append("</body></html>");

		m.sendMail(amail.getSendingMailRetryTimes());
	}

	private void checkMennewproduct() {
		boolean parsewithErr = false;

		List<HollistercoPage> urllist = HollistercoPage
				.getMenclearanceBySinglePageInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			HollistercoPage hollistercopage = urllist.get(i);
			ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(
					hollistercopage, allmennewprolist);
			try {
				parserHollistercopage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				parsewithErr = true;
				System.out.println("Error when get Hollisterco Men page."
						+ e.getMessage());
			}
		}

		urllist = HollistercoPage.getMenSaleBySinglePageInstance(); // getSaleInstance();getSaleBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			HollistercoPage hollistercopage = urllist.get(i);
			ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(
					hollistercopage, allmennewprolist);
			try {
				parserHollistercopage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				parsewithErr = true;
				SystemMail
						.sendSystemMail("Error when get Hollister Men Sale page."
								+ e.toString());
			}
		}

		urllist = HollistercoPage.getMenSecretInstance();
		for (int i = 0; i < urllist.size(); i++) {
			HollistercoPage hollistercopage = urllist.get(i);
			ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(
					hollistercopage, allmennewprolist);
			try {
				parserHollistercopage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				parsewithErr = true;
				SystemMail
						.sendSystemMail("Error when get Hollister Men Secret page."
								+ e.toString());
			}
		}

		urllist = HollistercoPage.getMenAllInstance();
		for (int i = 0; i < urllist.size(); i++) {
			HollistercoPage hollistercopage = urllist.get(i);
			ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(
					hollistercopage, allmennewprolist);
			try {
				parserHollistercopage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				parsewithErr = true;
				SystemMail
						.sendSystemMail("Error when get Hollisterco Men All page."
								+ e.toString());
			}
		}

		System.out.println("found products:" + allmennewprolist.size());

		if (allmennewprolist.size() == 0) {
			// didn't get new product
			return;
		}

		if (allmenoldprolist.size() == 0) {
			// first time
			if (!parsewithErr)
				this.allmenoldprolist.putAll(allmennewprolist);
			return;
		}

		// check new product
		Iterator<Integer> entryKeyIterator = allmennewprolist.keySet()
				.iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			HollistercoProduct product = allmennewprolist.get(key);
			HollistercoProduct oldproduct = allmenoldprolist.get(key);
			if (oldproduct != null) {
				// have found this
				if (oldproduct.price > product.price) {
					// if (product.realdiscount <=
					// HollistercoConstant.newproductcheck_discount) {
					// price dis
					product.addReason = "产品降价，旧价格：" + oldproduct.price;
					if (checkInventory(product))
						menmatchprolist.add(product);
					// }
				}
			} else {
				// if (product.realdiscount <=
				// HollistercoConstant.newproductcheck_discount) {
				// new one
				product.addReason = "新产品";
				if (checkInventory(product))
					menmatchprolist.add(product);
				// }
			}
		}

		// put new to old list
		if (!parsewithErr)
			this.allmenoldprolist.clear();
		this.allmenoldprolist.putAll(allmennewprolist);

	}

	private void checkWoMennewproduct() {
		boolean parsewithErr = false;

		List<HollistercoPage> urllist = HollistercoPage
				.getWomenclearanceBySinglePageInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			HollistercoPage hollistercopage = urllist.get(i);
			ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(
					hollistercopage, allwomennewprolist);
			try {
				parserHollistercopage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				parsewithErr = true;
				SystemMail
						.sendSystemMail("Error when get Hollister WoMen C page."
								+ e.toString());
			}
		}

		urllist = HollistercoPage.getWomenSaleBySinglePageInstance(); // getSaleInstance();getSaleBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			HollistercoPage hollistercopage = urllist.get(i);
			ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(
					hollistercopage, allwomennewprolist);
			try {
				parserHollistercopage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				parsewithErr = true;
				SystemMail
						.sendSystemMail("Error when get Hollister Women S page."
								+ e.toString());
			}
		}

		urllist = HollistercoPage.getWomenSecretInstance();
		for (int i = 0; i < urllist.size(); i++) {
			HollistercoPage hollistercopage = urllist.get(i);
			ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(
					hollistercopage, allwomennewprolist);
			try {
				parserHollistercopage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				parsewithErr = true;
				SystemMail
						.sendSystemMail("Error when get Hollister woMen Secret page."
								+ e.toString());
			}
		}

		urllist = HollistercoPage.getWomenAllInstance();
		for (int i = 0; i < urllist.size(); i++) {
			HollistercoPage hollistercopage = urllist.get(i);
			ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(
					hollistercopage, allmennewprolist);
			try {
				parserHollistercopage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				parsewithErr = true;
				SystemMail
						.sendSystemMail("Error when get Hollister woMen Secret page."
								+ e.toString());
			}
		}

		System.out.println("found products:" + allwomennewprolist.size());

		if (allwomennewprolist.size() == 0) {
			// didn't get new product
			return;
		}

		if (allwomenoldprolist.size() == 0) {
			// first time
			if (!parsewithErr)
				this.allwomenoldprolist.putAll(allwomennewprolist);
			return;
		}

		// check new product
		Iterator<Integer> entryKeyIterator = allwomennewprolist.keySet()
				.iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			HollistercoProduct product = allwomennewprolist.get(key);
			HollistercoProduct oldproduct = allwomenoldprolist.get(key);
			if (oldproduct != null) {
				// have found this
				if (oldproduct.price > product.price) {
					// if (product.realdiscount <=
					// HollistercoConstant.newproductcheck_discount) {
					// price dis
					product.addReason = "产品降价，旧价格:" + oldproduct.price;
					if (checkInventory(product))
						womenmatchprolist.add(product);
					// }
				}
			} else {
				// if (product.realdiscount <=
				// HollistercoConstant.newproductcheck_discount) {
				// new one
				product.addReason = "新产品";
				if (checkInventory(product))
					womenmatchprolist.add(product);
				// }
			}
		}

		if (!parsewithErr)
			this.allwomenoldprolist.clear();
		this.allwomenoldprolist.putAll(allwomennewprolist);
	}

	public boolean checkInventory(HollistercoProduct product) {
		ParserHollistercoProduct ParserHollistercoProduct = new ParserHollistercoProduct(
				product);
		ParserHollistercoProduct.checkColorItemInventory(true);
		// return product.inventoryList == null ? false : true;
		return true;
	}
}

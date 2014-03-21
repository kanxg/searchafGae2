package kxg.searchaf.url.af;

import java.util.*;
import kxg.searchaf.mail.Mail;
import kxg.searchaf.mail.SystemMail;
import kxg.searchaf.url.Constant;
import kxg.searchaf.util.ProxyUtli;

public class SearchAf {
	public HashMap<Integer, AfProduct> allmenoldprolist = new HashMap<Integer, AfProduct>();

	public HashMap<Integer, AfProduct> allmennewprolist = new HashMap<Integer, AfProduct>();

	public ArrayList<AfProduct> menmatchprolist = new ArrayList<AfProduct>();

	public HashMap<Integer, AfProduct> allwomenoldprolist = new HashMap<Integer, AfProduct>();

	public HashMap<Integer, AfProduct> allwomennewprolist = new HashMap<Integer, AfProduct>();

	public ArrayList<AfProduct> womenmatchprolist = new ArrayList<AfProduct>();

	public AfCode afcode;

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);
		SearchAf af = new SearchAf();
		try {
			// af.getafcode();
			// Thread.sleep(1000);
			// af.getafcode();
			// af.getnewproduct(1);
			af.getnewMenproduct();
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
			AfMailList.sync();
		}
		getafcode();
		getnewMenproduct();
		getnewWomenproduct();

	}

	public void getafcode() {
		System.out.println("Checking AF Code");
		ParserAfCode parserAfCode = new ParserAfCode();
		try {
			AfCode newafcode = parserAfCode.checkcode();
			if (newafcode != null) {
				if (afcode != null && !newafcode.equals(afcode)) {
					if (!newafcode.name.startsWith("Last Day"))
						sendmail(newafcode);

					afcode = newafcode;
				}
			}
		} catch (Exception e) {
			SystemMail.sendSystemMail("error when get afcode,err:"
					+ e.toString());
		}

	}

	public void getnewMenproduct() {
		System.out.println("Checking AF Mens");
		this.allmennewprolist.clear();
		this.menmatchprolist.clear();

		checkMennewproduct();

		sendmail(menmatchprolist, true);
	}

	public void getnewWomenproduct() {
		System.out.println("Checking AF Womens");
		this.allwomennewprolist.clear();
		this.womenmatchprolist.clear();

		checkWoMennewproduct();

		sendmail(womenmatchprolist, false);

	}

	private void sendmail(AfCode afCode) {
		Date currentDate = new Date();
		List<AfMailList> afmailist = AfMailList.getinstance();
		for (int i = 0; i < afmailist.size(); i++) {
			AfMailList amail = afmailist.get(i);
			if (amail.valideTime.after(currentDate) && amail.warningCode) {
				send2one(amail, afCode);
			}
		}
	}

	private void send2one(AfMailList amail, AfCode afCode) {
		Mail m = new Mail();
		m.mail_subject = AfMailList.mail_subject + " 新折扣码";
		m.mail_to = amail.mailaddress;
		m.tagcontext.append(afCode.tagcontext);

		m.sendMail(amail.getSendingMailRetryTimes());

	}

	private void sendmail(ArrayList<AfProduct> matchprolist, boolean isMen) {
		if (matchprolist.size() == 0)
			return;

		Collections.sort(matchprolist);

		Date currentDate = new Date();
		List<AfMailList> afmailist = AfMailList.getinstance();
		for (int i = 0; i < afmailist.size(); i++) {
			AfMailList amail = afmailist.get(i);
			if (amail.valideTime.after(currentDate)) {
				if (isMen && amail.warningMen) {
					AfUtil AfUtil = new AfUtil();
					ArrayList<AfProduct> usermatch = AfUtil.checkuser(
							matchprolist, amail, isMen);
					send2one(usermatch, amail, isMen);
				}
				if (!isMen && amail.warningWomen) {
					AfUtil AfUtil = new AfUtil();
					ArrayList<AfProduct> usermatch = AfUtil.checkuser(
							matchprolist, amail, isMen);
					send2one(usermatch, amail, isMen);
				}
			}
		}

	}

	private void send2one(ArrayList<AfProduct> matchprolist, AfMailList amail,
			boolean isMen) {
		if (matchprolist.size() == 0)
			return;

		Mail m = new Mail();
		if (isMen) {
			m.mail_subject = AfMailList.mail_subject + " 男士";
		} else {
			m.mail_subject = AfMailList.mail_subject + " 女士";
		}
		m.mail_to = amail.mailaddress;
		m.tagcontext
				.append("<html><head><link rel=\"stylesheet\" href=\"http://www.abercrombie.com/anf/55981/css/global/site.css\"/></head><body>");

		for (int i = 0; i < matchprolist.size(); i++) {
			AfProduct matchpro = matchprolist.get(i);
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

		List<AfPage> urllist = AfPage.getMenclearanceBySinglePageInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			AfPage afpage = urllist.get(i);
			ParserAfPage parserAfpage = new ParserAfPage(afpage,
					allmennewprolist);
			try {
				parserAfpage.checkprice();
			} catch (Exception e) {
 				parsewithErr = true;
				SystemMail
						.sendSystemMail("Error when get Af Clearance Men page."
								+ e.toString());
			}
		}

		urllist = AfPage.getMenSaleBySinglePageInstance(); // getSaleInstance();getSaleBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			AfPage afpage = urllist.get(i);
			ParserAfPage parserAfpage = new ParserAfPage(afpage,
					allmennewprolist);
			try {
				parserAfpage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Af Men Sale page."
						+ e.toString());
			}
		}

		urllist = AfPage.getMenSecretInstance();
		for (int i = 0; i < urllist.size(); i++) {
			AfPage afpage = urllist.get(i);
			ParserAfPage parserAfpage = new ParserAfPage(afpage,
					allmennewprolist);
			try {
				parserAfpage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Af Men Secret page."
						+ e.toString());
			}
		}

		urllist = AfPage.getMenAllInstance();
		for (int i = 0; i < urllist.size(); i++) {
			AfPage afpage = urllist.get(i);
			ParserAfPage parserAfpage = new ParserAfPage(afpage,
					allmennewprolist);
			try {
				parserAfpage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Af Men All page."
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
			AfProduct product = allmennewprolist.get(key);
			AfProduct oldproduct = allmenoldprolist.get(key);
			if (oldproduct != null) {
				// have found this
				if (oldproduct.price > product.price) {
					// if (product.realdiscount <=
					// AfConstant.newproductcheck_discount) {
					// price dis
					product.addReason = "产品降价，旧价格：" + oldproduct.price;
					if (checkInventory(product))
						menmatchprolist.add(product);
					// }
				}
			} else {
				// if (product.realdiscount <=
				// AfConstant.newproductcheck_discount) {
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

		List<AfPage> urllist = AfPage.getWomenclearanceBySinglePageInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			AfPage afpage = urllist.get(i);
			ParserAfPage parserAfpage = new ParserAfPage(afpage,
					allwomennewprolist);
			try {
				parserAfpage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Af WoMen C page."
						+ e.toString());
			}
		}

		urllist = AfPage.getWomenSaleBySinglePageInstance(); // getSaleInstance();getSaleBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			AfPage afpage = urllist.get(i);
			ParserAfPage parserAfpage = new ParserAfPage(afpage,
					allwomennewprolist);
			try {
				parserAfpage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Af Women S page."
						+ e.toString());
			}
		}

		urllist = AfPage.getWomenSecretInstance();
		for (int i = 0; i < urllist.size(); i++) {
			AfPage afpage = urllist.get(i);
			ParserAfPage parserAfpage = new ParserAfPage(afpage,
					allwomennewprolist);
			try {
				parserAfpage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				parsewithErr = true;
				SystemMail
						.sendSystemMail("Error when get Af woMen Secret page."
								+ e.toString());
			}
		}

		urllist = AfPage.getWomenAllInstance();
		for (int i = 0; i < urllist.size(); i++) {
			AfPage afpage = urllist.get(i);
			ParserAfPage parserAfpage = new ParserAfPage(afpage,
					allmennewprolist);
			try {
				parserAfpage.checkprice();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Af woMen all page."
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
			AfProduct product = allwomennewprolist.get(key);
			AfProduct oldproduct = allwomenoldprolist.get(key);
			if (oldproduct != null) {
				// have found this
				if (oldproduct.price > product.price) {
					// if (product.realdiscount <=
					// AfConstant.newproductcheck_discount) {
					// price dis
					product.addReason = "产品降价，旧价格:" + oldproduct.price;
					if (checkInventory(product))
						womenmatchprolist.add(product);
					// }
				}
			} else {
				// if (product.realdiscount <=
				// AfConstant.newproductcheck_discount) {
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

	public boolean checkInventory(AfProduct product) {
		ParserAfProduct ParserAfProduct = new ParserAfProduct(product);
		ParserAfProduct.checkColorItemInventory(true);
		// return product.inventoryList == null ? false : true;
		return true;
	}
}

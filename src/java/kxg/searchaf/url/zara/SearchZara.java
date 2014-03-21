package kxg.searchaf.url.zara;

import java.util.*;

import kxg.searchaf.mail.Mail;
import kxg.searchaf.mail.SystemMail;
import kxg.searchaf.url.Constant;

public class SearchZara {

	public HashMap<Integer, ZaraProduct> allmenoldprolist = new HashMap<Integer, ZaraProduct>();

	public HashMap<Integer, ZaraProduct> allmennewprolist = new HashMap<Integer, ZaraProduct>();

	public ArrayList<ZaraProduct> menmatchprolist = new ArrayList<ZaraProduct>();

	public HashMap<Integer, ZaraProduct> allwomenoldprolist = new HashMap<Integer, ZaraProduct>();

	public HashMap<Integer, ZaraProduct> allwomennewprolist = new HashMap<Integer, ZaraProduct>();

	public ArrayList<ZaraProduct> womenmatchprolist = new ArrayList<ZaraProduct>();

	public HashMap<Integer, ZaraProduct> allboyoldprolist = new HashMap<Integer, ZaraProduct>();

	public HashMap<Integer, ZaraProduct> allboynewprolist = new HashMap<Integer, ZaraProduct>();

	public ArrayList<ZaraProduct> boymatchprolist = new ArrayList<ZaraProduct>();

	public HashMap<Integer, ZaraProduct> allgirloldprolist = new HashMap<Integer, ZaraProduct>();

	public HashMap<Integer, ZaraProduct> allgirlnewprolist = new HashMap<Integer, ZaraProduct>();

	public ArrayList<ZaraProduct> girlmatchprolist = new ArrayList<ZaraProduct>();

	public void getnewproduct() {
		System.out.println("Checking Zara Mens");
		this.allmennewprolist.clear();
		this.menmatchprolist.clear();
		checkMennewproduct();

		sendmail(menmatchprolist, "men");

		System.out.println("Checking Zara Womens");
		this.allwomennewprolist.clear();
		this.womenmatchprolist.clear();
		checkWoMennewproduct();

		sendmail(womenmatchprolist, "women");

		System.out.println("Checking Zara Boy");
		this.allboynewprolist.clear();
		this.boymatchprolist.clear();
		checkBoynewproduct();

		sendmail(boymatchprolist, "boy");

		System.out.println("Checking Zara girl");
		this.allgirlnewprolist.clear();
		this.girlmatchprolist.clear();
		checkGirlnewproduct();

		sendmail(girlmatchprolist, "girl");
	}

	private void sendmail(ArrayList<ZaraProduct> matchprolist, String ttype) {
		if (matchprolist.size() == 0)
			return;

		Collections.sort(matchprolist);

		Date currentDate = new Date();
		List<ZaraMailList> mailist = ZaraMailList.getinstance();
		for (int i = 0; i < mailist.size(); i++) {
			ZaraMailList amail = mailist.get(i);
			if (amail.valideTime.after(currentDate)) {
				ZaraUtil futli = new ZaraUtil();
				ArrayList<ZaraProduct> usermatch = futli.checkuser(
						matchprolist, amail, ttype);
				send2one(usermatch, amail);
			}
		}

	}

	private void send2one(ArrayList<ZaraProduct> matchprolist,
			ZaraMailList amail) {
		if (matchprolist.size() == 0)
			return;
		Mail m = new Mail();
		m.mail_subject = ZaraMailList.mail_subject;
		m.mail_to = amail.mailaddress;

		for (int i = 0; i < matchprolist.size(); i++) {
			ZaraProduct matchpro = matchprolist.get(i);
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
		List<ZaraPage> urllist = ZaraPage.getMenSale(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			ZaraPage ZaraPage = urllist.get(i);
			ParserZaraPage ParserZaraPage = new ParserZaraPage(ZaraPage,
					allmennewprolist);
			try {
				ParserZaraPage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Zara page."
						+ e.toString());
			}
		}

		System.out.println("found all Men's products:"
				+ allmennewprolist.size());

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
			ZaraProduct product = allmennewprolist.get(key);
			ZaraProduct oldproduct = allmenoldprolist.get(key);
			if (oldproduct != null) {
				// have found this
				if (oldproduct.price > product.price) {
					// price dis
					product.addReason = "价格变化，旧价格：" + oldproduct.price;
					menmatchprolist.add(product);
				}
			} else {
				// new one
				product.addReason = "新产品";
				menmatchprolist.add(product);
			}
		}

		// put new to old list
		if (!parsewithErr)
			this.allmenoldprolist.clear();
		this.allmenoldprolist.putAll(allmennewprolist);

	}

	private void checkBoynewproduct() {
		boolean parsewithErr = false;
		List<ZaraPage> urllist = ZaraPage.getBoysInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			ZaraPage ZaraPage = urllist.get(i);
			ParserZaraPage ParserZaraPage = new ParserZaraPage(ZaraPage,
					allboynewprolist);
			try {
				ParserZaraPage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Zara page."
						+ e.toString());
			}

		}

		System.out.println("found all Boy's products:"
				+ allboynewprolist.size());

		if (allboynewprolist.size() == 0) {
			// didn't get new product
			return;
		}

		if (allboyoldprolist.size() == 0) {
			// first time
			if (!parsewithErr)
				this.allboyoldprolist.putAll(allboynewprolist);
			return;
		}

		// check new product
		Iterator<Integer> entryKeyIterator = allboynewprolist.keySet()
				.iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			ZaraProduct product = allboynewprolist.get(key);
			ZaraProduct oldproduct = allboyoldprolist.get(key);
			if (oldproduct != null) {
				// have found this
				if (oldproduct.price > product.price) {
					// price dis
					product.addReason = "价格变化，旧价格：" + oldproduct.price;
					boymatchprolist.add(product);
				}
			} else {
				// new one
				product.addReason = "新产品";
				boymatchprolist.add(product);
			}
		}

		// put new to old list
		if (!parsewithErr)
			this.allboyoldprolist.clear();
		this.allboyoldprolist.putAll(allboynewprolist);

	}

	private void checkWoMennewproduct() {
		boolean parsewithErr = false;
		List<ZaraPage> urllist = ZaraPage.getWomenSale(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			ZaraPage ZaraPage = urllist.get(i);
			ParserZaraPage ParserZaraPage = new ParserZaraPage(ZaraPage,
					allwomennewprolist);
			try {
				ParserZaraPage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Zara page."
						+ e.toString());
			}

		}

		System.out.println("found all Women's products:"
				+ allwomennewprolist.size());

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
			ZaraProduct product = allwomennewprolist.get(key);
			ZaraProduct oldproduct = allwomenoldprolist.get(key);
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

		if (!parsewithErr)
			this.allwomenoldprolist.clear();
		this.allwomenoldprolist.putAll(allwomennewprolist);

	}

	private void checkGirlnewproduct() {
		boolean parsewithErr = false;
		List<ZaraPage> urllist = ZaraPage.getGirlsInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			ZaraPage ZaraPage = urllist.get(i);
			ParserZaraPage ParserZaraPage = new ParserZaraPage(ZaraPage,
					allgirlnewprolist);
			try {
				ParserZaraPage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Zara page."
						+ e.toString());
			}

		}

		System.out.println("found all Girl's products:"
				+ allgirlnewprolist.size());

		if (allgirlnewprolist.size() == 0) {
			// didn't get new product
			return;
		}

		if (allgirloldprolist.size() == 0) {
			// first time
			if (!parsewithErr)
				this.allgirloldprolist.putAll(allgirlnewprolist);
			return;
		}

		// check new product
		Iterator<Integer> entryKeyIterator = allgirlnewprolist.keySet()
				.iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			ZaraProduct product = allgirlnewprolist.get(key);
			ZaraProduct oldproduct = allgirloldprolist.get(key);
			if (oldproduct != null) {
				// have found this
				if (oldproduct.price > product.price) {
					// price dis
					product.addReason = "价格变化，旧价格：" + oldproduct.price;
					girlmatchprolist.add(product);
				}
			} else {
				// new one
				product.addReason = "新产品";
				girlmatchprolist.add(product);
			}
		}

		// put new to old list
		if (!parsewithErr)
			this.allgirloldprolist.clear();
		this.allgirloldprolist.putAll(allboynewprolist);

	}
}

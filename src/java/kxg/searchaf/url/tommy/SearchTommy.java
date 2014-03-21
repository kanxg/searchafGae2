package kxg.searchaf.url.tommy;

import java.util.*;

import kxg.searchaf.mail.Mail;
import kxg.searchaf.mail.SystemMail;
import kxg.searchaf.url.Constant;

public class SearchTommy {

	public HashMap<Integer, TommyProduct> allmenoldprolist = new HashMap<Integer, TommyProduct>();

	public HashMap<Integer, TommyProduct> allmennewprolist = new HashMap<Integer, TommyProduct>();

	public ArrayList<TommyProduct> menmatchprolist = new ArrayList<TommyProduct>();

	public HashMap<Integer, TommyProduct> allwomenoldprolist = new HashMap<Integer, TommyProduct>();

	public HashMap<Integer, TommyProduct> allwomennewprolist = new HashMap<Integer, TommyProduct>();

	public ArrayList<TommyProduct> womenmatchprolist = new ArrayList<TommyProduct>();

	public HashMap<Integer, TommyProduct> allboyoldprolist = new HashMap<Integer, TommyProduct>();

	public HashMap<Integer, TommyProduct> allboynewprolist = new HashMap<Integer, TommyProduct>();

	public ArrayList<TommyProduct> boymatchprolist = new ArrayList<TommyProduct>();

	public HashMap<Integer, TommyProduct> allgirloldprolist = new HashMap<Integer, TommyProduct>();

	public HashMap<Integer, TommyProduct> allgirlnewprolist = new HashMap<Integer, TommyProduct>();

	public ArrayList<TommyProduct> girlmatchprolist = new ArrayList<TommyProduct>();

	public void getnewproduct() {
		System.out.println("Checking Tommy Mens");
		this.allmennewprolist.clear();
		this.menmatchprolist.clear();
		checkMennewproduct();

		sendmail(menmatchprolist, "men");

		System.out.println("Checking Tommy Womens");
		this.allwomennewprolist.clear();
		this.womenmatchprolist.clear();
		checkWoMennewproduct();

		sendmail(womenmatchprolist, "women");

		System.out.println("Checking Tommy Boy");
		this.allboynewprolist.clear();
		this.boymatchprolist.clear();
		checkBoynewproduct();

		sendmail(boymatchprolist, "boy");

		System.out.println("Checking Tommy girl");
		this.allgirlnewprolist.clear();
		this.girlmatchprolist.clear();
		checkGirlnewproduct();

		sendmail(girlmatchprolist, "girl");
	}

	private void sendmail(ArrayList<TommyProduct> matchprolist, String ttype) {
		if (matchprolist.size() == 0)
			return;

		Collections.sort(matchprolist);

		Date currentDate = new Date();
		List<TommyMailList> mailist = TommyMailList.getinstance();
		for (int i = 0; i < mailist.size(); i++) {
			TommyMailList amail = mailist.get(i);
			if (amail.valideTime.after(currentDate)) {
				TommyUtil futli = new TommyUtil();
				ArrayList<TommyProduct> usermatch = futli.checkuser(
						matchprolist, amail, ttype);
				send2one(usermatch, amail);
			}
		}

	}

	private void send2one(ArrayList<TommyProduct> matchprolist,
			TommyMailList amail) {
		if (matchprolist.size() == 0)
			return;
		Mail m = new Mail();
		m.mail_subject = TommyMailList.mail_subject;
		m.mail_to = amail.mailaddress;

		for (int i = 0; i < matchprolist.size(); i++) {
			TommyProduct matchpro = matchprolist.get(i);
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
		List<TommyPage> urllist = TommyPage.getMenSale(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			TommyPage TommyPage = urllist.get(i);
			ParserTommyPage ParserTommyPage = new ParserTommyPage(TommyPage,
					allmennewprolist);
			try {
				ParserTommyPage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Tommy page."
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
			TommyProduct product = allmennewprolist.get(key);
			TommyProduct oldproduct = allmenoldprolist.get(key);
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
		List<TommyPage> urllist = TommyPage.getBoysInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			TommyPage TommyPage = urllist.get(i);
			ParserTommyPage ParserTommyPage = new ParserTommyPage(TommyPage,
					allboynewprolist);
			try {
				ParserTommyPage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Tommy page."
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
			TommyProduct product = allboynewprolist.get(key);
			TommyProduct oldproduct = allboyoldprolist.get(key);
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
		List<TommyPage> urllist = TommyPage.getWomenSale(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			TommyPage TommyPage = urllist.get(i);
			ParserTommyPage ParserTommyPage = new ParserTommyPage(TommyPage,
					allwomennewprolist);
			try {
				ParserTommyPage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Tommy page."
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
			TommyProduct product = allwomennewprolist.get(key);
			TommyProduct oldproduct = allwomenoldprolist.get(key);
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
		List<TommyPage> urllist = TommyPage.getGirlsInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			TommyPage TommyPage = urllist.get(i);
			ParserTommyPage ParserTommyPage = new ParserTommyPage(TommyPage,
					allgirlnewprolist);
			try {
				ParserTommyPage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Tommy page."
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
			TommyProduct product = allgirlnewprolist.get(key);
			TommyProduct oldproduct = allgirloldprolist.get(key);
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

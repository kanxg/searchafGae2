package kxg.searchaf.url.ralphlauren;

import java.util.*;

import kxg.searchaf.mail.Mail;
import kxg.searchaf.mail.SystemMail;
import kxg.searchaf.url.Constant;

public class SearchRalphlauren {

	public HashMap<Integer, RalphlaurenProduct> allmenoldprolist = new HashMap<Integer, RalphlaurenProduct>();

	public HashMap<Integer, RalphlaurenProduct> allmennewprolist = new HashMap<Integer, RalphlaurenProduct>();

	public ArrayList<RalphlaurenProduct> menmatchprolist = new ArrayList<RalphlaurenProduct>();

	public HashMap<Integer, RalphlaurenProduct> allwomenoldprolist = new HashMap<Integer, RalphlaurenProduct>();

	public HashMap<Integer, RalphlaurenProduct> allwomennewprolist = new HashMap<Integer, RalphlaurenProduct>();

	public ArrayList<RalphlaurenProduct> womenmatchprolist = new ArrayList<RalphlaurenProduct>();

	public HashMap<Integer, RalphlaurenProduct> allboyoldprolist = new HashMap<Integer, RalphlaurenProduct>();

	public HashMap<Integer, RalphlaurenProduct> allboynewprolist = new HashMap<Integer, RalphlaurenProduct>();

	public ArrayList<RalphlaurenProduct> boymatchprolist = new ArrayList<RalphlaurenProduct>();

	public HashMap<Integer, RalphlaurenProduct> allgirloldprolist = new HashMap<Integer, RalphlaurenProduct>();

	public HashMap<Integer, RalphlaurenProduct> allgirlnewprolist = new HashMap<Integer, RalphlaurenProduct>();

	public ArrayList<RalphlaurenProduct> girlmatchprolist = new ArrayList<RalphlaurenProduct>();

	public void getnewproduct() {
		System.out.println("Checking Ralphlauren Mens");
		this.allmennewprolist.clear();
		this.menmatchprolist.clear();
		checkMennewproduct();

		sendmail(menmatchprolist, "men");

		System.out.println("Checking Ralphlauren Womens");
		this.allwomennewprolist.clear();
		this.womenmatchprolist.clear();
		checkWoMennewproduct();

		sendmail(womenmatchprolist, "women");

		System.out.println("Checking Ralphlauren Boy");
		this.allboynewprolist.clear();
		this.boymatchprolist.clear();
		checkBoynewproduct();

		sendmail(boymatchprolist, "boy");

		System.out.println("Checking Ralphlauren girl");
		this.allgirlnewprolist.clear();
		this.girlmatchprolist.clear();
		checkGirlnewproduct();

		sendmail(girlmatchprolist, "girl");
	}

	private void sendmail(ArrayList<RalphlaurenProduct> matchprolist,
			String ttype) {
		if (matchprolist.size() == 0)
			return;

		Collections.sort(matchprolist);

		Date currentDate = new Date();
		List<RalphlaurenMailList> mailist = RalphlaurenMailList.getinstance();
		for (int i = 0; i < mailist.size(); i++) {
			RalphlaurenMailList amail = mailist.get(i);
			if (amail.valideTime.after(currentDate)) {
				RalphlaurenUtil futli = new RalphlaurenUtil();
				ArrayList<RalphlaurenProduct> usermatch = futli.checkuser(
						matchprolist, amail, ttype);
				send2one(usermatch, amail);
			}
		}

	}

	private void send2one(ArrayList<RalphlaurenProduct> matchprolist,
			RalphlaurenMailList amail) {
		if (matchprolist.size() == 0)
			return;
		Mail m = new Mail();
		m.mail_subject = RalphlaurenMailList.mail_subject;
		m.mail_to = amail.mailaddress;

		for (int i = 0; i < matchprolist.size(); i++) {
			RalphlaurenProduct matchpro = matchprolist.get(i);
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
		List<RalphlaurenPage> urllist = RalphlaurenPage.getMenSaleByBrand(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			RalphlaurenPage RalphlaurenPage = urllist.get(i);
			ParserRalphlaurenPage ParserRalphlaurenPage = new ParserRalphlaurenPage(
					RalphlaurenPage, allmennewprolist);
			try {
				ParserRalphlaurenPage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get RL page."
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
			RalphlaurenProduct product = allmennewprolist.get(key);
			RalphlaurenProduct oldproduct = allmenoldprolist.get(key);
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
		List<RalphlaurenPage> urllist = RalphlaurenPage.getBoysInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			RalphlaurenPage RalphlaurenPage = urllist.get(i);
			ParserRalphlaurenPage ParserRalphlaurenPage = new ParserRalphlaurenPage(
					RalphlaurenPage, allboynewprolist);
			try {
				ParserRalphlaurenPage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get RL page."
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
			RalphlaurenProduct product = allboynewprolist.get(key);
			RalphlaurenProduct oldproduct = allboyoldprolist.get(key);
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
		List<RalphlaurenPage> urllist = RalphlaurenPage.getWomenSaleByBrand(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			RalphlaurenPage RalphlaurenPage = urllist.get(i);
			ParserRalphlaurenPage ParserRalphlaurenPage = new ParserRalphlaurenPage(
					RalphlaurenPage, allwomennewprolist);
			try {
				ParserRalphlaurenPage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get RL page."
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
			RalphlaurenProduct product = allwomennewprolist.get(key);
			RalphlaurenProduct oldproduct = allwomenoldprolist.get(key);
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
		List<RalphlaurenPage> urllist = RalphlaurenPage.getGirlsInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			RalphlaurenPage RalphlaurenPage = urllist.get(i);
			ParserRalphlaurenPage ParserRalphlaurenPage = new ParserRalphlaurenPage(
					RalphlaurenPage, allgirlnewprolist);
			try {
				ParserRalphlaurenPage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get RL page."
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
			RalphlaurenProduct product = allgirlnewprolist.get(key);
			RalphlaurenProduct oldproduct = allgirloldprolist.get(key);
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

package kxg.searchaf.url.jcrew;

import java.util.*;

import kxg.searchaf.mail.Mail;
import kxg.searchaf.mail.SystemMail;
import kxg.searchaf.url.Constant;

public class SearchJcrew {

	public HashMap<String, JcrewProduct> allmenoldprolist = new HashMap<String, JcrewProduct>();

	public HashMap<String, JcrewProduct> allmennewprolist = new HashMap<String, JcrewProduct>();

	public ArrayList<JcrewProduct> menmatchprolist = new ArrayList<JcrewProduct>();

	public HashMap<String, JcrewProduct> allwomenoldprolist = new HashMap<String, JcrewProduct>();

	public HashMap<String, JcrewProduct> allwomennewprolist = new HashMap<String, JcrewProduct>();

	public ArrayList<JcrewProduct> womenmatchprolist = new ArrayList<JcrewProduct>();

	public HashMap<String, JcrewProduct> allboyoldprolist = new HashMap<String, JcrewProduct>();

	public HashMap<String, JcrewProduct> allboynewprolist = new HashMap<String, JcrewProduct>();

	public ArrayList<JcrewProduct> boymatchprolist = new ArrayList<JcrewProduct>();

	public HashMap<String, JcrewProduct> allgirloldprolist = new HashMap<String, JcrewProduct>();

	public HashMap<String, JcrewProduct> allgirlnewprolist = new HashMap<String, JcrewProduct>();

	public ArrayList<JcrewProduct> girlmatchprolist = new ArrayList<JcrewProduct>();

	public void getnewproduct() {
		System.out.println("Checking Jcrew Mens");
		this.allmennewprolist.clear();
		this.menmatchprolist.clear();
		checkMennewproduct();

		sendmail(menmatchprolist, "men");

		System.out.println("Checking Jcrew Womens");
		this.allwomennewprolist.clear();
		this.womenmatchprolist.clear();
		checkWoMennewproduct();

		sendmail(womenmatchprolist, "women");

		System.out.println("Checking Jcrew Boy");
		this.allboynewprolist.clear();
		this.boymatchprolist.clear();
		checkBoynewproduct();

		sendmail(boymatchprolist, "boy");

		System.out.println("Checking Jcrew girl");
		this.allgirlnewprolist.clear();
		this.girlmatchprolist.clear();
		checkGirlnewproduct();

		sendmail(girlmatchprolist, "girl");
	}

	private void sendmail(ArrayList<JcrewProduct> matchprolist, String ttype) {
		if (matchprolist.size() == 0)
			return;

		Collections.sort(matchprolist);

		Date currentDate = new Date();
		List<JcrewMailList> mailist = JcrewMailList.getinstance();
		for (int i = 0; i < mailist.size(); i++) {
			JcrewMailList amail = mailist.get(i);
			if (amail.valideTime.after(currentDate)) {
				JcrewUtil futli = new JcrewUtil();
				ArrayList<JcrewProduct> usermatch = futli.checkuser(
						matchprolist, amail, ttype);
				send2one(usermatch, amail);
			}
		}

	}

	private void send2one(ArrayList<JcrewProduct> matchprolist,
			JcrewMailList amail) {
		if (matchprolist.size() == 0)
			return;
		Mail m = new Mail();
		m.mail_subject = JcrewMailList.mail_subject;
		m.mail_to = amail.mailaddress;

		for (int i = 0; i < matchprolist.size(); i++) {
			JcrewProduct matchpro = matchprolist.get(i);
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
		List<JcrewPage> urllist = JcrewPage.getMenSale(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			JcrewPage JcrewPage = urllist.get(i);
			ParserJcrewPage ParserJcrewPage = new ParserJcrewPage(JcrewPage,
					allmennewprolist);
			try {
				ParserJcrewPage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Jcrew page."
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
		Iterator<String> entryKeyIterator = allmennewprolist.keySet()
				.iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			JcrewProduct product = allmennewprolist.get(key);
			JcrewProduct oldproduct = allmenoldprolist.get(key);
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

		List<JcrewPage> urllist = JcrewPage.getBoysInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			JcrewPage JcrewPage = urllist.get(i);
			ParserJcrewPage ParserJcrewPage = new ParserJcrewPage(JcrewPage,
					allboynewprolist);
			try {
				ParserJcrewPage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Jcrew page."
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
		Iterator<String> entryKeyIterator = allboynewprolist.keySet()
				.iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			JcrewProduct product = allboynewprolist.get(key);
			JcrewProduct oldproduct = allboyoldprolist.get(key);
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

		List<JcrewPage> urllist = JcrewPage.getWomenSale(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			JcrewPage JcrewPage = urllist.get(i);
			ParserJcrewPage ParserJcrewPage = new ParserJcrewPage(JcrewPage,
					allwomennewprolist);
			try {
				ParserJcrewPage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Jcrew page."
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
		Iterator<String> entryKeyIterator = allwomennewprolist.keySet()
				.iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			JcrewProduct product = allwomennewprolist.get(key);
			JcrewProduct oldproduct = allwomenoldprolist.get(key);
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

		List<JcrewPage> urllist = JcrewPage.getGirlsInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			JcrewPage JcrewPage = urllist.get(i);
			ParserJcrewPage ParserJcrewPage = new ParserJcrewPage(JcrewPage,
					allgirlnewprolist);
			try {
				ParserJcrewPage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Jcrew page."
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
		Iterator<String> entryKeyIterator = allgirlnewprolist.keySet()
				.iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			JcrewProduct product = allgirlnewprolist.get(key);
			JcrewProduct oldproduct = allgirloldprolist.get(key);
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

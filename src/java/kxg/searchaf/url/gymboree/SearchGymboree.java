package kxg.searchaf.url.gymboree;

import java.util.*;

import kxg.searchaf.mail.Mail;
import kxg.searchaf.mail.SystemMail;
import kxg.searchaf.url.Constant;

public class SearchGymboree {

	public HashMap<Integer, GymboreeProduct> allmenoldprolist = new HashMap<Integer, GymboreeProduct>();

	public HashMap<Integer, GymboreeProduct> allmennewprolist = new HashMap<Integer, GymboreeProduct>();

	public ArrayList<GymboreeProduct> menmatchprolist = new ArrayList<GymboreeProduct>();

	public HashMap<Integer, GymboreeProduct> allwomenoldprolist = new HashMap<Integer, GymboreeProduct>();

	public HashMap<Integer, GymboreeProduct> allwomennewprolist = new HashMap<Integer, GymboreeProduct>();

	public ArrayList<GymboreeProduct> womenmatchprolist = new ArrayList<GymboreeProduct>();

	public HashMap<Integer, GymboreeProduct> allboyoldprolist = new HashMap<Integer, GymboreeProduct>();

	public HashMap<Integer, GymboreeProduct> allboynewprolist = new HashMap<Integer, GymboreeProduct>();

	public ArrayList<GymboreeProduct> boymatchprolist = new ArrayList<GymboreeProduct>();

	public HashMap<Integer, GymboreeProduct> allgirloldprolist = new HashMap<Integer, GymboreeProduct>();

	public HashMap<Integer, GymboreeProduct> allgirlnewprolist = new HashMap<Integer, GymboreeProduct>();

	public ArrayList<GymboreeProduct> girlmatchprolist = new ArrayList<GymboreeProduct>();

	public void getnewproduct() {
		System.out.println("Checking Gymboree baby boys");
		this.allmennewprolist.clear();
		this.menmatchprolist.clear();
		checkMennewproduct();

		sendmail(menmatchprolist, "men");

		System.out.println("Checking Gymboree baby girls");
		this.allwomennewprolist.clear();
		this.womenmatchprolist.clear();
		checkWoMennewproduct();

		sendmail(womenmatchprolist, "women");

		System.out.println("Checking Gymboree Boy");
		this.allboynewprolist.clear();
		this.boymatchprolist.clear();
		checkBoynewproduct();

		sendmail(boymatchprolist, "boy");

		System.out.println("Checking Gymboree grils");
		this.allgirlnewprolist.clear();
		this.girlmatchprolist.clear();
		checkGirlnewproduct();

		sendmail(girlmatchprolist, "girl");
	}

	private void sendmail(ArrayList<GymboreeProduct> matchprolist, String ttype) {
		if (matchprolist.size() == 0)
			return;

		Collections.sort(matchprolist);

		Date currentDate = new Date();
		List<GymboreeMailList> mailist = GymboreeMailList.getinstance();
		for (int i = 0; i < mailist.size(); i++) {
			GymboreeMailList amail = mailist.get(i);
			if (amail.valideTime.after(currentDate)) {
				GymboreeUtil futli = new GymboreeUtil();
				ArrayList<GymboreeProduct> usermatch = futli.checkuser(
						matchprolist, amail, ttype);
				send2one(usermatch, amail);
			}
		}

	}

	private void send2one(ArrayList<GymboreeProduct> matchprolist,
			GymboreeMailList amail) {
		if (matchprolist.size() == 0)
			return;
		Mail m = new Mail();
		m.mail_subject = GymboreeMailList.mail_subject;
		m.mail_to = amail.mailaddress;

		for (int i = 0; i < matchprolist.size(); i++) {
			GymboreeProduct matchpro = matchprolist.get(i);
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
		List<GymboreePage> urllist = GymboreePage.getBabyBoySale(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			GymboreePage GymboreePage = urllist.get(i);
			ParserGymboreePage ParserGymboreePage = new ParserGymboreePage(
					GymboreePage, allmennewprolist);
			try {
				ParserGymboreePage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Gym page."
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
			GymboreeProduct product = allmennewprolist.get(key);
			GymboreeProduct oldproduct = allmenoldprolist.get(key);
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

		List<GymboreePage> urllist = GymboreePage.getBoySale(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			GymboreePage GymboreePage = urllist.get(i);
			ParserGymboreePage ParserGymboreePage = new ParserGymboreePage(
					GymboreePage, allboynewprolist);
			try {
				ParserGymboreePage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Gym page."
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
			GymboreeProduct product = allboynewprolist.get(key);
			GymboreeProduct oldproduct = allboyoldprolist.get(key);
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
		List<GymboreePage> urllist = GymboreePage.getBabyGirlSale(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			GymboreePage GymboreePage = urllist.get(i);
			ParserGymboreePage ParserGymboreePage = new ParserGymboreePage(
					GymboreePage, allwomennewprolist);
			try {
				ParserGymboreePage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Gym page."
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
			GymboreeProduct product = allwomennewprolist.get(key);
			GymboreeProduct oldproduct = allwomenoldprolist.get(key);
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
		List<GymboreePage> urllist = GymboreePage.getGirlSale(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			GymboreePage GymboreePage = urllist.get(i);
			ParserGymboreePage ParserGymboreePage = new ParserGymboreePage(
					GymboreePage, allgirlnewprolist);
			try {
				ParserGymboreePage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Gym page."
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
			GymboreeProduct product = allgirlnewprolist.get(key);
			GymboreeProduct oldproduct = allgirloldprolist.get(key);
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

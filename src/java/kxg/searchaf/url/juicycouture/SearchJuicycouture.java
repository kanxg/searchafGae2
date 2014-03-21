package kxg.searchaf.url.juicycouture;

import java.util.*;

import kxg.searchaf.mail.Mail;
import kxg.searchaf.mail.SystemMail;
import kxg.searchaf.url.Constant;

public class SearchJuicycouture {

	public HashMap<String, JuicycoutureProduct> allwomenoldprolist = new HashMap<String, JuicycoutureProduct>();

	public HashMap<String, JuicycoutureProduct> allwomennewprolist = new HashMap<String, JuicycoutureProduct>();

	public ArrayList<JuicycoutureProduct> womenmatchprolist = new ArrayList<JuicycoutureProduct>();

	public void getnewproduct(int i) {
		System.out.println("Checking Juicycouture Womens");
		if (i % (1) == 0) {
			JuicycoutureMailList.sync();
		}
		this.allwomennewprolist.clear();
		this.womenmatchprolist.clear();
		checkWoMennewproduct();

		sendmail(womenmatchprolist);
	}

	private void sendmail(ArrayList<JuicycoutureProduct> matchprolist) {
		if (matchprolist.size() == 0)
			return;

		Collections.sort(matchprolist);

		Date currentDate = new Date();
		List<JuicycoutureMailList> mailist = JuicycoutureMailList.getinstance();
		for (int i = 0; i < mailist.size(); i++) {
			JuicycoutureMailList amail = mailist.get(i);
			if (amail.valideTime.after(currentDate)) {
				if (amail.warningWomen) {
					JuicycoutureUtil futli = new JuicycoutureUtil();
					ArrayList<JuicycoutureProduct> usermatch = futli.checkuser(
							matchprolist, amail);
					send2one(usermatch, amail);
				}
			}
		}

	}

	private void send2one(ArrayList<JuicycoutureProduct> matchprolist,
			JuicycoutureMailList amail) {
		if (matchprolist.size() == 0)
			return;
		Mail m = new Mail();
		m.mail_subject = JuicycoutureMailList.mail_subject;
		m.mail_to = amail.mailaddress;

		for (int i = 0; i < matchprolist.size(); i++) {
			JuicycoutureProduct matchpro = matchprolist.get(i);
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

	private void checkWoMennewproduct() {
		boolean parsewithErr = false;
		List<JuicycouturePage> urllist = JuicycouturePage.getWomenSale(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			JuicycouturePage JuicycouturePage = urllist.get(i);
			ParserJuicycouturePage ParserJuicycouturePage = new ParserJuicycouturePage(
					JuicycouturePage, allwomennewprolist);
			try {
				ParserJuicycouturePage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Juicycouture page."
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
		Iterator<String> entryKeyIterator = allwomennewprolist.keySet()
				.iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			JuicycoutureProduct product = allwomennewprolist.get(key);
			JuicycoutureProduct oldproduct = allwomenoldprolist.get(key);
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

}

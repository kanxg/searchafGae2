package kxg.searchaf.url.victoriassecret;

import java.util.*;

import kxg.searchaf.mail.Mail;
import kxg.searchaf.mail.SystemMail;
import kxg.searchaf.url.Constant;

public class SearchVictoriassecret {

	public HashMap<String, VictoriassecretProduct> allwomenoldprolist = new HashMap<String, VictoriassecretProduct>();

	public HashMap<String, VictoriassecretProduct> allwomennewprolist = new HashMap<String, VictoriassecretProduct>();

	public ArrayList<VictoriassecretProduct> womenmatchprolist = new ArrayList<VictoriassecretProduct>();

	public void getnewproduct(int i) {
		System.out.println("Checking Victoriassecret Womens");
		if (i % (1) == 0) {
			VictoriassecretMailList.sync();
		}
		this.allwomennewprolist.clear();
		this.womenmatchprolist.clear();
		checkWoMennewproduct();

		sendmail(womenmatchprolist);
	}

	private void sendmail(ArrayList<VictoriassecretProduct> matchprolist) {
		if (matchprolist.size() == 0)
			return;

		Collections.sort(matchprolist);

		Date currentDate = new Date();
		List<VictoriassecretMailList> mailist = VictoriassecretMailList
				.getinstance();
		for (int i = 0; i < mailist.size(); i++) {
			VictoriassecretMailList amail = mailist.get(i);
			if (amail.valideTime.after(currentDate)) {
				if (amail.warningWomen) {
					VictoriassecretUtil futli = new VictoriassecretUtil();
					ArrayList<VictoriassecretProduct> usermatch = futli
							.checkuser(matchprolist, amail);
					send2one(usermatch, amail);
				}
			}
		}

	}

	private void send2one(ArrayList<VictoriassecretProduct> matchprolist,
			VictoriassecretMailList amail) {
		if (matchprolist.size() == 0)
			return;
		Mail m = new Mail();
		m.mail_subject = VictoriassecretMailList.mail_subject;
		m.mail_to = amail.mailaddress;

		for (int i = 0; i < matchprolist.size(); i++) {
			VictoriassecretProduct matchpro = matchprolist.get(i);
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
		List<VictoriassecretPage> urllist = VictoriassecretPage.getWomenSale(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			VictoriassecretPage VictoriassecretPage = urllist.get(i);
			ParserVictoriassecretPage ParserVictoriassecretPage = new ParserVictoriassecretPage(
					VictoriassecretPage, allwomennewprolist);
			try {
				ParserVictoriassecretPage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get VS page."
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
			VictoriassecretProduct product = allwomennewprolist.get(key);
			VictoriassecretProduct oldproduct = allwomenoldprolist.get(key);
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

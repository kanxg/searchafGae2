package kxg.searchaf.url.toryburch;

import java.util.*;

import kxg.searchaf.mail.Mail;
import kxg.searchaf.mail.SystemMail;
import kxg.searchaf.url.Constant;

public class SearchToryburch {

	public HashMap<String, ToryburchProduct> allwomenoldprolist = new HashMap<String, ToryburchProduct>();

	public HashMap<String, ToryburchProduct> allwomennewprolist = new HashMap<String, ToryburchProduct>();

	public ArrayList<ToryburchProduct> womenmatchprolist = new ArrayList<ToryburchProduct>();

	public void getnewproduct(int i) {
		System.out.println("Checking Toryburch Womens");
		if (i % (1) == 0) {
			ToryburchMailList.sync();
		}
		this.allwomennewprolist.clear();
		this.womenmatchprolist.clear();
		checkWoMennewproduct();

		sendmail(womenmatchprolist);
	}

	private void sendmail(ArrayList<ToryburchProduct> matchprolist) {
		if (matchprolist.size() == 0)
			return;

		Collections.sort(matchprolist);

		Date currentDate = new Date();
		List<ToryburchMailList> mailist = ToryburchMailList.getinstance();
		for (int i = 0; i < mailist.size(); i++) {
			ToryburchMailList amail = mailist.get(i);
			if (amail.valideTime.after(currentDate)) {
				if (amail.warningWomen) {
					ToryburchUtil futli = new ToryburchUtil();
					ArrayList<ToryburchProduct> usermatch = futli.checkuser(
							matchprolist, amail);
					send2one(usermatch, amail);
				}
			}
		}

	}

	private void send2one(ArrayList<ToryburchProduct> matchprolist,
			ToryburchMailList amail) {
		if (matchprolist.size() == 0)
			return;
		Mail m = new Mail();
		m.mail_subject = ToryburchMailList.mail_subject;
		m.mail_to = amail.mailaddress;

		for (int i = 0; i < matchprolist.size(); i++) {
			ToryburchProduct matchpro = matchprolist.get(i);
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
		List<ToryburchPage> urllist = ToryburchPage.getWomenSale(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			ToryburchPage ToryburchPage = urllist.get(i);
			ParserToryburchPage ParserToryburchPage = new ParserToryburchPage(
					ToryburchPage, allwomennewprolist);
			try {
				ParserToryburchPage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get TB page."
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
			ToryburchProduct product = allwomennewprolist.get(key);
			ToryburchProduct oldproduct = allwomenoldprolist.get(key);
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

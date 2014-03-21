package kxg.searchaf.url.freepeople;

import java.util.*;

import kxg.searchaf.mail.Mail;
import kxg.searchaf.mail.SystemMail;
import kxg.searchaf.url.Constant;

public class SearchFreepeople {

	public HashMap<String, FreepeopleProduct> allwomenoldprolist = new HashMap<String, FreepeopleProduct>();

	public HashMap<String, FreepeopleProduct> allwomennewprolist = new HashMap<String, FreepeopleProduct>();

	public ArrayList<FreepeopleProduct> womenmatchprolist = new ArrayList<FreepeopleProduct>();

	public void getnewproduct(int i) {
		System.out.println("Checking Freepeople Womens");
		if (i % (1) == 0) {
			FreepeopleMailList.sync();
		}
		this.allwomennewprolist.clear();
		this.womenmatchprolist.clear();
		checkWoMennewproduct();

		sendmail(womenmatchprolist);
	}

	private void sendmail(ArrayList<FreepeopleProduct> matchprolist) {
		if (matchprolist.size() == 0)
			return;

		Collections.sort(matchprolist);

		Date currentDate = new Date();
		List<FreepeopleMailList> mailist = FreepeopleMailList.getinstance();
		for (int i = 0; i < mailist.size(); i++) {
			FreepeopleMailList amail = mailist.get(i);
			if (amail.valideTime.after(currentDate)) {
				if (amail.warningWomen) {
					FreepeopleUtil futli = new FreepeopleUtil();
					ArrayList<FreepeopleProduct> usermatch = futli.checkuser(
							matchprolist, amail);
					send2one(usermatch, amail);
				}
			}
		}

	}

	private void send2one(ArrayList<FreepeopleProduct> matchprolist,
			FreepeopleMailList amail) {
		if (matchprolist.size() == 0)
			return;
		Mail m = new Mail();
		m.mail_subject = FreepeopleMailList.mail_subject;
		m.mail_to = amail.mailaddress;

		for (int i = 0; i < matchprolist.size(); i++) {
			FreepeopleProduct matchpro = matchprolist.get(i);
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
		List<FreepeoplePage> urllist = FreepeoplePage.getWomenSale(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			FreepeoplePage FreepeoplePage = urllist.get(i);
			ParserFreepeoplePage ParserFreepeoplePage = new ParserFreepeoplePage(
					FreepeoplePage, allwomennewprolist);
			try {
				ParserFreepeoplePage.checkprice();
			} catch (Exception e) {
				parsewithErr = true;
				SystemMail.sendSystemMail("Error when get Freepeople page."
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
			FreepeopleProduct product = allwomennewprolist.get(key);
			FreepeopleProduct oldproduct = allwomenoldprolist.get(key);
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

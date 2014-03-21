package kxg.searchaf.url.coachfactory;

import java.util.*;

import kxg.searchaf.mail.Mail;
import kxg.searchaf.url.Constant;

public class Searchcoachfactory {

	public HashMap<Integer, coachfactoryProduct> alloldprolist = new HashMap<Integer, coachfactoryProduct>();

	public HashMap<Integer, coachfactoryProduct> allnewprolist = new HashMap<Integer, coachfactoryProduct>();

	public ArrayList<coachfactoryProduct> matchprolist = new ArrayList<coachfactoryProduct>();

	public static void main(String[] args) {

		Searchcoachfactory af = new Searchcoachfactory();

		// while (true) {
		try {
			// System.out.println("start checking...");
			af.getdiscountproduct();
			// System.out.println(new Date() + "done...");
			// Thread.sleep(1000 * 60 * Constant.sleeptime); // sleep 10 mines
		} catch (Exception e) {
			e.printStackTrace();
		}
		// }
	}

	public void getnewproduct() {
		System.out.println("Checking coachfactory Mens");
		this.allnewprolist.clear();
		this.matchprolist.clear();
		checknewproduct();
		sendmail(matchprolist, true);
	}

	public void getdiscountproduct() {
		this.allnewprolist.clear();
		this.matchprolist.clear();
		checkdiscountproduct();
		// sendmail(matchprolist, true);

	}

	private void sendmail(ArrayList<coachfactoryProduct> matchprolist,
			boolean isMen) {
		if (matchprolist.size() > 0) {
			Collections.sort(matchprolist);
			Mail m = new Mail();
			m.mail_subject = coachfactoryMailList.mail_subject;
			m.mail_to = coachfactoryMailList.mailto;

			for (int i = 0; i < matchprolist.size(); i++) {
				coachfactoryProduct matchpro = matchprolist.get(i);
				// attach html file
				m.tagcontext
						.append(matchpro
								+ "<br/>"
								+ matchpro.tagcontext
								+ "<br/>************************************************<br/>");
			}
			m.tagcontext.append("</body></html>");

			Date currentDate = new Date();
			List<coachfactoryMailList> afmailist = coachfactoryMailList
					.getinstance();
			List<String> cc_to = new ArrayList<String>();
			for (int i = 0; i < afmailist.size(); i++) {
				coachfactoryMailList amail = afmailist.get(i);
				if (amail.valideTime.after(currentDate)) {
					cc_to.add(amail.mailaddress);
				}
			}
			m.cc_to = cc_to;
			try {
				m.sendMail();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void checknewproduct() {
		List<coachfactoryPage> urllist = coachfactoryPage.getSale(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			coachfactoryPage coachfactoryPage = urllist.get(i);
			ParsercoachfactoryPage ParsercoachfactoryPage = new ParsercoachfactoryPage(
					coachfactoryPage, allnewprolist);
			ParsercoachfactoryPage.checkprice();
		}

		System.out.println("found all Men's products:" + allnewprolist.size());

		if (allnewprolist.size() == 0) {
			// didn't get new product
			return;
		}

		if (alloldprolist.size() == 0) {
			// first time
			this.alloldprolist.putAll(allnewprolist);
			return;
		}

		// check new product
		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			coachfactoryProduct product = allnewprolist.get(key);
			coachfactoryProduct oldproduct = alloldprolist.get(key);
			if (oldproduct != null) {
				// have found this
				if (oldproduct.price > product.price) {
					// price dis
					product.addReason = "价格变化，旧价格：" + oldproduct.price;
					matchprolist.add(product);
				}
			} else {
				// new one
				product.addReason = "新产品";
				matchprolist.add(product);
			}
		}

		// put new to old list
		this.alloldprolist.clear();
		this.alloldprolist.putAll(allnewprolist);

	}

	private void checkdiscountproduct() {

		List<coachfactoryPage> urllist = coachfactoryPage.getSale(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			coachfactoryPage coachfactoryPage = urllist.get(i);
			ParsercoachfactoryPage ParsercoachfactoryPage = new ParsercoachfactoryPage(
					coachfactoryPage, allnewprolist);
			ParsercoachfactoryPage.checkprice();
		}

		System.out.println("found all of products:" + allnewprolist.size());

		Iterator<Integer> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			Integer key = entryKeyIterator.next();
			coachfactoryProduct product = allnewprolist.get(key);
			if (product.realdiscount <= coachfactoryConstant.warningdiscount) {
				// check ignore product
				matchprolist.add(product);
			}
		}
		System.out.println("found meet price products:" + matchprolist.size());

	}

}

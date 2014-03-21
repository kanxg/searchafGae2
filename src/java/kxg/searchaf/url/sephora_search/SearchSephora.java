package kxg.searchaf.url.sephora_search;

import java.util.*;
import kxg.searchaf.mail.Mail;
import kxg.searchaf.url.Constant;

public class SearchSephora {

	public HashMap<String, SephoraProduct> alloldprolist = new HashMap<String, SephoraProduct>();

	public HashMap<String, SephoraProduct> allnewprolist = new HashMap<String, SephoraProduct>();

	public ArrayList<SephoraProduct> matchprolist = new ArrayList<SephoraProduct>();

	public static void main(String[] args) {
		SearchSephora sp = new SearchSephora();
		sp.getnewproduct();
	}

	public void getnewproduct() {
		// System.out.println("Checking Sephora Mens");
		// this.allmennewprolist.clear();
		// this.menmatchprolist.clear();
		// checkMennewproduct();
		// 
		// sendmail(menmatchprolist, true);

		System.out.println("Checking Sephora");
		this.allnewprolist.clear();
		this.matchprolist.clear();
		chechnewproduct();
			sendmail(matchprolist);
	}

	public void sendmail(ArrayList<SephoraProduct> matchprolist) {
		if (matchprolist.size() > 0) {
			Collections.sort(matchprolist);
			Mail m = new Mail();

			m.mail_subject = SephoraMailList.mail_subject;
			m.mail_to = SephoraMailList.mailto;

			for (int i = 0; i < matchprolist.size(); i++) {
				SephoraProduct matchpro = matchprolist.get(i);
				// attach html file
				m.tagcontext
						.append(matchpro
								+ "<br/>"
								+ matchpro.tagcontext
								+ "<br/>************************************************<br/>");
			}
			m.tagcontext.append("</body></html>");

			Date currentDate = new Date();
			List<SephoraMailList> afmailist = SephoraMailList.getinstance();
			List<String> cc_to = new ArrayList<String>();
			for (int i = 0; i < afmailist.size(); i++) {
				SephoraMailList amail = afmailist.get(i);
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

	private void chechnewproduct() {
		try {
			List<SephoraPage> urllist = SephoraPage.getSale(); // getClearanceInstance();getclearanceBySinglePageInstance
			for (int i = 0; i < urllist.size(); i++) {
				SephoraPage SephoraPage = urllist.get(i);
				ParserSephoraPage ParserSephoraPage = new ParserSephoraPage(
						SephoraPage, allnewprolist);
				ParserSephoraPage.checkprice();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error when get Sephora page." + e.getMessage());
		}

		System.out.println("found products:" + allnewprolist.size());

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
		Iterator<String> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			SephoraProduct product = allnewprolist.get(key);
			SephoraProduct oldproduct = alloldprolist.get(key);
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

}

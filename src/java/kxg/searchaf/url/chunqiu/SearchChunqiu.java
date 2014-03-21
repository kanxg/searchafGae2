package kxg.searchaf.url.chunqiu;

import java.util.*;
import kxg.searchaf.mail.Mail;
import kxg.searchaf.url.Constant;

public class SearchChunqiu {

	public HashMap<String, ChunqiuProduct> alloldprolist = new HashMap<String, ChunqiuProduct>();

	public HashMap<String, ChunqiuProduct> allnewprolist = new HashMap<String, ChunqiuProduct>();

	public ArrayList<ChunqiuProduct> matchprolist = new ArrayList<ChunqiuProduct>();

	public static void main(String[] args) throws Exception {
		SearchChunqiu search = new SearchChunqiu();
		search.getnewproduct();
	}

	public void getnewproduct() {
		System.out.println("Checking Chunqiu");
		this.allnewprolist.clear();
		this.matchprolist.clear();
		checkMennewproduct();

		
			sendmail(matchprolist);

	}

	private void sendmail(ArrayList<ChunqiuProduct> matchprolist) {
		if (matchprolist.size() > 0) {
			Collections.sort(matchprolist);
			Mail m = new Mail();
			m.mail_subject = ChunqiuMailList.mail_subject;
			m.mail_to = ChunqiuMailList.mailto;
			for (int i = 0; i < matchprolist.size(); i++) {
				ChunqiuProduct matchpro = matchprolist.get(i);
				// attach html file
				m.tagcontext
						.append(matchpro
								+ "<br/>"
								// S+ matchpro.tagcontext.toString()
								+ "<br/>************************************************<br/>");
			}
			m.tagcontext.append("</body></html>");

			Date currentDate = new Date();
			List<ChunqiuMailList> afmailist = ChunqiuMailList.getinstance();
			List<String> cc_to = new ArrayList<String>();
			for (int i = 0; i < afmailist.size(); i++) {
				ChunqiuMailList amail = afmailist.get(i);
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

 
	private void checkMennewproduct() {
		try {
			List<ChunqiuPage> urllist = ChunqiuPage.getAll(); // getClearanceInstance();getclearanceBySinglePageInstance
			for (int i = 0; i < urllist.size(); i++) {
				ChunqiuPage ChunqiuPage = urllist.get(i);
				ParserChunqiuPage ParserChunqiuPage = new ParserChunqiuPage(
						ChunqiuPage, allnewprolist);
				ParserChunqiuPage.checkprice();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error when get Chunqiu page." + e.getMessage());
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
			ChunqiuProduct product = allnewprolist.get(key);
			ChunqiuProduct oldproduct = alloldprolist.get(key);
			if (oldproduct != null) {
				// have found this
				if (oldproduct.price > product.price) {
					if (ChunqiuConstant.ischeckingHangban(product.hangban)) {
						// price dis
						product.addReason = "价格变化，旧价格：" + oldproduct.price;
						matchprolist.add(product);
					}
				}
			} else {
				if (ChunqiuConstant.ischeckingHangban(product.hangban)) {
					// new one
					product.addReason = "新产品";
					matchprolist.add(product);
				}
			}
		}

		// put new to old list
		this.alloldprolist.clear();
		this.alloldprolist.putAll(allnewprolist);

	}

}

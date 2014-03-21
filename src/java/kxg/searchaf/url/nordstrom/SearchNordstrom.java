package kxg.searchaf.url.nordstrom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

import kxg.searchaf.mail.Mail;
import kxg.searchaf.mail.SystemMail;
import kxg.searchaf.url.Constant;
import kxg.searchaf.util.ProxyUtli;

public class SearchNordstrom {

	Map<String, NordstromPage> pagelist = new HashMap<String, NordstromPage>();
	List<NordstromPage> maillist = new ArrayList<NordstromPage>();

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);
		SearchNordstrom af = new SearchNordstrom();
		// System.out.println("start checking...");
		af.searchsite();

	}

	public void searchsite() {
		System.out.println("Checking Nordstrom pages...");
		syncDb();
		check();
		removeDeletePage();
	}

	private void syncDb() {
		NordstromPageJSP jsp = new NordstromPageJSP();
		List<NordstromPage> urllist = jsp.loadAll();
		if (urllist != null & urllist.size() != 0)
			maillist = urllist;
	}

	private void sendmail(NordstromPage oldpage, NordstromPage page) {

		Mail m = new Mail();
		if (!oldpage.instock && page.instock) {
			m.mail_subject = "Nordstrom价格提醒,您的产品<" + page.prodname + ">,到货！";
		} else {
			m.mail_subject = "Nordstrom价格提醒,您的产品<" + page.prodname + ">,降价！";
		}
		m.tagcontext.append(page.parseHTML());
		// m.tagcontext
		// .append("<br/><br/><br/>***************以下调试信息，用户请忽略**************");
		// m.tagcontext.append("<br/>old price:" + oldpage.price);
		// m.tagcontext.append("<br/>new price:" + page.price);
		// m.tagcontext.append("<br/>old instock:" + oldpage.instock);
		// m.tagcontext.append("<br/>new instock:" + page.instock);

		// attach html file
		m.mail_to = page.mailaddress;
		try {
			m.sendMail();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void check() {
		if (maillist != null)
			System.out.println("Checking Nordstrom pages size:"
					+ maillist.size());
		for (int i = 0; i < maillist.size(); i++) {
			NordstromPage page = maillist.get(i);
			try {

				ParserNordstromPage parserNordstromPage = new ParserNordstromPage(
						page);
				parserNordstromPage.checkprice();

				if (page.price == 0) {
					// 没有取得数据
					continue;
				}
				String key = page.mailaddress + page.url;
				NordstromPage oldpage = pagelist.get(key);
				if (oldpage != null) {
					checkPageChange(oldpage, page);
				}
				pagelist.put(key, page);

			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println("error when get URL:" + page.url);
				SystemMail.sendSystemMail("Error when get Nordstrom page."
						+ page.url + ";" + e.toString());
			}
		}
	}

	private void checkPageChange(NordstromPage oldpage, NordstromPage page) {
		boolean sendmail = false;
		if (page.instock) {
			if (!oldpage.instock && page.expectPirce >= page.price) {
				sendmail = true;
			} else {
				if (oldpage.price > page.price
						&& page.expectPirce >= page.price)
					sendmail = true;
			}
		}
		if (sendmail)
			sendmail(oldpage, page);
	}

	private void removeDeletePage() {
		Iterator<String> entryKeyIterator = pagelist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			NordstromPage page = pagelist.get(key);
			if (!existinDb(page)) {
				entryKeyIterator.remove();
				pagelist.remove(key);
			}
		}
	}

	private boolean existinDb(NordstromPage page) {

		for (int i = 0; i < maillist.size(); i++) {
			NordstromPage pageindb = maillist.get(i);
			if (page.mailaddress.equalsIgnoreCase(pageindb.mailaddress)
					&& page.url.equalsIgnoreCase(pageindb.url))
				return true;
		}
		return false;
	}
}

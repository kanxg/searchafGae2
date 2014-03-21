package kxg.searchaf.url.gnc;

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

public class SearchGnc {

	Map<String, GncPage> pagelist = new HashMap<String, GncPage>();
	List<GncPage> maillist = new ArrayList<GncPage>();

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);
		SearchGnc af = new SearchGnc();
		// System.out.println("start checking...");
		af.searchsite();

	}

	public void searchsite() {
		System.out.println("Checking Gnc pages...");
		syncDb();
		check();
		removeDeletePage();
	}

	private void syncDb() {
		GncPageJSP jsp = new GncPageJSP();
		List<GncPage> urllist = jsp.loadAll();
		if (urllist != null & urllist.size() != 0)
			maillist = urllist;
	}

	private void sendmail(GncPage oldpage, GncPage page) {

		Mail m = new Mail();
		if (!oldpage.instock && page.instock) {
			m.mail_subject = "Gnc价格提醒,您的产品<" + page.prodname + ">,到货！";
		} else {
			m.mail_subject = "Gnc价格提醒,您的产品<" + page.prodname + ">,降价！";
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
			System.out.println("Checking Gnc pages size:"
					+ maillist.size());
		for (int i = 0; i < maillist.size(); i++) {
			GncPage page = maillist.get(i);
			try {

				ParserGncPage parserGncPage = new ParserGncPage(
						page);
				parserGncPage.checkprice();

				if (page.price == 0) {
					// 没有取得数据
					continue;
				}
				String key = page.mailaddress + page.url;
				GncPage oldpage = pagelist.get(key);
				if (oldpage != null) {
					checkPageChange(oldpage, page);
				}
				pagelist.put(key, page);

			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println("error when get URL:" + page.url);
				SystemMail.sendSystemMail("Error when get Gnc page."
						+ page.url + ";" + e.toString());
			}
		}
	}

	private void checkPageChange(GncPage oldpage, GncPage page) {
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
			GncPage page = pagelist.get(key);
			if (!existinDb(page)) {
				entryKeyIterator.remove();
				pagelist.remove(key);
			}
		}
	}

	private boolean existinDb(GncPage page) {

		for (int i = 0; i < maillist.size(); i++) {
			GncPage pageindb = maillist.get(i);
			if (page.mailaddress.equalsIgnoreCase(pageindb.mailaddress)
					&& page.url.equalsIgnoreCase(pageindb.url))
				return true;
		}
		return false;
	}
}

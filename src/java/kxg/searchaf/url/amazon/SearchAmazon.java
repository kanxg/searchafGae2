package kxg.searchaf.url.amazon;

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
import kxg.searchaf.weixin.WeixinHelper;

public class SearchAmazon {

	Map<String, AmazonPage> pagelist = new HashMap<String, AmazonPage>();
	List<AmazonPage> maillist = new ArrayList<AmazonPage>();

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);
		SearchAmazon af = new SearchAmazon();
		// System.out.println("start checking...");
		af.searchsite();

	}

	public void searchsite() {
		System.out.println("Checking Amazon pages...");
		syncDb();
		check();
		removeDeletePage();
	}

	private void syncDb() {
		AmazonPageJSP jsp = new AmazonPageJSP();
		List<AmazonPage> urllist = jsp.loadAll();
		if (urllist != null & urllist.size() != 0)
			maillist = urllist;
	}

	private void sendweixin(AmazonPage oldpage, AmazonPage page) {

		StringBuffer buff = new StringBuffer();
		if (!oldpage.instock && page.instock) {
			buff.append("Amazon价格提醒,您的产品<" + page.prodname + ">,到货！");
		} else {
			buff.append("Amazon价格提醒,您的产品<" + page.prodname + ">,降价！");
		}
		buff.append(page.parseHTML());
		// m.tagcontext
		// .append("<br/><br/><br/>***************以下调试信息，用户请忽略**************");
		// m.tagcontext.append("<br/>old price:" + oldpage.price);
		// m.tagcontext.append("<br/>new price:" + page.price);
		// m.tagcontext.append("<br/>old instock:" + oldpage.instock);
		// m.tagcontext.append("<br/>new instock:" + page.instock);

		WeixinHelper.sendWeixin(page.weixin, buff.toString());

	}

	private void sendmail(AmazonPage oldpage, AmazonPage page) {

		Mail m = new Mail();
		if (!oldpage.instock && page.instock) {
			m.mail_subject = "Amazon价格提醒,您的产品<" + page.prodname + ">,到货！";
		} else {
			m.mail_subject = "Amazon价格提醒,您的产品<" + page.prodname + ">,降价！";
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
			System.out.println("Checking Amazon pages size:" + maillist.size());
		for (int i = 0; i < maillist.size(); i++) {
			AmazonPage page = maillist.get(i);
			try {
				if (page.url.indexOf("amazon.cn") > 0) {
					ParserAmazonCnPage parserAmazonPage = new ParserAmazonCnPage(
							page);
					parserAmazonPage.checkprice();
				} else if (page.url.indexOf("amazon.de") > 0) {
					ParserAmazonDePage parserAmazonPage = new ParserAmazonDePage(
							page);
					parserAmazonPage.checkprice();
				} else if (page.url.indexOf("amazon.co.jp") > 0) {
					ParserAmazonJpPage parserAmazonPage = new ParserAmazonJpPage(
							page);
					parserAmazonPage.checkprice();
				} else if (page.url.indexOf("amazon.co.uk") > 0) {
					ParserAmazonUkPage parserAmazonPage = new ParserAmazonUkPage(
							page);
					parserAmazonPage.checkprice();
				} else {
					ParserAmazonPage parserAmazonPage = new ParserAmazonPage(
							page);
					parserAmazonPage.checkprice();
				}
				if (page.price == 0) {
					// 没有取得数据
					continue;
				}
				if (page.checksalerisAmazon && !page.salerisAmazon) {
					// 取得不是自营产品
					continue;
				}
				String key = page.mailaddress + page.url;
				AmazonPage oldpage = pagelist.get(key);
				if (oldpage != null) {
					checkPageChange(oldpage, page);
				}
				pagelist.put(key, page);

			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println("error when get URL:" + page.url);
				SystemMail.sendSystemMail("Error when get Amazon page."
						+ page.url + ";" + e.toString());
			}
		}
	}

	private void checkPageChange(AmazonPage oldpage, AmazonPage page) {
		boolean ischanged = false;
		if (page.instock) {
			if (!oldpage.instock) {
				if (page.expectPirce >= page.price)
					ischanged = true;
			} else {
				if (oldpage.price > page.price
						&& page.expectPirce >= page.price)
					ischanged = true;
			}
		}
		if (ischanged) {
			if (page.mailaddress != null)
				sendmail(oldpage, page);
			if (page.weixin != null)
				sendweixin(oldpage, page);
		}
	}

	private void removeDeletePage() {
		Iterator<String> entryKeyIterator = pagelist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			AmazonPage page = pagelist.get(key);
			if (!existinDb(page)) {
				entryKeyIterator.remove();
				pagelist.remove(key);
			}
		}
	}

	private boolean existinDb(AmazonPage page) {

		for (int i = 0; i < maillist.size(); i++) {
			AmazonPage pageindb = maillist.get(i);
			if (page.mailaddress.equalsIgnoreCase(pageindb.mailaddress)
					&& page.url.equalsIgnoreCase(pageindb.url))
				return true;
		}
		return false;
	}
}

package kxg.searchaf.url.macys;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kxg.searchaf.util.ProxyUtli;

import com.mongodb.WriteResult;

public class MacysPageJSP {
	public List<MacysPage> loadAll() {
		try {
			MacysPageMongoDao dao = new MacysPageMongoDao();
			return dao.findAll();

		} catch (Exception e) {

		}
		return null;
	}

	public List<MacysPage> loadMail(String mailaddress) {
		try {
			MacysPageMongoDao dao = new MacysPageMongoDao();
			return dao.find(mailaddress);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public MacysPage loadMail(String mailaddress, String url) {
		try {
			MacysPageMongoDao dao = new MacysPageMongoDao();
			return dao.find(mailaddress, url);

		} catch (Exception e) {

		}
		return null;
	}

	public String updateMail(String mailaddress, String url, String expectPirce) {
		String ErrMsg = "";
		try {
			MacysPageMongoDao dao = new MacysPageMongoDao();

			MacysPage macysPage = dao.find(mailaddress, url);
			if (macysPage == null) {
				ErrMsg = "notexist";
			} else {

				macysPage.expectPirce = Float.parseFloat(expectPirce);

				dao.update(macysPage);
				ErrMsg = "success";
			}
		} catch (Exception e) {
		}
		return ErrMsg;
	}

	public String addNewMail(String mailaddress, String prodname, String url,
			String expectPirce) {
		// System.out.println("checksalerisMacys:" + checksalerisMacys);
		String returnkey = "";
		if (!isMacysUrl(url)) {
			return "notmacys";
		}
		try {
			MacysPageMongoDao dao = new MacysPageMongoDao();

			MacysPage amaildb = dao.find(mailaddress, url);
			if (amaildb != null) {
				returnkey = "exist";
			} else {
				MacysPage macysPage = new MacysPage();
				macysPage.mailaddress = mailaddress;
				macysPage.url = url;
				macysPage.prodname = prodname;
				macysPage.expectPirce = Float.parseFloat(expectPirce);
				// macysPage.checkType = "daohuo";
				// macysPage.checksalerisMacys = true;
				// macysPage.expectPirce = 0l;

				dao.save(macysPage);
				returnkey = "success";
			}
		} catch (Exception e) {
			returnkey = e.getMessage();
		}
		return returnkey;
	}

	private boolean isMacysUrl(String url) {
		if (url.indexOf("macys.com") > 0) {
			return true;
		}
		return false;
	}

	public String deleteMail(String mailaddress, String url) {
		String returnkey = "";
		try {
			MacysPageMongoDao dao = new MacysPageMongoDao();

			MacysPage amaildb = dao.find(mailaddress, url);
			if (amaildb == null) {
				returnkey = "notexist";
			} else {
				dao.delete(mailaddress, url);
			}
			returnkey = "success";
		} catch (Exception e) {
			returnkey = e.getMessage();
		}
		return returnkey;
	}

	public MacysPage testUrl(String url) throws Exception {
		if (!isMacysUrl(url)) {
			throw new Exception("当前只支持macys美国,中国,德国,日本,英国站,请输入正确的链接!");
		}
		MacysPage page = new MacysPage();
		page.url = url;

		ParserMacysPage parserMacysPage = new ParserMacysPage(page);
		parserMacysPage.checkprice();

		return page;
	}

	public void testAllUrlValid() throws Exception {
		// List<MacysPage> pagelist = loadAll();
		List<MacysPage> pagelist = loadMail("nymeki@163.com");

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				MacysPage apage = pagelist.get(i);
				MacysPage apage1 = testUrl(apage.url);
				if (apage1.price == 0)
					System.out.println(apage1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);
		MacysPageJSP jsp = new MacysPageJSP();

		List<MacysPage> pagelist = jsp.loadMail("xingang.af1@gmail.com");
		// List<MacysPage> pagelist = jsp.loadAll();

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				MacysPage apage = pagelist.get(i);
				// jsp.deleteMail(apage.mailaddress, apage.url);
				System.out.println(apage.mailaddress);
			}
		}

		// MacysPage page = jsp
		// .testUrl(
		// "http://www.macys.com/New-Balance-KV990-Hook-Loop/dp/B005Q5UPZY/ref=sr_1_2?s=shoes&ie=UTF8&qid=1382147309&sr=1-2&keywords=New+Balance+KV990",
		// true);
		// System.out.println(page);

		// jsp.testAllUrlValid();
	}
}

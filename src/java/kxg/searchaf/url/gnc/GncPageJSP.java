package kxg.searchaf.url.gnc;

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

public class GncPageJSP {
	public List<GncPage> loadAll() {
		try {
			GncPageMongoDao dao = new GncPageMongoDao();
			return dao.findAll();

		} catch (Exception e) {

		}
		return null;
	}

	public List<GncPage> loadMail(String mailaddress) {
		try {
			GncPageMongoDao dao = new GncPageMongoDao();
			return dao.find(mailaddress);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public GncPage loadMail(String mailaddress, String url) {
		try {
			GncPageMongoDao dao = new GncPageMongoDao();
			return dao.find(mailaddress, url);

		} catch (Exception e) {

		}
		return null;
	}

	public String updateMail(String mailaddress, String url, String expectPirce) {
		String ErrMsg = "";
		try {
			GncPageMongoDao dao = new GncPageMongoDao();

			GncPage gncPage = dao.find(mailaddress, url);
			if (gncPage == null) {
				ErrMsg = "notexist";
			} else {

				gncPage.expectPirce = Float.parseFloat(expectPirce);

				dao.update(gncPage);
				ErrMsg = "success";
			}
		} catch (Exception e) {
		}
		return ErrMsg;
	}

	public String addNewMail(String mailaddress, String prodname, String url,
			String expectPirce) {
		// System.out.println("checksalerisGnc:" + checksalerisGnc);
		String returnkey = "";
		if (!isGncUrl(url)) {
			return "notgnc";
		}
		try {
			GncPageMongoDao dao = new GncPageMongoDao();

			GncPage amaildb = dao.find(mailaddress, url);
			if (amaildb != null) {
				returnkey = "exist";
			} else {
				GncPage gncPage = new GncPage();
				gncPage.mailaddress = mailaddress;
				gncPage.url = url;
				gncPage.prodname = prodname;
				gncPage.expectPirce = Float.parseFloat(expectPirce);
				// gncPage.checkType = "daohuo";
				// gncPage.checksalerisGnc = true;
				// gncPage.expectPirce = 0l;

				dao.save(gncPage);
				returnkey = "success";
			}
		} catch (Exception e) {
			returnkey = e.getMessage();
		}
		return returnkey;
	}

	private boolean isGncUrl(String url) {
		if (url.startsWith("http://www.gnc.com")) {
			return true;
		}
		return false;
	}

	public String deleteMail(String mailaddress, String url) {
		String returnkey = "";
		try {
			GncPageMongoDao dao = new GncPageMongoDao();

			GncPage amaildb = dao.find(mailaddress, url);
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

	public GncPage testUrl(String url) throws Exception {
		if (!isGncUrl(url)) {
			throw new Exception("当前只支持gnc美国,中国,德国,日本,英国站,请输入正确的链接!");
		}
		GncPage page = new GncPage();
		page.url = url;

		ParserGncPage parserGncPage = new ParserGncPage(page);
		parserGncPage.checkprice();

		return page;
	}

	public void testAllUrlValid() throws Exception {
		// List<GncPage> pagelist = loadAll();
		List<GncPage> pagelist = loadMail("nymeki@163.com");

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				GncPage apage = pagelist.get(i);
				GncPage apage1 = testUrl(apage.url);
				if (apage1.price == 0)
					System.out.println(apage1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);
		GncPageJSP jsp = new GncPageJSP();

		 List<GncPage> pagelist = jsp.loadMail("xingang.af1@gmail.com");
//		List<GncPage> pagelist = jsp.loadAll();

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				GncPage apage = pagelist.get(i);
				// jsp.deleteMail(apage.mailaddress, apage.url);
				System.out.println(apage.mailaddress);
			}
		}

		// GncPage page = jsp
		// .testUrl(
		// "http://www.gnc.com/New-Balance-KV990-Hook-Loop/dp/B005Q5UPZY/ref=sr_1_2?s=shoes&ie=UTF8&qid=1382147309&sr=1-2&keywords=New+Balance+KV990",
		// true);
		// System.out.println(page);

		// jsp.testAllUrlValid();
	}
}

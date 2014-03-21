package kxg.searchaf.url.saks;

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

public class SaksPageJSP {
	public List<SaksPage> loadAll() {
		try {
			SaksPageMongoDao dao = new SaksPageMongoDao();
			return dao.findAll();

		} catch (Exception e) {

		}
		return null;
	}

	public List<SaksPage> loadMail(String mailaddress) {
		try {
			SaksPageMongoDao dao = new SaksPageMongoDao();
			return dao.find(mailaddress);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public SaksPage loadMail(String mailaddress, String url) {
		try {
			SaksPageMongoDao dao = new SaksPageMongoDao();
			return dao.find(mailaddress, url);

		} catch (Exception e) {

		}
		return null;
	}

	public String updateMail(String mailaddress, String url, String expectPirce) {
		String ErrMsg = "";
		try {
			SaksPageMongoDao dao = new SaksPageMongoDao();

			SaksPage saksPage = dao.find(mailaddress, url);
			if (saksPage == null) {
				ErrMsg = "notexist";
			} else {

				saksPage.expectPirce = Float.parseFloat(expectPirce);

				dao.update(saksPage);
				ErrMsg = "success";
			}
		} catch (Exception e) {
		}
		return ErrMsg;
	}

	public String addNewMail(String mailaddress, String prodname, String url,
			String expectPirce) {
		// System.out.println("checksalerisSaks:" + checksalerisSaks);
		String returnkey = "";
		if (!isSaksUrl(url)) {
			return "notsaks";
		}
		try {
			SaksPageMongoDao dao = new SaksPageMongoDao();

			SaksPage amaildb = dao.find(mailaddress, url);
			if (amaildb != null) {
				returnkey = "exist";
			} else {
				SaksPage saksPage = new SaksPage();
				saksPage.mailaddress = mailaddress;
				saksPage.url = url;
				saksPage.prodname = prodname;
				saksPage.expectPirce = Float.parseFloat(expectPirce);
				// saksPage.checkType = "daohuo";
				// saksPage.checksalerisSaks = true;
				// saksPage.expectPirce = 0l;

				dao.save(saksPage);
				returnkey = "success";
			}
		} catch (Exception e) {
			returnkey = e.getMessage();
		}
		return returnkey;
	}

	private boolean isSaksUrl(String url) {
		if (url.startsWith("http://www.saksfifthavenue.com")) {
			return true;
		}
		return false;
	}

	public String deleteMail(String mailaddress, String url) {
		String returnkey = "";
		try {
			SaksPageMongoDao dao = new SaksPageMongoDao();

			SaksPage amaildb = dao.find(mailaddress, url);
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

	public SaksPage testUrl(String url) throws Exception {
		if (!isSaksUrl(url)) {
			throw new Exception("当前只支持saks美国,中国,德国,日本,英国站,请输入正确的链接!");
		}
		SaksPage page = new SaksPage();
		page.url = url;

		ParserSaksPage parserSaksPage = new ParserSaksPage(page);
		parserSaksPage.checkprice();

		return page;
	}

	public void testAllUrlValid() throws Exception {
		// List<SaksPage> pagelist = loadAll();
		List<SaksPage> pagelist = loadMail("nymeki@163.com");

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				SaksPage apage = pagelist.get(i);
				SaksPage apage1 = testUrl(apage.url);
				if (apage1.price == 0)
					System.out.println(apage1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);
		SaksPageJSP jsp = new SaksPageJSP();

		 List<SaksPage> pagelist = jsp.loadMail("xingang.af1@gmail.com");
//		List<SaksPage> pagelist = jsp.loadAll();

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				SaksPage apage = pagelist.get(i);
				// jsp.deleteMail(apage.mailaddress, apage.url);
				System.out.println(apage.mailaddress);
			}
		}

		// SaksPage page = jsp
		// .testUrl(
		// "http://www.saksfifthavenue.com/New-Balance-KV990-Hook-Loop/dp/B005Q5UPZY/ref=sr_1_2?s=shoes&ie=UTF8&qid=1382147309&sr=1-2&keywords=New+Balance+KV990",
		// true);
		// System.out.println(page);

		// jsp.testAllUrlValid();
	}
}

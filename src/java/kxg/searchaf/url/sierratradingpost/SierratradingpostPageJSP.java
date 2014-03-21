package kxg.searchaf.url.sierratradingpost;

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

public class SierratradingpostPageJSP {
	public List<SierratradingpostPage> loadAll() {
		try {
			SierratradingpostPageMongoDao dao = new SierratradingpostPageMongoDao();
			return dao.findAll();

		} catch (Exception e) {

		}
		return null;
	}

	public List<SierratradingpostPage> loadMail(String mailaddress) {
		try {
			SierratradingpostPageMongoDao dao = new SierratradingpostPageMongoDao();
			return dao.find(mailaddress);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public SierratradingpostPage loadMail(String mailaddress, String url) {
		try {
			SierratradingpostPageMongoDao dao = new SierratradingpostPageMongoDao();
			return dao.find(mailaddress, url);

		} catch (Exception e) {

		}
		return null;
	}

	public String updateMail(String mailaddress, String url, String expectPirce) {
		String ErrMsg = "";
		try {
			SierratradingpostPageMongoDao dao = new SierratradingpostPageMongoDao();

			SierratradingpostPage sierratradingpostPage = dao.find(mailaddress, url);
			if (sierratradingpostPage == null) {
				ErrMsg = "notexist";
			} else {

				sierratradingpostPage.expectPirce = Float.parseFloat(expectPirce);

				dao.update(sierratradingpostPage);
				ErrMsg = "success";
			}
		} catch (Exception e) {
		}
		return ErrMsg;
	}

	public String addNewMail(String mailaddress, String prodname, String url,
			String expectPirce) {
		// System.out.println("checksalerisSierratradingpost:" + checksalerisSierratradingpost);
		String returnkey = "";
		if (!isSierratradingpostUrl(url)) {
			return "notsierratradingpost";
		}
		try {
			SierratradingpostPageMongoDao dao = new SierratradingpostPageMongoDao();

			SierratradingpostPage amaildb = dao.find(mailaddress, url);
			if (amaildb != null) {
				returnkey = "exist";
			} else {
				SierratradingpostPage sierratradingpostPage = new SierratradingpostPage();
				sierratradingpostPage.mailaddress = mailaddress;
				sierratradingpostPage.url = url;
				sierratradingpostPage.prodname = prodname;
				sierratradingpostPage.expectPirce = Float.parseFloat(expectPirce);
				// sierratradingpostPage.checkType = "daohuo";
				// sierratradingpostPage.checksalerisSierratradingpost = true;
				// sierratradingpostPage.expectPirce = 0l;

				dao.save(sierratradingpostPage);
				returnkey = "success";
			}
		} catch (Exception e) {
			returnkey = e.getMessage();
		}
		return returnkey;
	}

	private boolean isSierratradingpostUrl(String url) {
		if (url.startsWith("http://www.sierratradingpost.com")) {
			return true;
		}
		return false;
	}

	public String deleteMail(String mailaddress, String url) {
		String returnkey = "";
		try {
			SierratradingpostPageMongoDao dao = new SierratradingpostPageMongoDao();

			SierratradingpostPage amaildb = dao.find(mailaddress, url);
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

	public SierratradingpostPage testUrl(String url) throws Exception {
		if (!isSierratradingpostUrl(url)) {
			throw new Exception("当前只支持sierratradingpost美国,中国,德国,日本,英国站,请输入正确的链接!");
		}
		SierratradingpostPage page = new SierratradingpostPage();
		page.url = url;

		ParserSierratradingpostPage parserSierratradingpostPage = new ParserSierratradingpostPage(page);
		parserSierratradingpostPage.checkprice();

		return page;
	}

	public void testAllUrlValid() throws Exception {
		// List<SierratradingpostPage> pagelist = loadAll();
		List<SierratradingpostPage> pagelist = loadMail("nymeki@163.com");

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				SierratradingpostPage apage = pagelist.get(i);
				SierratradingpostPage apage1 = testUrl(apage.url);
				if (apage1.price == 0)
					System.out.println(apage1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);
		SierratradingpostPageJSP jsp = new SierratradingpostPageJSP();

		 List<SierratradingpostPage> pagelist = jsp.loadMail("xingang.af1@gmail.com");
//		List<SierratradingpostPage> pagelist = jsp.loadAll();

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				SierratradingpostPage apage = pagelist.get(i);
				// jsp.deleteMail(apage.mailaddress, apage.url);
				System.out.println(apage.mailaddress);
			}
		}

		// SierratradingpostPage page = jsp
		// .testUrl(
		// "http://www.sierratradingpost.com/New-Balance-KV990-Hook-Loop/dp/B005Q5UPZY/ref=sr_1_2?s=shoes&ie=UTF8&qid=1382147309&sr=1-2&keywords=New+Balance+KV990",
		// true);
		// System.out.println(page);

		// jsp.testAllUrlValid();
	}
}

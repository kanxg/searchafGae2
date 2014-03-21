package kxg.searchaf.url.sixpm;

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

public class SixpmPageJSP {
	public List<SixpmPage> loadAll() {
		try {
			SixpmPageMongoDao dao = new SixpmPageMongoDao();
			return dao.findAll();

		} catch (Exception e) {

		}
		return null;
	}

	public List<SixpmPage> loadMail(String mailaddress) {
		try {
			SixpmPageMongoDao dao = new SixpmPageMongoDao();
			return dao.find(mailaddress);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public SixpmPage loadMail(String mailaddress, String url) {
		try {
			SixpmPageMongoDao dao = new SixpmPageMongoDao();
			return dao.find(mailaddress, url);

		} catch (Exception e) {

		}
		return null;
	}

	public String updateMail(String mailaddress, String url, String expectPirce) {
		String ErrMsg = "";
		try {
			SixpmPageMongoDao dao = new SixpmPageMongoDao();

			SixpmPage sixpmPage = dao.find(mailaddress, url);
			if (sixpmPage == null) {
				ErrMsg = "notexist";
			} else {

				sixpmPage.expectPirce = Float.parseFloat(expectPirce);

				dao.update(sixpmPage);
				ErrMsg = "success";
			}
		} catch (Exception e) {
		}
		return ErrMsg;
	}

	public String addNewMail(String mailaddress, String prodname, String url,
			String expectPirce) {
		// System.out.println("checksalerisSixpm:" + checksalerisSixpm);
		String returnkey = "";
		if (!isSixpmUrl(url)) {
			return "notsixpm";
		}
		try {
			SixpmPageMongoDao dao = new SixpmPageMongoDao();

			SixpmPage amaildb = dao.find(mailaddress, url);
			if (amaildb != null) {
				returnkey = "exist";
			} else {
				SixpmPage sixpmPage = new SixpmPage();
				sixpmPage.mailaddress = mailaddress;
				sixpmPage.url = url;
				sixpmPage.prodname = prodname;
				sixpmPage.expectPirce = Float.parseFloat(expectPirce);
				// sixpmPage.checkType = "daohuo";
				// sixpmPage.checksalerisSixpm = true;
				// sixpmPage.expectPirce = 0l;

				dao.save(sixpmPage);
				returnkey = "success";
			}
		} catch (Exception e) {
			returnkey = e.getMessage();
		}
		return returnkey;
	}

	private boolean isSixpmUrl(String url) {
		if (url.startsWith("http://www.6pm.com")) {
			return true;
		}
		return false;
	}

	public String deleteMail(String mailaddress, String url) {
		String returnkey = "";
		try {
			SixpmPageMongoDao dao = new SixpmPageMongoDao();

			SixpmPage amaildb = dao.find(mailaddress, url);
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

	public SixpmPage testUrl(String url) throws Exception {
		if (!isSixpmUrl(url)) {
			throw new Exception("当前只支持sixpm美国,中国,德国,日本,英国站,请输入正确的链接!");
		}
		SixpmPage page = new SixpmPage();
		page.url = url;

		ParserSixpmPage parserSixpmPage = new ParserSixpmPage(page);
		parserSixpmPage.checkprice();

		return page;
	}

	public void testAllUrlValid() throws Exception {
		// List<SixpmPage> pagelist = loadAll();
		List<SixpmPage> pagelist = loadMail("nymeki@163.com");

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				SixpmPage apage = pagelist.get(i);
				SixpmPage apage1 = testUrl(apage.url);
				if (apage1.price == 0)
					System.out.println(apage1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);
		SixpmPageJSP jsp = new SixpmPageJSP();

		 List<SixpmPage> pagelist = jsp.loadMail("xingang.af1@gmail.com");
//		List<SixpmPage> pagelist = jsp.loadAll();

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				SixpmPage apage = pagelist.get(i);
				// jsp.deleteMail(apage.mailaddress, apage.url);
				System.out.println(apage.mailaddress);
			}
		}

		// SixpmPage page = jsp
		// .testUrl(
		// "http://www.6pm.com/New-Balance-KV990-Hook-Loop/dp/B005Q5UPZY/ref=sr_1_2?s=shoes&ie=UTF8&qid=1382147309&sr=1-2&keywords=New+Balance+KV990",
		// true);
		// System.out.println(page);

		// jsp.testAllUrlValid();
	}
}

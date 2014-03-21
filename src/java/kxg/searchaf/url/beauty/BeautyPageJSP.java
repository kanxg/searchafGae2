package kxg.searchaf.url.beauty;

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

public class BeautyPageJSP {
	public List<BeautyPage> loadAll() {
		try {
			BeautyPageMongoDao dao = new BeautyPageMongoDao();
			return dao.findAll();

		} catch (Exception e) {

		}
		return null;
	}

	public List<BeautyPage> loadMail(String mailaddress) {
		try {
			BeautyPageMongoDao dao = new BeautyPageMongoDao();
			return dao.find(mailaddress);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public BeautyPage loadMail(String mailaddress, String url) {
		try {
			BeautyPageMongoDao dao = new BeautyPageMongoDao();
			return dao.find(mailaddress, url);

		} catch (Exception e) {

		}
		return null;
	}

	public String updateMail(String mailaddress, String url, String expectPirce) {
		String ErrMsg = "";
		try {
			BeautyPageMongoDao dao = new BeautyPageMongoDao();

			BeautyPage beautyPage = dao.find(mailaddress, url);
			if (beautyPage == null) {
				ErrMsg = "notexist";
			} else {

				beautyPage.expectPirce = Float.parseFloat(expectPirce);

				dao.update(beautyPage);
				ErrMsg = "success";
			}
		} catch (Exception e) {
		}
		return ErrMsg;
	}

	public String addNewMail(String mailaddress, String prodname, String url,
			String expectPirce) {
		// System.out.println("checksalerisBeauty:" + checksalerisBeauty);
		String returnkey = "";
		if (!isBeautyUrl(url)) {
			return "notbeauty";
		}
		try {
			BeautyPageMongoDao dao = new BeautyPageMongoDao();

			BeautyPage amaildb = dao.find(mailaddress, url);
			if (amaildb != null) {
				returnkey = "exist";
			} else {
				BeautyPage beautyPage = new BeautyPage();
				beautyPage.mailaddress = mailaddress;
				beautyPage.url = url;
				beautyPage.prodname = prodname;
				beautyPage.expectPirce = Float.parseFloat(expectPirce);
				// beautyPage.checkType = "daohuo";
				// beautyPage.checksalerisBeauty = true;
				// beautyPage.expectPirce = 0l;

				dao.save(beautyPage);
				returnkey = "success";
			}
		} catch (Exception e) {
			returnkey = e.getMessage();
		}
		return returnkey;
	}

	private boolean isBeautyUrl(String url) {
		if (url.startsWith("http://www.beauty.com")) {
			return true;
		}
		return false;
	}

	public String deleteMail(String mailaddress, String url) {
		String returnkey = "";
		try {
			BeautyPageMongoDao dao = new BeautyPageMongoDao();

			BeautyPage amaildb = dao.find(mailaddress, url);
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

	public BeautyPage testUrl(String url) throws Exception {
		if (!isBeautyUrl(url)) {
			throw new Exception("当前只支持beauty美国,中国,德国,日本,英国站,请输入正确的链接!");
		}
		BeautyPage page = new BeautyPage();
		page.url = url;

		ParserBeautyPage parserBeautyPage = new ParserBeautyPage(page);
		parserBeautyPage.checkprice();

		return page;
	}

	public void testAllUrlValid() throws Exception {
		// List<BeautyPage> pagelist = loadAll();
		List<BeautyPage> pagelist = loadMail("nymeki@163.com");

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				BeautyPage apage = pagelist.get(i);
				BeautyPage apage1 = testUrl(apage.url);
				if (apage1.price == 0)
					System.out.println(apage1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);
		BeautyPageJSP jsp = new BeautyPageJSP();

		 List<BeautyPage> pagelist = jsp.loadMail("xingang.af1@gmail.com");
//		List<BeautyPage> pagelist = jsp.loadAll();

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				BeautyPage apage = pagelist.get(i);
				// jsp.deleteMail(apage.mailaddress, apage.url);
				System.out.println(apage.mailaddress);
			}
		}

		// BeautyPage page = jsp
		// .testUrl(
		// "http://www.beauty.com/New-Balance-KV990-Hook-Loop/dp/B005Q5UPZY/ref=sr_1_2?s=shoes&ie=UTF8&qid=1382147309&sr=1-2&keywords=New+Balance+KV990",
		// true);
		// System.out.println(page);

		// jsp.testAllUrlValid();
	}
}

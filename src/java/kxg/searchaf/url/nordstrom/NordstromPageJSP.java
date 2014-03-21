package kxg.searchaf.url.nordstrom;

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

public class NordstromPageJSP {
	public List<NordstromPage> loadAll() {
		try {
			NordstromPageMongoDao dao = new NordstromPageMongoDao();
			return dao.findAll();

		} catch (Exception e) {

		}
		return null;
	}

	public List<NordstromPage> loadMail(String mailaddress) {
		try {
			NordstromPageMongoDao dao = new NordstromPageMongoDao();
			return dao.find(mailaddress);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public NordstromPage loadMail(String mailaddress, String url) {
		try {
			NordstromPageMongoDao dao = new NordstromPageMongoDao();
			return dao.find(mailaddress, url);

		} catch (Exception e) {

		}
		return null;
	}

	public String updateMail(String mailaddress, String url, String expectPirce) {
		String ErrMsg = "";
		try {
			NordstromPageMongoDao dao = new NordstromPageMongoDao();

			NordstromPage nordstromPage = dao.find(mailaddress, url);
			if (nordstromPage == null) {
				ErrMsg = "notexist";
			} else {

				nordstromPage.expectPirce = Float.parseFloat(expectPirce);

				dao.update(nordstromPage);
				ErrMsg = "success";
			}
		} catch (Exception e) {
		}
		return ErrMsg;
	}

	public String addNewMail(String mailaddress, String prodname, String url,
			String expectPirce) {
		// System.out.println("checksalerisNordstrom:" + checksalerisNordstrom);
		String returnkey = "";
		if (!isNordstromUrl(url)) {
			return "notnordstrom";
		}
		try {
			NordstromPageMongoDao dao = new NordstromPageMongoDao();

			NordstromPage amaildb = dao.find(mailaddress, url);
			if (amaildb != null) {
				returnkey = "exist";
			} else {
				NordstromPage nordstromPage = new NordstromPage();
				nordstromPage.mailaddress = mailaddress;
				nordstromPage.url = url;
				nordstromPage.prodname = prodname;
				nordstromPage.expectPirce = Float.parseFloat(expectPirce);
				// nordstromPage.checkType = "daohuo";
				// nordstromPage.checksalerisNordstrom = true;
				// nordstromPage.expectPirce = 0l;

				dao.save(nordstromPage);
				returnkey = "success";
			}
		} catch (Exception e) {
			returnkey = e.getMessage();
		}
		return returnkey;
	}

	private boolean isNordstromUrl(String url) {
		if (url.indexOf("nordstrom.com") > 0) {
			return true;
		}
		return false;
	}

	public String deleteMail(String mailaddress, String url) {
		String returnkey = "";
		try {
			NordstromPageMongoDao dao = new NordstromPageMongoDao();

			NordstromPage amaildb = dao.find(mailaddress, url);
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

	public NordstromPage testUrl(String url) throws Exception {
		if (!isNordstromUrl(url)) {
			throw new Exception("当前只支持nordstrom美国,中国,德国,日本,英国站,请输入正确的链接!");
		}
		NordstromPage page = new NordstromPage();
		page.url = url;

		ParserNordstromPage parserNordstromPage = new ParserNordstromPage(page);
		parserNordstromPage.checkprice();

		return page;
	}

	public void testAllUrlValid() throws Exception {
		// List<NordstromPage> pagelist = loadAll();
		List<NordstromPage> pagelist = loadMail("nymeki@163.com");

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				NordstromPage apage = pagelist.get(i);
				NordstromPage apage1 = testUrl(apage.url);
				if (apage1.price == 0)
					System.out.println(apage1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);
		NordstromPageJSP jsp = new NordstromPageJSP();

		List<NordstromPage> pagelist = jsp.loadMail("xingang.af1@gmail.com");
		// List<NordstromPage> pagelist = jsp.loadAll();

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				NordstromPage apage = pagelist.get(i);
				// jsp.deleteMail(apage.mailaddress, apage.url);
				System.out.println(apage.mailaddress);
			}
		}

		// NordstromPage page = jsp
		// .testUrl(
		// "http://www.nordstrom.com/New-Balance-KV990-Hook-Loop/dp/B005Q5UPZY/ref=sr_1_2?s=shoes&ie=UTF8&qid=1382147309&sr=1-2&keywords=New+Balance+KV990",
		// true);
		// System.out.println(page);

		// jsp.testAllUrlValid();
	}
}

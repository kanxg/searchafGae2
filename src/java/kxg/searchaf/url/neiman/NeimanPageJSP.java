package kxg.searchaf.url.neiman;

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

public class NeimanPageJSP {
	public List<NeimanPage> loadAll() {
		try {
			NeimanPageMongoDao dao = new NeimanPageMongoDao();
			return dao.findAll();

		} catch (Exception e) {

		}
		return null;
	}

	public List<NeimanPage> loadMail(String mailaddress) {
		try {
			NeimanPageMongoDao dao = new NeimanPageMongoDao();
			return dao.find(mailaddress);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public NeimanPage loadMail(String mailaddress, String url) {
		try {
			NeimanPageMongoDao dao = new NeimanPageMongoDao();
			return dao.find(mailaddress, url);

		} catch (Exception e) {

		}
		return null;
	}

	public String updateMail(String mailaddress, String url, String expectPirce) {
		String ErrMsg = "";
		try {
			NeimanPageMongoDao dao = new NeimanPageMongoDao();

			NeimanPage neimanPage = dao.find(mailaddress, url);
			if (neimanPage == null) {
				ErrMsg = "notexist";
			} else {

				neimanPage.expectPirce = Float.parseFloat(expectPirce);

				dao.update(neimanPage);
				ErrMsg = "success";
			}
		} catch (Exception e) {
		}
		return ErrMsg;
	}

	public String addNewMail(String mailaddress, String prodname, String url,
			String expectPirce) {
		// System.out.println("checksalerisNeiman:" + checksalerisNeiman);
		String returnkey = "";
		if (!isNeimanUrl(url)) {
			return "notneiman";
		}
		try {
			NeimanPageMongoDao dao = new NeimanPageMongoDao();

			NeimanPage amaildb = dao.find(mailaddress, url);
			if (amaildb != null) {
				returnkey = "exist";
			} else {
				NeimanPage neimanPage = new NeimanPage();
				neimanPage.mailaddress = mailaddress;
				neimanPage.url = url;
				neimanPage.prodname = prodname;
				neimanPage.expectPirce = Float.parseFloat(expectPirce);
				// neimanPage.checkType = "daohuo";
				// neimanPage.checksalerisNeiman = true;
				// neimanPage.expectPirce = 0l;

				dao.save(neimanPage);
				returnkey = "success";
			}
		} catch (Exception e) {
			returnkey = e.getMessage();
		}
		return returnkey;
	}

	private boolean isNeimanUrl(String url) {
		if (url.startsWith("http://www.neimanmarcus.com")) {
			return true;
		}
		return false;
	}

	public String deleteMail(String mailaddress, String url) {
		String returnkey = "";
		try {
			NeimanPageMongoDao dao = new NeimanPageMongoDao();

			NeimanPage amaildb = dao.find(mailaddress, url);
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

	public NeimanPage testUrl(String url) throws Exception {
		if (!isNeimanUrl(url)) {
			throw new Exception("当前只支持neiman美国,中国,德国,日本,英国站,请输入正确的链接!");
		}
		NeimanPage page = new NeimanPage();
		page.url = url;

		ParserNeimanPage parserNeimanPage = new ParserNeimanPage(page);
		parserNeimanPage.checkprice();

		return page;
	}

	public void testAllUrlValid() throws Exception {
		// List<NeimanPage> pagelist = loadAll();
		List<NeimanPage> pagelist = loadMail("nymeki@163.com");

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				NeimanPage apage = pagelist.get(i);
				NeimanPage apage1 = testUrl(apage.url);
				if (apage1.price == 0)
					System.out.println(apage1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);
		NeimanPageJSP jsp = new NeimanPageJSP();

		 List<NeimanPage> pagelist = jsp.loadMail("xingang.af1@gmail.com");
//		List<NeimanPage> pagelist = jsp.loadAll();

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				NeimanPage apage = pagelist.get(i);
				// jsp.deleteMail(apage.mailaddress, apage.url);
				System.out.println(apage.mailaddress);
			}
		}

		// NeimanPage page = jsp
		// .testUrl(
		// "http://www.neimanmarcus.com/New-Balance-KV990-Hook-Loop/dp/B005Q5UPZY/ref=sr_1_2?s=shoes&ie=UTF8&qid=1382147309&sr=1-2&keywords=New+Balance+KV990",
		// true);
		// System.out.println(page);

		// jsp.testAllUrlValid();
	}
}

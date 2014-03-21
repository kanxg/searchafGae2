package kxg.searchaf.url.vitacost;

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

public class VitacostPageJSP {
	public List<VitacostPage> loadAll() {
		try {
			VitacostPageMongoDao dao = new VitacostPageMongoDao();
			return dao.findAll();

		} catch (Exception e) {

		}
		return null;
	}

	public List<VitacostPage> loadMail(String mailaddress) {
		try {
			VitacostPageMongoDao dao = new VitacostPageMongoDao();
			return dao.find(mailaddress);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public VitacostPage loadMail(String mailaddress, String url) {
		try {
			VitacostPageMongoDao dao = new VitacostPageMongoDao();
			return dao.find(mailaddress, url);

		} catch (Exception e) {

		}
		return null;
	}

	public String updateMail(String mailaddress, String url, String expectPirce) {
		String ErrMsg = "";
		try {
			VitacostPageMongoDao dao = new VitacostPageMongoDao();

			VitacostPage vitacostPage = dao.find(mailaddress, url);
			if (vitacostPage == null) {
				ErrMsg = "notexist";
			} else {

				vitacostPage.expectPirce = Float.parseFloat(expectPirce);

				dao.update(vitacostPage);
				ErrMsg = "success";
			}
		} catch (Exception e) {
		}
		return ErrMsg;
	}

	public String addNewMail(String mailaddress, String prodname, String url,
			String expectPirce) {
		// System.out.println("checksalerisVitacost:" + checksalerisVitacost);
		String returnkey = "";
		if (!isVitacostUrl(url)) {
			return "notvitacost";
		}
		try {
			VitacostPageMongoDao dao = new VitacostPageMongoDao();

			VitacostPage amaildb = dao.find(mailaddress, url);
			if (amaildb != null) {
				returnkey = "exist";
			} else {
				VitacostPage vitacostPage = new VitacostPage();
				vitacostPage.mailaddress = mailaddress;
				vitacostPage.url = url;
				vitacostPage.prodname = prodname;
				vitacostPage.expectPirce = Float.parseFloat(expectPirce);
				// vitacostPage.checkType = "daohuo";
				// vitacostPage.checksalerisVitacost = true;
				// vitacostPage.expectPirce = 0l;

				dao.save(vitacostPage);
				returnkey = "success";
			}
		} catch (Exception e) {
			returnkey = e.getMessage();
		}
		return returnkey;
	}

	private boolean isVitacostUrl(String url) {
		if (url.startsWith("http://www.vitacost.com")) {
			return true;
		}
		return false;
	}

	public String deleteMail(String mailaddress, String url) {
		String returnkey = "";
		try {
			VitacostPageMongoDao dao = new VitacostPageMongoDao();

			VitacostPage amaildb = dao.find(mailaddress, url);
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

	public VitacostPage testUrl(String url) throws Exception {
		if (!isVitacostUrl(url)) {
			throw new Exception("当前只支持vitacost美国,中国,德国,日本,英国站,请输入正确的链接!");
		}
		VitacostPage page = new VitacostPage();
		page.url = url;

		ParserVitacostPage parserVitacostPage = new ParserVitacostPage(page);
		parserVitacostPage.checkprice();

		return page;
	}

	public void testAllUrlValid() throws Exception {
		// List<VitacostPage> pagelist = loadAll();
		List<VitacostPage> pagelist = loadMail("nymeki@163.com");

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				VitacostPage apage = pagelist.get(i);
				VitacostPage apage1 = testUrl(apage.url);
				if (apage1.price == 0)
					System.out.println(apage1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);
		VitacostPageJSP jsp = new VitacostPageJSP();

		 List<VitacostPage> pagelist = jsp.loadMail("xingang.af1@gmail.com");
//		List<VitacostPage> pagelist = jsp.loadAll();

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				VitacostPage apage = pagelist.get(i);
				// jsp.deleteMail(apage.mailaddress, apage.url);
				System.out.println(apage.mailaddress);
			}
		}

		// VitacostPage page = jsp
		// .testUrl(
		// "http://www.vitacost.com/New-Balance-KV990-Hook-Loop/dp/B005Q5UPZY/ref=sr_1_2?s=shoes&ie=UTF8&qid=1382147309&sr=1-2&keywords=New+Balance+KV990",
		// true);
		// System.out.println(page);

		// jsp.testAllUrlValid();
	}
}

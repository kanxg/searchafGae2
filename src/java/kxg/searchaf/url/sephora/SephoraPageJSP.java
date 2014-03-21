package kxg.searchaf.url.sephora;

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

public class SephoraPageJSP {
	public List<SephoraPage> loadAll() {
		try {
			SephoraPageMongoDao dao = new SephoraPageMongoDao();
			return dao.findAll();

		} catch (Exception e) {

		}
		return null;
	}

	public List<SephoraPage> loadMail(String mailaddress) {
		try {
			SephoraPageMongoDao dao = new SephoraPageMongoDao();
			return dao.find(mailaddress);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public SephoraPage loadMail(String mailaddress, String url) {
		try {
			SephoraPageMongoDao dao = new SephoraPageMongoDao();
			return dao.find(mailaddress, url);

		} catch (Exception e) {

		}
		return null;
	}

	public String updateMail(String mailaddress, String url, String expectPirce) {
		String ErrMsg = "";
		try {
			SephoraPageMongoDao dao = new SephoraPageMongoDao();

			SephoraPage sephoraPage = dao.find(mailaddress, url);
			if (sephoraPage == null) {
				ErrMsg = "notexist";
			} else {

				sephoraPage.expectPirce = Float.parseFloat(expectPirce);

				dao.update(sephoraPage);
				ErrMsg = "success";
			}
		} catch (Exception e) {
		}
		return ErrMsg;
	}

	public String addNewMail(String mailaddress, String prodname, String url,
			String expectPirce) {
		// System.out.println("checksalerisSephora:" + checksalerisSephora);
		String returnkey = "";
		if (!isSephoraUrl(url)) {
			return "notsephora";
		}
		try {
			SephoraPageMongoDao dao = new SephoraPageMongoDao();

			SephoraPage amaildb = dao.find(mailaddress, url);
			if (amaildb != null) {
				returnkey = "exist";
			} else {
				SephoraPage sephoraPage = new SephoraPage();
				sephoraPage.mailaddress = mailaddress;
				sephoraPage.url = url;
				sephoraPage.prodname = prodname;
				sephoraPage.expectPirce = Float.parseFloat(expectPirce);
				// sephoraPage.checkType = "daohuo";
				// sephoraPage.checksalerisSephora = true;
				// sephoraPage.expectPirce = 0l;

				dao.save(sephoraPage);
				returnkey = "success";
			}
		} catch (Exception e) {
			returnkey = e.getMessage();
		}
		return returnkey;
	}

	private boolean isSephoraUrl(String url) {
		if (url.startsWith("http://www.sephora.com")) {
			return true;
		}
		return false;
	}

	public String deleteMail(String mailaddress, String url) {
		String returnkey = "";
		try {
			SephoraPageMongoDao dao = new SephoraPageMongoDao();

			SephoraPage amaildb = dao.find(mailaddress, url);
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

	public SephoraPage testUrl(String url) throws Exception {
		if (!isSephoraUrl(url)) {
			throw new Exception("当前只支持sephora美国,中国,德国,日本,英国站,请输入正确的链接!");
		}
		SephoraPage page = new SephoraPage();
		page.url = url;

		ParserSephoraPage parserSephoraPage = new ParserSephoraPage(page);
		parserSephoraPage.checkprice();

		return page;
	}

	public void testAllUrlValid() throws Exception {
		// List<SephoraPage> pagelist = loadAll();
		List<SephoraPage> pagelist = loadMail("nymeki@163.com");

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				SephoraPage apage = pagelist.get(i);
				SephoraPage apage1 = testUrl(apage.url);
				if (apage1.price == 0)
					System.out.println(apage1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);
		SephoraPageJSP jsp = new SephoraPageJSP();

		 List<SephoraPage> pagelist = jsp.loadMail("xingang.af1@gmail.com");
//		List<SephoraPage> pagelist = jsp.loadAll();

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				SephoraPage apage = pagelist.get(i);
				// jsp.deleteMail(apage.mailaddress, apage.url);
				System.out.println(apage.mailaddress);
			}
		}

		// SephoraPage page = jsp
		// .testUrl(
		// "http://www.sephora.com/New-Balance-KV990-Hook-Loop/dp/B005Q5UPZY/ref=sr_1_2?s=shoes&ie=UTF8&qid=1382147309&sr=1-2&keywords=New+Balance+KV990",
		// true);
		// System.out.println(page);

		// jsp.testAllUrlValid();
	}
}

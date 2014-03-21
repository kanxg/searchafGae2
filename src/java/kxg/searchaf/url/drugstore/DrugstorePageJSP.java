	package kxg.searchaf.url.drugstore;

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

public class DrugstorePageJSP {
	public List<DrugstorePage> loadAll() {
		try {
			DrugstorePageMongoDao dao = new DrugstorePageMongoDao();
			return dao.findAll();

		} catch (Exception e) {

		}
		return null;
	}

	public List<DrugstorePage> loadMail(String mailaddress) {
		System.out.println("mailaddress is"+mailaddress);
		try {
			DrugstorePageMongoDao dao = new DrugstorePageMongoDao();
			return dao.find(mailaddress);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public DrugstorePage loadMail(String mailaddress, String url) {
		try {
			DrugstorePageMongoDao dao = new DrugstorePageMongoDao();
			return dao.find(mailaddress, url);

		} catch (Exception e) {

		}
		return null;
	}

	public String updateMail(String mailaddress, String url, String expectPirce) {
		String ErrMsg = "";
		try {
			DrugstorePageMongoDao dao = new DrugstorePageMongoDao();

			DrugstorePage drugstorePage = dao.find(mailaddress, url);
			if (drugstorePage == null) {
				ErrMsg = "notexist";
			} else {

				drugstorePage.expectPirce = Float.parseFloat(expectPirce);

				dao.update(drugstorePage);
				ErrMsg = "success";
			}
		} catch (Exception e) {
		}
		return ErrMsg;
	}

	public String addNewMail(String mailaddress, String prodname, String url,
			String expectPirce) {
		// System.out.println("checksalerisDrugstore:" + checksalerisDrugstore);
		String returnkey = "";
		if (!isDrugstoreUrl(url)) {
			return "notdrugstore";
		}
		try {
			DrugstorePageMongoDao dao = new DrugstorePageMongoDao();

			DrugstorePage amaildb = dao.find(mailaddress, url);
			if (amaildb != null) {
				returnkey = "exist";
			} else {
				DrugstorePage drugstorePage = new DrugstorePage();
				drugstorePage.mailaddress = mailaddress;
				drugstorePage.url = url;
				drugstorePage.prodname = prodname;
				drugstorePage.expectPirce = Float.parseFloat(expectPirce);
				// drugstorePage.checkType = "daohuo";
				// drugstorePage.checksalerisDrugstore = true;
				// drugstorePage.expectPirce = 0l;

				dao.save(drugstorePage);
				returnkey = "success";
			}
		} catch (Exception e) {
			returnkey = e.getMessage();
		}
		return returnkey;
	}

	private boolean isDrugstoreUrl(String url) {
		if (url.startsWith("http://www.drugstore.com")) {
			return true;
		}
		return false;
	}

	public String deleteMail(String mailaddress, String url) {
		String returnkey = "";
		try {
			DrugstorePageMongoDao dao = new DrugstorePageMongoDao();

			DrugstorePage amaildb = dao.find(mailaddress, url);
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

	public DrugstorePage testUrl(String url) throws Exception {
		if (!isDrugstoreUrl(url)) {
			throw new Exception("当前只支持drugstore美国,中国,德国,日本,英国站,请输入正确的链接!");
		}
		DrugstorePage page = new DrugstorePage();
		page.url = url;

		ParserDrugstorePage parserDrugstorePage = new ParserDrugstorePage(page);
		parserDrugstorePage.checkprice();

		return page;
	}

	public void testAllUrlValid() throws Exception {
		// List<DrugstorePage> pagelist = loadAll();
		List<DrugstorePage> pagelist = loadMail("nymeki@163.com");

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				DrugstorePage apage = pagelist.get(i);
				DrugstorePage apage1 = testUrl(apage.url);
				if (apage1.price == 0)
					System.out.println(apage1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);
		DrugstorePageJSP jsp = new DrugstorePageJSP();

		 List<DrugstorePage> pagelist = jsp.loadMail("xingang.af1@gmail.com");
//		List<DrugstorePage> pagelist = jsp.loadAll();

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				DrugstorePage apage = pagelist.get(i);
				// jsp.deleteMail(apage.mailaddress, apage.url);
				System.out.println(apage.mailaddress);
			}
		}

		// DrugstorePage page = jsp
		// .testUrl(
		// "http://www.drugstore.com/New-Balance-KV990-Hook-Loop/dp/B005Q5UPZY/ref=sr_1_2?s=shoes&ie=UTF8&qid=1382147309&sr=1-2&keywords=New+Balance+KV990",
		// true);
		// System.out.println(page);

		// jsp.testAllUrlValid();
	}
}

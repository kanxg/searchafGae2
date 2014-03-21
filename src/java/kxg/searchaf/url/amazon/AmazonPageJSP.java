package kxg.searchaf.url.amazon;

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

public class AmazonPageJSP {
	public List<AmazonPage> loadAll() {
		try {
			AmazonPageMongoDao dao = new AmazonPageMongoDao();
			return dao.findAll();

		} catch (Exception e) {

		}
		return null;
	}

	public List<AmazonPage> loadMail(String mailaddress) {
		try {
			AmazonPageMongoDao dao = new AmazonPageMongoDao();
			return dao.find(mailaddress);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public AmazonPage loadMail(String mailaddress, String url) {
		try {
			AmazonPageMongoDao dao = new AmazonPageMongoDao();
			return dao.find(mailaddress, url);

		} catch (Exception e) {

		}
		return null;
	}

	public String updateMail(String mailaddress, String url,
			String expectPirce, String checkType, boolean checksalerisAmazon) {
		String ErrMsg = "";
		try {
			AmazonPageMongoDao dao = new AmazonPageMongoDao();

			AmazonPage amazonPage = dao.find(mailaddress, url);
			if (amazonPage == null) {
				ErrMsg = "notexist";
			} else {
				amazonPage.checkType = checkType;
				amazonPage.checksalerisAmazon = checksalerisAmazon;

				amazonPage.expectPirce = Float.parseFloat(expectPirce);

				dao.update(amazonPage);
				ErrMsg = "success";
			}
		} catch (Exception e) {
		}
		return ErrMsg;
	}

	public String addNewMail(String mailaddress, String prodname, String url,
			String expectPirce, boolean checksalerisAmazon) {
		 System.out.println("mailaddress:" + mailaddress);
		String returnkey = "";
		if (!isAmazonUrl(url)) {
			return "notamazon";
		}
		try {
			AmazonPageMongoDao dao = new AmazonPageMongoDao();

			AmazonPage amaildb = dao.find(mailaddress, url);
			if (amaildb != null) {
				returnkey = "exist";
			} else {
				AmazonPage amazonPage = new AmazonPage();
				amazonPage.mailaddress = mailaddress;
				amazonPage.url = url;
				amazonPage.prodname = prodname;
				amazonPage.checksalerisAmazon = checksalerisAmazon;
				amazonPage.expectPirce = Float.parseFloat(expectPirce);
				// amazonPage.checkType = "daohuo";
				// amazonPage.checksalerisAmazon = true;
				// amazonPage.expectPirce = 0l;

				dao.save(amazonPage);
				returnkey = "success";
			}
		} catch (Exception e) {
			returnkey = e.getMessage();
		}
		return returnkey;
	}

	private boolean isAmazonUrl(String url) {
		if (url.startsWith("http://www.amazon.com")) {
			return true;
		} else if (url.startsWith("http://www.amazon.cn")) {
			return true;
		} else if (url.startsWith("http://www.amazon.co.jp")) {
			return true;
		} else if (url.startsWith("http://www.amazon.co.uk")) {
			return true;
		} else if (url.startsWith("http://www.amazon.de")) {
			return true;
		}
		return false;
	}

	public String deleteMail(String mailaddress, String url) {
		
		String returnkey = "";
		try {
			AmazonPageMongoDao dao = new AmazonPageMongoDao();
			AmazonPage amaildb = dao.find(mailaddress, url);
			if (amaildb == null) {
				returnkey = "notexist";
				System.out.println("notexist");
			} else {
				System.out.println(mailaddress);
				System.out.println(url);
				dao.delete(mailaddress, url);
			}
			returnkey = "success";
		} catch (Exception e) {
			returnkey = e.getMessage();
		}
		return returnkey;
	}

	public AmazonPage testUrl(String url, boolean checksalerisAmazon)
			throws Exception {
		ProxyUtli.setProxy(true);
		if (!isAmazonUrl(url)) {
			throw new Exception("当前只支持amazon美国,中国,德国,日本,英国站,请输入正确的链接!");
		}
		AmazonPage page = new AmazonPage();
		page.url = url;
		page.checksalerisAmazon = checksalerisAmazon;
		if (page.url.indexOf("amazon.cn") > 0) {
			ParserAmazonCnPage parserAmazonPage = new ParserAmazonCnPage(page);
			parserAmazonPage.checkprice();
		} else if (page.url.indexOf("amazon.de") > 0) {
			ParserAmazonDePage parserAmazonPage = new ParserAmazonDePage(page);
			parserAmazonPage.checkprice();
		} else if (page.url.indexOf("amazon.co.jp") > 0) {
			ParserAmazonJpPage parserAmazonPage = new ParserAmazonJpPage(page);
			parserAmazonPage.checkprice();
		} else if (page.url.indexOf("amazon.co.uk") > 0) {
			ParserAmazonUkPage parserAmazonPage = new ParserAmazonUkPage(page);
			parserAmazonPage.checkprice();
		} else {
			ParserAmazonPage parserAmazonPage = new ParserAmazonPage(page);
			parserAmazonPage.checkprice();
		}
		return page;
	}

	public void testAllUrlValid() throws Exception {
		// List<AmazonPage> pagelist = loadAll();
		List<AmazonPage> pagelist = loadMail("nymeki@163.com");

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				AmazonPage apage = pagelist.get(i);
				AmazonPage apage1 = testUrl(apage.url, apage.checksalerisAmazon);
				if (apage1.price == 0)
					System.out.println(apage1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		//ProxyUtli.setProxy(true);
		AmazonPageJSP jsp = new AmazonPageJSP();

		List<AmazonPage> pagelist = jsp.loadMail("1");
		// List<AmazonPage> pagelist = jsp.loadAll();

		if (pagelist != null) {
			for (int i = 0; i < pagelist.size(); i++) {
				AmazonPage apage = pagelist.get(i);
				// jsp.deleteMail(apage.mailaddress, apage.url);
				System.out.println(apage);
			}
		}

		 AmazonPage page = jsp
		 .loadMail("1",
		 "http://www.amazon.cn/gp/bestsellers/grocery/ref=zg_bs_grocery_pg_2?ie=UTF8&pg=1"
		 );
		 System.out.println(page);

		// jsp.testAllUrlValid();
	}
}

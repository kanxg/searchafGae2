package kxg.searchaf.url.af.afsecretlink;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import kxg.searchaf.mail.Mail;
import kxg.searchaf.url.Constant;
import kxg.searchaf.url.af.AfConstant;
import kxg.searchaf.url.af.AfMailList;
import kxg.searchaf.url.af.AfPage;
import kxg.searchaf.url.af.AfProduct;
import kxg.searchaf.url.af.ParserAfPage;
import kxg.searchaf.util.ProxyUtli;

public class SearchAfSecret {
	static final int[] otherprod = new int[] { 75751, 711979, 1053483, 272918,
			1054469, 709482, 856026, 856028, 856024, 855485 };

	static final String proURL = "http://www.abercrombie.com/webapp/wcs/stores/servlet/ProductDisplay?catalogId=10901&storeId=10051&langId=-1&productId=";

	public HashMap<Integer, AfProduct> allprolist = new HashMap<Integer, AfProduct>();

	public ArrayList<AfProduct> matchprolist = new ArrayList<AfProduct>();

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
 		ProxyUtli.setProxy(true);

		// while (true) {
		SearchAfSecret af = new SearchAfSecret();
		// System.out.println("start checking...");
		af.searchsite();
		// System.out.println(new Date() + "sleeping...");
		// Thread.sleep(1000 * 60 * Constant.sleeptime); // sleep 10 mines
		// }
	}

	public void searchsite() {
		//getAllproduct();
		searchSecretLink();
		// sendmail(matchprolist);

	}

	public void getAllproduct() {
		try {
			List<AfPage> urllist = AfPage.getMenAllInstance();
			for (int i = 0; i < urllist.size(); i++) {
				AfPage afpage = urllist.get(i);
				ParserAfPage parserAfpage = new ParserAfPage(afpage, allprolist);
				parserAfpage.checkprice();
			}
			urllist = AfPage.getMenclearanceBySinglePageInstance();
			for (int i = 0; i < urllist.size(); i++) {
				AfPage afpage = urllist.get(i);
				ParserAfPage parserAfpage = new ParserAfPage(afpage, allprolist);
				parserAfpage.checkprice();
			}
			urllist = AfPage.getMenSaleBySinglePageInstance();
			for (int i = 0; i < urllist.size(); i++) {
				AfPage afpage = urllist.get(i);
				ParserAfPage parserAfpage = new ParserAfPage(afpage, allprolist);
				parserAfpage.checkprice();
			}
			urllist = AfPage.getMenSecretInstance();
			for (int i = 0; i < urllist.size(); i++) {
				AfPage afpage = urllist.get(i);
				ParserAfPage parserAfpage = new ParserAfPage(afpage, allprolist);
				parserAfpage.checkprice();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("found all of products:" + allprolist.size());
	}

	private void searchSecretLink() {
		for (int i = 0; i < 10; i++) {
			String url1 = "http://www.abercrombie.com/webapp/wcs/stores/servlet/Search?storeId=10051&catalogId=10901&langId=-1&qs=?i=1;page=";
			String url2 = ";q=";
			String url3 = "*;q1=Mens;storeId=10051;x1=category-1";
			int j = 1;
			while (j < 10) {
				HashMap<Integer, AfProduct> tempsearchprolist = new HashMap<Integer, AfProduct>();
				String url = url1 + j + url2 + i + url3;
				ParserAfproductExist parserAfpage = new ParserAfproductExist(
						url, tempsearchprolist);
				parserAfpage.foundproduct();
				if (tempsearchprolist.size() == 0)
					break;
				else {
					Iterator<Integer> entryKeyIterator = tempsearchprolist
							.keySet().iterator();
					while (entryKeyIterator.hasNext()) {
						Integer key = entryKeyIterator.next();
						AfProduct product = tempsearchprolist.get(key);
						if (!isCologneProd(product)
								&& allprolist.get(product.productid) == null) {
							System.out.println("found product, url:" + proURL
									+ product.productid);
							allprolist.put(product.productid, product);
							matchprolist.add(product);
						}
					}
				}
				j = j + 1;
			}
		}
		System.out.println("found secret products:" + matchprolist.size());
	}

	private void sendmail(ArrayList<AfProduct> matchprolist) {
		if (matchprolist.size() > 0) {
			Mail m = new Mail();
			for (int i = 0; i < matchprolist.size(); i++) {
				AfProduct matchpro = matchprolist.get(i);
				// m.mail_body = m.mail_body + matchpro + "<br/>";
				// attach html file
				m.tagcontext.append(matchpro.tagcontext);
			}
			m.mail_to = AfMailList.mailto_men;
			m.mail_subject = m.mail_subject + " secret product";
			// Date currentDate = new Date();
			// List<AfMailList> afmailist = AfMailList.getinstance();
			// List<String> cc_to = new ArrayList<String>();
			// for (int i = 0; i < afmailist.size(); i++) {
			// AfMailList amail = afmailist.get(i);
			// if (amail.valideTime.after(currentDate)) {
			// cc_to.add(amail.mailaddress);
			// }
			// }
			// m.cc_to = cc_to;
			try {
				m.sendMail();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private boolean isCologneProd(AfProduct product) {
		for (int i = 0; i < otherprod.length; i++) {
			if (otherprod[i] == product.productid)
				return true;
		}
		return false;
	}

}

package kxg.searchaf.url.af.parseAfcode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

import kxg.searchaf.url.Constant;
import kxg.searchaf.url.af.AfProduct;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.StringFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeList;

public class perseAfcode {

	public static void main(String[] args) throws Exception {

		// new perseAfcode().test();
		long f = 15552l;
		long currentf;
		for (int i = 1; i < 10000; i++) {
			currentf = f + i;
			System.out.println("checking code:" + currentf);
			new perseAfcode().checkpromode(currentf);
		}
	}

	public void checkpromode(long currentf) {

		String urlString = "https://www.abercrombie.com/webapp/wcs/stores/servlet/PromotionCodeManage?storeId=10051&langId=-1&orderId=251732066&catalogId=10901&taskType=A&updatePrices=1&calculationUsageId=-1&URL=OrderCalculate?URL=PromoCodeApplyView&errorViewName=PromoCodeApplyView&promoCode=";
		urlString = urlString + String.valueOf(currentf);
		try {

			URL url = new URL(urlString);

			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			urlConnection.setConnectTimeout(Constant.connect_timeout);
			
			Parser parser = new Parser(urlConnection);

			NodeList nl = parser.parse(null);
			NodeList trs = nl.extractAllNodesThatMatch(new TagNameFilter("li"));
			if (trs.size() == 0) {
				System.out.println("code useful:" + currentf);
			}
			// for (int i = 0; i < trs.size(); i++) {
			// NodeList nodes = trs.elementAt(i).getChildren();
			// NodeList tds = nodes.extractAllNodesThatMatch(
			// new TagNameFilter("li"), true);
			// System.out.println(tds);
			// }

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testHtml() {
		// File inputHTML = new File("test.html");
		// Parser parser = new Parser(inputHTML);
		// parser.setInputHTML(inputHTML);
		// parser.setEncoding("UTF-8");
		// NodeList nl = parser.parse(null);
		// NodeList trs = nl.extractAllNodesThatMatch(new TagNameFilter("tr"),
		// true);
		// for (int i = 0; i < trs.size(); i++) {
		// NodeList nodes = trs.elementAt(i).getChildren();
		// NodeList tds = nodes.extractAllNodesThatMatch(new TagNameFilter(
		// "td"), true);
		// // Do stuff with tds
		// }
	}

	private final static String CRLF = System.getProperty("line.separator");

	public void test() {
		try {
			URL ur = new URL(
					"https://www.abercrombie.com/webapp/wcs/stores/servlet/PromotionCodeManage?storeId=10051&langId=-1&orderId=240511062&catalogId=10901&taskType=A&updatePrices=1&calculationUsageId=-1&URL=OrderCalculate?URL=PromoCodeApplyView&errorViewName=PromoCodeApplyView&promoCode=8075776406168357");
			InputStream instr = ur.openStream();
			String s, str;
			BufferedReader in = new BufferedReader(new InputStreamReader(instr));
			StringBuffer sb = new StringBuffer();
			BufferedWriter out = new BufferedWriter(
					new FileWriter("outPut.txt"));
			while ((s = in.readLine()) != null) {
				sb.append(s + CRLF);
			}
			System.out.println(sb);
			str = new String(sb);
			out.write(str);
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

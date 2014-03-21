package kxg.searchaf.url.amazon;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import kxg.searchaf.url.Constant;
import kxg.searchaf.url.amazon.ParserAmazonJpPage;
import kxg.searchaf.url.amazon.AmazonPage;
import kxg.searchaf.util.ProxyUtli;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.InputTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

public class ParserAmazonJpPage {
	public AmazonPage page;

	public ParserAmazonJpPage(AmazonPage page) {
		this.page = page;
	};

	public void checkprice() throws Exception {
		// System.out.println("checking amazon url:" + page.getUrl());

		String cookies = ""; // AmazonLogin.getCookies();

		URL url = new URL(page.getUrl());
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		urlConnection.setConnectTimeout(Constant.connect_timeout);
		urlConnection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (compatible; MSIE 6.0; Windows NT)");
		urlConnection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");

		urlConnection.setRequestProperty("Cookie", cookies);

		urlConnection.connect();
		// InputStream is = urlConnection.getInputStream();
		//
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(is));
		//
		// String s;
		// StringBuilder result = new StringBuilder();
		// while (((s = reader.readLine()) != null)) {
		// result.append(s);
		// }
		//
		// System.out.println("result= " + result.toString());
		//
		// is.close();

		Parser parser = new Parser(urlConnection);

		// parser.setEncoding(Constant.ENCODE);

		NodeFilter name_filter = new AndFilter(new NodeClassFilter(Span.class),
				new HasAttributeFilter("class", "availGreen"));

		NodeFilter name_filter2 = new AndFilter(new NodeClassFilter(
				TableTag.class), new HasAttributeFilter("class", "product"));

		NodeFilter name_filter3 = new AndFilter(new NodeClassFilter(Div.class),
				new HasAttributeFilter("class", "product"));

		NodeFilter name_filter5 = new AndFilter(new NodeClassFilter(
				InputTag.class), new HasAttributeFilter("id", "ASIN"));
		OrFilter lastFilter = new OrFilter();
		lastFilter
				.setPredicates(new NodeFilter[] {
						new NodeClassFilter(TitleTag.class), name_filter,
						name_filter2,name_filter5 });

		NodeList list = parser.extractAllNodesThatMatch(lastFilter);
		for (int i = 0; i < list.size(); i++) {
			Node tag = list.elementAt(i);
			if (tag instanceof TitleTag) {
				TitleTag d = (TitleTag) tag;
				page.title = d.getTitle().replaceAll("Amazon.co.jp：", "")
						.trim();
			} else if (tag instanceof TableTag) {
				TableTag d = (TableTag) tag;
				// found one product
				page.price = getPrice(d);
			} else if (tag instanceof Span) {
				Span sp = (Span) tag;
				Node p = sp.getParent();
				if (p instanceof Div) {
					Div psp = (Div) p;
					String sStr = psp.getStringText();
					if (sStr.indexOf("在庫あり") > 0) {
						page.instock = true;
					}
					if (sStr.indexOf("Amazon.co.jp") > 0) {
						page.salerisAmazon = true;
					}
					// System.out.println();

				}

			} else if (tag instanceof Div) {
				Div d = (Div) tag;
				// System.out.println(d.getStringText());
				// getinStock(d);
			}else if (tag instanceof InputTag) {
				InputTag d = (InputTag) tag;
				String dname = d.getAttribute("value");
				page.ASIN = dname;
			}
		}

	}

	private String processHTML(Node node) {
		String html = node.toHtml();
		// String html = node.getChildren().elementAt(3).toHtml();
		// html = html + node.getChildren().elementAt(5).toHtml();
		// html = html + node.getChildren().elementAt(9).toHtml();
		// html = html
		// .replaceAll("ProductDisplay",
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/ProductDisplay");
		// html = html.replace("//anf", "http://anf");
		return html;
	}

	private void getinStock(Node node) throws Exception {
		NodeList childList = node.getChildren();
		List<String> productvalue = new ArrayList<String>();
		processNodeList(childList, productvalue);
		System.out.println(productvalue);
	}

	private float getPrice(Node node) throws Exception {
		NodeList childList = node.getChildren();
		List<String> productvalue = new ArrayList<String>();
		processNodeList(childList, productvalue);
		// System.out.println(productvalue);
		String price = "0";
		for (int i = 0; i < productvalue.size(); i++) {
			String tmp = productvalue.get(i);
			if (tmp.indexOf("価格") == 0) {
				price = productvalue.get(i + 1);
				break;
			}
		}
		return getprice(price);
	}

	private float getprice(String priceStr) throws Exception {
		priceStr = priceStr.replaceAll("￥", "").replaceAll(",", "").trim();
//		System.out.println("priceStr:" + priceStr);
		float returnvalue = 0;
		try {
			// listprice= $80
			returnvalue = Float.parseFloat(priceStr);
		} catch (NumberFormatException ex) {
			// listprice= $80-$90
			returnvalue = Float.parseFloat(priceStr.substring(0,
					priceStr.indexOf("-")));
		}
		return returnvalue;
	}

	private void processNodeList(NodeList list, List<String> valueList) {
		// 迭代开始
		SimpleNodeIterator iterator = list.elements();
		while (iterator.hasMoreNodes()) {
			Node node = iterator.nextNode();
			// 得到该节点的子节点列表
			NodeList childList = node.getChildren();
			// 孩子节点为空，说明是值节点
			if (null == childList) {
				// 得到值节点的值
				String result = node.toPlainTextString().trim();
				// 若包含关键字，则简单打印出来文本
				// System.out.println(result);
				if (result != null && !"".equals(result))
					valueList.add(result);
			} // end if
				// 孩子节点不为空，继续迭代该孩子节点
			else {
				processNodeList(childList, valueList);
			}// end else
		}// end wile
	}

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);
		AmazonPage amazonPage = new AmazonPage();
		amazonPage.checksalerisAmazon = true;
		amazonPage.url = "http://www.amazon.co.jp/gp/switch-language/product/B001GQ2FCI/ref=topnav_switchLang?ie=UTF8&language=ja_JP";
		ParserAmazonJpPage ParserAmazonJpPage = new ParserAmazonJpPage(
				amazonPage);
		ParserAmazonJpPage.checkprice();
		System.out.println(amazonPage);
	}
}

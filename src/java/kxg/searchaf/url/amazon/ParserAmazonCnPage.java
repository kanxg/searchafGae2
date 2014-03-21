package kxg.searchaf.url.amazon;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import kxg.searchaf.url.Constant;
import kxg.searchaf.url.amazon.ParserAmazonPage;
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

public class ParserAmazonCnPage {
	public AmazonPage page;

	public ParserAmazonCnPage(AmazonPage page) {
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

		parser.setEncoding(Constant.ENCODE);
		
		NodeFilter name_filter5 = new AndFilter(new NodeClassFilter(
				InputTag.class), new HasAttributeFilter("id", "ASIN"));
		
		OrFilter lastFilter = new OrFilter();
		lastFilter.setPredicates(new NodeFilter[] {
				new NodeClassFilter(TitleTag.class),
				new NodeClassFilter(Span.class),
				new NodeClassFilter(TableTag.class),
				new NodeClassFilter(Div.class) ,name_filter5});

		NodeList list = parser.extractAllNodesThatMatch(lastFilter);
		for (int i = 0; i < list.size(); i++) {
			Node tag = list.elementAt(i);
			if (tag instanceof TitleTag) {
				TitleTag d = (TitleTag) tag;
				page.title = d.getTitle().replaceAll("Amazon.com:", "").trim();
			} else if (tag instanceof TableTag) {
				TableTag d = (TableTag) tag;
				String classname = d.getAttribute("class");
				if ("ddm-sbr-table".equalsIgnoreCase(classname)) {

					// System.out.println(d.getStringText());
					String sStr = d.getStringText();
					// System.out.println(sStr);
					if (sStr.indexOf("由<b>亚马逊</b>直接销售和发货") >= 0) {
						page.salerisAmazon = true;
					}
				}
			} else if (tag instanceof Span) {
				Span sp = (Span) tag;
				String sStr = sp.getStringText();
				String classname = sp.getAttribute("class");
				String id = sp.getAttribute("id");
				if ("availGreen".equalsIgnoreCase(classname)) {
					if (sStr.indexOf("现在有货") >= 0) {
						page.instock = true;
					}
				}else if ("priceLarge".equalsIgnoreCase(classname)) {
					page.price = getPrice(sp);
				}
				if ("buyingPriceValue".equalsIgnoreCase(id)
						|| "actualPriceValue".equalsIgnoreCase(id)) {
					page.price = getPrice(sp);
				}

			} else if (tag instanceof Div) {
				Div d = (Div) tag;

				// System.out.println(sStr);
				String classname = d.getAttribute("class");
				if ("buying".equalsIgnoreCase(classname)) {
					String sStr = d.getStringText();
					if (sStr.indexOf("由<b>亚马逊</b>直接销售和发货") >= 0) {
						page.salerisAmazon = true;
					}
				}
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
		// String price = "0";
		// for (int i = 0; i < productvalue.size(); i++) {
		// String tmp = productvalue.get(i);
		// if (tmp.indexOf("价格") == 0) {
		// price = productvalue.get(i + 1);
		// break;
		// }
		// }
		return getprice(productvalue.get(0));
	}

	private float getprice(String priceStr) throws Exception {
		float returnvalue = 0;
		try {
			// listprice= $80
			returnvalue = Float.parseFloat(priceStr.substring(1));
		} catch (NumberFormatException ex) {
			// listprice= $80-$90
			returnvalue = Float.parseFloat(priceStr.substring(1,
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
//		ProxyUtli.setProxy(true);
		AmazonPage amazonPage = new AmazonPage();
		// amazonPage.url =
		// "http://www.amazon.cn/dp/B007OZO03M/ref=sa_menu_kindle_l3_ebook_kindle_p";
		amazonPage.url = "http://www.amazon.cn/Adidas-NEO-%E9%98%BF%E8%BF%AA%E8%BE%BE%E6%96%AF%E8%BF%90%E5%8A%A8%E7%94%9F%E6%B4%BB-BBNEO-CLASSIC-%E7%94%B7%E5%BC%8F-%E4%BC%91%E9%97%B2%E8%BF%90%E5%8A%A8%E9%9E%8B/dp/B00CY50DF6/ref=pd_rhf_dp_p_img_2_NQQC";
		ParserAmazonCnPage parserAmazonPage = new ParserAmazonCnPage(amazonPage);
		parserAmazonPage.checkprice();
		System.out.println(amazonPage);
	}
}

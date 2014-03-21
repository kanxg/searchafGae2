package kxg.searchaf.url.neiman;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import kxg.searchaf.url.Constant;
import kxg.searchaf.url.neiman.ParserNeimanPage;
import kxg.searchaf.url.neiman.NeimanPage;
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
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

public class ParserNeimanPage {
	public NeimanPage page;

	public ParserNeimanPage(NeimanPage page) {
		this.page = page;
	};

	public void checkprice() throws Exception {
		// System.out.println("checking neiman url:" + page.getUrl());

		String cookies = ""; // NeimanLogin.getCookies();

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

		NodeFilter name_filter3 = new AndFilter(new NodeClassFilter(Div.class),
				new HasAttributeFilter("class", "lineItemInfo"));
		NodeFilter name_filter4 = new AndFilter(new NodeClassFilter(Div.class),
				new HasAttributeFilter("id", "productprice"));

		OrFilter lastFilter = new OrFilter();
		lastFilter.setPredicates(new NodeFilter[] {
				new NodeClassFilter(TitleTag.class),

				name_filter3, name_filter4 });

		NodeList list = parser.extractAllNodesThatMatch(lastFilter);
		for (int i = 0; i < list.size(); i++) {
			Node tag = list.elementAt(i);
			if (tag instanceof TitleTag) {
				TitleTag d = (TitleTag) tag;
				page.title = d.getTitle().replaceAll("- Neiman Marcus", "")
						.trim();
			} else if (tag instanceof Div) {
				Div d = (Div) tag;
				String sStr = d.getStringText();
				// System.out.println(sStr);
				if ("lineItemInfo".equalsIgnoreCase(d.getAttribute("class"))) {
					page.price = getPrice(d);
				} else if ("divAvailablity".equalsIgnoreCase(d
						.getAttribute("id"))) {
					if (sStr.indexOf("In Stock") >= 0
							|| sStr.indexOf("in stock") >= 0) {
						page.instock = true;
					}
				}
				// System.out.println(d.getStringText());
				// getinStock(d);
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
			if (tmp.indexOf("$") == 0) {
				price = tmp;
				break;
			}
		}
		return getprice(price);

	}

	private float getprice(String priceStr) throws Exception {
		// System.out.println("priceStr:" + priceStr);
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
		ProxyUtli.setProxy(true);
		NeimanPage neimanPage = new NeimanPage();
		neimanPage.url = "http://www.neimanmarcus.com/Theory-Nikay-Drop-Skirt-Plaid-Dress-Dresses/prod164090238_cat43810733__/p.prod?icid=&searchType=EndecaDrivenCat&rte=%252Fcategory.jsp%253FitemId%253Dcat43810733%2526pageSize%253D30%2526No%253D0%2526refinements%253D&eItemId=prod164090238&cmCat=product";
		ParserNeimanPage parserNeimanPage = new ParserNeimanPage(neimanPage);
		parserNeimanPage.checkprice();
		System.out.println(neimanPage);
	}
}

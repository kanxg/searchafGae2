package kxg.searchaf.url.saks_cat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import kxg.searchaf.url.Constant;
import kxg.searchaf.url.tommy.TommyProduct;
import kxg.searchaf.util.ProxyUtli;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.MetaTag;
import org.htmlparser.tags.Span;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;
import org.json.JSONObject;

public class ParserSaksPage {
	public SaksPage page;
	public HashMap<String, SaksProduct> allprolist;
	public SaksProduct product;

	public ParserSaksPage(SaksPage page, HashMap<String, SaksProduct> allprolist) {
		this.page = page;
		this.allprolist = allprolist;
	};

	public void checkprice() throws Exception {

		// System.out.println("checking Saks url [" + page.url + "]");
		URL url = new URL(page.url);
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		urlConnection.setConnectTimeout(Constant.connect_timeout);
		
		urlConnection.connect();

		Parser parser = new Parser(urlConnection);

		parser.setEncoding(Constant.ENCODE);
		NodeClassFilter div_filter = new NodeClassFilter(Div.class);

		OrFilter filters = new OrFilter();
		filters.setPredicates(new NodeFilter[] { div_filter });

		NodeList list = parser.extractAllNodesThatMatch(filters);

		for (int i = 0; i < list.size(); i++) {
			Node tag = list.elementAt(i);
			if (tag instanceof Div) {
				Div d = (Div) tag;
				String divclass = d.getAttribute("class");
				if ("pa-product-large".equalsIgnoreCase(divclass)
						|| "pa-product-large-third".equalsIgnoreCase(divclass)) {
					product = new SaksProduct();
					getName(d);
					product.productid = d.getAttribute("id");
					product.tagcontext = processHTML(tag);
					if (allprolist.get(product.productid) == null) {
						allprolist.put(product.productid, product);
						// System.out.println(product);
					}
				}
			}
		}

	}

	private String processHTML(Node node) {
		String html = node.toHtml();
		// String html = node.getChildren().elementAt(3).toHtml();
		// html = html + node.getChildren().elementAt(5).toHtml();
		// html = html + node.getChildren().elementAt(9).toHtml();
		// if (node.getChildren().size() > 11) {
		// html = html + node.getChildren().elementAt(11).toHtml();
		// }

		html = html.replaceAll("src=\"/",
				"src=\"http://www.saksfifthavenue.com/");

		return html;
	}

	private void getName(Node node) throws Exception {
		NodeList childList = node.getChildren();
		List<String> productvalue = new ArrayList<String>();
		processNodeList(childList, productvalue);
		// System.out.println(productvalue);
		// System.out.print(productvalue.get(2) + " " + productvalue.get(3));
		product.name = productvalue.get(0) + " " + productvalue.get(1);
		String listprice = productvalue.get(2).replaceAll("Was", "")
				.replaceAll("&#36;", "").trim();
		product.listprice = getprice(listprice);
		String price = productvalue.get(4).replaceAll("&#36;", "").trim();
		product.price = getprice(price);
		// boolean foundListprice = false;
		// for (int i = 4; i < productvalue.size(); i++) {
		// String a = productvalue.get(i).trim();
		// if (a.startsWith("$")) {
		// if (foundListprice) {
		// // it's price
		// product.price = getprice(a);
		// } else {
		// // it's list price
		// product.listprice = getprice(a);
		// product.price = product.listprice;
		// foundListprice = true;
		// }
		// }
		// }
		product.realdiscount = Math.round(product.price / product.listprice
				* 100) / 100f;
		// if (!foundListprice) {
		// System.out.println(productvalue);
		// }
	}

	private float getprice(String priceStr) throws Exception {
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
}

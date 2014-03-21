package kxg.searchaf.url.coachfactory;

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
import kxg.searchaf.util.Tag.Li;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.PrototypicalNodeFactory;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

public class ParsercoachfactoryPage {
	public coachfactoryPage page;
	public HashMap<Integer, coachfactoryProduct> allprolist;
	public coachfactoryProduct product;

	public ParsercoachfactoryPage(coachfactoryPage page,
			HashMap<Integer, coachfactoryProduct> allprolist) {
		this.page = page;
		this.allprolist = allprolist;
	};

	public void checkprice() {

		System.out.println("checking coachfactory url [" + page.url + "]");
		try {

			String cookies = coachfactoryLogin.getCookies();
			System.out.println("cookies:" + cookies);

			URL url = new URL(page.url);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			urlConnection.setRequestProperty("Cookie", cookies);

			urlConnection.setConnectTimeout(Constant.connect_timeout);
			
			urlConnection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (compatible; MSIE 6.0; Windows NT)");
			urlConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			urlConnection.connect();

			InputStream is = urlConnection.getInputStream();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));

			String s;
			StringBuilder result = new StringBuilder();
			while (((s = reader.readLine()) != null)) {
				result.append(s);
			}

			System.out.println("result= " + result.toString());

			Parser parser = new Parser(urlConnection);
			parser.setEncoding(Constant.ENCODE);

			NodeClassFilter div_filter = new NodeClassFilter(Div.class);
			NodeClassFilter span_filter = new NodeClassFilter(Span.class);
			OrFilter filters = new OrFilter();
			filters.setPredicates(new NodeFilter[] { span_filter, div_filter });

			NodeList list = parser.extractAllNodesThatMatch(filters);

			for (int i = 0; i < list.size(); i++) {
				Node tag = list.elementAt(i);
				if (tag instanceof Span) {
					Span stag = (Span) tag;
					if ("regular-price".equals(stag.getAttribute("class"))) {
						product.productid = Integer.parseInt(stag.getAttribute(
								"id").substring(15));
					}
				} else if (tag instanceof Div) {
					Div d = (Div) tag;
					if ("grid-container".equals(d.getAttribute("class"))) {
						product = new coachfactoryProduct();
						getPriceAndNameList(tag);
						// product.tagcontext = processHTML(tag);
						// if (allprolist.get(product.name) == null) {
						// allprolist.put(product.productid, product);
						// }
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
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
		// html = html
		// .replaceAll("/webapp/wcs/stores/servlet/ProductDisplay",
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/ProductDisplay");
		// html = html.replaceAll("//anf", "http://anf");

		// String htmldivswatches = node.getChildren().elementAt(11).toHtml();
		// htmldivswatches = htmldivswatches.replace("//anf", "http://anf");

		return html;
	}

	private void getPriceAndNameList1(Node node) throws Exception {
		NodeList childList = node.getChildren();
		List<String> productvalue = new ArrayList<String>();
		processNodeList(childList, productvalue);
		System.out.println(productvalue);
		String listprice = productvalue.get(0);
		product.listprice = getprice(listprice);
		if (productvalue.size() > 1) {
			String price = productvalue.get(1);
			product.price = getprice(price);
		} else {
			product.price = product.listprice;
		}
		product.realdiscount = Math.round(product.price / product.listprice
				* 100) / 100f;
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

	private void getPriceAndNameList(Node node) {
		NodeList childList = node.getChildren();
		List<String> productvalue = new ArrayList<String>();
		processNodeList(childList, productvalue);
		System.out.println(productvalue);
		product.name = productvalue.get(0);
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

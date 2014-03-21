package kxg.searchaf.url.chunqiu;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import kxg.searchaf.url.Constant;
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
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

public class ParserChunqiuPage {
	public ChunqiuPage page;
	public HashMap<String, ChunqiuProduct> allprolist;
	public ChunqiuProduct product;

	public ParserChunqiuPage(ChunqiuPage page,
			HashMap<String, ChunqiuProduct> allprolist) {
		this.page = page;
		this.allprolist = allprolist;
	};

	public void checkprice() throws Exception {

		// System.out.println("checking Chunqiu url [" + page.url + "]");
		URL url = new URL(page.url);
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		urlConnection.setConnectTimeout(Constant.connect_timeout);

		urlConnection.connect();

		Parser parser = new Parser(urlConnection);

		parser.setEncoding(Constant.ENCODE);

		NodeFilter input_filter = new AndFilter(new TagNameFilter("tr"),
				new HasAttributeFilter("class", "clickFocus"));

		OrFilter filters = new OrFilter();
		filters.setPredicates(new NodeFilter[] { input_filter });

		NodeList list = parser.extractAllNodesThatMatch(filters);

		for (int i = 0; i < list.size(); i++) {
			Node tag = list.elementAt(i);
			product = new ChunqiuProduct();
			getName(tag);
			// System.out.println(tagText);

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
		// .replaceAll("/webapp/wcs/stores/servlet/ProductDisplay", // html =
		// html.replaceAll("//anf", "http://anf");

		// String htmldivswatches = node.getChildren().elementAt(11).toHtml();
		// htmldivswatches = htmldivswatches.replace("//anf", "http://anf");

		return html;
	}

	private void getPriceList(Node node) throws Exception {
		NodeList childList = node.getChildren();
		List<String> productvalue = new ArrayList<String>();
		processNodeList(childList, productvalue);
		// System.out.println(productvalue);
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

	private void getName(Node node) {
		NodeList childList = node.getChildren();
		List<String> productvalue = new ArrayList<String>();
		processNodeList(childList, productvalue);
		// System.out.println(productvalue);
		product.hangban = productvalue.get(0);
		product.qifeitime = productvalue.get(1).replace(" ", "").trim();
		product.qifeitime = product.qifeitime.substring(0, 10) + " "
				+ product.qifeitime.substring(18, 23);
		// System.out.println(product.qifeitime);
		product.qifei = productvalue.get(2);
		product.daoda = productvalue.get(3);
		product.cangwei = productvalue.get(5);
		String price = productvalue.get(7).substring(1);
		product.price = new Long(price);
		if (productvalue.get(8).indexOf("已售完") > 0) {
			// System.out.println(productvalue.get(8));
			product.price = 0l;
		} else {
			allprolist.put(product.hangban + product.qifeitime, product);
			// System.out.println(product);
		}
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
		HashMap<String, ChunqiuProduct> allnewprolist = new HashMap<String, ChunqiuProduct>();
		ChunqiuPage page = new ChunqiuPage(
				"http://www.china-sss.com/SHA_HKG_False_28");
		ParserChunqiuPage parse = new ParserChunqiuPage(page, allnewprolist);
		parse.checkprice();
		ArrayList<ChunqiuProduct> matchprolist = new ArrayList<ChunqiuProduct>();
		Iterator<String> entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			ChunqiuProduct product = allnewprolist.get(key);
			if (
			// !product.hangban.equals("9C8501") &&
			product.price <= 299) {
				matchprolist.add(product);

			}
		}
		Collections.sort(matchprolist);
		for (int i = 0; i < matchprolist.size(); i++) {
			ChunqiuProduct matchpro = matchprolist.get(i);
			System.out.println(matchpro);
		}

		allnewprolist = new HashMap<String, ChunqiuProduct>();
		page = new ChunqiuPage("http://www.china-sss.com/SHA_HKG_False_89");
		parse = new ParserChunqiuPage(page, allnewprolist);
		parse.checkprice();
		matchprolist = new ArrayList<ChunqiuProduct>();
		entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			ChunqiuProduct product = allnewprolist.get(key);
			if (
			// !product.hangban.equals("9C8501") &&
			product.price <= 299) {
				matchprolist.add(product);
			}
		}
		Collections.sort(matchprolist);
		for (int i = 0; i < matchprolist.size(); i++) {
			ChunqiuProduct matchpro = matchprolist.get(i);
			System.out.println(matchpro);
		}

		System.out
				.println("==================================================");

		allnewprolist = new HashMap<String, ChunqiuProduct>();
		page = new ChunqiuPage("http://www.china-sss.com/HKG_SHA_False_28");
		parse = new ParserChunqiuPage(page, allnewprolist);
		parse.checkprice();
		matchprolist = new ArrayList<ChunqiuProduct>();
		entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			ChunqiuProduct product = allnewprolist.get(key);
			if (!product.hangban.equals("9C8502") && product.price <= 299) {
				matchprolist.add(product);
			}
		}
		Collections.sort(matchprolist);
		for (int i = 0; i < matchprolist.size(); i++) {
			ChunqiuProduct matchpro = matchprolist.get(i);
			System.out.println(matchpro);
		}

		allnewprolist = new HashMap<String, ChunqiuProduct>();
		page = new ChunqiuPage("http://www.china-sss.com/HKG_SHA_False_89");
		parse = new ParserChunqiuPage(page, allnewprolist);
		parse.checkprice();
		matchprolist = new ArrayList<ChunqiuProduct>();
		entryKeyIterator = allnewprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			ChunqiuProduct product = allnewprolist.get(key);
			if (!product.hangban.equals("9C8502") && product.price <= 299) {
				matchprolist.add(product);
			}
		}
		Collections.sort(matchprolist);
		for (int i = 0; i < matchprolist.size(); i++) {
			ChunqiuProduct matchpro = matchprolist.get(i);
			System.out.println(matchpro);
		}
	}
}

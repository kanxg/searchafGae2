package kxg.searchaf.url.jcrew;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import kxg.searchaf.mail.SystemMail;
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

public class ParserJcrewPage {
	public JcrewPage page;
	public HashMap<String, JcrewProduct> allprolist;
	public JcrewProduct product;

	public ParserJcrewPage(JcrewPage page,
			HashMap<String, JcrewProduct> allprolist) {
		this.page = page;
		this.allprolist = allprolist;
	};

	public void checkprice() throws Exception {

		// System.out.println("checking Jcrew url [" + page.url + "]");
		URL url = new URL(page.url);
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		urlConnection.setConnectTimeout(Constant.connect_timeout);

		urlConnection.connect();

		Parser parser = new Parser(urlConnection);

		parser.setEncoding(Constant.ENCODE);

		NodeFilter link_filter = new AndFilter(new NodeClassFilter(
				LinkTag.class),
				new HasAttributeFilter("class", "arrayProdName"));

		NodeFilter td1_filter = new AndFilter(new TagNameFilter("td"),
				new HasAttributeFilter("class", "arrayProdCell"));

		OrFilter filters = new OrFilter();
		filters.setPredicates(new NodeFilter[] { td1_filter, link_filter });

		NodeList list = parser.extractAllNodesThatMatch(filters);

		for (int i = 0; i < list.size(); i++) {
			Node tag = list.elementAt(i);
			if (tag instanceof LinkTag) {
				LinkTag d = (LinkTag) tag;
				String linkUrl = d.getLink();// url
				// System.out.println(linkUrl);
				product.productid = linkUrl;
				if (allprolist.get(product.productid) == null) {
					allprolist.put(product.productid, product);
					// System.out.println(product);
				}
			} else {
				product = new JcrewProduct();
				getName(tag);
				// product.productid = product.name;
				product.tagcontext = processHTML(tag);

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
		html = html.replaceAll("/media/images/",
				"http://www.jcrew.com/media/images/");

		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/ProductDisplay");
		// html = html.replaceAll("//anf", "http://anf");

		// String htmldivswatches = node.getChildren().elementAt(11).toHtml();
		// htmldivswatches = htmldivswatches.replace("//anf", "http://anf");

		return html;
	}

	private void getPriceList(Node node) throws Exception {
		NodeList childList = node.getChildren();
		List<String> productvalue = new ArrayList<String>();
		processNodeList(childList, productvalue);
		// System.out.println(productvalue);
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
		// System.out.println("priceStr:" + priceStr);
		float returnvalue = 0;
		try {
			// listprice= $80
			returnvalue = Float.parseFloat(priceStr.substring(1));
		} catch (NumberFormatException ex) {
			System.out.println("priceStr:" + priceStr);
			// listprice= $80-$90
			returnvalue = Float.parseFloat(priceStr.substring(1,
					priceStr.indexOf("-")));
		}
		return returnvalue;
	}

	private void getName(Node node) throws Exception {
		NodeList childList = node.getChildren();
		List<String> productvalue = new ArrayList<String>();
		processNodeList(childList, productvalue);
		// check null product
		if (productvalue.size() == 1)
			return;
		// System.out.println(productvalue);
		// System.out.println("name:" + productvalue.get(0));
		product.name = productvalue.get(1);

		String listprice = productvalue.get(2).replaceAll("was", "")
				.replaceAll(",", "").trim();
		// System.out.println("listprice:" + listprice);
		product.listprice = getprice(listprice);
		// System.out.println("listprice:" + product.listprice);
		if (productvalue.size() != 5) {
			// System.out.println("debug:" + productvalue);
			product.price = product.listprice;
		} else {
			if (productvalue.get(3).indexOf("also") == 0) {
				product.price = product.listprice;
			} else {
				String price = productvalue.get(3).replaceAll("now", "")
						.replaceAll(",", "").replaceAll("select colors", "")
						.trim();
				// System.out.println("price:" + price);
				product.price = getprice(price);
			}
		}

		// System.out.println("price:" + product.price);
		product.realdiscount = Math.round(product.price / product.listprice
				* 100) / 100f;
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
		HashMap<String, JcrewProduct> allmennewprolist = new HashMap<String, JcrewProduct>();

		List<JcrewPage> urllist = JcrewPage.getGirlsInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			JcrewPage JcrewPage = urllist.get(i);
			ParserJcrewPage ParserJcrewPage = new ParserJcrewPage(JcrewPage,
					allmennewprolist);
			try {
				ParserJcrewPage.checkprice();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}

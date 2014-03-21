package kxg.searchaf.url.freepeople;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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

public class ParserFreepeoplePage {
	public FreepeoplePage page;
	public HashMap<String, FreepeopleProduct> allprolist;
	public FreepeopleProduct product;

	public ParserFreepeoplePage(FreepeoplePage page,
			HashMap<String, FreepeopleProduct> allprolist) {
		this.page = page;
		this.allprolist = allprolist;
	};

	public void checkprice() throws Exception {

		// System.out.println("checking Freepeople url [" + page.url + "]");
		URL url = new URL(page.url);
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		urlConnection.setConnectTimeout(Constant.connect_timeout);

		urlConnection.connect();

		Parser parser = new Parser(urlConnection);

		parser.setEncoding(Constant.ENCODE);

		NodeFilter div_filter = new AndFilter(new NodeClassFilter(Div.class),
				new HasAttributeFilter("class", "details"));

		NodeFilter span1 = new AndFilter(new TagNameFilter("span"),
				new HasAttributeFilter("class", "price price-original"));

		NodeFilter span2 = new AndFilter(new TagNameFilter("span"),
				new HasAttributeFilter("class", "price price-sale"));

		NodeFilter filter3 = new AndFilter(new NodeClassFilter(LinkTag.class),
				new HasAttributeFilter("class", "quickview"));

		OrFilter filters = new OrFilter();
		filters.setPredicates(new NodeFilter[] { div_filter, filter3 });

		NodeList list = parser.extractAllNodesThatMatch(filters);

		for (int i = 0; i < list.size(); i++) {
			Node tag = list.elementAt(i);
			if (tag instanceof Div) {
				Div d = (Div) tag;
				// String divclass = d.getAttribute("class");
				// String name = tag.toPlainTextString().trim();
				// System.out.println("");
				// System.out.print(name);

				product = new FreepeopleProduct();
				getName(d);
				// getPriceList(d);
				// product.productid = Integer.valueOf(d
				// .getAttribute("data-catentryid"));
				product.tagcontext = processHTML(tag);

			} else if (tag instanceof LinkTag) {
				LinkTag lt = (LinkTag) tag;
				String href = lt.getAttribute("href");
				// System.out.println(href);
				product.productid = href;
				if (allprolist.get(product.productid) == null) {
					allprolist.put(product.productid, product);
				}
			}
		}

	}

	private String processHTML(Node node) {
		String html = node.getParent().getParent().toHtml()
				.replaceAll("Quick View", "");
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

	private float getprice(String priceStr) throws Exception {
		float returnvalue = 0;

		// listprice= $80
		if (priceStr.startsWith("$")) {
			returnvalue = Float.parseFloat(priceStr.substring(1));
		} else {
			returnvalue = Float.parseFloat(priceStr);
		}

		return returnvalue;
	}

	private void getName(Node node) throws Exception {
		NodeList childList = node.getChildren();
		List<String> productvalue = new ArrayList<String>();
		processNodeList(childList, productvalue);
		// System.out.println(productvalue);
		product.name = productvalue.get(0);
		String listprice = productvalue.get(2);
		product.listprice = getprice(listprice);
		if (productvalue.size() >= 5) {
			String price = productvalue.get(4);
			product.price = getprice(price);
		} else {
			product.price = product.listprice;
		}
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
}

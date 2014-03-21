package kxg.searchaf.url.gymboree;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import kxg.searchaf.url.Constant;
import kxg.searchaf.url.aptamil.AptamilConstant;
import kxg.searchaf.util.ProxyUtli;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

public class ParserGymboreePage {
	public GymboreePage page;
	public HashMap<Integer, GymboreeProduct> allprolist;
	public GymboreeProduct product;

	public ParserGymboreePage(GymboreePage page,
			HashMap<Integer, GymboreeProduct> allprolist) {
		this.page = page;
		this.allprolist = allprolist;
	};

	public void checkprice() throws Exception {

		//System.out.println("checking Gymboree url [" + page.url + "]");
		
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
				if ("cat_prodImg".equalsIgnoreCase(divclass)) {

					product = new GymboreeProduct();
					product.tagcontext = processHTML(tag);
					product.productid = parseProductId(tag);
					if (allprolist.get(product.productid) == null) {
						allprolist.put(product.productid, product);
					}
				} else if ("collection-pricing".equalsIgnoreCase(divclass)) {
					getNameAndPriceList(d);
					// System.out.println(product);
				}

			}
		}

	}

	private int parseProductId(Node node) {
		Node a = node.getChildren().elementAt(1);
		LinkTag it = (LinkTag) a;
		String name = it.getAttribute("name");
		return Integer.parseInt(name);

	}

	private String processHTML(Node node) {
		node = node.getParent();
		String html = node.getChildren().elementAt(1).toHtml();
		html = html + node.getChildren().elementAt(5).toHtml();
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

	private void getNameAndPriceList(Node node) {
		NodeList childList = node.getChildren();
		List<String> productvalue = new ArrayList<String>();
		processNodeList(childList, productvalue);
		// System.out.println(productvalue);
		try {
			product.name = productvalue.get(0);
			String listprice = productvalue.get(2);
			product.listprice = getprice(listprice);
			if (productvalue.size() > 3) {
				String price = productvalue.get(4);
				product.price = getprice(price);
			} else {
				product.price = product.listprice;
			}
			product.realdiscount = Math.round(product.price / product.listprice
					* 100) / 100f;
		} catch (Exception e) {
			System.out.println("error when get product: " + product.name);
		}
	}

	private float getprice(String priceStr) throws Exception {
		priceStr = priceStr.trim();
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
}

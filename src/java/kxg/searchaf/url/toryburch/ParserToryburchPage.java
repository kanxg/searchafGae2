package kxg.searchaf.url.toryburch;

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
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

public class ParserToryburchPage {
	public ToryburchPage page;
	public HashMap<String, ToryburchProduct> allprolist;
	public ToryburchProduct product;

	public ParserToryburchPage(ToryburchPage page,
			HashMap<String, ToryburchProduct> allprolist) {
		this.page = page;
		this.allprolist = allprolist;
	};

	public void checkprice() throws Exception {

		// System.out.println("checking Toryburch url [" + page.url + "]");
		URL url = new URL(page.url);
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		urlConnection.setConnectTimeout(Constant.connect_timeout);

		urlConnection.connect();

		Parser parser = new Parser(urlConnection);

		parser.setEncoding(Constant.ENCODE);

		NodeFilter div_filter1 = new AndFilter(new NodeClassFilter(Div.class),
				new HasAttributeFilter("class", "name"));

		NodeFilter div_filter2 = new AndFilter(new NodeClassFilter(Div.class),
				new HasAttributeFilter("class", "price"));

		NodeFilter filter3 = new AndFilter(new NodeClassFilter(ImageTag.class),
				new HasAttributeFilter("class", "alternateimage"));

		OrFilter filters = new OrFilter();
		filters.setPredicates(new NodeFilter[] { div_filter1, div_filter2,
				filter3 });

		NodeList list = parser.extractAllNodesThatMatch(filters);

		for (int i = 0; i < list.size(); i++) {
			Node tag = list.elementAt(i);
			if (tag instanceof Div) {
				Div d = (Div) tag;
				String divclass = d.getAttribute("class");
				if ("name".equalsIgnoreCase(divclass)) {
					getName(d);
					product.tagcontext = processHTML(tag);
				} else {
					getPriceList(d);

				}
			} else if (tag instanceof ImageTag) {
				ImageTag it = (ImageTag) tag;
				String src = it.getAttribute("src");
				// System.out.println(src);
				src = src.replaceAll(
						"http://s7d5.scene7.com/is/image/ToryBurchLLC/TB_", "")
						.replaceAll("\\?\\$trb_grid_md\\$", "");
				// System.out.println(src);
				product = new ToryburchProduct();
				product.productid = src;
				if (allprolist.get(product.productid) == null) {
					allprolist.put(product.productid, product);
				} else {
					System.out.println("dulp:" + src);
				}
			}
		}

	}

	private void getPriceList(Node node) {
		NodeList childList = node.getChildren();
		List<String> productvalue = new ArrayList<String>();
		processNodeList(childList, productvalue);
		// System.out.println(productvalue);
		try {
			String listprice = productvalue.get(0);
			product.listprice = getPrice(listprice);
			if (productvalue.size() > 1) {
				String price = productvalue.get(1);
				product.price = getPrice(price);
			} else {
				product.price = product.listprice;
			}
			product.realdiscount = Math.round(product.price / product.listprice
					* 100) / 100f;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String processHTML(Node node) {
		String html = node.getParent().toHtml();
		html = html.replaceAll("<i>Go to</i> PRODUCT", "");
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

	private float getPrice(String priceStr) throws Exception {
		priceStr = priceStr.replaceAll(",", "");
		return getprice(priceStr);

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

	private void getName(Node node) throws Exception {
		NodeList childList = node.getChildren();
		List<String> productvalue = new ArrayList<String>();
		processNodeList(childList, productvalue);
		// System.out.println(productvalue);
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

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);
		HashMap<String, ToryburchProduct> allwomennewprolist = new HashMap<String, ToryburchProduct>();
		List<ToryburchPage> urllist = ToryburchPage.getWomenSale(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			ToryburchPage ToryburchPage = urllist.get(i);
			ParserToryburchPage ParserToryburchPage = new ParserToryburchPage(
					ToryburchPage, allwomennewprolist);
			try {
				ParserToryburchPage.checkprice();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(allwomennewprolist.size());
	}
}

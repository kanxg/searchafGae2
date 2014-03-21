package kxg.searchaf.url.amazonWeibo;

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

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

public class ParserAmazonPage {
	public AmazonSearchPage page;
	public HashMap<String, AmazonProduct> allprolist;

	public ParserAmazonPage(AmazonSearchPage page,
			HashMap<String, AmazonProduct> allprolist) {
		this.page = page;
		this.allprolist = allprolist;
	};

	public boolean checkprice() {
		System.out.println("checking amazon url:" + page.url);
		try {		
			
			URL url = new URL(page.url);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			urlConnection.setConnectTimeout(Constant.connect_timeout);
			
			Parser parser = new Parser(urlConnection);
			parser.setEncoding(Constant.ENCODE);

			// OrFilter lastFilter = new OrFilter();
			// lastFilter.setPredicates(new NodeFilter[] {
			// new NodeClassFilter(TableTag.class),
			// new NodeClassFilter(Div.class) });
			//
			// NodeList list = parser.extractAllNodesThatMatch(lastFilter);

			NodeList list = parser
					.extractAllNodesThatMatch(new NodeClassFilter(Div.class));
			System.out.println("size:" + list.size());
			
			for (int i = 0; i < list.size(); i++) {
				Node tag = list.elementAt(i);

				if (tag instanceof Div) {
					Div d = (Div) tag;
					System.out.println(d.getAttribute("id"));

					if (d.getAttribute("id").startsWith("result_")) {
						// found one product
						try {
							AmazonProduct product = new AmazonProduct();
							product.name = d.getAttribute("name");
							getPriceAndLabel(d, product);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
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

	private void getPriceAndLabel(Node node, AmazonProduct product)
			throws Exception {
		NodeList childList = node.getChildren();
		List<String> productvalue = new ArrayList<String>();
		processNodeList(childList, productvalue);
		System.out.println(productvalue);
		product.label = productvalue.get(0);
		// String price = productvalue.get(3);
		// product.price = getprice(price);
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
}

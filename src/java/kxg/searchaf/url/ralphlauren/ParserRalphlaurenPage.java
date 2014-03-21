package kxg.searchaf.url.ralphlauren;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.DefinitionList;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;
import kxg.searchaf.url.Constant;
import kxg.searchaf.util.ProxyUtli;

public class ParserRalphlaurenPage {
	public RalphlaurenPage page;
	public HashMap<Integer, RalphlaurenProduct> allprolist;
	RalphlaurenProduct product;

	public ParserRalphlaurenPage(RalphlaurenPage page,
			HashMap<Integer, RalphlaurenProduct> allprolist) {
		this.page = page;
		this.allprolist = allprolist;
	};

	public void checkprice() throws Exception {
		// System.out.println("checking [ralphlauren] [" + page.type + "] ["
		// + page.Category + "]");
		URL url = new URL(page.url);
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		urlConnection.setConnectTimeout(Constant.connect_timeout);
		

		Parser parser = new Parser(urlConnection);

		parser.setEncoding(Constant.ENCODE);

		NodeList list = parser.parse(new NodeClassFilter(DefinitionList.class));
		for (int i = 0; i < list.size(); i++) {
			Node tag = list.elementAt(i);
			DefinitionList d = (DefinitionList) tag;
			if ("product-details".equals(d.getAttribute("class"))) {
				try {
					product = new RalphlaurenProduct();
					product.type = page.type;
					product.category = page.Category;

					// System.out.println(product);
					getProductId(d);

					getNameAndPrice(d);

					if (RalphlaurenConstant.isIgnoreProduct(product.name))
						continue;

					product.tagcontext = processHTML(tag);

					if (allprolist.get(product.productid) == null) {
						allprolist.put(product.productid, product);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	private String processHTML(Node node) {
		String html = node.getParent().getChildren().elementAt(1).toHtml();
		html = html.replaceAll("/product/index.jsp",
				"http://www.ralphlauren.com/product/index.jsp");
		// html = html.replaceAll("../graphics",
		// "http://www.ralphlauren.com/graphics");
		return html;
	}

	private void getProductId(Node node) throws Exception {
		Node parent = node.getParent();
		TagNode d = (TagNode) parent;
		String div_id = d.getAttribute("id");
		product.productid = Integer.valueOf(div_id.substring(8));
	}

	private void getNameAndPrice(Node node) throws Exception {
		NodeList childList = node.getChildren();
		List<String> productvalue = new ArrayList<String>();
		processNodeList(childList, productvalue);
		checkproduct(productvalue);
	}

	private float getprice(String priceStr) throws Exception {
		float returnvalue = 0;
		try {
			// listprice= $80
			returnvalue = Float.parseFloat(priceStr);
		} catch (NumberFormatException ex) {
			// listprice= $80-$90
			returnvalue = Float.parseFloat(priceStr.substring(0,
					priceStr.indexOf("&nbsp;")));
		}
		return returnvalue;
	}

	private void checkproduct(List<String> productvalue) throws Exception {
		product.name = productvalue.get(0);

		String list_price = productvalue.get(1);
		list_price = list_price.substring(list_price.indexOf(";") + 1,
				list_price.length());
		float listprice = getprice(list_price);

		String offer_price = productvalue.get(productvalue.size() - 1);
		offer_price = offer_price.substring(offer_price.indexOf(";") + 1,
				offer_price.length());
		float offerprice = getprice(offer_price);

		float realdiscount = offerprice / listprice;
		realdiscount = Math.round(realdiscount * 100) / 100f;

		product.listprice = listprice;
		product.price = offerprice;
		product.realdiscount = realdiscount;

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

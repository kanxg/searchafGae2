package kxg.searchaf.url.neiman_cat;

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

public class ParserNeimanPage {
	public NeimanPage page;
	public HashMap<Long, NeimanProduct> allprolist;
	public NeimanProduct product;

	public ParserNeimanPage(NeimanPage page,
			HashMap<Long, NeimanProduct> allprolist) {
		this.page = page;
		this.allprolist = allprolist;
	};

	public void checkprice() throws Exception {

		// System.out.println("checking Neiman url [" + page.url + "]");		
		URL url = new URL(page.url);
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		urlConnection.setConnectTimeout(Constant.connect_timeout);
		
		urlConnection.connect();

		InputStream is = urlConnection.getInputStream();

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		String s;
		StringBuilder result = new StringBuilder();
		while (((s = reader.readLine()) != null)) {
			result.append(s);
		}

		// System.out.println("result= " + result.toString());

		is.close();

		JSONObject jsonObj = new JSONObject(result.toString())
				.getJSONObject("GenericSearchResp");

		String productResults = jsonObj.getString("productResults");
		// System.out.println("productResults= " + productResults);

		Parser parser = new Parser(productResults.toString());
		// Parser parser = new Parser(urlConnection);

		parser.setEncoding(Constant.ENCODE);

		NodeClassFilter span_filter = new NodeClassFilter(Span.class);
		NodeClassFilter div_filter = new NodeClassFilter(Div.class);
		NodeClassFilter meta_filter = new NodeClassFilter(MetaTag.class);
		OrFilter filters = new OrFilter();
		filters.setPredicates(new NodeFilter[] { // span_filter, meta_filter,
		div_filter });

		NodeList list = parser.extractAllNodesThatMatch(filters);
		for (int i = 0; i < list.size(); i++) {
			Node tag = list.elementAt(i);
			if (tag instanceof Div) {
				Div d = (Div) tag;
				String divclass = d.getAttribute("class");
				if ("product".equalsIgnoreCase(divclass)
						|| "product start".equalsIgnoreCase(divclass)) {
					product = new NeimanProduct();
					String id = d.getAttribute("id");
					product.productid = Long.valueOf(id.substring(4,
							id.length() - 7));
					product.tagcontext = processHTML(tag);
					getName(d);
					if (allprolist.get(product.productid) == null) {
						allprolist.put(product.productid, product);
						// System.out.println(product);
					}
				}
				// else if ("qv-tip".equalsIgnoreCase(divclass)) {
				// System.out.println(d.getAttribute("product_id"));
				// product.productid = Integer.valueOf(d.getAttribute(
				// "product_id").substring(4));
				// if (allprolist.get(product.productid) == null) {
				// allprolist.put(product.productid, product);
				// System.out.println(product);
				// }
				// }

				// parsePriceWithDiv(tag);
			}
		}

	}

	private String processHTML(Node node) {
		//String html = node.toHtml();
		String html = node.getChildren().elementAt(3).toHtml();
		// html = html + node.getChildren().elementAt(5).toHtml();
		// html = html + node.getChildren().elementAt(9).toHtml();
		// if (node.getChildren().size() > 11) {
		// html = html + node.getChildren().elementAt(11).toHtml();
		// }
		html = html
				.replaceAll("<a href=\"/p",
				"<a href=\"http://www.neimanmarcus.com/p");
		// html = html.replaceAll("//anf", "http://anf");

		// String htmldivswatches = node.getChildren().elementAt(11).toHtml();
		// htmldivswatches = htmldivswatches.replace("//anf", "http://anf");
		//<div class="productImageContainer"> <a href="/p/Johnny-Was-Collection-Miliana-Embroidered-Georgette-Blouse/prod154410143_cat46520736__/;jsessionid=515190121FE79278BD9FB71515481871?icid=&searchType=EndecaDrivenCat&rte=%252Fcategory.service%253FitemId%253Dcat46520736%2526pageSize%253D120%2526No%253D0%2526refinements%253D&eItemId=prod152910204&cmCat=product" class="prodImgLink"> <img onerror="this.src='/images/shim.gif'" title="T5T9W Johnny Was Collection Miliana Embroidered Georgette Blouse" class="productImage" style="" src="http://images.neimanmarcus.com/ca/1/products/mi/NMT5T9W_mi.jpg" alt="" id="prod152910204"></img> <script>nm.thumbnails.addToggleImage('prod152910204', 'http://images.neimanmarcus.com/ca/1/products/mi/NMT5T9W_mi.jpg', 'http://images.neimanmarcus.com/ca/1/products/ai/NM-43ZY_ai.jpg')</script> </a> </div>
		
		return html;
	}

	private void getName(Node node) throws Exception {
		NodeList childList = node.getChildren();
		List<String> productvalue = new ArrayList<String>();
		processNodeList(childList, productvalue);
		// System.out.println(productvalue);
		// System.out.print(productvalue.get(2) + " " + productvalue.get(3));
		product.name = productvalue.get(2) + " " + productvalue.get(3);
		boolean foundListprice = false;
		for (int i = 4; i < productvalue.size(); i++) {
			String a = productvalue.get(i).trim();
			if (a.startsWith("$")) {
				if (foundListprice) {
					// it's price
					product.price = getprice(a);
				} else {
					// it's list price
					product.listprice = getprice(a);
					product.price = product.listprice;
					foundListprice = true;
				}
			}
		}
		product.realdiscount = Math.round(product.price / product.listprice
				* 100) / 100f;
		// if (!foundListprice) {
		// System.out.println(productvalue);
		// }
	}

	private float getprice(String priceStr) throws Exception {
		priceStr = priceStr.replace("&nbsp;", "");
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

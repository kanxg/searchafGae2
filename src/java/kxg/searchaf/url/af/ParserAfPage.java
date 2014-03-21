package kxg.searchaf.url.af;

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
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

public class ParserAfPage {
	public AfPage page;
	public HashMap<Integer, AfProduct> allprolist;
	public AfProduct product;

	public ParserAfPage(AfPage page, HashMap<Integer, AfProduct> allprolist) {
		this.page = page;
		this.allprolist = allprolist;
	};

	public void checkprice() throws Exception {

		String cookies = AfLogin.getCookies();

		String currentCategory = "";
		String titleText = "";
		// System.out.println("checking af url [" + page.url + "]");

		// HttpClient httpclient = new HttpClient();
		//
		// if (AfConstant.usingProxy) {
		// httpclient.getHostConfiguration().setProxy(Constant.ip,
		// new Integer(Constant.port));
		// }
		// GetMethod method = new GetMethod(page.url);
		// method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
		// new DefaultHttpMethodRetryHandler(3, false));
		// int statusCode = httpclient.executeMethod(method);
		// String redirectURL = method.getURI().toString();
		// // System.out.println("curReq:url:" + redirectURL);
		//
		// // System.out.println("response=" +
		// // method.getResponseBodyAsString());
		// InputStream resStream = method.getResponseBodyAsStream();
		// BufferedReader br = new BufferedReader(new
		// InputStreamReader(resStream));
		// StringBuffer resBuffer = new StringBuffer();
		// String resTemp = "";
		// while ((resTemp = br.readLine()) != null) {
		// resBuffer.append(resTemp);
		// }
		// String response = resBuffer.toString();
		// System.out.println("response=" + response);
		// Parser parser = new Parser(response);

		URL url = new URL(page.url);
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();

		urlConnection.setConnectTimeout(Constant.connect_timeout);

		// urlConnection.setRequestProperty("Cookie", cookies);

		urlConnection.connect();

		// InputStream is = urlConnection.getInputStream();
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(is));
		// String s;
		// StringBuilder result = new StringBuilder();
		// while (((s = reader.readLine()) != null)) {
		// result.append(s);
		// }
		// System.out.println("result= " + result.toString());
		// is.close();
		// if (1 == 1)
		// return;

		Parser parser = new Parser(urlConnection);
		String redirectURL = urlConnection.getURL().toString();

		// if (!redirectURL.equals(page.url)) {
		// System.out.println("URL:" + page.url +
		// " redirect to new address:"
		// + redirectURL);
		// }
		if (redirectURL.indexOf(AfConstant.checkingURLhost) < 0) {
			// System.out.println("URL:" + page.url
			// + " redirect to wrong address:" + redirectURL);
			// throw new Exception("URL:" + page.url
			// + " redirect to wrong address:" + redirectURL);
			return;
		}

		parser.setEncoding(Constant.ENCODE);
		NodeClassFilter div_filter = new NodeClassFilter(Div.class);
		NodeClassFilter title_filter = new NodeClassFilter(TitleTag.class);
		NodeClassFilter color_filter = new NodeClassFilter(LinkTag.class);

		OrFilter filters = new OrFilter();
		filters.setPredicates(new NodeFilter[] { div_filter, title_filter,
				color_filter });

		NodeList list = parser.extractAllNodesThatMatch(filters);

		for (int i = 0; i < list.size(); i++) {
			Node tag = list.elementAt(i);
			if (tag instanceof TitleTag) {
				TitleTag title = (TitleTag) tag;
				titleText = title.toPlainTextString()
						.replaceAll("Abercrombie.com", "").trim();
				titleText = titleText.substring(0, titleText.length() - 1)
						.trim();
			} else if (tag instanceof Div) {
				Div d = (Div) tag;
				String div_class = d.getAttribute("class");
				if ("category  products".equals(div_class)) {
					// get category
					currentCategory = getCategory(d);
				} else if ("category ScentByForm products".equals(div_class)) {
					// get category
					currentCategory = "Cologne&Fragrance";
				}
				if ("product".equals(div_class)
						|| "product model-bottom".equals(div_class)
						|| "product lazy-load-container".equals(div_class)
						|| "product model-bottom lazy-load-container"
								.equals(div_class)) {

					// found one product
					if (AfConstant.isIgnoreProduct(currentCategory)) {
						// ignore these products under Featured Items.
						continue;
					} else {
						product = new AfProduct();
						product.category = currentCategory;
					}
					String div_id = d.getAttribute("id");
					product.type = titleText;
					product.productid = Integer.valueOf(div_id.substring(4));
					product.data_sequence = d.getAttribute("data-sequence");
					product.data_collection = d.getAttribute("data-collection");
					getNameAndPrice(d);
					// getColor(d);
					// System.out.println(product.name);
					product.tagcontext = processHTML(tag);
					// System.out.println(product);
					if (allprolist.get(product.productid) == null) {
						allprolist.put(product.productid, product);
					} else {
						// System.out.println(product.productid);
					}
				}
			} else if (tag instanceof LinkTag) {
				LinkTag colortag = (LinkTag) tag;
				if ("swatch-link".equalsIgnoreCase(colortag
						.getAttribute("class"))) {
					if (AfConstant.isIgnoreProduct(currentCategory)) {
						// ignore these products under Featured Items.
						continue;
					} else {
						// String herf = colortag.getLink();
						String data_seq = colortag.getAttribute("data-seq");
						// System.out.println(data_seq);
						// String seq = herf.substring(herf.length() - 2,
						// herf.length());
						if (product.seqList == null)
							product.seqList = new ArrayList<String>();
						product.seqList.add(data_seq);
					}
				}
			}
		}

	}

	private String getCategory(Div cate) {
		NodeList childList = cate.getChildren();
		List<String> productvalue = new ArrayList<String>();
		processNodeList(childList, productvalue);
		return productvalue.get(0).replaceAll("amp;", "");
	}

	private String processHTML(Node node) {
		NodeList imgnode = node.getChildren().elementAt(3).getChildren()
				.elementAt(1).getChildren();
		imgnode.remove(1);
		String html = node.toHtml();
		// String html = node.getChildren().elementAt(3).toHtml();
		// html = html + node.getChildren().elementAt(5).toHtml();
		// html = html + node.getChildren().elementAt(9).toHtml();
		// if (node.getChildren().size() > 11) {
		// html = html + node.getChildren().elementAt(11).toHtml();
		// }
		html = html.replaceAll("<noscript>", "");
		html = html.replaceAll("</noscript>", "");
		// html = html
		// .replaceAll("/webapp/wcs/stores/servlet/ProductDisplay",
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/ProductDisplay");
		html = html
				.replaceAll("/shop/us", "http://www.abercrombie.com/shop/us");
		html = html.replaceAll("//anf", "http://anf");

		// String htmldivswatches = node.getChildren().elementAt(11).toHtml();
		// htmldivswatches = htmldivswatches.replace("//anf", "http://anf");

		return html;
	}

	private void getNameAndPrice(Node node) throws Exception {
		NodeList childList = node.getChildren();
		List<String> productvalue = new ArrayList<String>();
		processNodeList(childList, productvalue);
		// System.out.println(productvalue);
		getName(productvalue);
		int listpricePos = getlistprice(productvalue);
		int salepricePos = getsaleprice(productvalue, listpricePos);
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

	private void getName(List<String> productvalue) {
		product.name = productvalue.get(0).toUpperCase();
	}

	private void getColor(Node node) {
		if (node.getChildren().size() > 11) {
			// Node a = node.getChildren().elementAt(11);
			// NodeList aList = a.getChildren().elementAt(1).getChildren();
			// SimpleNodeIterator iterator = aList.elements();
			// while (iterator.hasMoreNodes()) {
			// Node b = iterator.nextNode();
			// // if(b instance of )
			// }
			NodeList childList = node.getChildren().elementAt(11).getChildren();
			List<String> productvalue = new ArrayList<String>();
			processNodeList(childList, productvalue);
			product.colorList = new String[productvalue.size()];
			for (int i = 0; i < productvalue.size(); i++) {
				product.colorList[i] = productvalue.get(i);
			}
		}
	}

	private int getlistprice(List<String> productvalue) throws Exception {
		int i = 1;
		String list_price;
		while (i < productvalue.size()) {
			list_price = productvalue.get(i);
			if (list_price.startsWith("$")) {
				product.listprice = getprice(list_price);
				break;
			}
			i = i + 1;
		}
		return i;
	}

	private int getsaleprice(List<String> productvalue, int listpricePos)
			throws Exception {
		int i = listpricePos + 1;
		boolean foundsale = false;
		if (i < productvalue.size()) {
			String sale_price = productvalue.get(i);
			if (sale_price.startsWith("$")) {
				foundsale = true;
				float saleprice = getprice(sale_price);
				product.price = saleprice;
				product.realdiscount = Math.round(product.price
						/ product.listprice * 100) / 100f;
			}
		}
		if (!foundsale) {
			// didn't get sale price
			product.price = product.listprice;
			product.realdiscount = 1;
			i = listpricePos;
		}
		return i;
	}

	private void processNodeList(NodeList list, List<String> valueList) {
		// 迭代开始
		if (list == null)
			return;
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
		Properties prop = System.getProperties();
		prop.setProperty("http.proxyHost", "cn-proxy.sg.oracle.com");
		prop.setProperty("http.proxyPort", "80");

		// String url = "http://www.abercrombie.com/shop/us/mens-shorts";
		// // String url = "http://www.abercrombie.com/shop/us/mens-sale";
		// // String url = "http://www.abercrombie.com/shop/us/womens-sale";
		// // String url = "http://www.abercrombie.com/shop/us/mens-tees";
		// AfPage page = new AfPage(url);
		// HashMap<Integer, AfProduct> allmenoldprolist = new HashMap<Integer,
		// AfProduct>();
		// ParserAfPage parse = new ParserAfPage(page, allmenoldprolist);
		// parse.checkprice();
		// System.out.println("found products:" + allmenoldprolist.size());
		// ArrayList<AfProduct> matchprolist = new ArrayList<AfProduct>(
		// allmenoldprolist.values());
		// for (int i = 0; i < matchprolist.size(); i++) {
		// AfProduct afProduct = matchprolist.get(i);
		// System.out.println("found products:" + afProduct);
		// }

		HashMap<Integer, AfProduct> allmenoldprolist = new HashMap<Integer, AfProduct>();
		List<AfPage> urllist = AfPage.getMenAllInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			AfPage afpage = urllist.get(i);
			ParserAfPage parserAfpage = new ParserAfPage(afpage,
					allmenoldprolist);
			parserAfpage.checkprice();
			System.out.println("found products:" + allmenoldprolist.size());
		}
		// ArrayList<AfProduct> matchprolist = new ArrayList<AfProduct>(
		// allmenoldprolist.values());
		// for (int i = 0; i < matchprolist.size(); i++) {
		// AfProduct afProduct = matchprolist.get(i);
		// System.out.println("found products:" + afProduct);
		//
		// }
	}
}

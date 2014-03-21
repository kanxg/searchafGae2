package kxg.searchaf.url.af.afsecretlink;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import kxg.searchaf.url.Constant;
import kxg.searchaf.url.af.AfProduct;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

public class ParserAfproductExist {
	public String url;
	public HashMap<Integer, AfProduct> searchprolist;
	public AfProduct product;

	public ParserAfproductExist(String url,
			HashMap<Integer, AfProduct> searchprolist) {
		this.url = url;
		this.searchprolist = searchprolist;
	};

	public void foundproduct() {
		System.out.println("checking af url [" + this.url + "]");
		try {
			URL url = new URL(this.url);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			urlConnection.setConnectTimeout(Constant.connect_timeout);

			Parser parser = new Parser(urlConnection);

			parser.setEncoding(Constant.ENCODE);

			NodeList list = parser.parse(new NodeClassFilter(Div.class));
			for (int i = 0; i < list.size(); i++) {
				Node tag = list.elementAt(i);
				Div d = (Div) tag;
				if ("product".equals(d.getAttribute("class"))) {
					try {
						String div_id = d.getAttribute("id");
						product = new AfProduct();
						product.productid = Integer
								.valueOf(div_id.substring(4));
						product.tagcontext = processHTML(tag);
						if (searchprolist.get(product.productid) == null) {
							searchprolist.put(product.productid, product);
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private String processHTML(Node node) {
		String html = node.getChildren().elementAt(3).toHtml();
		html = html + node.getChildren().elementAt(5).toHtml();
		html = html + node.getChildren().elementAt(9).toHtml();
		html = html
				.replaceAll("ProductDisplay",
						"http://www.abercrombie.com/webapp/wcs/stores/servlet/ProductDisplay");
		html = html.replace("//anf", "http://anf");
		return html;
	}
}

package kxg.searchaf.url.aptamil;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import kxg.searchaf.url.Constant;
import kxg.searchaf.url.af.AfConstant;
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
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;

public class ParserWindelnPage extends ParserAptamilPage {

	public ParserWindelnPage(AptamilPage page,
			Map<String, AptamilProduct> aptamilProduct) {
		super(page, aptamilProduct);
	}

	String current_prod_name = "";
	AptamilProduct curr_prod;

	public void checkprice() throws Exception {

		String cookies = "";// WindelnLogin.getCookies();

		// System.out.println("checking Aptamil url [" + page.url + "]");

		URL url = new URL(page.url);
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		urlConnection.setRequestProperty("Cookie", cookies);
		urlConnection.setConnectTimeout(Constant.connect_timeout);
		// urlConnection.setRequestProperty("User-Agent",
		// "Mozilla/5.0 (compatible; MSIE 6.0; Windows NT)");
		// urlConnection.setRequestProperty("Content-Type",
		// "application/x-www-form-urlencoded");

		urlConnection.connect();

		Parser parser = new Parser(urlConnection);

		parser.setEncoding(Constant.ENCODE);

		// NodeFilter div_filter = new AndFilter(new NodeClassFilter(Div.class),
		// new HasAttributeFilter("id", "sp-stock_qty"));

		// NodeFilter input_filter = new AndFilter(new TagNameFilter("input"),
		// new HasAttributeFilter("content", "in_stock"));

		NodeFilter name_filter = new AndFilter(new TagNameFilter("span"),
				new HasAttributeFilter("class", "span-8 name"));

		NodeFilter inventory_filter = new AndFilter(new TagNameFilter("span"),
				new HasAttributeFilter("class", "span-1 quantity align-right"));

		OrFilter filters = new OrFilter();
		filters.setPredicates(new NodeFilter[] { // div_filter, // ,input_filter
		name_filter, inventory_filter });

		NodeList list = parser.extractAllNodesThatMatch(filters);

		for (int i = 0; i < list.size(); i++) {
			Node tag = list.elementAt(i);
			if (tag instanceof Span) {
				Span s = (Span) tag;
				String spanClass = s.getAttribute("class");
				// System.out.println(current_prod_name);
				// System.out.println("spanClass" + spanClass);
				if (spanClass.equalsIgnoreCase("span-8 name")) {
					current_prod_name = tag.toPlainTextString();
					if (!AptamilConstant.ignoreProduct(current_prod_name)) {
						curr_prod = new AptamilProduct();
						curr_prod.name = current_prod_name;
						curr_prod.website = page.website;
						curr_prod.buylink = page.url;
						curr_prod.hasInventory = false;
						aptamilProduct.put(page.website + current_prod_name,
								curr_prod);
						// System.out.println(current_prod_name);
					}
				} else {
					if (!AptamilConstant.ignoreProduct(current_prod_name)) {
						// Integer curr_inventory = Integer.parseInt(tag
						// .toPlainTextString());

						// curr_prod.inventory = curr_inventory;
						curr_prod.hasInventory = true;
						System.out.println(current_prod_name + "    有货  ");

					}
				}

			}
		}

	}

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);
		String url = "http://www.windeln.de/aptamil-milchnahrung.html";
		String productName = "Aptamil 1";
		String Stringwebsite = "Amazon De";
		Map<String, AptamilProduct> products = new HashMap<String, AptamilProduct>();
		AptamilPage page = new AptamilPage(url, productName, Stringwebsite);
		ParserWindelnPage parse = new ParserWindelnPage(page, products);
		parse.checkprice();

	}
}

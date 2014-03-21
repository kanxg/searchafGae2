package kxg.searchaf.url.aptamil;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.htmlparser.tags.Span;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;

public class ParserAmazonDePage extends ParserAptamilPage {

	public ParserAmazonDePage(AptamilPage page,
			Map<String, AptamilProduct> aptamilProduct) {
		super(page, aptamilProduct);
	}

	public void checkprice() throws Exception {

		// System.out.println("checking Aptamil url [" + page.url + "]");
		URL url = new URL(page.url);
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		urlConnection.setConnectTimeout(Constant.connect_timeout);
		urlConnection.connect();

		Parser parser = new Parser(urlConnection);

		parser.setEncoding(Constant.ENCODE);
		NodeClassFilter span_filter = new NodeClassFilter(TableTag.class);

		NodeFilter td_filter = new AndFilter(span_filter,
				new HasAttributeFilter("class", "product"));

		OrFilter filters = new OrFilter();
		filters.setPredicates(new NodeFilter[] { td_filter });

		NodeList list = parser.extractAllNodesThatMatch(filters);

		AptamilProduct product = new AptamilProduct();
		product.website = page.website;
		product.buylink = page.url;
		product.name = page.productName;
		product.hasInventory = false;

		for (int i = 0; i < list.size(); i++) {
			Node tag = list.elementAt(i);
			if (tag instanceof TableTag) {
				TableTag d = (TableTag) tag;
				getPrice(d);
				product.hasInventory = true;
				//System.out.println("有货");
			}
		}
		aptamilProduct.put(page.website + product.name, product);

	}

	private void getPrice(Node node) throws Exception {
		NodeList childList = node.getChildren();
		List<String> productvalue = new ArrayList<String>();
		processNodeList(childList, productvalue);
		System.out.println(productvalue);
		String price = productvalue.get(3);

	}

	public static void main(String[] args) throws Exception {
		String url = "http://www.amazon.de/Aptamil-Anfangsmilch-4er-Pack-800/dp/B00D8V0972/ref=dp_ob_title_grocery";
		String productName = "Aptamil 1";
		String Stringwebsite = "Amazon De";
		Map<String, AptamilProduct> products = new HashMap<String, AptamilProduct>();
		AptamilPage page = new AptamilPage(url, productName, Stringwebsite);
		ParserAmazonDePage parse = new ParserAmazonDePage(page, products);
		parse.checkprice();
	}

}

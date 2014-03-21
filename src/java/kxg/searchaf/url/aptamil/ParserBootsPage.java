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
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeList;

public class ParserBootsPage extends ParserAptamilPage {

	public ParserBootsPage(AptamilPage page,
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
		NodeClassFilter div_filter = new NodeClassFilter(Div.class);

		OrFilter filters = new OrFilter();
		filters.setPredicates(new NodeFilter[] { div_filter });

		NodeList list = parser.extractAllNodesThatMatch(filters);

		for (int i = 0; i < list.size(); i++) {
			Node tag = list.elementAt(i);
			if (tag instanceof Div) {
				Div d = (Div) tag;
				String divclass = d.getAttribute("class");
				if ("pl_addToBasket".equalsIgnoreCase(divclass)) {
					//return getName(d);
				}
			}
		}

	}

	private boolean getName(Node node) {
		NodeList childList = node.getChildren();
		List<String> productvalue = new ArrayList<String>();
		processNodeList(childList, productvalue);
		// System.out.println(productvalue);
		int i = 0;
		while (i < productvalue.size()) {
			String Quantity = productvalue.get(i);
			if (Quantity.startsWith("Quantity")) {
				return true;
			}
			i = i + 1;
		}
		return false;
	}
}

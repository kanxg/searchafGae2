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
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;

public class ParserBabyneoPage extends ParserAptamilPage {

	public ParserBabyneoPage(AptamilPage page,
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
		NodeClassFilter span_filter = new NodeClassFilter(Span.class);

		OrFilter filters = new OrFilter();
		filters.setPredicates(new NodeFilter[] { span_filter });

		NodeList list = parser.extractAllNodesThatMatch(filters);

		for (int i = 0; i < list.size(); i++) {
			Node tag = list.elementAt(i);
			if (tag instanceof Span) {
				Span d = (Span) tag;
				String divclass = d.getAttribute("class");
				//if ("notavailability".equals(divclass))
					//return false;

			}
		}
 
	}

	public static void main(String[] args) throws Exception {
		String url = "http://www.babyneo.de/Babymilch--Aptamil-Nestle-Hipp--39/Aptamil--Milupa--Produkte/Milupa-Aptamil-1-800g.html";
		String productName = "Aptamil 1";
		String Stringwebsite = "babyneo";
		Map<String, AptamilProduct> products = new HashMap<String, AptamilProduct>();
		AptamilPage page = new AptamilPage(url, productName, Stringwebsite);
		ParserBabyneoPage parse = new ParserBabyneoPage(page, products);
		parse.checkprice();
	}

}

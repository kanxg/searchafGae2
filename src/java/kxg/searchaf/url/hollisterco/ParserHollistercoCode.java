package kxg.searchaf.url.hollisterco;

import java.net.HttpURLConnection;
import java.net.URL;
import kxg.searchaf.url.Constant;
import kxg.searchaf.util.ProxyUtli;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeList;

public class ParserHollistercoCode {

	public HollistercoCode checkcode() throws Exception {
		URL url = new URL("http://www.hollisterco.com/shop/us");
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		urlConnection.setConnectTimeout(Constant.connect_timeout);

		urlConnection.connect();

		Parser parser = new Parser(urlConnection);

		parser.setEncoding(Constant.ENCODE);
		NodeFilter div_filter = new AndFilter(new NodeClassFilter(Div.class),
				new HasAttributeFilter("id", "shortPromo"));

		OrFilter filters = new OrFilter();
		filters.setPredicates(new NodeFilter[] { div_filter });

		NodeList list = parser.extractAllNodesThatMatch(filters);

		for (int i = 0; i < list.size(); i++) {
			Node tag = list.elementAt(i);
			if (tag instanceof Div) {
				Div d = (Div) tag;
				HollistercoCode hollistercocode = new HollistercoCode();
				hollistercocode.name = d.toPlainTextString();
				// System.out.println(hollistercocode.name);
				hollistercocode.tagcontext = processHTML(d); // d.getParent().toHtml();
				return hollistercocode;
			}
		}
		return null;
	}

	private String processHTML(Node node) {
		String html = node.getParent().toHtml();
		// String html = node.getChildren().elementAt(3).toHtml();
		// html = html + node.getChildren().elementAt(5).toHtml();
		// html = html + node.getChildren().elementAt(9).toHtml();
		// if (node.getChildren().size() > 11) {
		// html = html + node.getChildren().elementAt(11).toHtml();
		// }
		html = html
				.replaceAll("/shop/us", "http://www.hollisterco.com/shop/us");
		html = html.replaceAll("/anf/media",
				"http://www.hollisterco.com/anf/media");

		// String htmldivswatches = node.getChildren().elementAt(11).toHtml();
		// htmldivswatches = htmldivswatches.replace("//anf", "http://anf");

		return html;
	}

	public static void main(String[] args) throws Exception {
 		ProxyUtli.setProxy(true);

		ParserHollistercoCode parse = new ParserHollistercoCode();
		HollistercoCode code = parse.checkcode();
		System.out.println(code.toJspString());

	}
}

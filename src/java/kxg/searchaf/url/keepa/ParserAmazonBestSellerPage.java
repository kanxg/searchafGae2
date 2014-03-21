package kxg.searchaf.url.keepa;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kxg.searchaf.url.Constant;
import kxg.searchaf.url.amazon.ParserAmazonPage;
import kxg.searchaf.url.amazon.AmazonPage;
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
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;

public class ParserAmazonBestSellerPage {
	public AmazonBestSellers page;
	public Map<String, AmazonProduct> productMap;
	public AmazonProduct product;

	public ParserAmazonBestSellerPage(AmazonBestSellers page,
			Map<String, AmazonProduct> productMap) {
		this.page = page;
		this.productMap = productMap;
	};

	public void checkprice() throws Exception {
		// System.out.println("checking amazon url:" + page.url);

		String cookies = ""; // AmazonLogin.getCookies();

		URL url = new URL(page.url);
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		urlConnection.setConnectTimeout(Constant.connect_timeout);
		urlConnection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (compatible; MSIE 6.0; Windows NT)");
		urlConnection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");

		urlConnection.setRequestProperty("Cookie", cookies);

		urlConnection.connect();
		// InputStream is = urlConnection.getInputStream();
		//
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(is));
		//
		// String s;
		// StringBuilder result = new StringBuilder();
		// while (((s = reader.readLine()) != null)) {
		// result.append(s);
		// }
		//
		// System.out.println("result= " + result.toString());
		//
		// is.close();

		Parser parser = new Parser(urlConnection);

		parser.setEncoding(Constant.ENCODE);

		NodeFilter name_filter = new AndFilter(new NodeClassFilter(Span.class),
				new HasAttributeFilter("class",
						"asinReviewsSummary acr-popover"));

		NodeFilter name_filter2 = new AndFilter(
				new NodeClassFilter(Span.class), new HasAttributeFilter(
						"class", "asinReviewsSummary"));

		OrFilter lastFilter = new OrFilter();
		lastFilter.setPredicates(new NodeFilter[] { name_filter, name_filter2
		// name_filter3, name_filter4
				});

		NodeList list = parser.extractAllNodesThatMatch(lastFilter);
		for (int i = 0; i < list.size(); i++) {
			Node tag = list.elementAt(i);
			if (tag instanceof TableTag) {
				TableTag d = (TableTag) tag;
				// found one product
			} else if (tag instanceof Span) {
				Span sp = (Span) tag;
				String classname = sp.getAttribute("class");
				if (classname.indexOf("asinReviewsSummary") >= 0) {
					String asin_name = sp.getAttribute("name");
					if (productMap.get("asin_name") == null) {
						product = new AmazonProduct();
						product.ASIN = asin_name;
						product.amazonZone = page.amazonZone;
						product.category= page.category;
						productMap.put(asin_name, product);
					}

				}

			} else if (tag instanceof Div) {
				Div d = (Div) tag;

			}
		}

	}

	public static void main(String[] args) throws Exception {
		ProxyUtli.setProxy(true);
		AmazonPage amazonPage = new AmazonPage();
		amazonPage.checksalerisAmazon = true;
		amazonPage.url = "http://www.amazon.com/Seiko-SMY111-Stainless-Steel-Kinetic/dp/B003CG1878?SubscriptionId=AKIAJ7T5BOVUVRD2EFYQ&taag=camelproducts-20&linkCode=xm2&camp=2025&creative=165953&creativeASIN=B003CG1878&smid=ATVPDKIKX0DER";
		ParserAmazonPage parserAmazonPage = new ParserAmazonPage(amazonPage);
		parserAmazonPage.checkprice();
		System.out.println(amazonPage);
	}
}

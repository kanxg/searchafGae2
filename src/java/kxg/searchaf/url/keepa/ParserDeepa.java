package kxg.searchaf.url.keepa;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.zip.GZIPInputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kxg.searchaf.url.Constant;
import kxg.searchaf.url.af.Inventory;
import kxg.searchaf.util.ProxyUtli;

public class ParserDeepa {
	private AmazonProduct product;

	public ParserDeepa(AmazonProduct product) {
		this.product = product;
	}

	public void checkAmazonPriceList() {

		getInventory();
	}

	public void getInventory() {

		// System.out.println("checking inventory, url:"
		// + this.product.getKeepaUrl());
		try {

			URL url = new URL(this.product.getKeepaUrl());
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			urlConnection.setConnectTimeout(Constant.connect_timeout);
			// urlConnection.setRequestProperty("Charset", "UTF-8");// 字符集编码
			// urlConnection.setRequestProperty("User-Agent",
			// "Mozilla/5.0 (compatible; MSIE 6.0; Windows NT)");
			// urlConnection.setRequestProperty("Content-Type",
			// "application/x-www-form-urlencoded");
			// urlConnection.setRequestProperty("contentType", "UTF-8");

			urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type",
					"application/json; charset=utf-8");
			urlConnection.setRequestProperty("Accept-Encoding", "gzip,deflate");

			urlConnection.connect();

			InputStream urlStream = new GZIPInputStream(
					urlConnection.getInputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					urlStream, "UTF-8"));
			String line = "";
			StringBuilder result = new StringBuilder();

			while ((line = reader.readLine()) != null) {
				result.append(line);
				// System.out.println(line);
			}
			reader.close();
			urlStream.close();
			// System.out.println("result= " + result.toString());

			// InputStream is = urlConnection.getInputStream();
			//
			// BufferedReader reader = new BufferedReader(
			// new InputStreamReader(is));
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
			//
			parseInventory(result.toString());

		} catch (Exception e) {
			System.out.println("Exception when get keepa price list:ASIN is"
					+ this.product.ASIN);
			e.printStackTrace();
		}
	}

	public void parseInventory(String jsonStr) {
		if (jsonStr.startsWith("(")) {
			jsonStr = jsonStr.substring(1, jsonStr.length() - 2);
		}
		// System.out.println("jsonStr" + jsonStr);

		try {
			JSONObject jsonObj = new JSONObject(jsonStr);
			JSONObject product = jsonObj.getJSONObject("product");
			String AMAZON = product.getString("AMAZON");
			// System.out.println("AMAZON:" + AMAZON);
			AMAZON = AMAZON.substring(1, AMAZON.length() - 1);
			this.product.pricelist = AMAZON.split(",");
			this.product.name = product.getString("TITLE");
			this.product.currentprice = Float.parseFloat(product
					.getString("AMAZON_LAST")) / 100;
		} catch (Exception e) {
			// System.out.println("Exception when get keepa price list:ASIN is"
			// + this.product.ASIN);
		}

		// for (String s : pricelist) {
		// System.out.println("pricelist:" + s);
		// }
		// for (int i = 0; i < jsons.length(); ++i) {
		// JSONObject tempJson = jsons.getJSONObject(i);
		// // String inventory = tempJson.getString("AMAZON");
		// // String itemseq = tempJson.getString("seq");
		// System.out.println("tempJson:" + tempJson);
		//
		// }

	}

	public static void main(String[] args) throws Exception {
		AmazonProduct product = new AmazonProduct();
		product.ASIN = "B00EI3YC40";
		product.amazonZone = "amazon.cn";
		ParserDeepa parse = new ParserDeepa(product);
		parse.checkAmazonPriceList();
		System.out.println(product);
	}
}

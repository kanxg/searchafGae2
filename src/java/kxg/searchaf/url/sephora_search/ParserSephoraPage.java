package kxg.searchaf.url.sephora_search;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import kxg.searchaf.url.Constant;
import kxg.searchaf.util.ProxyUtli;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParserSephoraPage {
	public SephoraPage page;
	public HashMap<String, SephoraProduct> allprolist;
	public SephoraProduct product;

	public ParserSephoraPage(SephoraPage page,
			HashMap<String, SephoraProduct> allprolist) {
		this.page = page;
		this.allprolist = allprolist;
	};

	public void checkprice() throws Exception {

		// System.out.println("checking Sephora url [" + page.url + "]");
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

		JSONObject jsonObj = new JSONObject(result.toString());
		JSONArray jsons = jsonObj.getJSONArray("products");
		for (int i = 0; i < jsons.length(); ++i) {
			SephoraProduct product = new SephoraProduct();
			JSONObject tempJson = jsons.getJSONObject(i);
			String id = tempJson.getString("id");
			product.productid = id;
			String display_name = tempJson.getString("display_name");
			// System.out.println("display_name= " + display_name);
			product.name = display_name;
			String product_url = tempJson.getString("product_url");
			product.url = product_url;
			String hero_image = tempJson.getString("hero_image");
			product.img_src = hero_image;
			String brand_name = tempJson.getString("brand_name");
			product.brand_name = brand_name;
			JSONObject derived_sku = tempJson.getJSONObject("derived_sku");
			String list_price = derived_sku.getString("list_price");
			// System.out.println("list_price= " + list_price);
			product.listprice = getprice(list_price);
			if (derived_sku.has("sale_price")) {
				String sale_price = derived_sku.getString("sale_price");
				// System.out.println("sale_price= " + sale_price);
				product.price = getprice(sale_price);
			} else {
				product.price = product.listprice;
			}
			product.realdiscount = Math.round(product.price / product.listprice
					* 100) / 100f;
			product.buildTagContext();
			allprolist.put(product.productid, product);

		}
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

}

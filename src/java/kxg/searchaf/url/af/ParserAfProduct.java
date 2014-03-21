package kxg.searchaf.url.af;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import kxg.searchaf.url.Constant;
import kxg.searchaf.util.ProxyUtli;

public class ParserAfProduct {
	public AfProduct product;
	public String ProductUrlStr;

	public ParserAfProduct(AfProduct product) {
		this.product = product;
		ProductUrlStr = AfConstant.ProductInventoryUrl + product.productid
				+ "&seq=";
	}

	public void checkColorItemInventory(boolean ignoreSaleOut) {
		if (product.seqList == null) {
			getInventory(product.data_sequence, ignoreSaleOut);
		} else {
			for (int i = 0; i < product.seqList.size(); i++) {
				getInventory(product.seqList.get(i), ignoreSaleOut);
			}
		}
	}

	public void getInventory(String seq, boolean ignoreSaleOut) {
		if (seq == null & "".equals(seq))
			seq = "01";
		// System.out.println("checking inventory, url:" + ProductUrlStr + seq);
		try {
			String cookies = AfLogin.getCookies();
			
			URL url = new URL(ProductUrlStr + seq);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			urlConnection.setConnectTimeout(Constant.connect_timeout);
			urlConnection.setRequestProperty("Cookie", cookies);
			urlConnection.connect();

			InputStream is = urlConnection.getInputStream();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));

			String s;
			StringBuilder result = new StringBuilder();
			while (((s = reader.readLine()) != null)) {
				result.append(s);
			}

			// System.out.println("result= " + result.toString());

			is.close();

			product.parseInventory(result.toString(), ignoreSaleOut);

		} catch (Exception e) {
			System.out.println("Exception when get product:"
					+ product.productid + ";seq:" + seq);
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		
 		ProxyUtli.setProxy(true);

		AfProduct product=new AfProduct();
		product.productid=1346482;
		product.data_sequence="01";
		ParserAfProduct parse=new ParserAfProduct(product);
		parse.checkColorItemInventory(true);
		System.out.println(product);
	}
}

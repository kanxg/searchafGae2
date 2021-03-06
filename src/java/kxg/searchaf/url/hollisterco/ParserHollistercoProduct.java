package kxg.searchaf.url.hollisterco;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import kxg.searchaf.url.Constant;
import kxg.searchaf.util.ProxyUtli;

public class ParserHollistercoProduct {
	public HollistercoProduct product;
	public String ProductUrlStr;

	public ParserHollistercoProduct(HollistercoProduct product) {
		this.product = product;
		ProductUrlStr = HollistercoConstant.ProductInventoryUrl + product.productid
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
			String cookies = HollistercoLogin.getCookies();
			
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

		HollistercoProduct product=new HollistercoProduct();
		product.productid=1164234;
		product.data_sequence="01";
		ParserHollistercoProduct parse=new ParserHollistercoProduct(product);
		parse.checkColorItemInventory(true);
		System.out.println(product);
	}
}

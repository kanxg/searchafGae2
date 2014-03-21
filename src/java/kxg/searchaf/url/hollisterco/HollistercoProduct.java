package kxg.searchaf.url.hollisterco;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class HollistercoProduct implements Comparable<Object> {
	public String addReason = "";
	public String type;
	public String category;
	public String name;
	public int productid;
	public float listprice = 0;
	public float price = 0;
	public float realdiscount;
	public String url;
	public String img_src;
	public String tagcontext;
	public String[] colorList;
	public List<String> seqList;
	public String data_sequence;
	public String data_collection;
	public List<Inventory> inventoryList;

	public String toString() {
		String matchpro = "";
		if (!"".equals(addReason)) {
			matchpro = matchpro + "[" + addReason + "]";
		}
		matchpro = matchpro + "[" + type + "]";
		matchpro = matchpro + "[" + category;
		matchpro = matchpro + "][" + name;
		// matchpro = matchpro + "][productid:" + productid;
		matchpro = matchpro + "][标价:" + listprice;
		matchpro = matchpro + "][现价:" + price;
		matchpro = matchpro + "][折扣:" + realdiscount + "]";
		// matchpro = matchpro + "][color:";
		// if (colorList != null) {
		// for (int i = 0; i < colorList.length; i++) {
		// matchpro = matchpro + colorList[i] + ",";
		// }
		// }
		String currentColor = "";
		if (inventoryList != null && inventoryList.size() > 0) {
			if (seqList == null) {
				matchpro = matchpro + "<br/>";
			}
			for (int i = 0; i < inventoryList.size(); i++) {
				Inventory inventory = inventoryList.get(i);
				if (seqList != null) {
					if (!currentColor.equalsIgnoreCase(inventory.name))
						matchpro = matchpro + "<br/>颜色:" + inventory.name
								+ "<br/>";
					currentColor = inventory.name;
				}
				matchpro = matchpro + "[尺码:" + inventory.itemSize + ";";
				matchpro = matchpro + "库存:" + inventory.itemInventory + "]";
			}
		}
		// matchpro = matchpro + "]";
		return matchpro;
	}

	public String toJspString() {
		String matchpro = "";
		matchpro = matchpro + "折扣:" + realdiscount;
		// matchpro = matchpro + "][color:";
		// if (colorList != null) {
		// for (int i = 0; i < colorList.length; i++) {
		// matchpro = matchpro + colorList[i] + ",";
		// }
		// }
		String currentColor = "";
		if (inventoryList != null && inventoryList.size() > 0) {
			if (seqList == null) {
				matchpro = matchpro + "<br/>";
			}
			for (int i = 0; i < inventoryList.size(); i++) {
				Inventory inventory = inventoryList.get(i);
				if (seqList != null) {
					if (!currentColor.equalsIgnoreCase(inventory.name))
						matchpro = matchpro + "<br/>" + inventory.name
								+ "<br/>";
					currentColor = inventory.name;
				}
				String additemurl = HollistercoConstant.addtoCartUrl
						+ "&productId=" + this.productid + "&partNumber="
						+ inventory.sku;
				matchpro = matchpro + "[" + "<a href=\"javascript:addToCart('"
						+ additemurl + "');\">" + inventory.itemSize;
				matchpro = matchpro + ":" + inventory.itemInventory + "</a>"
						+ "]";
			}
		}
		// matchpro = matchpro + "]";
		return matchpro;
	}

	@Override
	public int compareTo(Object o) {
		HollistercoProduct comp = (HollistercoProduct) o;
		// return this.realdiscount - comp.realdiscount > 0.01 ? 1 : -1;

		int flag = 0;
		flag = this.type.compareTo(comp.type);
		if (flag != 0)
			return flag;
		flag = getSequence(this.category) - getSequence(comp.category);
		if (flag != 0)
			return flag;
		flag = this.name.compareTo(comp.name);
		if (flag != 0)
			return flag;
		return 0;
	}

	private static int getSequence(String name) {
		// men
		// category

		if ("GRAPHIC T-SHIRTS".equalsIgnoreCase(name))
			return 10;
		if ("SHORT SLEEVE T-SHIRTS".equalsIgnoreCase(name))
			return 20;
		if ("LONG SLEEVE T-SHIRTS".equalsIgnoreCase(name))
			return 30;
		if ("T-Shirts &amp; Tanks".equalsIgnoreCase(name))
			return 31;
		if ("POLOS".equalsIgnoreCase(name))
			return 40;
		if ("SHIRTS".equalsIgnoreCase(name))
			return 50;
		if ("PLAID".equalsIgnoreCase(name))
			return 51;
		if ("CLASSIC".equalsIgnoreCase(name))
			return 52;
		if (name.startsWith("Hoodies"))
			return 60;
		if ("SWEATERS".equalsIgnoreCase(name))
			return 70;
		if ("OUTERWEAR".equalsIgnoreCase(name))
			return 80;
		if ("CLASSIC SHORTS".equalsIgnoreCase(name))
			return 90;
		if ("ATHLETIC SHORTS".equalsIgnoreCase(name))
			return 100;
		if ("SHORTS".equalsIgnoreCase(name))
			return 101;
		if ("JEANS".equalsIgnoreCase(name))
			return 110;
		if ("SWEATPANTS".equalsIgnoreCase(name))
			return 120;
		if ("PANTS".equalsIgnoreCase(name))
			return 130;
		if ("SWIM".equalsIgnoreCase(name))
			return 140;
		if ("ACCESSORIES".equalsIgnoreCase(name))
			return 150;

		// women
		if ("Fashion Tops".equalsIgnoreCase(name))
			return 7;
		if ("Tees &amp; Tanks".equalsIgnoreCase(name))
			return 8;
		if ("SLEEVELESS".equalsIgnoreCase(name))
			return 9;
		if ("SHORT SLEEVE".equalsIgnoreCase(name))
			return 10;
		if ("LONG SLEEVE".equalsIgnoreCase(name))
			return 20;
		if ("GRAPHIC TEES".equalsIgnoreCase(name))
			return 30;
		if ("Tanks".equalsIgnoreCase(name))
			return 31;
		if ("PLAID".equalsIgnoreCase(name))
			return 51;
		if ("CLASSIC".equalsIgnoreCase(name))
			return 52;
		if ("FLANNEL".equalsIgnoreCase(name))
			return 52;
		if (name.startsWith("Hoodies"))
			return 60;
		if ("SWEATERS".equalsIgnoreCase(name))
			return 70;
		if ("OUTERWEAR".equalsIgnoreCase(name))
			return 80;
		if ("CLASSIC SHORTS".equalsIgnoreCase(name))
			return 90;
		if ("ATHLETIC SHORTS".equalsIgnoreCase(name))
			return 100;
		if ("SHORTS".equalsIgnoreCase(name))
			return 101;
		if ("JEANS".equalsIgnoreCase(name))
			return 110;
		if ("SWEATPANTS".equalsIgnoreCase(name))
			return 120;
		if ("PANTS".equalsIgnoreCase(name))
			return 130;
		if ("SKIRTS".equalsIgnoreCase(name))
			return 131;
		if ("DRESSES".equalsIgnoreCase(name))
			return 132;
		if ("TOPS".equalsIgnoreCase(name))
			return 133;
		if ("BOTTOMS".equalsIgnoreCase(name))
			return 134;
		if ("SWIM".equalsIgnoreCase(name))
			return 140;
		if ("FLIP FLOPS".equalsIgnoreCase(name))
			return 141;
		if ("ACCESSORIES".equalsIgnoreCase(name))
			return 150;
		if ("Sleep".equalsIgnoreCase(name))
			return 151;
		if ("UNDERWEAR".equalsIgnoreCase(name))
			return 160;
		// System.out.println(name);
		return -1;
	}

	public void parseInventory(String jsonStr, boolean ignoreSaleOut)
			throws Exception {

		JSONObject jsonObj = new JSONObject(jsonStr);

		String soldOut = "true";
		try {
			soldOut = jsonObj.getString("soldOut");
		} catch (Exception e) {
			return;
		}
		if (ignoreSaleOut && "true".equalsIgnoreCase(soldOut))
			return;

		if (this.inventoryList == null)
			this.inventoryList = new ArrayList<Inventory>();

		String name = jsonObj.getString("name");
		String seq = jsonObj.getString("seq");

		String listprice = jsonObj.getString("list");
		String imgPrefix = jsonObj.getString("imgPrefix");
		String longSku = jsonObj.getString("longSku");

		JSONArray jsons = jsonObj.getJSONArray("items");
		for (int i = 0; i < jsons.length(); ++i) {
			JSONObject tempJson = jsons.getJSONObject(i);
			String inventory = tempJson.getString("inventory");

			if (ignoreSaleOut && "0".equals(inventory))
				continue;

			String itemseq = tempJson.getString("seq");
			// System.out.println("seq:" + seq);
			String size = tempJson.getString("size");
			// System.out.println("size:" + size);
			String sku = tempJson.getString("sku");
			String cat = tempJson.getString("cat");

			inventoryList.add(new Inventory(name, seq, listprice, imgPrefix,
					longSku, size, itemseq, inventory, sku, cat));
		}

	}

	// private class Inventory {
	// public String name;
	// public String seq;
	// public String itemSize;
	// public String itemSeq;
	// public String itemInventory;
	// public String sku;
	//
	// public Inventory(String name, String seq, String itemSize,
	// String itemSeq, String itemInventory, String sku) {
	// this.name = name;
	// this.seq = seq;
	// this.itemSize = itemSize;
	// this.itemSeq = itemSeq;
	// this.itemInventory = itemInventory;
	// this.sku = sku;
	// }
	// }
}

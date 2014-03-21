package kxg.searchaf.url.hollisterco;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import kxg.searchaf.util.ProxyUtli;

import org.json.JSONArray;
import org.json.JSONObject;

public class HollistercoUtil {

	public ArrayList<HollistercoProduct> checkuser(
			ArrayList<HollistercoProduct> matchprolist,
			HollistercoMailList amail, boolean isMen) {
		ArrayList<HollistercoProduct> usermatch = new ArrayList<HollistercoProduct>();
		for (int i = 0; i < matchprolist.size(); i++) {
			HollistercoProduct hollistercoProduct = matchprolist.get(i);
			if (meetingprod(hollistercoProduct, amail, isMen)) {
				usermatch.add(hollistercoProduct);
			}
		}
		return usermatch;
	}

	private boolean meetingprod(HollistercoProduct hollistercoProduct,
			HollistercoMailList amail, boolean isMen) {
		if (isMen) {
			return menmeetingprod(hollistercoProduct, amail);
		} else {
			return womenmeetingprod(hollistercoProduct, amail);
		}
	}

	private boolean menmeetingprod(HollistercoProduct hollistercoProduct,
			HollistercoMailList amail) {
		if ("Dudes Clearance".equalsIgnoreCase(hollistercoProduct.type)) {
			if (!amail.mencheckingClearance)
				return false;
			if (hollistercoProduct.realdiscount > amail.mencheckingClearanceDiscount)
				return false;
		} else if ("Dudes Sale".equalsIgnoreCase(hollistercoProduct.type)) {
			if (!amail.mencheckingSale)
				return false;
			if (hollistercoProduct.realdiscount > amail.mencheckingSaleDiscount)
				return false;
		} else if ("Dudes Secret Sale"
				.equalsIgnoreCase(hollistercoProduct.type)) {
			if (!amail.mencheckingSecretSale)
				return false;
		} else {
			if (!amail.mencheckingRegular)
				return false;
		}
		if (amail.mencheckingCategory != null
			 && amail.mencheckingCategory.size() != 15) {
			if (!menexistCategory(hollistercoProduct.type,
					hollistercoProduct.category, amail.mencheckingCategory))
				return false;
		}
		if (amail.mencheckingSize != null && amail.mencheckingSize.size() != 34)  {
			if (!existSize(hollistercoProduct, amail.mencheckingSize))
				return false;
		}
		return true;
	}

	private boolean womenmeetingprod(HollistercoProduct hollistercoProduct,
			HollistercoMailList amail) {
		if ("Bettys Clearance".equalsIgnoreCase(hollistercoProduct.type)) {
			if (!amail.womencheckingClearance)
				return false;
			if (hollistercoProduct.realdiscount > amail.womencheckingClearanceDiscount)
				return false;
		} else if ("Bettys Sale".equalsIgnoreCase(hollistercoProduct.type)) {
			if (!amail.womencheckingSale)
				return false;
			if (hollistercoProduct.realdiscount > amail.womencheckingSaleDiscount)
				return false;
		} else if ("Bettys Secret Sale"
				.equalsIgnoreCase(hollistercoProduct.type)) {
			if (!amail.womencheckingSecretSale)
				return false;
		} else {
			if (!amail.womencheckingRegular)
				return false;
		}
		if (amail.womencheckingCategory != null
				&& amail.womencheckingCategory.size() != 19) {
			if (!womenexistCategory(hollistercoProduct.type,
					hollistercoProduct.category, amail.womencheckingCategory))
				return false;
		}
		if (amail.womencheckingSize != null
			&& amail.womencheckingSize.size() != 16) {
			if (!existSize(hollistercoProduct, amail.womencheckingSize))
				return false;
		}
		return true;
	}

	private boolean menexistCategory(String type, String category,
			List<String> checkingCategory) {
		if ("Dudes Clearance".equalsIgnoreCase(type)) {
			if ("GRAPHIC SHIRTS".equalsIgnoreCase(category)
					|| "SHORT SLEEVE T-SHIRTS".equalsIgnoreCase(category)
					|| "LONG SLEEVE T-SHIRTS".equalsIgnoreCase(category))
				return existCategory("T-SHIRTS", checkingCategory);

			if ("DENIM".equalsIgnoreCase(category)
					|| "PLAID".equalsIgnoreCase(category)
					|| "CLASSIC".equalsIgnoreCase(category)) {
				return existCategory("SHIRTS", checkingCategory);
			}

			if ("CLASSIC SHORTS".equalsIgnoreCase(category)
					|| "ATHLETIC SHORTS".equalsIgnoreCase(category)) {
				return existCategory("SHORTS", checkingCategory);
			}

			return existCategory(category, checkingCategory);

		} else if ("Dudes Sale".equalsIgnoreCase(type)) {
			if ("T-SHIRTS & TANKS".equalsIgnoreCase(category))
				return existCategory("T-SHIRTS", checkingCategory);

			return existCategory(category, checkingCategory);

		} else if ("Dudes Secret Sale".equalsIgnoreCase(type)) {
			return true;
		} else {
			// regular
			String cate = type.replaceAll("Dudes", "").trim();
			return existCategory(cate, checkingCategory);
		}

	}

	private boolean womenexistCategory(String type, String category,
			List<String> checkingCategory) {
		if ("Bettys Clearance".equalsIgnoreCase(type)) {
			if ("SLEEVELESS".equalsIgnoreCase(category)
					|| "SHORT SLEEVE".equalsIgnoreCase(category)
					|| "LONG SLEEVE".equalsIgnoreCase(category))
				return existCategory("FASHION TOPS", checkingCategory);

			if ("BRALETTES & BANDEAUS".equalsIgnoreCase(category)
					|| "TANKS".equalsIgnoreCase(category)
					|| "SHORT SLEEVE".equalsIgnoreCase(category)
					|| "LONG SLEEVE".equalsIgnoreCase(category)) {
				return existCategory("SHIRTS", checkingCategory);
			}

			if ("DENIM".equalsIgnoreCase(category)
					|| "PLAID".equalsIgnoreCase(category)
					|| "CLASSIC".equalsIgnoreCase(category)
					|| "FLANNEL".equalsIgnoreCase(category)) {
				return existCategory("SHIRTS", checkingCategory);
			}

			if ("DRESSES".equalsIgnoreCase(category)) {
				return existCategory("DRESSES & ROMPERS", checkingCategory);
			}

			if ("TOPS".equalsIgnoreCase(category)
					|| "BOTTOMS".equalsIgnoreCase(category)
					|| "ONE PIECE".equalsIgnoreCase(category)) {
				return existCategory("SWIM", checkingCategory);
			}

			return existCategory(category, checkingCategory);

		} else if ("Bettys Sale".equalsIgnoreCase(type)) {
			if ("DRESSES".equalsIgnoreCase(category)) {
				return existCategory("DRESSES & ROMPERS", checkingCategory);
			}

			return existCategory(category, checkingCategory);

		} else if ("Bettys Secret Sale".equalsIgnoreCase(type)) {
			return true;
		} else {
			// regular
			String cate = type.replaceAll("Bettys", "").trim();
			return existCategory(cate, checkingCategory);
		}

	}

	private boolean existCategory(String category, List<String> checkingCategory) {
		for (int i = 0; i < checkingCategory.size(); i++) {
			if (category.equalsIgnoreCase(checkingCategory.get(i))) {
				return true;
			}
		}
		return false;
	}

	private boolean existSize(HollistercoProduct hollistercoProduct,
			List<String> checkingSize) {
		if (hollistercoProduct.inventoryList == null
				|| hollistercoProduct.inventoryList.size() == 0)
			return true;

		for (int i = 0; i < hollistercoProduct.inventoryList.size(); i++) {
			Inventory inventory = hollistercoProduct.inventoryList.get(i);
			String mapSize = maptoSize(inventory.itemSize);
			for (int j = 0; j < checkingSize.size(); j++) {
				if (mapSize.equalsIgnoreCase(checkingSize.get(j))) {
					return true;
				}
			}
		}

		return false;
	}

	private String maptoSize(String itemSize) {
		if (itemSize.indexOf("000") == 0) {
			return "000";
		}
		if (itemSize.indexOf("00") == 0) {
			return "00";
		}
		if (itemSize.indexOf("0") == 0) {
			return "0";
		}
		if ("2S".equalsIgnoreCase(itemSize) || "2R".equalsIgnoreCase(itemSize)
				|| "2L".equalsIgnoreCase(itemSize)) {
			return "2";
		}
		if ("4S".equalsIgnoreCase(itemSize) || "4R".equalsIgnoreCase(itemSize)
				|| "4L".equalsIgnoreCase(itemSize)) {
			return "4";
		}
		if ("6S".equalsIgnoreCase(itemSize) || "6R".equalsIgnoreCase(itemSize)
				|| "6L".equalsIgnoreCase(itemSize)) {
			return "6";
		}
		if ("8S".equalsIgnoreCase(itemSize) || "8R".equalsIgnoreCase(itemSize)
				|| "8L".equalsIgnoreCase(itemSize)) {
			return "8";
		}
		if ("10S".equalsIgnoreCase(itemSize)
				|| "10R".equalsIgnoreCase(itemSize)
				|| "10L".equalsIgnoreCase(itemSize)) {
			return "10";
		}
		if ("12S".equalsIgnoreCase(itemSize)
				|| "12R".equalsIgnoreCase(itemSize)
				|| "12L".equalsIgnoreCase(itemSize)) {
			return "12";
		}
		return itemSize;
	}

	public static void main(String[] args) throws Exception {

		HollistercoMailListMongoDao dao = new HollistercoMailListMongoDao();
		HollistercoMailList amail = dao.find("xingang.hollisterco1@gmail.com");
		System.out.println(amail);

		ProxyUtli.setProxy(true);

		HashMap<Integer, HollistercoProduct> allmennewprolist = new HashMap<Integer, HollistercoProduct>();

		List<HollistercoPage> urllist = HollistercoPage.getWomenAllInstance(); // getClearanceInstance();getclearanceBySinglePageInstance
		for (int i = 0; i < urllist.size(); i++) {
			HollistercoPage hollistercopage = urllist.get(i);
			ParserHollistercoPage parserHollistercopage = new ParserHollistercoPage(
					hollistercopage, allmennewprolist);
			parserHollistercopage.checkprice();
		}
		System.out.println("found products:" + allmennewprolist.size());
		ArrayList<HollistercoProduct> matchprolist = new ArrayList<HollistercoProduct>(
				allmennewprolist.values());

		HollistercoUtil util = new HollistercoUtil();

		ArrayList<HollistercoProduct> matchprolist1 = util.checkuser(
				matchprolist, amail, false);
		System.out.println("meet products:" + matchprolist1.size());

	}
}

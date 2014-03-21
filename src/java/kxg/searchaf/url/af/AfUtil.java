package kxg.searchaf.url.af;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import kxg.searchaf.util.ProxyUtli;

import org.json.JSONArray;
import org.json.JSONObject;

public class AfUtil {

	public ArrayList<AfProduct> checkuser(ArrayList<AfProduct> matchprolist,
			AfMailList amail, boolean isMen) {
		ArrayList<AfProduct> usermatch = new ArrayList<AfProduct>();
		for (int i = 0; i < matchprolist.size(); i++) {
			AfProduct afProduct = matchprolist.get(i);
			if (meetingprod(afProduct, amail, isMen)) {
				usermatch.add(afProduct);
			}
		}
		return usermatch;
	}

	private boolean meetingprod(AfProduct afProduct, AfMailList amail,
			boolean isMen) {
		if (isMen) {
			return menmeetingprod(afProduct, amail);
		} else {
			return womenmeetingprod(afProduct, amail);
		}
	}

	private boolean menmeetingprod(AfProduct afProduct, AfMailList amail) {
		if ("Mens Clearance".equalsIgnoreCase(afProduct.type)) {
			if (!amail.mencheckingClearance)
				return false;
			if (afProduct.realdiscount > amail.mencheckingClearanceDiscount)
				return false;
		} else if ("Mens Sale".equalsIgnoreCase(afProduct.type)) {
			if (!amail.mencheckingSale)
				return false;
			if (afProduct.realdiscount > amail.mencheckingSaleDiscount)
				return false;
		} else if ("Mens Secret Sale".equalsIgnoreCase(afProduct.type)) {
			if (!amail.mencheckingSecretSale)
				return false;
		} else {
			if (!amail.mencheckingRegular)
				return false;
		}
		if (amail.mencheckingCategory != null
				&& amail.mencheckingCategory.size() != 15) {
			if (!menexistCategory(afProduct.type, afProduct.category,
					amail.mencheckingCategory))
				return false;
		}
		if (amail.mencheckingSize != null && amail.mencheckingSize.size() != 34) {
			if (!existSize(afProduct, amail.mencheckingSize))
				return false;
		}
		return true;
	}

	private boolean womenmeetingprod(AfProduct afProduct, AfMailList amail) {
		if ("Womens Clearance".equalsIgnoreCase(afProduct.type)) {
			if (!amail.womencheckingClearance)
				return false;
			if (afProduct.realdiscount > amail.womencheckingClearanceDiscount)
				return false;
		} else if ("Womens Sale".equalsIgnoreCase(afProduct.type)) {
			if (!amail.womencheckingSale)
				return false;
			if (afProduct.realdiscount > amail.womencheckingSaleDiscount)
				return false;
		} else if ("Womens Secret Sale".equalsIgnoreCase(afProduct.type)) {
			if (!amail.womencheckingSecretSale)
				return false;
		} else {
			if (!amail.womencheckingRegular)
				return false;
		}
		if (amail.womencheckingCategory != null
				&& amail.womencheckingCategory.size() != 21) {
			if (!womenexistCategory(afProduct.type, afProduct.category,
					amail.womencheckingCategory))
				return false;
		}
		if (amail.womencheckingSize != null
				&& amail.womencheckingSize.size() != 16) {
			if (!existSize(afProduct, amail.womencheckingSize))
				return false;
		}
		return true;
	}

	private boolean menexistCategory(String type, String category,
			List<String> checkingCategory) {

		if ("Mens Clearance".equalsIgnoreCase(type)) {
			if ("GRAPHIC TEES".equalsIgnoreCase(category)
					|| "TANKS".equalsIgnoreCase(category)
					|| "SHORT SLEEVE TEES".equalsIgnoreCase(category)
					|| "LONG SLEEVE TEES".equalsIgnoreCase(category))
				return existCategory("TEES", checkingCategory);

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

		} else if ("Mens Sale".equalsIgnoreCase(type)) {
			if ("TEES & TANKS".equalsIgnoreCase(category))
				return existCategory("TEES", checkingCategory);

			return existCategory(category, checkingCategory);

		} else if ("Mens Secret Sale".equalsIgnoreCase(type)) {
			return true;
		} else {
			// regular
			String cate = type.replaceAll("Mens", "").trim();
			return existCategory(cate, checkingCategory);
		}

	}

	private boolean womenexistCategory(String type, String category,
			List<String> checkingCategory) {

		if ("Womens Clearance".equalsIgnoreCase(type)) {
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

		} else if ("Womens Sale".equalsIgnoreCase(type)) {
			if ("DRESSES".equalsIgnoreCase(category)) {
				return existCategory("DRESSES & ROMPERS", checkingCategory);
			}

			return existCategory(category, checkingCategory);

		} else if ("Womens Secret Sale".equalsIgnoreCase(type)) {
			return true;
		} else {
			// regular
			String cate = type.replaceAll("Womens", "").trim();
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

	private boolean existSize(AfProduct afProduct, List<String> checkingSize) {
		if (afProduct.inventoryList == null
				|| afProduct.inventoryList.size() == 0)
			return true;

		for (int i = 0; i < afProduct.inventoryList.size(); i++) {
			Inventory inventory = afProduct.inventoryList.get(i);
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

		AfMailListMongoDao dao = new AfMailListMongoDao();
		AfMailList amail = dao.find("xingang.af2@gmail.com");
		System.out.println(amail);
		amail.warningMen = true;
		amail.warningWomen = true;
		//
		// ProxyUtli.setProxy(true);
		//
		// HashMap<Integer, AfProduct> allmennewprolist = new HashMap<Integer,
		// AfProduct>();
		//
		// List<AfPage> urllist = AfPage.getWomenAllInstance(); //
		// getClearanceInstance();getclearanceBySinglePageInstance
		// for (int i = 0; i < urllist.size(); i++) {
		// AfPage afpage = urllist.get(i);
		// ParserAfPage parserAfpage = new ParserAfPage(afpage,
		// allmennewprolist);
		// parserAfpage.checkprice();
		// }
		// System.out.println("found products:" + allmennewprolist.size());
		// ArrayList<AfProduct> matchprolist = new ArrayList<AfProduct>(
		// allmennewprolist.values());
		//
		// AfUtil util = new AfUtil();
		//
		// ArrayList<AfProduct> matchprolist1 = util.checkuser(matchprolist,
		// amail, false);
		// System.out.println("meet products:" + matchprolist1.size());

		AfUtil util = new AfUtil();
		ArrayList<AfProduct> matchprolist = new ArrayList<AfProduct>();
		AfProduct product = new AfProduct();
		product.type = "Mens Clearance";
		product.category = "Tees1";
		product.name = "test";
		product.price = 100;
		product.listprice = 100;
		product.realdiscount = 1;
		matchprolist.add(product);
		//
		// AfMailList amail = new AfMailList();
		//
		// amail.mailaddress = "test@test.com";
		// amail.userType = "buyer";
		// amail.warningCode = true;
		// amail.mencheckingClearance= true;
		// amail.mencheckingSale= true;
		// amail.mencheckingRegular= true;
		// amail.mencheckingSecretSale= true;
		// amail.mencheckingClearanceDiscount=1;
		// amail.mencheckingSaleDiscount=1;
		// amail.mencheckingCategory=['TEES', 'POLOS', 'SHIRTS', H'OODIES &
		// SWEATSHIRTS, SWEATERS, OUTERWEAR, SHORTS, JEANS, SWEATPANTS, PANTS,
		// SWIM, SLEEP, FLIP FLOPS, ACCESSORIES, UNDERWEAR];
		// amail.mencheckingSize=[XS, S, M, L, XL, XXL, 28 X 30, 28 X 32, 30 X
		// 30, 30 X 32, 30 X 34, 31 X 30, 31 X 32, 31 X 34, 32 X 30, 32 X 32, 32
		// X 34, 33 X 32, 33 X 34, 34 X 32, 34 X 34, 36 X 32, 36 X 34, 28, 30,
		// 31, 32, 33, 34, 36, 38, 1 SIZE, S/M, L/XL]
		//
		ArrayList<AfProduct> matchprolist1 = util.checkuser(matchprolist,
				amail, true);
		System.out.println("meet products:" + matchprolist1.size());

	}
}

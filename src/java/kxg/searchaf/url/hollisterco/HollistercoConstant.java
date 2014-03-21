package kxg.searchaf.url.hollisterco;

public class HollistercoConstant {
	public static float warningdiscount = 0.3f;

	// public static float shipfee = 0;

	public static String ProductInventoryUrl = "http://www.hollisterco.com/webapp/wcs/stores/servlet/GetColorJSON?catalogId=10201&storeId=10251&langId=-1&productId=";

	public static String[] ignoreCategory = { "Featured Items", "COLOGNE",
			"FRAGRANCE", "Cologne&Fragrance", "Body Care"
	// , "Underwear","Accessories"
	};

	public static boolean isIgnoreProduct(String category) {
		for (int i = 0; i < ignoreCategory.length; i++) {
			if (ignoreCategory[i].equalsIgnoreCase(category))
				return true;
		}
		return false;
	}

	public static String addtoCartUrl = "http://www.hollisterco.com/webapp/wcs/stores/servlet/OrderItemAdd?storeId=10051&catalogId=10901&langId=-1&URL=OrderCalculate%3FcalculationUsageId%3D-1%26URL%3DShopRestrict&quantity=1";

	public static long sleeptime = 60L; // minutes

	public static String checkingURLhost = "/shop/us/";

	public static int tryerLicenseDay = 300;

}

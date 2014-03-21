package kxg.searchaf.url.ralphlauren;

public class RalphlaurenConstant {
	public static float warningdiscount = 0.3f;

	public static String[] ignoreproductName = { "Sneaker", "Boot", "Flat" };

	public static boolean isIgnoreProduct(String productName) {
		for (int i = 0; i < ignoreproductName.length; i++) {
			if (productName.indexOf(ignoreproductName[i]) >= 0)
				return true;
		}
		return false;
	}

	public static long sleeptime = 120L; // minutes
	
	public static int tryerLicenseDay = 30;

}

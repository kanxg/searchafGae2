package kxg.searchaf.url.aptamil;

public class AptamilConstant {

	public static long sleeptime = 60L; // minutes

	public static String[] CheckProduct = {
			"Pre (300 g), 1 Stück",
			"Pre (300 g), 8 Stück",
			"Pre (800 g), 1 Stück",
			"Pre (800 g), 4 Stück",
			"1 (800 g), 1 Stück",
			"1 (800 g), 4 Stück",
			"2 (800 g), 1 Stück",
			"2 (800 g), 4 Stück",
			"3 (800 g), 1 Stück",
			"3 (800 g), 4 Stück",
			"Kinder-Milch 1 plus (300 g), 1 Stück",
			"Kinder-Milch 1 plus (300 g), 8 Stück",
			"Kinder-Milch 1 plus (600 g), 1 Stück",
			"Kinder-Milch 1 plus (600 g), 4 Stück",
			"Kinder-Milch 2 plus (600 g), 1 Stück",
			"Kinder-Milch 2 plus (600 g), 4 Stück",

			"Pre BIO Anfangsmilch (600 g), 1 Stück",
			"Pre BIO Anfangsmilch (600 g), 4 Stück",

			"1 BIO Anfangsmilch (600 g), 1 Stück",
			"1 BIO Anfangsmilch (600 g), 4 Stück",

			"2 BIO Folgemilch (800 g), 1 Stück",
			"2 BIO Folgemilch (800 g), 4 Stück",

			"3 BIO Folgemilch (800 g), 1 Stück",
			"3 BIO Folgemilch (800 g), 4 Stück",

			"Kindermilch BIO (800 g), 1 Stück",
			"Kindermilch BIO (800 g), 4 Stück",

			// "Pre BIO Combiotik Anfangsmilch trinkfertig (200 ml), 1 Stück",
			// "Pre BIO Combiotik Anfangsmilch trinkfertig (200 ml), 12 Stück",
			"Pre BIO Combiotik Anfangsmilch (600 g), 1 Stück",
			"Pre BIO Combiotik Anfangsmilch (600 g), 4 Stück",

			"1 BIO Combiotik Anfangsmilch (600 g), 1 Stück",
			"1 BIO Combiotik Anfangsmilch (600 g), 4 Stück",

			"2 BIO Combiotik Folgemilch (600 g), 1 Stück",
			"2 BIO Combiotik Folgemilch (600 g), 4 Stück",
			"2 BIO Combiotik Folgemilch Stärkefrei (600 g), 1 Stück",
			"2 BIO Combiotik Folgemilch Stärkefrei (600 g), 4 Stück",

			"3 BIO Combiotik Folgemilch (600 g), 1 Stück",
			"3 BIO Combiotik Folgemilch (600 g), 4 Stück",

			"Kindermilch BIO Combiotik (600 g), 1 Stück",
			"Kindermilch BIO Combiotik (600 g), 4 Stück",
			"Kindermilch BIO Combiotik Lactosefrei (500 g), 1 Stück",
			"Kindermilch BIO Combiotik Lactosefrei (500 g), 4 Stück",

			"Kindermilch 2 BIO Combiotik (600 g), 1 Stück",
			"Kindermilch 2 BIO Combiotik (600 g), 4 Stück" };

	public static boolean isCheckProduct(String prd_name) {
		for (int i = 0; i < CheckProduct.length; i++) {
			if (CheckProduct[i].equalsIgnoreCase(prd_name))
				return true;
		}
		return false;
	}

	public static String[] ignoreProduct = { "Pre BIO Combiotik Anfangsmilch trinkfertig (200 ml)" };

	public static boolean ignoreProduct(String prd_name) {
		for (int i = 0; i < ignoreProduct.length; i++) {
			if (prd_name.indexOf(ignoreProduct[i]) >= 0)
				return true;
			// if (ignoreProduct[i].equalsIgnoreCase(prd_name))
			// return true;
		}
		return false;
	}

}

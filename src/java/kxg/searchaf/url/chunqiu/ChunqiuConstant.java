package kxg.searchaf.url.chunqiu;

public class ChunqiuConstant {
	public static float warningdiscount = 0.3f;

	// public static float shipfee = 0;

	public static long sleeptime = 60L; // minutes

	public static String[] checkingHangban = { "9C8921", "9C8960", "9C8922",
			"9C8917" };

	public static boolean ischeckingHangban(String hangban) {
		for (int i = 0; i < checkingHangban.length; i++) {
			if (checkingHangban[i].equalsIgnoreCase(hangban))
				return true;
		}
		return true;
	}
}

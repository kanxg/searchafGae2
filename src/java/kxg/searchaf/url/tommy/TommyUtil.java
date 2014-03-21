package kxg.searchaf.url.tommy;

import java.util.ArrayList;

public class TommyUtil {

	public ArrayList<TommyProduct> checkuser(
			ArrayList<TommyProduct> matchprolist,
			TommyMailList amail, String ttype) {
		ArrayList<TommyProduct> usermatch = new ArrayList<TommyProduct>();
		if ("men".equalsIgnoreCase(ttype) && amail.warningMen) {
			for (int i = 0; i < matchprolist.size(); i++) {
				TommyProduct afProduct = matchprolist.get(i);
				if (afProduct.realdiscount <= amail.mencheckingSaleDiscount) {
					usermatch.add(afProduct);
				}
			}
		}
		if ("women".equalsIgnoreCase(ttype) && amail.warningWomen) {
			for (int i = 0; i < matchprolist.size(); i++) {
				TommyProduct afProduct = matchprolist.get(i);
				if (afProduct.realdiscount <= amail.womencheckingSaleDiscount) {
					usermatch.add(afProduct);
				}
			}
		}
		if ("boy".equalsIgnoreCase(ttype) && amail.warningBoy) {
			for (int i = 0; i < matchprolist.size(); i++) {
				TommyProduct afProduct = matchprolist.get(i);
				if (afProduct.realdiscount <= amail.boycheckingSaleDiscount) {
					usermatch.add(afProduct);
				}
			}
		}
		if ("girl".equalsIgnoreCase(ttype) && amail.warningGirl) {
			for (int i = 0; i < matchprolist.size(); i++) {
				TommyProduct afProduct = matchprolist.get(i);
				if (afProduct.realdiscount <= amail.girlcheckingSaleDiscount) {
					usermatch.add(afProduct);
				}
			}
		}
		return usermatch;
	}

}

package kxg.searchaf.url.gymboree;

import java.util.ArrayList;

public class GymboreeUtil {

	public ArrayList<GymboreeProduct> checkuser(
			ArrayList<GymboreeProduct> matchprolist,
			GymboreeMailList amail, String ttype) {
		ArrayList<GymboreeProduct> usermatch = new ArrayList<GymboreeProduct>();
		//babyboy
		if ("men".equalsIgnoreCase(ttype) && amail.warningMen) {
			for (int i = 0; i < matchprolist.size(); i++) {
				GymboreeProduct afProduct = matchprolist.get(i);
				if (afProduct.realdiscount <= amail.mencheckingSaleDiscount) {
					usermatch.add(afProduct);
				}
			}
		}
		//baby girl
		if ("women".equalsIgnoreCase(ttype) && amail.warningWomen) {
			for (int i = 0; i < matchprolist.size(); i++) {
				GymboreeProduct afProduct = matchprolist.get(i);
				if (afProduct.realdiscount <= amail.womencheckingSaleDiscount) {
					usermatch.add(afProduct);
				}
			}
		}
		if ("boy".equalsIgnoreCase(ttype) && amail.warningBoy) {
			for (int i = 0; i < matchprolist.size(); i++) {
				GymboreeProduct afProduct = matchprolist.get(i);
				if (afProduct.realdiscount <= amail.boycheckingSaleDiscount) {
					usermatch.add(afProduct);
				}
			}
		}
		if ("girl".equalsIgnoreCase(ttype) && amail.warningGirl) {
			for (int i = 0; i < matchprolist.size(); i++) {
				GymboreeProduct afProduct = matchprolist.get(i);
				if (afProduct.realdiscount <= amail.girlcheckingSaleDiscount) {
					usermatch.add(afProduct);
				}
			}
		}
		return usermatch;
	}

}

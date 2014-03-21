package kxg.searchaf.url.jcrew;

import java.util.ArrayList;

public class JcrewUtil {

	public ArrayList<JcrewProduct> checkuser(
			ArrayList<JcrewProduct> matchprolist,
			JcrewMailList amail, String ttype) {
		ArrayList<JcrewProduct> usermatch = new ArrayList<JcrewProduct>();
		if ("men".equalsIgnoreCase(ttype) && amail.warningMen) {
			for (int i = 0; i < matchprolist.size(); i++) {
				JcrewProduct afProduct = matchprolist.get(i);
				if (afProduct.realdiscount <= amail.mencheckingSaleDiscount) {
					usermatch.add(afProduct);
				}
			}
		}
		if ("women".equalsIgnoreCase(ttype) && amail.warningWomen) {
			for (int i = 0; i < matchprolist.size(); i++) {
				JcrewProduct afProduct = matchprolist.get(i);
				if (afProduct.realdiscount <= amail.womencheckingSaleDiscount) {
					usermatch.add(afProduct);
				}
			}
		}
		if ("boy".equalsIgnoreCase(ttype) && amail.warningBoy) {
			for (int i = 0; i < matchprolist.size(); i++) {
				JcrewProduct afProduct = matchprolist.get(i);
				if (afProduct.realdiscount <= amail.boycheckingSaleDiscount) {
					usermatch.add(afProduct);
				}
			}
		}
		if ("girl".equalsIgnoreCase(ttype) && amail.warningGirl) {
			for (int i = 0; i < matchprolist.size(); i++) {
				JcrewProduct afProduct = matchprolist.get(i);
				if (afProduct.realdiscount <= amail.girlcheckingSaleDiscount) {
					usermatch.add(afProduct);
				}
			}
		}
		return usermatch;
	}

}

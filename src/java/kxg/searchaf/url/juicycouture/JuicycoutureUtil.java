package kxg.searchaf.url.juicycouture;

import java.util.ArrayList;

public class JuicycoutureUtil {

	public ArrayList<JuicycoutureProduct> checkuser(ArrayList<JuicycoutureProduct> matchprolist,
			JuicycoutureMailList amail ) {
		ArrayList<JuicycoutureProduct> usermatch = new ArrayList<JuicycoutureProduct>();
		for (int i = 0; i < matchprolist.size(); i++) {
			JuicycoutureProduct afProduct = matchprolist.get(i);
			if (afProduct.realdiscount <= amail.womencheckingSaleDiscount){
				usermatch.add(afProduct);
			}
 
		}
		return usermatch;
	}

	 
}

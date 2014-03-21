package kxg.searchaf.url.victoriassecret;

import java.util.ArrayList;

public class VictoriassecretUtil {

	public ArrayList<VictoriassecretProduct> checkuser(ArrayList<VictoriassecretProduct> matchprolist,
			VictoriassecretMailList amail ) {
		ArrayList<VictoriassecretProduct> usermatch = new ArrayList<VictoriassecretProduct>();
		for (int i = 0; i < matchprolist.size(); i++) {
			VictoriassecretProduct afProduct = matchprolist.get(i);
			if (afProduct.realdiscount <= amail.womencheckingSaleDiscount){
				usermatch.add(afProduct);
			}
 
		}
		return usermatch;
	}

	 
}

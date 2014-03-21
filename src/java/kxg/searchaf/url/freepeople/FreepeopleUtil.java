package kxg.searchaf.url.freepeople;

import java.util.ArrayList;

public class FreepeopleUtil {

	public ArrayList<FreepeopleProduct> checkuser(ArrayList<FreepeopleProduct> matchprolist,
			FreepeopleMailList amail ) {
		ArrayList<FreepeopleProduct> usermatch = new ArrayList<FreepeopleProduct>();
		for (int i = 0; i < matchprolist.size(); i++) {
			FreepeopleProduct afProduct = matchprolist.get(i);
			if (afProduct.realdiscount <= amail.womencheckingSaleDiscount){
				usermatch.add(afProduct);
			}
 
		}
		return usermatch;
	}

	 
}

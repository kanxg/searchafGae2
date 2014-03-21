package kxg.searchaf.url.toryburch;

import java.util.ArrayList;

public class ToryburchUtil {

	public ArrayList<ToryburchProduct> checkuser(ArrayList<ToryburchProduct> matchprolist,
			ToryburchMailList amail ) {
		ArrayList<ToryburchProduct> usermatch = new ArrayList<ToryburchProduct>();
		for (int i = 0; i < matchprolist.size(); i++) {
			ToryburchProduct afProduct = matchprolist.get(i);
			if (afProduct.realdiscount <= amail.womencheckingSaleDiscount){
				usermatch.add(afProduct);
			}
 
		}
		return usermatch;
	}

	 
}

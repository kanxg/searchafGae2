package kxg.searchaf.url.chunqiu;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import kxg.searchaf.util.TimeUtli;

public class SearchChunqiuJSP {

	public static void main(String[] args) {
		SearchChunqiuJSP af = new SearchChunqiuJSP();

		// while (true) {
		try {

			// // System.out.println("start checking...");
			// List<ChunqiuLaihuiResult> result = af.getMixHangbanWangfan("HKG",
			// "SHA", 5);
			// for (int i = 0; i < result.size(); i++) {
			// ChunqiuLaihuiResult tmp = result.get(i);
			// System.out.println(tmp);
			// }
			// result = af.getMixHangbanWangfan("HKG", "SHA", 6);
			// for (int i = 0; i < result.size(); i++) {
			// ChunqiuLaihuiResult tmp = result.get(i);
			// System.out.println(tmp);
			// }

			List<ChunqiuProduct> result1 = af.getMixHangban("HKG", "SHA");
			for (int i = 0; i < result1.size(); i++) {
				ChunqiuProduct tmp = result1.get(i);
				System.out.println(tmp);
			}
			
			result1 = af.getMixHangban("SHA", "HKG");
			for (int i = 0; i < result1.size(); i++) {
				ChunqiuProduct tmp = result1.get(i);
				System.out.println(tmp);
			}
			// Thread.sleep(1000 * 60 * Constant.sleeptime); // sleep 10 mines
		} catch (Exception e) {
			e.printStackTrace();
		}
		// }
	}

	public List<ChunqiuProduct> getMixHangban(String fromAdd, String toAdd) {
		if (fromAdd.equals("HKG") && toAdd.equals("SHA")) {
			List<ChunqiuProduct> qu = getHangban(ChunqiuPage.getHKG_SHA());
			Collections.sort(qu);
			return qu;
		}
		if (fromAdd.equals("SHA") && toAdd.equals("HKG")) {
			List<ChunqiuProduct> qu = getHangban(ChunqiuPage.getSHA_HKG());
			Collections.sort(qu);
			return qu;
		}
		return null;
	}

	public List<ChunqiuLaihuiResult> getMixHangbanWangfan(String quAdd,
			String huiAdd, int intervalDay) {
		if (quAdd.equals("HKG") && huiAdd.equals("SHA")) {
			List<ChunqiuProduct> qu = getHangban(ChunqiuPage.getSHA_HKG());
			List<ChunqiuProduct> hui = getHangban(ChunqiuPage.getHKG_SHA());
			return checkMixprice(qu, hui, intervalDay);
		}
		return null;
	}

	public List<ChunqiuLaihuiResult> checkMixprice(List<ChunqiuProduct> qu,
			List<ChunqiuProduct> hui, int intervalDay) {
		List<ChunqiuLaihuiResult> returnkey = new ArrayList<ChunqiuLaihuiResult>();
		for (int i = 0; i < qu.size(); i++) {
			ChunqiuProduct quHangban = qu.get(i);
			for (int j = 0; j < hui.size(); j++) {
				ChunqiuProduct huiHangban = hui.get(j);
				// get qu date
				// get hui date
				// hui -qu=invervalDay, add returnkey
				try {
					if (TimeUtli.getDays(quHangban.qifeitime,
							huiHangban.qifeitime) == intervalDay) {
						ChunqiuLaihuiResult rs = new ChunqiuLaihuiResult(
								quHangban, huiHangban);
						returnkey.add(rs);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
		}
		Collections.sort(returnkey);
		return returnkey;
	}

	public List<ChunqiuProduct> getHangban(List<ChunqiuPage> urllist) {
		List<ChunqiuProduct> returnkey = new ArrayList<ChunqiuProduct>();
		HashMap<String, ChunqiuProduct> allprolist = new HashMap<String, ChunqiuProduct>();
		try {
			for (int i = 0; i < urllist.size(); i++) {
				ChunqiuPage ChunqiuPage = urllist.get(i);
				ParserChunqiuPage ParserChunqiuPage = new ParserChunqiuPage(
						ChunqiuPage, allprolist);
				ParserChunqiuPage.checkprice();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<String> entryKeyIterator = allprolist.keySet().iterator();
		while (entryKeyIterator.hasNext()) {
			String key = entryKeyIterator.next();
			ChunqiuProduct product = allprolist.get(key);
			returnkey.add(product);
		}
		return returnkey;
	}

}

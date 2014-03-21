package kxg.searchaf.url.chunqiu;

import java.util.ArrayList;
import java.util.List;

public class ChunqiuPage {

	public String url;

	public ChunqiuPage(String url) {
		this.url = url;
	}

	public static List<ChunqiuPage> getSHA_HKG() {
		ArrayList<ChunqiuPage> urllist = new ArrayList<ChunqiuPage>();

		// 上海 -- 香港
		String url = "http://www.china-sss.com/SHA_HKG_False_28";
		ChunqiuPage page = new ChunqiuPage(url);
		urllist.add(page);

		url = "http://www.china-sss.com/SHA_HKG_False_89";
		page = new ChunqiuPage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<ChunqiuPage> getHKG_SHA() {
		ArrayList<ChunqiuPage> urllist = new ArrayList<ChunqiuPage>();

		// 香港  --  上海
		String url = "http://www.china-sss.com/HKG_SHA_False_28";
		ChunqiuPage page = new ChunqiuPage(url);
		urllist.add(page);

		url = "http://www.china-sss.com/HKG_SHA_False_89";
		page = new ChunqiuPage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<ChunqiuPage> getAll() {
		ArrayList<ChunqiuPage> urllist = new ArrayList<ChunqiuPage>();

		// 上海 -- 香港
		String url = "http://www.china-sss.com/SHA_HKG_False_28";
		ChunqiuPage page = new ChunqiuPage(url);
		urllist.add(page);

		url = "http://www.china-sss.com/SHA_HKG_False_89";
		page = new ChunqiuPage(url);
		urllist.add(page);

		// 香港 -- 上海
		url = "http://www.china-sss.com/HKG_SHA_False_28";
		page = new ChunqiuPage(url);
		urllist.add(page);

		url = "http://www.china-sss.com/HKG_SHA_False_89";
		page = new ChunqiuPage(url);
		urllist.add(page);

		// 上海-- 深圳
		url = "http://www.china-sss.com/SHA_SZX_False_28";
		page = new ChunqiuPage(url);
		urllist.add(page);

		url = "http://www.china-sss.com/SHA_SZX_False_89";
		page = new ChunqiuPage(url);
		urllist.add(page);

		return urllist;
	}

}

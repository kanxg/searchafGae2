package kxg.searchaf.url.amazonWeibo;

import java.util.ArrayList;
import java.util.List;

public class AmazonSearchPage {

	public String url;
	public String category;

	public AmazonSearchPage(String category, String url) {
		this.category = category;
		this.url = url;
	}

	public static List<AmazonSearchPage> getInstance() {
		List<AmazonSearchPage> list = new ArrayList<AmazonSearchPage>();
		list.add(new AmazonSearchPage(
				"蓝牙耳机",
				"http://www.amazon.com/b/ref=wrl_ln_head?ie=UTF8&node=2407775011&pf_rd_m=ATVPDKIKX0DER&pf_rd_s=left-2&pf_rd_r=14JJWRFB2QBS4ZM3888J&pf_rd_t=101&pf_rd_p=1478751342&pf_rd_i=2335752011"));
		return list;
	}
}

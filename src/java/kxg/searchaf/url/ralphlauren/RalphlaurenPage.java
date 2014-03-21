package kxg.searchaf.url.ralphlauren;

import java.util.ArrayList;
import java.util.List;

public class RalphlaurenPage {
	public String Category;
	public String type; // clearance or sale
	public String url;
 
	public RalphlaurenPage() {

	}

	public static List<RalphlaurenPage> getBoysInstance() {
		ArrayList<RalphlaurenPage> urllist = new ArrayList<RalphlaurenPage>();
		String type = "sale";

		// Boy
		String category = "Boy 8-20";
		String url = "http://www.ralphlauren.com/family/index.jsp?view=399&categoryId=12480297&s=A-StorePrice-POLO&pg=1";
		RalphlaurenPage page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Boy 2-7";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=399&pg=1&categoryId=12480295";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		// Baby
		category = "Infant Boy";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=399&pg=1&categoryId=12480336";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Layette Boy";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=399&pg=1&categoryId=12480336";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		return urllist;
	}

	public static List<RalphlaurenPage> getGirlsInstance() {
		ArrayList<RalphlaurenPage> urllist = new ArrayList<RalphlaurenPage>();
		String type = "sale";

		// Baby
		String category = "Infant Baby Girl";
		String url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=399&pg=1&categoryId=12480374";
		RalphlaurenPage page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		// KID
		category = "Girl 2-6x";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=399&pg=1&categoryId=12480298";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Girl 7-16";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=399&pg=1&categoryId=12480299";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Layette Girl";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=399&pg=1&categoryId=12480372";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		return urllist;
	}

	public static List<RalphlaurenPage> getMenSaleByBrand() {
		ArrayList<RalphlaurenPage> urllist = new ArrayList<RalphlaurenPage>();
		String type = "sale";

		String category = "polo ralph lauren";
		String url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=399&categoryId=3607674&pg=1";
		RalphlaurenPage page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Denim";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=399&categoryId=12253832&pg=1";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		return urllist;
	}

	public static List<RalphlaurenPage> getWomenSaleByBrand() {
		ArrayList<RalphlaurenPage> urllist = new ArrayList<RalphlaurenPage>();
		String type = "sale";

		String category = "lauren";
		String url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=399&categoryId=11084907&pg=1";
		RalphlaurenPage page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Lauren Petite";
		url = "http://www.ralphlauren.com/family/index.jsp?categoryId=10931539&view=399&cp=2943768&ab=ln_nodivision_cs10_laurenpetite(sizes2%9614)";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Lauren Woman";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=399&categoryId=10931540&pg=1";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Denim";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=399&categoryId=12254000&pg=1";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		return urllist;
	}

	public static List<RalphlaurenPage> getMenSale() {

		ArrayList<RalphlaurenPage> urllist = new ArrayList<RalphlaurenPage>();
		String type = "sale";

		String category = "POLO";
		String url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=1&categoryId=12618507";
		RalphlaurenPage page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "POLO"; // page 2
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=2&categoryId=12618507";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Sport Shirt";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=1&categoryId=12618509";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Sport Shirt"; // page 2
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=2&categoryId=12618509";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Dress Shirt";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=1&categoryId=12997217";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Jacket & Outwear";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=1&categoryId=4216762";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Sweaters"; // 1766320
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=1&categoryId=1766320";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Sweaters"; // page 2
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=2&categoryId=1766320";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Sweatshirts & T-shirts"; // 13183412
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=1&categoryId=13183412";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Rugbys"; // 2551783
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=1&categoryId=2551783";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Pants"; // 1766319
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=1&categoryId=1766319";
		page = new RalphlaurenPage(type, category, url);
		// urllist.add(page);

		category = "Chinos";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=1&categoryId=3599821";
		page = new RalphlaurenPage(type, category, url);
		// urllist.add(page);

		category = "Jeans";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=1&categoryId=2061050";
		page = new RalphlaurenPage(type, category, url);
		// urllist.add(page);

		category = "Shorts";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=1&categoryId=1785464";
		page = new RalphlaurenPage(type, category, url);
		// urllist.add(page);

		category = "Swim";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=1&categoryId=1803513";
		page = new RalphlaurenPage(type, category, url);
		// urllist.add(page);

		category = "Sport Coat";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=1&categoryId=12993134";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Suits";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=1&categoryId=13080553";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "underwear";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=1&categoryId=1785475";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Sleepwear & Robes";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=1&categoryId=15474366";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		// Shoes And Accessories

		category = "Shoes";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=1&categoryId=2018030";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Hats";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=1&categoryId=11618129";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Bags";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=1&categoryId=11757767";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Small leather thing";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=1&categoryId=12342421";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		category = "Belts";
		url = "http://www.ralphlauren.com/family/index.jsp?s=A-StorePrice-POLO&view=all&pg=1&categoryId=11618127";
		page = new RalphlaurenPage(type, category, url);
		urllist.add(page);

		return urllist;

	}

	public RalphlaurenPage(String type, String Category, String url) {
		this.type = type;
		this.Category = Category;
		this.url = url;
	}

}

package kxg.searchaf.url.af;

import java.util.ArrayList;
import java.util.List;

public class AfPage {

	public String url;

	public AfPage(String url) {
		this.url = url;
	}

	public static List<AfPage> getMenSaleBySinglePageInstance() {
		ArrayList<AfPage> urllist = new ArrayList<AfPage>();
		// String type = "sale";
		// String category = "";
		// String url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=68948&parentCategoryId=68948";
		String url = "http://www.abercrombie.com/shop/us/mens-sale";
		AfPage page = new AfPage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<AfPage> getMenclearanceBySinglePageInstance() {
		ArrayList<AfPage> urllist = new ArrayList<AfPage>();
		// String type = "clearance";
		// String category = "";
		// String url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=12204&parentCategoryId=12204";
		String url = "http://www.abercrombie.com/shop/us/mens-clearance";
		AfPage page = new AfPage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<AfPage> getMenSecretInstance() {
		ArrayList<AfPage> urllist = new ArrayList<AfPage>();
		// String type = "SECRET SALE";
		// String category = "";
		// String url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=86655&parentCategoryId=86655";
		String url = "http://www.abercrombie.com/shop/us/mens-secret-sale";
		AfPage page = new AfPage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<AfPage> getMenSearchPageInstance() {
		ArrayList<AfPage> urllist = new ArrayList<AfPage>();
		// String type = "search";
		// String category = "";
		String url = "http://www.abercrombie.com/webapp/wcs/stores/servlet/Search?storeId=10051&catalogId=10901&langId=-1&qs=?i=1;q=Hoodie;q1=Mens;storeId=10051;x1=category-1";
		AfPage page = new AfPage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<AfPage> getMenAllInstance() {
		ArrayList<AfPage> urllist = new ArrayList<AfPage>();
		// String type = "All";

		// FLAGSHIP EXCLUSIVES
		// String category = "TEES";
		// String url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=13050&catalogId=10901&categoryId=13050&langId=-1&storeId=10051&topCategoryId=12202";
		// String
		// url="http://www.abercrombie.com/shop/us/mens-flagship-exclusives";
		// AfPage page = new AfPage(url);
		// urllist.add(page);

		// TEES
		// String category = "TEES";
		// String url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=12837&parentCategoryId=12837";
		String url = "http://www.abercrombie.com/shop/us/mens-tees";
		AfPage page = new AfPage(url);
		urllist.add(page);

		// POLOS
		// category = "POLOS";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=87661&parentCategoryId=87661";
		url = "http://www.abercrombie.com/shop/us/mens-polos";
		page = new AfPage(url);
		urllist.add(page);

		// SHIRTS
		// category = "SHIRTS";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=87658&parentCategoryId=87658";
		url = "http://www.abercrombie.com/shop/us/mens-shirts";
		page = new AfPage(url);
		urllist.add(page);

		// HOODIES
		// category = "HOODIES";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=12351&parentCategoryId=12351";
		url = "http://www.abercrombie.com/shop/us/mens-hoodies-and-sweatshirts";
		page = new AfPage(url);
		urllist.add(page);

		// SWEATERS
		// category = "SWEATERS";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=12206&parentCategoryId=12206";
		url = "http://www.abercrombie.com/shop/us/mens-sweaters";
		page = new AfPage(url);
		urllist.add(page);

		// OUTERWEAR
		// category = "OUTERWEAR";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=12221&parentCategoryId=12221";
		url = "http://www.abercrombie.com/shop/us/mens-outerwear";
		page = new AfPage(url);
		urllist.add(page);

		// SHORTS
		// category = "SHORTS";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=60501&parentCategoryId=60501";
		url = "http://www.abercrombie.com/shop/us/mens-shorts";
		page = new AfPage(url);
		urllist.add(page);

		// JEANS
		// category = "JEANS";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=12236&parentCategoryId=12236";
		url = "http://www.abercrombie.com/shop/us/mens-jeans";
		page = new AfPage(url);
		urllist.add(page);

		// SWEATPANTS
		// category = "SWEATPANTS";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=12224&parentCategoryId=12224";
		url = "http://www.abercrombie.com/shop/us/mens-sweatpants";
		page = new AfPage(url);
		urllist.add(page);

		// PANTS
		// category = "PANTS";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=12227&parentCategoryId=12227";
		url = "http://www.abercrombie.com/shop/us/mens-pants";
		page = new AfPage(url);
		urllist.add(page);

		// SWIM
		// category = "SWIM";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=12230&parentCategoryId=12230";
		url = "http://www.abercrombie.com/shop/us/mens-swim";
		page = new AfPage(url);
		urllist.add(page);

		// FLIP FLOPS
		// category = "FLIP FLOPS";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=60499&parentCategoryId=60499";
		url = "http://www.abercrombie.com/shop/us/mens-flip-flops";
		page = new AfPage(url);
		urllist.add(page);

		// ACCESSORIES
		// category = "ACCESSORIES";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=13053&parentCategoryId=13053";
		url = "http://www.abercrombie.com/shop/us/mens-accessories";
		page = new AfPage(url);
		urllist.add(page);

		// SLEEP
		// category = "SLEEP";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=12223&parentCategoryId=12223";
		// page = new AfPage(url);
		// urllist.add(page);

		// UNDERWEAR
		// category = "UNDERWEAR";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=13196&parentCategoryId=13196";
		url = "http://www.abercrombie.com/shop/us/mens-underwear";
		page = new AfPage(url);
		urllist.add(page);

		// // BODY CARE
		// category = "BODY CARE";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=13048&parentCategoryId=13048";
		// page = new AfPage(url);
		// urllist.add(page);
		//
		// // COLOGNE
		// //category = "COLOGNE";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=12232&parentCategoryId=12232";
		// page = new AfPage(url);
		// urllist.add(page);

		return urllist;
	}

	public static List<AfPage> getWomenSaleBySinglePageInstance() {
		ArrayList<AfPage> urllist = new ArrayList<AfPage>();
		// String type = "sale";
		// String category = "";
		// String url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12203&categoryId=12840&parentCategoryId=12840";
		String url = "http://www.abercrombie.com/shop/us/womens-sale";
		AfPage page = new AfPage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<AfPage> getWomenclearanceBySinglePageInstance() {
		ArrayList<AfPage> urllist = new ArrayList<AfPage>();
		// String type = "clearance";
		// String category = "";
		// String url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12203&categoryId=12205&parentCategoryId=12205";
		String url = "http://www.abercrombie.com/shop/us/womens-clearance";
		AfPage page = new AfPage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<AfPage> getWomenSecretInstance() {
		ArrayList<AfPage> urllist = new ArrayList<AfPage>();
		// String type = "SECRET SALE";
		// String category = "";
		// String url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12203&categoryId=86656&parentCategoryId=86656";
		String url = "http://www.abercrombie.com/shop/us/womens-secret-sale";
		AfPage page = new AfPage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<AfPage> getWomenSearchPageInstance() {
		ArrayList<AfPage> urllist = new ArrayList<AfPage>();
		return urllist;
	}

	public static List<AfPage> getWomenAllInstance() {
		ArrayList<AfPage> urllist = new ArrayList<AfPage>();
		// FLAGSHIP EXCLUSIVES
		// String url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=13063&catalogId=10901&categoryId=13063&langId=-1&storeId=10051&topCategoryId=12203";
		// String
		// url="http://www.abercrombie.com/shop/us/womens-flagship-exclusives";
		// AfPage page = new AfPage(url);
		// urllist.add(page);

		// FASHION TOPS
		// String url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=120207&catalogId=10901&categoryId=120207&langId=-1&storeId=10051&topCategoryId=12203";
		String url = "http://www.abercrombie.com/shop/us/womens-fashion-tops";
		AfPage page = new AfPage(url);
		urllist.add(page);

		// LAYERING TEES & TANKS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=120206&catalogId=10901&categoryId=120206&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.abercrombie.com/shop/us/womens-tees-and-tanks";
		page = new AfPage(url);
		urllist.add(page);

		// GRAPHIC TEES
		url = "http://www.abercrombie.com/shop/us/womens-graphic-tees";
		page = new AfPage(url);
		urllist.add(page);

		// SHIRTS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=120205&catalogId=10901&categoryId=120205&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.abercrombie.com/shop/us/womens-shirts";
		page = new AfPage(url);
		urllist.add(page);

		// POLOS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=119705&catalogId=10901&categoryId=119705&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.abercrombie.com/shop/us/womens-polos";
		page = new AfPage(url);
		urllist.add(page);

		// HOODIES & SWEATSHIRTS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12843&catalogId=10901&categoryId=12843&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.abercrombie.com/shop/us/womens-hoodies-and-sweatshirts";
		page = new AfPage(url);
		urllist.add(page);

		// SWEATERS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12282&catalogId=10901&categoryId=12282&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.abercrombie.com/shop/us/womens-sweaters";
		page = new AfPage(url);
		urllist.add(page);

		// OUTERWEAR
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12252&catalogId=10901&categoryId=12252&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.abercrombie.com/shop/us/womens-outerwear";
		page = new AfPage(url);
		urllist.add(page);

		// SHORTS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12262&catalogId=10901&categoryId=12262&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.abercrombie.com/shop/us/womens-shorts";
		page = new AfPage(url);
		urllist.add(page);

		// JEANS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12261&catalogId=10901&categoryId=12261&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.abercrombie.com/shop/us/womens-jeans-135275";
		page = new AfPage(url);
		urllist.add(page);

		// SWEATPANTS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12272&catalogId=10901&categoryId=12272&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.abercrombie.com/shop/us/womens-sweatpants";
		page = new AfPage(url);
		urllist.add(page);

		// LEGGINGS
		url = "http://www.abercrombie.com/shop/us/womens-leggings";
		page = new AfPage(url);
		urllist.add(page);

		// PANTS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12267&catalogId=10901&categoryId=12267&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.abercrombie.com/shop/us/womens-pants";
		page = new AfPage(url);
		urllist.add(page);

		// YOGA
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=73463&catalogId=10901&categoryId=73463&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.abercrombie.com/shop/us/womens-yoga";
		page = new AfPage(url);
		urllist.add(page);

		// SKIRTS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=13058&catalogId=10901&categoryId=13058&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.abercrombie.com/shop/us/womens-skirts";
		page = new AfPage(url);
		urllist.add(page);

		// DRESSES
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12265&catalogId=10901&categoryId=12265&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.abercrombie.com/shop/us/womens-dresses";
		page = new AfPage(url);
		urllist.add(page);

		// SWIM
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=23598&catalogId=10901&categoryId=23598&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.abercrombie.com/shop/us/womens-swim";
		page = new AfPage(url);
		urllist.add(page);

		// FLIP FLOPS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=13056&catalogId=10901&categoryId=13056&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.abercrombie.com/shop/us/womens-flip-flops";
		page = new AfPage(url);
		urllist.add(page);

		// ACCESSORIES
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=13060&catalogId=10901&categoryId=13060&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.abercrombie.com/shop/us/womens-accessories";
		page = new AfPage(url);
		urllist.add(page);

		// BRAS & UNDIES
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12248&catalogId=10901&categoryId=12248&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.abercrombie.com/shop/us/womens-bras-and-undies";
		page = new AfPage(url);
		urllist.add(page);

		// BODYCARE
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=122941&catalogId=10901&categoryId=122941&langId=-1&storeId=10051&topCategoryId=12203";
		// page = new AfPage(url);
		// urllist.add(page);
		// FRAGRANCE
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12276&catalogId=10901&categoryId=12276&langId=-1&storeId=10051&topCategoryId=12203";
		// page = new AfPage(url);
		// urllist.add(page);

		return urllist;
	}

	public static List<AfPage> getBoyclearanceBySinglePageInstance() {
		ArrayList<AfPage> urllist = new ArrayList<AfPage>();
		// String type = "clearance";
		// String category = "";
		String url = "http://www.abercrombiekids.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12104&catalogId=10851&categoryId=12104&langId=-1&storeId=10101&topCategoryId=12102";
		AfPage page = new AfPage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<AfPage> getGirlclearanceBySinglePageInstance() {
		ArrayList<AfPage> urllist = new ArrayList<AfPage>();
		// String type = "clearance";
		// String category = "";
		String url = "http://www.abercrombiekids.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12105&catalogId=10851&categoryId=12105&langId=-1&storeId=10101&topCategoryId=12103";
		AfPage page = new AfPage(url);
		urllist.add(page);

		return urllist;
	}
}

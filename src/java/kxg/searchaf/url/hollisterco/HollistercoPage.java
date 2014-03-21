package kxg.searchaf.url.hollisterco;

import java.util.ArrayList;
import java.util.List;

public class HollistercoPage {

	public String url;

	public HollistercoPage(String url) {
		this.url = url;
	}

	public static List<HollistercoPage> getMenSaleBySinglePageInstance() {
		ArrayList<HollistercoPage> urllist = new ArrayList<HollistercoPage>();
		// String type = "sale";
		// String category = "";
		// String url =
		// "http://www.hollisterco.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10201&storeId=10251&langId=-1&topCategoryId=12551&categoryId=60539&parentCategoryId=60539";
		String url = "http://www.hollisterco.com/shop/us/dudes-sale";
		HollistercoPage page = new HollistercoPage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<HollistercoPage> getMenclearanceBySinglePageInstance() {
		ArrayList<HollistercoPage> urllist = new ArrayList<HollistercoPage>();
		// String type = "clearance";
		// String category = "";
		// String url =
		// "http://www.hollisterco.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10201&storeId=10251&langId=-1&topCategoryId=12551&categoryId=12634&parentCategoryId=12634";
		String url = "http://www.hollisterco.com/shop/us/dudes-clearance";
		HollistercoPage page = new HollistercoPage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<HollistercoPage> getMenSecretInstance() {
		ArrayList<HollistercoPage> urllist = new ArrayList<HollistercoPage>();
		// String type = "SECRET SALE";
		// String category = "";
		// String url =
		// "http://www.hollisterco.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10201&storeId=10251&langId=-1&topCategoryId=12551&categoryId=86658&parentCategoryId=86658";
		String url = "http://www.hollisterco.com/shop/us/dudes-secret-sale";
		HollistercoPage page = new HollistercoPage(url);
		urllist.add(page);

		return urllist;
	}

	public static List<HollistercoPage> getWomenSaleBySinglePageInstance() {
		ArrayList<HollistercoPage> urllist = new ArrayList<HollistercoPage>();
		// String url =
		// "http://www.hollisterco.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10201&storeId=10251&langId=-1&topCategoryId=12552&categoryId=60550&parentCategoryId=60550";
		String url = "http://www.hollisterco.com/shop/us/bettys-sale";
		HollistercoPage page = new HollistercoPage(url);
		urllist.add(page);
		return urllist;
	}

	public static List<HollistercoPage> getWomenclearanceBySinglePageInstance() {
		ArrayList<HollistercoPage> urllist = new ArrayList<HollistercoPage>();
		// String url =
		// "http://www.hollisterco.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10201&storeId=10251&langId=-1&topCategoryId=12552&categoryId=12635&parentCategoryId=12635";
		String url = "http://www.hollisterco.com/shop/us/bettys-clearance";
		HollistercoPage page = new HollistercoPage(url);
		urllist.add(page);
		return urllist;
	}

	public static List<HollistercoPage> getWomenSecretInstance() {
		ArrayList<HollistercoPage> urllist = new ArrayList<HollistercoPage>();
		// String url =
		// "http://www.hollisterco.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10201&storeId=10251&langId=-1&topCategoryId=12552&categoryId=86657&parentCategoryId=86657";
		String url = "http://www.hollisterco.com/shop/us/bettys-secret-sale";
		HollistercoPage page = new HollistercoPage(url);
		urllist.add(page);
		return urllist;
	}

	public static List<HollistercoPage> getMenAllInstance() {
		ArrayList<HollistercoPage> urllist = new ArrayList<HollistercoPage>();
		// String type = "All";

		// FLAGSHIP EXCLUSIVES
		// String category = "TEES";
		// String url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=13050&catalogId=10901&categoryId=13050&langId=-1&storeId=10051&topCategoryId=12202";
		// HollistercoPage page = new HollistercoPage(url);
		// urllist.add(page);

		// T-Shirts
		// String category = "T-Shirts";
		// String url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=12837&parentCategoryId=12837";
		String url = "http://www.hollisterco.com/shop/us/dudes-t-shirts";
		HollistercoPage page = new HollistercoPage(url);
		urllist.add(page);

		// Tanks
		// category = "Tanks";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=87661&parentCategoryId=87661";
		url = "http://www.hollisterco.com/shop/us/dudes-tanks";
		page = new HollistercoPage(url);
		urllist.add(page);

		// POLOS
		// category = "POLOS";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=87661&parentCategoryId=87661";
		url = "http://www.hollisterco.com/shop/us/dudes-polos";
		page = new HollistercoPage(url);
		urllist.add(page);

		// SHIRTS
		// category = "SHIRTS";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=87658&parentCategoryId=87658";
		url = "http://www.hollisterco.com/shop/us/dudes-shirts";
		page = new HollistercoPage(url);
		urllist.add(page);

		// HOODIES
		// category = "HOODIES";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=12351&parentCategoryId=12351";
		url = "http://www.hollisterco.com/shop/us/dudes-hoodies-and-sweatshirts";
		page = new HollistercoPage(url);
		urllist.add(page);

		// SWEATERS
		// category = "SWEATERS";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=12206&parentCategoryId=12206";
		url = "http://www.hollisterco.com/shop/us/dudes-sweaters";
		page = new HollistercoPage(url);
		urllist.add(page);

		// OUTERWEAR
		// category = "OUTERWEAR";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=12221&parentCategoryId=12221";
		url = "http://www.hollisterco.com/shop/us/dudes-outerwear";
		page = new HollistercoPage(url);
		urllist.add(page);

		// SHORTS
		// category = "SHORTS";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=60501&parentCategoryId=60501";
		url = "http://www.hollisterco.com/shop/us/dudes-shorts";
		page = new HollistercoPage(url);
		urllist.add(page);

		// JEANS
		// category = "JEANS";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=12236&parentCategoryId=12236";
		url = "http://www.hollisterco.com/shop/us/dudes-jeans";
		page = new HollistercoPage(url);
		urllist.add(page);

		// SWEATPANTS
		// category = "SWEATPANTS";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=12224&parentCategoryId=12224";
		url = "http://www.hollisterco.com/shop/us/dudes-sweatpants";
		page = new HollistercoPage(url);
		urllist.add(page);

		// PANTS
		// category = "PANTS";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=12227&parentCategoryId=12227";
		url = "http://www.hollisterco.com/shop/us/dudes-pants";
		page = new HollistercoPage(url);
		urllist.add(page);

		// SWIM
		// category = "SWIM";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=12230&parentCategoryId=12230";
		url = "http://www.hollisterco.com/shop/us/dudes-swim";
		page = new HollistercoPage(url);
		urllist.add(page);

		// FLIP FLOPS
		// category = "FLIP FLOPS";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=60499&parentCategoryId=60499";
		url = "http://www.hollisterco.com/shop/us/dudes-flip-flops";
		page = new HollistercoPage(url);
		urllist.add(page);

		// ACCESSORIES
		// category = "ACCESSORIES";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=13053&parentCategoryId=13053";
		url = "http://www.hollisterco.com/shop/us/dudes-accessories";
		page = new HollistercoPage(url);
		urllist.add(page);

		// SLEEP
		// category = "SLEEP";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=12223&parentCategoryId=12223";
		// page = new HollistercoPage(url);
		// urllist.add(page);

		// UNDERWEAR
		// category = "UNDERWEAR";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=13196&parentCategoryId=13196";
		url = "http://www.hollisterco.com/shop/us/dudes-underwear";
		page = new HollistercoPage(url);
		urllist.add(page);

		// // BODY CARE
		// category = "BODY CARE";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=13048&parentCategoryId=13048";
		// page = new HollistercoPage(url);
		// urllist.add(page);
		//
		// // COLOGNE
		// //category = "COLOGNE";
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?catalogId=10901&storeId=10051&langId=-1&topCategoryId=12202&categoryId=12232&parentCategoryId=12232";
		// page = new HollistercoPage(url);
		// urllist.add(page);

		return urllist;
	}

	public static List<HollistercoPage> getWomenAllInstance() {
		ArrayList<HollistercoPage> urllist = new ArrayList<HollistercoPage>();
		// FLAGSHIP EXCLUSIVES
		// String url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=13063&catalogId=10901&categoryId=13063&langId=-1&storeId=10051&topCategoryId=12203";
		// HollistercoPage page = new HollistercoPage(url);
		// urllist.add(page);

		// FASHION TOPS
		// String url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=120207&catalogId=10901&categoryId=120207&langId=-1&storeId=10051&topCategoryId=12203";
		String url = "http://www.hollisterco.com/shop/us/bettys-fashion-tops";
		HollistercoPage page = new HollistercoPage(url);
		urllist.add(page);

		// LAYERING TEES & TANKS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=120206&catalogId=10901&categoryId=120206&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.hollisterco.com/shop/us/bettys-t-shirts-and-tanks";
		page = new HollistercoPage(url);
		urllist.add(page);

		// GRAPHIC TEES
		url = "http://www.hollisterco.com/shop/us/bettys-graphic-t-shirts";
		page = new HollistercoPage(url);
		urllist.add(page);

		// SHIRTS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=120205&catalogId=10901&categoryId=120205&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.hollisterco.com/shop/us/bettys-shirts";
		page = new HollistercoPage(url);
		urllist.add(page);

		// POLOS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=119705&catalogId=10901&categoryId=119705&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.hollisterco.com/shop/us/bettys-polos";
		page = new HollistercoPage(url);
		urllist.add(page);

		// HOODIES & SWEATSHIRTS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12843&catalogId=10901&categoryId=12843&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.hollisterco.com/shop/us/bettys-hoodies-and-sweatshirts";
		page = new HollistercoPage(url);
		urllist.add(page);

		// SWEATERS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12282&catalogId=10901&categoryId=12282&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.hollisterco.com/shop/us/bettys-sweaters";
		page = new HollistercoPage(url);
		urllist.add(page);

		// OUTERWEAR
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12252&catalogId=10901&categoryId=12252&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.hollisterco.com/shop/us/bettys-outerwear";
		page = new HollistercoPage(url);
		urllist.add(page);

		// SHORTS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12262&catalogId=10901&categoryId=12262&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.hollisterco.com/shop/us/bettys-shorts";
		page = new HollistercoPage(url);
		urllist.add(page);

		// JEANS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12261&catalogId=10901&categoryId=12261&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.hollisterco.com/shop/us/bettys-jeans-135305";
		page = new HollistercoPage(url);
		urllist.add(page);

		// SWEATPANTS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12272&catalogId=10901&categoryId=12272&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.hollisterco.com/shop/us/bettys-sweatpants";
		page = new HollistercoPage(url);
		urllist.add(page);

		// LEGGINGS
		url = "http://www.hollisterco.com/shop/us/bettys-leggings";
		page = new HollistercoPage(url);
		urllist.add(page);

		// PANTS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12267&catalogId=10901&categoryId=12267&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.hollisterco.com/shop/us/bettys-pants";
		page = new HollistercoPage(url);
		urllist.add(page);

		// YOGA
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=73463&catalogId=10901&categoryId=73463&langId=-1&storeId=10051&topCategoryId=12203";
		// url = "http://www.abercrombie.com/shop/us/womens-yoga";
		// page = new HollistercoPage(url);
		// urllist.add(page);

		// SKIRTS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=13058&catalogId=10901&categoryId=13058&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.hollisterco.com/shop/us/bettys-skirts";
		page = new HollistercoPage(url);
		urllist.add(page);

		// DRESSES
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12265&catalogId=10901&categoryId=12265&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.hollisterco.com/shop/us/bettys-dresses-and-rompers";
		page = new HollistercoPage(url);
		urllist.add(page);

		// SWIM
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=23598&catalogId=10901&categoryId=23598&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.hollisterco.com/shop/us/bettys-swim";
		page = new HollistercoPage(url);
		urllist.add(page);

		// FLIP FLOPS
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=13056&catalogId=10901&categoryId=13056&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.hollisterco.com/shop/us/bettys-flip-flops";
		page = new HollistercoPage(url);
		urllist.add(page);

		// ACCESSORIES
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=13060&catalogId=10901&categoryId=13060&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.hollisterco.com/shop/us/bettys-accessories";
		page = new HollistercoPage(url);
		urllist.add(page);

		// BRAS & UNDIES
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12248&catalogId=10901&categoryId=12248&langId=-1&storeId=10051&topCategoryId=12203";
		url = "http://www.hollisterco.com/shop/us/bettys-bras-and-undies";
		page = new HollistercoPage(url);
		urllist.add(page);

		// BODYCARE
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=122941&catalogId=10901&categoryId=122941&langId=-1&storeId=10051&topCategoryId=12203";
		// page = new HollistercoPage(url);
		// urllist.add(page);
		// FRAGRANCE
		// url =
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=12276&catalogId=10901&categoryId=12276&langId=-1&storeId=10051&topCategoryId=12203";
		// page = new HollistercoPage(url);
		// urllist.add(page);

		return urllist;
	}

}

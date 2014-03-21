package kxg.searchaf.url.keepa;

import java.util.ArrayList;
import java.util.List;

public class AmazonBestSellers {

	public String amazonZone;
	public String category;
	public String url;
	static int pagenum = 6;

	public AmazonBestSellers() {
	}

	public AmazonBestSellers(String url) {
		this.url = url;
		if (this.url.indexOf("amazon.cn") > 0) {
			this.amazonZone = "amazon.cn";
		} else {
			this.amazonZone = "amazon.com";
		}
	}

	public AmazonBestSellers(String amazonZone, String category, String url) {
		this.amazonZone = amazonZone;
		this.category = category;
		this.url = url;
	}

	public List<AmazonBestSellers> getCategory(String category) {
		ArrayList<AmazonBestSellers> urllist = new ArrayList<AmazonBestSellers>();
		for(AmazonBestSellers item:getUsAll()){
			if(category.equals(item.category)){
				urllist.add(item);
			}
		}
		for(AmazonBestSellers item:getCnAll()){
			if(category.equals(item.category)){
				urllist.add(item);
			}
		}
		return urllist;
	}

	public static List<AmazonBestSellers> getUsAll() {
		ArrayList<AmazonBestSellers> urllist = new ArrayList<AmazonBestSellers>();
		String amazonZone = "amazon.com";
		AmazonBestSellers page = null;

		String category = "Appliances";
		String url = "http://www.amazon.com/Best-Sellers-Appliances/zgbs/appliances/ref=zg_bs_appliances_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Appstore for Android";
		url = "http://www.amazon.com/Best-Sellers-Appstore-Android/zgbs/mobile-apps/ref=zg_bs_mobile-apps_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Arts, Crafts & Sewing";
		url = "http://www.amazon.com/Best-Sellers-Arts-Crafts-Sewing/zgbs/arts-crafts/ref=zg_bs_arts-crafts_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Automotive";
		url = "http://www.amazon.com/Best-Sellers-Automotive/zgbs/automotive/ref=zg_bs_automotive_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Baby";
		url = "http://www.amazon.com/Best-Sellers-Baby/zgbs/baby-products/ref=zg_bs_baby-products_pg_4?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Beauty";
		url = "http://www.amazon.com/Best-Sellers-Beauty/zgbs/beauty/ref=zg_bs_beauty_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Books";
		url = "http://www.amazon.com/best-sellers-books-Amazon/zgbs/books/ref=zg_bs_books_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Camera & Photo";
		url = "http://www.amazon.com/best-sellers-camera-photo/zgbs/photo/ref=zg_bs_photo_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Cell Phones & Accessories";
		url = "http://www.amazon.com/Best-Sellers-Cell-Phones-Accessories/zgbs/wireless/ref=zg_bs_wireless_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Clothing";
		url = "http://www.amazon.com/Best-Sellers-Clothing/zgbs/apparel/ref=zg_bs_apparel_pg_3?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Computers & Accessories";
		url = "http://www.amazon.com/Best-Sellers-Computers-Accessories/zgbs/pc/ref=zg_bs_pc_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Electronics";
		url = "http://www.amazon.com/Best-Sellers-Electronics/zgbs/electronics/ref=zg_bs_electronics_pg_3?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Gift Cards Store";
		url = "http://www.amazon.com/Best-Sellers-Gift-Cards-Store/zgbs/gift-cards/ref=zg_bs_gift-cards_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Grocery & Gourmet Food";
		url = "http://www.amazon.com/Best-Sellers-Grocery-Gourmet-Food/zgbs/grocery/ref=zg_bs_grocery_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Health & Personal Care";
		url = "http://www.amazon.com/Best-Sellers-Health-Personal-Care/zgbs/hpc/ref=zg_bs_hpc_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Home & Kitchen";
		url = "http://www.amazon.com/Best-Sellers-Home-Kitchen/zgbs/home-garden/ref=zg_bs_home-garden_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Home Improvement";
		url = "http://www.amazon.com/Best-Sellers-Home-Improvement/zgbs/hi/ref=zg_bs_hi_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Industrial & Scientific";
		url = "http://www.amazon.com/Best-Sellers-Industrial-Scientific/zgbs/industrial/ref=zg_bs_industrial_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}
		category = "Jewelry";
		url = "http://www.amazon.com/Best-Sellers-Jewelry/zgbs/jewelry/ref=zg_bs_jewelry_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}
		category = "Kindle Store";
		url = "http://www.amazon.com/Best-Sellers-Kindle-Store/zgbs/digital-text/ref=zg_bs_digital-text_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Kitchen & Dining";
		url = "http://www.amazon.com/Best-Sellers-Kitchen-Dining/zgbs/kitchen/ref=zg_bs_kitchen_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		// MP3 Downloads

		category = "Magazines";
		url = "http://www.amazon.com/Best-Sellers-Magazines/zgbs/magazines/ref=zg_bs_magazines_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Movies & TV";
		url = "http://www.amazon.com/best-sellers-movies-TV-DVD-Blu-ray/zgbs/movies-tv/ref=zg_bs_movies-tv_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Music";
		url = "http://www.amazon.com/best-sellers-music-albums/zgbs/music/ref=zg_bs_music_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Musical Instruments";
		url = "http://www.amazon.com/Best-Sellers-Musical-Instruments/zgbs/musical-instruments/ref=zg_bs_musical-instruments_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Office Products";
		url = "http://www.amazon.com/Best-Sellers-Office-Products/zgbs/office-products/ref=zg_bs_office-products_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Patio, Lawn & Garden";
		url = "http://www.amazon.com/Best-Sellers-Patio-Lawn-Garden/zgbs/lawn-garden/ref=zg_bs_lawn-garden_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Pet Supplies";
		url = "http://www.amazon.com/Best-Sellers-Pet-Supplies/zgbs/pet-supplies/ref=zg_bs_pet-supplies_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "shoes";
		url = "http://www.amazon.com/best-sellers-shoes/zgbs/shoes/ref=zg_bs_shoes_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Software";
		url = "http://www.amazon.com/best-sellers-software/zgbs/software/ref=zg_bs_software_pg_3?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Sports & Outdoors";
		url = "http://www.amazon.com/Best-Sellers-Sports-Outdoors/zgbs/sporting-goods/ref=zg_bs_sporting-goods_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Toys & Games";
		url = "http://www.amazon.com/Best-Sellers-Toys-Games/zgbs/toys-and-games/ref=zg_bs_toys-and-games_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Video Games";
		url = "http://www.amazon.com/best-sellers-video-games/zgbs/videogames/ref=zg_bs_videogames_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Watches";
		url = "http://www.amazon.com/Best-Sellers-Watches/zgbs/watches/ref=zg_bs_watches_pg_2?_encoding=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		// category = "";
		// url = "";
		// for (int i = 1; i < pagenum; i++) {
		// String url1 = url + i;
		// page = new AmazonBestSellers(amazonZone, category, url1);
		// urllist.add(page);
		// }

		return urllist;
	}

	public static List<AmazonBestSellers> getCnAll() {
		ArrayList<AmazonBestSellers> urllist = new ArrayList<AmazonBestSellers>();
		String amazonZone = "amazon.cn";
		String category = "大家电";
		String url = "http://www.amazon.cn/gp/bestsellers/appliances/ref=zg_bs_appliances_pg_2?ie=UTF8&pg=";
		AmazonBestSellers page = null;
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}
		category = "电视 、音响";
		url = "http://www.amazon.cn/gp/bestsellers/audio-video/ref=zg_bs_audio-video_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "Kindle商店";
		url = "http://www.amazon.cn/gp/bestsellers/digital-text/ref=zg_bs_digital-text_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "个护健康";
		url = "http://www.amazon.cn/gp/bestsellers/hpc/ref=zg_bs_hpc_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "乐器";
		url = "http://www.amazon.cn/gp/bestsellers/musical-instruments/ref=zg_bs_musical-instruments_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "亚马逊应用商店";
		url = "http://www.amazon.cn/gp/bestsellers/mobile-apps/ref=zg_bs_mobile-apps_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "办公用品";
		url = "http://www.amazon.cn/gp/bestsellers/office-products/ref=zg_bs_office-products_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "化妆";
		url = "http://www.amazon.cn/gp/bestsellers/beauty/ref=zg_bs_beauty_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "厨具";
		url = "http://www.amazon.cn/gp/bestsellers/kitchen/ref=zg_bs_kitchen_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "图书";
		url = "http://www.amazon.cn/gp/bestsellers/books/ref=zg_bs_books_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "宠物用品";
		url = "http://www.amazon.cn/gp/bestsellers/pet-supplies/ref=zg_bs_pet-supplies_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "家居";
		url = "http://www.amazon.cn/gp/bestsellers/home-garden/ref=zg_bs_home-garden_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "家居装修";
		url = "http://www.amazon.cn/gp/bestsellers/home-improvement/ref=zg_bs_home-improvement_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "小家电";
		url = "http://www.amazon.cn/gp/bestsellers/home-appliances/ref=zg_bs_home-appliances_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "影视";
		url = "http://www.amazon.cn/gp/bestsellers/video/ref=zg_bs_video_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}
 

		category = "手机/通讯";
		url = "http://www.amazon.cn/gp/bestsellers/wireless/ref=zg_bs_wireless_pg_3?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}
		category = "摄影/摄像";
		url = "http://www.amazon.cn/gp/bestsellers/photo/ref=zg_bs_photo_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}
		category = "数码影音";
		url = "http://www.amazon.cn/gp/bestsellers/music-players/ref=zg_bs_music-players_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}
		category = "服饰箱包";
		url = "http://www.amazon.cn/gp/bestsellers/apparel/ref=zg_bs_apparel_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}
		category = "母婴用品";
		url = "http://www.amazon.cn/gp/bestsellers/baby/ref=zg_bs_baby_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "汽车用品";
		url = "http://www.amazon.cn/gp/bestsellers/automotive/ref=zg_bs_automotive_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "游戏/娱乐";
		url = "http://www.amazon.cn/gp/bestsellers/videogames/ref=zg_bs_videogames_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}
		
		category = "玩具";
		url = "http://www.amazon.cn/gp/bestsellers/toys-and-games/ref=zg_bs_toys-and-games_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}
		
		category = "珠宝首饰";
		url = "http://www.amazon.cn/gp/bestsellers/jewelry/ref=zg_bs_jewelry_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}
		
		category = "电子";
		url = "http://www.amazon.cn/gp/bestsellers/electronics/ref=zg_bs_electronics_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "电脑/IT/办公";
		url = "http://www.amazon.cn/gp/bestsellers/pc/ref=zg_bs_pc_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}
		
		category = "软件";
		url = "http://www.amazon.cn/gp/bestsellers/software/ref=zg_bs_software_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "运动户外休闲";
		url = "http://www.amazon.cn/gp/bestsellers/sporting-goods/ref=zg_bs_sporting-goods_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "钟表";
		url = "http://www.amazon.cn/gp/bestsellers/watch/ref=zg_bs_watch_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "鞋靴";
		url = "http://www.amazon.cn/gp/bestsellers/shoes/ref=zg_bs_shoes_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "音乐";
		url = "http://www.amazon.cn/gp/bestsellers/music/ref=zg_bs_music_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		category = "食品";
		url = "http://www.amazon.cn/gp/bestsellers/grocery/ref=zg_bs_grocery_pg_2?ie=UTF8&pg=";
		for (int i = 1; i < pagenum; i++) {
			String url1 = url + i;
			page = new AmazonBestSellers(amazonZone, category, url1);
			urllist.add(page);
		}

		// category = "";
		// url = "";
		// for (int i = 1; i < pagenum; i++) {
		// String url1 = url + i;
		// page = new AmazonBestSellers(amazonZone, category, url1);
		// urllist.add(page);
		// }
		return urllist;
	}
}

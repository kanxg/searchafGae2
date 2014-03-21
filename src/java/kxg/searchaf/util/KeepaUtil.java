package kxg.searchaf.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import kxg.searchaf.url.keepa.AmazonProduct;
import kxg.searchaf.url.keepa.ParserDeepa;

public class KeepaUtil {

	public static final long baseline = 24923;
	public static final GregorianCalendar calendar;
	static {
		calendar = new GregorianCalendar();
		calendar.clear();
		calendar.set(Calendar.YEAR, 2013);
		calendar.set(Calendar.MONTH, 10);
		calendar.set(Calendar.DAY_OF_MONTH, 4);
		// calendar.set(Calendar.HOUR, 12);
		// calendar.set(Calendar.MINUTE, 12);
		// System.out.println("Calendar   is:" + calendar.getTime());

	}

	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	public static long getTimeLine(Date d) {

		// long difference = Math.abs(d.getTime() - calendar.getTimeInMillis());
		long difference = d.getTime() - calendar.getTimeInMillis();
		// long day = difference / (3600 * 24 * 1000);
		long hour = difference / (3600 * 1000);
		// long minute = difference / (60 * 1000);
		// long second = difference / 1000;
		// System.out.println("day is:" + day);
		// System.out.println("hour is:" + hour);
		// System.out.println("minute is:" + minute);
		// System.out.println("second is:" + second);
		return baseline + hour;
	}

	public static String[] getValidPriceList(String[] pricehistory, int howlong) {
		List<String> validpircelist = new ArrayList<String>();
		Date today = new Date();
		Date beginDate = getDateBefore(today, howlong);
		long startline = getTimeLine(beginDate);
		for (int i = 0; i < pricehistory.length; i++) {
			if (i % 2 == 0) {
				long priceline = Long.valueOf(pricehistory[i]);
				long price = Long.valueOf(pricehistory[i + 1]);
				if (priceline > startline) {
					validpircelist.add(String.valueOf(priceline));
					validpircelist.add(String.valueOf(price));
				}
			}
		}
		return validpircelist.toArray(new String[validpircelist.size()]);
	}

	public static boolean isMinPirce(long startline, String[] pricehistory,
			float currentprice) {
		if (currentprice < 0)
			return false;
		if (pricehistory == null || pricehistory.length == 0)
			return false;
		long minprice = 0;
		long maxprice = 0;
		for (int i = 0; i < pricehistory.length - 2; i++) {
			if (i % 2 == 0) {
				long priceline = Long.valueOf(pricehistory[i]);
				long price = Long.valueOf(pricehistory[i + 1]);
				if (price < 0)
					continue;
				if (priceline > startline) {
					// 有效价格
					if (minprice == 0 && maxprice == 0) {
						minprice = price;
						maxprice = price;
					} else {
						if (price > maxprice)
							maxprice = price;
						if (price < minprice)
							minprice = price;
					}
				}
			}
		}
		if (minprice == maxprice)
			return false;

		// System.out.println(maxprice);
		// System.out.println(minprice);
		// System.out.println(currentprice);

		if (minprice <= currentprice * 100) {
			return false;
		}
		return true;
	}

	/**
	 * @param args
	 * @throws ParseException
	 * 
	 */
	public static void main(String[] args) throws ParseException {
		Date today = new Date();
		// long todayline = getTimeLine(today);
		System.out.println("today   is:" + today);
		// System.out.println("today line is:" + todayline);
		// java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
		// "yyyy-MM-dd");
		// java.util.Date beginDate = format.parse("2012-11-04");
		Date beginDate = getDateBefore(today, 90);
		System.out.println("beginDate   is:" + beginDate);

		long dline = getTimeLine(beginDate);
		System.out.println("beginDate line is:" + dline);
		AmazonProduct product = new AmazonProduct();
		product.ASIN = "B00857SGRO";
		product.amazonZone = "amazon.cn";
		ParserDeepa parse = new ParserDeepa(product);
		parse.checkAmazonPriceList();
		System.out.println(product);
		boolean ismixprice = isMinPirce(dline, product.pricelist,
				product.currentprice);
		System.out.println("ismixprice is:" + ismixprice);

	}
}

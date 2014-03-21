package kxg.searchaf.url.amazon;

import java.util.Calendar;
import java.util.Date;

public class testAmazon {

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// AmazonPage oldpage = new AmazonPage();
		// oldpage.instock = true;
		// oldpage.expectPirce = 120.1f;
		// oldpage.price = 131.123f;
		//
		// AmazonPage page = new AmazonPage();
		// page.instock = true;
		// page.expectPirce = 130.122f;
		// page.price = 131.113f;
		//
		// if (page.instock) {
		// if (!oldpage.instock && page.expectPirce >= page.price) {
		// System.out.println("meet stock!");
		// } else {
		// if (oldpage.price > page.price
		// && page.expectPirce >= page.price)
		// System.out.println("meet price!");
		//
		// }
		// }
		// Calendar calendar = Calendar.getInstance();
		//
		// System.out.println(calendar.getTimeInMillis());
		// calendar.add(Calendar.MONTH, -3);
		// System.out.println(calendar.getTime());
		// System.out.println(calendar.getTimeInMillis());

		int s[] = { 5402, 29900, 5486, 33900, 5504, 29900, 5674, 33900, 5794,
				29900, 5816, 28400, 5863, 28900, 5961, 28400, 6011, 33900,
				6019, 36900, 6368, 29900, 7174, -1, 9596, 31900, 9636, 32600,
				9657, 31900, 9661, 30400, 9697, 31800, 10139, 30400, 10208,
				33900, 10238, 31900, 10272, 30400, 10323, 35900, 10377, 33900,
				10397, 29900, 10504, 30400, 11463, 29900, 11482, 26900, 12255,
				35900, 12275, 30400, 12324, 35900, 12339, 30390, 12667, 30900,
				13027, 30400, 13054, 29900, 14676, 25900, 14702, 29900, 15938,
				26900, 24821, 29900, 24954, 26900, 24988, 22900, 25000, 25900,
				25095, 19900 };
		System.out.println(s.length);
		for (int ss : s) {
			// System.out.println(ss);
		}

		int s2[] = { 17144, 128800, 17222, 129900, 17320, 128800, 17355,
				129900, 17438, 118800, 17461, 120900, 17650, 129900, 17697,
				129800, 17871, 128800, 17899, 119900, 18226, 122800, 18294,
				129800, 18961, 129900, 18985, 129800, 19017, 129600, 19061,
				134610, 19064, 129600, 19069, 134610, 19089, 129800, 19248,
				122800, 19439, 129800, 19453, 128800, 19471, 129800, 19542,
				128800, 19576, 129800, 19653, 128800, 19733, 129800, 20201,
				119800, 20209, 129800, 20705, 128800, 21382, 119900, 21633,
				129800, 21635, 143900, 21659, 130550, 21662, 129800, 21778,
				119900, 21996, 109900, 22061, 119800, 22086, 115800, 22130,
				119700, 22244, 115800, 22267, 107800, 22330, 108800, 22684,
				114900, 22835, 119600, 22874, 108800, 22929, 114300, 22943,
				114200, 22984, 108800, 23056, 107900, 23109, 114200, 23343,
				119600, 23753, 109900, 23934, 119600, 23968, 114900, 24099,
				109900, 24238, 108000, 24414, 114800, 24512, 119800, 24543,
				109000, 24589, 109900, 24615, 108800, 24632, 109900, 24762,
				108000, 24872, 119800, 24940, 108000, 25014, 109900, 25046,
				108000, 25082, 105000 };
		System.out.println(s2.length);
		for (int ss : s2) {
			// System.out.println(ss);
		}

	}
}

package kxg.searchaf.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtli {

	public static int getDays(String from, String to) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date quTime = df.parse(from);
		Date huiTime = df.parse(to);
		Calendar fc = Calendar.getInstance();
		fc.setTime(quTime);
		Calendar tc = Calendar.getInstance();
		tc.setTime(huiTime);
		long t1 = fc.getTimeInMillis();
		long t2 = tc.getTimeInMillis();
		// 计算天数
		long days = (t2 - t1) / (24 * 60 * 60 * 1000);
		return Integer.parseInt(new Long(days).toString());
	}

	public static void main(String[] args) {
		try {
			int days = TimeUtli.getDays("2013-08-30", "2013-09-01");
			System.out.println(days);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String dateToString(Date time) {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		String ctime = formatter.format(time);

		return ctime;
	}
}

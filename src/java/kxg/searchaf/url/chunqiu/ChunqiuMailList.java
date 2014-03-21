package kxg.searchaf.url.chunqiu;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChunqiuMailList {
	private static List<ChunqiuMailList> maillist;

	public static String mail_subject = "春秋航班  监视系统提醒";

	public static String mailto = "watch_chunqiu@163.com";

	public String mailaddress;
	public Date valideTime;

	public ChunqiuMailList(String mailaddress, Date valideTime) {
		this.mailaddress = mailaddress;
		this.valideTime = valideTime;
	}

	public static List<ChunqiuMailList> getinstance() {
		if (maillist == null) {
			maillist = new ArrayList<ChunqiuMailList>();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			// try {
			// maillist.add(new ChunqiuMailList("kanqiduo@163.com", df
			// .parse("2014-06-01")));
			//
			// } catch (ParseException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		}
		return maillist;

		// maillist.add(new AfMailList("kanqiduo@163.com", new Date(2013, 1,
		// 9)));

	}

	public static boolean HasLicense(String validateEmail) {
		if (validateEmail == null || "".equals(validateEmail))
			return false;

		Date currentDate = new Date();
		List<ChunqiuMailList> afmailist = ChunqiuMailList.getinstance();
		for (int i = 0; i < afmailist.size(); i++) {
			ChunqiuMailList amail = afmailist.get(i);
			if (validateEmail.equalsIgnoreCase(amail.mailaddress)) {
				if (amail.valideTime.after(currentDate)) {
					return true;
				}
			}

		}
		return false;
	}
}

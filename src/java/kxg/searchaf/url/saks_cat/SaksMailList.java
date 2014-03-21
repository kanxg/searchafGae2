package kxg.searchaf.url.saks_cat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaksMailList {
	private static List<SaksMailList> maillist;

	public static String mail_subject = "Saks 监视系统提醒";

	public static String mailto = "watch_saks@163.com";

	public String mailaddress;
	public Date valideTime;

	public SaksMailList(String mailaddress, Date valideTime) {
		this.mailaddress = mailaddress;
		this.valideTime = valideTime;
	}

	public static List<SaksMailList> getinstance() {
		if (maillist == null) {
			maillist = new ArrayList<SaksMailList>();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			// try {
			// // maillist.add(new SaksMailList("xingang.kan@Gmail.com", true,
			// // true, df.parse("2013-06-01")));
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
		List<SaksMailList> afmailist = SaksMailList.getinstance();
		for (int i = 0; i < afmailist.size(); i++) {
			SaksMailList amail = afmailist.get(i);
			if (validateEmail.equalsIgnoreCase(amail.mailaddress)) {
				if (amail.valideTime.after(currentDate)) {
					return true;
				}
			}

		}
		return false;
	}
}

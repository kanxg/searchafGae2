package kxg.searchaf.url.neiman_cat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NeimanMailList {
	private static List<NeimanMailList> maillist;

	public static String mail_subject = "Neiman 监视系统提醒";

	public static String mailto = "watch_neiman@163.com";

	public String mailaddress;
	public Date valideTime;

	public NeimanMailList(String mailaddress, Date valideTime) {
		this.mailaddress = mailaddress;
		this.valideTime = valideTime;
	}

	public static List<NeimanMailList> getinstance() {
		if (maillist == null) {
			maillist = new ArrayList<NeimanMailList>();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			// try {
			// // maillist.add(new NeimanMailList("xingang.kan@Gmail.com", true,
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
		List<NeimanMailList> afmailist = NeimanMailList.getinstance();
		for (int i = 0; i < afmailist.size(); i++) {
			NeimanMailList amail = afmailist.get(i);
			if (validateEmail.equalsIgnoreCase(amail.mailaddress)) {
				if (amail.valideTime.after(currentDate)) {
					return true;
				}
			}

		}
		return false;
	}
}

package kxg.searchaf.url.sephora_search;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SephoraMailList {
	private static List<SephoraMailList> maillist;

	public static String mail_subject = "Sephora 监视系统提醒";

	public static String mailto = "watch_sephora@163.com";

	public String mailaddress;
	public Date valideTime;

	public SephoraMailList(String mailaddress, Date valideTime) {
		this.mailaddress = mailaddress;
		this.valideTime = valideTime;
	}

	public static List<SephoraMailList> getinstance() {
		if (maillist == null) {
			maillist = new ArrayList<SephoraMailList>();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				maillist.add(new SephoraMailList("877053052@qq.com", df
						.parse("2013-07-01")));

				maillist.add(new SephoraMailList("zaizai619@126.com", df
						.parse("2013-07-01")));

				maillist.add(new SephoraMailList("381822862@qq.com", df
						.parse("2013-07-01")));

				maillist.add(new SephoraMailList("303931027@qq.com", df
						.parse("2013-07-01")));
				

				maillist.add(new SephoraMailList("78361110@qq.com", df
						.parse("2013-07-01")));

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return maillist;

	}

	public static boolean HasLicense(String validateEmail) {
		if (validateEmail == null || "".equals(validateEmail))
			return false;

		Date currentDate = new Date();
		List<SephoraMailList> afmailist = SephoraMailList.getinstance();
		for (int i = 0; i < afmailist.size(); i++) {
			SephoraMailList amail = afmailist.get(i);
			if (validateEmail.equalsIgnoreCase(amail.mailaddress)) {
				if (amail.valideTime.after(currentDate)) {
					return true;
				}
			}

		}
		return false;
	}
}

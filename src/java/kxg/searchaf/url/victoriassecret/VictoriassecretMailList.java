package kxg.searchaf.url.victoriassecret;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import kxg.searchaf.mail.SystemMail;
import kxg.searchaf.url.victoriassecret.VictoriassecretMailList;
import kxg.searchaf.url.victoriassecret.VictoriassecretMailListMongoDao;

public class VictoriassecretMailList implements Comparable<Object> {
	private static List<VictoriassecretMailList> maillist;

	public static String mail_subject = "Victoriassecret  监视系统提醒";

	public static String mailto_women = "watch_victoriassecret@163.com";

	public String mailaddress;
	public Date valideTime;
	public String userType;
	public boolean warningWomen;
	public float womencheckingSaleDiscount;

	public VictoriassecretMailList() {

	}

	public VictoriassecretMailList(String mailaddress, Date valideTime) {
		this.mailaddress = mailaddress;
		this.valideTime = valideTime;

	}

	public static void sync() {
		try {
			VictoriassecretMailListMongoDao dao = new VictoriassecretMailListMongoDao();
			maillist = dao.findAll();
			Collections.sort(maillist);
		} catch (Exception e) {
			SystemMail.sendSystemMail("error when get mongdb,err:"
					+ e.getMessage());
		}

	}

	public static List<VictoriassecretMailList> getinstance() {
		if (maillist == null || maillist.size() == 0) {
			// maillist = getinstanceFromText();
			sync();
		}
		return maillist;
	}

	public static boolean HasLicense(String validateEmail) {
		if (validateEmail == null || "".equals(validateEmail))
			return false;

		Date currentDate = new Date();
		List<VictoriassecretMailList> victoriassecretmailist = VictoriassecretMailList
				.getinstance();
		for (int i = 0; i < victoriassecretmailist.size(); i++) {
			VictoriassecretMailList amail = victoriassecretmailist.get(i);
			if (validateEmail.equalsIgnoreCase(amail.mailaddress)) {
				if (amail.valideTime.after(currentDate)) {
					return true;
				}
			}

		}
		return false;
	}

	public int getSendingMailRetryTimes() {
		if ("buyer".equalsIgnoreCase(this.userType))
			return 1; // retry 3 times
		return 1; // not try
	}

	@Override
	public int compareTo(Object o) {
		VictoriassecretMailList comp = (VictoriassecretMailList) o;
		int flag = 0;
		flag = this.userType.compareTo(comp.userType);
		if (flag != 0)
			return flag;
		flag = comp.valideTime.compareTo(this.valideTime);
		if (flag != 0)
			return flag;
		return 0;
	}
}

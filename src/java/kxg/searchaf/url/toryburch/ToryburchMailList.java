package kxg.searchaf.url.toryburch;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import kxg.searchaf.mail.SystemMail;
import kxg.searchaf.url.toryburch.ToryburchMailList;
import kxg.searchaf.url.toryburch.ToryburchMailListMongoDao;

public class ToryburchMailList implements Comparable<Object> {
	private static List<ToryburchMailList> maillist;

	public static String mail_subject = "Toryburch  监视系统提醒";

	public static String mailto_women = "watch_toryburch@163.com";

	public String mailaddress;
	public Date valideTime;
	public String userType;
	public boolean warningWomen;
	public float womencheckingSaleDiscount;

	public ToryburchMailList() {

	}

	public ToryburchMailList(String mailaddress, Date valideTime) {
		this.mailaddress = mailaddress;
		this.valideTime = valideTime;

	}

	public static void sync() {
		try {
			ToryburchMailListMongoDao dao = new ToryburchMailListMongoDao();
			maillist = dao.findAll();
			Collections.sort(maillist);
		} catch (Exception e) {
			SystemMail.sendSystemMail("error when get mongdb,err:"
					+ e.getMessage());
		}

	}

	public static List<ToryburchMailList> getinstance() {
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
		List<ToryburchMailList> toryburchmailist = ToryburchMailList
				.getinstance();
		for (int i = 0; i < toryburchmailist.size(); i++) {
			ToryburchMailList amail = toryburchmailist.get(i);
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
		ToryburchMailList comp = (ToryburchMailList) o;
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

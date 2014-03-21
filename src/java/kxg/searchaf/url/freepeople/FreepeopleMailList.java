package kxg.searchaf.url.freepeople;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import kxg.searchaf.mail.SystemMail;
import kxg.searchaf.url.freepeople.FreepeopleMailList;
import kxg.searchaf.url.freepeople.FreepeopleMailListMongoDao;

public class FreepeopleMailList implements Comparable<Object> {
	private static List<FreepeopleMailList> maillist;

	public static String mail_subject = "Freepeople  监视系统提醒";

	public static String mailto_women = "watch_freepeople@163.com";

	public String mailaddress;
	public Date valideTime;
	public String userType;
	public boolean warningWomen;
	public float womencheckingSaleDiscount;

	public FreepeopleMailList() {

	}

	public FreepeopleMailList(String mailaddress, Date valideTime) {
		this.mailaddress = mailaddress;
		this.valideTime = valideTime;

	}

	public static void sync() {
		try {
			FreepeopleMailListMongoDao dao = new FreepeopleMailListMongoDao();
			maillist = dao.findAll();
			Collections.sort(maillist);
		} catch (Exception e) {
			SystemMail.sendSystemMail("error when get mongdb,err:"
					+ e.getMessage());
		}

	}

	public static List<FreepeopleMailList> getinstance() {
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
		List<FreepeopleMailList> freepeoplemailist = FreepeopleMailList
				.getinstance();
		for (int i = 0; i < freepeoplemailist.size(); i++) {
			FreepeopleMailList amail = freepeoplemailist.get(i);
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
		FreepeopleMailList comp = (FreepeopleMailList) o;
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

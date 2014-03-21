package kxg.searchaf.url.ralphlauren;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import kxg.searchaf.mail.SystemMail;
import kxg.searchaf.url.ralphlauren.RalphlaurenMailList;
import kxg.searchaf.url.ralphlauren.RalphlaurenMailListMongoDao;

public class RalphlaurenMailList implements Comparable<Object> {
	private static List<RalphlaurenMailList> maillist;

	public static String mail_subject = "Ralphlauren  监视系统提醒";

	public static String mailto_women = "watch_ralphlauren@163.com";

	public String mailaddress;
	public Date valideTime;
	public String userType;
	public boolean warningMen;
	public float mencheckingSaleDiscount;
	
	public boolean warningWomen;
	public float womencheckingSaleDiscount;

	public boolean warningBoy;
	public float boycheckingSaleDiscount;
	
	public boolean warningGirl;
	public float girlcheckingSaleDiscount;
	
	public RalphlaurenMailList() {

	}

	public RalphlaurenMailList(String mailaddress, Date valideTime) {
		this.mailaddress = mailaddress;
		this.valideTime = valideTime;

	}

	public static void sync() {
		try {
			RalphlaurenMailListMongoDao dao = new RalphlaurenMailListMongoDao();
			maillist = dao.findAll();
			Collections.sort(maillist);
		} catch (Exception e) {
			SystemMail.sendSystemMail("error when get mongdb,err:"
					+ e.getMessage());
		}

	}

	public static List<RalphlaurenMailList> getinstance() {
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
		List<RalphlaurenMailList> ralphlaurenmailist = RalphlaurenMailList
				.getinstance();
		for (int i = 0; i < ralphlaurenmailist.size(); i++) {
			RalphlaurenMailList amail = ralphlaurenmailist.get(i);
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
		RalphlaurenMailList comp = (RalphlaurenMailList) o;
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

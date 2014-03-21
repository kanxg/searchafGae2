package kxg.searchaf.url.af;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import kxg.searchaf.mail.SystemMail;
import kxg.searchaf.util.TimeUtli;

public class AfMailList implements Comparable<Object> {
	private static List<AfMailList> maillist;

	public static String mail_subject = "abercrombie&fitch 监视系统提醒";
	public static String mailto_men = "watch_af_men@163.com";
	public static String mailto_women = "watch_af_women@163.com";

	public String mailaddress;
	public String userType;

	public Date valideTime;

	public boolean warningMen;
	public boolean warningWomen;

	public boolean warningCode;

	public boolean mencheckingClearance;
	public boolean mencheckingSale;
	public boolean mencheckingRegular;
	public boolean mencheckingSecretSale;
	public float mencheckingClearanceDiscount;
	public float mencheckingSaleDiscount;
	public List<String> mencheckingCategory;
	public List<String> mencheckingSize;

	public boolean womencheckingClearance;
	public boolean womencheckingSale;
	public boolean womencheckingRegular;
	public boolean womencheckingSecretSale;
	public float womencheckingClearanceDiscount;
	public float womencheckingSaleDiscount;
	public List<String> womencheckingCategory;
	public List<String> womencheckingSize;
	// no use
	public long duplicateTime;
	public List<AfProduct> hasWaringProdList;

	public AfMailList() {

	}

	public AfMailList(String mailaddress, String userType, boolean warningMen,
			boolean warningWomen, Date valideTime) {
		this.mailaddress = mailaddress;
		this.userType = userType;
		this.valideTime = valideTime;
		this.warningMen = warningMen;
		this.warningWomen = warningWomen;

	}

	public static void sync() {
		try {
			AfMailListMongoDao dao = new AfMailListMongoDao();
			maillist = dao.findAll();
			Collections.sort(maillist);
		} catch (Exception e) {
			SystemMail.sendSystemMail("error when get mongdb,err:"
					+ e.getMessage());
		}

	}

	public static List<AfMailList> getinstance() {
		if (maillist == null || maillist.size() == 0) {
			// maillist = getinstanceFromText();
			sync();
		}
		return maillist;
	}

	public static List<AfMailList> getinstanceFromText() {
		if (maillist == null) {
			maillist = new ArrayList<AfMailList>();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				maillist.add(new AfMailList("xingang.af1@gmail.com", "buy",
						true, true, df.parse("2013-09-15")));

				maillist.add(new AfMailList("44506750@qq.com", "buy", true,
						true, df.parse("2013-09-15")));

				maillist.add(new AfMailList("lancexsp@hotmail.com", "buy",
						true, true, df.parse("2013-08-15")));

				maillist.add(new AfMailList("popbo@qq.com", "buy", true, true,
						df.parse("2013-09-10")));

				maillist.add(new AfMailList("411521092@qq.com", "buy", true,
						false, df.parse("2013-08-15")));

				maillist.add(new AfMailList("qjiang448@gmail.com", "buy", true,
						true, df.parse("2013-09-10")));

				maillist.add(new AfMailList("lwm62@msn.com", "buy", true, true,
						df.parse("2013-09-01")));

				maillist.add(new AfMailList("leochaolin@gmail.com", "buy",
						true, true, df.parse("2013-06-26")));

				maillist.add(new AfMailList("45413434@qq.com", "buy", true,
						false, df.parse("2013-06-03")));

				maillist.add(new AfMailList("17299478@qq.com", "buy", true,
						true, df.parse("2013-06-25")));

				maillist.add(new AfMailList("tinylxn@qq.com", "buy", true,
						true, df.parse("2013-07-04")));

				maillist.add(new AfMailList("qiaohu120720@163.com", "buy",
						true, true, df.parse("2013-07-16")));

				maillist.add(new AfMailList("jasonborn1980@gmail.com", "buy",
						true, false, df.parse("2013-07-20")));

				maillist.add(new AfMailList("308003936@qq.com", "buy", true,
						true, df.parse("2013-07-20")));

				maillist.add(new AfMailList("11298126@qq.com", "buy", true,
						false, df.parse("2013-07-20")));

				// 以下为试用用户

				// huaren try

				// taobao try
				
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
		List<AfMailList> afmailist = AfMailList.getinstance();
		for (int i = 0; i < afmailist.size(); i++) {
			AfMailList amail = afmailist.get(i);
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
		AfMailList comp = (AfMailList) o;
		int flag = 0;
		flag = this.userType.compareTo(comp.userType);
		if (flag != 0)
			return flag;
		flag = comp.valideTime.compareTo(this.valideTime);
		if (flag != 0)
			return flag;
		return 0;
	}

	@Override
	public String toString() {
		return "AfMailList [mailaddress=" + mailaddress + ", userType="
				+ userType + ", valideTime="
				+ TimeUtli.dateToString(valideTime) + ", warningCode="
				+ warningCode + ", warningMen=" + warningMen
				+ ", warningWomen=" + warningWomen + ", mencheckingClearance="
				+ mencheckingClearance + ", mencheckingSale=" + mencheckingSale
				+ ", mencheckingRegular=" + mencheckingRegular
				+ ", mencheckingSecretSale=" + mencheckingSecretSale
				+ ", mencheckingClearanceDiscount="
				+ mencheckingClearanceDiscount + ", mencheckingSaleDiscount="
				+ mencheckingSaleDiscount + ", mencheckingCategory="
				+ mencheckingCategory + ", mencheckingSize=" + mencheckingSize
				+ ", womencheckingClearance=" + womencheckingClearance
				+ ", womencheckingSale=" + womencheckingSale
				+ ", womencheckingRegular=" + womencheckingRegular
				+ ", womencheckingSecretSale=" + womencheckingSecretSale
				+ ", womencheckingClearanceDiscount="
				+ womencheckingClearanceDiscount
				+ ", womencheckingSaleDiscount=" + womencheckingSaleDiscount
				+ ", womencheckingCategory=" + womencheckingCategory
				+ ", womencheckingSize=" + womencheckingSize + "]";
	}

	public static void main(String[] args) throws Exception {
		List<AfMailList> afmailist = AfMailList.getinstance();
		for (int i = 0; i < afmailist.size(); i++) {
			AfMailList amail = afmailist.get(i);
			System.out.print(amail.mailaddress + ",");
		}

		// List<AfMailList> afmailist = AfMailList.getinstanceFromText();
		// for (int i = 0; i < afmailist.size(); i++) {
		// AfMailList amail = afmailist.get(i);
		// System.out.print(amail.mailaddress + ",");
		// }
	}

}

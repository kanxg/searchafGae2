package kxg.searchaf.url.hollisterco;

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

public class HollistercoMailList implements Comparable<Object> {
	private static List<HollistercoMailList> maillist;

	public static String mail_subject = "Hollisterco 监视系统提醒";
	public static String mailto_men = "watch_hco_men@163.com";
	public static String mailto_women = "watch_hco_women@163.com";

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
	public List<HollistercoProduct> hasWaringProdList;

	public HollistercoMailList() {

	}

	public HollistercoMailList(String mailaddress, String userType,
			boolean warningMen, boolean warningWomen, Date valideTime) {
		this.mailaddress = mailaddress;
		this.userType = userType;
		this.valideTime = valideTime;
		this.warningMen = warningMen;
		this.warningWomen = warningWomen;

	}

	public static void sync() {
		try {
			HollistercoMailListMongoDao dao = new HollistercoMailListMongoDao();
			maillist = dao.findAll();
			Collections.sort(maillist);
		} catch (Exception e) {
		SystemMail.sendSystemMail("error when get mongdb,err:"
					+ e.getMessage());
		}

	}

	public static List<HollistercoMailList> getinstance() {
		if (maillist == null || maillist.size() == 0) {
			// maillist = getinstanceFromText();
			sync();
		}
		return maillist;
	}

	public static List<HollistercoMailList> getinstanceFromText() {
		if (maillist == null) {
			maillist = new ArrayList<HollistercoMailList>();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				maillist.add(new HollistercoMailList("watch_hco_men@163.com",
						"buy", true, true, df.parse("2013-09-15")));

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
		List<HollistercoMailList> hollistercomailist = HollistercoMailList
				.getinstance();
		for (int i = 0; i < hollistercomailist.size(); i++) {
			HollistercoMailList amail = hollistercomailist.get(i);
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
		HollistercoMailList comp = (HollistercoMailList) o;
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
		return "HollistercoMailList [mailaddress=" + mailaddress
				+ ", userType=" + userType + ", valideTime="
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
		List<HollistercoMailList> hollistercomailist = HollistercoMailList
				.getinstance();
		for (int i = 0; i < hollistercomailist.size(); i++) {
			HollistercoMailList amail = hollistercomailist.get(i);
			System.out.println(amail);
		}

		// List<HollistercoMailList> hollistercomailist =
		// HollistercoMailList.getinstanceFromText();
		// for (int i = 0; i < hollistercomailist.size(); i++) {
		// HollistercoMailList amail = hollistercomailist.get(i);
		// System.out.print(amail.mailaddress + ",");
		// }
	}

}

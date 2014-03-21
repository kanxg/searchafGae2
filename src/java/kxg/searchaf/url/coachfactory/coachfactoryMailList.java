package kxg.searchaf.url.coachfactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class coachfactoryMailList {

	public static String mail_subject = "coachfactory 监视系统提醒";

	public static String mailto = "watch_coachfactory@163.com";

	public String mailaddress;
	public Date valideTime;

	public coachfactoryMailList(String mailaddress, boolean warningMen,
			boolean warningWomen, Date valideTime) {
		this.mailaddress = mailaddress;
		this.valideTime = valideTime;
	}

	public static List<coachfactoryMailList> getinstance() {
		List<coachfactoryMailList> maillist = new ArrayList<coachfactoryMailList>();

		// maillist.add(new AfMailList("kanqiduo@163.com", new Date(2013, 1,
		// 9)));
		return maillist;
	}
}

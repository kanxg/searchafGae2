package kxg.searchaf.url.aptamil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AptamilMailList {
	private static List<AptamilMailList> maillist;

	public static String mail_subject = "Aptamil&HIPP 到货提醒";

	public static String mailto = "watch_aptamil@163.com";

	public String mailaddress;
	public Date valideTime;
	public boolean warningAptamil;
	public boolean warningHipp;

	public AptamilMailList(String mailaddress, Date valideTime,
			boolean warningAptamil, boolean warningHipp) {
		this.mailaddress = mailaddress;
		this.valideTime = valideTime;
		this.warningAptamil = warningAptamil;
		this.warningHipp = warningHipp;
	}

	public static List<AptamilMailList> getinstance() {
		if (maillist == null) {
			maillist = new ArrayList<AptamilMailList>();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				// buy 1 month
				maillist.add(new AptamilMailList("zhangxi00@gmail.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("sopherxie@sina.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("fisher_yucj@163.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("174304726@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("doumatai@126.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("596526375@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("slamdunkyuan@hotmail.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("18855568@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("102246054@163.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("allenzhuyi@163.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("mayaxiaoxiao@126.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("xia_yanfei@163.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("shuchen123@163.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("153307423@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("2723190@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("271040717@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("flora.gan@bennalong.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("catherine0419@163.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("25798464@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("amo1981@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("roger1@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("ncdrimx@126.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("187194754@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("15188842@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("564444739@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("2668496870@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("106350050@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("1529979543@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("247324894@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("sevenana@foxmail.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("9531613@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("576997541@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("say_yes@163.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("247324894@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("really_yestday@hotmail.com",
						df.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("11477420@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("wanghaiji@126.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("13933556@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("284292570@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("coco0313@hotmail.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("sxf_8661@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("dot.dot.dot@163.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("749924828@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("69926198@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("yangyu561@126.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("xuening_7_1@sina.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("Jessieseven@126.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("627692117@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("87246515@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("cynthiagargar@msn.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("88311835@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("12078843@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("4152741@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("20561507@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("23904050@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("anilan@126.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("532167328@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("695434630@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("138136166@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("526227845@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("yama@live.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("sxbob@hotmail.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("516567357@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("llylgy01@163.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("xxczbgs@163.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("sxbob81@gmail.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("16372957@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("28152914@163.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("wmty@139.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("29581113@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("angela_hao@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("19831690@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("158654821@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("4158169@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("121576556@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("13782172591@163.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("364620766@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("327685553@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("christine_lee18@hotmail.com",
						df.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("chenyanch@hotmail.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("285811360@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("35618905@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("393153898@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("xiangdangniubi@gmail.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("daitu07@163.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("253750076@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("5536207@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("wangwei830518@hotmail.com",
						df.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("403486518@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("309241@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("9226623@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("sunwallop@gmail.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("76484948@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("274207171@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("yanjin207@sohu.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("13811500936@139.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("floratina0921@163.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("justfinal@126.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("kingbai@gmail.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("108602294@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("383976746@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("hodgen@gmail.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("16512512@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("aneidket@yahoo.com.cn", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("827623225@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("hauyew@gmail.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("514006307@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("fengli82@gmail.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("37658170@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("lilyero@163.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("chenkaavie@gmail.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("weihua_nj@163.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("75598039@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("2239515@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("1691926691@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("elva_nb@hotmail.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("yash2010@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("464243592@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("27233122@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("122007228@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("928077130@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("zhulan@suda.edu.cn", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("bilei_0@126.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("elva_nb@hotmail.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("928077130@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("237685920@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("84310360@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("songanhang@hotmail.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("84310360@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("122007228@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("52929193@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("viewsonic002@163.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("yuexuyuan@vip.qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("84760181@qq.com", df
						.parse("2013-09-01"), true, true));

				maillist.add(new AptamilMailList("heycamel@163.com", df
						.parse("2013-09-01"), true, true));

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
		List<AptamilMailList> afmailist = AptamilMailList.getinstance();
		for (int i = 0; i < afmailist.size(); i++) {
			AptamilMailList amail = afmailist.get(i);
			if (validateEmail.equalsIgnoreCase(amail.mailaddress)) {
				if (amail.valideTime.after(currentDate)) {
					return true;
				}
			}

		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		Date currentDate = new Date();
		List<AptamilMailList> afmailist = AptamilMailList.getinstance();
		List<String> cc_to = new ArrayList<String>();
		int i = 0;
		for (i = 0; i < afmailist.size(); i++) {
			AptamilMailList amail = afmailist.get(i);
			if (amail.valideTime.after(currentDate)) {
				cc_to.add(amail.mailaddress);
			}
		}
		System.out.println("客户总数：" + i);
		String bcc = "";
		for (i = 0; i < cc_to.size(); i++) {
			if (i == 0) {
				bcc = cc_to.get(i);
			} else {
				bcc = bcc + ";" + cc_to.get(i);
			}
		}
		System.out.println(bcc);
	}

}

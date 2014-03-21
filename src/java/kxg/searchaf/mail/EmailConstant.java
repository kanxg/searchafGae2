package kxg.searchaf.mail;

public class EmailConstant {

	// public static String system_host = "smtp.163.com";
	// public static String system_port = "25";
	// public static String mail_from = "kanxg@163.com";
	// public static String system_username = "kanxg";
	// public static String system_password = "Kanqi051115";

	// public String host = "smtp.gmail.com";
	// public String port = "465";
	// public String mail_from = "xingang.af1@gmail.com";

	// public String host = "smtpout.asia.secureserver.net";
	// public String port = "3535";
	// public String mail_from = "system@ebuybot.com";

	// String username = "xingang.af1@gmail.com";
	// String password = "Kanduoduo1234";

	// String username = "system@ebuybot.com";
	// String password = "fsm1021";

	public static String host = "smtp.mailgun.org";
	public static String port = "25";
	public static String mail_from = "watch_af_men@163.com";

	public static String username = "";
	public static String password = "";

	// watch_RL_men
	public static String username0 = "postmaster@searchaf.mailgun.org";
	public static String password0 = "81213325dkv5";

	// watch_gym_girl
	public static String username1 = "postmaster@searchaf111.mailgun.org";
	public static String password1 = "9noie5kn39t9";

	public static int mailcount = 2;

	public static int i = 0;

	public static void setMailCount() {
		switch (i % mailcount) {
		case 0:
			username = username0;
			password = password0;
			break;
		case 1:
			username = username1;
			password = password1;
			break;
		// case 2:
		// username = username2;
		// password = password2;
		// break;
		// case 3:
		// username = username3;
		// password = password3;
		// break;
		// case 4:
		// username = username4;
		// password = password4;
		// break;
		// case 5:
		// username = username5;
		// password = password5;
		// break;
		// case 6:
		// username = username6;
		// password = password6;
		// break;
		// case 7:
		// username = username7;
		// password = password7;
		// break;
		// case 8:
		// username = username8;
		// password = password8;
		// break;
		}
		i = i + 1;
	}

}

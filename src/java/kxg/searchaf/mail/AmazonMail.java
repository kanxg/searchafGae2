package kxg.searchaf.mail;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AmazonMail {
	public String host = "email-smtp.us-east-1.amazonaws.com";
	public String port = "25";

	public String mail_to = "xingang.af1@gmail.com";

	public List<String> cc_to;

	public String mail_from = "watch_af_men@163.com";

	public String mail_subject = "ebuybot系统提醒邮件";

	public StringBuffer tagcontext = new StringBuffer();

	public void sendMail(int failRetryTimes) {
		boolean flag = false;
		String err = "";
		for (int i = 0; i < failRetryTimes; i++) {
			try {
				sendMail();
				flag = true;
			} catch (Exception e) {
				err = e.getClass() + e.toString();
			}
			if (flag)
				break;
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
		}
		if (!flag) {
			// System.out.println("error when sending mailto:" + mail_to);
			SystemMail.sendSystemMail("error when send mail,;err:" + err);
		}
	}

	public void sendMail() throws Exception {

		Authenticator auth = new Email_Autherticator("AKIAI5WYBSM3W2M3CVPA",
				"AiNoswEHAMJNR+56orSNrv/XqXcQnBiWn9SS+hVnFyUZ");// 进行邮件服务用户认证

		// Properties props = new Properties();// 获取系统环境
		Properties props = System.getProperties(); // 方式二
		// props.setProperty("proxySet", "true");
		// props.setProperty("socksProxyHost", "127.0.0.1"); //
		// props.setProperty("socksProxyPort", "1080");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		// props.put("mail.debug", "true");

		// System.out.println(props);
		Session session = Session.getDefaultInstance(props, auth);
		// 设置session,和邮件服务器进行通讯
		MimeMessage message = new MimeMessage(session);
		// message.setContent("Hello", "text/plain");// 设置邮件格式
		// message.setContent("Hello", "text/html;charset=UTF-8");
		message.setContent(tagcontext.toString(), "text/html;charset=utf-8");
		message.setSubject(mail_subject);// 设置邮件主题
		// message.setText(mail_body + tagcontext.toString());// 设置邮件内容
		// message.setHeader(mail_head_name, mail_head_value);// 设置邮件标题
		message.setSentDate(new Date());// 设置邮件发送时期
		Address address = new InternetAddress(mail_from, "www.ebuybot.com");
		message.setFrom(address);// 设置邮件发送者的地址
		Address toaddress = new InternetAddress(mail_to);// 设置邮件接收者的地址
		message.addRecipient(Message.RecipientType.TO, toaddress);
		if (cc_to != null) {
			for (int i = 0; i < cc_to.size(); i++) {
				Address ccaddress = new InternetAddress(cc_to.get(i));// 设置邮件接收者的地址
				message.addRecipient(Message.RecipientType.BCC, ccaddress);
			}
		}
		// System.out.println(message);
		Transport.send(message);
		System.out.println("Send Mail Ok!");

		// return flag;
	}

	public static void main(String[] args) {
		Mail m = new Mail();
		for (int i = 0; i < 1; i++) {
			try {
				m.sendMail();
			} catch (Exception e) {
				// System.out.println(e.getClass());
				e.printStackTrace();
			}
		}
	}
}

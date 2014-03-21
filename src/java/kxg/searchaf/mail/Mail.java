package kxg.searchaf.mail;

import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Mail {
	public String mail_to = "xingang.af1@gmail.com";

	public List<String> cc_to;

	public String mail_from = "watch_af_men@163.com";

	public String mail_subject = "ebuybot系统提醒邮件";

	public StringBuffer tagcontext = new StringBuffer();

	// Supply your SMTP credentials below. Note that your SMTP credentials are
	// different from your AWS credentials.
	// static final String SMTP_USERNAME = "AKIAI5WYBSM3W2M3CVPA";
	// static final String SMTP_PASSWORD =
	// "AiNoswEHAMJNR+56orSNrv/XqXcQnBiWn9SS+hVnFyUZ";

	static final String SMTP_USERNAME = "AKIAIBBCV3QYV6XGZ6CQ";
	static final String SMTP_PASSWORD = "AiLAhv8UqxHSlI8X0dySUmNEX7a5FI0Laxo53TTSe77g";

	// Amazon SES SMTP host name.
	static final String HOST = "email-smtp.us-east-1.amazonaws.com";

	// Port we will connect to on the Amazon SES SMTP endpoint. We are choosing
	// port 25 because we will use
	// STARTTLS to encrypt the connection.
	static final int PORT = 25;

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
			if (failRetryTimes != 1 && !flag) {
				try {
					Thread.sleep(1000 / 5);
				} catch (InterruptedException e) {
				}
			}
			if (flag)
				break;
		}
		if (!flag) {
			// System.out.println("error when sending mailto:" + mail_to);
			//SystemMail.sendSystemMail("error when send mail,;err:" + err);
		}
	}

	public void sendMail() throws Exception {

		// Create a Properties object to contain connection configuration
		// information.
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", PORT);

		// Set properties indicating that we want to use STARTTLS to encrypt the
		// connection.
		// The SMTP session will begin on an unencrypted connection, and then
		// the client
		// will issue a STARTTLS command to upgrade to an encrypted connection.
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.required", "true");

		// Create a Session object to represent a mail session with the
		// specified properties.
		Session session = null;
		try {
			session = Session.getDefaultInstance(props);
		} catch (Exception e) {
			session = Session.getInstance(props);
		}

		// Create a message with the specified information.
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(mail_from, "www.ebuybot.com"));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mail_to));
		if (cc_to != null) {
			for (int i = 0; i < cc_to.size(); i++) {
				Address ccaddress = new InternetAddress(cc_to.get(i));// 设置邮件接收者的地址
				msg.addRecipient(Message.RecipientType.BCC, ccaddress);
			}
		}

		msg.setSubject(mail_subject);
		msg.setContent(tagcontext.toString(), "text/html;charset=utf-8");

		// Create a transport.
		Transport transport = session.getTransport();

		// Send the message.
		try {
			// System.out
			// .println("Attempting to send an email through the Amazon SES SMTP interface...");

			// Connect to Amazon SES using the SMTP username and password you
			// specified above.
			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

			// Send the email.
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Email sent!");
		} finally {
			// Close and terminate the connection.
			transport.close();
		}
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
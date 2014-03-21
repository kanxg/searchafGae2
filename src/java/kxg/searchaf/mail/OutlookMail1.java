package kxg.searchaf.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kxg.searchaf.url.Constant;
import kxg.searchaf.url.aptamil.AptamilMailList;

//import com.jacob.activeX.ActiveXComponent;
//import com.jacob.com.Dispatch;

public class OutlookMail1 {

//	public String mail_from = "kanxg@163.com";
//	public String mail_to = "kanqiqiqi@163.com";
//	public String mail_subject = "";
//
//	public List<String> cc_to;
//	public StringBuffer tagcontext = new StringBuffer();
//
//	public static void main(String[] args) throws Exception {
//		OutlookMail1 m = new OutlookMail1();
//		try {
//			Date currentDate = new Date();
//			List<AptamilMailList> afmailist = AptamilMailList.getinstance();
//			List<String> cc_to = new ArrayList<String>();
//			for (int i = 0; i < afmailist.size(); i++) {
//				AptamilMailList amail = afmailist.get(i);
//				if (amail.valideTime.after(currentDate)) {
//					if (amail.warningAptamil) {
//						cc_to.add(amail.mailaddress);
//					}
//				}
//			}
//			// m.cc_to = cc_to;
//			m.sendMail();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void sendMail() throws Exception {
//		if (cc_to != null && cc_to.size() > Constant.maxMailCCcount) {
//			List<String> tempcc = new ArrayList<String>();
//			tempcc.addAll(cc_to);
//			cc_to.clear();
//			for (int i = 0; i < tempcc.size(); i++) {
//				if (i != 0 && i % (Constant.maxMailCCcount - 1) == 0) {
//					// System.out.println(i);
//					// send
//					cc_to.add(tempcc.get(i));
//					sendMailAll();
// 					cc_to.clear();
//				} else {
//					cc_to.add(tempcc.get(i));
//				}
//			}
//			if (cc_to.size() != 0) {
//				sendMailAll();
//			}
//		} else {
//			sendMailAll();
//		}
//	}
//
//	public void sendMailAll() throws Exception {
//
//		/*
//		 * String body = "<div style='color:red;'>This is a Test !</div>";
//		 * Runtime.getRuntime().exec(
//		 * "D:\\Program Files\\Microsoft Office\\Office14\\OUTLOOK.EXE  mailto:KO@techson.com.hk?subject=Chinese New Year backup arrangement"
//		 * + "&cc=Ko@techson.com.hk&body="+body);
//		 */
//
//		// System.out.println(System.getProperty("java.library.path"));
//
//		ActiveXComponent axOutlook = new ActiveXComponent("Outlook.Application");
//		// Dispatch ds = axOutlook.getObject();
//		/*
//		 * Dispatch namespace = Dispatch.call(axOutlook, "GetNamespace",
//		 * "MAPI").toDispatch() ; String path = new
//		 * File("F:/test.htm").getAbsolutePath(); Dispatch mailItem =
//		 * Dispatch.call(namespace, "OpenSharedItem", path).toDispatch();
//		 */
//
//		Dispatch mailItem = Dispatch.call(axOutlook, "CreateItem", 0)
//				.getDispatch();
//
//		// Dispatch mailItem = Dispatch.call(axOutlook, "Open",
//		// "F:/test.htm").toDispatch();
//		// Dispatch body = Dispatch.call(mailItem, "Body").getDispatch();;
//		// Dispatch.call(body, "Copy");
//
//		// Dispatch inspector = Dispatch.get(mailItem, "GetInspector")
//		// .getDispatch();
//
//		// 收件人
//		Dispatch recipients = Dispatch.call(mailItem, "Recipients")
//				.getDispatch();
//		Dispatch.call(recipients, "Add", mail_to);
//		// Dispatch.call(recipients, "from", mail_from);
//		// 邮件主题
//		Dispatch.put(mailItem, "Subject", mail_subject);
//		if (cc_to != null) {
//			String bcc = "";
//			for (int i = 0; i < cc_to.size(); i++) {
//				if (i == 0) {
//					bcc = cc_to.get(i);
//				} else {
//					bcc = bcc + ";" + cc_to.get(i);
//				}
//			}
//			// System.out.println(bcc);
//			Dispatch.put(mailItem, "BCC", bcc);
//		}
//
//		String body = tagcontext.toString();
//		Dispatch.put(mailItem, "HTMLBody", body);
//
//		// File file = new File("C:/test.htm");
//		// FileOutputStream f = new FileOutputStream("F:/test.htm");
//		// f.write(body.getBytes());
//		// f.close();
//
//		// BufferedReader bf = new BufferedReader(new FileReader(new File(
//		// "C:/Downloads/test.txt")));
//		// String html = "";
//		// String str = "";
//		// while ((str = bf.readLine()) != null) {
//		// html += str;
//		// }
//		// Dispatch.put(mailItem, "HTMLBody", html);
//
//		// Dispatch bodyDis = Dispatch.get(mailItem, "Body").toDispatch();
//		// Dispatch.call(bodyDis, "Add" , "F:/test.htm");
//		// Dispatch.put(mailItem, "Body", new Variant("F:/test.htm"));
//
//		// Dispatch.put(mailItem, "Body", body);
//		// Dispatch.call(mailItem, "InsertAfter",body );//插入一个段落
//
//		// Dispatch selection = Dispatch.get(axOutlook,
//		// "Selection").toDispatch();
//
//		// Dispatch inHtmleditor = Dispatch.call(inspector,
//		// "HTMLEditor").getDispatch();
//		// Dispatch.call(inHtmleditor, "Paste").getDispatch();
//		// Dispatch.invoke(inHtmleditor, "Paste", Dispatch.Method, new
//		// Object[]{false}, new Variant(false));
//
//		// 附件
//		// Dispatch attachments = Dispatch.call(mailItem,
//		// "Attachments").getDispatch();
//		// Dispatch.call(attachments, "Add" , "D:\\20110127.txt");
//		// Dispatch.call(attachments, "Add" , "D:\\20110128.txt");
//		// 显示新邮件窗口
//
//		// Dispatch.call(mailItem, "Display");
//		Dispatch.call(mailItem, "Send");
//		System.out.println("Send Mail Ok!");
//	}

}

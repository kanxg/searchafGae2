package kxg.searchaf.weixin;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

public class WeixinHelper {

	private static Weixin wx;

	public static void connect2Weixin() {
		String LOGIN_USER = "xingang.af1@gmail.com"; // 此为上一任作者的用户名和密码，截止到我最后用发现还能用
		String LOGIN_PWD = "fsm1021";
		wx = new Weixin(LOGIN_USER, LOGIN_PWD);
		wx.login();
		wx.getFans();
		// wx.getCookiestr();
		// ImgFileForm form = new ImgFileForm();
		// form.setUploadfile(new File("D:\\Data\\image\\4.jpg"));
		// wx.updateImg(form);
		// System.out.println("粉丝数：" + wx.);
	}

	public static void sendWeixin(String weixinName, String msgText) {
		String userid = wx.getUserbyName(weixinName);
		if (userid != null)
			wx.sendMsg(userid, msgText);
	}

	public static void disconnect2Weixin() {
		try {
			wx.logout();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

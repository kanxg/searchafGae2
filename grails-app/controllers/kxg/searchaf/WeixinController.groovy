package kxg.searchaf

import grails.converters.XML;

class WeixinController {

	def WexinSerivice

	def TOKEN = "ebuybot";
	def index() {
		if (request.method == 'GET') {
			def signature = params["signature"]
			def echostr = params["echostr"]
			def timestamp  = params["timestamp"]
			def nonce  = params["nonce"]
			def str=[]
			str.add TOKEN
			str.add timestamp
			str.add nonce

			//		Arrays.sort(str); // 字典序排序
			//		String bigStr = str[0] + str[1] + str[2];
			// SHA1加密
			//		String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();

			// 确认请求来至微信
			//		if (digest.equals(signature)) {
			//			response.getWriter().print(echostr);
			//		}

			render echostr
		}else{
			//		def ToUserName  = request.XML?.xml?.ToUserName
			//		def FromUserName  =  request.XML?.xml?.FromUserName
			//		def CreateTime  =  request.XML?.xml?.CreateTime
			//		def MsgType  =  request.XML?.xml?.MsgType
			//		def Content  =  request.XML?.xml?.Content

			String weiXinXML = org.apache.commons.io.IOUtils.toString(request.getReader());
			//			System.out.println( weiXinXML );

			String ToUserName = weiXinXML.substring(weiXinXML.indexOf("<ToUserName>") + "<ToUserName>".length(), weiXinXML.indexOf("</ToUserName>"));
			String FromUserName = weiXinXML.substring(weiXinXML.indexOf("<FromUserName>") + "<FromUserName>".length(), weiXinXML.indexOf("</FromUserName>"));
			String CreateTime = weiXinXML.substring(weiXinXML.indexOf("<CreateTime>") + "<CreateTime>".length(), weiXinXML.indexOf("</CreateTime>"));
			String MsgType = weiXinXML.substring(weiXinXML.indexOf("<MsgType>") + "<MsgType>".length(), weiXinXML.indexOf("</MsgType>"));
			String Content = weiXinXML.substring(weiXinXML.indexOf("<Content>") + "<Content>".length(), weiXinXML.indexOf("</Content>"));

			Content=WexinSerivice.getCommandRespond(MsgType,Content)

			def xmltext=new StringBuffer()
			xmltext.append "<xml>"
			xmltext.append "<ToUserName>${FromUserName}</ToUserName>"
			xmltext.append "<FromUserName>${ToUserName}</FromUserName>"
			xmltext.append "<CreateTime>${CreateTime}</CreateTime>"
			xmltext.append "<MsgType>${MsgType}</MsgType>"
			xmltext.append "<Content>${Content}</Content>"
			xmltext.append "</xml>"

			render(text: xmltext, contentType: "text/html", encoding: "UTF-8")
			//render str as XML
		}
	}
}

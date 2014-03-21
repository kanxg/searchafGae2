package kxg.searchaf;

import kxg.searchaf.util.HttpRequest;
import java.util.*;
/**
 * Created with IntelliJ IDEA. User: rock Date: 3/19/14 Time: 6:14 PM To change
 * this template use File | Settings | File Templates.
 */
public class OracleWifiPass {

	public OracleWifiPass(){
		//System.out.println("inti a OracleWifi class" + new Date() );
	}
	public void run() {
		System.out.println("OracleWifiPass run:" + new Date() );
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("OracleWifiPass sleep:" + new Date() );

	}

	public static void main(String[] args) {
		HttpRequest request = HttpRequest.get("http://localh1ost:8080/searchaf");
		int code = request.code();
		String body = request.body();
		System.out.println("code:" + code);
		System.out.println("body:" + body);
	}

}

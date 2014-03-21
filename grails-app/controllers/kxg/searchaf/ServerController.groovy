package kxg.searchaf

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


class ServerController {

	def index() {
	}
	
	def alive(){
		keppalive "http://searchaf.elasticbeanstalk.com/timetask/createtask"
		
		keppalive "http://searchaf.herokuapp.com"
		keepalive "http://searchaf.kanxg.cloudbees.net"
	}
	 
	def keppalive(String url) {
		try {
//			URL url = new URL("http://searchaf.herokuapp.com/searchAfTask");
 			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();

			urlConnection.connect();
			InputStream is = urlConnection.getInputStream();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));

			String s;
			StringBuilder result = new StringBuilder();
			while (((s = reader.readLine()) != null)) {
				result.append(s);
			}

			// System.out.println("result= " + result.toString());

			is.close();

		} catch (Exception e) {
			System.out
					.println("error when visit http://searchaf.herokuapp.com");
			e.printStackTrace();
		}
	}
}

package kxg.searchaf.db.mongo;

import java.net.UnknownHostException;
import java.util.Set;

import kxg.searchaf.url.af.AfConstant;
import kxg.searchaf.util.ProxyUtli;

import com.mongodb.MongoURI;
import com.mongodb.DB;

public class MongoBackupDao {

	private static String mongolabURL = "mongodb://searchaf:searchaf@ds051437.mongolab.com:51437/searchaf";

	private static String uriString = mongolabURL;

	private static DB db;

	public static DB getDao() {
		if (db == null) {
			try {
				connect();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		return db;
	}

	public static void connect() throws UnknownHostException {

		// String uriString =
		// "mongodb://searchaf:searchaf@ds051437.mongolab.com:51437/searchaf";
		// String uriString =
		// "mongodb://searchaf:searchaf@dharma.mongohq.com:10039/searchaf";
		MongoURI uri = new MongoURI(uriString);
		uri.getOptions().setMaxWaitTime(1000 * 5);
		uri.getOptions().setConnectTimeout(1000 * 10);
		uri.getOptions().setSocketTimeout(1000 * 10);
		DB db = uri.connectDB();

		db.authenticate(uri.getUsername(), uri.getPassword());

		// Set<String> colls = db.getCollectionNames();
		//
		// for (String s : colls) {
		// System.out.println(s);
		// }

		MongoBackupDao.db = db;
	}

	public static void main(String[] args) throws Exception {

		ProxyUtli.setProxy(true);

		MongoDao.getDao();
	}

}

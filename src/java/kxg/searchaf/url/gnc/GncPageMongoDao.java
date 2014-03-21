package kxg.searchaf.url.gnc;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kxg.searchaf.db.mongo.MongoBackupDao;
import kxg.searchaf.db.mongo.MongoDao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.WriteResult;

public class GncPageMongoDao {

	private DB db;
	private DBCollection coll;

	public GncPageMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("gnclist");
	}

	public WriteResult save(GncPage gncPage) {
		return coll.insert(convert2DB(gncPage));
	}

	public void update(GncPage gncPage) {
		BasicDBObject newDocument = convert2DB(gncPage);
		coll.update(
				new BasicDBObject().append("mailaddress",
						gncPage.mailaddress).append("url", gncPage.url),
				newDocument);
	}

	public void delete(String mailaddress, String url) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		document.put("url", url);
		coll.remove(document);
	}

	public List<GncPage> findAll() {
		List<GncPage> maillist = new ArrayList<GncPage>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				GncPage gncPage = parseGncMailList(object);
				maillist.add(gncPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public List<GncPage> find(String mailaddress) {

		List<GncPage> maillist = new ArrayList<GncPage>();
		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				GncPage gncPage = parseGncMailList(object);
				maillist.add(gncPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public GncPage find(String mailaddress, String url) {

		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress).append("url", url);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseGncMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private GncPage parseGncMailList(BasicDBObject object) {
		GncPage gncPage = new GncPage();
		gncPage.mailaddress = (String) object.get("mailaddress");
		gncPage.url = (String) object.get("url");
		gncPage.prodname = (String) object.get("prodname");
		if (object.get("expectPirce") != null)
			gncPage.expectPirce = Float.valueOf((String) object
					.get("expectPirce"));
		// gncPage.expectPirce = (Float) object.get("expectPirce");
		// gncPage.checkType = (String) object.get("checkType");
		// gncPage.checksalerisGnc = (Boolean) object
		// .get("checksalerisGnc");
		return gncPage;
	}

	public BasicDBObject convert2DB(GncPage gncPage) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", gncPage.mailaddress);
		doc.put("url", gncPage.url);
		doc.put("prodname", gncPage.prodname);
		doc.put("expectPirce", String.valueOf(gncPage.expectPirce));

		// doc.put("expectPirce", gncPage.expectPirce);
		// doc.put("checkType", gncPage.checkType);
		// doc.put("checksalerisGnc", gncPage.checksalerisGnc);
		return doc;

	}

	public static void main(String[] args) throws Exception {
		// sync2DB();

		System.out.println("done.");
	}

}

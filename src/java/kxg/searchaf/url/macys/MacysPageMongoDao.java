package kxg.searchaf.url.macys;

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

public class MacysPageMongoDao {

	private DB db;
	private DBCollection coll;

	public MacysPageMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("macyslist");
	}

	public WriteResult save(MacysPage macysPage) {
		return coll.insert(convert2DB(macysPage));
	}

	public void update(MacysPage macysPage) {
		BasicDBObject newDocument = convert2DB(macysPage);
		coll.update(
				new BasicDBObject().append("mailaddress",
						macysPage.mailaddress).append("url", macysPage.url),
				newDocument);
	}

	public void delete(String mailaddress, String url) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		document.put("url", url);
		coll.remove(document);
	}

	public List<MacysPage> findAll() {
		List<MacysPage> maillist = new ArrayList<MacysPage>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				MacysPage macysPage = parseMacysMailList(object);
				maillist.add(macysPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public List<MacysPage> find(String mailaddress) {

		List<MacysPage> maillist = new ArrayList<MacysPage>();
		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				MacysPage macysPage = parseMacysMailList(object);
				maillist.add(macysPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public MacysPage find(String mailaddress, String url) {

		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress).append("url", url);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseMacysMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private MacysPage parseMacysMailList(BasicDBObject object) {
		MacysPage macysPage = new MacysPage();
		macysPage.mailaddress = (String) object.get("mailaddress");
		macysPage.url = (String) object.get("url");
		macysPage.prodname = (String) object.get("prodname");
		if (object.get("expectPirce") != null)
			macysPage.expectPirce = Float.valueOf((String) object
					.get("expectPirce"));
		// macysPage.expectPirce = (Float) object.get("expectPirce");
		// macysPage.checkType = (String) object.get("checkType");
		// macysPage.checksalerisMacys = (Boolean) object
		// .get("checksalerisMacys");
		return macysPage;
	}

	public BasicDBObject convert2DB(MacysPage macysPage) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", macysPage.mailaddress);
		doc.put("url", macysPage.url);
		doc.put("prodname", macysPage.prodname);
		doc.put("expectPirce", String.valueOf(macysPage.expectPirce));

		// doc.put("expectPirce", macysPage.expectPirce);
		// doc.put("checkType", macysPage.checkType);
		// doc.put("checksalerisMacys", macysPage.checksalerisMacys);
		return doc;

	}

	public static void main(String[] args) throws Exception {
		// sync2DB();

		System.out.println("done.");
	}

}

package kxg.searchaf.url.saks;

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

public class SaksPageMongoDao {

	private DB db;
	private DBCollection coll;

	public SaksPageMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("sakslist");
	}

	public WriteResult save(SaksPage saksPage) {
		return coll.insert(convert2DB(saksPage));
	}

	public void update(SaksPage saksPage) {
		BasicDBObject newDocument = convert2DB(saksPage);
		coll.update(
				new BasicDBObject().append("mailaddress",
						saksPage.mailaddress).append("url", saksPage.url),
				newDocument);
	}

	public void delete(String mailaddress, String url) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		document.put("url", url);
		coll.remove(document);
	}

	public List<SaksPage> findAll() {
		List<SaksPage> maillist = new ArrayList<SaksPage>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				SaksPage saksPage = parseSaksMailList(object);
				maillist.add(saksPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public List<SaksPage> find(String mailaddress) {

		List<SaksPage> maillist = new ArrayList<SaksPage>();
		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				SaksPage saksPage = parseSaksMailList(object);
				maillist.add(saksPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public SaksPage find(String mailaddress, String url) {

		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress).append("url", url);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseSaksMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private SaksPage parseSaksMailList(BasicDBObject object) {
		SaksPage saksPage = new SaksPage();
		saksPage.mailaddress = (String) object.get("mailaddress");
		saksPage.url = (String) object.get("url");
		saksPage.prodname = (String) object.get("prodname");
		if (object.get("expectPirce") != null)
			saksPage.expectPirce = Float.valueOf((String) object
					.get("expectPirce"));
		// saksPage.expectPirce = (Float) object.get("expectPirce");
		// saksPage.checkType = (String) object.get("checkType");
		// saksPage.checksalerisSaks = (Boolean) object
		// .get("checksalerisSaks");
		return saksPage;
	}

	public BasicDBObject convert2DB(SaksPage saksPage) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", saksPage.mailaddress);
		doc.put("url", saksPage.url);
		doc.put("prodname", saksPage.prodname);
		doc.put("expectPirce", String.valueOf(saksPage.expectPirce));

		// doc.put("expectPirce", saksPage.expectPirce);
		// doc.put("checkType", saksPage.checkType);
		// doc.put("checksalerisSaks", saksPage.checksalerisSaks);
		return doc;

	}

	public static void main(String[] args) throws Exception {
		// sync2DB();

		System.out.println("done.");
	}

}

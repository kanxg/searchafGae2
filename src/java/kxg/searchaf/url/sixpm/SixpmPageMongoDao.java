package kxg.searchaf.url.sixpm;

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

public class SixpmPageMongoDao {

	private DB db;
	private DBCollection coll;

	public SixpmPageMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("sixpmlist");
	}

	public WriteResult save(SixpmPage sixpmPage) {
		return coll.insert(convert2DB(sixpmPage));
	}

	public void update(SixpmPage sixpmPage) {
		BasicDBObject newDocument = convert2DB(sixpmPage);
		coll.update(
				new BasicDBObject().append("mailaddress",
						sixpmPage.mailaddress).append("url", sixpmPage.url),
				newDocument);
	}

	public void delete(String mailaddress, String url) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		document.put("url", url);
		coll.remove(document);
	}

	public List<SixpmPage> findAll() {
		List<SixpmPage> maillist = new ArrayList<SixpmPage>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				SixpmPage sixpmPage = parseSixpmMailList(object);
				maillist.add(sixpmPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public List<SixpmPage> find(String mailaddress) {

		List<SixpmPage> maillist = new ArrayList<SixpmPage>();
		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				SixpmPage sixpmPage = parseSixpmMailList(object);
				maillist.add(sixpmPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public SixpmPage find(String mailaddress, String url) {

		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress).append("url", url);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseSixpmMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private SixpmPage parseSixpmMailList(BasicDBObject object) {
		SixpmPage sixpmPage = new SixpmPage();
		sixpmPage.mailaddress = (String) object.get("mailaddress");
		sixpmPage.url = (String) object.get("url");
		sixpmPage.prodname = (String) object.get("prodname");
		if (object.get("expectPirce") != null)
			sixpmPage.expectPirce = Float.valueOf((String) object
					.get("expectPirce"));
		// sixpmPage.expectPirce = (Float) object.get("expectPirce");
		// sixpmPage.checkType = (String) object.get("checkType");
		// sixpmPage.checksalerisSixpm = (Boolean) object
		// .get("checksalerisSixpm");
		return sixpmPage;
	}

	public BasicDBObject convert2DB(SixpmPage sixpmPage) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", sixpmPage.mailaddress);
		doc.put("url", sixpmPage.url);
		doc.put("prodname", sixpmPage.prodname);
		doc.put("expectPirce", String.valueOf(sixpmPage.expectPirce));

		// doc.put("expectPirce", sixpmPage.expectPirce);
		// doc.put("checkType", sixpmPage.checkType);
		// doc.put("checksalerisSixpm", sixpmPage.checksalerisSixpm);
		return doc;

	}

	public static void main(String[] args) throws Exception {
		// sync2DB();

		System.out.println("done.");
	}

}

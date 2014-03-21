package kxg.searchaf.url.beauty;

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

public class BeautyPageMongoDao {

	private DB db;
	private DBCollection coll;

	public BeautyPageMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("beautylist");
	}

	public WriteResult save(BeautyPage beautyPage) {
		return coll.insert(convert2DB(beautyPage));
	}

	public void update(BeautyPage beautyPage) {
		BasicDBObject newDocument = convert2DB(beautyPage);
		coll.update(
				new BasicDBObject().append("mailaddress",
						beautyPage.mailaddress).append("url", beautyPage.url),
				newDocument);
	}

	public void delete(String mailaddress, String url) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		document.put("url", url);
		coll.remove(document);
	}

	public List<BeautyPage> findAll() {
		List<BeautyPage> maillist = new ArrayList<BeautyPage>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				BeautyPage beautyPage = parseBeautyMailList(object);
				maillist.add(beautyPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public List<BeautyPage> find(String mailaddress) {

		List<BeautyPage> maillist = new ArrayList<BeautyPage>();
		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				BeautyPage beautyPage = parseBeautyMailList(object);
				maillist.add(beautyPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public BeautyPage find(String mailaddress, String url) {

		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress).append("url", url);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseBeautyMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private BeautyPage parseBeautyMailList(BasicDBObject object) {
		BeautyPage beautyPage = new BeautyPage();
		beautyPage.mailaddress = (String) object.get("mailaddress");
		beautyPage.url = (String) object.get("url");
		beautyPage.prodname = (String) object.get("prodname");
		if (object.get("expectPirce") != null)
			beautyPage.expectPirce = Float.valueOf((String) object
					.get("expectPirce"));
		// beautyPage.expectPirce = (Float) object.get("expectPirce");
		// beautyPage.checkType = (String) object.get("checkType");
		// beautyPage.checksalerisBeauty = (Boolean) object
		// .get("checksalerisBeauty");
		return beautyPage;
	}

	public BasicDBObject convert2DB(BeautyPage beautyPage) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", beautyPage.mailaddress);
		doc.put("url", beautyPage.url);
		doc.put("prodname", beautyPage.prodname);
		doc.put("expectPirce", String.valueOf(beautyPage.expectPirce));

		// doc.put("expectPirce", beautyPage.expectPirce);
		// doc.put("checkType", beautyPage.checkType);
		// doc.put("checksalerisBeauty", beautyPage.checksalerisBeauty);
		return doc;

	}

	public static void main(String[] args) throws Exception {
		// sync2DB();

		System.out.println("done.");
	}

}

package kxg.searchaf.url.sierratradingpost;

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

public class SierratradingpostPageMongoDao {

	private DB db;
	private DBCollection coll;

	public SierratradingpostPageMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("sierratradingpostlist");
	}

	public WriteResult save(SierratradingpostPage sierratradingpostPage) {
		return coll.insert(convert2DB(sierratradingpostPage));
	}

	public void update(SierratradingpostPage sierratradingpostPage) {
		BasicDBObject newDocument = convert2DB(sierratradingpostPage);
		coll.update(
				new BasicDBObject().append("mailaddress",
						sierratradingpostPage.mailaddress).append("url", sierratradingpostPage.url),
				newDocument);
	}

	public void delete(String mailaddress, String url) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		document.put("url", url);
		coll.remove(document);
	}

	public List<SierratradingpostPage> findAll() {
		List<SierratradingpostPage> maillist = new ArrayList<SierratradingpostPage>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				SierratradingpostPage sierratradingpostPage = parseSierratradingpostMailList(object);
				maillist.add(sierratradingpostPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public List<SierratradingpostPage> find(String mailaddress) {

		List<SierratradingpostPage> maillist = new ArrayList<SierratradingpostPage>();
		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				SierratradingpostPage sierratradingpostPage = parseSierratradingpostMailList(object);
				maillist.add(sierratradingpostPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public SierratradingpostPage find(String mailaddress, String url) {

		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress).append("url", url);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseSierratradingpostMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private SierratradingpostPage parseSierratradingpostMailList(BasicDBObject object) {
		SierratradingpostPage sierratradingpostPage = new SierratradingpostPage();
		sierratradingpostPage.mailaddress = (String) object.get("mailaddress");
		sierratradingpostPage.url = (String) object.get("url");
		sierratradingpostPage.prodname = (String) object.get("prodname");
		if (object.get("expectPirce") != null)
			sierratradingpostPage.expectPirce = Float.valueOf((String) object
					.get("expectPirce"));
		// sierratradingpostPage.expectPirce = (Float) object.get("expectPirce");
		// sierratradingpostPage.checkType = (String) object.get("checkType");
		// sierratradingpostPage.checksalerisSierratradingpost = (Boolean) object
		// .get("checksalerisSierratradingpost");
		return sierratradingpostPage;
	}

	public BasicDBObject convert2DB(SierratradingpostPage sierratradingpostPage) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", sierratradingpostPage.mailaddress);
		doc.put("url", sierratradingpostPage.url);
		doc.put("prodname", sierratradingpostPage.prodname);
		doc.put("expectPirce", String.valueOf(sierratradingpostPage.expectPirce));

		// doc.put("expectPirce", sierratradingpostPage.expectPirce);
		// doc.put("checkType", sierratradingpostPage.checkType);
		// doc.put("checksalerisSierratradingpost", sierratradingpostPage.checksalerisSierratradingpost);
		return doc;

	}

	public static void main(String[] args) throws Exception {
		// sync2DB();

		System.out.println("done.");
	}

}

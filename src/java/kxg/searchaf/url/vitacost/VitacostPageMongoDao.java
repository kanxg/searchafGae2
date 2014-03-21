package kxg.searchaf.url.vitacost;

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

public class VitacostPageMongoDao {

	private DB db;
	private DBCollection coll;

	public VitacostPageMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("vitacostlist");
	}

	public WriteResult save(VitacostPage vitacostPage) {
		return coll.insert(convert2DB(vitacostPage));
	}

	public void update(VitacostPage vitacostPage) {
		BasicDBObject newDocument = convert2DB(vitacostPage);
		coll.update(
				new BasicDBObject().append("mailaddress",
						vitacostPage.mailaddress).append("url", vitacostPage.url),
				newDocument);
	}

	public void delete(String mailaddress, String url) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		document.put("url", url);
		coll.remove(document);
	}

	public List<VitacostPage> findAll() {
		List<VitacostPage> maillist = new ArrayList<VitacostPage>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				VitacostPage vitacostPage = parseVitacostMailList(object);
				maillist.add(vitacostPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public List<VitacostPage> find(String mailaddress) {

		List<VitacostPage> maillist = new ArrayList<VitacostPage>();
		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				VitacostPage vitacostPage = parseVitacostMailList(object);
				maillist.add(vitacostPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public VitacostPage find(String mailaddress, String url) {

		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress).append("url", url);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseVitacostMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private VitacostPage parseVitacostMailList(BasicDBObject object) {
		VitacostPage vitacostPage = new VitacostPage();
		vitacostPage.mailaddress = (String) object.get("mailaddress");
		vitacostPage.url = (String) object.get("url");
		vitacostPage.prodname = (String) object.get("prodname");
		if (object.get("expectPirce") != null)
			vitacostPage.expectPirce = Float.valueOf((String) object
					.get("expectPirce"));
		// vitacostPage.expectPirce = (Float) object.get("expectPirce");
		// vitacostPage.checkType = (String) object.get("checkType");
		// vitacostPage.checksalerisVitacost = (Boolean) object
		// .get("checksalerisVitacost");
		return vitacostPage;
	}

	public BasicDBObject convert2DB(VitacostPage vitacostPage) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", vitacostPage.mailaddress);
		doc.put("url", vitacostPage.url);
		doc.put("prodname", vitacostPage.prodname);
		doc.put("expectPirce", String.valueOf(vitacostPage.expectPirce));

		// doc.put("expectPirce", vitacostPage.expectPirce);
		// doc.put("checkType", vitacostPage.checkType);
		// doc.put("checksalerisVitacost", vitacostPage.checksalerisVitacost);
		return doc;

	}

	public static void main(String[] args) throws Exception {
		// sync2DB();

		System.out.println("done.");
	}

}

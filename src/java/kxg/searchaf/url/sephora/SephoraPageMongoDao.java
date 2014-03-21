package kxg.searchaf.url.sephora;

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

public class SephoraPageMongoDao {

	private DB db;
	private DBCollection coll;

	public SephoraPageMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("sephoralist");
	}

	public WriteResult save(SephoraPage sephoraPage) {
		return coll.insert(convert2DB(sephoraPage));
	}

	public void update(SephoraPage sephoraPage) {
		BasicDBObject newDocument = convert2DB(sephoraPage);
		coll.update(
				new BasicDBObject().append("mailaddress",
						sephoraPage.mailaddress).append("url", sephoraPage.url),
				newDocument);
	}

	public void delete(String mailaddress, String url) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		document.put("url", url);
		coll.remove(document);
	}

	public List<SephoraPage> findAll() {
		List<SephoraPage> maillist = new ArrayList<SephoraPage>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				SephoraPage sephoraPage = parseSephoraMailList(object);
				maillist.add(sephoraPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public List<SephoraPage> find(String mailaddress) {

		List<SephoraPage> maillist = new ArrayList<SephoraPage>();
		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				SephoraPage sephoraPage = parseSephoraMailList(object);
				maillist.add(sephoraPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public SephoraPage find(String mailaddress, String url) {

		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress).append("url", url);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseSephoraMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private SephoraPage parseSephoraMailList(BasicDBObject object) {
		SephoraPage sephoraPage = new SephoraPage();
		sephoraPage.mailaddress = (String) object.get("mailaddress");
		sephoraPage.url = (String) object.get("url");
		sephoraPage.prodname = (String) object.get("prodname");
		if (object.get("expectPirce") != null)
			sephoraPage.expectPirce = Float.valueOf((String) object
					.get("expectPirce"));
		// sephoraPage.expectPirce = (Float) object.get("expectPirce");
		// sephoraPage.checkType = (String) object.get("checkType");
		// sephoraPage.checksalerisSephora = (Boolean) object
		// .get("checksalerisSephora");
		return sephoraPage;
	}

	public BasicDBObject convert2DB(SephoraPage sephoraPage) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", sephoraPage.mailaddress);
		doc.put("url", sephoraPage.url);
		doc.put("prodname", sephoraPage.prodname);
		doc.put("expectPirce", String.valueOf(sephoraPage.expectPirce));

		// doc.put("expectPirce", sephoraPage.expectPirce);
		// doc.put("checkType", sephoraPage.checkType);
		// doc.put("checksalerisSephora", sephoraPage.checksalerisSephora);
		return doc;

	}

	public static void main(String[] args) throws Exception {
		// sync2DB();

		System.out.println("done.");
	}

}

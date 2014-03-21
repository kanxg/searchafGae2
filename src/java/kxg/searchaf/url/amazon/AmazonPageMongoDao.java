package kxg.searchaf.url.amazon;

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

public class AmazonPageMongoDao {

	private DB db;
	private DBCollection coll;

	public AmazonPageMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("amazonlist");
	}

	public WriteResult save(AmazonPage amazonPage) {
		return coll.insert(convert2DB(amazonPage));
	}

	public void update(AmazonPage amazonPage) {
		BasicDBObject newDocument = convert2DB(amazonPage);
		coll.update(
				new BasicDBObject().append("mailaddress",
						amazonPage.mailaddress).append("url", amazonPage.url),
				newDocument);
	}

	public void delete(String mailaddress, String url) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		document.put("url", url);
		coll.remove(document);
	}

	public List<AmazonPage> findAll() {
		List<AmazonPage> maillist = new ArrayList<AmazonPage>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				AmazonPage amazonPage = parseAmazonMailList(object);
				maillist.add(amazonPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public List<AmazonPage> find(String mailaddress) {

		List<AmazonPage> maillist = new ArrayList<AmazonPage>();
		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				AmazonPage amazonPage = parseAmazonMailList(object);
				maillist.add(amazonPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public AmazonPage find(String mailaddress, String url) {

		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress).append("url", url);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseAmazonMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private AmazonPage parseAmazonMailList(BasicDBObject object) {
		AmazonPage amazonPage = new AmazonPage();
		amazonPage.mailaddress = (String) object.get("mailaddress");
		amazonPage.mailaddress = (String) object.get("mailaddress");

		amazonPage.url = (String) object.get("url");
		amazonPage.prodname = (String) object.get("prodname");
		if (object.get("expectPirce") != null)
			amazonPage.expectPirce = Float.valueOf((String) object
					.get("expectPirce"));
		if (object.get("checksalerisAmazon") != null)
			amazonPage.checksalerisAmazon = (Boolean) object
					.get("checksalerisAmazon");
		// amazonPage.expectPirce = (Float) object.get("expectPirce");
		// amazonPage.checkType = (String) object.get("checkType");
		// amazonPage.checksalerisAmazon = (Boolean) object
		// .get("checksalerisAmazon");
		return amazonPage;
	}

	public BasicDBObject convert2DB(AmazonPage amazonPage) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", amazonPage.mailaddress);
		doc.put("mailaddress", amazonPage.mailaddress);
		doc.put("url", amazonPage.url);
		doc.put("prodname", amazonPage.prodname);
		doc.put("expectPirce", String.valueOf(amazonPage.expectPirce));
 
		doc.put("checksalerisAmazon", amazonPage.checksalerisAmazon);
		// doc.put("expectPirce", amazonPage.expectPirce);
		// doc.put("checkType", amazonPage.checkType);
		// doc.put("checksalerisAmazon", amazonPage.checksalerisAmazon);
		return doc;

	}

	public static void main(String[] args) throws Exception {
		// sync2DB();

		System.out.println("done.");
	}

}

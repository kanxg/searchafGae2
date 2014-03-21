package kxg.searchaf.url.nordstrom;

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

public class NordstromPageMongoDao {

	private DB db;
	private DBCollection coll;

	public NordstromPageMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("nordstromlist");
	}

	public WriteResult save(NordstromPage nordstromPage) {
		return coll.insert(convert2DB(nordstromPage));
	}

	public void update(NordstromPage nordstromPage) {
		BasicDBObject newDocument = convert2DB(nordstromPage);
		coll.update(
				new BasicDBObject().append("mailaddress",
						nordstromPage.mailaddress).append("url", nordstromPage.url),
				newDocument);
	}

	public void delete(String mailaddress, String url) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		document.put("url", url);
		coll.remove(document);
	}

	public List<NordstromPage> findAll() {
		List<NordstromPage> maillist = new ArrayList<NordstromPage>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				NordstromPage nordstromPage = parseNordstromMailList(object);
				maillist.add(nordstromPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public List<NordstromPage> find(String mailaddress) {

		List<NordstromPage> maillist = new ArrayList<NordstromPage>();
		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				NordstromPage nordstromPage = parseNordstromMailList(object);
				maillist.add(nordstromPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public NordstromPage find(String mailaddress, String url) {

		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress).append("url", url);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseNordstromMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private NordstromPage parseNordstromMailList(BasicDBObject object) {
		NordstromPage nordstromPage = new NordstromPage();
		nordstromPage.mailaddress = (String) object.get("mailaddress");
		nordstromPage.url = (String) object.get("url");
		nordstromPage.prodname = (String) object.get("prodname");
		if (object.get("expectPirce") != null)
			nordstromPage.expectPirce = Float.valueOf((String) object
					.get("expectPirce"));
		// nordstromPage.expectPirce = (Float) object.get("expectPirce");
		// nordstromPage.checkType = (String) object.get("checkType");
		// nordstromPage.checksalerisNordstrom = (Boolean) object
		// .get("checksalerisNordstrom");
		return nordstromPage;
	}

	public BasicDBObject convert2DB(NordstromPage nordstromPage) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", nordstromPage.mailaddress);
		doc.put("url", nordstromPage.url);
		doc.put("prodname", nordstromPage.prodname);
		doc.put("expectPirce", String.valueOf(nordstromPage.expectPirce));

		// doc.put("expectPirce", nordstromPage.expectPirce);
		// doc.put("checkType", nordstromPage.checkType);
		// doc.put("checksalerisNordstrom", nordstromPage.checksalerisNordstrom);
		return doc;

	}

	public static void main(String[] args) throws Exception {
		// sync2DB();

		System.out.println("done.");
	}

}

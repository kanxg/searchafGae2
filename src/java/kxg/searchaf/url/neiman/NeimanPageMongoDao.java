package kxg.searchaf.url.neiman;

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

public class NeimanPageMongoDao {

	private DB db;
	private DBCollection coll;

	public NeimanPageMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("neimanlist");
	}

	public WriteResult save(NeimanPage neimanPage) {
		return coll.insert(convert2DB(neimanPage));
	}

	public void update(NeimanPage neimanPage) {
		BasicDBObject newDocument = convert2DB(neimanPage);
		coll.update(
				new BasicDBObject().append("mailaddress",
						neimanPage.mailaddress).append("url", neimanPage.url),
				newDocument);
	}

	public void delete(String mailaddress, String url) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		document.put("url", url);
		coll.remove(document);
	}

	public List<NeimanPage> findAll() {
		List<NeimanPage> maillist = new ArrayList<NeimanPage>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				NeimanPage neimanPage = parseNeimanMailList(object);
				maillist.add(neimanPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public List<NeimanPage> find(String mailaddress) {

		List<NeimanPage> maillist = new ArrayList<NeimanPage>();
		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				NeimanPage neimanPage = parseNeimanMailList(object);
				maillist.add(neimanPage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public NeimanPage find(String mailaddress, String url) {

		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress).append("url", url);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseNeimanMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private NeimanPage parseNeimanMailList(BasicDBObject object) {
		NeimanPage neimanPage = new NeimanPage();
		neimanPage.mailaddress = (String) object.get("mailaddress");
		neimanPage.url = (String) object.get("url");
		neimanPage.prodname = (String) object.get("prodname");
		if (object.get("expectPirce") != null)
			neimanPage.expectPirce = Float.valueOf((String) object
					.get("expectPirce"));
		// neimanPage.expectPirce = (Float) object.get("expectPirce");
		// neimanPage.checkType = (String) object.get("checkType");
		// neimanPage.checksalerisNeiman = (Boolean) object
		// .get("checksalerisNeiman");
		return neimanPage;
	}

	public BasicDBObject convert2DB(NeimanPage neimanPage) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", neimanPage.mailaddress);
		doc.put("url", neimanPage.url);
		doc.put("prodname", neimanPage.prodname);
		doc.put("expectPirce", String.valueOf(neimanPage.expectPirce));

		// doc.put("expectPirce", neimanPage.expectPirce);
		// doc.put("checkType", neimanPage.checkType);
		// doc.put("checksalerisNeiman", neimanPage.checksalerisNeiman);
		return doc;

	}

	public static void main(String[] args) throws Exception {
		// sync2DB();

		System.out.println("done.");
	}

}

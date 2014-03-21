package kxg.searchaf.url.drugstore;

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

public class DrugstorePageMongoDao {

	private DB db;
	private DBCollection coll;

	public DrugstorePageMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("drugstorelist");
	}

	public WriteResult save(DrugstorePage drugstorePage) {
		return coll.insert(convert2DB(drugstorePage));
	}

	public void update(DrugstorePage drugstorePage) {
		BasicDBObject newDocument = convert2DB(drugstorePage);
		coll.update(
				new BasicDBObject().append("mailaddress",
						drugstorePage.mailaddress).append("url", drugstorePage.url),
				newDocument);
	}

	public void delete(String mailaddress, String url) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		document.put("url", url);
		coll.remove(document);
	}

	public List<DrugstorePage> findAll() {
		List<DrugstorePage> maillist = new ArrayList<DrugstorePage>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				DrugstorePage drugstorePage = parseDrugstoreMailList(object);
				maillist.add(drugstorePage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public List<DrugstorePage> find(String mailaddress) {

		List<DrugstorePage> maillist = new ArrayList<DrugstorePage>();
		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				DrugstorePage drugstorePage = parseDrugstoreMailList(object);
				maillist.add(drugstorePage);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public DrugstorePage find(String mailaddress, String url) {

		BasicDBObject query = new BasicDBObject().append("mailaddress",
				mailaddress).append("url", url);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseDrugstoreMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private DrugstorePage parseDrugstoreMailList(BasicDBObject object) {
		DrugstorePage drugstorePage = new DrugstorePage();
		drugstorePage.mailaddress = (String) object.get("mailaddress");
		drugstorePage.mailaddress = (String) object.get("mailaddress");
		drugstorePage.url = (String) object.get("url");
		drugstorePage.prodname = (String) object.get("prodname");
		if (object.get("expectPirce") != null)
			drugstorePage.expectPirce = Float.valueOf((String) object
					.get("expectPirce"));
		// drugstorePage.expectPirce = (Float) object.get("expectPirce");
		// drugstorePage.checkType = (String) object.get("checkType");
		// drugstorePage.checksalerisDrugstore = (Boolean) object
		// .get("checksalerisDrugstore");
		return drugstorePage;
	}

	public BasicDBObject convert2DB(DrugstorePage drugstorePage) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", drugstorePage.mailaddress);
		doc.put("mailaddress", drugstorePage.mailaddress);
		doc.put("url", drugstorePage.url);
		doc.put("prodname", drugstorePage.prodname);
		doc.put("expectPirce", String.valueOf(drugstorePage.expectPirce));

		// doc.put("expectPirce", drugstorePage.expectPirce);
		// doc.put("checkType", drugstorePage.checkType);
		// doc.put("checksalerisDrugstore", drugstorePage.checksalerisDrugstore);
		return doc;

	}

	public static void main(String[] args) throws Exception {
		// sync2DB();
		DrugstorePageMongoDao	dao=new DrugstorePageMongoDao();
		System.out.println("done.");
	}

}

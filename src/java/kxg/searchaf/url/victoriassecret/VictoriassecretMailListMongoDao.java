package kxg.searchaf.url.victoriassecret;

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

public class VictoriassecretMailListMongoDao {

	private DB db;
	private DBCollection coll;

	public VictoriassecretMailListMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("victoriassecretmaillist");
	}

	public VictoriassecretMailListMongoDao(boolean usingBackupDb)
			throws UnknownHostException {
		if (usingBackupDb) {
 			db = MongoBackupDao.getDao();
			coll = db.getCollection("victoriassecretmaillist");
		}
	}

	public WriteResult save(VictoriassecretMailList victoriassecretMailList) {
		return coll.insert(convert2DB(victoriassecretMailList));
	}

	public void update(VictoriassecretMailList victoriassecretMailList) {
		BasicDBObject newDocument = convert2DB(victoriassecretMailList);
		coll.update(new BasicDBObject().append("mailaddress",
				victoriassecretMailList.mailaddress), newDocument);
	}

	public void delete(String mailaddress) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		coll.remove(document);
	}

	public List<VictoriassecretMailList> findAll() {
		List<VictoriassecretMailList> maillist = new ArrayList<VictoriassecretMailList>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				VictoriassecretMailList victoriassecretMailList = parseVictoriassecretMailList(object);
				maillist.add(victoriassecretMailList);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public VictoriassecretMailList find(String mailaddress) {

		BasicDBObject query = new BasicDBObject("mailaddress", mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseVictoriassecretMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private VictoriassecretMailList parseVictoriassecretMailList(BasicDBObject object) {
		VictoriassecretMailList victoriassecretMailList = new VictoriassecretMailList();
		victoriassecretMailList.mailaddress = (String) object.get("mailaddress");
		victoriassecretMailList.userType = (String) object.get("userType");
		victoriassecretMailList.valideTime = (Date) object.get("valideTime");
		victoriassecretMailList.warningWomen = (Boolean) object.get("warningWomen");

		if (object.get("womencheckingSaleDiscount") != null)
			victoriassecretMailList.womencheckingSaleDiscount = Float
					.valueOf((String) object.get("womencheckingSaleDiscount"));

		return victoriassecretMailList;
	}

	public BasicDBObject convert2DB(VictoriassecretMailList victoriassecretMailList) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", victoriassecretMailList.mailaddress);
		doc.put("userType", victoriassecretMailList.userType);
		doc.put("valideTime", victoriassecretMailList.valideTime);
		doc.put("warningWomen", victoriassecretMailList.warningWomen);
		doc.put("womencheckingSaleDiscount",
				String.valueOf(victoriassecretMailList.womencheckingSaleDiscount));

		return doc;

	}

	public static void main(String[] args) throws Exception {
		// sync2DB();
		System.out.println("done.");
	}

}

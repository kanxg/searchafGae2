package kxg.searchaf.url.toryburch;

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

public class ToryburchMailListMongoDao {

	private DB db;
	private DBCollection coll;

	public ToryburchMailListMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("toryburchmaillist");
	}

	public ToryburchMailListMongoDao(boolean usingBackupDb)
			throws UnknownHostException {
		if (usingBackupDb) {
 			db = MongoBackupDao.getDao();
			coll = db.getCollection("toryburchmaillist");
		}
	}

	public WriteResult save(ToryburchMailList toryburchMailList) {
		return coll.insert(convert2DB(toryburchMailList));
	}

	public void update(ToryburchMailList toryburchMailList) {
		BasicDBObject newDocument = convert2DB(toryburchMailList);
		coll.update(new BasicDBObject().append("mailaddress",
				toryburchMailList.mailaddress), newDocument);
	}

	public void delete(String mailaddress) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		coll.remove(document);
	}

	public List<ToryburchMailList> findAll() {
		List<ToryburchMailList> maillist = new ArrayList<ToryburchMailList>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				ToryburchMailList toryburchMailList = parseToryburchMailList(object);
				maillist.add(toryburchMailList);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public ToryburchMailList find(String mailaddress) {

		BasicDBObject query = new BasicDBObject("mailaddress", mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseToryburchMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private ToryburchMailList parseToryburchMailList(BasicDBObject object) {
		ToryburchMailList toryburchMailList = new ToryburchMailList();
		toryburchMailList.mailaddress = (String) object.get("mailaddress");
		toryburchMailList.userType = (String) object.get("userType");
		toryburchMailList.valideTime = (Date) object.get("valideTime");
		toryburchMailList.warningWomen = (Boolean) object.get("warningWomen");

		if (object.get("womencheckingSaleDiscount") != null)
			toryburchMailList.womencheckingSaleDiscount = Float
					.valueOf((String) object.get("womencheckingSaleDiscount"));

		return toryburchMailList;
	}

	public BasicDBObject convert2DB(ToryburchMailList toryburchMailList) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", toryburchMailList.mailaddress);
		doc.put("userType", toryburchMailList.userType);
		doc.put("valideTime", toryburchMailList.valideTime);
		doc.put("warningWomen", toryburchMailList.warningWomen);
		doc.put("womencheckingSaleDiscount",
				String.valueOf(toryburchMailList.womencheckingSaleDiscount));

		return doc;

	}

	public static void main(String[] args) throws Exception {
		// sync2DB();
		System.out.println("done.");
	}

}

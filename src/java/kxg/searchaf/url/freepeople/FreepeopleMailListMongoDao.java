package kxg.searchaf.url.freepeople;

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

public class FreepeopleMailListMongoDao {

	private DB db;
	private DBCollection coll;

	public FreepeopleMailListMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("freepeoplemaillist");
	}

	public FreepeopleMailListMongoDao(boolean usingBackupDb)
			throws UnknownHostException {
		if (usingBackupDb) {
 			db = MongoBackupDao.getDao();
			coll = db.getCollection("freepeoplemaillist");
		}
	}

	public WriteResult save(FreepeopleMailList freepeopleMailList) {
		return coll.insert(convert2DB(freepeopleMailList));
	}

	public void update(FreepeopleMailList freepeopleMailList) {
		BasicDBObject newDocument = convert2DB(freepeopleMailList);
		coll.update(new BasicDBObject().append("mailaddress",
				freepeopleMailList.mailaddress), newDocument);
	}

	public void delete(String mailaddress) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		coll.remove(document);
	}

	public List<FreepeopleMailList> findAll() {
		List<FreepeopleMailList> maillist = new ArrayList<FreepeopleMailList>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				FreepeopleMailList freepeopleMailList = parseFreepeopleMailList(object);
				maillist.add(freepeopleMailList);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public FreepeopleMailList find(String mailaddress) {

		BasicDBObject query = new BasicDBObject("mailaddress", mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseFreepeopleMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private FreepeopleMailList parseFreepeopleMailList(BasicDBObject object) {
		FreepeopleMailList freepeopleMailList = new FreepeopleMailList();
		freepeopleMailList.mailaddress = (String) object.get("mailaddress");
		freepeopleMailList.userType = (String) object.get("userType");
		freepeopleMailList.valideTime = (Date) object.get("valideTime");
		freepeopleMailList.warningWomen = (Boolean) object.get("warningWomen");

		if (object.get("womencheckingSaleDiscount") != null)
			freepeopleMailList.womencheckingSaleDiscount = Float
					.valueOf((String) object.get("womencheckingSaleDiscount"));

		return freepeopleMailList;
	}

	public BasicDBObject convert2DB(FreepeopleMailList freepeopleMailList) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", freepeopleMailList.mailaddress);
		doc.put("userType", freepeopleMailList.userType);
		doc.put("valideTime", freepeopleMailList.valideTime);
		doc.put("warningWomen", freepeopleMailList.warningWomen);
		doc.put("womencheckingSaleDiscount",
				String.valueOf(freepeopleMailList.womencheckingSaleDiscount));

		return doc;

	}

	public static void main(String[] args) throws Exception {
		// sync2DB();
		System.out.println("done.");
	}

}

package kxg.searchaf.url.tommy;

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

public class TommyMailListMongoDao {

	private DB db;
	private DBCollection coll;

	public TommyMailListMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("tommymaillist");
	}

	public TommyMailListMongoDao(boolean usingBackupDb)
			throws UnknownHostException {
		if (usingBackupDb) {
 			db = MongoBackupDao.getDao();
			coll = db.getCollection("tommymaillist");
		}
	}

	public WriteResult save(TommyMailList tommyMailList) {
		return coll.insert(convert2DB(tommyMailList));
	}

	public void update(TommyMailList tommyMailList) {
		BasicDBObject newDocument = convert2DB(tommyMailList);
		coll.update(new BasicDBObject().append("mailaddress",
				tommyMailList.mailaddress), newDocument);
	}

	public void delete(String mailaddress) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		coll.remove(document);
	}

	public List<TommyMailList> findAll() {
		List<TommyMailList> maillist = new ArrayList<TommyMailList>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				TommyMailList tommyMailList = parseTommyMailList(object);
				maillist.add(tommyMailList);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public TommyMailList find(String mailaddress) {

		BasicDBObject query = new BasicDBObject("mailaddress", mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseTommyMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private TommyMailList parseTommyMailList(BasicDBObject object) {
		TommyMailList tommyMailList = new TommyMailList();
		tommyMailList.mailaddress = (String) object.get("mailaddress");
		tommyMailList.userType = (String) object.get("userType");
		tommyMailList.valideTime = (Date) object.get("valideTime");

		tommyMailList.warningMen = (Boolean) object.get("warningMen");
		if (object.get("mencheckingSaleDiscount") != null)
			tommyMailList.mencheckingSaleDiscount = Float
					.valueOf((String) object.get("mencheckingSaleDiscount"));

		tommyMailList.warningWomen = (Boolean) object.get("warningWomen");
		if (object.get("womencheckingSaleDiscount") != null)
			tommyMailList.womencheckingSaleDiscount = Float
					.valueOf((String) object.get("womencheckingSaleDiscount"));

		tommyMailList.warningBoy = (Boolean) object.get("warningBoy");
		if (object.get("boycheckingSaleDiscount") != null)
			tommyMailList.boycheckingSaleDiscount = Float
					.valueOf((String) object.get("boycheckingSaleDiscount"));

		tommyMailList.warningGirl = (Boolean) object.get("warningGirl");
		if (object.get("girlcheckingSaleDiscount") != null)
			tommyMailList.girlcheckingSaleDiscount = Float
					.valueOf((String) object.get("girlcheckingSaleDiscount"));

		return tommyMailList;
	}

	public BasicDBObject convert2DB(TommyMailList tommyMailList) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", tommyMailList.mailaddress);
		doc.put("userType", tommyMailList.userType);
		doc.put("valideTime", tommyMailList.valideTime);

		doc.put("warningMen", tommyMailList.warningMen);
		doc.put("mencheckingSaleDiscount",
				String.valueOf(tommyMailList.mencheckingSaleDiscount));

		doc.put("warningWomen", tommyMailList.warningWomen);
		doc.put("womencheckingSaleDiscount",
				String.valueOf(tommyMailList.womencheckingSaleDiscount));

		doc.put("warningBoy", tommyMailList.warningBoy);
		doc.put("boycheckingSaleDiscount",
				String.valueOf(tommyMailList.boycheckingSaleDiscount));

		doc.put("warningGirl", tommyMailList.warningGirl);
		doc.put("girlcheckingSaleDiscount",
				String.valueOf(tommyMailList.girlcheckingSaleDiscount));

		return doc;

	}

	public static void main(String[] args) throws Exception {
		// sync2DB();
		System.out.println("done.");
	}

}

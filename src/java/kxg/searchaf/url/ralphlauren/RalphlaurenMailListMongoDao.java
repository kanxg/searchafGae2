package kxg.searchaf.url.ralphlauren;

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

public class RalphlaurenMailListMongoDao {

	private DB db;
	private DBCollection coll;

	public RalphlaurenMailListMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("ralphlaurenmaillist");
	}

	public RalphlaurenMailListMongoDao(boolean usingBackupDb)
			throws UnknownHostException {
		if (usingBackupDb) {
 			db = MongoBackupDao.getDao();
			coll = db.getCollection("ralphlaurenmaillist");
		}
	}

	public WriteResult save(RalphlaurenMailList ralphlaurenMailList) {
		return coll.insert(convert2DB(ralphlaurenMailList));
	}

	public void update(RalphlaurenMailList ralphlaurenMailList) {
		BasicDBObject newDocument = convert2DB(ralphlaurenMailList);
		coll.update(new BasicDBObject().append("mailaddress",
				ralphlaurenMailList.mailaddress), newDocument);
	}

	public void delete(String mailaddress) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		coll.remove(document);
	}

	public List<RalphlaurenMailList> findAll() {
		List<RalphlaurenMailList> maillist = new ArrayList<RalphlaurenMailList>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				RalphlaurenMailList ralphlaurenMailList = parseRalphlaurenMailList(object);
				maillist.add(ralphlaurenMailList);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public RalphlaurenMailList find(String mailaddress) {

		BasicDBObject query = new BasicDBObject("mailaddress", mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseRalphlaurenMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private RalphlaurenMailList parseRalphlaurenMailList(BasicDBObject object) {
		RalphlaurenMailList ralphlaurenMailList = new RalphlaurenMailList();
		ralphlaurenMailList.mailaddress = (String) object.get("mailaddress");
		ralphlaurenMailList.userType = (String) object.get("userType");
		ralphlaurenMailList.valideTime = (Date) object.get("valideTime");

		ralphlaurenMailList.warningMen = (Boolean) object.get("warningMen");
		if (object.get("mencheckingSaleDiscount") != null)
			ralphlaurenMailList.mencheckingSaleDiscount = Float
					.valueOf((String) object.get("mencheckingSaleDiscount"));

		ralphlaurenMailList.warningWomen = (Boolean) object.get("warningWomen");
		if (object.get("womencheckingSaleDiscount") != null)
			ralphlaurenMailList.womencheckingSaleDiscount = Float
					.valueOf((String) object.get("womencheckingSaleDiscount"));

		ralphlaurenMailList.warningBoy = (Boolean) object.get("warningBoy");
		if (object.get("boycheckingSaleDiscount") != null)
			ralphlaurenMailList.boycheckingSaleDiscount = Float
					.valueOf((String) object.get("boycheckingSaleDiscount"));

		ralphlaurenMailList.warningGirl = (Boolean) object.get("warningGirl");
		if (object.get("girlcheckingSaleDiscount") != null)
			ralphlaurenMailList.girlcheckingSaleDiscount = Float
					.valueOf((String) object.get("girlcheckingSaleDiscount"));

		return ralphlaurenMailList;
	}

	public BasicDBObject convert2DB(RalphlaurenMailList ralphlaurenMailList) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", ralphlaurenMailList.mailaddress);
		doc.put("userType", ralphlaurenMailList.userType);
		doc.put("valideTime", ralphlaurenMailList.valideTime);

		doc.put("warningMen", ralphlaurenMailList.warningMen);
		doc.put("mencheckingSaleDiscount",
				String.valueOf(ralphlaurenMailList.mencheckingSaleDiscount));

		doc.put("warningWomen", ralphlaurenMailList.warningWomen);
		doc.put("womencheckingSaleDiscount",
				String.valueOf(ralphlaurenMailList.womencheckingSaleDiscount));

		doc.put("warningBoy", ralphlaurenMailList.warningBoy);
		doc.put("boycheckingSaleDiscount",
				String.valueOf(ralphlaurenMailList.boycheckingSaleDiscount));

		doc.put("warningGirl", ralphlaurenMailList.warningGirl);
		doc.put("girlcheckingSaleDiscount",
				String.valueOf(ralphlaurenMailList.girlcheckingSaleDiscount));

		return doc;

	}

	public static void main(String[] args) throws Exception {
		// sync2DB();
		System.out.println("done.");
	}

}

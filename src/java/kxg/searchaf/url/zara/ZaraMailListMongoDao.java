package kxg.searchaf.url.zara;

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

public class ZaraMailListMongoDao {

	private DB db;
	private DBCollection coll;

	public ZaraMailListMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("zaramaillist");
	}

	public ZaraMailListMongoDao(boolean usingBackupDb)
			throws UnknownHostException {
		if (usingBackupDb) {
 			db = MongoBackupDao.getDao();
			coll = db.getCollection("zaramaillist");
		}
	}

	public WriteResult save(ZaraMailList zaraMailList) {
		return coll.insert(convert2DB(zaraMailList));
	}

	public void update(ZaraMailList zaraMailList) {
		BasicDBObject newDocument = convert2DB(zaraMailList);
		coll.update(new BasicDBObject().append("mailaddress",
				zaraMailList.mailaddress), newDocument);
	}

	public void delete(String mailaddress) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		coll.remove(document);
	}

	public List<ZaraMailList> findAll() {
		List<ZaraMailList> maillist = new ArrayList<ZaraMailList>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				ZaraMailList zaraMailList = parseZaraMailList(object);
				maillist.add(zaraMailList);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public ZaraMailList find(String mailaddress) {

		BasicDBObject query = new BasicDBObject("mailaddress", mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseZaraMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private ZaraMailList parseZaraMailList(BasicDBObject object) {
		ZaraMailList zaraMailList = new ZaraMailList();
		zaraMailList.mailaddress = (String) object.get("mailaddress");
		zaraMailList.userType = (String) object.get("userType");
		zaraMailList.valideTime = (Date) object.get("valideTime");

		zaraMailList.warningMen = (Boolean) object.get("warningMen");
		if (object.get("mencheckingSaleDiscount") != null)
			zaraMailList.mencheckingSaleDiscount = Float
					.valueOf((String) object.get("mencheckingSaleDiscount"));

		zaraMailList.warningWomen = (Boolean) object.get("warningWomen");
		if (object.get("womencheckingSaleDiscount") != null)
			zaraMailList.womencheckingSaleDiscount = Float
					.valueOf((String) object.get("womencheckingSaleDiscount"));

		zaraMailList.warningBoy = (Boolean) object.get("warningBoy");
		if (object.get("boycheckingSaleDiscount") != null)
			zaraMailList.boycheckingSaleDiscount = Float
					.valueOf((String) object.get("boycheckingSaleDiscount"));

		zaraMailList.warningGirl = (Boolean) object.get("warningGirl");
		if (object.get("girlcheckingSaleDiscount") != null)
			zaraMailList.girlcheckingSaleDiscount = Float
					.valueOf((String) object.get("girlcheckingSaleDiscount"));

		return zaraMailList;
	}

	public BasicDBObject convert2DB(ZaraMailList zaraMailList) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", zaraMailList.mailaddress);
		doc.put("userType", zaraMailList.userType);
		doc.put("valideTime", zaraMailList.valideTime);

		doc.put("warningMen", zaraMailList.warningMen);
		doc.put("mencheckingSaleDiscount",
				String.valueOf(zaraMailList.mencheckingSaleDiscount));

		doc.put("warningWomen", zaraMailList.warningWomen);
		doc.put("womencheckingSaleDiscount",
				String.valueOf(zaraMailList.womencheckingSaleDiscount));

		doc.put("warningBoy", zaraMailList.warningBoy);
		doc.put("boycheckingSaleDiscount",
				String.valueOf(zaraMailList.boycheckingSaleDiscount));

		doc.put("warningGirl", zaraMailList.warningGirl);
		doc.put("girlcheckingSaleDiscount",
				String.valueOf(zaraMailList.girlcheckingSaleDiscount));

		return doc;

	}

	public static void main(String[] args) throws Exception {
		// sync2DB();
		System.out.println("done.");
	}

}

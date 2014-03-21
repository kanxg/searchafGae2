package kxg.searchaf.url.gymboree;

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

public class GymboreeMailListMongoDao {

	private DB db;
	private DBCollection coll;

	public GymboreeMailListMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("gymboreemaillist");
	}

	public GymboreeMailListMongoDao(boolean usingBackupDb)
			throws UnknownHostException {
		if (usingBackupDb) {
 			db = MongoBackupDao.getDao();
			coll = db.getCollection("gymboreemaillist");
		}
	}

	public WriteResult save(GymboreeMailList gymboreeMailList) {
		return coll.insert(convert2DB(gymboreeMailList));
	}

	public void update(GymboreeMailList gymboreeMailList) {
		BasicDBObject newDocument = convert2DB(gymboreeMailList);
		coll.update(new BasicDBObject().append("mailaddress",
				gymboreeMailList.mailaddress), newDocument);
	}

	public void delete(String mailaddress) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		coll.remove(document);
	}

	public List<GymboreeMailList> findAll() {
		List<GymboreeMailList> maillist = new ArrayList<GymboreeMailList>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				GymboreeMailList gymboreeMailList = parseGymboreeMailList(object);
				maillist.add(gymboreeMailList);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public GymboreeMailList find(String mailaddress) {

		BasicDBObject query = new BasicDBObject("mailaddress", mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseGymboreeMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private GymboreeMailList parseGymboreeMailList(BasicDBObject object) {
		GymboreeMailList gymboreeMailList = new GymboreeMailList();
		gymboreeMailList.mailaddress = (String) object.get("mailaddress");
		gymboreeMailList.userType = (String) object.get("userType");
		gymboreeMailList.valideTime = (Date) object.get("valideTime");

		gymboreeMailList.warningMen = (Boolean) object.get("warningMen");
		if (object.get("mencheckingSaleDiscount") != null)
			gymboreeMailList.mencheckingSaleDiscount = Float
					.valueOf((String) object.get("mencheckingSaleDiscount"));

		gymboreeMailList.warningWomen = (Boolean) object.get("warningWomen");
		if (object.get("womencheckingSaleDiscount") != null)
			gymboreeMailList.womencheckingSaleDiscount = Float
					.valueOf((String) object.get("womencheckingSaleDiscount"));

		gymboreeMailList.warningBoy = (Boolean) object.get("warningBoy");
		if (object.get("boycheckingSaleDiscount") != null)
			gymboreeMailList.boycheckingSaleDiscount = Float
					.valueOf((String) object.get("boycheckingSaleDiscount"));

		gymboreeMailList.warningGirl = (Boolean) object.get("warningGirl");
		if (object.get("girlcheckingSaleDiscount") != null)
			gymboreeMailList.girlcheckingSaleDiscount = Float
					.valueOf((String) object.get("girlcheckingSaleDiscount"));

		return gymboreeMailList;
	}

	public BasicDBObject convert2DB(GymboreeMailList gymboreeMailList) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", gymboreeMailList.mailaddress);
		doc.put("userType", gymboreeMailList.userType);
		doc.put("valideTime", gymboreeMailList.valideTime);

		doc.put("warningMen", gymboreeMailList.warningMen);
		doc.put("mencheckingSaleDiscount",
				String.valueOf(gymboreeMailList.mencheckingSaleDiscount));

		doc.put("warningWomen", gymboreeMailList.warningWomen);
		doc.put("womencheckingSaleDiscount",
				String.valueOf(gymboreeMailList.womencheckingSaleDiscount));

		doc.put("warningBoy", gymboreeMailList.warningBoy);
		doc.put("boycheckingSaleDiscount",
				String.valueOf(gymboreeMailList.boycheckingSaleDiscount));

		doc.put("warningGirl", gymboreeMailList.warningGirl);
		doc.put("girlcheckingSaleDiscount",
				String.valueOf(gymboreeMailList.girlcheckingSaleDiscount));

		return doc;

	}

	public static void main(String[] args) throws Exception {
		// sync2DB();
		System.out.println("done.");
	}

}

package kxg.searchaf.url.juicycouture;

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

public class JuicycoutureMailListMongoDao {

	private DB db;
	private DBCollection coll;

	public JuicycoutureMailListMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("juicycouturemaillist");
	}

	public JuicycoutureMailListMongoDao(boolean usingBackupDb)
			throws UnknownHostException {
		if (usingBackupDb) {
 			db = MongoBackupDao.getDao();
			coll = db.getCollection("juicycouturemaillist");
		}
	}

	public WriteResult save(JuicycoutureMailList juicycoutureMailList) {
		return coll.insert(convert2DB(juicycoutureMailList));
	}

	public void update(JuicycoutureMailList juicycoutureMailList) {
		BasicDBObject newDocument = convert2DB(juicycoutureMailList);
		coll.update(new BasicDBObject().append("mailaddress",
				juicycoutureMailList.mailaddress), newDocument);
	}

	public void delete(String mailaddress) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		coll.remove(document);
	}

	public List<JuicycoutureMailList> findAll() {
		List<JuicycoutureMailList> maillist = new ArrayList<JuicycoutureMailList>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				JuicycoutureMailList juicycoutureMailList = parseJuicycoutureMailList(object);
				maillist.add(juicycoutureMailList);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public JuicycoutureMailList find(String mailaddress) {

		BasicDBObject query = new BasicDBObject("mailaddress", mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseJuicycoutureMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private JuicycoutureMailList parseJuicycoutureMailList(BasicDBObject object) {
		JuicycoutureMailList juicycoutureMailList = new JuicycoutureMailList();
		juicycoutureMailList.mailaddress = (String) object.get("mailaddress");
		juicycoutureMailList.userType = (String) object.get("userType");
		juicycoutureMailList.valideTime = (Date) object.get("valideTime");
		juicycoutureMailList.warningWomen = (Boolean) object.get("warningWomen");

		if (object.get("womencheckingSaleDiscount") != null)
			juicycoutureMailList.womencheckingSaleDiscount = Float
					.valueOf((String) object.get("womencheckingSaleDiscount"));

		return juicycoutureMailList;
	}

	public BasicDBObject convert2DB(JuicycoutureMailList juicycoutureMailList) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", juicycoutureMailList.mailaddress);
		doc.put("userType", juicycoutureMailList.userType);
		doc.put("valideTime", juicycoutureMailList.valideTime);
		doc.put("warningWomen", juicycoutureMailList.warningWomen);
		doc.put("womencheckingSaleDiscount",
				String.valueOf(juicycoutureMailList.womencheckingSaleDiscount));

		return doc;

	}

	public static void main(String[] args) throws Exception {
		// sync2DB();
		System.out.println("done.");
	}

}

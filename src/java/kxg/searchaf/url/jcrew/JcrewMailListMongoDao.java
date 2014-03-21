package kxg.searchaf.url.jcrew;

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

public class JcrewMailListMongoDao {

	private DB db;
	private DBCollection coll;

	public JcrewMailListMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("jcrewmaillist");
	}

	public JcrewMailListMongoDao(boolean usingBackupDb)
			throws UnknownHostException {
		if (usingBackupDb) {
 			db = MongoBackupDao.getDao();
			coll = db.getCollection("jcrewmaillist");
		}
	}

	public WriteResult save(JcrewMailList jcrewMailList) {
		return coll.insert(convert2DB(jcrewMailList));
	}

	public void update(JcrewMailList jcrewMailList) {
		BasicDBObject newDocument = convert2DB(jcrewMailList);
		coll.update(new BasicDBObject().append("mailaddress",
				jcrewMailList.mailaddress), newDocument);
	}

	public void delete(String mailaddress) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		coll.remove(document);
	}

	public List<JcrewMailList> findAll() {
		List<JcrewMailList> maillist = new ArrayList<JcrewMailList>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				JcrewMailList jcrewMailList = parseJcrewMailList(object);
				maillist.add(jcrewMailList);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public JcrewMailList find(String mailaddress) {

		BasicDBObject query = new BasicDBObject("mailaddress", mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseJcrewMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private JcrewMailList parseJcrewMailList(BasicDBObject object) {
		JcrewMailList jcrewMailList = new JcrewMailList();
		jcrewMailList.mailaddress = (String) object.get("mailaddress");
		jcrewMailList.userType = (String) object.get("userType");
		jcrewMailList.valideTime = (Date) object.get("valideTime");

		jcrewMailList.warningMen = (Boolean) object.get("warningMen");
		if (object.get("mencheckingSaleDiscount") != null)
			jcrewMailList.mencheckingSaleDiscount = Float
					.valueOf((String) object.get("mencheckingSaleDiscount"));

		jcrewMailList.warningWomen = (Boolean) object.get("warningWomen");
		if (object.get("womencheckingSaleDiscount") != null)
			jcrewMailList.womencheckingSaleDiscount = Float
					.valueOf((String) object.get("womencheckingSaleDiscount"));

		jcrewMailList.warningBoy = (Boolean) object.get("warningBoy");
		if (object.get("boycheckingSaleDiscount") != null)
			jcrewMailList.boycheckingSaleDiscount = Float
					.valueOf((String) object.get("boycheckingSaleDiscount"));

		jcrewMailList.warningGirl = (Boolean) object.get("warningGirl");
		if (object.get("girlcheckingSaleDiscount") != null)
			jcrewMailList.girlcheckingSaleDiscount = Float
					.valueOf((String) object.get("girlcheckingSaleDiscount"));

		return jcrewMailList;
	}

	public BasicDBObject convert2DB(JcrewMailList jcrewMailList) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", jcrewMailList.mailaddress);
		doc.put("userType", jcrewMailList.userType);
		doc.put("valideTime", jcrewMailList.valideTime);

		doc.put("warningMen", jcrewMailList.warningMen);
		doc.put("mencheckingSaleDiscount",
				String.valueOf(jcrewMailList.mencheckingSaleDiscount));

		doc.put("warningWomen", jcrewMailList.warningWomen);
		doc.put("womencheckingSaleDiscount",
				String.valueOf(jcrewMailList.womencheckingSaleDiscount));

		doc.put("warningBoy", jcrewMailList.warningBoy);
		doc.put("boycheckingSaleDiscount",
				String.valueOf(jcrewMailList.boycheckingSaleDiscount));

		doc.put("warningGirl", jcrewMailList.warningGirl);
		doc.put("girlcheckingSaleDiscount",
				String.valueOf(jcrewMailList.girlcheckingSaleDiscount));

		return doc;

	}

	public static void main(String[] args) throws Exception {
		// sync2DB();
		System.out.println("done.");
	}

}

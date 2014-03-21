package kxg.searchaf.url.hollisterco;

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

public class HollistercoMailListMongoDao {

	private DB db;
	private DBCollection coll;

	public HollistercoMailListMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("hollistercomaillist");
	}

	public HollistercoMailListMongoDao(boolean usingBackupDb)
			throws UnknownHostException {
		if (usingBackupDb) {
 			db = MongoBackupDao.getDao();
			coll = db.getCollection("hollistercomaillist");
		}
	}

	public WriteResult save(HollistercoMailList hollistercoMailList) {
		return coll.insert(convert2DB(hollistercoMailList));
	}

	public void update(HollistercoMailList hollistercoMailList) {
		BasicDBObject newDocument = convert2DB(hollistercoMailList);
		coll.update(new BasicDBObject().append("mailaddress",
				hollistercoMailList.mailaddress), newDocument);
	}

	public void delete(String mailaddress) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		coll.remove(document);
	}

	public List<HollistercoMailList> findAll() {
		List<HollistercoMailList> maillist = new ArrayList<HollistercoMailList>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				HollistercoMailList hollistercoMailList = parseHollistercoMailList(object);
				maillist.add(hollistercoMailList);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public HollistercoMailList find(String mailaddress) {

		BasicDBObject query = new BasicDBObject("mailaddress", mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseHollistercoMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private HollistercoMailList parseHollistercoMailList(BasicDBObject object) {
		HollistercoMailList hollistercoMailList = new HollistercoMailList();
		hollistercoMailList.mailaddress = (String) object.get("mailaddress");
		hollistercoMailList.userType = (String) object.get("userType");
		hollistercoMailList.valideTime = (Date) object.get("valideTime");
		hollistercoMailList.warningMen = (Boolean) object.get("warningMen");
		hollistercoMailList.warningWomen = (Boolean) object.get("warningWomen");
		if (object.get("warningCode") != null)
			hollistercoMailList.warningCode = (Boolean) object.get("warningCode");
		if (object.get("mencheckingRegular") != null)
			hollistercoMailList.mencheckingRegular = (Boolean) object
					.get("mencheckingRegular");
		if (object.get("mencheckingSecretSale") != null)
			hollistercoMailList.mencheckingSecretSale = (Boolean) object
					.get("mencheckingSecretSale");
		if (object.get("mencheckingClearance") != null)
			hollistercoMailList.mencheckingClearance = (Boolean) object
					.get("mencheckingClearance");
		if (object.get("mencheckingSale") != null)
			hollistercoMailList.mencheckingSale = (Boolean) object
					.get("mencheckingSale");
		if (object.get("mencheckingClearanceDiscount") != null)
			hollistercoMailList.mencheckingClearanceDiscount = Float
					.valueOf((String) object
							.get("mencheckingClearanceDiscount"));
		if (object.get("mencheckingSaleDiscount") != null)
			hollistercoMailList.mencheckingSaleDiscount = Float.valueOf((String) object
					.get("mencheckingSaleDiscount"));
		if (object.get("mencheckingCategory") != null) {
			// hollistercoMailList.checkingCategory = (ArrayList<String>) object
			// .get("checkingCategory");
			if (hollistercoMailList.mencheckingCategory == null)
				hollistercoMailList.mencheckingCategory = new ArrayList<String>();
			hollistercoMailList.mencheckingCategory.addAll((List<String>) object
					.get("mencheckingCategory"));
		}
		if (object.get("mencheckingSize") != null) {
			if (hollistercoMailList.mencheckingSize == null)
				hollistercoMailList.mencheckingSize = new ArrayList<String>();
			hollistercoMailList.mencheckingSize.addAll((List<String>) object
					.get("mencheckingSize"));
		}
		if (object.get("womencheckingRegular") != null)
			hollistercoMailList.womencheckingRegular = (Boolean) object
					.get("womencheckingRegular");
		if (object.get("womencheckingSecretSale") != null)
			hollistercoMailList.womencheckingSecretSale = (Boolean) object
					.get("womencheckingSecretSale");
		if (object.get("womencheckingClearance") != null)
			hollistercoMailList.womencheckingClearance = (Boolean) object
					.get("womencheckingClearance");
		if (object.get("womencheckingSale") != null)
			hollistercoMailList.womencheckingSale = (Boolean) object
					.get("womencheckingSale");
		if (object.get("womencheckingClearanceDiscount") != null)
			hollistercoMailList.womencheckingClearanceDiscount = Float
					.valueOf((String) object
							.get("womencheckingClearanceDiscount"));
		if (object.get("womencheckingSaleDiscount") != null)
			hollistercoMailList.womencheckingSaleDiscount = Float
					.valueOf((String) object.get("womencheckingSaleDiscount"));
		if (object.get("womencheckingCategory") != null) {
			// hollistercoMailList.checkingCategory = (ArrayList<String>) object
			// .get("checkingCategory");
			if (hollistercoMailList.womencheckingCategory == null)
				hollistercoMailList.womencheckingCategory = new ArrayList<String>();
			hollistercoMailList.womencheckingCategory.addAll((List<String>) object
					.get("womencheckingCategory"));
		}
		if (object.get("womencheckingSize") != null) {
			if (hollistercoMailList.womencheckingSize == null)
				hollistercoMailList.womencheckingSize = new ArrayList<String>();
			hollistercoMailList.womencheckingSize.addAll((List<String>) object
					.get("womencheckingSize"));
		}
		return hollistercoMailList;
	}

	public BasicDBObject convert2DB(HollistercoMailList hollistercoMailList) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", hollistercoMailList.mailaddress);
		doc.put("userType", hollistercoMailList.userType);
		doc.put("valideTime", hollistercoMailList.valideTime);
		doc.put("warningMen", hollistercoMailList.warningMen);
		doc.put("warningCode", hollistercoMailList.warningCode);
		doc.put("warningWomen", hollistercoMailList.warningWomen);
		doc.put("mencheckingRegular", hollistercoMailList.mencheckingRegular);
		doc.put("mencheckingSecretSale", hollistercoMailList.mencheckingSecretSale);
		doc.put("mencheckingClearance", hollistercoMailList.mencheckingClearance);
		doc.put("mencheckingSale", hollistercoMailList.mencheckingSale);
		doc.put("mencheckingClearanceDiscount",
				String.valueOf(hollistercoMailList.mencheckingClearanceDiscount));
		doc.put("mencheckingSaleDiscount",
				String.valueOf(hollistercoMailList.mencheckingSaleDiscount));
		doc.put("mencheckingCategory", hollistercoMailList.mencheckingCategory);
		doc.put("mencheckingSize", hollistercoMailList.mencheckingSize);

		doc.put("womencheckingRegular", hollistercoMailList.womencheckingRegular);
		doc.put("womencheckingSecretSale", hollistercoMailList.womencheckingSecretSale);
		doc.put("womencheckingClearance", hollistercoMailList.womencheckingClearance);
		doc.put("womencheckingSale", hollistercoMailList.womencheckingSale);
		doc.put("womencheckingClearanceDiscount",
				String.valueOf(hollistercoMailList.womencheckingClearanceDiscount));
		doc.put("womencheckingSaleDiscount",
				String.valueOf(hollistercoMailList.womencheckingSaleDiscount));
		doc.put("womencheckingCategory", hollistercoMailList.womencheckingCategory);
		doc.put("womencheckingSize", hollistercoMailList.womencheckingSize);

		return doc;

	}

	public static void sync2DB() throws UnknownHostException {
		HollistercoMailListMongoDao dao = new HollistercoMailListMongoDao();
		List<HollistercoMailList> hollistercomailist = dao.findAll();
		for (int i = 0; i < hollistercomailist.size(); i++) {
			HollistercoMailList amail = hollistercomailist.get(i);
			amail.userType = "buyer";

			amail.mencheckingClearance = true;
			amail.mencheckingSale = true;
			amail.mencheckingRegular = true;
			amail.mencheckingSecretSale = true;
			amail.mencheckingClearanceDiscount = 0.6f;
			amail.mencheckingSaleDiscount = 0.6f;

			amail.womencheckingClearance = true;
			amail.womencheckingSale = true;
			amail.womencheckingRegular = true;
			amail.womencheckingSecretSale = true;
			amail.womencheckingClearanceDiscount = 0.6f;
			amail.womencheckingSaleDiscount = 0.6f;

			// if (amail.mailaddress.equals("xingang.hollisterco1@gmail.com")) {
			dao.update(amail);
			// System.out.println("update done.");
			System.out.println("update：" + amail);

			// HollistercoMailList amaildb = dao.find(amail.mailaddress);
			// if (amaildb == null) {
			// dao.save(amail);
			// System.out.println("insert：" + amail);
			//
			// } else {
			// dao.update(amail);
			// // System.out.println("update done.");
			// System.out.println("update：" + amail);
			//
			// }
			// }

		}

	}

	public static void copy2BackupDb() {
		try {
			HollistercoMailListMongoDao dao = new HollistercoMailListMongoDao();
			HollistercoMailListMongoDao backupdao = new HollistercoMailListMongoDao(true);

			List<HollistercoMailList> hollistercomailist = dao.findAll();
			for (int i = 0; i < hollistercomailist.size(); i++) {
				HollistercoMailList amail = hollistercomailist.get(i);
				HollistercoMailList backupMail = backupdao.find(amail.mailaddress);
				if (backupMail != null) {
					backupdao.update(amail);
				} else {
					backupdao.save(amail);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		// sync2DB();
		copy2BackupDb();
		System.out.println("done.");
	}

}

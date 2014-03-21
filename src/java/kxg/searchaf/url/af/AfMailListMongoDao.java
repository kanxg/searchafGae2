package kxg.searchaf.url.af;

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

public class AfMailListMongoDao {

	private DB db;
	private DBCollection coll;

	public AfMailListMongoDao() throws UnknownHostException {
 		db = MongoDao.getDao();
		coll = db.getCollection("afmaillist");
	}

	public AfMailListMongoDao(boolean usingBackupDb)
			throws UnknownHostException {
		if (usingBackupDb) {
 			db = MongoBackupDao.getDao();
			coll = db.getCollection("afmaillist");
		}
	}

	public WriteResult save(AfMailList afMailList) {
		return coll.insert(convert2DB(afMailList));
	}

	public void update(AfMailList afMailList) {
		BasicDBObject newDocument = convert2DB(afMailList);
		coll.update(new BasicDBObject().append("mailaddress",
				afMailList.mailaddress), newDocument);
	}

	public void delete(String mailaddress) {
		BasicDBObject document = new BasicDBObject();
		document.put("mailaddress", mailaddress);
		coll.remove(document);
	}

	public List<AfMailList> findAll() {
		List<AfMailList> maillist = new ArrayList<AfMailList>();
		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				AfMailList afMailList = parseAfMailList(object);
				maillist.add(afMailList);
			}
		} finally {
			cursor.close();
		}
		return maillist;
	}

	public AfMailList find(String mailaddress) {

		BasicDBObject query = new BasicDBObject("mailaddress", mailaddress);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				return parseAfMailList(object);
			}
		} finally {
			cursor.close();
		}
		return null;
	}

	private AfMailList parseAfMailList(BasicDBObject object) {
		AfMailList afMailList = new AfMailList();
		afMailList.mailaddress = (String) object.get("mailaddress");
		afMailList.userType = (String) object.get("userType");
		afMailList.valideTime = (Date) object.get("valideTime");
		afMailList.warningMen = (Boolean) object.get("warningMen");
		afMailList.warningWomen = (Boolean) object.get("warningWomen");
		if (object.get("warningCode") != null)
			afMailList.warningCode = (Boolean) object.get("warningCode");
		if (object.get("mencheckingRegular") != null)
			afMailList.mencheckingRegular = (Boolean) object
					.get("mencheckingRegular");
		if (object.get("mencheckingSecretSale") != null)
			afMailList.mencheckingSecretSale = (Boolean) object
					.get("mencheckingSecretSale");
		if (object.get("mencheckingClearance") != null)
			afMailList.mencheckingClearance = (Boolean) object
					.get("mencheckingClearance");
		if (object.get("mencheckingSale") != null)
			afMailList.mencheckingSale = (Boolean) object
					.get("mencheckingSale");
		if (object.get("mencheckingClearanceDiscount") != null)
			afMailList.mencheckingClearanceDiscount = Float
					.valueOf((String) object
							.get("mencheckingClearanceDiscount"));
		if (object.get("mencheckingSaleDiscount") != null)
			afMailList.mencheckingSaleDiscount = Float.valueOf((String) object
					.get("mencheckingSaleDiscount"));
		if (object.get("mencheckingCategory") != null) {
			// afMailList.checkingCategory = (ArrayList<String>) object
			// .get("checkingCategory");
			if (afMailList.mencheckingCategory == null)
				afMailList.mencheckingCategory = new ArrayList<String>();
			afMailList.mencheckingCategory.addAll((List<String>) object
					.get("mencheckingCategory"));
		}
		if (object.get("mencheckingSize") != null) {
			if (afMailList.mencheckingSize == null)
				afMailList.mencheckingSize = new ArrayList<String>();
			afMailList.mencheckingSize.addAll((List<String>) object
					.get("mencheckingSize"));
		}
		if (object.get("womencheckingRegular") != null)
			afMailList.womencheckingRegular = (Boolean) object
					.get("womencheckingRegular");
		if (object.get("womencheckingSecretSale") != null)
			afMailList.womencheckingSecretSale = (Boolean) object
					.get("womencheckingSecretSale");
		if (object.get("womencheckingClearance") != null)
			afMailList.womencheckingClearance = (Boolean) object
					.get("womencheckingClearance");
		if (object.get("womencheckingSale") != null)
			afMailList.womencheckingSale = (Boolean) object
					.get("womencheckingSale");
		if (object.get("womencheckingClearanceDiscount") != null)
			afMailList.womencheckingClearanceDiscount = Float
					.valueOf((String) object
							.get("womencheckingClearanceDiscount"));
		if (object.get("womencheckingSaleDiscount") != null)
			afMailList.womencheckingSaleDiscount = Float
					.valueOf((String) object.get("womencheckingSaleDiscount"));
		if (object.get("womencheckingCategory") != null) {
			// afMailList.checkingCategory = (ArrayList<String>) object
			// .get("checkingCategory");
			if (afMailList.womencheckingCategory == null)
				afMailList.womencheckingCategory = new ArrayList<String>();
			afMailList.womencheckingCategory.addAll((List<String>) object
					.get("womencheckingCategory"));
		}
		if (object.get("womencheckingSize") != null) {
			if (afMailList.womencheckingSize == null)
				afMailList.womencheckingSize = new ArrayList<String>();
			afMailList.womencheckingSize.addAll((List<String>) object
					.get("womencheckingSize"));
		}
		return afMailList;
	}

	public BasicDBObject convert2DB(AfMailList afMailList) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("mailaddress", afMailList.mailaddress);
		doc.put("userType", afMailList.userType);
		doc.put("valideTime", afMailList.valideTime);
		doc.put("warningMen", afMailList.warningMen);
		doc.put("warningCode", afMailList.warningCode);
		doc.put("warningWomen", afMailList.warningWomen);
		doc.put("mencheckingRegular", afMailList.mencheckingRegular);
		doc.put("mencheckingSecretSale", afMailList.mencheckingSecretSale);
		doc.put("mencheckingClearance", afMailList.mencheckingClearance);
		doc.put("mencheckingSale", afMailList.mencheckingSale);
		doc.put("mencheckingClearanceDiscount",
				String.valueOf(afMailList.mencheckingClearanceDiscount));
		doc.put("mencheckingSaleDiscount",
				String.valueOf(afMailList.mencheckingSaleDiscount));
		doc.put("mencheckingCategory", afMailList.mencheckingCategory);
		doc.put("mencheckingSize", afMailList.mencheckingSize);

		doc.put("womencheckingRegular", afMailList.womencheckingRegular);
		doc.put("womencheckingSecretSale", afMailList.womencheckingSecretSale);
		doc.put("womencheckingClearance", afMailList.womencheckingClearance);
		doc.put("womencheckingSale", afMailList.womencheckingSale);
		doc.put("womencheckingClearanceDiscount",
				String.valueOf(afMailList.womencheckingClearanceDiscount));
		doc.put("womencheckingSaleDiscount",
				String.valueOf(afMailList.womencheckingSaleDiscount));
		doc.put("womencheckingCategory", afMailList.womencheckingCategory);
		doc.put("womencheckingSize", afMailList.womencheckingSize);

		return doc;

	}

	public static void sync2DB() throws UnknownHostException {
		AfMailListMongoDao dao = new AfMailListMongoDao();
		List<AfMailList> afmailist = dao.findAll();
		for (int i = 0; i < afmailist.size(); i++) {
			AfMailList amail = afmailist.get(i);
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

			// if (amail.mailaddress.equals("xingang.af1@gmail.com")) {
			dao.update(amail);
			// System.out.println("update done.");
			System.out.println("update：" + amail);

			// AfMailList amaildb = dao.find(amail.mailaddress);
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
			AfMailListMongoDao dao = new AfMailListMongoDao();
			AfMailListMongoDao backupdao = new AfMailListMongoDao(true);

			List<AfMailList> afmailist = dao.findAll();
			for (int i = 0; i < afmailist.size(); i++) {
				AfMailList amail = afmailist.get(i);
				AfMailList backupMail = backupdao.find(amail.mailaddress);
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

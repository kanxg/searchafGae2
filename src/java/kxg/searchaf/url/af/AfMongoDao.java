package kxg.searchaf.url.af;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import kxg.searchaf.db.mongo.MongoDao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

public class AfMongoDao {

	private DB db;
	private DBCollection coll;

	public AfMongoDao() throws UnknownHostException {
		
		db = MongoDao.getDao();
		coll = db.getCollection("afproduct");
	}

	public void save(AfProduct product) {
		coll.insert(convert2DB(product));
		System.out.println("insert done.");
	}

	public void update(AfProduct product) {

	}

	public void delete(int productid) {
		BasicDBObject document = new BasicDBObject();
		document.put("productid", productid);
		coll.remove(document);
	}

	public AfProduct find(int productid) {

		BasicDBObject query = new BasicDBObject("productid", productid);

		DBCursor cursor = coll.find(query);
		try {
			if (cursor.hasNext()) {
				BasicDBObject object = (BasicDBObject) cursor.next();
				AfProduct product = new AfProduct();
				product.productid = (Integer) object.get("productid");
				product.name = (String) object.get("name");
				return product;
			}
		} finally {
			cursor.close();
		}

		return null;
	}

	public BasicDBObject convert2DB(AfProduct product) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("productid", product.productid);
		doc.put("name", product.name);
		doc.put("listprice", product.listprice);
		// doc.put("price", product.price);
		// doc.put("url", product.url);
		// doc.put("img_src", product.img_src);

		if (product.inventoryList != null && product.inventoryList.size() > 0) {
			List<BasicDBObject> inv_dbList = new ArrayList<BasicDBObject>();

			for (int i = 0; i < product.inventoryList.size(); i++) {
				Inventory inventory = product.inventoryList.get(i);
				BasicDBObject doc_inv = new BasicDBObject();
				doc_inv.put("name", inventory.name);
				doc_inv.put("seq", inventory.seq);
				doc_inv.put("itemSize", inventory.itemSize);
				doc_inv.put("itemSeq", inventory.itemSeq);
				// doc_inv.put("itemInventory", inventory.itemInventory);
				doc_inv.put("sku", inventory.sku);
				inv_dbList.add(doc_inv);
			}

			doc.put("inventory", inv_dbList);
		}
		return doc;

	}

	public static void main(String[] args) throws Exception {
		// AfConstant.usingProxy = true;
		// ProxyUtli.setProxy(AfConstant.usingProxy);
		//
		AfMongoDao dao = new AfMongoDao();
		// SearchAfJSP af = new SearchAfJSP();
		// List<AfProduct> list = af.getmendiscountproductforJsp("0.2",
		// "44506750@qq.com", "Shirt");
		// for (int i = 0; i < list.size(); i++) {
		// AfProduct product = list.get(i);
		// dao.save(product);
		// }

		AfProduct product = dao.find(1048129);

		System.out.println(product);
	}

}

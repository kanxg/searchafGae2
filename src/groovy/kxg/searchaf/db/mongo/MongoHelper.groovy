package kxg.searchaf.db.mongo

import com.mongodb.DB;
import com.mongodb.DBCollection;

class MongoHelper {

	static DB db;

	def get(def tablename,def classtype){
		db=MongoDao.getDao()
		DBCollection coll =db.getCollection(tablename);
	}
	public static void main(String[] args) {
	}
}

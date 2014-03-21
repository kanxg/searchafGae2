package kxg.searchaf.url.af;

import java.sql.*;
import java.util.List;

import kxg.searchaf.db.mysql.MysqlDao;
import kxg.searchaf.util.ProxyUtli;

public class AfMysqlDao extends MysqlDao {

	public void save(AfProduct product) {
		if (product == null || product.productid == 0)
			return;
		if (product.inventoryList == null || product.inventoryList.size() == 0) {
			return;
		}
		try {
			getConnection();
			conn.setAutoCommit(false);
			String sql = "INSERT INTO afproduct(productid, name, listprice, collection)"
					+ " VALUES (?,?,?,?)"; // 插入数据的sql语句

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, product.productid);
			ps.setString(2, product.name);
			ps.setFloat(3, product.listprice);
			ps.setString(4, product.data_collection);
			ps.executeUpdate();

			// insert inventory
			if (product.inventoryList != null
					&& product.inventoryList.size() > 0) {
				for (int i = 0; i < product.inventoryList.size(); i++) {
					Inventory inventory = product.inventoryList.get(i);
					sql = "INSERT INTO afproduct_inventory(name, seq,listprice,imgPrefix,longSku, itemSize,itemSeq,sku,cat,productid)"
							+ " VALUES (?,?,?,?,?,?,?,?,?,?)"; // 插入数据的sql语句
					ps = conn.prepareStatement(sql);
					ps.setString(1, inventory.name);
					ps.setString(2, inventory.seq);
					ps.setString(3, inventory.listprice);
					ps.setString(4, inventory.imgPrefix);
					ps.setString(5, inventory.longSku);
					ps.setString(6, inventory.itemSize);
					ps.setString(7, inventory.itemSeq);
					ps.setString(8, inventory.sku);
					ps.setString(9, inventory.cat);
					ps.setInt(10, product.productid);
					ps.executeUpdate();
				}

			}
			conn.commit();
			System.out.println("插入数据");
			ps.close();
			conn.close(); // 关闭数据库连接
		} catch (SQLException e) {
			System.out.println("插入数据失败" + e.getMessage());
			try {
				conn.rollback();
			} catch (Exception e1) {

			}
		}
	}

	public void update(AfProduct product) {
		try {
			getConnection();
			conn.setAutoCommit(false);
			boolean prodindb = false;
			String sql = "select *  afproduct where productid=?"; // 插入数据的sql语句

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				prodindb = true;
			}
			rs.close();

			if (prodindb) {
				save(product);
				return;
			}

			sql = "update afproduct(productid, name, listprice, collection)"
					+ " VALUES (?,?,?,?)"; // 插入数据的sql语句

			ps = conn.prepareStatement(sql);
			ps.setInt(1, product.productid);
			ps.setString(2, product.name);
			ps.setFloat(3, product.listprice);
			ps.setString(4, product.data_collection);
			ps.executeUpdate();

			// insert inventory
			if (product.inventoryList != null
					&& product.inventoryList.size() > 0) {
				for (int i = 0; i < product.inventoryList.size(); i++) {
					Inventory inventory = product.inventoryList.get(i);
					sql = "INSERT INTO afproduct_inventory(name, seq,listprice,imgPrefix,longSku, itemSize,itemSeq,sku,cat,productid)"
							+ " VALUES (?,?,?,?,?,?,?,?,?,?)"; // 插入数据的sql语句
					ps = conn.prepareStatement(sql);
					ps.setString(1, inventory.name);
					ps.setString(2, inventory.seq);
					ps.setString(3, inventory.listprice);
					ps.setString(4, inventory.imgPrefix);
					ps.setString(5, inventory.longSku);
					ps.setString(6, inventory.itemSize);
					ps.setString(7, inventory.itemSeq);
					ps.setString(8, inventory.sku);
					ps.setString(9, inventory.cat);
					ps.setInt(10, product.productid);
					ps.executeUpdate();
					// System.out.println("插入数据");
				}

			}
			conn.commit();
			ps.close();
			conn.close(); // 关闭数据库连接
		} catch (SQLException e) {
			System.out.println("插入数据失败" + e.getMessage());
			try {
				conn.rollback();
			} catch (Exception e1) {

			}
		}
	}

	public void delete(AfProduct product) {
		getConnection();
	}

	public AfProduct find(AfProduct product) {
		try {
			getConnection();
			String sql = "select *  afproduct where productid=?"; // 插入数据的sql语句

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return null;
			}

			// insert inventory
			if (product.inventoryList != null
					&& product.inventoryList.size() > 0) {
				for (int i = 0; i < product.inventoryList.size(); i++) {
					Inventory inventory = product.inventoryList.get(i);
					sql = "INSERT INTO afproduct_inventory(name, seq,listprice,imgPrefix,longSku, itemSize,itemSeq,sku,cat,productid)"
							+ " VALUES (?,?,?,?,?,?,?,?,?,?)"; // 插入数据的sql语句
					ps = conn.prepareStatement(sql);
					ps.setString(1, inventory.name);
					ps.setString(2, inventory.seq);
					ps.setString(3, inventory.listprice);
					ps.setString(4, inventory.imgPrefix);
					ps.setString(5, inventory.longSku);
					ps.setString(6, inventory.itemSize);
					ps.setString(7, inventory.itemSeq);
					ps.setString(8, inventory.sku);
					ps.setString(9, inventory.cat);
					ps.setInt(10, product.productid);
					ps.executeUpdate();
					// System.out.println("插入数据");
				}

			}
			conn.commit();
			ps.close();
			conn.close(); // 关闭数据库连接
		} catch (SQLException e) {
			System.out.println("插入数据失败" + e.getMessage());
			try {
				conn.rollback();
			} catch (Exception e1) {

			}
		}
		return null;
	}

	public boolean existIndb(int productid) {
		boolean returnkey = false;
		try {
			getConnection();
			String sql = "select * from afproduct where productid=?"; // 插入数据的sql语句

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, productid);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				returnkey = true;
			}

			ps.close();
			conn.close(); // 关闭数据库连接
		} catch (SQLException e) {
			System.out.println("插入数据失败" + e.getMessage());
			try {
				conn.rollback();
			} catch (Exception e1) {

			}
		}
		return returnkey;
	}

	public static void main(String[] args) throws Exception {
 		ProxyUtli.setProxy(true);

		//
		AfMysqlDao dao = new AfMysqlDao();
		// SearchAfJSP af = new SearchAfJSP();
		// List<AfProduct> list = af
		// .getcustomerdiscountproductforJsp(
		// "http://www.abercrombie.com/webapp/wcs/stores/servlet/CategoryDisplay?parentCategoryId=13050&catalogId=10901&categoryId=78566&langId=-1&storeId=10051&topCategoryId=12202",
		// "1", "", "");
		// for (int i = 0; i < list.size(); i++) {
		// AfProduct product = list.get(i);
		// ParserAfProduct ParserAfProduct = new ParserAfProduct(product);
		// ParserAfProduct.checkColorItemInventory(false);
		// dao.save(product);
		// }

		// AfProduct product = dao.find(1048129);

		System.out.println(dao.existIndb(11226461));

	}
}

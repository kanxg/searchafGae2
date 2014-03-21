package kxg.searchaf.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlDao {

	public Connection conn = null;

	public void getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载mysq驱动
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/searchaf", "root", "");// 创建数据连接

		} catch (Exception e) {
			e.printStackTrace();// 打印出错详细信息
		}
	}

	public void getConnection(String dbname) {

		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载mysq驱动
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
					+ dbname, "root", "");// 创建数据连接

		} catch (Exception e) {
			e.printStackTrace();// 打印出错详细信息
		}
	}

	public static void main(String[] args) throws Exception {

		MysqlDao dao = new MysqlDao();
		dao.getConnection();
	}

}

package kxg.searchaf.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AmazonMysqlDao {

	public Connection conn = null;

	public void getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载mysq驱动
			conn = DriverManager
					.getConnection(
							"jdbc:mysql://searchaf.clntr9xm1dcd.us-east-1.rds.amazonaws.com:3306/searchaf",
							"searchaf", "searchaf");// 创建数据连接

		} catch (Exception e) {
			e.printStackTrace();// 打印出错详细信息
		}
	}

	public void getConnection(String dbname) {

		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载mysq驱动
			conn = DriverManager.getConnection(
					"jdbc:mysql://searchaf.clntr9xm1dcd.us-east-1.rds.amazonaws.com:3306/"
							+ dbname, "searchaf", "searchaf");// 创建数据连接

		} catch (Exception e) {
			e.printStackTrace();// 打印出错详细信息
		}
	}

	public static void main(String[] args) throws Exception {

		AmazonMysqlDao dao = new AmazonMysqlDao();
		dao.getConnection();
	}

}

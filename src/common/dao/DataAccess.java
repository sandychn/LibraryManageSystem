package common.dao;

import common.util.MyXMLReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public abstract class DataAccess {

	// the following members will be initialized in class static block
	private static final String dbAddress;
	private static final String dbName;
	private static final String dbUsername;
	private static final String dbPassword;

	// static member so all the DataAccess object shared this connection
	private static Connection connection = null;

	static {
		MyXMLReader reader = new MyXMLReader();
		Map<String, String> map = reader.readXML();
		dbAddress = map.get("address");
		dbName = map.get("dbname");
		dbUsername = map.get("dbusername");
		dbPassword = map.get("dbpassword");
	}

	// 数据库是否已连接
	public boolean isConnected() {
		try {
			if (connection == null || connection.isClosed())
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	// 连接数据库
	public void connectDatabase() throws SQLException {
		String connectionString = "jdbc:mysql://" + dbAddress + "/" + dbName;
		System.out.println(connectionString);
		DriverManager.setLoginTimeout(5);
		long start = System.currentTimeMillis();
		connection = DriverManager.getConnection(connectionString, dbUsername, dbPassword);
		System.out.println("connection got in " + (System.currentTimeMillis() - start) + " ms");
	}

	// 断开数据库
	public void disconnectDatabase() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}

	protected ResultSet executeQuery(String sql) throws SQLException {
		return connection.createStatement().executeQuery(sql);
	}

	protected int executeUpdate(String sql) throws SQLException {
		return connection.createStatement().executeUpdate(sql);
	}

}

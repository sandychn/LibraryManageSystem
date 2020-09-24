package manager.bookclass.addition.dao;

import java.sql.SQLException;

import common.dao.CommonDataAccess;

public class MgrAddBookClassDataAccess extends CommonDataAccess {

	public void addBookClass(String className) throws SQLException {
		int cnt = getBookClassCount();
		String sql = "insert into book_class values(\'" + (cnt + 1) + "\',\'" + className + "\')";
		executeUpdate(sql);
	}

}

package manager.bookclass.update.dao;

import java.sql.SQLException;

import common.dao.CommonDataAccess;

public class MgrUpdateBookClassDataAccess extends CommonDataAccess {

	public void updateBookClassName(int classId, String className) throws SQLException {
		String sql = "update book_class set bClassName=\'" + className + "\' where bClassId = \'" + classId + "\' ";
		executeUpdate(sql);
	}

}

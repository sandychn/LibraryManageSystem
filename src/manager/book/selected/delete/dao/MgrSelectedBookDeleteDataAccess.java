package manager.book.selected.delete.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import common.dao.CommonDataAccess;
import entity.Book;

public class MgrSelectedBookDeleteDataAccess extends CommonDataAccess {

	public void deleteBook(Book book) throws SQLException {
		String sql = "delete from book where bISBN = \'" + book.getIsbn() + "\' ";
		executeUpdate(sql);
	}

	public boolean isBookHasLendingRecord(Book book) throws SQLException {
		String sql = "select COUNT(*) cnt from book_lending where bISBN=\'" + book.getIsbn() + "\'";
		ResultSet resultSet = executeQuery(sql);
		resultSet.next();
		return resultSet.getInt("cnt") > 0;
	}

}

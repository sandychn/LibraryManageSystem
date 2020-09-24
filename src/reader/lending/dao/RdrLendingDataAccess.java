package reader.lending.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import common.constant.BookConstant;
import common.dao.DataAccess;
import entity.Book;
import entity.User;

public class RdrLendingDataAccess extends DataAccess {

	public boolean isBookNotReturnYetByUser(User user, Book book) throws SQLException {
		String sql = "select bISBN from book_lending where uName=\'" + user.getUsername() + "\' and bISBN=\'"
				+ book.getIsbn() + "\' and brTime is null";
		return executeQuery(sql).next() == true;
	}

	public int getBookCount(Book book) throws SQLException {
	    String sql = "select bCount from book where bISBN=\'" + book.getIsbn() + "\'";
	    ResultSet res = executeQuery(sql);
	    res.next();
	    return res.getInt("bCount");
	}
	
	public void lendBookByUser(User user, Book book) throws SQLException {
	    
		String sql = "update book set bCount=bCount-1 where bISBN=\'" + book.getIsbn() + "\'";
		executeUpdate(sql);
		sql = "insert into book_lending values(\'" + book.getIsbn() + "\',\'" + user.getUsername() + "\',getdate(),"
				+ BookConstant.DEFAULT_BLDUE + ",null)";
		executeUpdate(sql);
	}

}

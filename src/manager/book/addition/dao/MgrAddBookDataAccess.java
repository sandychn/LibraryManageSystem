package manager.book.addition.dao;

import common.dao.CommonDataAccess;
import entity.Book;

import java.sql.SQLException;

public class MgrAddBookDataAccess extends CommonDataAccess {

    public void addBook(Book book) throws SQLException {
        String sql = "insert into book values(\'" + book.getIsbn() + "\',\'" + book.getName() + "\',\'"
                + book.getAuthor() + "\',\'" + book.getPress() + "\',\'" + book.getClassId() + "\',\'" + book.getCount()
                + "\',\'" + book.getSum() + "\')";
        executeUpdate(sql);
    }

}

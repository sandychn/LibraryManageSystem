package manager.book.selected.update.dao;

import common.dao.CommonDataAccess;
import entity.Book;

import java.sql.SQLException;

public class MgrSelectedBookUpdateDataAccess extends CommonDataAccess {

    public void updateBook(Book book) throws SQLException {
        String sql = "update book set bName=\'" + book.getName() + "\',bAuthor=\'" + book.getAuthor() + "\',bPress=\'"
                + book.getPress() + "\',bClassId=\'" + book.getClassId() + "\',bCount=\'" + book.getCount()
                + "\',bSum=\'" + book.getSum() + "\' where bISBN = \'" + book.getIsbn() + "\' ";
        executeUpdate(sql);
    }

}

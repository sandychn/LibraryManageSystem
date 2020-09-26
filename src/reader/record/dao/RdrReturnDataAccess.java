package reader.record.dao;

import common.dao.CommonDataAccess;
import entity.BookLendingRecord;
import entity.User;

import java.sql.SQLException;

public class RdrReturnDataAccess extends CommonDataAccess {

    public void returnBookByBookLendingRecordAndUser(BookLendingRecord bookLendingRecord, User user)
            throws SQLException {
        String sql = "update book_lending set brTime=now() where uName=\'" + user.getUsername() + "\' and bISBN=\'"
                + bookLendingRecord.getbISBN() + "\'";
        executeUpdate(sql);
        sql = "update book set bcount=bcount+1 where bISBN=\'" + bookLendingRecord.getbISBN() + "\'";
        executeUpdate(sql);
    }
}

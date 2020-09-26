package reader.record.dao;

import common.constant.BookConstant;
import common.dao.CommonDataAccess;
import entity.BookLendingRecord;
import entity.User;

import java.sql.SQLException;

public class RdrRenewDataAccess extends CommonDataAccess {

    public void renewBookByBookLendingRecordAndUser(BookLendingRecord bookLendingRecord, User user)
            throws SQLException {
        String sql = "update book_lending set bldue=" + BookConstant.RENEW_BLDUE + " where uName=\'"
                + user.getUsername() + "\' and bISBN=\'" + bookLendingRecord.getbISBN() + "\'";
        executeUpdate(sql);
    }

}

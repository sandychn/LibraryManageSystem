package reader.record.dao;

import common.constant.BookConstant;
import common.dao.DataAccess;
import entity.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class RdrRecordDataAccess extends DataAccess {

    public Vector<Vector<String>> getBookLendingRecordVectorByUser(User user) throws SQLException {
        String sql = "select book.bISBN, book.bName, book.bAuthor, blTime, brTime, blDue from book, book_lending "
                + "where book.bISBN=book_lending.bISBN and uName=\'" + user.getUsername() + "\' and brTime is null "
                + "order by blTime desc"; // 未还
        ResultSet resultSet = executeQuery(sql);
        Vector<Vector<String>> data = new Vector<Vector<String>>();
        while (resultSet.next()) {
            Vector<String> row = new Vector<String>();
            row.add(resultSet.getString("bISBN"));
            row.add(resultSet.getString("bName"));
            row.add(resultSet.getString("bAuthor"));
            row.add(resultSet.getDate("blTime").toString());
            Date brTimeDate = resultSet.getDate("brTime");
            row.add(brTimeDate == null ? "尚未归还" : brTimeDate.toString());
            int blDue = resultSet.getInt("blDue");
            row.add(String.valueOf(blDue));
            row.add(blDue > BookConstant.DEFAULT_BLDUE ? "是" : "否");
            data.add(row);
        }
        sql = "select book.bISBN, book.bName, book.bAuthor, blTime, brTime, blDue from book, book_lending "
                + "where book.bISBN=book_lending.bISBN and uName=\'" + user.getUsername() + "\' and brTime is not null "
                + "order by blTime desc"; // 已还
        resultSet = executeQuery(sql);
        while (resultSet.next()) {
            Vector<String> row = new Vector<String>();
            row.add(resultSet.getString("bISBN"));
            row.add(resultSet.getString("bName"));
            row.add(resultSet.getString("bAuthor"));
            row.add(resultSet.getDate("blTime").toString());
            Date brTimeDate = resultSet.getDate("brTime");
            row.add(brTimeDate == null ? "尚未归还" : brTimeDate.toString());
            int blDue = resultSet.getInt("blDue");
            row.add(String.valueOf(blDue));
            row.add(blDue > BookConstant.DEFAULT_BLDUE ? "是" : "否");
            data.add(row);
        }
        return data;
    }

}

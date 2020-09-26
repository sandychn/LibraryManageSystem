package manager.record.dao;

import common.constant.BookConstant;
import common.dao.DataAccess;
import entity.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class MgrRecordDataAccess extends DataAccess {
    public Vector<Vector<String>> getBookTableLendVectorTwo(String bISBN) throws SQLException {
        String sql = "select a.bISBN,a.uName,a.blTime,a.blDue,a.brTime,b.bName from book_lending a, book b where a.bISBN=b.bISBN and a.bISBN='" + bISBN + "'";
        //	System.out.println(sql);
        ResultSet resultSet = executeQuery(sql);
        Vector<Vector<String>> data = new Vector<Vector<String>>();
        while (resultSet.next()) {
            Vector<String> row = new Vector<String>();
            row.add(resultSet.getString("bName"));
            row.add(resultSet.getString("bISBN"));
            row.add(resultSet.getString("uName"));
            row.add(resultSet.getString("blTime"));
            row.add(resultSet.getString("blDue"));
            row.add(resultSet.getString("brTime"));
            data.add(row);
        }
        System.out.println(data);
        return data;
    }
}


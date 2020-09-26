package manager.record.service;

import entity.User;
import manager.record.dao.MgrRecordDataAccess;
import reader.record.dao.RdrRecordDataAccess;

import java.sql.SQLException;
import java.util.Vector;

public class MgrRecordService {
    private MgrRecordDataAccess dao;

    public MgrRecordService() {
        dao = new MgrRecordDataAccess();
    }

    public Vector<Vector<String>> getTableVectorTwo(String bISBN) throws SQLException {
        return dao.getBookTableLendVectorTwo(bISBN);
    }
}

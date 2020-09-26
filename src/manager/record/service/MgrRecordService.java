package manager.record.service;

import manager.record.dao.MgrRecordDataAccess;

import java.sql.SQLException;
import java.util.Vector;

public class MgrRecordService {
    private MgrRecordDataAccess dao;

    public MgrRecordService() {
        dao = new MgrRecordDataAccess();
    }

    public Vector<Vector<String>> getLendingRecordVector(String bISBN) throws SQLException {
        return dao.getLendingRecordTableVector(bISBN);
    }
}

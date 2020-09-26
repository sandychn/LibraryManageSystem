package reader.record.service;

import entity.User;
import reader.record.dao.RdrRecordDataAccess;

import java.sql.SQLException;
import java.util.Vector;

public class RdrRecordService {

    private RdrRecordDataAccess dao;

    public RdrRecordService() {
        dao = new RdrRecordDataAccess();
    }

    public Vector<Vector<String>> getTableVector(User user) throws SQLException {
        return dao.getBookLendingRecordVectorByUser(user);
    }

}

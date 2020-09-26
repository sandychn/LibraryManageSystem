package common.service;

import common.dao.CommonDataAccess;

import java.sql.SQLException;
import java.util.Vector;

public class SearchPanelService {

    private CommonDataAccess dao;
    private String[] bookClassList;

    public SearchPanelService() {
        dao = new CommonDataAccess();
        bookClassList = null;
    }

    // 取图书分类表
    public String[] getBookClassList() {
        return bookClassList;
    }

    // 从数据库中读取图书分类表
    public void setBookClassList() throws SQLException {
        bookClassList = dao.getBookClassList();
    }

    // 以所给条件返回满足条件的表
    public Vector<Vector<String>> getTableVector(String bName, String bISBN, String bAuthor, String bPress,
                                                 int bClassId) throws SQLException {
        return dao.getBookTableVector(bName, bISBN, bAuthor, bPress, bClassId);
    }
}

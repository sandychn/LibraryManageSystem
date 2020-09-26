package manager.bookclass.addition.service;

import common.constant.BookConstant;
import manager.bookclass.addition.dao.MgrAddBookClassDataAccess;

import javax.swing.*;
import java.sql.SQLException;

public class MgrAddBookClassService {// 添加图书分类的操作

    private MgrAddBookClassDataAccess dao = null;

    public MgrAddBookClassService() {
        dao = new MgrAddBookClassDataAccess();
    }

    public void bookAddition(String className) throws MgrAddBookClassException {
        try {// 向数据库中添加图书类名
            dao.addBookClass(className);
        } catch (SQLException sqle) {
            throw new MgrAddBookClassException("数据库出错。错误信息:\n" + sqle.getMessage());
        }
    }

    public int getClassCount() throws MgrAddBookClassException {
        try {// 从数据库中读取类名数量
            int bookClassCount = dao.getBookClassCount();
            return bookClassCount;
        } catch (SQLException sqle) {
            throw new MgrAddBookClassException("数据库出错。错误信息:\n" + sqle.getMessage());
        }
    }

    public boolean isTrue(String className) {// 判断是否进行添加图书分类操作
        if (className.length() == 0) {
            JOptionPane.showMessageDialog(null, "请输入图书分类名", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            if (dao.isBookClassNameExists(className)) {
                JOptionPane.showMessageDialog(null, "该图书类已存在", "错误", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "连接数据库失败", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (className.length() > BookConstant.CLASS_MAXLEN) {
            JOptionPane.showMessageDialog(null, "图书类名超出限制（最多" + BookConstant.CLASS_MAXLEN + "字符）", "错误",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (JOptionPane.showOptionDialog(null, "确认添加该图书分类吗?", "确认", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null) != JOptionPane.YES_OPTION) {
            return false;
        }
        return true;
    }

}

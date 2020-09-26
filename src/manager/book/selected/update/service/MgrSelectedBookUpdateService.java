package manager.book.selected.update.service;

import common.constant.BookConstant;
import entity.Book;
import manager.book.selected.update.dao.MgrSelectedBookUpdateDataAccess;

import javax.swing.*;
import java.sql.SQLException;

public class MgrSelectedBookUpdateService {// 修改图书信息操作

    private MgrSelectedBookUpdateDataAccess dao = null;

    public MgrSelectedBookUpdateService() {
        dao = new MgrSelectedBookUpdateDataAccess();
    }

    public boolean bookUpdating(Book book) throws MgrSelectedBookUpdateException {
        try {// 向数据库中修改图书信息
            dao.updateBook(book);
            return true;
        } catch (SQLException sqle) {
            throw new MgrSelectedBookUpdateException("数据库出错。错误信息:\n" + sqle.getMessage());
        }
    }

    public String[] getBookClass() throws MgrSelectedBookUpdateException {
        try {// 从数据库中读取类名加入下拉列表中
            String[] bookClassList = dao.getBookClassList();
            return bookClassList;
        } catch (SQLException sqle) {
            throw new MgrSelectedBookUpdateException("数据库出错。错误信息:\n" + sqle.getMessage());
        }
    }

    public boolean isTrue(Book windowBook, Book book) {// 判断是否修改图书信息
        if (windowBook.getName().length() == 0 || windowBook.getAuthor().length() == 0
                || windowBook.getPress().length() == 0) {
            JOptionPane.showMessageDialog(null, "请输入完整图书信息", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (windowBook.getName().length() > BookConstant.NAME_MAXLEN) {
            JOptionPane.showMessageDialog(null, "图书名字超出限制（最多" + BookConstant.NAME_MAXLEN + "字符）", "错误",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (windowBook.getPress().length() > BookConstant.PRESS_MAXLEN) {
            JOptionPane.showMessageDialog(null, "图书出版社超出限制（最多" + BookConstant.PRESS_MAXLEN + "字符）", "错误",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (windowBook.getAuthor().length() > BookConstant.AUTHOR_MAXLEN) {
            JOptionPane.showMessageDialog(null, "图书作者名超出限制（最多" + BookConstant.AUTHOR_MAXLEN + "字符）", "错误",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (windowBook.getSum() == -1) {
            return false;
        }
        if (book.getCount() != book.getSum()) {
            JOptionPane.showMessageDialog(null, "该书籍不可修改（此书已有借出）", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (windowBook.getSum() < 0) {
            JOptionPane.showMessageDialog(null, "图书总数量不为负", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (JOptionPane.showOptionDialog(null, "确认修改该书籍吗?", "确认", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null) != JOptionPane.YES_OPTION) {
            return false;
        }
        return true;
    }
}

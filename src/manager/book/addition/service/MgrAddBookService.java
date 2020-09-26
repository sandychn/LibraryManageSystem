package manager.book.addition.service;

import common.constant.BookConstant;
import entity.Book;
import manager.book.addition.dao.MgrAddBookDataAccess;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Vector;

public class MgrAddBookService { // 添加书本操作

    private MgrAddBookDataAccess dao = null;

    public MgrAddBookService() {
        dao = new MgrAddBookDataAccess();// 连接数据库
    }

    public boolean bookAddition(Book book) throws MgrAddBookException { // 向数据库中添加图书
        try {
            if (dao.getBookByBookISBN(book.getIsbn()) == null) {
                dao.addBook(book);
                return true;
            } else {
                throw new MgrAddBookException("该书籍已存在");
            }
        } catch (SQLException sqle) {
            throw new MgrAddBookException("数据库出错。错误信息:\n" + sqle.getMessage());
        }
    }

    public String[] getBookClass() throws MgrAddBookException { // 从数据库中读取类名加入下拉列表中
        Vector<String> bookClassVector = new Vector<String>();
        try {
            String[] bookClassList = dao.getBookClassList();
            for (String str : bookClassList) {
                bookClassVector.add(str);
            }
            return bookClassList;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new MgrAddBookException("数据库出错。错误信息:\n" + sqle.getMessage());
        }

    }

    public boolean isTrue(Book windowBook) {// 判断是否可向数据库添加图书

        if (windowBook.getIsbn().length() == 0 || windowBook.getName().length() == 0
                || windowBook.getAuthor().length() == 0 || windowBook.getPress().length() == 0) {
            JOptionPane.showMessageDialog(null, "请输入完整图书信息", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (windowBook.getIsbn().length() != BookConstant.ISBN_LEN) {
            JOptionPane.showMessageDialog(null, "请输入正确的图书编号", "错误", JOptionPane.ERROR_MESSAGE);
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
        if (windowBook.getClassId() == 0) {
            JOptionPane.showMessageDialog(null, "请选择图书分类", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (JOptionPane.showOptionDialog(null, "确认添加该书籍吗?", "确认", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null) != JOptionPane.YES_OPTION) {
            return false;
        }
        return true;
    }
}

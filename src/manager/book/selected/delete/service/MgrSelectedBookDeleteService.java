package manager.book.selected.delete.service;

import common.util.SearchPanel;
import entity.Book;
import manager.book.selected.delete.dao.MgrSelectedBookDeleteDataAccess;
import manager.book.selected.update.service.MgrSelectedBookUpdateException;

import javax.swing.*;
import java.sql.SQLException;

public class MgrSelectedBookDeleteService {// 删除图书操作
    MgrSelectedBookDeleteDataAccess dao;

    public MgrSelectedBookDeleteService() {
        dao = new MgrSelectedBookDeleteDataAccess();
    }

    public boolean bookDeleting(SearchPanel searchPanel) throws MgrSelectedBookUpdateException {
        Book book = searchPanel.getSelectedBook();
        try {// 向数据库中删除图书信息
            if (dao.isBookHasLendingRecord(book)) {
                JOptionPane.showMessageDialog(null, "此书已有借出记录，该书籍不可删除", "错误", JOptionPane.ERROR_MESSAGE);
                return false;
            } else if (JOptionPane.showOptionDialog(null, "确认删除该书籍吗?", "确认", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null) != JOptionPane.YES_OPTION) {
                return false;
            } else {
                dao.deleteBook(book);
                return true;
            }

        } catch (SQLException sqle) {
            throw new MgrSelectedBookUpdateException("数据库出错。错误信息:\n" + sqle.getMessage());
        }
    }
}

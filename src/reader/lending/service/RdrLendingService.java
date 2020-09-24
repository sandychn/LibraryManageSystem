package reader.lending.service;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import common.util.CurrentUser;
import entity.Book;
import reader.lending.dao.RdrLendingDataAccess;

public class RdrLendingService {

    private RdrLendingDataAccess dao;

    public RdrLendingService() {
        dao = new RdrLendingDataAccess();
    }

    public boolean tryLend(Book book) throws RdrLendingException, SQLException {
        // 是否已借过一本该书籍并且还没还
        if (dao.isBookNotReturnYetByUser(CurrentUser.getCurrentUser(), book)) {
            throw new RdrLendingException("您已借过一本该书籍了，请先归还");
        }
        
        // 弹窗确认
        if (JOptionPane.showOptionDialog(null,
                "您选择的书籍信息:\n\n书名: " + book.getName() + "\nISBN: " + book.getIsbn() + "\n\n确认借阅该书籍吗?", "确认",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null) != JOptionPane.YES_OPTION) {
            return false;
        }
        
        // 重新访问数据库检查该书是否有剩余
        if (dao.getBookCount(book) < 1) {
            throw new RdrLendingException("该书籍已无剩余书本可借出");
        }
        
        dao.lendBookByUser(CurrentUser.getCurrentUser(), book);
        return true;
    }

}

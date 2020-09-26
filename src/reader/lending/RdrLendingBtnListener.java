package reader.lending;

import common.util.SearchPanel;
import reader.lending.service.RdrLendingException;
import reader.lending.service.RdrLendingService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RdrLendingBtnListener implements ActionListener {

    private SearchPanel searchPanel;
    private RdrLendingService lendingOperation;

    public RdrLendingBtnListener(SearchPanel searchPanel, RdrLendingService lendingOperation) {
        this.searchPanel = searchPanel;
        this.lendingOperation = lendingOperation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!searchPanel.isSelected()) {
            JOptionPane.showMessageDialog(null, "请在列表中选择您想要借阅的书籍", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            if (lendingOperation.tryLend(searchPanel.getSelectedBook())) {
                searchPanel.setTable();
                JOptionPane.showMessageDialog(null, "借阅成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (RdrLendingException exp) {
            JOptionPane.showMessageDialog(null, exp.getMessage(), "借阅图书失败", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            JOptionPane.showMessageDialog(null, "获取信息时出现错误。请联系管理员", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

}

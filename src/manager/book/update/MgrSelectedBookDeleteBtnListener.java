package manager.book.update;

import common.dao.CommonDataAccess;
import common.util.SearchPanel;
import manager.book.selected.delete.service.MgrSelectedBookDeleteService;
import manager.book.selected.update.service.MgrSelectedBookUpdateException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MgrSelectedBookDeleteBtnListener implements ActionListener {// 删除图书按钮事件

    CommonDataAccess connect = new CommonDataAccess();
    private MgrSelectedBookDeleteService bookDeleteoper;
    private SearchPanel searchPanel;

    public MgrSelectedBookDeleteBtnListener(SearchPanel searchPanel, MgrSelectedBookDeleteService bookDeleteoper) {
        this.searchPanel = searchPanel;
        this.bookDeleteoper = bookDeleteoper;
    }

    public void actionPerformed(ActionEvent e) {
        if (!searchPanel.isSelected()) {
            JOptionPane.showMessageDialog(null, "请选择要删除的书籍", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            if (bookDeleteoper.bookDeleting(searchPanel)) {
                JOptionPane.showMessageDialog(null, "成功删除所选书籍", "成功", JOptionPane.INFORMATION_MESSAGE);
                searchPanel.setTable();
            } else {
                JOptionPane.showMessageDialog(null, "删除所选书籍失败", "失败", JOptionPane.ERROR_MESSAGE);
            }
        } catch (MgrSelectedBookUpdateException exp) {
            JOptionPane.showMessageDialog(null, exp.getMessage(), "删除图书失败", JOptionPane.ERROR_MESSAGE);
        }
    }
}

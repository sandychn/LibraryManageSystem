package manager.book.update;

import common.util.SearchPanel;
import manager.book.selected.update.MgrSelectedBookUpdateWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MgrUpdateBookBtnListener implements ActionListener {// 修改图书事件

    private SearchPanel searchPanel;
    private MgrUpdateBookWindow updatingWindow;

    public MgrUpdateBookBtnListener(SearchPanel searchPanel, MgrUpdateBookWindow updatingWindow) {
        this.searchPanel = searchPanel;
        this.updatingWindow = updatingWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!searchPanel.isSelected()) {
            JOptionPane.showMessageDialog(null, "请选择要修改的书籍", "提示", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        @SuppressWarnings("unused")
        MgrSelectedBookUpdateWindow bookupdating = new MgrSelectedBookUpdateWindow(updatingWindow,
                searchPanel.getSelectedBook());// 修改图书信息窗口
    }

}

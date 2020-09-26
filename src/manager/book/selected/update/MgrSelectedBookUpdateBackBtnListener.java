package manager.book.selected.update;

import manager.book.update.MgrUpdateBookWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MgrSelectedBookUpdateBackBtnListener implements ActionListener {// 修改图书窗口返回选择图书窗口事件

    private MgrUpdateBookWindow updatingWindow;
    private MgrSelectedBookUpdateWindow bookUpdatingWindow;

    public MgrSelectedBookUpdateBackBtnListener(MgrSelectedBookUpdateWindow bookUpdatingWindow,
                                                MgrUpdateBookWindow updatingWindow) {
        this.updatingWindow = updatingWindow;
        this.bookUpdatingWindow = bookUpdatingWindow;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        updatingWindow.setTable();
        updatingWindow.setLocationRelativeTo(null);
        updatingWindow.setVisible(true);
        bookUpdatingWindow.dispose();
    }

}

package manager.book.update;

import manager.menu.WarehouseMgrMenuWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MgrUpdateBookBackBtnListener implements ActionListener {// 修改图书的返回目录的按钮事件

    private MgrUpdateBookWindow updatingWindow;
    private WarehouseMgrMenuWindow manMenuWindow;

    public MgrUpdateBookBackBtnListener(MgrUpdateBookWindow updatingWindow, WarehouseMgrMenuWindow manMenuWindow) {
        this.updatingWindow = updatingWindow;
        this.manMenuWindow = manMenuWindow;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        manMenuWindow.setLocationRelativeTo(null);
        manMenuWindow.setVisible(true);
        updatingWindow.dispose();
    }

}

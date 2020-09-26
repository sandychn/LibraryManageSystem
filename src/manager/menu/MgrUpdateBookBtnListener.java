package manager.menu;

import manager.book.update.MgrUpdateBookWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MgrUpdateBookBtnListener implements ActionListener {// 目录修改/删除图书按钮事件
    private WarehouseMgrMenuWindow manMenuWindow;

    public MgrUpdateBookBtnListener(WarehouseMgrMenuWindow manMenuWindow) {
        this.manMenuWindow = manMenuWindow;
    }

    public void actionPerformed(ActionEvent e) {
        @SuppressWarnings("unused")
        MgrUpdateBookWindow manmenu = new MgrUpdateBookWindow(manMenuWindow);
        manMenuWindow.setVisible(false);
    }
}
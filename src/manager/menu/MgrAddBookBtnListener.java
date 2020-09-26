package manager.menu;

import manager.book.addition.MgrAddBookWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MgrAddBookBtnListener implements ActionListener {// 目录添加图书按钮事件
    private WarehouseMgrMenuWindow manMenuWindow;

    public MgrAddBookBtnListener(WarehouseMgrMenuWindow manMenuWindow) {
        this.manMenuWindow = manMenuWindow;
    }

    public void actionPerformed(ActionEvent e) {
        @SuppressWarnings("unused")
        MgrAddBookWindow manmenu = new MgrAddBookWindow(manMenuWindow);// 添加图书窗口
        manMenuWindow.setVisible(false);
    }
}

package manager.book.addition;

import manager.menu.WarehouseMgrMenuWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MgrAddBookBackBtnListener implements ActionListener {// 图书添加窗口返回目录按钮事件

    private MgrAddBookWindow addingWindow;
    private WarehouseMgrMenuWindow manMenuWindow;

    public MgrAddBookBackBtnListener(MgrAddBookWindow addingWindow, WarehouseMgrMenuWindow manMenuWindow) {
        this.addingWindow = addingWindow;
        this.manMenuWindow = manMenuWindow;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        manMenuWindow.setLocationRelativeTo(null);
        manMenuWindow.setVisible(true);
        addingWindow.dispose();
    }

}
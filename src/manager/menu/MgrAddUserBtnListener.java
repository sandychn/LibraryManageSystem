package manager.menu;

import manager.user.addition.MgrUserAddWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MgrAddUserBtnListener implements ActionListener {// 新建用户按钮事件
    private LibraryMgrMenuWindow manMenuWindow;

    public MgrAddUserBtnListener(LibraryMgrMenuWindow manMenuWindow) {
        this.manMenuWindow = manMenuWindow;
    }

    public void actionPerformed(ActionEvent e) {
        @SuppressWarnings("unused")
        MgrUserAddWindow manmenu = new MgrUserAddWindow(manMenuWindow);// 新建用户窗口
        manMenuWindow.setVisible(false);
    }
}

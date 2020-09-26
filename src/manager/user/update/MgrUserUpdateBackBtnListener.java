package manager.user.update;

import manager.menu.BackgroundMgrMenuWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MgrUserUpdateBackBtnListener implements ActionListener {// 修改用户身份返回目录按钮事件

    private MgrUserUpdateWindow userUpdatingWindow;
    private BackgroundMgrMenuWindow manMenuWindow;

    public MgrUserUpdateBackBtnListener(MgrUserUpdateWindow userUpdatingWindow, BackgroundMgrMenuWindow manMenuWindow) {
        this.userUpdatingWindow = userUpdatingWindow;
        this.manMenuWindow = manMenuWindow;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        manMenuWindow.setLocationRelativeTo(null);
        manMenuWindow.setVisible(true);
        userUpdatingWindow.dispose();
    }

}
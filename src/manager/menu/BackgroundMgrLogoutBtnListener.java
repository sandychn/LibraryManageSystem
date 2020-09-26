package manager.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackgroundMgrLogoutBtnListener implements ActionListener {// 目录退出按钮事件

    private BackgroundMgrMenuWindow manMenuWindow;

    public BackgroundMgrLogoutBtnListener(BackgroundMgrMenuWindow manMenuWindow) {
        this.manMenuWindow = manMenuWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (JOptionPane.showOptionDialog(null, "确定要退出系统吗", "确认", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null) == JOptionPane.YES_OPTION)
            manMenuWindow.dispose();
    }
}

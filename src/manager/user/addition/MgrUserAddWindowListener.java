package manager.user.addition;

import manager.menu.LibraryMgrMenuWindow;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MgrUserAddWindowListener implements WindowListener {// 新建用户窗口事件

    private LibraryMgrMenuWindow manMenuWindow;

    public MgrUserAddWindowListener(LibraryMgrMenuWindow manMenuWindow) {
        super();
        this.manMenuWindow = manMenuWindow;
    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        manMenuWindow.setLocationRelativeTo(null);
        manMenuWindow.setVisible(true);
    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

}

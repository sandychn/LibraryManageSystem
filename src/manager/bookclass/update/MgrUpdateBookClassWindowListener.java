package manager.bookclass.update;

import manager.menu.WarehouseMgrMenuWindow;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MgrUpdateBookClassWindowListener implements WindowListener {// 修改图书分类窗口事件

    private WarehouseMgrMenuWindow manMenuWindow;

    public MgrUpdateBookClassWindowListener(WarehouseMgrMenuWindow manMenuWindow) {
        this.manMenuWindow = manMenuWindow;
    }

    @Override
    public void windowClosing(WindowEvent arg0) {
        manMenuWindow.setLocationRelativeTo(null);
        manMenuWindow.setVisible(true);
    }

    @Override
    public void windowActivated(WindowEvent arg0) {

    }

    @Override
    public void windowClosed(WindowEvent arg0) {

    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {

    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {

    }

    @Override
    public void windowIconified(WindowEvent arg0) {

    }

    @Override
    public void windowOpened(WindowEvent arg0) {

    }

}

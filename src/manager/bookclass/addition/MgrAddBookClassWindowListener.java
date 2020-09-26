package manager.bookclass.addition;

import manager.menu.WarehouseMgrMenuWindow;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MgrAddBookClassWindowListener implements WindowListener {// 添加图书类别的窗口事件

    private WarehouseMgrMenuWindow manMenuWindow;

    public MgrAddBookClassWindowListener(WarehouseMgrMenuWindow manMenuWindow) {
        this.manMenuWindow = manMenuWindow;
    }

    @Override
    public void windowClosing(WindowEvent arg0) {// 窗口关闭时的事件
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

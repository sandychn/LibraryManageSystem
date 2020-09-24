package manager.book.addition;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import manager.menu.WarehouseMgrMenuWindow;

public class MgrAddBookWindowListener implements WindowListener {// 添加书本窗口事件

	private WarehouseMgrMenuWindow manMenuWindow;

	public MgrAddBookWindowListener(WarehouseMgrMenuWindow manMenuWindow) {
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

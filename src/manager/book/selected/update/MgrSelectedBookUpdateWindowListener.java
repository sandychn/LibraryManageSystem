package manager.book.selected.update;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import manager.book.update.MgrUpdateBookWindow;

public class MgrSelectedBookUpdateWindowListener implements WindowListener {// 添加书本窗口事件

	private MgrUpdateBookWindow updatingWindow;

	public MgrSelectedBookUpdateWindowListener(MgrUpdateBookWindow updatingWindow) {
		this.updatingWindow = updatingWindow;

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		updatingWindow.setTable();
		updatingWindow.setLocationRelativeTo(null);
		updatingWindow.setVisible(true);

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

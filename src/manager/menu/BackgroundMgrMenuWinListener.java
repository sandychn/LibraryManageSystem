package manager.menu;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import login.LoginWindow;

public class BackgroundMgrMenuWinListener implements WindowListener {// 目录窗口事件

	private LoginWindow loginWindow;
	private BackgroundMgrMenuWindow manMenuWindow;

	public BackgroundMgrMenuWinListener(LoginWindow loginWindow, BackgroundMgrMenuWindow manMenuWindow) {
		this.loginWindow = loginWindow;
		this.manMenuWindow = manMenuWindow;
	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {
		loginWindow.setPassword("");
		loginWindow.setLocationRelativeTo(null);
		loginWindow.setVisible(true);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (JOptionPane.showOptionDialog(null, "确定要退出系统吗?", "确认", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null) == JOptionPane.YES_OPTION)
			manMenuWindow.dispose();
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

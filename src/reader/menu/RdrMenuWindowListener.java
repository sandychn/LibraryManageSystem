package reader.menu;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import login.LoginWindow;

public class RdrMenuWindowListener implements WindowListener {

	LoginWindow loginWindow;
	RdrMenuWindow rdrMenuWindow;

	public RdrMenuWindowListener(LoginWindow loginWindow, RdrMenuWindow rdrMenuWindow) {
		this.loginWindow = loginWindow;
		this.rdrMenuWindow = rdrMenuWindow;
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
			rdrMenuWindow.dispose();
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

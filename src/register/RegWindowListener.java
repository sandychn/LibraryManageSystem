package register;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import login.LoginWindow;

public class RegWindowListener implements WindowListener {

	private LoginWindow loginWindow;

	public RegWindowListener(LoginWindow father) {
		super();
		this.loginWindow = father;
	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {
		loginWindow.setUName("");
		loginWindow.setPassword("");
		loginWindow.setLocationRelativeTo(null);
		loginWindow.setVisible(true);
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

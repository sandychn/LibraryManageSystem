package login;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import login.service.LoginService;

public class LoginWindowListener implements WindowListener {

	private LoginService loginService;

	public LoginWindowListener(LoginService loginService) {
		this.loginService = loginService;
	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) { // 在窗口即将关闭时断开数据库连接
		try {
			loginService.disconnectDatabase();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("断开数据库失败: " + sqle.getMessage());
		}
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

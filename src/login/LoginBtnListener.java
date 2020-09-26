package login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import common.constant.UserConstant;
import common.util.CurrentUser;
import entity.User;
import login.service.LoginException;
import login.service.LoginService;
import manager.menu.BackgroundMgrMenuWindow;
import manager.menu.LibraryMgrMenuWindow;
import manager.menu.WarehouseMgrMenuWindow;
import reader.menu.RdrMenuWindow;

public class LoginBtnListener implements ActionListener {

	private final LoginWindow loginWindow;
	private final LoginService loginService;

	public LoginBtnListener(LoginWindow loginWindow, LoginService loginService) {
		this.loginWindow = loginWindow;
		this.loginService = loginService;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username = loginWindow.getUName(), password = loginWindow.getPassword();
		try {
			User user = loginService.tryLogin(new User(username, password, -1));
			CurrentUser.setCurrentUser(user);
			loginWindow.launchNextWindow(user);
		} catch (LoginException exp) {
			JOptionPane.showMessageDialog(null, exp.getMessage(),
					"登录时遇到错误，请联系管理员", JOptionPane.ERROR_MESSAGE);
		}
	}

}

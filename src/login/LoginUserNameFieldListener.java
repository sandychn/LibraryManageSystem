package login;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import common.constant.UserConstant;
import common.util.CurrentUser;
import common.util.Tools;
import entity.User;
import login.service.LoginException;
import login.service.LoginService;
import manager.menu.BackgroundMgrMenuWindow;
import manager.menu.LibraryMgrMenuWindow;
import manager.menu.WarehouseMgrMenuWindow;
import reader.menu.RdrMenuWindow;

public class LoginUserNameFieldListener implements KeyListener {

	private LoginWindow loginWindow;
	private LoginService loginService;

	public LoginUserNameFieldListener(LoginWindow loginWindow, LoginService loginService) {
		this.loginWindow = loginWindow;
		this.loginService = loginService;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		String username = loginWindow.getUName(), password = loginWindow.getPassword();
		int code = e.getKeyChar();
		if (code == KeyEvent.VK_BACK_SPACE) {
			e.isActionKey();
		} else if (code == KeyEvent.VK_ENTER) {
			try {
				User user = loginService.tryLogin(new User(username, password, -1));
				CurrentUser.setCurrentUser(user);
				if (user.getIdentity() == UserConstant.IDENTITY_USER_ADMIN) {
					@SuppressWarnings("unused")
					LibraryMgrMenuWindow managerMenu = new LibraryMgrMenuWindow(loginWindow);
					loginWindow.setVisible(false);
				} else if (user.getIdentity() == UserConstant.IDENTITY_READER) {
					@SuppressWarnings("unused")
					RdrMenuWindow readerMenuWindow = new RdrMenuWindow(loginWindow);
					loginWindow.setVisible(false);
				} else if (user.getIdentity() == UserConstant.IDENTITY_STORE_ADMIN) {
					@SuppressWarnings("unused")
					WarehouseMgrMenuWindow managerMenu = new WarehouseMgrMenuWindow(loginWindow);
					loginWindow.setVisible(false);
				} else if (user.getIdentity() == UserConstant.IDENTITY_SUPER_USER_ADMIN) {
					@SuppressWarnings("unused")
					BackgroundMgrMenuWindow managerMenu = new BackgroundMgrMenuWindow(loginWindow);
					loginWindow.setVisible(false);
				}
			} catch (LoginException exp) {
				JOptionPane.showMessageDialog(null, exp.getMessage(), "登录失败", JOptionPane.ERROR_MESSAGE);
			}
		} else if (username.length() >= UserConstant.USERNAME_MAXLEN) { // 用户名长度限制
			e.consume();
		} else if (!Tools.isLegalCharacter(code)) {
			e.consume();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}

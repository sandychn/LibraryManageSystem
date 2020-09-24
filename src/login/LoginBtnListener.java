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

	private LoginWindow loginWindow;
	private LoginService loginService;

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
			JOptionPane.showMessageDialog(null, exp.getMessage(), "µÇÂ¼Ê§°Ü", JOptionPane.ERROR_MESSAGE);
		}
	}

}

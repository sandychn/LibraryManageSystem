package login;

import common.constant.UserConstant;
import common.util.CurrentUser;
import common.util.Tools;
import entity.User;
import login.service.LoginException;
import login.service.LoginService;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginUsernameFieldListener implements KeyListener {

    private final LoginWindow loginWindow;
    private final LoginService loginService;

    public LoginUsernameFieldListener(LoginWindow loginWindow, LoginService loginService) {
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
                loginWindow.launchNextWindow(user);
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

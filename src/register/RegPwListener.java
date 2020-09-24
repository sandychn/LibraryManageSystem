package register;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import common.constant.UserConstant;
import common.util.Tools;
import entity.User;
import register.service.RegException;
import register.service.RegService;

public class RegPwListener implements KeyListener {

	private RegWindow regWindow;
	private RegService regService;

	public RegPwListener(RegWindow regWindow, RegService regService) {
		this.regWindow = regWindow;
		this.regService = regService;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		String username = regWindow.getUName(), password = regWindow.getPassword();
		int code = e.getKeyChar();
		if (code == KeyEvent.VK_BACK_SPACE) {
			e.isActionKey();
		} else if (code == KeyEvent.VK_ENTER) {
			try {
				regService.tryRegister(new User(username, password, 2));
				JOptionPane.showMessageDialog(null, "注册成功", "注册成功", JOptionPane.INFORMATION_MESSAGE);
				regWindow.dispose();
			} catch (RegException exp) {
				JOptionPane.showMessageDialog(null, exp.getMessage(), "注册失败", JOptionPane.ERROR_MESSAGE);
			}
		} else if (password.length() >= UserConstant.PASSWORD_MAXLEN) { // 密码长度限制
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

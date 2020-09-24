package manager.user.addition;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import common.constant.UserConstant;
import common.util.Tools;
import entity.User;
import manager.user.addition.service.MgrUserAddException;
import manager.user.addition.service.MgrUserAddService;

public class MgrUserPwFieldListener implements KeyListener {// 密码框键盘事件

	private MgrUserAddWindow userAddingWindow;
	private MgrUserAddService userAddingOperation;

	public MgrUserPwFieldListener(MgrUserAddWindow userAddingWindow, MgrUserAddService userAddingOperation) {
		this.userAddingWindow = userAddingWindow;
		this.userAddingOperation = userAddingOperation;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		String username = userAddingWindow.getUName(), password = userAddingWindow.getPassword(),
				userId = userAddingWindow.getUId();
		int code = e.getKeyChar();
		if (code == KeyEvent.VK_BACK_SPACE) {
			e.isActionKey();
		} else if (code == KeyEvent.VK_ENTER) {
			try {
				userAddingOperation.tryRegister(new User(username, password, Integer.parseInt(userId)));
				JOptionPane.showMessageDialog(null, "新建成功", "成功", JOptionPane.INFORMATION_MESSAGE);
				userAddingWindow.dispose();
			} catch (MgrUserAddException exp) {
				JOptionPane.showMessageDialog(null, exp.getMessage(), "失败", JOptionPane.ERROR_MESSAGE);
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

package manager.user.addition;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import common.constant.UserConstant;
import entity.User;
import manager.user.addition.service.MgrUserAddException;
import manager.user.addition.service.MgrUserAddService;

public class MgrUserAddBtnListener implements ActionListener {// 新建用户事件

	private MgrUserAddWindow userAddingWindow;
	private MgrUserAddService userAddingOperation;

	public MgrUserAddBtnListener(MgrUserAddWindow userAddingWindow, MgrUserAddService userAddingOperation) {
		this.userAddingWindow = userAddingWindow;
		this.userAddingOperation = userAddingOperation;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username = userAddingWindow.getUName(), password = userAddingWindow.getPassword(),
				userId = userAddingWindow.getUId().trim();
		try {
			userAddingOperation.tryRegister(new User(username, password, Integer.parseInt(userId)));
			JOptionPane.showMessageDialog(null, "新建成功", "成功", JOptionPane.INFORMATION_MESSAGE);
			userAddingWindow.dispose();
		} catch (MgrUserAddException exp) {
			JOptionPane.showMessageDialog(null, exp.getMessage(), "失败", JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException exp) {
			JOptionPane.showMessageDialog(null,
					"身份只能为管理员(" + UserConstant.IDENTITY_USER_ADMIN + ")或用户(" + UserConstant.IDENTITY_READER + ")",
					"失败", JOptionPane.ERROR_MESSAGE);
		}
	}

}

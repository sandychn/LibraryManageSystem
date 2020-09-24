package manager.user.update;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import common.constant.UserConstant;
import manager.user.update.service.MgrUserUpdateException;
import manager.user.update.service.MgrUserUpdateService;

public class MgrUserSelectBtnListener implements ActionListener {// 查询按钮事件

	private MgrUserUpdateWindow userUpdateWindow;
	private MgrUserUpdateService mgrUserUpdateService;

	public MgrUserSelectBtnListener(MgrUserUpdateWindow userUpdate, MgrUserUpdateService mgrUserUpdateService) {
		this.userUpdateWindow = userUpdate;
		this.mgrUserUpdateService = mgrUserUpdateService;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			String uName = userUpdateWindow.getUName();
			if (uName.equals("") == false) {
				if (mgrUserUpdateService.judgeUserExists(uName) == false) {
					JOptionPane.showMessageDialog(null, "不存在该用户", "查询失败", JOptionPane.ERROR_MESSAGE);
					mgrUserUpdateService.setUserExists(false);
				} else {
					mgrUserUpdateService.setUserByuName(uName);
					int identity = mgrUserUpdateService.getUser().getIdentity();
					if (identity == UserConstant.IDENTITY_READER) {
						userUpdateWindow.setUId("学生");
					} else if (identity == UserConstant.IDENTITY_USER_ADMIN) {
						userUpdateWindow.setUId("图书馆用户管理员");
					} else if (identity == UserConstant.IDENTITY_STORE_ADMIN) {
						userUpdateWindow.setUId("仓库管理员");
					} else if (identity == UserConstant.IDENTITY_SUPER_USER_ADMIN) {
						userUpdateWindow.setUId("后台数据管理员");
					} else {
						JOptionPane.showMessageDialog(null, "该用户身份异常", "查询失败", JOptionPane.ERROR_MESSAGE);
					}
					mgrUserUpdateService.setUserExists(true);
				}
			}
		} catch (MgrUserUpdateException exp) {
			JOptionPane.showMessageDialog(null, "操作时出现错误。错误信息:\n" + exp.getMessage(), "失败", JOptionPane.ERROR_MESSAGE);
			exp.printStackTrace();
		}
	}

}

package manager.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import manager.user.update.MgrUserUpdateWindow;

public class MgrUpdateUserBtnListener implements ActionListener {// 目录更新用户身份按钮事件
	private BackgroundMgrMenuWindow manMenuWindow;

	public MgrUpdateUserBtnListener(BackgroundMgrMenuWindow manMenuWindow) {
		this.manMenuWindow = manMenuWindow;
	}

	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unused")
		MgrUserUpdateWindow manmenu = new MgrUserUpdateWindow(manMenuWindow);
		manMenuWindow.setVisible(false);
	}
}

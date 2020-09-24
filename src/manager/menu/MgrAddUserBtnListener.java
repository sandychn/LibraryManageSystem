package manager.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import manager.user.addition.MgrUserAddWindow;

public class MgrAddUserBtnListener implements ActionListener {// 新建用户按钮事件
	private LibraryMgrMenuWindow manMenuWindow;

	public MgrAddUserBtnListener(LibraryMgrMenuWindow manMenuWindow) {
		this.manMenuWindow = manMenuWindow;
	}

	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unused")
		MgrUserAddWindow manmenu = new MgrUserAddWindow(manMenuWindow);// 新建用户窗口
		manMenuWindow.setVisible(false);
	}
}

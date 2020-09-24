package manager.user.addition;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import manager.menu.LibraryMgrMenuWindow;

public class MgrUserAddBackBtnListener implements ActionListener {// 新建用户返回目录的按钮事件

	private MgrUserAddWindow userAddingWindow;
	private LibraryMgrMenuWindow manMenuWindow;

	public MgrUserAddBackBtnListener(MgrUserAddWindow userAddingWindow, LibraryMgrMenuWindow manMenuWindow) {
		this.userAddingWindow = userAddingWindow;
		this.manMenuWindow = manMenuWindow;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		manMenuWindow.setLocationRelativeTo(null);
		manMenuWindow.setVisible(true);
		userAddingWindow.dispose();
	}
}

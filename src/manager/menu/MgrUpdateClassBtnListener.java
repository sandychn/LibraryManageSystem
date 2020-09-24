package manager.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import manager.bookclass.update.MgrUpdateBookClassWindow;

public class MgrUpdateClassBtnListener implements ActionListener {// 目录修改图书分类按钮事件
	private WarehouseMgrMenuWindow manMenuWindow;

	public MgrUpdateClassBtnListener(WarehouseMgrMenuWindow manMenuWindow) {
		this.manMenuWindow = manMenuWindow;
	}

	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unused")
		MgrUpdateBookClassWindow manmenu = new MgrUpdateBookClassWindow(manMenuWindow);// 目录修改图书分类窗口
		manMenuWindow.setVisible(false);
	}
}

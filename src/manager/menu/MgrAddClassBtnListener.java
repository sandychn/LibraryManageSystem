package manager.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import manager.bookclass.addition.MgrAddBookClassWindow;

public class MgrAddClassBtnListener implements ActionListener {// 添加图书类的按钮事件
	private WarehouseMgrMenuWindow manMenuWindow;

	public MgrAddClassBtnListener(WarehouseMgrMenuWindow manMenuWindow) {
		this.manMenuWindow = manMenuWindow;
	}

	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unused")
		MgrAddBookClassWindow manmenu = new MgrAddBookClassWindow(manMenuWindow);// 添加图书类的窗口
		manMenuWindow.setVisible(false);
	}
}

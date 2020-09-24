package manager.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import manager.book.select.MgrSelectBookWindow;

public class MgrSelectBookBtnListener implements ActionListener {// 目录查询图书按钮事件
	private LibraryMgrMenuWindow manMenuWindow;

	public MgrSelectBookBtnListener(LibraryMgrMenuWindow manMenuWindow) {
		this.manMenuWindow = manMenuWindow;
	}

	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unused")
		MgrSelectBookWindow manmenu = new MgrSelectBookWindow(manMenuWindow);// 添加图书窗口
		manMenuWindow.setVisible(false);
	}
}

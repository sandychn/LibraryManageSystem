package manager.book.select;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import manager.menu.LibraryMgrMenuWindow;

public class MgrSelectBookBackBtnListener implements ActionListener {// 修改图书的返回目录的按钮事件

	private MgrSelectBookWindow updatingWindow;
	private LibraryMgrMenuWindow manMenuWindow;

	public MgrSelectBookBackBtnListener(MgrSelectBookWindow updatingWindow, LibraryMgrMenuWindow manMenuWindow) {
		this.updatingWindow = updatingWindow;
		this.manMenuWindow = manMenuWindow;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		manMenuWindow.setLocationRelativeTo(null);
		manMenuWindow.setVisible(true);
		updatingWindow.dispose();
	}
}

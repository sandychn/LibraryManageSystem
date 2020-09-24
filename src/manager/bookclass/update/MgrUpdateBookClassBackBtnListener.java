package manager.bookclass.update;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import manager.menu.WarehouseMgrMenuWindow;

public class MgrUpdateBookClassBackBtnListener implements ActionListener {// 修改图书分类的返回目录按钮事件

	private MgrUpdateBookClassWindow addingClassWindow;
	private WarehouseMgrMenuWindow manMenuWindow;

	public MgrUpdateBookClassBackBtnListener(MgrUpdateBookClassWindow addingClassWindow,
			WarehouseMgrMenuWindow manMenuWindow) {
		this.addingClassWindow = addingClassWindow;
		this.manMenuWindow = manMenuWindow;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		manMenuWindow.setLocationRelativeTo(null);
		manMenuWindow.setVisible(true);
		addingClassWindow.dispose();
	}

}
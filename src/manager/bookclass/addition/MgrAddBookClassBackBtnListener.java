package manager.bookclass.addition;

import manager.menu.WarehouseMgrMenuWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MgrAddBookClassBackBtnListener implements ActionListener {// 添加图书分类的返回目录按钮事件

    private MgrAddBookClassWindow addingClassWindow;
    private WarehouseMgrMenuWindow manMenuWindow;

    public MgrAddBookClassBackBtnListener(MgrAddBookClassWindow addingClassWindow,
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
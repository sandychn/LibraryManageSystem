package manager.bookclass.update;

import manager.bookclass.update.service.MgrUpdateBookClassException;
import manager.bookclass.update.service.MgrUpdateBookClassService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MgrUpdateBookClassBtnListener implements ActionListener {// 添加图书分类 的事件

    private MgrUpdateBookClassWindow bookClassAdd;
    private MgrUpdateBookClassService updateBookClassOperation;

    public MgrUpdateBookClassBtnListener(MgrUpdateBookClassWindow bookClassAdd,
                                         MgrUpdateBookClassService updateBookClassOperation) {
        this.bookClassAdd = bookClassAdd;
        this.updateBookClassOperation = updateBookClassOperation;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (updateBookClassOperation.isTrue(bookClassAdd.getSelectedClassId(),
                    bookClassAdd.getNameText()) == true) {
                MgrUpdateBookClassService oper = new MgrUpdateBookClassService(bookClassAdd);
                if (oper.updateBookClass(bookClassAdd.getSelectedClassId(), bookClassAdd.getNameText())) {
                    JOptionPane.showMessageDialog(null, "该类名已成功修改", "修改成功", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (MgrUpdateBookClassException exp) {
            JOptionPane.showMessageDialog(null, exp.getMessage(), "修改失败", JOptionPane.ERROR_MESSAGE);
        }
    }
}

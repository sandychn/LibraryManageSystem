package manager.bookclass.addition;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import manager.bookclass.addition.service.MgrAddBookClassException;
import manager.bookclass.addition.service.MgrAddBookClassService;

public class MgrAddBookClassBtnListener implements ActionListener {// 添加图书分类事件

	private MgrAddBookClassWindow bookClassAdd;
	private MgrAddBookClassService bookClassOperation;

	public MgrAddBookClassBtnListener(MgrAddBookClassWindow bookClassAdd, MgrAddBookClassService bookClassOperation) {
		this.bookClassAdd = bookClassAdd;
		this.bookClassOperation = bookClassOperation;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			String className = bookClassAdd.getClassName();
			if (bookClassOperation.isTrue(className) == true) {
				bookClassOperation.bookAddition(className);
				JOptionPane.showMessageDialog(null, "该类名已成功添加", "添加成功", JOptionPane.INFORMATION_MESSAGE);
				bookClassAdd.setClassIdText(Integer.parseInt(bookClassAdd.getClassIdText()) + 1);
				bookClassAdd.setClassNameText();
			}
		} catch (MgrAddBookClassException exp) {
			JOptionPane.showMessageDialog(null, exp.getMessage(), "添加失败", JOptionPane.ERROR_MESSAGE);
		}
	}
}

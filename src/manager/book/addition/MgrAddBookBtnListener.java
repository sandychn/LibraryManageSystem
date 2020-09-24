package manager.book.addition;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import manager.book.addition.service.MgrAddBookException;
import manager.book.addition.service.MgrAddBookService;

public class MgrAddBookBtnListener implements ActionListener {// 添加书本按钮事件

	private MgrAddBookWindow bookAdd;
	private MgrAddBookService bookAddOperation;

	public MgrAddBookBtnListener(MgrAddBookWindow bookAdd, MgrAddBookService bookAddOperation) {
		this.bookAdd = bookAdd;
		this.bookAddOperation = bookAddOperation;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (bookAddOperation.isTrue(bookAdd.getWindowBook()) == true) {// 添加图书
				if (bookAddOperation.bookAddition(bookAdd.getWindowBook())) {
					JOptionPane.showMessageDialog(null, "该书籍已成功添加", "添加成功", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} catch (MgrAddBookException exp) {
			JOptionPane.showMessageDialog(null, exp.getMessage(), "添加失败", JOptionPane.ERROR_MESSAGE);
		}
	}
}

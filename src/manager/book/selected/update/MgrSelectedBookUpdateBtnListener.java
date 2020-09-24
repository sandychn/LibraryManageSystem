package manager.book.selected.update;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import entity.Book;
import manager.book.selected.update.service.MgrSelectedBookUpdateException;
import manager.book.selected.update.service.MgrSelectedBookUpdateService;

public class MgrSelectedBookUpdateBtnListener implements ActionListener {// 添加书本事件

	private MgrSelectedBookUpdateWindow bookUpdetingWindow;
	private MgrSelectedBookUpdateService bookUpdeteOperation;
	private Book book;

	public MgrSelectedBookUpdateBtnListener(MgrSelectedBookUpdateWindow bookUpdetingWindow,
			MgrSelectedBookUpdateService bookUpdeteOperation, Book book) {
		this.bookUpdetingWindow = bookUpdetingWindow;
		this.bookUpdeteOperation = bookUpdeteOperation;
		this.book = book;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (bookUpdeteOperation.isTrue(bookUpdetingWindow.getWindowBook(), book) == true) {
				MgrSelectedBookUpdateService oper = new MgrSelectedBookUpdateService();
				if (oper.bookUpdating(bookUpdetingWindow.getWindowBook())) {
					JOptionPane.showMessageDialog(null, "该书籍已成功修改", "修改成功", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} catch (MgrSelectedBookUpdateException exp) {
			JOptionPane.showMessageDialog(null, exp.getMessage(), "修改失败", JOptionPane.ERROR_MESSAGE);
		}
	}
}

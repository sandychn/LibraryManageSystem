package manager.bookclass.update.service;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import common.constant.BookConstant;
import manager.bookclass.update.MgrUpdateBookClassWindow;
import manager.bookclass.update.dao.MgrUpdateBookClassDataAccess;

public class MgrUpdateBookClassService {

	private MgrUpdateBookClassDataAccess connect = null;
	private MgrUpdateBookClassWindow bookClassUpdatingWindow;

	public MgrUpdateBookClassService(MgrUpdateBookClassWindow bookClassUpdatingWindow) {
		this.connect = new MgrUpdateBookClassDataAccess();
		this.bookClassUpdatingWindow = bookClassUpdatingWindow;
	}

	public boolean updateBookClass(int classId, String className) throws MgrUpdateBookClassException {
		try {// 向数据库中修改图书类名
			connect.updateBookClassName(classId, className);
			bookClassUpdatingWindow.updateComboBoxItemAt(className, classId - 1);// 更新下拉列表类名
			return true;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new MgrUpdateBookClassException("数据库出错。错误信息:\n" + sqle.getMessage());
		}
	}

	public String[] getBookClassStrings() throws MgrUpdateBookClassException {
		try {// 从数据库中读取类名加入下拉列表中
			return connect.getBookClassList();
		} catch (SQLException sqle) {
			throw new MgrUpdateBookClassException("数据库出错。错误信息:\n" + sqle.getMessage());
		}
	}

	public String getBClassNameByBClassId(int bookClassId) throws MgrUpdateBookClassException {
		try {// 返回对应类名号的名字
			return connect.getbClassNameBybClassId(bookClassId);
		} catch (SQLException sqle) {
			throw new MgrUpdateBookClassException("数据库出错。错误信息:\n" + sqle.getMessage());
		}
	}

	public boolean isBClassNameExists(String className) throws MgrUpdateBookClassException {
		try {// 判断数据库是否存在该类名
			return connect.isBookClassNameExists(className);
		} catch (SQLException sqle) {
			throw new MgrUpdateBookClassException("数据库出错。错误信息:\n" + sqle.getMessage());
		}
	}

	public boolean isTrue(int classId, String className) {// 判断是否修改图书类名
		if (className.length() == 0) {
			JOptionPane.showMessageDialog(null, "请输入要修改的图书类名", "错误", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {
			String oldClassName = getBClassNameByBClassId(classId);
			if (oldClassName.equals(className)) {
				JOptionPane.showMessageDialog(null, "请输入与储存图书类名不同的类名", "错误", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (MgrUpdateBookClassException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "连接数据库失败", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {
			if (isBClassNameExists(className)) {
				JOptionPane.showMessageDialog(null, "该图书类名已存在", "错误", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (MgrUpdateBookClassException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "连接数据库失败", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (className.length() > BookConstant.CLASS_MAXLEN) {
			JOptionPane.showMessageDialog(null, "图书类名超出限制（最多" + BookConstant.CLASS_MAXLEN + "字符）", "错误",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (JOptionPane.showOptionDialog(null, "确认修改该图书分类吗?", "确认", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null) != JOptionPane.YES_OPTION) {
			return false;
		}
		return true;
	}
}

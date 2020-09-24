package reader.record.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import common.util.CurrentUser;
import entity.BookLendingRecord;
import reader.record.dao.RdrReturnDataAccess;

public class RdrReturnService {

	private RdrReturnDataAccess connect;

	public RdrReturnService() {
		connect = new RdrReturnDataAccess();
	}

	public boolean tryReturn(BookLendingRecord bookRecord) throws RdrReturnException, SQLException {
		// 检查是否已归还
		if (bookRecord.getBrTime() != null) {
			throw new RdrReturnException("该书已归还。");
		}
		// 检查是否已经超期
		Date nowTime = connect.getDate(); // 先取服务器(数据库)时间
		String bookName = bookRecord.getbName();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date blDate = bookRecord.getBlTime();
		long due = bookRecord.getBlDue() * 24L * 60 * 60 * 1000;
		long exceptedReturnTime = blDate.getTime() + due;
		Date exceptedReturnDate = new Date(exceptedReturnTime);
		boolean overdue = nowTime.after(exceptedReturnDate); // 是否超期

		String isbn = bookRecord.getbISBN();
		String dialogMessageHtml = "<html>您选择的书籍信息:<br/><br/>书名: " + bookName + "<br/>ISBN: " + isbn + "<br/>";
		if (overdue) {
			long overdueDay = (nowTime.getTime() - exceptedReturnTime) / 1000 / 3600 / 24;
			dialogMessageHtml += "<br/><font color=\"red\">请注意：<br/>该书已于 " + formatter.format(exceptedReturnDate)
					+ " 超期，已逾期" + overdueDay + "天。<br/>请在还书之后前往服务台，缴纳图书超期滞纳金。</font><br/>";
		}
		dialogMessageHtml += "<br/>确认归还该图书吗?</html>";
		if (JOptionPane.showOptionDialog(null, new JLabel(dialogMessageHtml), "确认", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null) != JOptionPane.YES_OPTION) {
			return false;
		}
		connect.returnBookByBookLendingRecordAndUser(bookRecord, CurrentUser.getCurrentUser());
		return true;
	}

}

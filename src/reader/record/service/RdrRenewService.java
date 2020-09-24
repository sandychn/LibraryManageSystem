package reader.record.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import common.constant.BookConstant;
import common.util.CurrentUser;
import entity.BookLendingRecord;
import reader.record.dao.RdrRenewDataAccess;

public class RdrRenewService {

	private RdrRenewDataAccess connect;

	public RdrRenewService() {
		connect = new RdrRenewDataAccess();
	}

	public Date tryRenew(BookLendingRecord bookRecord) throws RdrRenewException, SQLException { // 返回null表示用户放弃操作
		// 检查是否已归还
		if (bookRecord.getBrTime() != null) {
			throw new RdrRenewException("该书已归还。");
		}
		// 检查是否已经续借过
		if (bookRecord.getBlDue() > BookConstant.DEFAULT_BLDUE) {
			throw new RdrRenewException("该书已经续借，请按期归还。");
		}
		// 检查是否已经超期
		// 取当前时间和ISBN对应书名
		String bName = bookRecord.getbName();
		Date nowDate = connect.getDate(); // 服务器(数据库)时间
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date blDate = bookRecord.getBlTime();
		// due mill seconds
		long due = bookRecord.getBlDue() * 24L * 60 * 60 * 1000;
		long exceptedReturnTime = blDate.getTime() + due;
		long canRenewStartTime = exceptedReturnTime - BookConstant.TRY_RENEW_DAY * 24L * 60 * 60 * 1000;
		Date exceptedReturnDate = new Date(exceptedReturnTime);
		Date canRenewStartDate = new Date(canRenewStartTime);
		if (nowDate.after(exceptedReturnDate)) {
			throw new RdrRenewException("该书已于 " + formatter.format(exceptedReturnDate) + " 到期，不能续借。\n");
		}

		// 检查是否到达可以申请续借的时间
		if (nowDate.before(canRenewStartDate)) {
			throw new RdrRenewException("到期前" + BookConstant.TRY_RENEW_DAY + "天才能续借!");
		}

		// 确认续借窗口
		if (JOptionPane.showOptionDialog(null,
				"您选择的书籍信息:\n\n书名: " + bName + "\nISBN: " + bookRecord.getbISBN() + "\n\n确认续借该书籍吗?", "确认",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null) != JOptionPane.YES_OPTION) {
			return null;
		}
		connect.renewBookByBookLendingRecordAndUser(bookRecord, CurrentUser.getCurrentUser());
		long newDue = BookConstant.RENEW_BLDUE * 24L * 60 * 60 * 1000;
		long newExceptedReturnTime = blDate.getTime() + newDue;
		Date newExceptedReturnDate = new Date(newExceptedReturnTime);
		return newExceptedReturnDate;
	}

}

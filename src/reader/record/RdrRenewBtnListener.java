package reader.record;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import reader.record.service.RdrRecordException;
import reader.record.service.RdrRenewException;
import reader.record.service.RdrRenewService;

public class RdrRenewBtnListener implements ActionListener {

	private RdrRecordPanel recordPanel;
	private RdrRenewService renewOperation;

	public RdrRenewBtnListener(RdrRecordPanel recordPanel, RdrRenewService renewOperation) {
		this.recordPanel = recordPanel;
		this.renewOperation = renewOperation;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!recordPanel.isRowSelected()) {
			JOptionPane.showMessageDialog(null, "请在列表中选择您需要续借的图书", "提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		try {
			Date exceptedReturnDate = renewOperation.tryRenew(recordPanel.getSelectedBookRecord());
			if (exceptedReturnDate != null) {
				recordPanel.setTable();
				JOptionPane.showMessageDialog(null,
						"续借成功。此书将于 " + new SimpleDateFormat("yyyy-MM-dd").format(exceptedReturnDate) + " 到期，请按期归还。",
						"续借成功", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (RdrRecordException exp) {
			JOptionPane.showMessageDialog(null, exp.getMessage(), "续借失败", JOptionPane.ERROR_MESSAGE);
		} catch (RdrRenewException exp) {
			JOptionPane.showMessageDialog(null, exp.getMessage(), "续借失败", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			JOptionPane.showMessageDialog(null, "获取借阅记录时出现错误。请联系管理员", "错误", JOptionPane.ERROR_MESSAGE);
		}
	}
}

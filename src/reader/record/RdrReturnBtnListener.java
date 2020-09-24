package reader.record;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import reader.record.service.RdrRecordException;
import reader.record.service.RdrReturnException;
import reader.record.service.RdrReturnService;

public class RdrReturnBtnListener implements ActionListener {

	private RdrRecordPanel recordPanel;
	private RdrReturnService returnOperation;

	public RdrReturnBtnListener(RdrRecordPanel recordPanel, RdrReturnService returnOperation) {
		this.recordPanel = recordPanel;
		this.returnOperation = returnOperation;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!recordPanel.isRowSelected()) {
			JOptionPane.showMessageDialog(null, "请在列表中选择您需要归还的图书", "提示", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		try {
			if (returnOperation.tryReturn(recordPanel.getSelectedBookRecord())) {
				recordPanel.setTable();
				JOptionPane.showMessageDialog(null, "还书成功", "还书成功", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (RdrReturnException exp) {
			JOptionPane.showMessageDialog(null, exp.getMessage(), "还书失败", JOptionPane.ERROR_MESSAGE);
		} catch (RdrRecordException exp) {
			JOptionPane.showMessageDialog(null, exp.getMessage(), "还书失败", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			JOptionPane.showMessageDialog(null, "获取借阅记录时出现错误。请联系管理员", "错误", JOptionPane.ERROR_MESSAGE);
		}
	}

}

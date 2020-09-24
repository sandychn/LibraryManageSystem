package reader.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class RdrLogoutBtnListener implements ActionListener {

	RdrMenuWindow rdrMenuWindow;

	public RdrLogoutBtnListener(RdrMenuWindow rdrMenuWindow) {
		this.rdrMenuWindow = rdrMenuWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (JOptionPane.showOptionDialog(null, "确定要退出系统吗?", "确认", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null) == JOptionPane.YES_OPTION) {
			rdrMenuWindow.dispose();
		}
	}

}

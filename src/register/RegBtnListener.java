package register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import entity.User;
import register.service.RegException;
import register.service.RegService;

public class RegBtnListener implements ActionListener {

	private RegWindow regWindow;
	private RegService regService;

	public RegBtnListener(RegWindow regWindow, RegService regService) {
		this.regWindow = regWindow;
		this.regService = regService;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username = regWindow.getUName(), password = regWindow.getPassword();
		try {
			regService.tryRegister(new User(username, password, 2));
			JOptionPane.showMessageDialog(null, "×¢²á³É¹¦", "×¢²á³É¹¦", JOptionPane.INFORMATION_MESSAGE);
			regWindow.dispose();
		} catch (RegException exp) {
			JOptionPane.showMessageDialog(null, exp.getMessage(), "×¢²áÊ§°Ü", JOptionPane.ERROR_MESSAGE);
		}
	}

}

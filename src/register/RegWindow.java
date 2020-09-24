package register;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import login.LoginWindow;
import register.service.RegService;

@SuppressWarnings("serial")
public class RegWindow extends JFrame {

	private JPanel panelTitle, panelUName, panelPassword, panelButtons;
	private JLabel labelTitle, labelUName, labelPassword, labelUNameTip, labelPasswordTip;
	private JTextField textUName;
	private JPasswordField textPassword;
	private JButton buttonRegister, buttonReset, buttonBack;

	private RegService regService;

	public RegWindow(LoginWindow loginWindow) {
		regService = new RegService();

		this.setTitle("图书管理系统");
		this.setLayout(new FlowLayout());

		panelTitle = new JPanel();
		labelTitle = new JLabel("注册图书管理系统");
		labelTitle.setFont(new Font("微软雅黑", Font.BOLD, 20));
		panelTitle.add(labelTitle);

		labelUName = new JLabel("用户名");
		textUName = new JTextField(15);
		textUName.addKeyListener(new RegUNameListener(this, regService));

		labelUNameTip = new JLabel("(5-20个字符,由大小写字母或数字组成)");
		panelUName = new JPanel();
		panelUName.add(labelUName);
		panelUName.add(textUName);
		panelUName.add(labelUNameTip);

		labelPassword = new JLabel("密　码");
		textPassword = new JPasswordField(15);
		textPassword.addKeyListener(new RegPwListener(this, regService));
		labelPasswordTip = new JLabel("(6-16个字符,由大小写字母或数字组成)");
		panelPassword = new JPanel();
		panelPassword.add(labelPassword);
		panelPassword.add(textPassword);
		panelPassword.add(labelPasswordTip);

		buttonRegister = new JButton("注册");
		buttonReset = new JButton("重置");
		buttonBack = new JButton("返回");

		RegWindow self = this;
		// 注册按钮功能
		buttonRegister.addActionListener(new RegBtnListener(this, regService));

		// 重置按钮功能
		buttonReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textPassword.setText("");
				textUName.setText("");
				textUName.requestFocus();
			}
		});

		// 返回按钮功能
		buttonBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				self.dispose();
			}
		});

		panelButtons = new JPanel();
		panelButtons.add(buttonRegister);
		panelButtons.add(buttonReset);
		panelButtons.add(buttonBack);

		this.add(panelTitle);
		this.add(panelUName);
		this.add(panelPassword);
		this.add(panelButtons);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new RegWindowListener(loginWindow));
		this.setResizable(false);
		this.setSize(525, 200);
		this.setLocationRelativeTo(null);
		loginWindow.setVisible(false);
		this.setVisible(true);

	}

	public String getUName() {
		return textUName.getText();
	}

	public String getPassword() {
		return String.valueOf(textPassword.getPassword());
	}

}

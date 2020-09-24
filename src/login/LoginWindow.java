package login;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import login.service.LoginService;
import register.RegWindow;

@SuppressWarnings("serial")
public class LoginWindow extends JFrame {

	private JPanel panelTitle, panelUName, panelPassword, panelButtons;
	private JLabel labelTitle, labelUName, labelPassword;
	private JTextField textUName;
	private JPasswordField textPassword;
	private JButton buttonLogin, buttonReset, buttonRegister;

	private LoginService loginService = null;

	public LoginWindow() {

		// connect to database
		try {
			loginService = new LoginService();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			JOptionPane.showMessageDialog(null, "抱歉，连接数据库失败，请联系管理员。\n按确定键退出系统。", "图书管理系统", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		this.setTitle("图书管理系统");
		FlowLayout layout = new FlowLayout();
		this.setLayout(layout);

		panelTitle = new JPanel();
		labelTitle = new JLabel("登录图书管理系统");
		labelTitle.setFont(new Font("微软雅黑", Font.BOLD, 20));
		panelTitle.add(labelTitle);

		labelUName = new JLabel("用户名");
		textUName = new JTextField(15);
		textUName.addKeyListener(new LoginUserNameFieldListener(this, loginService));
		panelUName = new JPanel();
		panelUName.add(labelUName);
		panelUName.add(textUName);

		labelPassword = new JLabel("密　码");
		textPassword = new JPasswordField(15);
		textPassword.addKeyListener(new LoginUserNameFieldListener(this, loginService));
		panelPassword = new JPanel();
		panelPassword.add(labelPassword);
		panelPassword.add(textPassword);

		buttonLogin = new JButton("登录");
		buttonReset = new JButton("重置");
		buttonRegister = new JButton("注册");

		// 登录按钮功能
		buttonLogin.addActionListener(new LoginBtnListener(this, loginService));

		// 重置按钮功能
		buttonReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textPassword.setText("");
				textUName.setText("");
				textUName.requestFocus();
			}
		});

		// 注册按钮功能
		final LoginWindow self = this;
		buttonRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				RegWindow rew = new RegWindow(self);
			}
		});

		panelButtons = new JPanel();
		panelButtons.add(buttonLogin);
		panelButtons.add(buttonReset);
		panelButtons.add(buttonRegister);

		this.add(panelTitle);
		this.add(panelUName);
		this.add(panelPassword);
		this.add(panelButtons);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new LoginWindowListener(loginService)); // 在窗口即将关闭时断开数据库连接
		this.setResizable(false);
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	public String getUName() {
		return textUName.getText();
	}

	public String getPassword() {
		return String.valueOf(textPassword.getPassword());
	}

	public void setUName(String username) {
		textUName.setText(username);
	}

	public void setPassword(String password) {
		textPassword.setText(password);
	}

}

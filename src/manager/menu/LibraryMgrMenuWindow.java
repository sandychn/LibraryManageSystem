package manager.menu;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import login.LoginWindow;

@SuppressWarnings("serial")
public class LibraryMgrMenuWindow extends JFrame {// 目录窗口
	private JLabel labelMenuTitle;
	private JPanel panelTitle, panelButtons;
	private JButton btnSelect, btnAddUser, btnLogout;

	@SuppressWarnings("unused")
	private LoginWindow loginWindow;

	public LibraryMgrMenuWindow(LoginWindow loginWindow) {
		this.loginWindow = loginWindow;
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(10);
		this.setLayout(flowLayout);
		this.setTitle("图书管理系统");
		this.setResizable(false);
		this.setSize(310, 240);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		panelTitle = new JPanel();
		panelButtons = new JPanel();

		labelMenuTitle = new JLabel("欢迎使用图书管理系统");
		labelMenuTitle.setFont(new Font("微软雅黑", Font.BOLD, 20));
		panelTitle.add(labelMenuTitle);

		btnSelect = new JButton("查询图书信息");
		btnAddUser = new JButton("新建用户");
		btnLogout = new JButton("退出系统");
		btnSelect.setFocusPainted(false);
		btnAddUser.setFocusPainted(false);
		btnLogout.setFocusPainted(false);

		GridLayout gridLayout = new GridLayout(3, 1);
		gridLayout.setVgap(15);
		panelButtons.setLayout(gridLayout);
		panelButtons.add(btnSelect);
		panelButtons.add(btnAddUser);
		panelButtons.add(btnLogout);

		btnSelect.addActionListener(new MgrSelectBookBtnListener(this));// 查询图书
		btnAddUser.addActionListener(new MgrAddUserBtnListener(this));// 添加用户
		btnLogout.addActionListener(new LibraryMgrLogoutBtnListener(this));// 退出

		this.add(panelTitle);
		this.add(panelButtons);
		this.addWindowListener(new LibraryMgrMenuWinListener(loginWindow, this));
		this.setVisible(true);

	}
}

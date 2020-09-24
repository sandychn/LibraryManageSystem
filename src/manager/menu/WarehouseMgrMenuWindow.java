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
public class WarehouseMgrMenuWindow extends JFrame {// 目录窗口
	private JLabel labelMenuTitle;
	private JPanel panelTitle, panelButtons;
	private JButton btnAdd, btnUpdate, btnAddClass, btnUpdateClass, btnLogout;

	@SuppressWarnings("unused")
	private LoginWindow loginWindow;

	public WarehouseMgrMenuWindow(LoginWindow loginWindow) {
		this.loginWindow = loginWindow;
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(10);
		this.setLayout(flowLayout);
		this.setTitle("图书管理系统");
		this.setResizable(false);
		this.setSize(310, 330);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		panelTitle = new JPanel();
		panelButtons = new JPanel();

		labelMenuTitle = new JLabel("欢迎使用图书管理系统");
		labelMenuTitle.setFont(new Font("微软雅黑", Font.BOLD, 20));
		panelTitle.add(labelMenuTitle);

		btnAdd = new JButton("增加图书");
		btnUpdate = new JButton("更改和删除图书");
		btnAddClass = new JButton("增加图书分类");
		btnUpdateClass = new JButton("修改图书分类名");
		btnLogout = new JButton("退出系统");
		btnAdd.setFocusPainted(false);
		btnUpdate.setFocusPainted(false);
		btnAddClass.setFocusPainted(false);
		btnUpdateClass.setFocusPainted(false);
		btnLogout.setFocusPainted(false);

		GridLayout gridLayout = new GridLayout(5, 1);
		gridLayout.setVgap(15);
		panelButtons.setLayout(gridLayout);
		panelButtons.add(btnAdd);
		panelButtons.add(btnUpdate);
		panelButtons.add(btnAddClass);
		panelButtons.add(btnUpdateClass);
		panelButtons.add(btnLogout);

		btnAdd.addActionListener(new MgrAddBookBtnListener(this));// 添加图书信息按钮
		btnUpdate.addActionListener(new MgrUpdateBookBtnListener(this));// 更改图书信息按钮
		btnAddClass.addActionListener(new MgrAddClassBtnListener(this));// 添加图书类名按钮
		btnUpdateClass.addActionListener(new MgrUpdateClassBtnListener(this));// 修改图书类名按钮
		btnLogout.addActionListener(new WarehouseMgrLogoutBtnListener(this));// 退出

		this.add(panelTitle);
		this.add(panelButtons);
		this.addWindowListener(new WarehouseMgrMenuWinListener(loginWindow, this));
		this.setVisible(true);

	}
}

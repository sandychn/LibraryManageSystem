package manager.book.update;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import common.util.SearchPanel;
import manager.book.selected.delete.service.MgrSelectedBookDeleteService;
import manager.menu.WarehouseMgrMenuWindow;

@SuppressWarnings("serial")
public class MgrUpdateBookWindow extends JFrame {// 查询/选择可修改图书窗口

	private JPanel titlePanel, northPanel, southPanel;
	private JLabel titleLabel;
	private SearchPanel searchPanel;
	private JButton updatingButton, deletingButton, backButton;

	private MgrSelectedBookDeleteService bookDelteoper;

	public MgrUpdateBookWindow(WarehouseMgrMenuWindow manMenuWindow) {
		this.bookDelteoper = new MgrSelectedBookDeleteService();
		this.setLayout(new BorderLayout());
		this.setTitle("图书管理系统");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new MgrUpdateBookWindowListener(manMenuWindow));

		// northPanel components
		northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		titlePanel = new JPanel();
		titleLabel = new JLabel("修改与删除图书");
		titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		titlePanel.add(titleLabel);
		northPanel.add(titlePanel, BorderLayout.NORTH);

		searchPanel = new SearchPanel();
		northPanel.add(searchPanel, BorderLayout.SOUTH);
		this.add(northPanel);

		// southPanel component
		southPanel = new JPanel();
		FlowLayout southFlowLayout = new FlowLayout();
		southFlowLayout.setHgap(30);
		southPanel.setLayout(southFlowLayout);
		updatingButton = new JButton("修改所选书籍");
		deletingButton = new JButton("删除所选书籍");
		backButton = new JButton("返回主菜单");
		southPanel.add(updatingButton);
		southPanel.add(deletingButton);
		southPanel.add(backButton);

		updatingButton.setPreferredSize(new Dimension(150, 30));
		updatingButton.addActionListener(new MgrUpdateBookBtnListener(searchPanel, this));
		deletingButton.setPreferredSize(new Dimension(150, 30));
		deletingButton.addActionListener(new MgrSelectedBookDeleteBtnListener(searchPanel, bookDelteoper));
		backButton.setPreferredSize(new Dimension(150, 30));
		backButton.addActionListener(new MgrUpdateBookBackBtnListener(this, manMenuWindow));

		this.add(southPanel, BorderLayout.SOUTH);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}

	public void setTable() {
		searchPanel.setTable();
	}
}

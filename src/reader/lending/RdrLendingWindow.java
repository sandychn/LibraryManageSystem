package reader.lending;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import common.util.SearchPanel;
import reader.lending.service.RdrLendingService;
import reader.menu.RdrMenuWindow;

@SuppressWarnings("serial")
public class RdrLendingWindow extends JFrame {

	private JPanel titlePanel, northPanel, southPanel;
	private JLabel titleLabel;
	private SearchPanel searchPanel;
	private JButton lendingButton, backButton;

	private RdrLendingService lendingOperation;

	public RdrLendingWindow(RdrMenuWindow rdrMenuWindow) {
		this.lendingOperation = new RdrLendingService();
		this.setLayout(new BorderLayout());
		this.setTitle("图书管理系统");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new RdrLendingWindowListener(rdrMenuWindow));

		// northPanel components
		northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		titlePanel = new JPanel();
		titleLabel = new JLabel("查询与借阅图书");
		titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		titlePanel.add(titleLabel);
		northPanel.add(titlePanel, BorderLayout.NORTH);

		searchPanel = new SearchPanel(false);
		northPanel.add(searchPanel, BorderLayout.SOUTH);
		this.add(northPanel, BorderLayout.NORTH);

		// southPanel component
		southPanel = new JPanel();
		FlowLayout southFlowLayout = new FlowLayout();
		southFlowLayout.setHgap(30);
		southPanel.setLayout(southFlowLayout);
		lendingButton = new JButton("借阅所选书籍");
		backButton = new JButton("返回主菜单");
		lendingButton.setPreferredSize(new Dimension(150, 30));
		lendingButton.addActionListener(new RdrLendingBtnListener(searchPanel, lendingOperation));
		backButton.setPreferredSize(new Dimension(150, 30));
		backButton.addActionListener(new RdrLendingBackBtnListener(this, rdrMenuWindow));
		southPanel.add(lendingButton);
		southPanel.add(backButton);
		this.add(southPanel, BorderLayout.SOUTH);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}

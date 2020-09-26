package manager.book.select;

import common.util.SearchPanel;
import manager.menu.LibraryMgrMenuWindow;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class MgrSelectBookWindow extends JFrame {// 查询/选择可修改图书窗口

    private JPanel titlePanel, northPanel, southPanel;
    private JLabel titleLabel;
    private SearchPanel searchPanel;
    private JButton backButton;

    public MgrSelectBookWindow(LibraryMgrMenuWindow manMenuWindow) {
        this.setLayout(new BorderLayout());
        this.setTitle("图书管理系统");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new MgrSelectBookWindowListener(manMenuWindow));

        // northPanel components
        northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        titlePanel = new JPanel();
        titleLabel = new JLabel("修改与删除图书");
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        titlePanel.add(titleLabel);
        northPanel.add(titlePanel, BorderLayout.NORTH);

        searchPanel = new SearchPanel(false);
        northPanel.add(searchPanel, BorderLayout.SOUTH);
        this.add(northPanel);

        // southPanel component
        southPanel = new JPanel();
        FlowLayout southFlowLayout = new FlowLayout();
        southFlowLayout.setHgap(30);
        southPanel.setLayout(southFlowLayout);
        backButton = new JButton("返回主菜单");
        southPanel.add(backButton);

        backButton.setPreferredSize(new Dimension(150, 30));
        backButton.addActionListener(new MgrSelectBookBackBtnListener(this, manMenuWindow));

        this.add(southPanel, BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }

}

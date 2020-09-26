package manager.record;

import common.util.SearchPanel;
import manager.book.update.MgrUpdateBookWindowListener;
import manager.menu.WarehouseMgrMenuWindow;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class MgrBookListWindow extends JFrame { // 选择书籍窗口(双击以查询特定书籍的借阅记录)

    private JPanel titlePanel, northPanel, southPanel;
    private JLabel titleLabel;
    private SearchPanel searchPanel;
    private JButton backButton;

    public MgrBookListWindow(WarehouseMgrMenuWindow manMenuWindow) {
        this.setLayout(new BorderLayout());
        this.setTitle("图书管理系统");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new MgrUpdateBookWindowListener(manMenuWindow));

        // northPanel components
        northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        titlePanel = new JPanel();
        titleLabel = new JLabel("双击书籍以查阅对应书籍借阅记录");
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        titlePanel.add(titleLabel);
        northPanel.add(titlePanel, BorderLayout.NORTH);

        searchPanel = new SearchPanel(true);
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
        backButton.addActionListener(e -> {
            manMenuWindow.setLocationRelativeTo(null);
            manMenuWindow.setVisible(true);
            this.dispose();
        });

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

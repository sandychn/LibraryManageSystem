package reader.menu;

import common.util.CurrentUser;
import login.LoginWindow;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class RdrMenuWindow extends JFrame {

    private JLabel menuTitleLabel, footerLabel;
    private JPanel titlePanel, buttonsPanel, footerPanel;
    private JButton lendingBtn, recordBtn, logoutBtn;

    public RdrMenuWindow(LoginWindow loginWindow) {
        this.setLayout(new BorderLayout());
        this.setTitle("图书管理系统");
        this.setSize(300, 230);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new RdrMenuWindowListener(loginWindow, this));

        titlePanel = new JPanel();
        menuTitleLabel = new JLabel("欢迎使用图书管理系统");
        menuTitleLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        titlePanel.add(menuTitleLabel);

        buttonsPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(10);
        buttonsPanel.setLayout(flowLayout);

        lendingBtn = new JButton("查询与借阅图书");
        recordBtn = new JButton("查询借阅记录 / 续借与归还");
        logoutBtn = new JButton("退出系统");
        lendingBtn.setFocusPainted(false);
        recordBtn.setFocusPainted(false);
        logoutBtn.setFocusPainted(false);
        lendingBtn.setPreferredSize(new Dimension(230, 30));
        recordBtn.setPreferredSize(new Dimension(230, 30));
        logoutBtn.setPreferredSize(new Dimension(230, 30));

        buttonsPanel.add(lendingBtn);
        buttonsPanel.add(recordBtn);
        buttonsPanel.add(logoutBtn);

        lendingBtn.addActionListener(new RdrLendingBtnListener(this));
        recordBtn.addActionListener(new RdrRecordBtnListener(this));
        logoutBtn.addActionListener(new RdrLogoutBtnListener(this));

        footerPanel = new JPanel();
        footerLabel = new JLabel("<html>当前以  <b>" + CurrentUser.getCurrentUser().getUsername() + "</b>  用户登录</html>");
        footerPanel.add(footerLabel);

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(buttonsPanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);

        this.setVisible(true);

    }

}

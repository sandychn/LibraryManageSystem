package manager.menu;

import login.LoginWindow;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class BackgroundMgrMenuWindow extends JFrame {// 目录窗口
    private JLabel labelMenuTitle;
    private JPanel panelTitle, panelButtons;
    private JButton btnUpdateUser, btnLogout;

    @SuppressWarnings("unused")
    private LoginWindow loginWindow;

    public BackgroundMgrMenuWindow(LoginWindow loginWindow) {
        this.loginWindow = loginWindow;
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(10);
        this.setLayout(flowLayout);
        this.setTitle("图书管理系统");
        this.setResizable(false);
        this.setSize(310, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        panelTitle = new JPanel();
        panelButtons = new JPanel();

        labelMenuTitle = new JLabel("欢迎使用图书管理系统");
        labelMenuTitle.setFont(new Font("微软雅黑", Font.BOLD, 20));
        panelTitle.add(labelMenuTitle);

        btnUpdateUser = new JButton("修改用户身份");
        btnLogout = new JButton("退出系统");
        btnUpdateUser.setFocusPainted(false);
        btnLogout.setFocusPainted(false);

        GridLayout gridLayout = new GridLayout(2, 1);
        gridLayout.setVgap(15);
        panelButtons.setLayout(gridLayout);
        panelButtons.add(btnUpdateUser);
        panelButtons.add(btnLogout);

        btnUpdateUser.addActionListener(new MgrUpdateUserBtnListener(this));// 修改用户身份
        btnLogout.addActionListener(new BackgroundMgrLogoutBtnListener(this));// 退出

        this.add(panelTitle);
        this.add(panelButtons);
        this.addWindowListener(new BackgroundMgrMenuWinListener(loginWindow, this));
        this.setVisible(true);

    }
}

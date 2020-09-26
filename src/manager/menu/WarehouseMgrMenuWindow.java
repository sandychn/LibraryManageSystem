package manager.menu;

import login.LoginWindow;
import manager.record.MgrBookListWindow;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class WarehouseMgrMenuWindow extends JFrame { // 目录窗口
    private JLabel labelMenuTitle;
    private JPanel panelTitle;
    private JPanel panelButtons;
    private JButton btnAdd;                  // 增加图书
    private JButton btnUpdate;              // 更改和删除图书
    private JButton btnSelectLendingRecord;   // 查询借阅记录
    private JButton btnAddClass;              // 增加图书分类
    private JButton btnUpdateClass;              // 修改图书分类名
    private JButton btnLogout;              // 登出

    @SuppressWarnings("unused")
    private LoginWindow loginWindow;

    public WarehouseMgrMenuWindow(LoginWindow loginWindow) {
        this.loginWindow = loginWindow;
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(10);
        this.setLayout(flowLayout);
        this.setTitle("图书管理系统");
        this.setResizable(false);
        this.setSize(310, 380);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        panelTitle = new JPanel();
        panelButtons = new JPanel();

        labelMenuTitle = new JLabel("欢迎使用图书管理系统");
        labelMenuTitle.setFont(new Font("微软雅黑", Font.BOLD, 20));
        panelTitle.add(labelMenuTitle);

        btnAdd = new JButton("增加图书");
        btnUpdate = new JButton("更改和删除图书");
        btnSelectLendingRecord = new JButton("查询书籍借阅记录");
        btnAddClass = new JButton("增加图书分类");
        btnUpdateClass = new JButton("修改图书分类名");
        btnLogout = new JButton("退出系统");

        btnAdd.setFocusPainted(false);
        btnUpdate.setFocusPainted(false);
        btnSelectLendingRecord.setFocusPainted(false);
        btnAddClass.setFocusPainted(false);
        btnUpdateClass.setFocusPainted(false);
        btnLogout.setFocusPainted(false);

        GridLayout gridLayout = new GridLayout(6, 1);
        gridLayout.setVgap(15);
        panelButtons.setLayout(gridLayout);
        panelButtons.add(btnAdd);
        panelButtons.add(btnUpdate);
        panelButtons.add(btnSelectLendingRecord);
        panelButtons.add(btnAddClass);
        panelButtons.add(btnUpdateClass);
        panelButtons.add(btnLogout);

        btnAdd.addActionListener(new MgrAddBookBtnListener(this));
        btnUpdate.addActionListener(new MgrUpdateBookBtnListener(this));
        btnSelectLendingRecord.addActionListener(e -> new MgrBookListWindow(this));
        btnAddClass.addActionListener(new MgrAddClassBtnListener(this));
        btnUpdateClass.addActionListener(new MgrUpdateClassBtnListener(this));
        btnLogout.addActionListener(new WarehouseMgrLogoutBtnListener(this));

        this.add(panelTitle);
        this.add(panelButtons);
        this.addWindowListener(new WarehouseMgrMenuWinListener(loginWindow, this));
        this.setVisible(true);

    }
}

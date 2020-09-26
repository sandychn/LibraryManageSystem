package login;

import common.constant.UserConstant;
import entity.User;
import login.service.LoginService;
import manager.menu.BackgroundMgrMenuWindow;
import manager.menu.LibraryMgrMenuWindow;
import manager.menu.WarehouseMgrMenuWindow;
import reader.menu.RdrMenuWindow;
import register.RegWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

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
        textUName.addKeyListener(new LoginUsernameFieldListener(this, loginService));
        panelUName = new JPanel();
        panelUName.add(labelUName);
        panelUName.add(textUName);

        labelPassword = new JLabel("密　码");
        textPassword = new JPasswordField(15);
        textPassword.addKeyListener(new LoginPasswordFieldListener(this, loginService));
        panelPassword = new JPanel();
        panelPassword.add(labelPassword);
        panelPassword.add(textPassword);

        buttonLogin = new JButton("登录");
        buttonReset = new JButton("重置");
        buttonRegister = new JButton("注册");

        // 登录按钮功能
        buttonLogin.addActionListener(new LoginBtnListener(this, loginService));

        // 重置按钮功能
        buttonReset.addActionListener(e -> {
            textPassword.setText("");
            textUName.setText("");
            textUName.requestFocus();
        });

        // 注册按钮功能
        buttonRegister.addActionListener(e -> {
            new RegWindow(this);
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

        // 在窗口即将关闭时断开数据库连接
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    loginService.disconnectDatabase();
                } catch (SQLException exp) {
                    exp.printStackTrace();
                    System.err.println("断开数据库失败: " + exp.getMessage());
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
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

    public void launchNextWindow(User user) {
        switch (user.getIdentity()) {
            case UserConstant.IDENTITY_READER:
                new RdrMenuWindow(this);
                this.setVisible(false);
                break;
            case UserConstant.IDENTITY_USER_ADMIN:
                new LibraryMgrMenuWindow(this);
                this.setVisible(false);
                break;
            case UserConstant.IDENTITY_STORE_ADMIN:
                new WarehouseMgrMenuWindow(this);
                this.setVisible(false);
                break;
            case UserConstant.IDENTITY_SUPER_USER_ADMIN:
                new BackgroundMgrMenuWindow(this);
                this.setVisible(false);
                break;
            default:
                JOptionPane.showMessageDialog(null, "未知用户身份",
                        "登录时遇到错误，请联系管理员", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

}

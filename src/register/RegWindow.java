package register;

import common.constant.UserConstant;
import common.util.Tools;
import entity.User;
import login.LoginWindow;
import register.service.RegException;
import register.service.RegService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

@SuppressWarnings("serial")
public class RegWindow extends JFrame {

    private final JPanel panelTitle;
    private final JPanel panelUName;
    private final JPanel panelPassword;
    private final JPanel panelButtons;
    private final JLabel labelTitle;
    private final JLabel labelUName;
    private final JLabel labelPassword;
    private final JLabel labelUNameTip;
    private final JLabel labelPasswordTip;
    private final JTextField textUName;
    private final JPasswordField textPassword;
    private final JButton buttonRegister;
    private final JButton buttonReset;
    private final JButton buttonBack;
    private final RegService regService;
    private final LoginWindow loginWindow;

    public RegWindow(LoginWindow loginWindow) {
        this.loginWindow = loginWindow;
        regService = new RegService();

        this.setTitle("图书管理系统");
        this.setLayout(new FlowLayout());

        panelTitle = new JPanel();
        labelTitle = new JLabel("注册图书管理系统");
        labelTitle.setFont(new Font("微软雅黑", Font.BOLD, 20));
        panelTitle.add(labelTitle);

        labelUName = new JLabel("用户名");
        textUName = new JTextField(15);

        textUName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                String username = RegWindow.this.getUName(), password = RegWindow.this.getPassword();
                int code = e.getKeyChar();
                if (code == KeyEvent.VK_BACK_SPACE) {
                    e.isActionKey();
                } else if (code == KeyEvent.VK_ENTER) {
                    tryRegister();
                } else if (username.length() >= UserConstant.USERNAME_MAXLEN) { // 用户名长度限制
                    e.consume();
                } else if (!Tools.isLegalCharacter(code)) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        labelUNameTip = new JLabel("(5-20个字符,由大小写字母或数字组成)");
        panelUName = new JPanel();
        panelUName.add(labelUName);
        panelUName.add(textUName);
        panelUName.add(labelUNameTip);

        labelPassword = new JLabel("密　码");
        textPassword = new JPasswordField(15);

        textPassword.addKeyListener(new KeyListener() {
                                        @Override
                                        public void keyTyped(KeyEvent e) {
                                            String username = RegWindow.this.getUName(), password = RegWindow.this.getPassword();
                                            int code = e.getKeyChar();
                                            if (code == KeyEvent.VK_BACK_SPACE) {
                                                e.isActionKey();
                                            } else if (code == KeyEvent.VK_ENTER) {
                                                tryRegister();
                                            } else if (password.length() >= UserConstant.PASSWORD_MAXLEN) { // 密码长度限制
                                                e.consume();
                                            } else if (!Tools.isLegalCharacter(code)) {
                                                e.consume();
                                            }
                                        }

                                        @Override
                                        public void keyPressed(KeyEvent e) {

                                        }

                                        @Override
                                        public void keyReleased(KeyEvent e) {

                                        }
                                    }
        );

        labelPasswordTip = new JLabel("(6-16个字符,由大小写字母或数字组成)");
        panelPassword = new JPanel();
        panelPassword.add(labelPassword);
        panelPassword.add(textPassword);
        panelPassword.add(labelPasswordTip);

        buttonRegister = new JButton("注册");
        buttonReset = new JButton("重置");
        buttonBack = new JButton("返回");

        // 注册按钮功能
        buttonRegister.addActionListener(e -> tryRegister());

        // 重置按钮功能
        buttonReset.addActionListener(e -> {
            textPassword.setText("");
            textUName.setText("");
            textUName.requestFocus();
        });

        // 返回按钮功能
        buttonBack.addActionListener(e -> this.dispose());

        panelButtons = new JPanel();
        panelButtons.add(buttonRegister);
        panelButtons.add(buttonReset);
        panelButtons.add(buttonBack);

        this.add(panelTitle);
        this.add(panelUName);
        this.add(panelPassword);
        this.add(panelButtons);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            // will be called when calling JFrame.dispose(), but windowClosing will NOT
            @Override
            public void windowClosed(WindowEvent e) {
                loginWindow.setUName("");
                loginWindow.setPassword("");
                loginWindow.setLocationRelativeTo(null);
                loginWindow.setVisible(true);
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
        this.setSize(525, 200);
        this.setLocationRelativeTo(null);
        loginWindow.setVisible(false);
        this.setVisible(true);
    }

    public String getUName() {
        return textUName.getText();
    }

    public String getPassword() {
        return String.valueOf(textPassword.getPassword());
    }

    public void tryRegister() {
        String username = this.getUName(), password = this.getPassword();
        try {
            regService.tryRegister(new User(username, password, UserConstant.IDENTITY_READER));
            JOptionPane.showMessageDialog(null, "注册成功", "注册成功", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            loginWindow.setVisible(true);
        } catch (RegException exp) {
            JOptionPane.showMessageDialog(null, exp.getMessage(), "注册失败", JOptionPane.ERROR_MESSAGE);
        }
    }
}

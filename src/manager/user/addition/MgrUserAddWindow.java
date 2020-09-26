package manager.user.addition;

import common.constant.UserConstant;
import manager.menu.LibraryMgrMenuWindow;
import manager.user.addition.service.MgrUserAddService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class MgrUserAddWindow extends JFrame {// 新建用户窗口

    private JPanel panelTitle, panelUName, panelPassword, panelUid, panelButtons;
    private JLabel labelTitle, labelUName, labelPassword, labelUid, labelUNameTip, labelPasswordTip;
    private JTextField textUName;
    private JPasswordField textPassword;
    private JButton buttonRegister, buttonReset, buttonBack;
    private JComboBox<String> comBox;

    private MgrUserAddService userAddingOperation;

    public MgrUserAddWindow(LibraryMgrMenuWindow manMenuWindow) {
        userAddingOperation = new MgrUserAddService();

        this.setTitle("图书管理系统");
        this.setLayout(new GridLayout(5, 1));

        panelTitle = new JPanel();
        labelTitle = new JLabel("新建用户");
        labelTitle.setFont(new Font("微软雅黑", Font.BOLD, 20));
        panelTitle.add(labelTitle);

        labelUName = new JLabel("用户名");
        textUName = new JTextField(15);
        textUName.addKeyListener(new MgrUserNameTextFieldListener(this, userAddingOperation));

        labelUNameTip = new JLabel("(5-20个字符,由大小写字母或数字组成)");
        panelUName = new JPanel();
        panelUName.add(labelUName);
        panelUName.add(textUName);
        panelUName.add(labelUNameTip);

        labelPassword = new JLabel("密　码");
        textPassword = new JPasswordField(15);
        textUName.addKeyListener(new MgrUserPwFieldListener(this, userAddingOperation));
        textPassword.addKeyListener(new MgrUserPwFieldListener(this, userAddingOperation));
        labelPasswordTip = new JLabel("(6-16个字符,由大小写字母或数字组成)");
        panelPassword = new JPanel();
        panelPassword.add(labelPassword);
        panelPassword.add(textPassword);
        panelPassword.add(labelPasswordTip);

        panelUid = new JPanel();
        labelUid = new JLabel("身   份");
        comBox = new JComboBox<String>();
        comBox.setPreferredSize(new Dimension(200, 30));
        comBox.addItem(String.valueOf("学生")); // User.IDENTITY_READER
        comBox.addItem(String.valueOf("管理员")); // User.IDENTITY_ADMIN
        comBox.setSelectedIndex(UserConstant.IDENTITY_READER - 1);
        panelUid = new JPanel();
        panelUid.add(labelUid);
        panelUid.add(comBox);

        buttonRegister = new JButton("新建");
        buttonReset = new JButton("重置");
        buttonBack = new JButton("返回");

        // 新建按钮功能
        buttonRegister.addActionListener(new MgrUserAddBtnListener(this, userAddingOperation));

        // 重置按钮功能
        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textPassword.setText("");
                textUName.setText("");
                textUName.requestFocus();
            }
        });

        // 返回按钮功能
        buttonBack.addActionListener(new MgrUserAddBackBtnListener(this, manMenuWindow));

        panelButtons = new JPanel();
        panelButtons.add(buttonRegister);
        panelButtons.add(buttonReset);
        panelButtons.add(buttonBack);

        this.add(panelTitle);
        this.add(panelUName);
        this.add(panelPassword);
        this.add(panelUid);
        this.add(panelButtons);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new MgrUserAddWindowListener(manMenuWindow));
        this.setResizable(false);
        this.setSize(525, 250);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);

    }

    public String getUName() {// 获取文本框用户名
        return textUName.getText();
    }

    public String getPassword() {// 获取密码框的密码
        return String.valueOf(textPassword.getPassword());
    }

    public String getUId() {// 获取用户身份
        return String.valueOf(comBox.getSelectedIndex() + 1);
    }
}

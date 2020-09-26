package manager.bookclass.addition;

import manager.bookclass.addition.service.MgrAddBookClassException;
import manager.bookclass.addition.service.MgrAddBookClassService;
import manager.menu.WarehouseMgrMenuWindow;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class MgrAddBookClassWindow extends JFrame {// 添加图书分类窗口

    private JLabel labelTitle;
    private Box baseBox, boxLabel, boxText;
    private JTextField idText, nameText;
    private JButton addButton, backButton;
    private JPanel panelButton;
    private MgrAddBookClassService bookClassAdditionOper;

    public MgrAddBookClassWindow(WarehouseMgrMenuWindow manMenuWindow) {
        labelTitle = new JLabel("添加图书类名");
        labelTitle.setFont(new Font("微软雅黑", Font.BOLD, 20));
        add(labelTitle);
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.addWindowListener(new MgrAddBookClassWindowListener(manMenuWindow));

        boxLabel = Box.createVerticalBox();
        boxLabel.add(new JLabel("图书类名号:"));
        boxLabel.add(Box.createVerticalStrut(12));
        boxLabel.add(new JLabel("图书类名:"));
        boxLabel.add(Box.createVerticalStrut(12));
        boxText = Box.createVerticalBox();
        boxText.add(idText = new JTextField(15));
        boxText.add(Box.createVerticalStrut(10));
        idText.setEnabled(false);
        boxText.add(nameText = new JTextField(15));
        boxText.add(Box.createVerticalStrut(10));
        baseBox = Box.createHorizontalBox();
        baseBox.add(boxLabel);
        baseBox.add(Box.createHorizontalStrut(10));
        baseBox.add(boxText);
        add(baseBox);
        addButton = new JButton("添加");
        backButton = new JButton("返回主菜单");
        panelButton = new JPanel();
        FlowLayout layout1 = new FlowLayout();
        layout1.setHgap(20);
        panelButton.setLayout(layout1);
        panelButton.add(addButton);
        panelButton.add(backButton);
        add(panelButton);
        bookClassAdditionOper = new MgrAddBookClassService();
        try {
            idText.setText(String.valueOf(bookClassAdditionOper.getClassCount() + 1));
        } catch (MgrAddBookClassException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "连接数据库失败", JOptionPane.ERROR_MESSAGE);
        }
        this.setTitle("图书管理系统");
        this.setSize(350, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        addButton.addActionListener(new MgrAddBookClassBtnListener(this, bookClassAdditionOper));
        backButton.addActionListener(new MgrAddBookClassBackBtnListener(this, manMenuWindow));

    }

    public String getClassName() {
        return nameText.getText();
    }

    public void setClassIdText(int newClassId) {// 设置图书类名编号的文本框
        idText.setText(String.valueOf(newClassId));
    }

    public String getClassIdText() {
        return idText.getText();
    }

    public void setClassNameText() {// 设置图书类名的文本框
        nameText.setText("");
    }
}

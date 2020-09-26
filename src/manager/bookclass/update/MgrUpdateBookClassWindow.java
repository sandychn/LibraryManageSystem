package manager.bookclass.update;

import manager.bookclass.update.service.MgrUpdateBookClassException;
import manager.bookclass.update.service.MgrUpdateBookClassService;
import manager.menu.WarehouseMgrMenuWindow;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class MgrUpdateBookClassWindow extends JFrame {// 修改图书分类的窗口
    private JLabel labelTitle;
    private Box baseBox, boxLabel, boxText;
    private JTextField nameText;
    private JButton addButton, backButton;
    private JPanel panelButton;
    private JComboBox<String> comBox;
    private MgrUpdateBookClassService bookClassUpdatingOper;
    private String[] bookClassStrings;

    public MgrUpdateBookClassWindow(WarehouseMgrMenuWindow manMenuWindow) {
        labelTitle = new JLabel("修改图书类名");
        labelTitle.setFont(new Font("微软雅黑", Font.BOLD, 20));
        add(labelTitle);
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.addWindowListener(new MgrUpdateBookClassWindowListener(manMenuWindow));

        boxLabel = Box.createVerticalBox();
        boxLabel.add(new JLabel("图书类名号(类名):"));
        boxLabel.add(Box.createVerticalStrut(12));
        boxLabel.add(new JLabel("修改后的图书类名:"));
        boxLabel.add(Box.createVerticalStrut(12));
        boxText = Box.createVerticalBox();
        comBox = new JComboBox<String>();
        bookClassUpdatingOper = new MgrUpdateBookClassService(this);
        try {
            bookClassStrings = bookClassUpdatingOper.getBookClassStrings();
            for (String str : bookClassStrings) {
                comBox.addItem(str);
            }

        } catch (MgrUpdateBookClassException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "连接数据库失败", JOptionPane.ERROR_MESSAGE);
        }
        boxText.add(comBox);
        boxText.add(Box.createVerticalStrut(10));
        boxText.add(nameText = new JTextField(15));
        boxText.add(Box.createVerticalStrut(10));
        baseBox = Box.createHorizontalBox();
        baseBox.add(boxLabel);
        baseBox.add(Box.createHorizontalStrut(10));
        baseBox.add(boxText);
        add(baseBox);
        addButton = new JButton("修改");
        backButton = new JButton("返回主菜单");
        panelButton = new JPanel();
        FlowLayout layout1 = new FlowLayout();
        layout1.setHgap(20);
        panelButton.setLayout(layout1);
        panelButton.add(addButton);
        panelButton.add(backButton);
        add(panelButton);
        this.setTitle("图书管理系统");
        this.setSize(350, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        addButton.addActionListener(new MgrUpdateBookClassBtnListener(this, bookClassUpdatingOper));
        backButton.addActionListener(new MgrUpdateBookClassBackBtnListener(this, manMenuWindow));

    }

    public String getNameText() {
        return nameText.getText();
    }

    public int getSelectedClassId() {
        return comBox.getSelectedIndex() + 1;
    }

    public void updateComboBoxItemAt(String text, int index) {// 刷新修改的图书分类
        comBox.removeItemAt(index);
        comBox.insertItemAt(text, index);
        comBox.setSelectedIndex(index);
    }
}

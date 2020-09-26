package manager.book.selected.update;

import entity.Book;
import manager.book.selected.update.service.MgrSelectedBookUpdateException;
import manager.book.selected.update.service.MgrSelectedBookUpdateService;
import manager.book.update.MgrUpdateBookWindow;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class MgrSelectedBookUpdateWindow extends JFrame {// 修改图书窗口

    private static final String REGEX = "[0-9]+";// 0~9

    private JLabel labelTitle;
    private Box baseBox, boxLabel, boxText;
    private JTextField isbnText, nameText, authorText, pressText, countText, sumText;
    private JButton updateButton, backButton;
    private JComboBox<String> comBox;
    private JPanel panelButton;
    private MgrSelectedBookUpdateService bookUpdateOper;
    private String[] bookClassStrings;

    public MgrSelectedBookUpdateWindow(MgrUpdateBookWindow updatingWindow, Book book) {
        labelTitle = new JLabel("修改图书");
        labelTitle.setFont(new Font("微软雅黑", Font.BOLD, 20));
        add(labelTitle);
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.addWindowListener(new MgrSelectedBookUpdateWindowListener(updatingWindow));

        boxLabel = Box.createVerticalBox();
        boxLabel.add(new JLabel("图书编号:"));
        boxLabel.add(Box.createVerticalStrut(12));
        boxLabel.add(new JLabel("书名:"));
        boxLabel.add(Box.createVerticalStrut(12));
        boxLabel.add(new JLabel("作者:"));
        boxLabel.add(Box.createVerticalStrut(12));
        boxLabel.add(new JLabel("出版社:"));
        boxLabel.add(Box.createVerticalStrut(15));
        boxLabel.add(new JLabel("图书剩余数量:"));
        boxLabel.add(Box.createVerticalStrut(12));
        boxLabel.add(new JLabel("图书总数量:"));
        boxLabel.add(Box.createVerticalStrut(10));
        boxLabel.add(new JLabel("图书分类名:"));
        boxLabel.add(Box.createVerticalStrut(10));
        boxText = Box.createVerticalBox();
        boxText.add(isbnText = new JTextField(15));
        boxText.add(Box.createVerticalStrut(8));
        boxText.add(nameText = new JTextField(15));
        boxText.add(Box.createVerticalStrut(8));
        boxText.add(authorText = new JTextField(15));
        boxText.add(Box.createVerticalStrut(8));
        boxText.add(pressText = new JTextField(15));
        boxText.add(Box.createVerticalStrut(8));
        boxText.add(countText = new JTextField(15));
        boxText.add(Box.createVerticalStrut(8));
        boxText.add(sumText = new JTextField(15));
        boxText.add(Box.createVerticalStrut(8));
        comBox = new JComboBox<String>();
        bookUpdateOper = new MgrSelectedBookUpdateService();
        try {
            bookClassStrings = bookUpdateOper.getBookClass();
            for (String str : bookClassStrings) {
                comBox.addItem(str);
            }
        } catch (MgrSelectedBookUpdateException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage(), "连接数据库失败", JOptionPane.ERROR_MESSAGE);
        }
        boxText.add(comBox);
        baseBox = Box.createHorizontalBox();
        baseBox.add(boxLabel);
        baseBox.add(Box.createHorizontalStrut(10));
        baseBox.add(boxText);
        add(baseBox);
        updateButton = new JButton("修改");
        backButton = new JButton("返回上一级");
        panelButton = new JPanel();
        FlowLayout layout1 = new FlowLayout();
        layout1.setHgap(20);
        panelButton.setLayout(layout1);
        panelButton.add(updateButton);
        panelButton.add(backButton);
        add(panelButton);
        this.setTitle("图书管理系统");
        this.setSize(350, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        isbnText.setEnabled(false);
        countText.setEnabled(false);

        // { "书名", "作者", "出版社", "ISBN", "分类", "总数", "剩余数" };
        isbnText.setText(book.getIsbn());
        nameText.setText(book.getName());
        authorText.setText(book.getAuthor());
        pressText.setText(book.getPress());
        countText.setText(String.valueOf(book.getCount()));
        sumText.setText(String.valueOf(book.getSum()));
        comBox.setSelectedItem(book.getClassId());

        updateButton.addActionListener(new MgrSelectedBookUpdateBtnListener(this, bookUpdateOper, book));
        backButton.addActionListener(new MgrSelectedBookUpdateBackBtnListener(this, updatingWindow));
        updatingWindow.setVisible(false);
    }

    public Book getWindowBook() {
        if (!sumText.getText().matches(REGEX) || !sumText.getText().matches(REGEX)) {
            JOptionPane.showMessageDialog(null, "请在图书总数量栏输入数字", "错误", JOptionPane.ERROR_MESSAGE);
            return new Book(isbnText.getText(), nameText.getText(), authorText.getText(), pressText.getText(),
                    comBox.getSelectedIndex(), -1, -1);
        } else {
            return new Book(isbnText.getText(), nameText.getText(), authorText.getText(), pressText.getText(),
                    comBox.getSelectedIndex() + 1, Integer.parseInt(sumText.getText()),
                    Integer.parseInt(sumText.getText()));
        }
    }
}

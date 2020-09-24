package manager.book.addition;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import common.constant.BookConstant;
import entity.Book;
import manager.book.addition.service.MgrAddBookException;
import manager.book.addition.service.MgrAddBookService;
import manager.menu.WarehouseMgrMenuWindow;

@SuppressWarnings("serial")
public class MgrAddBookWindow extends JFrame {// 添加书本窗口
	private JLabel labelTitle;
	private Box baseBox, boxLabel, boxText;
	private JTextField isbnText, nameText, authorText, pressText, sumText;
	private JButton addButton, backButton;
	private JComboBox<String> comBox;
	private JPanel panelButton;
	private String[] bookClassStrings;
	private MgrAddBookService bookAdditionOper;

	public MgrAddBookWindow(WarehouseMgrMenuWindow manMenuWindow) {
		labelTitle = new JLabel("添加图书");
		labelTitle.setFont(new Font("微软雅黑", Font.BOLD, 20));
		add(labelTitle);
		FlowLayout layout = new FlowLayout();
		this.setLayout(layout);
		this.addWindowListener(new MgrAddBookWindowListener(manMenuWindow));

		boxLabel = Box.createVerticalBox();
		boxLabel.add(new JLabel("图书编号:"));
		boxLabel.add(Box.createVerticalStrut(12));
		boxLabel.add(new JLabel("书名:"));
		boxLabel.add(Box.createVerticalStrut(12));
		boxLabel.add(new JLabel("作者:"));
		boxLabel.add(Box.createVerticalStrut(12));
		boxLabel.add(new JLabel("出版社:"));
		boxLabel.add(Box.createVerticalStrut(15));
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
		boxText.add(sumText = new JTextField(15));
		boxText.add(Box.createVerticalStrut(8));
		comBox = new JComboBox<String>();
		comBox.addItem("请选择");
		bookAdditionOper = new MgrAddBookService();
		try {
			bookClassStrings = bookAdditionOper.getBookClass();
			for (String str : bookClassStrings) {
				comBox.addItem(str);
			}
		} catch (MgrAddBookException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "连接数据库失败", JOptionPane.ERROR_MESSAGE);
		}

		boxText.add(comBox);
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
		this.setTitle("图书管理系统");
		this.setSize(350, 350);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		sumText.setText("0");

		addButton.addActionListener(new MgrAddBookBtnListener(this, bookAdditionOper));
		backButton.addActionListener(new MgrAddBookBackBtnListener(this, manMenuWindow));
		isbnText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int code = e.getKeyChar();
				if (code == KeyEvent.VK_BACK_SPACE) {
					e.isActionKey();
				} else if (isbnText.getText().length() >= BookConstant.ISBN_LEN) { // 图书编号长度限制
					e.consume();
				}
			}
		});
	}

	public Book getWindowBook() {
		try {
			int count = Integer.parseInt(sumText.getText());
			int sum = Integer.parseInt(sumText.getText());
			return new Book(isbnText.getText(), nameText.getText(), authorText.getText(), pressText.getText(),
					comBox.getSelectedIndex(), count, sum);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "请在图书总数量栏输入数字", "错误", JOptionPane.ERROR_MESSAGE);
			return new Book(isbnText.getText(), nameText.getText(), authorText.getText(), pressText.getText(),
					comBox.getSelectedIndex(), -1, -1);
		}
	}
}

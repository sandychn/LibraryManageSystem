package manager.user.update;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import manager.menu.BackgroundMgrMenuWindow;
import manager.user.update.service.MgrUserUpdateService;

@SuppressWarnings("serial")
public class MgrUserUpdateWindow extends JFrame {// 修改用户身份窗口
	private JLabel labelTitle;
	private Box baseBox, boxLabel, boxText;
	private JTextField uNameText, uIdText;
	private JButton selectButton, updateButton, backButton;
	private JPanel panelButton;
	private JComboBox<String> comBox;
	private MgrUserUpdateService mgrUserUpdateService;

	public MgrUserUpdateWindow(BackgroundMgrMenuWindow manMenuWindow) {
		mgrUserUpdateService = new MgrUserUpdateService();
		labelTitle = new JLabel("修改用户身份");
		labelTitle.setFont(new Font("微软雅黑", Font.BOLD, 20));
		add(labelTitle);
		FlowLayout layout = new FlowLayout();
		this.setLayout(layout);
		this.addWindowListener(new MgrUserUpdateWindowListener(manMenuWindow));
		selectButton = new JButton("查询");
		boxLabel = Box.createVerticalBox();
		boxLabel.add(new JLabel("用户名:"));
		boxLabel.add(Box.createVerticalStrut(12));
		boxLabel.add(new JLabel("当前用户身份:"));
		boxLabel.add(Box.createVerticalStrut(12));
		boxLabel.add(new JLabel("修改用户身份:"));
		boxLabel.add(Box.createVerticalStrut(12));
		boxText = Box.createVerticalBox();
		boxText.add(uNameText = new JTextField(15));
		boxText.add(Box.createVerticalStrut(10));
		boxText.add(uIdText = new JTextField(15));
		uIdText.setEnabled(false);
		boxText.add(Box.createVerticalStrut(10));
		comBox = new JComboBox<String>();
		comBox.addItem("学生");
		comBox.addItem("图书馆用户管理员");
		comBox.addItem("仓库管理员");
		boxText.add(comBox);
		boxText.add(Box.createVerticalStrut(10));
		baseBox = Box.createHorizontalBox();
		baseBox.add(boxLabel);
		baseBox.add(Box.createHorizontalStrut(10));
		baseBox.add(boxText);
		baseBox.add(Box.createHorizontalStrut(10));
		baseBox.add(selectButton);
		baseBox.add(Box.createHorizontalStrut(10));
		add(baseBox);
		updateButton = new JButton("修改");
		backButton = new JButton("返回主菜单");
		panelButton = new JPanel();
		FlowLayout layout1 = new FlowLayout();
		layout1.setHgap(20);

		panelButton.setLayout(layout1);
		panelButton.add(updateButton);
		panelButton.add(backButton);
		add(panelButton);

		this.setTitle("图书管理系统");
		this.setSize(420, 250);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		selectButton.addActionListener(new MgrUserSelectBtnListener(this, mgrUserUpdateService));
		updateButton.addActionListener(new MgrUserUpdateBtnListener(this, mgrUserUpdateService));
		backButton.addActionListener(new MgrUserUpdateBackBtnListener(this, manMenuWindow));

	}

	public String getUName() {// 返回文本框的用户名
		return uNameText.getText();
	}

	public void setUId(String uId) {// 设置文本框用户身份
		uIdText.setText(uId);
	}

	public int getUserNewIdentity() {
		return comBox.getSelectedIndex() + 1;
	}

	public String getUserNewIdentityString() {
		return comBox.getSelectedItem().toString();
	}
}

package manager.record;

import javax.swing.*;
import java.awt.*;

// 管理员查看某一本书的借阅记录 窗口
public class MgrSelectRecordWindow extends JFrame {
    private JPanel northPanel, southPanel, buttonPanel;
    private MgrRecordPanel recordPanel;
    private JLabel titleLabel;

    public MgrSelectRecordWindow(String bISBN) {
        titleLabel = new JLabel("借阅记录");
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));

        northPanel = new JPanel();
        northPanel.add(titleLabel);
        southPanel = new JPanel(new BorderLayout());
        recordPanel = new MgrRecordPanel(bISBN);
        if (recordPanel.isTableEmpty()) {
            titleLabel.setText("此书当前无借阅记录");
        }
        southPanel.add(recordPanel, BorderLayout.NORTH);

        FlowLayout buttonPanelFlowLayout = new FlowLayout();
        buttonPanelFlowLayout.setHgap(30);
        buttonPanel = new JPanel(buttonPanelFlowLayout);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(southPanel, BorderLayout.SOUTH);

        this.pack();
        this.setTitle("图书管理系统");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}

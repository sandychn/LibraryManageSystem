package manager.record;

import manager.book.select.MgrSelectBookWindow;
import reader.menu.RdrMenuWindow;
import reader.record.*;
import reader.record.service.RdrRenewService;
import reader.record.service.RdrReturnService;

import javax.swing.*;
import java.awt.*;

public class MgrSelectRecordWindow extends JFrame {
    private JPanel northPanel, southPanel, buttonPanel;
    private MgrRecordPanel recordPanel;
    private JLabel titleLabel;
    private String bISBN;
    //private JButton renewBtn, returnBtn, backBtn;

    private RdrRenewService renewOperation;
    private RdrReturnService returnOperation;

    public MgrSelectRecordWindow(String bISBN) {
        this.bISBN = bISBN;

        titleLabel = new JLabel("借阅记录");
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));

        northPanel = new JPanel();
        northPanel.add(titleLabel);
        southPanel = new JPanel(new BorderLayout());
        recordPanel = new MgrRecordPanel(bISBN);
        if (recordPanel.isTableEmpty()) {
            titleLabel.setText("您当前无借阅记录");
        }
        southPanel.add(recordPanel, BorderLayout.NORTH);

        FlowLayout buttonPanelFlowLayout = new FlowLayout();
        buttonPanelFlowLayout.setHgap(30);
        buttonPanel = new JPanel(buttonPanelFlowLayout);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Listener for buttons
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

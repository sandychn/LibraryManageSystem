package reader.record;

import reader.menu.RdrMenuWindow;
import reader.record.service.RdrRenewService;
import reader.record.service.RdrReturnService;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class RdrRecordWindow extends JFrame {

    private JPanel northPanel, southPanel, buttonPanel;
    private RdrRecordPanel recordPanel;
    private JLabel titleLabel;
    private JButton renewBtn, returnBtn, backBtn;

    private RdrRenewService renewOperation;
    private RdrReturnService returnOperation;

    public RdrRecordWindow(RdrMenuWindow rdrMenuWindow) {

        renewOperation = new RdrRenewService();
        returnOperation = new RdrReturnService();
        BorderLayout borderLayout = new BorderLayout();
        borderLayout.setVgap(-5);
        this.setLayout(borderLayout);

        titleLabel = new JLabel("借阅记录");
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        renewBtn = new JButton("续借所选图书");
        returnBtn = new JButton("归还所选图书");
        backBtn = new JButton("返回主菜单");
        renewBtn.setPreferredSize(new Dimension(150, 30));
        returnBtn.setPreferredSize(new Dimension(150, 30));
        backBtn.setPreferredSize(new Dimension(150, 30));

        northPanel = new JPanel();
        northPanel.add(titleLabel);

        southPanel = new JPanel(new BorderLayout());
        recordPanel = new RdrRecordPanel();
        if (recordPanel.isTableEmpty()) {
            titleLabel.setText("您当前无借阅记录");
            renewBtn.setEnabled(false);
            returnBtn.setEnabled(false);
        }
        southPanel.add(recordPanel, BorderLayout.NORTH);

        FlowLayout buttonPanelFlowLayout = new FlowLayout();
        buttonPanelFlowLayout.setHgap(30);
        buttonPanel = new JPanel(buttonPanelFlowLayout);
        buttonPanel.add(renewBtn);
        buttonPanel.add(returnBtn);
        buttonPanel.add(backBtn);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Listener for buttons
        renewBtn.addActionListener(new RdrRenewBtnListener(recordPanel, renewOperation));
        returnBtn.addActionListener(new RdrReturnBtnListener(recordPanel, returnOperation));
        backBtn.addActionListener(new RdrRecordBackBtnListener(rdrMenuWindow, this));

        this.add(northPanel, BorderLayout.NORTH);
        this.add(southPanel, BorderLayout.SOUTH);

        this.pack();
        this.setTitle("图书管理系统");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new RdrRecordWindowListener(rdrMenuWindow, this));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

}

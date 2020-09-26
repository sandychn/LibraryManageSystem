package manager.record;

import common.util.CurrentUser;
import common.util.Tools;
import entity.Book;
import entity.BookLendingRecord;
import manager.record.service.MgrRecordService;
import reader.record.service.RdrRecordException;
import reader.record.service.RdrRecordService;

import javax.swing.*;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Map;
import java.util.Vector;

public class MgrRecordPanel extends JPanel {

    private MgrRecordService recordOperation;
    private JPanel tablePanel;
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel defaultTableModel;
    private DefaultTableCellRenderer defaultTableCellRenderer;
    private Vector<String> tableTitles;
    private String bISBN;

    private static final String[] names = {"书名", "ISBN", "借阅人", "借阅日期", "借期/天", "归还日期"};

    public MgrRecordPanel(String bISBN) {
        this.bISBN = bISBN;
        this.setLayout(new BorderLayout());

        recordOperation = new MgrRecordService();

        // tablePanel components
        tableTitles = new Vector<String>();
        Collections.addAll(tableTitles, names);
        tablePanel = new JPanel();
        defaultTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table = new JTable(defaultTableModel);
        table.setDefaultRenderer(Object.class, defaultTableCellRenderer);
        scrollPane = new JScrollPane(table);

        this.setTable(bISBN);

        // 表格内容属性
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        table.setSelectionBackground(new Color(220, 220, 220));
        table.setGridColor(Color.GRAY);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // 表头
        table.getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 14));
        table.getTableHeader().setResizingAllowed(true); // 不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false); // 不允许拖动重新排序各列

        // 行属性
        table.setRowHeight(30); // 设置行高
        table.setPreferredScrollableViewportSize(new Dimension(1000, 300)); // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）

        tablePanel.add(scrollPane);
        this.add(tablePanel);
    }

    public void setTable(String bISBN) {
        try {
            defaultTableModel.setDataVector(recordOperation.getLendingRecordVector(bISBN), tableTitles);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            JOptionPane.showMessageDialog(null, "获取借阅记录时出现错误。请联系管理员", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isTableEmpty() {
        return table.getRowCount() == 0;
    }

}

package reader.record;

import common.util.CurrentUser;
import common.util.Tools;
import entity.BookLendingRecord;
import reader.record.service.RdrRecordException;
import reader.record.service.RdrRecordService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;

@SuppressWarnings("serial")
public class RdrRecordPanel extends JPanel {

    private RdrRecordService recordOperation;
    private JPanel tablePanel;
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel defaultTableModel;
    private DefaultTableCellRenderer defaultTableCellRenderer;
    private Vector<String> tableTitles;

    private static final String[] names = {"ISBN", "书名", "作者", "借阅日期", "归还日期", "借期/天", "是否已续借"};
    public static final int VECTOR_INDEX_BISBN = 0;
    public static final int VECTOR_INDEX_BNAME = 1;
    public static final int VECTOR_INDEX_BAUTHOR = 2;
    public static final int VECTOR_INDEX_BLTIME = 3;
    public static final int VECTOR_INDEX_BRTIME = 4;
    public static final int VECTOR_INDEX_BLDUE = 5;
    public static final int VECTOR_INDEX_RENEWRD = 6;

    public RdrRecordPanel() {
        this.setLayout(new BorderLayout());

        recordOperation = new RdrRecordService();

        // tablePanel components
        tableTitles = new Vector<String>();
        for (String str : names) {
            tableTitles.add(str);
        }
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

        this.setTable();

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

    // 根据所输入的约束条件 从数据库中读取数据 刷新Table
    public void setTable() {
        try {
            defaultTableModel.setDataVector(recordOperation.getTableVector(CurrentUser.getCurrentUser()), tableTitles);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            JOptionPane.showMessageDialog(null, "获取借阅记录时出现错误。请联系管理员", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 表中是否有行被选中
    public boolean isRowSelected() {
        return table.getSelectedColumnCount() != 0;
    }

    // 返回已选中的行列表; 在调用此方法前应先调用 isRowSelected
    @SuppressWarnings("unchecked")
    public BookLendingRecord getSelectedBookRecord() throws RdrRecordException {
        if (!isRowSelected()) {
            throw new RdrRecordException("请在表格中要操作的图书记录");
        }
        try {
            Vector<String> row = (Vector<String>) defaultTableModel.getDataVector().elementAt(table.getSelectedRow());
            BookLendingRecord bookRecord = new BookLendingRecord(row.elementAt(VECTOR_INDEX_BISBN),
                    row.elementAt(VECTOR_INDEX_BNAME), CurrentUser.getCurrentUser().getUsername(),
                    Tools.stringToDate(row.elementAt(VECTOR_INDEX_BLTIME)),
                    row.elementAt(VECTOR_INDEX_BRTIME) == "尚未归还" ? null
                            : Tools.stringToDate(row.elementAt(VECTOR_INDEX_BRTIME)),
                    Integer.parseInt(row.elementAt(VECTOR_INDEX_BLDUE)));
            return bookRecord;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RdrRecordException("数据异常");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new RdrRecordException("数据异常");
        }
    }

    public boolean isTableEmpty() {
        return table.getRowCount() == 0;
    }

}

package common.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import common.constant.UserConstant;
import common.service.SearchPanelService;
import entity.Book;
import manager.record.MgrSelectRecordWindow;

@SuppressWarnings("serial")
public class SearchPanel extends JPanel {

    private JComboBox<String> bookClassBox;
    private SearchPanelService searchPanelService;
    private JPanel inputPanel, tablePanel;
    private JLabel[] labels;
    private JTextField[] textFields;
    private JButton searchButton;
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel defaultTableModel;
    private DefaultTableCellRenderer defaultTableCellRenderer;
    private Vector<String> tableTitles;
    private Map<String, Integer> bookClassMap;
    private boolean seeLendingRecordFlag; // 是否可以通过双击来查看对应书籍的借阅记录

    private static final String[] names = {"书名", "作者", "出版社", "ISBN", "分类", "总数", "剩余数"};
    private static final int[] limits = {10, 8, 8, 10}; // 文本框 textFields 宽度

    public static final int VECTOR_INDEX_BNAME = 0;
    public static final int VECTOR_INDEX_BAUTHOR = 1;
    public static final int VECTOR_INDEX_BPRESS = 2;
    public static final int VECTOR_INDEX_BISBN = 3;
    public static final int VECTOR_INDEX_BCLASSNAME = 4;
    public static final int VECTOR_INDEX_BSUM = 5;
    public static final int VECTOR_INDEX_BCOUNT = 6;

    public SearchPanel(boolean seeLendingRecordFlag) {
        this.setLayout(new BorderLayout());

        // Initialize the ComboBox first, since it needs to connect to the database
        bookClassBox = new JComboBox<String>();
        bookClassBox.addItem("所有图书类别");
        bookClassBox.setPreferredSize(new Dimension(200, 25));

        searchPanelService = new SearchPanelService();
        try {
            searchPanelService.setBookClassList();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            JOptionPane.showMessageDialog(null, "获取图书信息时出现错误。请联系管理员", "错误", JOptionPane.ERROR_MESSAGE);
        }
        String[] bookClassList = searchPanelService.getBookClassList();
        bookClassMap = new HashMap<String, Integer>();
        for (int i = 0; i < bookClassList.length; ++i) {
            bookClassMap.put(bookClassList[i], i + 1);
            bookClassBox.addItem(bookClassList[i]);
        }
        // End of ComboBox Initialization

        inputPanel = new JPanel();
        labels = new JLabel[5];
        textFields = new JTextField[4];
        for (int i = 0; i < 4; ++i) {
            labels[i] = new JLabel(names[i]);
            inputPanel.add(labels[i]);
            textFields[i] = new JTextField(limits[i]);
            inputPanel.add(textFields[i]);
        }
        labels[4] = new JLabel(names[4]);
        inputPanel.add(labels[4]);
        inputPanel.add(bookClassBox);
        searchButton = new JButton("查询");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTable();
            }
        });
        searchButton.setFocusPainted(false);
        inputPanel.add(searchButton);

        this.add(inputPanel, BorderLayout.NORTH);

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

        this.setTable();

        tablePanel.add(scrollPane);
        this.add(tablePanel);

        this.seeLendingRecordFlag = seeLendingRecordFlag;

        int currentUserIdentity = CurrentUser.getCurrentUser().getIdentity();
        if (this.seeLendingRecordFlag && currentUserIdentity == UserConstant.IDENTITY_STORE_ADMIN) {
            table.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2 && isSelected()) {
                        Book book = getSelectedBook();
                        String bISBN = book.getIsbn();
                        new MgrSelectRecordWindow(bISBN);
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
        }
    }

    // 根据所输入的约束条件 从数据库中读取数据 刷新Table
    public void setTable() {
        try {
            String bName = textFields[0].getText().trim(), bISBN = textFields[3].getText().trim();
            String bAuthor = textFields[1].getText().trim(), bPress = textFields[2].getText().trim();
            int bClassId = bookClassBox.getSelectedIndex();
            bISBN = bISBN.replace("\'", "\'\'");
            defaultTableModel.setDataVector(searchPanelService.getTableVector(bName, bISBN, bAuthor, bPress, bClassId),
                    tableTitles);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            JOptionPane.showMessageDialog(null, "获取图书信息时出现错误。请联系管理员", "错误", JOptionPane.ERROR_MESSAGE);
        }
        table.getColumnModel().getColumn(0).setPreferredWidth(200); // 书名
        table.getColumnModel().getColumn(5).setPreferredWidth(30); // 总数
        table.getColumnModel().getColumn(6).setPreferredWidth(30); // 剩余数
    }

    // 表中是否有行被选中
    public boolean isSelected() {
        return table.getSelectedColumnCount() != 0;
    }

    // 返回已选中的行列表; 在调用此方法前应先调用 isRowSelected
    @SuppressWarnings("unchecked")
    public Book getSelectedBook() {
        if (!isSelected()) {
            return null;
        }
        Vector<String> row = (Vector<String>) defaultTableModel.getDataVector().elementAt(table.getSelectedRow());
        Book book = new Book(row.get(VECTOR_INDEX_BISBN), row.get(VECTOR_INDEX_BNAME), row.get(VECTOR_INDEX_BAUTHOR),
                row.get(VECTOR_INDEX_BPRESS), bookClassMap.get(row.get(VECTOR_INDEX_BCLASSNAME)),
                Integer.parseInt(row.get(VECTOR_INDEX_BCOUNT)), Integer.parseInt(row.get(VECTOR_INDEX_BSUM)));
        return book;
    }

}

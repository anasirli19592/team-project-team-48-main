package Database;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.List;

public class PersonalLibrary extends JPanel {
    private DefaultTableModel tableModel;
    private JTable table;
    private List<PersonalBook> personalLibrary;

    public PersonalLibrary(List<PersonalBook> personalLibrary) {
        this.personalLibrary = personalLibrary;
        setLayout(new BorderLayout());
        initTable();
    }

    public void initTable() {
        tableModel = new DefaultTableModel(new Object[]{"Title", "Author", "Status", "Time Spent", "Start Date", "End Date", "User Rating", "User Review"}, 0);
        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setBackground(Color.decode("#ebedfa"));
        table.setGridColor(Color.decode("#16344f"));
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setForeground(Color.decode("#16344f"));
        for (int i = 0; i < 8; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
        for (PersonalBook book : personalLibrary) {
            Object[] rowData = {book.getTitle(), book.getAuthor(), book.getStatus(), book.getTimeSpent(), book.getStartDate(), book.getEndDate(), book.getRating(), book.getReviews()};
            tableModel.addRow(rowData);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}

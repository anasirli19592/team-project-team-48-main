package DatabaseReader;

import java.awt.BorderLayout;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class SortableTable {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sortable Table");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Sample data
            String[] columnNames = {"Status", "Time spent"};
            Object[][] data = {
                    {"Completed", 550},
                    {"Ongoing", 200},
                    {"Ongoing", 400},
                    {"Not Started", null}
            };

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            JTable table = new JTable(model);

            // Create a TableRowSorter and set it to the JTable
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            table.setRowSorter(sorter);

            // Set the comparator for the "Time spent" column
            sorter.setComparator(1, Comparator.nullsLast(Comparator.naturalOrder()));

            frame.add(new JScrollPane(table), BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

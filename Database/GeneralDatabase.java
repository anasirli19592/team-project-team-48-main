package Database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Book.Book;


public class GeneralDatabase extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private List<Book> bookList;
    private List<Book> personalLibrary;

    private JTextField searchField;

    public GeneralDatabase(String csvFilePath) {
        setTitle("Team-48");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        bookList = readBooksFromCSV(csvFilePath);
        personalLibrary = new ArrayList<>();
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText().trim();
            if (!searchText.isEmpty()) {
                List<Book> filteredList = bookList.stream()
                        .filter(book -> book.getTitle().toLowerCase().contains(searchText.toLowerCase()) ||
                                book.getAuthor().toLowerCase().contains(searchText.toLowerCase()))
                        .collect(Collectors.toList());
                refreshTable(filteredList);
            } else {
                refreshTable(bookList);
            }
        });
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        getContentPane().add(searchPanel, BorderLayout.NORTH);
        initTable();
        JButton personalLibraryButton = new JButton("Personal Library");
        personalLibraryButton.addActionListener(e -> showPersonalLibrary());
        getContentPane().add(personalLibraryButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private static List<Book> readBooksFromCSV(String csvFilePath) {
        List<Book> books = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            br.readLine();

            while ((line = br.readLine()) != null) {    
                String[] parts = line.split(",", -1);
               
                String title = !parts[0].isEmpty() ? parts[0] : "Unknown";
                String author = !parts[1].isEmpty() ? parts[1] : "Unknown";

                books.add(new Book(title, author));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void initTable() {
        tableModel = new DefaultTableModel(new Object[]{"Title", "Author", "Ratings", "Reviews", "Add to your Personal Library"}, 0);
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
        for (int i = 0; i < 5; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
        for (Book book : bookList) {
            Object[] rowData = {book.getTitle(), book.getAuthor(),
                    book.hasNoRatings() ? "No rating" : book.getUserRatings().size(),
                    book.hasNoReviews() ? "No review" : "Clickable", "+ Add"};
            tableModel.addRow(rowData);
        }
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();
                if (row != -1 && column != -1 && table.getValueAt(row, column).equals("Clickable")) {
                    String review = "Title: \nAuthor: \nRating: \nUsername: \nUser Rating \nUser Review ";
                    JTextArea textArea = new JTextArea(review);
                    textArea.setEditable(false);
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    JOptionPane.showMessageDialog(GeneralDatabase.this, scrollPane, "Review", JOptionPane.PLAIN_MESSAGE);
                } else if (row != -1 && column != -1 && table.getValueAt(row, column).equals("+ Add")) {
                    addToPersonalLibrary();
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void refreshTable(List<Book> books) {
        tableModel.setRowCount(0);
        for (Book book : books) {
            Object[] rowData = {book.getTitle(), book.getAuthor(),
                    book.hasNoRatings() ? "No rating" : book.getUserRatings().size(),
                    book.hasNoReviews() ? "No review" : "Clickable", "+ Add"};
            tableModel.addRow(rowData);
        }
    }

    private void addToPersonalLibrary() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Book selectedBook = bookList.get(selectedRow);
            Book personalBook = new Book(selectedBook.getTitle(), selectedBook.getAuthor());
            personalLibrary.add(personalBook);
            JOptionPane.showMessageDialog(null, "The book has been added to your Personal Library");
        } else {
            JOptionPane.showMessageDialog(null, "Select a book to add to your Personal Library");
        }
    }

    private void showPersonalLibrary() {
        PersonalLibrary personalLibraryPanel = new PersonalLibrary(personalLibrary);
        JFrame personalFrame = new JFrame("Personal Library");
        personalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        personalFrame.setSize(800, 600);
        personalFrame.getContentPane().add(personalLibraryPanel);
        personalFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new GeneralDatabase("team-project-team-48/broodsky.csv");
    }
}

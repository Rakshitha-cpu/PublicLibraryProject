package ui;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LibraryUI extends JFrame {

    private JTextField titleField, authorField, idField;
    private JCheckBox availableBox;
    private JTextArea outputArea;

    private PublicBookService service = new PublicBookService();

    public LibraryUI() {
        setTitle("Library Management System");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("ID:"));
        idField = new JTextField(10);
        add(idField);

        add(new JLabel("Title:"));
        titleField = new JTextField(10);
        add(titleField);

        add(new JLabel("Author:"));
        authorField = new JTextField(10);
        add(authorField);

        availableBox = new JCheckBox("Available");
        add(availableBox);

        JButton addBtn = new JButton("Add");
        JButton getBtn = new JButton("Get By ID");
        JButton allBtn = new JButton("Get All");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");

        add(addBtn);
        add(getBtn);
        add(allBtn);
        add(updateBtn);
        add(deleteBtn);

        outputArea = new JTextArea(10, 40);
        add(new JScrollPane(outputArea));

        addBtn.addActionListener(e -> {
            service.addBook(new PublicBookDTO(0,
                    titleField.getText(),
                    authorField.getText(),
                    availableBox.isSelected()));
            outputArea.setText("Book Added!");
        });

        getBtn.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            PublicBookDTO b = service.getBook(id);
            outputArea.setText(b != null ? b.getId()+" "+b.getTitle()+" "+b.getAuthor()+" "+b.isAvailable() : "Not Found");
        });

        allBtn.addActionListener(e -> {
            List<PublicBookDTO> list = service.getAllBooks();
            StringBuilder sb = new StringBuilder();
            for (PublicBookDTO b : list) {
                sb.append(b.getId()).append(" ").append(b.getTitle()).append(" ")
                  .append(b.getAuthor()).append(" ").append(b.isAvailable()).append("\n");
            }
            outputArea.setText(sb.toString());
        });

        updateBtn.addActionListener(e -> {
            service.updateBook(new PublicBookDTO(
                    Integer.parseInt(idField.getText()),
                    titleField.getText(),
                    authorField.getText(),
                    availableBox.isSelected()));
            outputArea.setText("Updated!");
        });

        deleteBtn.addActionListener(e -> {
            service.deleteBook(Integer.parseInt(idField.getText()));
            outputArea.setText("Deleted!");
        });
    }

    public static void main(String[] args) {
        new LibraryUI().setVisible(true);
    }
}
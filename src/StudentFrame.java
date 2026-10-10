import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentFrame extends JFrame {

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField classField;

    private JButton addButton;
    private JButton searchButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton viewButton;
    private JButton clearButton;

    private JTable studentTable;
    private DefaultTableModel tableModel;

    private static final Color NAVY = new Color(20, 45, 85);
    private static final Color BLUE = new Color(45, 105, 190);
    private static final Color BACKGROUND = new Color(242, 246, 252);
    private static final Color BORDER = new Color(220, 230, 243);
    private static final Color TEXT = new Color(40, 55, 75);
    private static final Color MUTED = new Color(110, 125, 145);
    private static final Color WHITE = Color.WHITE;

    public StudentFrame() {

        setTitle("School Management System - Student Management");
        setSize(1000, 720);
        setMinimumSize(new Dimension(820, 620));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(0, 20));
        root.setBackground(BACKGROUND);
        root.setBorder(new EmptyBorder(25, 28, 22, 28));

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel headingPanel = new JPanel();
        headingPanel.setOpaque(false);
        headingPanel.setLayout(
                new BoxLayout(headingPanel, BoxLayout.Y_AXIS)
        );

        JLabel titleLabel = new JLabel("Student Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 27));
        titleLabel.setForeground(NAVY);

        JLabel subtitleLabel = new JLabel(
                "Add, search, update and manage student records."
        );
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(MUTED);

        headingPanel.add(titleLabel);
        headingPanel.add(Box.createVerticalStrut(7));
        headingPanel.add(subtitleLabel);

        JLabel moduleBadge = new JLabel("  STUDENTS  ");
        moduleBadge.setOpaque(true);
        moduleBadge.setBackground(new Color(225, 238, 255));
        moduleBadge.setForeground(BLUE);
        moduleBadge.setFont(new Font("Arial", Font.BOLD, 12));
        moduleBadge.setBorder(new EmptyBorder(10, 8, 10, 8));

        header.add(headingPanel, BorderLayout.WEST);
        header.add(moduleBadge, BorderLayout.EAST);

        // Form card
        JPanel formCard = new JPanel(new BorderLayout(0, 18));
        formCard.setBackground(WHITE);
        formCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(20, 22, 20, 22)
        ));

        JLabel formTitle = new JLabel("Student Information");
        formTitle.setFont(new Font("Arial", Font.BOLD, 18));
        formTitle.setForeground(NAVY);

        JPanel fieldsPanel = new JPanel(new GridLayout(2, 2, 22, 16));
        fieldsPanel.setOpaque(false);

        idField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();
        classField = new JTextField();

        fieldsPanel.add(createFieldPanel("Student ID", idField));
        fieldsPanel.add(createFieldPanel("Student Name", nameField));
        fieldsPanel.add(createFieldPanel("Age", ageField));
        fieldsPanel.add(createFieldPanel("Class", classField));

        JPanel formContent = new JPanel(new BorderLayout(0, 18));
        formContent.setOpaque(false);
        formContent.add(formTitle, BorderLayout.NORTH);
        formContent.add(fieldsPanel, BorderLayout.CENTER);

        // Action buttons
        JPanel actionsPanel = new JPanel(new FlowLayout(
                FlowLayout.LEFT, 10, 0
        ));
        actionsPanel.setOpaque(false);

        addButton = createButton("Add Student", BLUE, WHITE);
        searchButton = createButton(
                "Search", new Color(225, 238, 255), BLUE
        );
        updateButton = createButton(
                "Update", new Color(225, 238, 255), BLUE
        );
        deleteButton = createButton(
                "Delete", new Color(255, 235, 235),
                new Color(180, 45, 45)
        );
        viewButton = createButton("Refresh Table", NAVY, WHITE);
        clearButton = createButton(
                "Clear Fields", new Color(238, 242, 248), TEXT
        );

        actionsPanel.add(addButton);
        actionsPanel.add(searchButton);
        actionsPanel.add(updateButton);
        actionsPanel.add(deleteButton);
        actionsPanel.add(viewButton);
        actionsPanel.add(clearButton);

        formCard.add(formContent, BorderLayout.CENTER);
        formCard.add(actionsPanel, BorderLayout.SOUTH);

        // Student table card
        JPanel tableCard = new JPanel(new BorderLayout(0, 14));
        tableCard.setBackground(WHITE);
        tableCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(18, 20, 18, 20)
        ));

        JPanel tableHeader = new JPanel(new BorderLayout());
        tableHeader.setOpaque(false);

        JLabel tableTitle = new JLabel("Student Records");
        tableTitle.setFont(new Font("Arial", Font.BOLD, 18));
        tableTitle.setForeground(NAVY);

        JLabel tableHint = new JLabel("Select a row to edit");
        tableHint.setFont(new Font("Arial", Font.PLAIN, 12));
        tableHint.setForeground(MUTED);

        tableHeader.add(tableTitle, BorderLayout.WEST);
        tableHeader.add(tableHint, BorderLayout.EAST);

        String[] columns = {"Student ID", "Name", "Age", "Class"};

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        studentTable = new JTable(tableModel);
        studentTable.setFont(new Font("Arial", Font.PLAIN, 13));
        studentTable.setForeground(TEXT);
        studentTable.setBackground(WHITE);
        studentTable.setRowHeight(32);
        studentTable.setSelectionBackground(new Color(220, 235, 255));
        studentTable.setSelectionForeground(NAVY);
        studentTable.setShowVerticalLines(false);
        studentTable.setGridColor(BORDER);
        studentTable.setFillsViewportHeight(true);

        // Enable sorting
        studentTable.setAutoCreateRowSorter(true);

        studentTable.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 13)
        );
        studentTable.getTableHeader().setBackground(
                new Color(232, 240, 251)
        );
        studentTable.getTableHeader().setForeground(NAVY);
        studentTable.getTableHeader().setPreferredSize(
                new Dimension(0, 38)
        );
        studentTable.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer cellRenderer =
                new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column
                    ) {
                        Component component =
                                super.getTableCellRendererComponent(
                                        table, value, isSelected,
                                        hasFocus, row, column
                                );

                        setBorder(new EmptyBorder(0, 10, 0, 10));

                        if (!isSelected) {
                            component.setBackground(
                                    row % 2 == 0
                                            ? WHITE
                                            : new Color(248, 250, 254)
                            );
                        }

                        return component;
                    }
                };

        for (int i = 0; i < studentTable.getColumnCount(); i++) {
            studentTable.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(cellRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(BORDER));
        scrollPane.getViewport().setBackground(WHITE);

        tableCard.add(tableHeader, BorderLayout.NORTH);
        tableCard.add(scrollPane, BorderLayout.CENTER);

        // Form and table layout
        JPanel centerPanel = new JPanel(new BorderLayout(0, 18));
        centerPanel.setOpaque(false);
        centerPanel.add(formCard, BorderLayout.NORTH);
        centerPanel.add(tableCard, BorderLayout.CENTER);

        root.add(header, BorderLayout.NORTH);
        root.add(centerPanel, BorderLayout.CENTER);

        add(root);

        // Add student
        addButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                String studentClass = classField.getText().trim();

                if (name.isEmpty() || studentClass.isEmpty()) {
                    showMessage(
                            "Please fill all fields!",
                            "Missing Information",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                if (age <= 0) {
                    showMessage(
                            "Age must be greater than zero.",
                            "Invalid Age",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                StudentDAO.addStudent(id, name, age, studentClass);

                showMessage(
                        "Student added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();
                loadStudents();

            } catch (NumberFormatException ex) {
                showMessage(
                        "Enter a valid Student ID and Age.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                showMessage(
                        "Could not add student. Check the input and database.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // Search student
        searchButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());

                String result = StudentDAO.searchStudentForGUI(id);

                showMessage(
                        result,
                        "Student Search Result",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (NumberFormatException ex) {
                showMessage(
                        "Enter a valid Student ID.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                showMessage(
                        "Could not search for this student.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // Update student
        updateButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                String studentClass = classField.getText().trim();

                if (name.isEmpty() || studentClass.isEmpty()) {
                    showMessage(
                            "Please fill all fields!",
                            "Missing Information",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                if (age <= 0) {
                    showMessage(
                            "Age must be greater than zero.",
                            "Invalid Age",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                StudentDAO.updateStudent(id, name, age, studentClass);

                showMessage(
                        "Update request completed.",
                        "Student Management",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();
                loadStudents();

            } catch (NumberFormatException ex) {
                showMessage(
                        "Enter a valid Student ID and Age.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                showMessage(
                        "Could not update student. Check the input and database.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // Delete student
        deleteButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());

                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete Student ID " + id + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (confirm == JOptionPane.YES_OPTION) {

                    // Remove selection before changing the table
                    studentTable.clearSelection();

                    StudentDAO.deleteStudent(id);

                    clearFields();
                    loadStudents();

                    showMessage(
                            "Delete request completed.",
                            "Student Management",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }

            } catch (NumberFormatException ex) {
                showMessage(
                        "Enter a valid Student ID.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                showMessage(
                        "Could not delete student. Check the database.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // Refresh table
        viewButton.addActionListener(e -> loadStudents());

        // Clear form
        clearButton.addActionListener(e -> clearFields());

        // Select a row to fill the form
        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }

            int selectedViewRow = studentTable.getSelectedRow();

            if (selectedViewRow < 0) {
                return;
            }

            int modelRow = studentTable.convertRowIndexToModel(
                    selectedViewRow
            );

            // Prevent an invalid model-row access
            if (modelRow < 0 || modelRow >= tableModel.getRowCount()) {
                return;
            }

            idField.setText(
                    String.valueOf(tableModel.getValueAt(modelRow, 0))
            );
            nameField.setText(
                    String.valueOf(tableModel.getValueAt(modelRow, 1))
            );
            ageField.setText(
                    String.valueOf(tableModel.getValueAt(modelRow, 2))
            );
            classField.setText(
                    String.valueOf(tableModel.getValueAt(modelRow, 3))
            );
        });

        loadStudents();
    }

    // Create form field
    private JPanel createFieldPanel(
            String labelText,
            JTextField field
    ) {
        JPanel panel = new JPanel(new BorderLayout(0, 7));
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setForeground(TEXT);

        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setForeground(TEXT);
        field.setBackground(WHITE);
        field.setCaretColor(BLUE);
        field.setPreferredSize(new Dimension(180, 38));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(7, 10, 7, 10)
        ));

        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);

        return panel;
    }

    // Create styled button
    private JButton createButton(
            String text,
            Color background,
            Color foreground
    ) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(new EmptyBorder(10, 14, 10, 14));

        return button;
    }

    // Display message
    private void showMessage(
            String message,
            String title,
            int messageType
    ) {
        JOptionPane.showMessageDialog(
                this, message, title, messageType
        );
    }

    // Reload records from the database
    void loadStudents() {

        // Clear selection before changing table rows
        studentTable.clearSelection();

        // Remove old records
        tableModel.setRowCount(0);

        // Load fresh records using the existing DAO
        StudentDAO.getStudentsInTable(tableModel);

        // Update table display
        studentTable.revalidate();
        studentTable.repaint();
    }

    // Clear form fields
    void clearFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        classField.setText("");

        studentTable.clearSelection();
        idField.requestFocusInWindow();
    }
}


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SubjectFrame extends JFrame {

    private JTextField idField;
    private JTextField nameField;
    private JTextField teacherIdField;

    private JTable subjectTable;
    private DefaultTableModel tableModel;

    private static final Color NAVY = new Color(20, 45, 85);
    private static final Color BLUE = new Color(45, 105, 190);
    private static final Color BACKGROUND = new Color(242, 246, 252);
    private static final Color BORDER = new Color(220, 230, 243);
    private static final Color TEXT = new Color(40, 55, 75);
    private static final Color MUTED = new Color(110, 125, 145);
    private static final Color WHITE = Color.WHITE;

    public SubjectFrame() {

        setTitle("School Management System - Subject Management");
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

        JLabel titleLabel = new JLabel("Subject Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 27));
        titleLabel.setForeground(NAVY);

        JLabel subtitleLabel = new JLabel(
                "Add, search, update and manage subjects."
        );
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(MUTED);

        headingPanel.add(titleLabel);
        headingPanel.add(Box.createVerticalStrut(7));
        headingPanel.add(subtitleLabel);

        JLabel badge = new JLabel("  SUBJECTS  ");
        badge.setOpaque(true);
        badge.setBackground(new Color(225, 238, 255));
        badge.setForeground(BLUE);
        badge.setFont(new Font("Arial", Font.BOLD, 12));
        badge.setBorder(new EmptyBorder(10, 8, 10, 8));

        header.add(headingPanel, BorderLayout.WEST);
        header.add(badge, BorderLayout.EAST);

        // Form card
        JPanel formCard = new JPanel(new BorderLayout(0, 18));
        formCard.setBackground(WHITE);
        formCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(20, 22, 20, 22)
        ));

        JLabel formTitle = new JLabel("Subject Information");
        formTitle.setFont(new Font("Arial", Font.BOLD, 18));
        formTitle.setForeground(NAVY);

        JPanel fieldsPanel = new JPanel(new GridLayout(2, 2, 22, 16));
        fieldsPanel.setOpaque(false);

        idField = new JTextField();
        nameField = new JTextField();
        teacherIdField = new JTextField();

        fieldsPanel.add(createFieldPanel("Subject ID", idField));
        fieldsPanel.add(createFieldPanel("Subject Name", nameField));
        fieldsPanel.add(createFieldPanel("Teacher ID", teacherIdField));

        JLabel hint = new JLabel("Enter the ID of the teacher assigned to this subject.");
        hint.setFont(new Font("Arial", Font.PLAIN, 12));
        hint.setForeground(MUTED);
        fieldsPanel.add(hint);

        JPanel formContent = new JPanel(new BorderLayout(0, 18));
        formContent.setOpaque(false);
        formContent.add(formTitle, BorderLayout.NORTH);
        formContent.add(fieldsPanel, BorderLayout.CENTER);

        JPanel actionsPanel = new JPanel(new FlowLayout(
                FlowLayout.LEFT, 10, 0
        ));
        actionsPanel.setOpaque(false);

        JButton addButton = createButton("Add Subject", BLUE, WHITE);
        JButton searchButton = createButton(
                "Search", new Color(225, 238, 255), BLUE
        );
        JButton updateButton = createButton(
                "Update", new Color(225, 238, 255), BLUE
        );
        JButton deleteButton = createButton(
                "Delete", new Color(255, 235, 235),
                new Color(180, 45, 45)
        );
        JButton refreshButton = createButton("Refresh Table", NAVY, WHITE);
        JButton clearButton = createButton(
                "Clear Fields", new Color(238, 242, 248), TEXT
        );

        actionsPanel.add(addButton);
        actionsPanel.add(searchButton);
        actionsPanel.add(updateButton);
        actionsPanel.add(deleteButton);
        actionsPanel.add(refreshButton);
        actionsPanel.add(clearButton);

        formCard.add(formContent, BorderLayout.CENTER);
        formCard.add(actionsPanel, BorderLayout.SOUTH);

        // Table
        JPanel tableCard = new JPanel(new BorderLayout(0, 14));
        tableCard.setBackground(WHITE);
        tableCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                new EmptyBorder(18, 20, 18, 20)
        ));

        JPanel tableHeader = new JPanel(new BorderLayout());
        tableHeader.setOpaque(false);

        JLabel tableTitle = new JLabel("Subject Records");
        tableTitle.setFont(new Font("Arial", Font.BOLD, 18));
        tableTitle.setForeground(NAVY);

        JLabel tableHint = new JLabel("Select a row to edit");
        tableHint.setFont(new Font("Arial", Font.PLAIN, 12));
        tableHint.setForeground(MUTED);

        tableHeader.add(tableTitle, BorderLayout.WEST);
        tableHeader.add(tableHint, BorderLayout.EAST);

        String[] columns = {"Subject ID", "Subject Name", "Teacher ID"};

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        subjectTable = new JTable(tableModel);
        subjectTable.setFont(new Font("Arial", Font.PLAIN, 13));
        subjectTable.setForeground(TEXT);
        subjectTable.setBackground(WHITE);
        subjectTable.setRowHeight(32);
        subjectTable.setSelectionBackground(new Color(220, 235, 255));
        subjectTable.setSelectionForeground(NAVY);
        subjectTable.setShowVerticalLines(false);
        subjectTable.setGridColor(BORDER);
        subjectTable.setFillsViewportHeight(true);
        subjectTable.setAutoCreateRowSorter(true);

        subjectTable.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 13)
        );
        subjectTable.getTableHeader().setBackground(
                new Color(232, 240, 251)
        );
        subjectTable.getTableHeader().setForeground(NAVY);
        subjectTable.getTableHeader().setPreferredSize(
                new Dimension(0, 38)
        );
        subjectTable.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer renderer =
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

        for (int i = 0; i < subjectTable.getColumnCount(); i++) {
            subjectTable.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(renderer);
        }

        JScrollPane scrollPane = new JScrollPane(subjectTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(BORDER));
        scrollPane.getViewport().setBackground(WHITE);

        tableCard.add(tableHeader, BorderLayout.NORTH);
        tableCard.add(scrollPane, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout(0, 18));
        centerPanel.setOpaque(false);
        centerPanel.add(formCard, BorderLayout.NORTH);
        centerPanel.add(tableCard, BorderLayout.CENTER);

        root.add(header, BorderLayout.NORTH);
        root.add(centerPanel, BorderLayout.CENTER);

        add(root);

        // ADD SUBJECT
        addButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                int teacherId = Integer.parseInt(
                        teacherIdField.getText().trim()
                );

                if (name.isEmpty()) {
                    showMessage(
                            "Please enter the subject name.",
                            "Missing Information",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                SubjectDAO.addSubject(id, name, teacherId);
                loadSubjects();
                clearFields();

                showMessage(
                        "Add request completed.",
                        "Subject Management",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (NumberFormatException ex) {
                showMessage(
                        "Enter valid Subject ID and Teacher ID.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                showMessage(
                        "Could not add subject. Check the DAO and database.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // SEARCH SUBJECT
        searchButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());

                String result = SubjectDAO.searchSubjectForGUI(id);

                showMessage(
                        result,
                        "Subject Search Result",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (NumberFormatException ex) {
                showMessage(
                        "Enter a valid Subject ID.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                showMessage(
                        "Could not search for subject.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // UPDATE SUBJECT
        updateButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                int teacherId = Integer.parseInt(
                        teacherIdField.getText().trim()
                );

                if (name.isEmpty()) {
                    showMessage(
                            "Please enter the subject name.",
                            "Missing Information",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                SubjectDAO.updateSubject(id, name, teacherId);
                loadSubjects();
                clearFields();

                showMessage(
                        "Update request completed.",
                        "Subject Management",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (NumberFormatException ex) {
                showMessage(
                        "Enter valid Subject ID and Teacher ID.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                showMessage(
                        "Could not update subject. Check the DAO and database.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // DELETE SUBJECT
        deleteButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());

                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Delete Subject ID " + id + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    subjectTable.clearSelection();

                    SubjectDAO.deleteSubject(id);
                    loadSubjects();
                    clearFields();

                    showMessage(
                            "Delete request completed.",
                            "Subject Management",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }

            } catch (NumberFormatException ex) {
                showMessage(
                        "Enter a valid Subject ID.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                showMessage(
                        "Could not delete subject. Check the database.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // REFRESH AND CLEAR
        refreshButton.addActionListener(e -> loadSubjects());
        clearButton.addActionListener(e -> clearFields());

        // ROW SELECTION
        subjectTable.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }

            int viewRow = subjectTable.getSelectedRow();

            if (viewRow < 0) {
                return;
            }

            int modelRow = subjectTable.convertRowIndexToModel(viewRow);

            if (modelRow < 0 || modelRow >= tableModel.getRowCount()) {
                return;
            }

            idField.setText(
                    String.valueOf(tableModel.getValueAt(modelRow, 0))
            );
            nameField.setText(
                    String.valueOf(tableModel.getValueAt(modelRow, 1))
            );
            teacherIdField.setText(
                    String.valueOf(tableModel.getValueAt(modelRow, 2))
            );
        });

        loadSubjects();
    }

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

    private void showMessage(
            String message,
            String title,
            int messageType
    ) {
        JOptionPane.showMessageDialog(
                this, message, title, messageType
        );
    }

    // Reload subjects from the existing DAO
    void loadSubjects() {
        subjectTable.clearSelection();
        tableModel.setRowCount(0);

        SubjectDAO.getSubjectsInTable(tableModel);

        subjectTable.revalidate();
        subjectTable.repaint();
    }

    // Clear input fields
    void clearFields() {
        idField.setText("");
        nameField.setText("");
        teacherIdField.setText("");

        subjectTable.clearSelection();
        idField.requestFocusInWindow();
    }
}


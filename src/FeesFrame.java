import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class FeesFrame extends JFrame {

    JTextField studentIdField;
    JTextField amountField;
    JTextField dateField;
    JComboBox<String> statusBox;

    JButton addButton;
    JButton searchButton;
    JButton updateButton;
    JButton deleteButton;
    JButton viewButton;
    JButton clearButton;

    JTable feesTable;
    DefaultTableModel tableModel;

    private final Color primaryBlue = new Color(37, 99, 235);
    private final Color darkBlue = new Color(30, 64, 175);
    private final Color backgroundColor = new Color(243, 246, 251);
    private final Color white = Color.WHITE;
    private final Color textColor = new Color(31, 41, 55);

    public FeesFrame() {

        setTitle("School Management System - Fees Management");
        setSize(1000, 700);
        setMinimumSize(new Dimension(850, 600));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        );

        // ================= HEADER =================

        JLabel titleLabel = new JLabel(
                "Fees Management",
                SwingConstants.CENTER
        );

        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(darkBlue);

        JLabel subtitleLabel = new JLabel(
                "Manage student fee records",
                SwingConstants.CENTER
        );

        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.GRAY);

        JPanel headerPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        headerPanel.setOpaque(false);
        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);

        // ================= FORM =================

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(white);
        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 228, 240)
                        ),
                        BorderFactory.createEmptyBorder(20, 20, 20, 20)
                )
        );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        studentIdField = new JTextField(15);
        amountField = new JTextField(15);
        dateField = new JTextField(15);

        dateField.setToolTipText("Enter date in yyyy-MM-dd format");

        statusBox = new JComboBox<>(
                new String[]{"Paid", "Pending"}
        );

        addFormField(formPanel, gbc, 0, 0, "Student ID:",
                studentIdField);

        addFormField(formPanel, gbc, 2, 0, "Amount:",
                amountField);

        addFormField(formPanel, gbc, 0, 1, "Date (yyyy-MM-dd):",
                dateField);

        addFormField(formPanel, gbc, 2, 1, "Status:",
                statusBox);

        // ================= BUTTONS =================

        addButton = createButton("Add Fee", primaryBlue);
        searchButton = createButton("Search", new Color(8, 145, 178));
        updateButton = createButton("Update", new Color(22, 163, 74));
        deleteButton = createButton("Delete", new Color(220, 38, 38));
        viewButton = createButton("View Fees", darkBlue);
        clearButton = createButton("Clear", new Color(107, 114, 128));

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 10, 5)
        );
        buttonPanel.setOpaque(false);

        buttonPanel.add(addButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(clearButton);

        // ================= TABLE =================

        String[] columns = {
                "Student ID", "Amount", "Date", "Status"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        feesTable = new JTable(tableModel);
        feesTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        feesTable.setRowHeight(28);
        feesTable.setSelectionBackground(new Color(219, 234, 254));
        feesTable.setSelectionForeground(textColor);
        feesTable.setGridColor(new Color(229, 231, 235));
        feesTable.setShowVerticalLines(false);
        feesTable.setFillsViewportHeight(true);
        feesTable.setAutoCreateRowSorter(true);

        feesTable.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );
        feesTable.getTableHeader().setBackground(primaryBlue);
        feesTable.getTableHeader().setForeground(white);
        feesTable.getTableHeader().setPreferredSize(
                new Dimension(100, 35)
        );

        JScrollPane scrollPane = new JScrollPane(feesTable);
        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 228, 240)
                )
        );

        JPanel tablePanel = new JPanel(new BorderLayout(0, 10));
        tablePanel.setBackground(white);
        tablePanel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        JLabel tableTitle = new JLabel("Fee Records");
        tableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tableTitle.setForeground(textColor);

        tablePanel.add(tableTitle, BorderLayout.NORTH);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // ================= ADD FEE =================

        addButton.addActionListener(e -> {

            if (!validateInputs()) {
                return;
            }

            try {
                int studentId = Integer.parseInt(
                        studentIdField.getText().trim()
                );

                double amount = Double.parseDouble(
                        amountField.getText().trim()
                );

                String date = dateField.getText().trim();
                String status = statusBox.getSelectedItem().toString();

                FeesDAO.addFees(studentId, amount, date, status);

                JOptionPane.showMessageDialog(
                        this,
                        "Fee added successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();
                loadFees();

            } catch (NumberFormatException ex) {
                showError("Student ID and amount must be valid numbers.");
            } catch (Exception ex) {
                showError("Could not add fee. Check the student ID and database.");
                ex.printStackTrace();
            }
        });

        // ================= SEARCH =================

        searchButton.addActionListener(e -> {

            if (studentIdField.getText().trim().isEmpty()) {
                showError("Please enter a Student ID.");
                return;
            }

            try {
                int studentId = Integer.parseInt(
                        studentIdField.getText().trim()
                );

                String result = FeesDAO.searchFeesForGUI(studentId);

                JOptionPane.showMessageDialog(
                        this,
                        result,
                        "Fees Search Result",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (NumberFormatException ex) {
                showError("Please enter a valid Student ID.");
            } catch (Exception ex) {
                showError("Could not search fee records.");
                ex.printStackTrace();
            }
        });

        // ================= UPDATE =================

        updateButton.addActionListener(e -> {

            if (!validateInputs()) {
                return;
            }

            try {
                int studentId = Integer.parseInt(
                        studentIdField.getText().trim()
                );

                double amount = Double.parseDouble(
                        amountField.getText().trim()
                );

                String date = dateField.getText().trim();
                String status = statusBox.getSelectedItem().toString();

                FeesDAO.updateFees(studentId, amount, date, status);

                JOptionPane.showMessageDialog(
                        this,
                        "Fee update request completed.",
                        "Update",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();
                loadFees();

            } catch (NumberFormatException ex) {
                showError("Student ID and amount must be valid numbers.");
            } catch (Exception ex) {
                showError("Could not update fee. Check the student ID and database.");
                ex.printStackTrace();
            }
        });

        // ================= DELETE =================

        deleteButton.addActionListener(e -> {

            if (studentIdField.getText().trim().isEmpty()) {
                showError("Please enter a Student ID.");
                return;
            }

            try {
                int studentId = Integer.parseInt(
                        studentIdField.getText().trim()
                );

                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Delete the fee record for Student ID "
                                + studentId + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (confirm == JOptionPane.YES_OPTION) {

                    FeesDAO.deleteFees(studentId);

                    JOptionPane.showMessageDialog(
                            this,
                            "Delete request completed.",
                            "Delete",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    clearFields();
                    loadFees();
                }

            } catch (NumberFormatException ex) {
                showError("Please enter a valid Student ID.");
            } catch (Exception ex) {
                showError("Could not delete fee record.");
                ex.printStackTrace();
            }
        });

        // ================= VIEW FEES =================

        viewButton.addActionListener(e -> loadFees());

        // ================= CLEAR FIELDS =================

        clearButton.addActionListener(e -> clearFields());

        // ================= TABLE ROW CLICK =================

        feesTable.addMouseListener(
                new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent e) {

                        int viewRow = feesTable.getSelectedRow();

                        if (viewRow >= 0) {
                            int modelRow =
                                    feesTable.convertRowIndexToModel(viewRow);

                            studentIdField.setText(
                                    tableModel.getValueAt(modelRow, 0).toString()
                            );

                            amountField.setText(
                                    tableModel.getValueAt(modelRow, 1).toString()
                            );

                            dateField.setText(
                                    tableModel.getValueAt(modelRow, 2).toString()
                            );

                            statusBox.setSelectedItem(
                                    tableModel.getValueAt(modelRow, 3).toString()
                            );
                        }
                    }
                }
        );

        // ================= LAYOUT =================

        JPanel topPanel = new JPanel(new BorderLayout(0, 15));
        topPanel.setOpaque(false);
        topPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel formAndButtons = new JPanel(new BorderLayout(0, 10));
        formAndButtons.setOpaque(false);
        formAndButtons.add(formPanel, BorderLayout.CENTER);
        formAndButtons.add(buttonPanel, BorderLayout.SOUTH);

        topPanel.add(formAndButtons, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        add(mainPanel);

        // Load existing records when the frame opens.
        loadFees();
    }

    // ================= HELPER METHODS =================

    private void addFormField(
            JPanel panel,
            GridBagConstraints gbc,
            int x,
            int y,
            String labelText,
            JComponent field) {

        gbc.gridx = x;
        gbc.gridy = y;
        gbc.weightx = 0;

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(textColor);
        panel.add(label, gbc);

        gbc.gridx = x + 1;
        gbc.weightx = 1;

        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(field, gbc);
    }

    private JButton createButton(String text, Color color) {

        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setForeground(white);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(120, 36));

        return button;
    }

    private boolean validateInputs() {

        String studentIdText = studentIdField.getText().trim();
        String amountText = amountField.getText().trim();
        String dateText = dateField.getText().trim();

        if (studentIdText.isEmpty()
                || amountText.isEmpty()
                || dateText.isEmpty()) {

            showError("Please fill Student ID, Amount and Date.");
            return false;
        }

        try {
            int studentId = Integer.parseInt(studentIdText);

            if (studentId <= 0) {
                showError("Student ID must be greater than zero.");
                return false;
            }
        } catch (NumberFormatException ex) {
            showError("Student ID must be a whole number.");
            return false;
        }

        try {
            double amount = Double.parseDouble(amountText);

            if (!Double.isFinite(amount) || amount < 0) {
                showError("Amount must be a valid, non-negative number.");
                return false;
            }
        } catch (NumberFormatException ex) {
            showError("Please enter a valid amount.");
            return false;
        }

        try {
            LocalDate.parse(dateText);
        } catch (DateTimeParseException ex) {
            showError("Enter a valid date in yyyy-MM-dd format, e.g. 2026-10-09.");
            return false;
        }

        return true;
    }

    void loadFees() {

        try {
            tableModel.setRowCount(0);
            FeesDAO.getFeesInTable(tableModel);
        } catch (Exception ex) {
            showError("Could not load fee records. Check the database connection.");
            ex.printStackTrace();
        }
    }

    void clearFields() {

        studentIdField.setText("");
        amountField.setText("");
        dateField.setText("");
        statusBox.setSelectedIndex(0);
        feesTable.clearSelection();
    }

    private void showError(String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Input / Database Error",
                JOptionPane.WARNING_MESSAGE
        );
    }
}


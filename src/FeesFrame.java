import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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

    JTable feesTable;
    DefaultTableModel tableModel;

    public FeesFrame() {

        setTitle("School Management System - Fees Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Title
        JLabel titleLabel = new JLabel(
                "FEES MANAGEMENT",
                SwingConstants.CENTER
        );

        titleLabel.setBounds(200, 15, 400, 35);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        // Student ID
        JLabel studentIdLabel = new JLabel("Student ID:");
        studentIdLabel.setBounds(50, 70, 100, 25);

        studentIdField = new JTextField();
        studentIdField.setBounds(160, 70, 200, 25);

        // Amount
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(400, 70, 100, 25);

        amountField = new JTextField();
        amountField.setBounds(500, 70, 200, 25);

        // Date
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(50, 110, 100, 25);

        dateField = new JTextField();
        dateField.setBounds(160, 110, 200, 25);

        // Status
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(400, 110, 100, 25);

        String[] statuses = {
                "Paid",
                "Pending"
        };

        statusBox = new JComboBox<>(statuses);
        statusBox.setBounds(500, 110, 200, 25);

        // Buttons
        addButton = new JButton("Add Fee");
        addButton.setBounds(40, 155, 110, 35);

        searchButton = new JButton("Search");
        searchButton.setBounds(160, 155, 100, 35);

        updateButton = new JButton("Update");
        updateButton.setBounds(270, 155, 100, 35);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(380, 155, 100, 35);

        viewButton = new JButton("View Fees");
        viewButton.setBounds(490, 155, 120, 35);

        // Table
        String[] columns = {
                "Student ID",
                "Amount",
                "Date",
                "Status"
        };

        tableModel = new DefaultTableModel(columns, 0);

        feesTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(feesTable);
        scrollPane.setBounds(50, 220, 680, 280);

        // ADD FEE
        addButton.addActionListener(e -> {

            try {

                int studentId =
                        Integer.parseInt(studentIdField.getText());

                double amount =
                        Double.parseDouble(amountField.getText());

                String date = dateField.getText();

                String status =
                        statusBox.getSelectedItem().toString();

                if (date.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter the date!"
                    );

                    return;
                }

                if (amount < 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Amount cannot be negative!"
                    );

                    return;
                }

                FeesDAO.addFees(
                        studentId,
                        amount,
                        date,
                        status
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Fee Added Successfully!"
                );

                clearFields();
                loadFees();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid Student ID and Amount!"
                );
            }
        });

        // SEARCH
        searchButton.addActionListener(e -> {

            try {

                int studentId =
                        Integer.parseInt(studentIdField.getText());

                String result =
                        FeesDAO.searchFeesForGUI(studentId);

                JOptionPane.showMessageDialog(
                        this,
                        result,
                        "Fees Search Result",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Student ID!",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });

        // UPDATE
        updateButton.addActionListener(e -> {

            try {

                int studentId =
                        Integer.parseInt(studentIdField.getText());

                double amount =
                        Double.parseDouble(amountField.getText());

                String date = dateField.getText();

                String status =
                        statusBox.getSelectedItem().toString();

                if (date.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter the date!"
                    );

                    return;
                }

                if (amount < 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Amount cannot be negative!"
                    );

                    return;
                }

                FeesDAO.updateFees(
                        studentId,
                        amount,
                        date,
                        status
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Fee Updated Successfully!"
                );

                clearFields();
                loadFees();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid Student ID and Amount!"
                );
            }
        });

        // DELETE
        deleteButton.addActionListener(e -> {

            try {

                int studentId =
                        Integer.parseInt(studentIdField.getText());

                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this fee record?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {

                    FeesDAO.deleteFees(studentId);

                    JOptionPane.showMessageDialog(
                            this,
                            "Fee Deleted Successfully!"
                    );

                    clearFields();
                    loadFees();
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Student ID!"
                );
            }
        });

        // VIEW
        viewButton.addActionListener(e -> {

            loadFees();

        });

        // Table row click
        feesTable.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    public void mouseClicked(
                            java.awt.event.MouseEvent e) {

                        int row = feesTable.getSelectedRow();

                        if (row >= 0) {

                            studentIdField.setText(
                                    tableModel
                                            .getValueAt(row, 0)
                                            .toString()
                            );

                            amountField.setText(
                                    tableModel
                                            .getValueAt(row, 1)
                                            .toString()
                            );

                            dateField.setText(
                                    tableModel
                                            .getValueAt(row, 2)
                                            .toString()
                            );

                            statusBox.setSelectedItem(
                                    tableModel
                                            .getValueAt(row, 3)
                                            .toString()
                            );
                        }
                    }
                }
        );

        // Add components
        panel.add(titleLabel);

        panel.add(studentIdLabel);
        panel.add(studentIdField);

        panel.add(amountLabel);
        panel.add(amountField);

        panel.add(dateLabel);
        panel.add(dateField);

        panel.add(statusLabel);
        panel.add(statusBox);

        panel.add(addButton);
        panel.add(searchButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(viewButton);

        panel.add(scrollPane);

        add(panel);

        // Load fees when window opens
        loadFees();
    }

    // Load fees into JTable
    void loadFees() {

        FeesDAO.getFeesInTable(tableModel);

    }

    // Clear fields
    void clearFields() {

        studentIdField.setText("");
        amountField.setText("");
        dateField.setText("");
        statusBox.setSelectedIndex(0);
    }
}
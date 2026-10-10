import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MarksFrame extends JFrame {

    private JTextField studentIdField;
    private JTextField subjectIdField;
    private JTextField marksField;

    private JButton addButton;
    private JButton searchButton;
    private JButton deleteButton;
    private JButton viewButton;
    private JButton resultButton;
    private JButton clearButton;
    private JButton updateButton;

    private JTable marksTable;
    private DefaultTableModel tableModel;

    private final Color primaryBlue = new Color(37, 99, 235);
    private final Color darkBlue = new Color(30, 58, 138);
    private final Color backgroundColor = new Color(245, 248, 255);
    private final Color white = Color.WHITE;

    public MarksFrame() {
        setTitle("School Management System - Marks Management");
        setSize(900, 650);
        setMinimumSize(new Dimension(800, 580));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        );

        // ===== TITLE =====
        JLabel titleLabel = new JLabel(
                "MARKS / RESULT MANAGEMENT",
                SwingConstants.CENTER
        );
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
        titleLabel.setForeground(darkBlue);
        titleLabel.setBorder(
                BorderFactory.createEmptyBorder(5, 0, 10, 0)
        );

        // ===== INPUT FORM =====
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(white);
        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 228, 240)
                        ),
                        BorderFactory.createEmptyBorder(18, 20, 18, 20)
                )
        );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        studentIdField = new JTextField(15);
        subjectIdField = new JTextField(15);
        marksField = new JTextField(15);

        styleField(studentIdField);
        styleField(subjectIdField);
        styleField(marksField);

        addFormRow(formPanel, gbc, 0, "Student ID:", studentIdField);
        addFormRow(formPanel, gbc, 1, "Subject ID:", subjectIdField);
        addFormRow(formPanel, gbc, 2, "Marks (0-100):", marksField);

        // ===== BUTTONS =====
        JPanel buttonPanel = new JPanel(new FlowLayout(
                FlowLayout.CENTER, 10, 10
        ));
        buttonPanel.setBackground(backgroundColor);

        addButton = createButton("Add Marks", primaryBlue);
        searchButton = createButton("Search", darkBlue);
        deleteButton = createButton("Delete", new Color(220, 53, 69));
        viewButton = createButton("View Marks", new Color(13, 148, 136));
        resultButton = createButton("View Result", new Color(124, 58, 237));
        clearButton = createButton("Clear", new Color(100, 116, 139));
        updateButton = createButton("Update Marks", new Color(22, 163, 74));

        buttonPanel.add(addButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(resultButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(updateButton);

        // ===== TABLE =====
        String[] columns = {"Student ID", "Subject ID", "Marks"};

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        marksTable = new JTable(tableModel);
        marksTable.setRowHeight(30);
        marksTable.setFont(new Font("SansSerif", Font.PLAIN, 14));
        marksTable.setSelectionBackground(new Color(219, 234, 254));
        marksTable.setSelectionForeground(Color.BLACK);
        marksTable.setGridColor(new Color(226, 232, 240));
        marksTable.setShowVerticalLines(false);
        marksTable.setAutoCreateRowSorter(true);
        marksTable.setFillsViewportHeight(true);

        marksTable.getTableHeader().setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );
        marksTable.getTableHeader().setBackground(primaryBlue);
        marksTable.getTableHeader().setForeground(Color.BLACK);
        marksTable.getTableHeader().setOpaque(true);
        marksTable.getTableHeader().setReorderingAllowed(false);
        marksTable.getTableHeader().setPreferredSize(
                new Dimension(0, 38)
        );
        marksTable.getTableHeader().setPreferredSize(
                new Dimension(0, 35)
        );

        JScrollPane scrollPane = new JScrollPane(marksTable);
        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 228, 240)
                )
        );

        JPanel tablePanel = new JPanel(new BorderLayout(5, 10));
        tablePanel.setBackground(backgroundColor);

        JLabel tableTitle = new JLabel("Student Marks Records");
        tableTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        tableTitle.setForeground(darkBlue);

        tablePanel.add(tableTitle, BorderLayout.NORTH);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // ===== TOP SECTION =====
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBackground(backgroundColor);
        topPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formAndButtons = new JPanel(new BorderLayout(5, 5));
        formAndButtons.setBackground(backgroundColor);
        formAndButtons.add(formPanel, BorderLayout.CENTER);
        formAndButtons.add(buttonPanel, BorderLayout.SOUTH);

        topPanel.add(formAndButtons, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        add(mainPanel);

        // ===== ADD MARKS =====
        addButton.addActionListener(e -> {
            try {
                int studentId = Integer.parseInt(
                        studentIdField.getText().trim()
                );

                int subjectId = Integer.parseInt(
                        subjectIdField.getText().trim()
                );

                int marks = Integer.parseInt(
                        marksField.getText().trim()
                );

                if (studentId <= 0 || subjectId <= 0) {
                    showMessage(
                            "Student ID and Subject ID must be positive numbers.",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                if (marks < 0 || marks > 100) {
                    showMessage(
                            "Marks must be between 0 and 100!",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                MarksDAO.addMarks(studentId, subjectId, marks);

                showMessage(
                        "Marks added successfully!",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();
                loadMarks();

            } catch (NumberFormatException ex) {
                showMessage(
                        "Please enter valid numeric values in all fields.",
                        JOptionPane.WARNING_MESSAGE
                );
            } catch (Exception ex) {
                showMessage(
                        "Could not add marks. Check that the Student ID and "
                                + "Subject ID exist.\n\n"
                                + ex.getMessage(),
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // ===== UPDATE MARKS =====
        updateButton.addActionListener(e -> {
            try {
                int studentId = Integer.parseInt(
                        studentIdField.getText().trim()
                );

                int subjectId = Integer.parseInt(
                        subjectIdField.getText().trim()
                );

                int marks = Integer.parseInt(
                        marksField.getText().trim()
                );

                if (studentId <= 0 || subjectId <= 0) {
                    showMessage(
                            "Student ID and Subject ID must be positive numbers.",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                if (marks < 0 || marks > 100) {
                    showMessage(
                            "Marks must be between 0 and 100!",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Update marks for Student ID " + studentId
                                + " and Subject ID " + subjectId + "?",
                        "Confirm Update",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }

                boolean updated = MarksDAO.updateMarks(
                        studentId, subjectId, marks
                );

                if (updated) {
                    showMessage(
                            "Marks updated successfully!",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    clearFields();
                    loadMarks();
                } else {
                    showMessage(
                            "No existing marks record found for these IDs.",
                            JOptionPane.WARNING_MESSAGE
                    );
                }

            } catch (NumberFormatException ex) {
                showMessage(
                        "Please enter valid numeric values in all fields.",
                        JOptionPane.WARNING_MESSAGE
                );
            } catch (Exception ex) {
                showMessage(
                        "Could not update marks.\n" + ex.getMessage(),
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // ===== SEARCH MARKS =====
        searchButton.addActionListener(e -> {
            try {
                int studentId = Integer.parseInt(
                        studentIdField.getText().trim()
                );

                int subjectId = Integer.parseInt(
                        subjectIdField.getText().trim()
                );

                String result = MarksDAO.searchMarksForGUI(
                        studentId, subjectId
                );

                showMessage(
                        result,
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (NumberFormatException ex) {
                showMessage(
                        "Enter valid Student ID and Subject ID.",
                        JOptionPane.WARNING_MESSAGE
                );
            } catch (Exception ex) {
                showMessage(
                        "Could not search marks.\n" + ex.getMessage(),
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // ===== DELETE MARKS =====
        deleteButton.addActionListener(e -> {
            try {
                int studentId = Integer.parseInt(
                        studentIdField.getText().trim()
                );

                int subjectId = Integer.parseInt(
                        subjectIdField.getText().trim()
                );

                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Delete marks for Student ID " + studentId
                                + " and Subject ID " + subjectId + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    MarksDAO.deleteMarks(studentId, subjectId);

                    showMessage(
                            "Delete request completed.",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    clearFields();
                    loadMarks();
                }

            } catch (NumberFormatException ex) {
                showMessage(
                        "Enter valid Student ID and Subject ID.",
                        JOptionPane.WARNING_MESSAGE
                );
            } catch (Exception ex) {
                showMessage(
                        "Could not delete marks.\n" + ex.getMessage(),
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // ===== VIEW MARKS =====
        viewButton.addActionListener(e -> loadMarks());

        // ===== VIEW RESULT =====
        resultButton.addActionListener(e -> {
            try {
                int studentId = Integer.parseInt(
                        studentIdField.getText().trim()
                );

                if (studentId <= 0) {
                    showMessage(
                            "Student ID must be a positive number.",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                int[] marks = MarksDAO.getStudentMarks(studentId);

                if (marks == null || marks.length == 0) {
                    showMessage(
                            "No marks found for Student ID: " + studentId,
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    return;
                }

                int total = ResultCalculator.calculateTotal(marks);

                double percentage =
                        ResultCalculator.calculatePercentage(marks);

                String grade =
                        ResultCalculator.calculateGrade(percentage);

                String result =
                        ResultCalculator.calculateResult(marks);

                String subjectWiseMarks =
                        MarksDAO.getSubjectWiseMarks(studentId);

                String message =
                        "----- STUDENT RESULT -----\n\n"
                                + "Student ID: " + studentId + "\n\n"
                                + "Subject-wise Marks\n"
                                + "-------------------------\n"
                                + subjectWiseMarks + "\n"
                                + "-------------------------\n"
                                + "Total Marks: " + total + "\n"
                                + "Percentage: "
                                + String.format("%.2f", percentage) + "%\n"
                                + "Grade: " + grade + "\n"
                                + "Result: " + result;

                showMessage(
                        message,
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (NumberFormatException ex) {
                showMessage(
                        "Enter a valid Student ID.",
                        JOptionPane.WARNING_MESSAGE
                );
            } catch (Exception ex) {
                showMessage(
                        "Could not calculate result.\n" + ex.getMessage(),
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // ===== CLEAR FIELDS =====
        clearButton.addActionListener(e -> clearFields());

        // ===== TABLE ROW SELECTION =====
        marksTable.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }

            int viewRow = marksTable.getSelectedRow();

            if (viewRow < 0) {
                return;
            }

            // Convert sorted table row to original model row.
            int modelRow = marksTable.convertRowIndexToModel(viewRow);

            if (modelRow < 0 || modelRow >= tableModel.getRowCount()) {
                return;
            }

            studentIdField.setText(
                    tableModel.getValueAt(modelRow, 0).toString()
            );

            subjectIdField.setText(
                    tableModel.getValueAt(modelRow, 1).toString()
            );

            marksField.setText(
                    tableModel.getValueAt(modelRow, 2).toString()
            );
        });

        loadMarks();
    }

    // ===== FORM ROW HELPER =====
    private void addFormRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String labelText,
            JTextField field
    ) {
        gbc.gridy = row;

        gbc.gridx = 0;
        gbc.weightx = 0;

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 14));
        label.setForeground(darkBlue);
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(field, gbc);
    }

    // ===== FIELD STYLE =====
    private void styleField(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(200, 34));
        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(203, 213, 225)
                        ),
                        BorderFactory.createEmptyBorder(5, 8, 5, 8)
                )
        );
    }

    // ===== BUTTON STYLE =====
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);

        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setForeground(Color.BLACK);
        button.setBackground(color);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setFocusPainted(false);
        button.setEnabled(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color.darker()),
                BorderFactory.createEmptyBorder(9, 12, 9, 12)
        ));

        return button;
    }

    // ===== LOAD TABLE DATA =====
    private void loadMarks() {
        marksTable.clearSelection();
        tableModel.setRowCount(0);

        MarksDAO.getMarksInTable(tableModel);

        tableModel.fireTableDataChanged();
    }
    // ===== CLEAR INPUTS =====
    private void clearFields() {
        studentIdField.setText("");
        subjectIdField.setText("");
        marksField.setText("");
        marksTable.clearSelection();
        studentIdField.requestFocusInWindow();
    }

    // ===== MESSAGE DIALOG =====
    private void showMessage(String message, int messageType) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Marks Management",
                messageType
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MarksFrame frame = new MarksFrame();
            frame.setVisible(true);
        });
    }
}


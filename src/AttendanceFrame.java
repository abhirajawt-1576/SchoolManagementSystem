import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.table.JTableHeader;

public class AttendanceFrame extends JFrame {

    private JTextField studentIdField;
    private JTextField dateField;
    private JComboBox<String> statusBox;

    private JButton markButton;
    private JButton searchButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton viewButton;
    private JButton clearButton;

    private JTable attendanceTable;
    private DefaultTableModel tableModel;

    private final Color primaryBlue = new Color(37, 99, 235);
    private final Color darkBlue = new Color(30, 58, 138);
    private final Color backgroundColor = new Color(245, 248, 255);
    private final Color white = Color.WHITE;

    public AttendanceFrame() {

        setTitle("School Management System - Attendance");
        setSize(900, 650);
        setMinimumSize(new Dimension(800, 580));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // ===== MAIN PANEL =====
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        );

        // ===== TITLE =====
        JLabel titleLabel = new JLabel(
                "ATTENDANCE MANAGEMENT",
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
        dateField = new JTextField(15);
        statusBox = new JComboBox<>(
                new String[]{"Present", "Absent"}
        );

        styleField(studentIdField);
        styleField(dateField);

        statusBox.setFont(new Font("SansSerif", Font.PLAIN, 14));
        statusBox.setBackground(white);
        statusBox.setPreferredSize(new Dimension(200, 34));

        addFormRow(formPanel, gbc, 0, "Student ID:", studentIdField);
        addFormRow(formPanel, gbc, 1, "Date (yyyy-MM-dd):", dateField);
        addFormRow(formPanel, gbc, 2, "Attendance Status:", statusBox);

        // ===== BUTTONS =====
        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 8, 10)
        );
        buttonPanel.setBackground(backgroundColor);

        markButton = createButton("Mark Attendance", primaryBlue);
        searchButton = createButton("Search", darkBlue);
        updateButton = createButton("Update", new Color(13, 148, 136));
        deleteButton = createButton("Delete", new Color(220, 53, 69));
        viewButton = createButton("View Attendance", new Color(124, 58, 237));
        clearButton = createButton("Clear", new Color(100, 116, 139));

        buttonPanel.add(markButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(clearButton);

        // ===== ATTENDANCE TABLE =====

        String[] columns = {"Student ID", "Date", "Status"};

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        attendanceTable = new JTable(tableModel);

        attendanceTable.setRowHeight(30);
        attendanceTable.setFont(new Font("SansSerif", Font.PLAIN, 14));
        attendanceTable.setForeground(Color.BLACK);
        attendanceTable.setBackground(Color.WHITE);
        attendanceTable.setSelectionBackground(new Color(219, 234, 254));
        attendanceTable.setSelectionForeground(Color.BLACK);
        attendanceTable.setGridColor(new Color(226, 232, 240));
        attendanceTable.setShowVerticalLines(false);
        attendanceTable.setAutoCreateRowSorter(true);
        attendanceTable.setFillsViewportHeight(true);

        JTableHeader header = attendanceTable.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 14));
        header.setBackground(primaryBlue);
        header.setForeground(Color.BLACK);
        header.setOpaque(true);
        header.setReorderingAllowed(false);
        header.setPreferredSize(new Dimension(0, 38));
        header.setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {

                JLabel label = new JLabel(
                        value == null ? "" : value.toString()
                );

                label.setFont(new Font("SansSerif", Font.BOLD, 14));
                label.setForeground(Color.BLACK);
                label.setBackground(new Color(219, 234, 254));
                label.setOpaque(true);
                label.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));

                return label;
            }
        });


        JScrollPane scrollPane = new JScrollPane(attendanceTable);
        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 228, 240)
                )
        );

        JPanel tablePanel = new JPanel(new BorderLayout(5, 10));
        tablePanel.setBackground(backgroundColor);

        JLabel tableTitle = new JLabel("Attendance Records");
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

        // ===== MARK ATTENDANCE =====
        markButton.addActionListener(e -> {

            Integer studentId = readStudentId();
            if (studentId == null) {
                return;
            }

            String date = readDate();
            if (date == null) {
                return;
            }

            String status = statusBox.getSelectedItem().toString();

            try {
                AttendanceDAO.markAttendance(
                        studentId, date, status
                );

                showMessage(
                        "Attendance marked successfully!",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();
                loadAttendance();

            } catch (Exception ex) {
                showError(
                        "Could not mark attendance. Check that the Student ID "
                                + "exists and attendance for this date is not "
                                + "already recorded.\n\n"
                                + ex.getMessage()
                );
            }
        });

        // ===== SEARCH ATTENDANCE =====
        searchButton.addActionListener(e -> {

            Integer studentId = readStudentId();
            if (studentId == null) {
                return;
            }

            String date = readDate();
            if (date == null) {
                return;
            }

            try {
                String result =
                        AttendanceDAO.searchAttendanceForGUI(
                                studentId, date
                        );

                showMessage(
                        result,
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (Exception ex) {
                showError(
                        "Could not search attendance.\n"
                                + ex.getMessage()
                );
            }
        });

        // ===== UPDATE ATTENDANCE =====
        updateButton.addActionListener(e -> {

            Integer studentId = readStudentId();
            if (studentId == null) {
                return;
            }

            String date = readDate();
            if (date == null) {
                return;
            }

            String status = statusBox.getSelectedItem().toString();

            try {
                AttendanceDAO.updateAttendance(
                        studentId, date, status
                );

                showMessage(
                        "Attendance update request completed.",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loadAttendance();

            } catch (Exception ex) {
                showError(
                        "Could not update attendance.\n"
                                + ex.getMessage()
                );
            }
        });

        // ===== DELETE ATTENDANCE =====
        deleteButton.addActionListener(e -> {

            Integer studentId = readStudentId();
            if (studentId == null) {
                return;
            }

            String date = readDate();
            if (date == null) {
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Delete attendance for Student ID "
                            + studentId + " on " + date + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            try {
                AttendanceDAO.deleteAttendance(studentId, date);

                showMessage(
                        "Delete request completed.",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();
                loadAttendance();

            } catch (Exception ex) {
                showError(
                        "Could not delete attendance.\n"
                                + ex.getMessage()
                );
            }
        });

        // ===== VIEW ATTENDANCE =====
        viewButton.addActionListener(e -> loadAttendance());

        // ===== CLEAR FIELDS =====
        clearButton.addActionListener(e -> clearFields());

        // ===== TABLE ROW SELECTION =====
        attendanceTable.getSelectionModel()
                .addListSelectionListener(e -> {

                    if (e.getValueIsAdjusting()) {
                        return;
                    }

                    int viewRow = attendanceTable.getSelectedRow();

                    if (viewRow < 0) {
                        return;
                    }

                    // Sorting ke baad correct model row find karo.
                    int modelRow =
                            attendanceTable.convertRowIndexToModel(viewRow);

                    if (modelRow < 0
                            || modelRow >= tableModel.getRowCount()) {
                        return;
                    }

                    studentIdField.setText(
                            tableModel.getValueAt(modelRow, 0).toString()
                    );

                    dateField.setText(
                            tableModel.getValueAt(modelRow, 1).toString()
                    );

                    statusBox.setSelectedItem(
                            tableModel.getValueAt(modelRow, 2).toString()
                    );
                });

        // Window opens -> load existing records.
        loadAttendance();
    }

    // ===== ADD A FORM ROW =====
    private void addFormRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String labelText,
            JComponent field
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

    // ===== STYLE TEXT FIELDS =====
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

    // ===== STYLE BUTTONS =====


    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);

        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setForeground(Color.WHITE);
        button.setBackground(color);

        // Force the button to display our selected colors
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(true);
        button.setFocusPainted(false);
        button.setEnabled(true);

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color.darker()),
                BorderFactory.createEmptyBorder(9, 12, 9, 12)
        ));

        return button;
    }


    // ===== VALIDATE STUDENT ID =====
    private Integer readStudentId() {
        String input = studentIdField.getText().trim();

        try {
            int studentId = Integer.parseInt(input);

            if (studentId <= 0) {
                showMessage(
                        "Student ID must be a positive number.",
                        JOptionPane.WARNING_MESSAGE
                );
                return null;
            }

            return studentId;

        } catch (NumberFormatException ex) {
            showMessage(
                    "Please enter a valid Student ID.",
                    JOptionPane.WARNING_MESSAGE
            );
            return null;
        }
    }

    // ===== VALIDATE DATE =====
    private String readDate() {
        String date = dateField.getText().trim();

        try {
            if (date.isEmpty()) {
                throw new DateTimeParseException(
                        "Date is empty", date, 0
                );
            }

            // Requires a valid date in yyyy-MM-dd format.
            LocalDate parsedDate = LocalDate.parse(date);

            return parsedDate.toString();

        } catch (DateTimeParseException ex) {
            showMessage(
                    "Enter a valid date in yyyy-MM-dd format.\n"
                            + "Example: 2026-10-09",
                    JOptionPane.WARNING_MESSAGE
            );
            return null;
        }
    }

    // ===== LOAD TABLE DATA =====
    private void loadAttendance() {
        tableModel.setRowCount(0);
        attendanceTable.clearSelection();

        try {
            AttendanceDAO.getAttendanceInTable(tableModel);
            tableModel.fireTableDataChanged();

        } catch (Exception ex) {
            showError(
                    "Could not load attendance records.\n"
                            + ex.getMessage()
            );
        }
    }

    // ===== CLEAR INPUTS =====
    private void clearFields() {
        studentIdField.setText("");
        dateField.setText("");
        statusBox.setSelectedIndex(0);
        attendanceTable.clearSelection();
        studentIdField.requestFocusInWindow();
    }

    // ===== SHOW MESSAGE =====
    private void showMessage(String message, int messageType) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Attendance Management",
                messageType
        );
    }

    // ===== SHOW ERROR =====
    private void showError(String message) {
        showMessage(message, JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AttendanceFrame frame = new AttendanceFrame();
            frame.setVisible(true);
        });
    }
}


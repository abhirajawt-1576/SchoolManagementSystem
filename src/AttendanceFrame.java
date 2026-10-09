import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AttendanceFrame extends JFrame {

    JTextField studentIdField;
    JTextField dateField;
    JComboBox<String> statusBox;

    JButton markButton;
    JButton searchButton;
    JButton updateButton;
    JButton deleteButton;
    JButton viewButton;

    JTable attendanceTable;
    DefaultTableModel tableModel;

    public AttendanceFrame() {

        setTitle("School Management System - Attendance");
        setSize(750, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        // =========================
        // TITLE
        // =========================

        JLabel titleLabel = new JLabel(
                "ATTENDANCE MANAGEMENT",
                SwingConstants.CENTER
        );

        titleLabel.setBounds(180, 15, 390, 35);
        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        // =========================
        // STUDENT ID
        // =========================

        JLabel studentIdLabel =
                new JLabel("Student ID:");

        studentIdLabel.setBounds(
                50, 70, 100, 25
        );

        studentIdField = new JTextField();

        studentIdField.setBounds(
                160, 70, 200, 25
        );

        // =========================
        // DATE
        // =========================

        JLabel dateLabel =
                new JLabel("Date:");

        dateLabel.setBounds(
                400, 70, 100, 25
        );

        dateField = new JTextField();

        dateField.setBounds(
                500, 70, 180, 25
        );

        // =========================
        // STATUS
        // =========================

        JLabel statusLabel =
                new JLabel("Status:");

        statusLabel.setBounds(
                50, 110, 100, 25
        );

        String[] statuses = {
                "Present",
                "Absent"
        };

        statusBox =
                new JComboBox<>(statuses);

        statusBox.setBounds(
                160, 110, 200, 25
        );

        // =========================
        // BUTTONS
        // =========================

        markButton =
                new JButton("Mark Attendance");

        markButton.setBounds(
                30, 155, 140, 35
        );

        searchButton =
                new JButton("Search");

        searchButton.setBounds(
                180, 155, 90, 35
        );

        updateButton =
                new JButton("Update");

        updateButton.setBounds(
                280, 155, 90, 35
        );

        deleteButton =
                new JButton("Delete");

        deleteButton.setBounds(
                380, 155, 90, 35
        );

        viewButton =
                new JButton("View Attendance");

        viewButton.setBounds(
                480, 155, 150, 35
        );

        // =========================
        // TABLE
        // =========================

        String[] columns = {
                "Student ID",
                "Date",
                "Status"
        };

        tableModel =
                new DefaultTableModel(columns, 0);

        attendanceTable =
                new JTable(tableModel);

        JScrollPane scrollPane =
                new JScrollPane(attendanceTable);

        scrollPane.setBounds(
                50, 220, 630, 280
        );

        // =========================
        // MARK ATTENDANCE
        // =========================

        markButton.addActionListener(e -> {

            try {

                int studentId =
                        Integer.parseInt(
                                studentIdField.getText()
                        );

                String date =
                        dateField.getText().trim();

                String status =
                        statusBox
                                .getSelectedItem()
                                .toString();

                if (date.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter the date!",
                            "Invalid Input",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }

                AttendanceDAO.markAttendance(
                        studentId,
                        date,
                        status
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Attendance Marked Successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();
                loadAttendance();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Student ID!",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });

        // =========================
        // SEARCH
        // =========================

        searchButton.addActionListener(e -> {

            try {

                int studentId =
                        Integer.parseInt(
                                studentIdField.getText()
                        );

                String date =
                        dateField.getText().trim();

                if (date.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter the date!",
                            "Invalid Input",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }

                String result =
                        AttendanceDAO.searchAttendanceForGUI(
                                studentId,
                                date
                        );

                JOptionPane.showMessageDialog(
                        this,
                        result,
                        "Attendance Search Result",
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

        // =========================
        // UPDATE ATTENDANCE
        // =========================

        updateButton.addActionListener(e -> {

            try {

                int studentId =
                        Integer.parseInt(
                                studentIdField.getText()
                        );

                String date =
                        dateField.getText().trim();

                String status =
                        statusBox
                                .getSelectedItem()
                                .toString();

                if (date.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter the date!",
                            "Invalid Input",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }

                AttendanceDAO.updateAttendance(
                        studentId,
                        date,
                        status
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Attendance Updated Successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loadAttendance();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Student ID!",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });

        // =========================
        // DELETE
        // =========================

        deleteButton.addActionListener(e -> {

            try {

                int studentId =
                        Integer.parseInt(
                                studentIdField.getText()
                        );

                String date =
                        dateField.getText().trim();

                if (date.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter the date!",
                            "Invalid Input",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }

                int confirm =
                        JOptionPane.showConfirmDialog(
                                this,
                                "Are you sure you want to delete this attendance?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION
                        );

                if (confirm ==
                        JOptionPane.YES_OPTION) {

                    AttendanceDAO.deleteAttendance(
                            studentId,
                            date
                    );

                    JOptionPane.showMessageDialog(
                            this,
                            "Attendance Deleted Successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    clearFields();
                    loadAttendance();
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Student ID!",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });

        // =========================
        // VIEW
        // =========================

        viewButton.addActionListener(e -> {

            loadAttendance();

        });

        // =========================
        // TABLE ROW CLICK
        // =========================

        attendanceTable.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    public void mouseClicked(
                            java.awt.event.MouseEvent e) {

                        int row =
                                attendanceTable
                                        .getSelectedRow();

                        if (row >= 0) {

                            studentIdField.setText(
                                    tableModel
                                            .getValueAt(row, 0)
                                            .toString()
                            );

                            dateField.setText(
                                    tableModel
                                            .getValueAt(row, 1)
                                            .toString()
                            );

                            statusBox.setSelectedItem(
                                    tableModel
                                            .getValueAt(row, 2)
                                            .toString()
                            );
                        }
                    }
                }
        );

        // =========================
        // ADD COMPONENTS
        // =========================

        panel.add(titleLabel);

        panel.add(studentIdLabel);
        panel.add(studentIdField);

        panel.add(dateLabel);
        panel.add(dateField);

        panel.add(statusLabel);
        panel.add(statusBox);

        panel.add(markButton);
        panel.add(searchButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(viewButton);

        panel.add(scrollPane);

        add(panel);

        // Load attendance when window opens
        loadAttendance();
    }

    // =========================
    // LOAD ATTENDANCE
    // =========================

    void loadAttendance() {

        AttendanceDAO.getAttendanceInTable(
                tableModel
        );
    }

    // =========================
    // CLEAR FIELDS
    // =========================

    void clearFields() {

        studentIdField.setText("");
        dateField.setText("");

        statusBox.setSelectedIndex(0);
    }
}
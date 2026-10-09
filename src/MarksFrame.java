import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MarksFrame extends JFrame {

    JTextField studentIdField;
    JTextField subjectIdField;
    JTextField marksField;

    JButton addButton;
    JButton searchButton;
    JButton deleteButton;
    JButton viewButton;
    JButton resultButton;

    JTable marksTable;
    DefaultTableModel tableModel;

    public MarksFrame() {

        setTitle("School Management System - Marks / Result");
        setSize(750, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Title
        JLabel titleLabel = new JLabel(
                "MARKS / RESULT MANAGEMENT",
                SwingConstants.CENTER
        );

        titleLabel.setBounds(180, 15, 390, 35);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        // Student ID
        JLabel studentIdLabel = new JLabel("Student ID:");
        studentIdLabel.setBounds(50, 70, 100, 25);

        studentIdField = new JTextField();
        studentIdField.setBounds(160, 70, 200, 25);

        // Subject ID
        JLabel subjectIdLabel = new JLabel("Subject ID:");
        subjectIdLabel.setBounds(400, 70, 100, 25);

        subjectIdField = new JTextField();
        subjectIdField.setBounds(500, 70, 180, 25);

        // Marks
        JLabel marksLabel = new JLabel("Marks:");
        marksLabel.setBounds(50, 110, 100, 25);

        marksField = new JTextField();
        marksField.setBounds(160, 110, 200, 25);

        // Buttons
        addButton = new JButton("Add Marks");
        addButton.setBounds(50, 155, 110, 35);

        searchButton = new JButton("Search");
        searchButton.setBounds(170, 155, 100, 35);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(280, 155, 100, 35);

        viewButton = new JButton("View Marks");
        viewButton.setBounds(390, 155, 120, 35);

        resultButton = new JButton("View Result");
        resultButton.setBounds(510, 155, 120, 35);
        // Table
        String[] columns = {
                "Student ID",
                "Subject ID",
                "Marks"
        };

        tableModel = new DefaultTableModel(columns, 0);

        marksTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(marksTable);
        scrollPane.setBounds(50, 220, 630, 280);

        // ADD MARKS
        addButton.addActionListener(e -> {

            try {

                int studentId =
                        Integer.parseInt(studentIdField.getText());

                int subjectId =
                        Integer.parseInt(subjectIdField.getText());

                int marks =
                        Integer.parseInt(marksField.getText());

                if (marks < 0 || marks > 100) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Marks must be between 0 and 100!"
                    );

                    return;
                }

                MarksDAO.addMarks(
                        studentId,
                        subjectId,
                        marks
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Marks Added Successfully!"
                );

                clearFields();
                loadMarks();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid numbers!"
                );
            }
        });

        // SEARCH
        searchButton.addActionListener(e -> {

            try {

                int studentId =
                        Integer.parseInt(studentIdField.getText());

                int subjectId =
                        Integer.parseInt(subjectIdField.getText());

                String result =
                        MarksDAO.searchMarksForGUI(
                                studentId,
                                subjectId
                        );

                JOptionPane.showMessageDialog(
                        this,
                        result,
                        "Marks Search Result",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid Student ID and Subject ID!",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });
        // DELETE
        deleteButton.addActionListener(e -> {

            try {

                int studentId =
                        Integer.parseInt(studentIdField.getText());

                int subjectId =
                        Integer.parseInt(subjectIdField.getText());

                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete these marks?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {

                    MarksDAO.deleteMarks(
                            studentId,
                            subjectId
                    );

                    JOptionPane.showMessageDialog(
                            this,
                            "Marks Deleted Successfully!"
                    );

                    clearFields();
                    loadMarks();
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid Student ID and Subject ID!"
                );
            }
        });

        // VIEW
        viewButton.addActionListener(e -> {

            loadMarks();

        });
        resultButton.addActionListener(e -> {

            try {

                int studentId =
                        Integer.parseInt(
                                studentIdField.getText()
                        );

                int[] marks =
                        MarksDAO.getStudentMarks(studentId);

                if (marks.length == 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "No marks found for Student ID: "
                                    + studentId,
                            "Result",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    return;
                }

                int total =
                        ResultCalculator.calculateTotal(marks);

                double percentage =
                        ResultCalculator.calculatePercentage(marks);

                String grade =
                        ResultCalculator.calculateGrade(
                                percentage
                        );

                String result =
                        ResultCalculator.calculateResult(marks);

                String subjectWiseMarks =
                        MarksDAO.getSubjectWiseMarks(studentId);

                String message =
                        "----- STUDENT RESULT -----\n\n" +
                                "Student ID: " + studentId + "\n\n" +
                                "Subject-wise Marks\n" +
                                "-------------------------\n" +
                                subjectWiseMarks +
                                "\n" +
                                "-------------------------\n" +
                                "Total Marks: " + total + "\n" +
                                "Percentage: " +
                                String.format("%.2f", percentage) +
                                "%\n" +
                                "Grade: " + grade + "\n" +
                                "Result: " + result;

                JOptionPane.showMessageDialog(
                        this,
                        message,
                        "Student Result",
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

        // Table row click
        marksTable.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    public void mouseClicked(
                            java.awt.event.MouseEvent e) {

                        int row = marksTable.getSelectedRow();

                        if (row >= 0) {

                            studentIdField.setText(
                                    tableModel
                                            .getValueAt(row, 0)
                                            .toString()
                            );

                            subjectIdField.setText(
                                    tableModel
                                            .getValueAt(row, 1)
                                            .toString()
                            );

                            marksField.setText(
                                    tableModel
                                            .getValueAt(row, 2)
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

        panel.add(subjectIdLabel);
        panel.add(subjectIdField);

        panel.add(marksLabel);
        panel.add(marksField);

        panel.add(addButton);
        panel.add(searchButton);
        panel.add(deleteButton);
        panel.add(viewButton);
        panel.add(resultButton);

        panel.add(scrollPane);

        add(panel);

        // Load marks when window opens
        loadMarks();
    }

    // Load marks into JTable
    void loadMarks() {

        MarksDAO.getMarksInTable(tableModel);

    }

    // Clear fields
    void clearFields() {

        studentIdField.setText("");
        subjectIdField.setText("");
        marksField.setText("");
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MarksFrame frame = new MarksFrame();
            frame.setVisible(true);
        });
    }
}
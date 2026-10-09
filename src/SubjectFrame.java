import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SubjectFrame extends JFrame {

    JTextField idField;
    JTextField nameField;
    JTextField teacherIdField;

    JButton addButton;
    JButton searchButton;
    JButton updateButton;
    JButton deleteButton;
    JButton viewButton;

    JTable subjectTable;
    DefaultTableModel tableModel;

    public SubjectFrame() {

        setTitle("School Management System - Subject Management");
        setSize(750, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Title
        JLabel titleLabel = new JLabel(
                "SUBJECT MANAGEMENT",
                SwingConstants.CENTER
        );

        titleLabel.setBounds(200, 15, 350, 35);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        // Subject ID
        JLabel idLabel = new JLabel("Subject ID:");
        idLabel.setBounds(50, 70, 100, 25);

        idField = new JTextField();
        idField.setBounds(160, 70, 200, 25);

        // Subject Name
        JLabel nameLabel = new JLabel("Subject Name:");
        nameLabel.setBounds(50, 110, 100, 25);

        nameField = new JTextField();
        nameField.setBounds(160, 110, 200, 25);

        // Teacher ID
        JLabel teacherIdLabel = new JLabel("Teacher ID:");
        teacherIdLabel.setBounds(400, 70, 100, 25);

        teacherIdField = new JTextField();
        teacherIdField.setBounds(500, 70, 180, 25);

        // Buttons
        addButton = new JButton("Add");
        addButton.setBounds(50, 155, 100, 35);

        searchButton = new JButton("Search");
        searchButton.setBounds(160, 155, 100, 35);

        updateButton = new JButton("Update");
        updateButton.setBounds(270, 155, 100, 35);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(380, 155, 100, 35);

        viewButton = new JButton("View Subjects");
        viewButton.setBounds(490, 155, 150, 35);

        // Table
        String[] columns = {
                "Subject ID",
                "Subject Name",
                "Teacher ID"
        };

        tableModel = new DefaultTableModel(columns, 0);

        subjectTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(subjectTable);
        scrollPane.setBounds(50, 220, 630, 280);

        // ADD
        addButton.addActionListener(e -> {

            try {

                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int teacherId = Integer.parseInt(teacherIdField.getText());

                if (name.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter Subject Name!"
                    );

                    return;
                }

                SubjectDAO.addSubject(
                        id,
                        name,
                        teacherId
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Subject Added Successfully!"
                );

                clearFields();
                loadSubjects();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid ID values!"
                );
            }
        });

        // SEARCH
        // 7. Search Subject for GUI
        searchButton.addActionListener(e -> {

            try {

                int id =
                        Integer.parseInt(idField.getText());

                String result =
                        SubjectDAO.searchSubjectForGUI(id);

                JOptionPane.showMessageDialog(
                        this,
                        result,
                        "Subject Search Result",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Subject ID!",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });

        // UPDATE
        updateButton.addActionListener(e -> {

            try {

                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int teacherId = Integer.parseInt(teacherIdField.getText());

                SubjectDAO.updateSubject(
                        id,
                        name,
                        teacherId
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Subject Updated Successfully!"
                );

                clearFields();
                loadSubjects();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid ID values!"
                );
            }
        });

        // DELETE
        deleteButton.addActionListener(e -> {

            try {

                int id = Integer.parseInt(idField.getText());

                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this subject?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {

                    SubjectDAO.deleteSubject(id);

                    JOptionPane.showMessageDialog(
                            this,
                            "Subject Deleted Successfully!"
                    );

                    clearFields();
                    loadSubjects();
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Subject ID!"
                );
            }
        });

        // VIEW
        viewButton.addActionListener(e -> {

            loadSubjects();

        });

        // Table row click
        subjectTable.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    public void mouseClicked(
                            java.awt.event.MouseEvent e) {

                        int row = subjectTable.getSelectedRow();

                        if (row >= 0) {

                            idField.setText(
                                    tableModel
                                            .getValueAt(row, 0)
                                            .toString()
                            );

                            nameField.setText(
                                    tableModel
                                            .getValueAt(row, 1)
                                            .toString()
                            );

                            teacherIdField.setText(
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

        panel.add(idLabel);
        panel.add(idField);

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(teacherIdLabel);
        panel.add(teacherIdField);

        panel.add(addButton);
        panel.add(searchButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(viewButton);

        panel.add(scrollPane);

        add(panel);

        // Load subjects when window opens
        loadSubjects();
    }

    // Load subjects into JTable
    void loadSubjects() {

        SubjectDAO.getSubjectsInTable(tableModel);

    }

    // Clear fields
    void clearFields() {

        idField.setText("");
        nameField.setText("");
        teacherIdField.setText("");
    }
}
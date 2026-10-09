import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TeacherFrame extends JFrame {

    JTextField idField;
    JTextField nameField;
    JTextField ageField;
    JTextField subjectField;

    JButton addButton;
    JButton searchButton;
    JButton updateButton;
    JButton deleteButton;
    JButton viewButton;

    JTable teacherTable;
    DefaultTableModel tableModel;

    public TeacherFrame() {

        setTitle("School Management System - Teacher Management");
        setSize(750, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Title
        JLabel titleLabel = new JLabel(
                "TEACHER MANAGEMENT",
                SwingConstants.CENTER
        );

        titleLabel.setBounds(200, 15, 350, 35);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        // Teacher ID
        JLabel idLabel = new JLabel("Teacher ID:");
        idLabel.setBounds(50, 70, 100, 25);

        idField = new JTextField();
        idField.setBounds(160, 70, 200, 25);

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 110, 100, 25);

        nameField = new JTextField();
        nameField.setBounds(160, 110, 200, 25);

        // Age
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(400, 70, 100, 25);

        ageField = new JTextField();
        ageField.setBounds(500, 70, 180, 25);

        // Subject
        JLabel subjectLabel = new JLabel("Subject:");
        subjectLabel.setBounds(400, 110, 100, 25);

        subjectField = new JTextField();
        subjectField.setBounds(500, 110, 180, 25);

        // Buttons
        addButton = new JButton("Add");
        addButton.setBounds(50, 155, 100, 35);

        searchButton = new JButton("Search");
        searchButton.setBounds(160, 155, 100, 35);

        updateButton = new JButton("Update");
        updateButton.setBounds(270, 155, 100, 35);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(380, 155, 100, 35);

        viewButton = new JButton("View Teachers");
        viewButton.setBounds(490, 155, 150, 35);

        // Table
        String[] columns = {
                "Teacher ID",
                "Name",
                "Age",
                "Subject"
        };

        tableModel = new DefaultTableModel(columns, 0);

        teacherTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(teacherTable);
        scrollPane.setBounds(50, 220, 630, 280);

        // ADD
        addButton.addActionListener(e -> {

            try {

                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String subject = subjectField.getText();

                if (name.isEmpty() || subject.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please fill all fields!"
                    );

                    return;
                }

                TeacherDAO.addTeacher(
                        id,
                        name,
                        age,
                        subject
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Teacher Added Successfully!"
                );

                clearFields();
                loadTeachers();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid ID and Age!"
                );
            }
        });

        // SEARCH
        searchButton.addActionListener(e -> {

            try {

                int id =
                        Integer.parseInt(idField.getText());

                String result =
                        TeacherDAO.searchTeacherForGUI(id);

                JOptionPane.showMessageDialog(
                        this,
                        result,
                        "Teacher Search Result",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Teacher ID!",
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
                int age = Integer.parseInt(ageField.getText());
                String subject = subjectField.getText();

                TeacherDAO.updateTeacher(
                        id,
                        name,
                        age,
                        subject
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Teacher Updated Successfully!"
                );

                clearFields();
                loadTeachers();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid ID and Age!"
                );
            }
        });

        // DELETE
        deleteButton.addActionListener(e -> {

            try {

                int id = Integer.parseInt(idField.getText());

                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this teacher?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {

                    TeacherDAO.deleteTeacher(id);

                    JOptionPane.showMessageDialog(
                            this,
                            "Teacher Deleted Successfully!"
                    );

                    clearFields();
                    loadTeachers();
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Teacher ID!"
                );
            }
        });

        // VIEW
        viewButton.addActionListener(e -> {

            loadTeachers();

        });

        // Click table row
        teacherTable.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    public void mouseClicked(
                            java.awt.event.MouseEvent e) {

                        int row = teacherTable.getSelectedRow();

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

                            ageField.setText(
                                    tableModel
                                            .getValueAt(row, 2)
                                            .toString()
                            );

                            subjectField.setText(
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

        panel.add(idLabel);
        panel.add(idField);

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(ageLabel);
        panel.add(ageField);

        panel.add(subjectLabel);
        panel.add(subjectField);

        panel.add(addButton);
        panel.add(searchButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(viewButton);

        panel.add(scrollPane);

        add(panel);

        // Load data when window opens
        loadTeachers();
    }

    // Load teachers into table
    void loadTeachers() {

        TeacherDAO.getTeachersInTable(tableModel);

    }

    // Clear fields
    void clearFields() {

        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        subjectField.setText("");
    }
}
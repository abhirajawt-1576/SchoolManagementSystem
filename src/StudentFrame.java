import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentFrame extends JFrame {

    JTextField idField;
    JTextField nameField;
    JTextField ageField;
    JTextField classField;

    JButton addButton;
    JButton searchButton;
    JButton updateButton;
    JButton deleteButton;
    JButton viewButton;

    JTable studentTable;
    DefaultTableModel tableModel;

    public StudentFrame() {

        setTitle("School Management System - Student Management");
        setSize(750, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Title
        JLabel titleLabel = new JLabel(
                "STUDENT MANAGEMENT",
                SwingConstants.CENTER
        );

        titleLabel.setBounds(200, 15, 350, 35);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        // Student ID
        JLabel idLabel = new JLabel("Student ID:");
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

        // Class
        JLabel classLabel = new JLabel("Class:");
        classLabel.setBounds(400, 110, 100, 25);

        classField = new JTextField();
        classField.setBounds(500, 110, 180, 25);

        // Add Button
        addButton = new JButton("Add");
        addButton.setBounds(50, 155, 100, 35);

        // Search Button
        searchButton = new JButton("Search");
        searchButton.setBounds(160, 155, 100, 35);

        // Update Button
        updateButton = new JButton("Update");
        updateButton.setBounds(270, 155, 100, 35);

        // Delete Button
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(380, 155, 100, 35);

        // View Button
        viewButton = new JButton("View Students");
        viewButton.setBounds(490, 155, 150, 35);

        // Table
        String[] columns = {
                "Student ID",
                "Name",
                "Age",
                "Class"
        };

        tableModel = new DefaultTableModel(columns, 0);

        studentTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBounds(50, 220, 630, 280);


        // ADD STUDENT
        addButton.addActionListener(e -> {

            try {

                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String studentClass = classField.getText();

                if (name.isEmpty() || studentClass.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please fill all fields!"
                    );

                    return;
                }

                StudentDAO.addStudent(
                        id,
                        name,
                        age,
                        studentClass
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Student Added Successfully!"
                );

                clearFields();

                loadStudents();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid ID and Age!"
                );
            }
        });


        // SEARCH STUDENT
        searchButton.addActionListener(e -> {

            try {

                int id =
                        Integer.parseInt(idField.getText());

                String result =
                        StudentDAO.searchStudentForGUI(id);

                JOptionPane.showMessageDialog(
                        this,
                        result,
                        "Student Search Result",
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


        // UPDATE STUDENT
        updateButton.addActionListener(e -> {

            try {

                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String studentClass = classField.getText();

                StudentDAO.updateStudent(
                        id,
                        name,
                        age,
                        studentClass
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Student Updated Successfully!"
                );

                clearFields();

                loadStudents();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid ID and Age!"
                );
            }
        });


        // DELETE STUDENT
        deleteButton.addActionListener(e -> {

            try {

                int id = Integer.parseInt(idField.getText());

                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this student?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {

                    StudentDAO.deleteStudent(id);

                    JOptionPane.showMessageDialog(
                            this,
                            "Student Deleted Successfully!"
                    );

                    clearFields();

                    loadStudents();
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a valid Student ID!"
                );
            }
        });


        // VIEW STUDENTS
        viewButton.addActionListener(e -> {

            loadStudents();

        });


        // Table row click
        studentTable.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent e) {

                int row = studentTable.getSelectedRow();

                if (row >= 0) {

                    idField.setText(
                            tableModel.getValueAt(row, 0).toString()
                    );

                    nameField.setText(
                            tableModel.getValueAt(row, 1).toString()
                    );

                    ageField.setText(
                            tableModel.getValueAt(row, 2).toString()
                    );

                    classField.setText(
                            tableModel.getValueAt(row, 3).toString()
                    );
                }
            }
        });


        // Add components
        panel.add(titleLabel);

        panel.add(idLabel);
        panel.add(idField);

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(ageLabel);
        panel.add(ageField);

        panel.add(classLabel);
        panel.add(classField);

        panel.add(addButton);
        panel.add(searchButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(viewButton);

        panel.add(scrollPane);

        add(panel);

        // Load students when window opens
        loadStudents();
    }


    // Load students into JTable
    void loadStudents() {

        StudentDAO.getStudentsInTable(tableModel);

    }


    // Clear input fields
    void clearFields() {

        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        classField.setText("");
    }
}
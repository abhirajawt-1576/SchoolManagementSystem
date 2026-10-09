import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {

        setTitle("School Management System - Dashboard");
        setSize(700, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(15, 15));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        );

        // =========================
        // HEADER
        // =========================

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(2, 1));

        JLabel titleLabel = new JLabel(
                "SCHOOL MANAGEMENT SYSTEM",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        JLabel dashboardLabel = new JLabel(
                "DASHBOARD",
                SwingConstants.CENTER
        );

        dashboardLabel.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        headerPanel.add(titleLabel);
        headerPanel.add(dashboardLabel);

        // =========================
        // BUTTON PANEL
        // =========================

        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(
                new GridLayout(4, 2, 15, 15)
        );

        JButton studentButton =
                new JButton("Student Management");

        JButton teacherButton =
                new JButton("Teacher Management");

        JButton subjectButton =
                new JButton("Subject Management");

        JButton marksButton =
                new JButton("Marks / Result");

        JButton attendanceButton =
                new JButton("Attendance");

        JButton feesButton =
                new JButton("Fees Management");

        JButton logoutButton =
                new JButton("Logout");

        // Button font
        Font buttonFont =
                new Font("Arial", Font.BOLD, 15);

        studentButton.setFont(buttonFont);
        teacherButton.setFont(buttonFont);
        subjectButton.setFont(buttonFont);
        marksButton.setFont(buttonFont);
        attendanceButton.setFont(buttonFont);
        feesButton.setFont(buttonFont);
        logoutButton.setFont(buttonFont);

        // =========================
        // BUTTON ACTIONS
        // =========================

        studentButton.addActionListener(e -> {

            StudentFrame studentFrame =
                    new StudentFrame();

            studentFrame.setVisible(true);
        });

        teacherButton.addActionListener(e -> {

            TeacherFrame teacherFrame =
                    new TeacherFrame();

            teacherFrame.setVisible(true);
        });

        subjectButton.addActionListener(e -> {

            SubjectFrame subjectFrame =
                    new SubjectFrame();

            subjectFrame.setVisible(true);
        });

        marksButton.addActionListener(e -> {

            MarksFrame marksFrame =
                    new MarksFrame();

            marksFrame.setVisible(true);
        });

        attendanceButton.addActionListener(e -> {

            AttendanceFrame attendanceFrame =
                    new AttendanceFrame();

            attendanceFrame.setVisible(true);
        });

        feesButton.addActionListener(e -> {

            FeesFrame feesFrame =
                    new FeesFrame();

            feesFrame.setVisible(true);
        });

        logoutButton.addActionListener(e -> {

            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {

                dispose();

                LoginFrame loginFrame =
                        new LoginFrame();

                loginFrame.setVisible(true);
            }
        });

        // =========================
        // ADD BUTTONS
        // =========================

        buttonPanel.add(studentButton);
        buttonPanel.add(teacherButton);

        buttonPanel.add(subjectButton);
        buttonPanel.add(marksButton);

        buttonPanel.add(attendanceButton);
        buttonPanel.add(feesButton);

        // Empty space
        buttonPanel.add(new JLabel(""));

        buttonPanel.add(logoutButton);

        // =========================
        // FOOTER
        // =========================

        JLabel footerLabel = new JLabel(
                "School Management System | Java + MySQL",
                SwingConstants.CENTER
        );

        footerLabel.setFont(
                new Font("Arial", Font.PLAIN, 12)
        );

        // =========================
        // MAIN PANEL
        // =========================

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                footerLabel,
                BorderLayout.SOUTH
        );

        add(mainPanel);
    }
}
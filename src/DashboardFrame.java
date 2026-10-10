
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private static final Color NAVY = new Color(20, 45, 85);
    private static final Color BLUE = new Color(45, 105, 190);
    private static final Color BACKGROUND = new Color(242, 246, 252);
    private static final Color TEXT = new Color(40, 55, 75);
    private static final Color MUTED = new Color(110, 125, 145);
    private static final Color WHITE = Color.WHITE;

    public DashboardFrame() {

        setTitle("School Management System - Dashboard");
        setSize(1050, 720);
        setMinimumSize(new Dimension(850, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BACKGROUND);

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setBackground(NAVY);
        sidebar.setPreferredSize(new Dimension(225, 0));
        sidebar.setBorder(new EmptyBorder(30, 18, 25, 18));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel("SMS");
        logo.setFont(new Font("Arial", Font.BOLD, 30));
        logo.setForeground(WHITE);
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel sideTitle = new JLabel(
                "<html>School<br>Management<br>System</html>"
        );
        sideTitle.setFont(new Font("Arial", Font.BOLD, 19));
        sideTitle.setForeground(WHITE);
        sideTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel sideSubtitle = new JLabel("ADMINISTRATION");
        sideSubtitle.setFont(new Font("Arial", Font.BOLD, 10));
        sideSubtitle.setForeground(new Color(190, 210, 240));
        sideSubtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(15));
        sidebar.add(sideTitle);
        sidebar.add(Box.createVerticalStrut(8));
        sidebar.add(sideSubtitle);
        sidebar.add(Box.createVerticalStrut(45));

        JLabel menuLabel = new JLabel("MAIN MENU");
        menuLabel.setFont(new Font("Arial", Font.BOLD, 11));
        menuLabel.setForeground(new Color(175, 195, 225));
        menuLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(menuLabel);
        sidebar.add(Box.createVerticalStrut(15));

        JButton homeButton = createSidebarButton("Dashboard", true);
        homeButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    this,
                    "You are already on the Dashboard.",
                    "Dashboard",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });
        sidebar.add(homeButton);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(createSidebarButton("Students", false));
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(createSidebarButton("Teachers", false));
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(createSidebarButton("Subjects", false));
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(createSidebarButton("Marks / Results", false));
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(createSidebarButton("Attendance", false));
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(createSidebarButton("Fees", false));

        // Make sidebar menu buttons open the existing module windows
        Component[] menuComponents = sidebar.getComponents();
        for (Component component : menuComponents) {
            if (component instanceof JButton button
                    && !button.getText().equals("Dashboard")) {

                button.addActionListener(e -> openModule(button.getText()));
            }
        }

        sidebar.add(Box.createVerticalGlue());

        JLabel sidebarFooter = new JLabel("Java + MySQL");
        sidebarFooter.setForeground(new Color(190, 210, 240));
        sidebarFooter.setFont(new Font("Arial", Font.PLAIN, 12));
        sidebarFooter.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(sidebarFooter);

        // Main content area
        JPanel content = new JPanel(new BorderLayout(0, 22));
        content.setBackground(BACKGROUND);
        content.setBorder(new EmptyBorder(28, 30, 22, 30));

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel welcome = new JLabel("Welcome to your Dashboard");
        welcome.setFont(new Font("Arial", Font.BOLD, 27));
        welcome.setForeground(NAVY);

        JLabel description = new JLabel(
                "Manage students, staff, academic records and school fees."
        );
        description.setFont(new Font("Arial", Font.PLAIN, 14));
        description.setForeground(MUTED);

        header.add(welcome);
        header.add(Box.createVerticalStrut(8));
        header.add(description);

        // Summary cards
        JPanel summaryPanel = new JPanel(new GridLayout(1, 3, 14, 0));
        summaryPanel.setOpaque(false);

        summaryPanel.add(createSummaryCard(
                "STUDENT RECORDS", "Manage students"
        ));
        summaryPanel.add(createSummaryCard(
                "ACADEMIC RECORDS", "Subjects and results"
        ));
        summaryPanel.add(createSummaryCard(
                "SCHOOL SERVICES", "Attendance and fees"
        ));

        // Module cards
        JPanel modulesPanel = new JPanel(new GridLayout(3, 2, 16, 16));
        modulesPanel.setOpaque(false);

        modulesPanel.add(createModuleCard(
                "01", "Student Management",
                "Add, search, update and view students.",
                () -> openModule("Students")
        ));

        modulesPanel.add(createModuleCard(
                "02", "Teacher Management",
                "Manage teacher information.",
                () -> openModule("Teachers")
        ));

        modulesPanel.add(createModuleCard(
                "03", "Subject Management",
                "Maintain subjects and teacher assignments.",
                () -> openModule("Subjects")
        ));

        modulesPanel.add(createModuleCard(
                "04", "Marks / Results",
                "Record marks and calculate results.",
                () -> openModule("Marks / Results")
        ));

        modulesPanel.add(createModuleCard(
                "05", "Attendance",
                "Record and review student attendance.",
                () -> openModule("Attendance")
        ));

        modulesPanel.add(createModuleCard(
                "06", "Fees Management",
                "Manage fee records and payment status.",
                () -> openModule("Fees")
        ));

        JPanel center = new JPanel(new BorderLayout(0, 20));
        center.setOpaque(false);
        center.add(summaryPanel, BorderLayout.NORTH);
        center.add(modulesPanel, BorderLayout.CENTER);

        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(false);

        JLabel footerText = new JLabel(
                "School Management System  |  Java Swing + MySQL"
        );
        footerText.setFont(new Font("Arial", Font.PLAIN, 11));
        footerText.setForeground(MUTED);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 12));
        logoutButton.setForeground(WHITE);
        logoutButton.setBackground(BLUE);
        logoutButton.setFocusPainted(false);
        logoutButton.setBorderPainted(false);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.setBorder(new EmptyBorder(10, 20, 10, 20));

        logoutButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                dispose();
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            }
        });

        footer.add(footerText, BorderLayout.WEST);
        footer.add(logoutButton, BorderLayout.EAST);

        content.add(header, BorderLayout.NORTH);
        content.add(center, BorderLayout.CENTER);
        content.add(footer, BorderLayout.SOUTH);

        root.add(sidebar, BorderLayout.WEST);
        root.add(content, BorderLayout.CENTER);

        add(root);
    }

    private JButton createSidebarButton(String text, boolean active) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setForeground(WHITE);
        button.setBackground(active ? BLUE : NAVY);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        button.setPreferredSize(new Dimension(185, 42));
        button.setBorder(new EmptyBorder(10, 12, 10, 8));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private JPanel createSummaryCard(String title, String subtitle) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(222, 231, 243)
                ),
                new EmptyBorder(15, 16, 15, 16)
        ));

        JLabel heading = new JLabel(title);
        heading.setFont(new Font("Arial", Font.BOLD, 11));
        heading.setForeground(BLUE);

        JLabel detail = new JLabel(subtitle);
        detail.setFont(new Font("Arial", Font.PLAIN, 12));
        detail.setForeground(MUTED);

        panel.add(heading);
        panel.add(Box.createVerticalStrut(8));
        panel.add(detail);

        return panel;
    }

    private JPanel createModuleCard(
            String number,
            String title,
            String description,
            Runnable action
    ) {
        JPanel card = new JPanel(new BorderLayout(0, 10));
        card.setBackground(WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(222, 231, 243)
                ),
                new EmptyBorder(15, 17, 15, 17)
        ));

        JLabel numberLabel = new JLabel("MODULE " + number);
        numberLabel.setFont(new Font("Arial", Font.BOLD, 10));
        numberLabel.setForeground(BLUE);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(NAVY);

        JLabel descriptionLabel = new JLabel(
                "<html>" + description + "</html>"
        );
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descriptionLabel.setForeground(MUTED);

        JButton openButton = new JButton("Open Module  >");
        openButton.setFont(new Font("Arial", Font.BOLD, 12));
        openButton.setForeground(BLUE);
        openButton.setBackground(new Color(235, 243, 253));
        openButton.setFocusPainted(false);
        openButton.setBorderPainted(false);
        openButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        openButton.setHorizontalAlignment(SwingConstants.LEFT);
        openButton.setBorder(new EmptyBorder(8, 10, 8, 10));
        openButton.addActionListener(e -> action.run());

        JPanel top = new JPanel();
        top.setOpaque(false);
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.add(numberLabel);
        top.add(Box.createVerticalStrut(7));
        top.add(titleLabel);
        top.add(Box.createVerticalStrut(6));
        top.add(descriptionLabel);

        card.add(top, BorderLayout.CENTER);
        card.add(openButton, BorderLayout.SOUTH);

        return card;
    }

    private void openModule(String module) {
        switch (module) {
            case "Students" -> new StudentFrame().setVisible(true);
            case "Teachers" -> new TeacherFrame().setVisible(true);
            case "Subjects" -> new SubjectFrame().setVisible(true);
            case "Marks / Results" -> new MarksFrame().setVisible(true);
            case "Attendance" -> new AttendanceFrame().setVisible(true);
            case "Fees" -> new FeesFrame().setVisible(true);
            default -> JOptionPane.showMessageDialog(
                    this,
                    "Unknown module: " + module,
                    "Navigation Error",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DashboardFrame frame = new DashboardFrame();
            frame.setVisible(true);
        });
    }
}
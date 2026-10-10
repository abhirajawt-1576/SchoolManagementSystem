
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JCheckBox showPasswordCheckBox;

    private static final Color NAVY = new Color(20, 45, 85);
    private static final Color BLUE = new Color(45, 105, 190);
    private static final Color LIGHT_BLUE = new Color(235, 243, 253);
    private static final Color TEXT_COLOR = new Color(40, 55, 75);
    private static final Color MUTED = new Color(115, 130, 150);
    private static final Color WHITE = Color.WHITE;

    public LoginFrame() {

        setTitle("School Management System | Login");
        setSize(900, 560);
        setMinimumSize(new Dimension(760, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.setBackground(WHITE);

        // Left branding panel
        JPanel brandingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_RENDERING,
                        RenderingHints.VALUE_RENDER_QUALITY
                );

                GradientPaint gradient = new GradientPaint(
                        0, 0, NAVY,
                        getWidth(), getHeight(), BLUE
                );

                g2.setPaint(gradient);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };

        brandingPanel.setLayout(new BoxLayout(
                brandingPanel, BoxLayout.Y_AXIS
        ));
        brandingPanel.setBorder(
                new EmptyBorder(45, 35, 35, 35)
        );

        JLabel schoolIcon = new JLabel("SMS");
        schoolIcon.setFont(new Font("Arial", Font.BOLD, 34));
        schoolIcon.setForeground(WHITE);

        JLabel brandTitle = new JLabel(
                "<html>School<br>Management<br>System</html>"
        );
        brandTitle.setFont(new Font("Arial", Font.BOLD, 30));
        brandTitle.setForeground(WHITE);

        JLabel brandDescription = new JLabel(
                "<html>One place to manage<br>"
                        + "your school's daily activities.</html>"
        );
        brandDescription.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );
        brandDescription.setForeground(
                new Color(220, 232, 250)
        );

        brandingPanel.add(schoolIcon);
        brandingPanel.add(Box.createVerticalStrut(35));
        brandingPanel.add(brandTitle);
        brandingPanel.add(Box.createVerticalStrut(18));
        brandingPanel.add(brandDescription);
        brandingPanel.add(Box.createVerticalStrut(38));

        brandingPanel.add(createFeatureLabel("Student Management"));
        brandingPanel.add(Box.createVerticalStrut(13));
        brandingPanel.add(createFeatureLabel("Teacher Management"));
        brandingPanel.add(Box.createVerticalStrut(13));
        brandingPanel.add(createFeatureLabel("Attendance and Results"));
        brandingPanel.add(Box.createVerticalGlue());

        JLabel brandFooter = new JLabel("JAVA  |  MYSQL  |  JDBC");
        brandFooter.setFont(new Font("Arial", Font.BOLD, 11));
        brandFooter.setForeground(new Color(205, 220, 245));
        brandingPanel.add(brandFooter);

        // Right login panel
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(LIGHT_BLUE);
        rightPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

        JPanel loginCard = new JPanel();
        loginCard.setLayout(new BoxLayout(
                loginCard, BoxLayout.Y_AXIS
        ));
        loginCard.setBackground(WHITE);
        loginCard.setBorder(
                new EmptyBorder(32, 30, 28, 30)
        );

        JLabel welcomeLabel = new JLabel("Welcome Back!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 27));
        welcomeLabel.setForeground(NAVY);
        welcomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitleLabel = new JLabel(
                "Sign in to continue to your account"
        );
        subtitleLabel.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );
        subtitleLabel.setForeground(MUTED);
        subtitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel usernameLabel = createFieldLabel("Username");

        usernameField = new JTextField();
        styleTextField(usernameField);
        usernameField.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 44)
        );
        usernameField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel passwordLabel = createFieldLabel("Password");

        passwordField = new JPasswordField();
        styleTextField(passwordField);
        passwordField.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 44)
        );
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);

        showPasswordCheckBox = new JCheckBox("Show password");
        showPasswordCheckBox.setFont(
                new Font("Arial", Font.PLAIN, 12)
        );
        showPasswordCheckBox.setForeground(MUTED);
        showPasswordCheckBox.setBackground(WHITE);
        showPasswordCheckBox.setFocusPainted(false);
        showPasswordCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);

        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('\u2022');
            }
        });

        loginButton = new JButton("SIGN IN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 15));
        loginButton.setBackground(BLUE);
        loginButton.setForeground(WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );
        loginButton.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 48)
        );
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel footerLabel = new JLabel(
                "School Management System"
        );
        footerLabel.setFont(
                new Font("Arial", Font.PLAIN, 11)
        );
        footerLabel.setForeground(MUTED);
        footerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginCard.add(welcomeLabel);
        loginCard.add(Box.createVerticalStrut(8));
        loginCard.add(subtitleLabel);
        loginCard.add(Box.createVerticalStrut(30));

        loginCard.add(usernameLabel);
        loginCard.add(Box.createVerticalStrut(8));
        loginCard.add(usernameField);
        loginCard.add(Box.createVerticalStrut(20));

        loginCard.add(passwordLabel);
        loginCard.add(Box.createVerticalStrut(8));
        loginCard.add(passwordField);
        loginCard.add(Box.createVerticalStrut(8));

        loginCard.add(showPasswordCheckBox);
        loginCard.add(Box.createVerticalStrut(24));
        loginCard.add(loginButton);
        loginCard.add(Box.createVerticalStrut(25));
        loginCard.add(footerLabel);

        loginCard.setPreferredSize(new Dimension(350, 405));
        rightPanel.add(loginCard);

        mainPanel.add(brandingPanel);
        mainPanel.add(rightPanel);

        add(mainPanel);

        // Preserve existing database login and dashboard navigation
        loginButton.addActionListener(e -> performLogin());

        usernameField.addActionListener(
                e -> passwordField.requestFocusInWindow()
        );

        passwordField.addActionListener(e -> performLogin());

        getRootPane().setDefaultButton(loginButton);
    }

    private JLabel createFeatureLabel(String text) {
        JLabel label = new JLabel("\u2713   " + text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(WHITE);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JLabel createFieldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setForeground(TEXT_COLOR);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private void styleTextField(JTextField field) {
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setForeground(TEXT_COLOR);
        field.setBackground(WHITE);
        field.setCaretColor(BLUE);

        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(210, 220, 235)
                ),
                new EmptyBorder(8, 12, 8, 12)
        ));
    }

    private void performLogin() {

        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter both username and password.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        loginButton.setEnabled(false);
        loginButton.setText("SIGNING IN...");

        try {
            boolean success = LoginDAO.login(username, password);

            if (success) {
                JOptionPane.showMessageDialog(
                        this,
                        "Login successful! Welcome, " + username + ".",
                        "Welcome",
                        JOptionPane.INFORMATION_MESSAGE
                );

                dispose();

                DashboardFrame dashboard = new DashboardFrame();
                dashboard.setLocationRelativeTo(null);
                dashboard.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid username or password.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );

                passwordField.setText("");
                passwordField.requestFocusInWindow();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Unable to complete login. Please check your database connection.",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE
            );
            ex.printStackTrace();

        } finally {
            if (isDisplayable()) {
                loginButton.setEnabled(true);
                loginButton.setText("SIGN IN");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame frame = new LoginFrame();
            frame.setVisible(true);
        });
    }
}
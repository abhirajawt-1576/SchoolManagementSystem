import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;

    public LoginFrame() {

        setTitle("School Management System - Login");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // =========================
        // MAIN PANEL
        // =========================

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 40, 25, 40
                )
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
                new Font("Arial", Font.BOLD, 24)
        );

        JLabel loginLabel = new JLabel(
                "ADMIN LOGIN",
                SwingConstants.CENTER
        );

        loginLabel.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        headerPanel.add(titleLabel);
        headerPanel.add(loginLabel);

        // =========================
        // FORM PANEL
        // =========================

        JPanel formPanel = new JPanel();

        formPanel.setLayout(
                new GridLayout(4, 2, 10, 15)
        );

        JLabel usernameLabel =
                new JLabel("Username:");

        usernameLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        usernameField =
                new JTextField();

        usernameField.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        JLabel passwordLabel =
                new JLabel("Password:");

        passwordLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        passwordField =
                new JPasswordField();

        passwordField.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        loginButton =
                new JButton("LOGIN");

        loginButton.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        // Empty labels for spacing
        JLabel emptyLabel1 = new JLabel("");
        JLabel emptyLabel2 = new JLabel("");

        formPanel.add(usernameLabel);
        formPanel.add(usernameField);

        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        formPanel.add(emptyLabel1);
        formPanel.add(loginButton);

        formPanel.add(emptyLabel2);
        formPanel.add(new JLabel(""));

        // =========================
        // LOGIN ACTION
        // =========================

        loginButton.addActionListener(e -> {

            String username =
                    usernameField.getText().trim();

            String password =
                    new String(
                            passwordField.getPassword()
                    );

            // Empty field validation
            if (username.isEmpty() ||
                    password.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter username and password!",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // Database login
            boolean success =
                    LoginDAO.login(
                            username,
                            password
                    );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Login Successful!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                dispose();

                DashboardFrame dashboard =
                        new DashboardFrame();

                dashboard.setVisible(true);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Username or Password!",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );

                passwordField.setText("");
            }
        });

        // =========================
        // FOOTER
        // =========================

        JLabel footerLabel =
                new JLabel(
                        "Please login to continue",
                        SwingConstants.CENTER
                );

        footerLabel.setFont(
                new Font("Arial", Font.PLAIN, 12)
        );

        // =========================
        // ADD TO MAIN PANEL
        // =========================

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                footerLabel,
                BorderLayout.SOUTH
        );

        add(mainPanel);
    }

    // =========================
    // MAIN METHOD
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            LoginFrame frame =
                    new LoginFrame();

            frame.setVisible(true);
        });
    }
}
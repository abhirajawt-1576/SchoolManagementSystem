
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        // Start the application safely on Swing's Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {

            try {
                // Open the Login screen
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);

            } catch (Exception e) {

                e.printStackTrace();

                JOptionPane.showMessageDialog(
                        null,
                        "Unable to open the School Management System.\n"
                                + "Error: " + e.getMessage(),
                        "Application Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}

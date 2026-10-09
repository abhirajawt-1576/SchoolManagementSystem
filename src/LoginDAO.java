import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * LoginDAO handles user authentication
 * by verifying username and password
 * from the database.
 */
public class LoginDAO {
    /**
     * Verifies the user's login credentials
     * using the users table in the database.
     *
     * @param username username entered by the user
     * @param password password entered by the user
     * @return true if login is successful, otherwise false
     */

    public static boolean login(String username, String password) {

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Login successful!");
                System.out.println("Welcome, " + username);
                return true;
            } else {
                System.out.println("Invalid username or password!");
                return false;
            }

        } catch (SQLException e) {

            System.out.println("Login error!");
            e.printStackTrace();
            return false;
        }
    }
}
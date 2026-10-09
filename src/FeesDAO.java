import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * FeesDAO handles all database operations
 * related to student fees such as adding,
 * searching, updating, deleting, and viewing
 * fee records.
 */

public class FeesDAO {

    /**
     * Adds a new fee record for a student.
     */

    // 1. Add Fees
    public static void addFees(int studentId, double amount, String date, String status) {

        String sql = "INSERT INTO fees (studentId, amount, date, status) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setDouble(2, amount);
            ps.setString(3, date);
            ps.setString(4, status);

            ps.executeUpdate();

            System.out.println("Fees added successfully!");

        } catch (SQLException e) {

            System.out.println("Error adding fees!");
            e.printStackTrace();
        }
    }

    /**
     * Retrieves and displays all fee records
     * from the database.
     */

    // 2. View Fees
    public static void viewFees() {

        String sql = "SELECT * FROM fees";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n----- Fees List -----");

            while (rs.next()) {

                System.out.println("Student ID: " + rs.getInt("studentId"));
                System.out.println("Amount: " + rs.getDouble("amount"));
                System.out.println("Date: " + rs.getString("date"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println("-----------------------");
            }

        } catch (SQLException e) {

            System.out.println("Error viewing fees!");
            e.printStackTrace();
        }
    }

    /**
     * Searches fee records for a particular student.
     *
     * @param studentId student ID
     */

    // 3. Search Fees
    public static void searchFees(int studentId) {

        String sql = "SELECT * FROM fees WHERE studentId = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println("\nStudent ID: " + rs.getInt("studentId"));
                System.out.println("Amount: " + rs.getDouble("amount"));
                System.out.println("Date: " + rs.getString("date"));
                System.out.println("Status: " + rs.getString("status"));
            }

            if (!found) {
                System.out.println("Fees record not found!");
            }

        } catch (SQLException e) {

            System.out.println("Error searching fees!");
            e.printStackTrace();
        }
    }

    /**
     * Updates the fee information of a student.
     *
     * @param studentId student ID
     */

    // 4. Update Fees
    public static void updateFees(int studentId, double amount, String date, String status) {

        String sql = "UPDATE fees SET amount = ?, date = ?, status = ? WHERE studentId = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, amount);
            ps.setString(2, date);
            ps.setString(3, status);
            ps.setInt(4, studentId);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Fees updated successfully!");

            } else {

                System.out.println("Fees record not found!");
            }

        } catch (SQLException e) {

            System.out.println("Error updating fees!");
            e.printStackTrace();
        }
    }

    /**
     * Deletes the fee record of a student.
     *
     * @param studentId student ID
     */

    // 5. Delete Fees
    public static void deleteFees(int studentId) {

        String sql = "DELETE FROM fees WHERE studentId = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Fees deleted successfully!");

            } else {

                System.out.println("Fees record not found!");
            }

        } catch (SQLException e) {

            System.out.println("Error deleting fees!");
            e.printStackTrace();
        }
    }

    /**
     * Loads fee records into the GUI table.
     *
     * @param model table model used by the FeesFrame
     */

    // 6. Get Fees for GUI Table
    public static void getFeesInTable(
            javax.swing.table.DefaultTableModel model) {

        String sql = "SELECT studentId, amount, date, status FROM fees";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Purana table data clear
            model.setRowCount(0);

            // Database se fees read karo
            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("studentId"),
                        rs.getDouble("amount"),
                        rs.getString("date"),
                        rs.getString("status")
                });
            }

        } catch (SQLException e) {

            System.out.println("Error loading fees!");
            e.printStackTrace();
        }
    }

    /**
     * Searches for fee information and returns the details
     * in a format suitable for displaying in the GUI.
     *
     * @param studentId student ID
     * @return fee details or an error message
     */

    // 7. Search Fees for GUI
    public static String searchFeesForGUI(int studentId) {

        String sql =
                "SELECT * FROM fees WHERE studentId = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String result =
                        "Fee Record Found!\n\n" +
                                "Student ID: " + rs.getInt("studentId") + "\n" +
                                "Amount: " + rs.getDouble("amount") + "\n" +
                                "Date: " + rs.getString("date") + "\n" +
                                "Status: " + rs.getString("status");

                return result;

            } else {

                return "Fee record not found!";
            }

        } catch (SQLException e) {

            e.printStackTrace();

            return "Error searching fees!";
        }
    }
}
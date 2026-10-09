import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * AttendanceDAO handles all database operations
 * related to student attendance such as marking,
 * searching, deleting, and viewing attendance records.
 */

public class AttendanceDAO {

    /**
     * Records attendance for a student.
     */
    // 1. Mark Attendance
    public static void markAttendance(int studentId, String date, String status) {

        String sql = "INSERT INTO attendance (studentId, date, status) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setString(2, date);
            ps.setString(3, status);

            ps.executeUpdate();

            System.out.println("Attendance marked successfully!");

        } catch (SQLException e) {

            System.out.println("Error marking attendance!");
            e.printStackTrace();
        }
    }

    /**
     * Retrieves and displays all attendance records
     * from the database.
     */


    // 2. View Attendance
    public static void viewAttendance() {

        String sql = "SELECT * FROM attendance";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n----- Attendance List -----");

            while (rs.next()) {

                System.out.println("Student ID: " + rs.getInt("studentId"));
                System.out.println("Date: " + rs.getString("date"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println("-----------------------");
            }

        } catch (SQLException e) {

            System.out.println("Error viewing attendance!");
            e.printStackTrace();
        }
    }

    /**
     * Searches attendance records for a particular student.
     *
     * @param studentId student ID
     */

    // 3. Search Attendance
    public static void searchAttendance(int studentId) {

        String sql = "SELECT * FROM attendance WHERE studentId = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println("\nStudent ID: " + rs.getInt("studentId"));
                System.out.println("Date: " + rs.getString("date"));
                System.out.println("Status: " + rs.getString("status"));
            }

            if (!found) {
                System.out.println("Attendance not found!");
            }

        } catch (SQLException e) {

            System.out.println("Error searching attendance!");
            e.printStackTrace();
        }
    }

    /**
     * Deletes an attendance record using student ID and date.
     *
     * @param studentId student ID
     * @param date attendance date
     */

    // 4. Delete Attendance
    public static void deleteAttendance(int studentId, String date) {

        String sql = "DELETE FROM attendance WHERE studentId = ? AND date = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setString(2, date);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Attendance deleted successfully!");

            } else {

                System.out.println("Attendance not found!");
            }

        } catch (SQLException e) {

            System.out.println("Error deleting attendance!");
            e.printStackTrace();
        }
    }

    /**
     * Loads attendance records into the GUI table.
     *
     * @param model table model used by the AttendanceFrame
     */

    // 5. Get Attendance for GUI Table
    public static void getAttendanceInTable(
            javax.swing.table.DefaultTableModel model) {

        String sql = "SELECT studentId, date, status FROM attendance";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Purana table data clear
            model.setRowCount(0);

            // Database se attendance read karo
            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("studentId"),
                        rs.getString("date"),
                        rs.getString("status")
                });
            }

        } catch (SQLException e) {

            System.out.println("Error loading attendance!");
            e.printStackTrace();
        }
    }

    /**
     * Searches for attendance and returns the details
     * in a format suitable for displaying in the GUI.
     *
     * @param studentId student ID
     * @param date attendance date
     * @return attendance details or an error message
     */

    // 6. Search Attendance for GUI
    public static String searchAttendanceForGUI(
            int studentId,
            String date) {

        String sql =
                "SELECT * FROM attendance " +
                        "WHERE studentId = ? AND date = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setString(2, date);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String result =
                        "Attendance Found!\n\n" +
                                "Student ID: " + rs.getInt("studentId") + "\n" +
                                "Date: " + rs.getString("date") + "\n" +
                                "Status: " + rs.getString("status");

                return result;

            } else {

                return "Attendance record not found!";
            }

        } catch (SQLException e) {

            e.printStackTrace();

            return "Error searching attendance!";
        }
    }
    // 7. Update Attendance
    public static void updateAttendance(
            int studentId,
            String date,
            String status) {

        String sql =
                "UPDATE attendance SET status = ? " +
                        "WHERE studentId = ? AND date = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, studentId);
            ps.setString(3, date);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Attendance updated successfully!"
                );

            } else {

                System.out.println(
                        "Attendance record not found!"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error updating attendance!"
            );

            e.printStackTrace();
        }
    }
}
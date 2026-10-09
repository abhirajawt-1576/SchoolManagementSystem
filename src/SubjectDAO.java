import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * SubjectDAO handles all database operations
 * related to subjects such as adding, searching,
 * updating, deleting, and viewing subject records.
 */

public class SubjectDAO {

    /**
     * Adds a new subject record to the database.
     */

    // 1. Add Subject
    public static void addSubject(int id, String name, int teacherId) {

        String sql = "INSERT INTO subjects (id, name, teacherId) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, teacherId);

            ps.executeUpdate();

            System.out.println("Subject added successfully!");

        } catch (SQLException e) {

            System.out.println("Error adding subject!");
            e.printStackTrace();
        }
    }

    /**
     * Retrieves and displays all subject records
     * from the database.
     */

    // 2. View Subjects
    public static void viewSubjects() {

        String sql = "SELECT * FROM subjects";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n----- Subject List -----");

            while (rs.next()) {

                System.out.println("Subject ID: " + rs.getInt("id"));
                System.out.println("Subject Name: " + rs.getString("name"));
                System.out.println("Teacher ID: " + rs.getInt("teacherId"));
                System.out.println("-----------------------");
            }

        } catch (SQLException e) {

            System.out.println("Error viewing subjects!");
            e.printStackTrace();
        }
    }

    /**
     * Searches for a subject using the subject ID.
     *
     * @param id subject ID
     */

    // 3. Search Subject
    public static void searchSubject(int id) {

        String sql = "SELECT * FROM subjects WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\nSubject Found!");

                System.out.println("Subject ID: " + rs.getInt("id"));
                System.out.println("Subject Name: " + rs.getString("name"));
                System.out.println("Teacher ID: " + rs.getInt("teacherId"));

            } else {

                System.out.println("Subject not found!");
            }

        } catch (SQLException e) {

            System.out.println("Error searching subject!");
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing subject record.
     */

    // 4. Update Subject
    public static void updateSubject(int id, String name, int teacherId) {

        String sql = "UPDATE subjects SET name = ?, teacherId = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, teacherId);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Subject updated successfully!");

            } else {

                System.out.println("Subject not found!");
            }

        } catch (SQLException e) {

            System.out.println("Error updating subject!");
            e.printStackTrace();
        }
    }

    /**
     * Deletes a subject record using the subject ID.
     *
     * @param id subject ID
     */

    // 5. Delete Subject
    public static void deleteSubject(int id) {

        String sql = "DELETE FROM subjects WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Subject deleted successfully!");

            } else {

                System.out.println("Subject not found!");
            }

        } catch (SQLException e) {

            System.out.println("Error deleting subject!");
            e.printStackTrace();
        }
    }

    /**
     * Loads subject records into the GUI table.
     *
     * @param model table model used by the SubjectFrame
     */

    // 6. Get Subjects for GUI Table
    public static void getSubjectsInTable(
            javax.swing.table.DefaultTableModel model) {

        String sql = "SELECT id, name, teacherId FROM subjects";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Purana table data clear
            model.setRowCount(0);

            // Database se subjects read karo
            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("teacherId")
                });
            }

        } catch (SQLException e) {

            System.out.println("Error loading subjects!");
            e.printStackTrace();
        }
    }

    /**
     * Searches for a subject and returns the details
     * in a format suitable for displaying in the GUI.
     *
     * @param id subject ID
     * @return subject details or an error message
     */

    // 7. Search Subject for GUI
    public static String searchSubjectForGUI(int id) {

        String sql = "SELECT * FROM subjects WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String result =
                        "Subject Found!\n\n" +
                                "ID: " + rs.getInt("id") + "\n" +
                                "Name: " + rs.getString("name") + "\n" +
                                "Teacher ID: " + rs.getInt("teacherId");

                return result;

            } else {

                return "Subject not found!";
            }

        } catch (SQLException e) {

            e.printStackTrace();

            return "Error searching subject!";
        }
    }
}
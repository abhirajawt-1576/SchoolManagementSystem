import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * TeacherDAO handles all database operations
 * related to teachers such as adding, searching,
 * updating, deleting, and viewing teacher records.
 */

public class TeacherDAO {

    /**
     * Adds a new teacher record to the database.
     */

    // 1. Add Teacher
    public static void addTeacher(int id, String name, int age, String subject) {

        String sql = "INSERT INTO teachers (id, name, age, subject) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setString(4, subject);

            ps.executeUpdate();

            System.out.println("Teacher added successfully!");

        } catch (SQLException e) {

            System.out.println("Error adding teacher!");
            e.printStackTrace();
        }
    }

    /**
     * Retrieves and displays all teacher records.
     */

    // 2. View Teachers
    public static void viewTeachers() {

        String sql = "SELECT * FROM teachers";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n----- Teacher List -----");

            while (rs.next()) {

                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Subject: " + rs.getString("subject"));
                System.out.println("-----------------------");
            }

        } catch (SQLException e) {

            System.out.println("Error viewing teachers!");
            e.printStackTrace();
        }
    }

    /**
     * Searches for a teacher using the teacher ID.
     *
     * @param id teacher ID
     */
    // 3. Search Teacher
    public static void searchTeacher(int id) {

        String sql = "SELECT * FROM teachers WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\nTeacher Found!");

                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Subject: " + rs.getString("subject"));

            } else {

                System.out.println("Teacher not found!");
            }

        } catch (SQLException e) {

            System.out.println("Error searching teacher!");
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing teacher record.
     */


    // 4. Update Teacher
    public static void updateTeacher(int id, String name, int age, String subject) {

        String sql = "UPDATE teachers SET name = ?, age = ?, subject = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, subject);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Teacher updated successfully!");

            } else {

                System.out.println("Teacher not found!");
            }

        } catch (SQLException e) {

            System.out.println("Error updating teacher!");
            e.printStackTrace();
        }
    }

    /**
     * Deletes a teacher record using the teacher ID.
     *
     * @param id teacher ID
     */

    // 5. Delete Teacher
    public static void deleteTeacher(int id) {

        String sql = "DELETE FROM teachers WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Teacher deleted successfully!");

            } else {

                System.out.println("Teacher not found!");
            }

        } catch (SQLException e) {

            System.out.println("Error deleting teacher!");
            e.printStackTrace();
        }
    }

    /**
     * Loads teacher records into the GUI table.
     *
     * @param model table model used by the TeacherFrame
     */

    // 6. Get Teachers for GUI Table
    public static void getTeachersInTable(
            javax.swing.table.DefaultTableModel model) {

        String sql = "SELECT id, name, age, subject FROM teachers";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Purana table data clear
            model.setRowCount(0);

            // Database se teachers read karo
            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("subject")
                });
            }

        } catch (SQLException e) {

            System.out.println("Error loading teachers!");
            e.printStackTrace();
        }
    }

    /**
     * Searches for a teacher and returns the details
     * in a format suitable for displaying in the GUI.
     *
     * @param id teacher ID
     * @return teacher details or an error message
     */

    // 7. Search Teacher for GUI
    public static String searchTeacherForGUI(int id) {

        String sql = "SELECT * FROM teachers WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String result =
                        "Teacher Found!\n\n" +
                                "ID: " + rs.getInt("id") + "\n" +
                                "Name: " + rs.getString("name") + "\n" +
                                "Age: " + rs.getInt("age") + "\n" +
                                "Subject: " + rs.getString("subject");

                return result;

            } else {

                return "Teacher not found!";
            }

        } catch (SQLException e) {

            e.printStackTrace();

            return "Error searching teacher!";
        }
    }
}
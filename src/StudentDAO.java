import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * StudentDAO handles all database operations
 * related to students such as adding, searching,
 * updating, deleting, and viewing student records.
 */
public class StudentDAO {
    /**
     * Adds a new student record to the database.
     *
     * @param student student object containing student details
     */

    // 1. Add Student
    public static void addStudent(int id, String name, int age, String studentClass) {

        String sql = "INSERT INTO students (id, name, age, class) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setString(4, studentClass);

            ps.executeUpdate();

            System.out.println("Student added successfully!");

        } catch (SQLException e) {

            System.out.println("Error adding student!");
            e.printStackTrace();
        }
    }

    /**
     * Retrieves and displays all student records
     * from the database.
     */

    // 2. View Students
    public static void viewStudents() {

        String sql = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n----- Student List -----");

            while (rs.next()) {

                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Class: " + rs.getString("class"));
                System.out.println("-----------------------");
            }

        } catch (SQLException e) {

            System.out.println("Error viewing students!");
            e.printStackTrace();
        }
    }

    /**
     * Searches for a student using the student ID.
     *
     * @param id student ID
     */

    // 3. Search Student
    public static void searchStudent(int id) {

        String sql = "SELECT * FROM students WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\nStudent Found!");

                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Class: " + rs.getString("class"));

            } else {

                System.out.println("Student not found!");
            }

        } catch (SQLException e) {

            System.out.println("Error searching student!");
            e.printStackTrace();
        }
    }


    /**
     * Updates an existing student record.
     *
     * @param student student object containing updated details
     */
    // 4. Update Student
    public static void updateStudent(
            int id,
            String name,
            int age,
            String studentClass) {

        String sql =
                "UPDATE students SET name = ?, age = ?, class = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, studentClass);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Student updated successfully!");

            } else {

                System.out.println("Student not found!");
            }

        } catch (SQLException e) {

            System.out.println("Error updating student!");
            e.printStackTrace();
        }
    }

    /**
     * Deletes a student record using the student ID.
     *
     * @param id student ID
     */

    // 5. Delete Student
    public static void deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Student deleted successfully!");

            } else {

                System.out.println("Student not found!");
            }

        } catch (SQLException e) {

            System.out.println("Error deleting student!");
            e.printStackTrace();
        }
    }

    /**
     * Loads student records into the GUI table.
     *
     * @param tableModel table model used by the StudentFrame
     */
        // 6. Get Students for GUI Table
        public static void getStudentsInTable(javax.swing.table.DefaultTableModel model) {

            String sql = "SELECT id, name, age, `class` FROM students";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                model.setRowCount(0);

                while (rs.next()) {

                    model.addRow(new Object[]{
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("class")
                    });
                }

            } catch (SQLException e) {

                System.out.println("Error loading students!");
                e.printStackTrace();
            }
        }

    /**
     * Searches for a student and returns the details
     * in a format suitable for displaying in the GUI.
     *
     * @param id student ID
     * @return student details or an error message
     */
    // 7. Search Student for GUI
    public static String searchStudentForGUI(int id) {

        String sql = "SELECT * FROM students WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String result =
                        "Student Found!\n\n" +
                                "ID: " + rs.getInt("id") + "\n" +
                                "Name: " + rs.getString("name") + "\n" +
                                "Age: " + rs.getInt("age") + "\n" +
                                "Class: " + rs.getString("class");

                return result;

            } else {

                return "Student not found!";
            }

        } catch (SQLException e) {

            e.printStackTrace();

            return "Error searching student!";
        }
    }
}
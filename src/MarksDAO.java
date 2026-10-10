import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MarksDAO handles all database operations
 * related to student marks such as adding,
 * searching, deleting, and viewing marks.
 */

public class MarksDAO {

    /**
     * Adds marks for a student in a subject.
     */

    // 1. Add Marks
    public static void addMarks(int studentId, int subjectId, int marks) {

        String sql = "INSERT INTO marks (studentId, subjectId, marks) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, subjectId);
            ps.setInt(3, marks);

            ps.executeUpdate();

            System.out.println("Marks added successfully!");

        } catch (SQLException e) {

            System.out.println("Error adding marks!");
            e.printStackTrace();
        }
    }

    /**
     * Retrieves and displays all marks records
     * from the database.
     */

    // 2. View Marks
    public static void viewMarks() {

        String sql = "SELECT * FROM marks";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n----- Marks List -----");

            while (rs.next()) {

                System.out.println("Student ID: " + rs.getInt("studentId"));
                System.out.println("Subject ID: " + rs.getInt("subjectId"));
                System.out.println("Marks: " + rs.getInt("marks"));
                System.out.println("-----------------------");
            }

        } catch (SQLException e) {

            System.out.println("Error viewing marks!");
            e.printStackTrace();
        }
    }

    /**
     * Searches for the result of a student
     * and calculates total, average, and pass/fail status.
     *
     * @param studentId student ID
     */

    // 3. Search Student Result
    public static void searchStudentResult(int studentId) {

        String sql = "SELECT * FROM marks WHERE studentId = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            int total = 0;
            int count = 0;

            while (rs.next()) {

                System.out.println("\nSubject ID: " + rs.getInt("subjectId"));
                System.out.println("Marks: " + rs.getInt("marks"));

                total += rs.getInt("marks");
                count++;
            }

            if (count == 0) {

                System.out.println("No result found!");
                return;
            }

            double average = (double) total / count;

            System.out.println("\n===== RESULT =====");
            System.out.println("Total Marks: " + total);
            System.out.println("Average: " + average);

            if (average >= 40) {
                System.out.println("Result: PASS");
            } else {
                System.out.println("Result: FAIL");
            }

        } catch (SQLException e) {

            System.out.println("Error searching result!");
            e.printStackTrace();
        }
    }

    /**
     * Deletes marks using student ID and subject ID.
     *
     * @param studentId student ID
     * @param subjectId subject ID
     */

    // 4. Delete Marks
    public static void deleteMarks(int studentId, int subjectId) {

        String sql = "DELETE FROM marks WHERE studentId = ? AND subjectId = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, subjectId);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Marks deleted successfully!");

            } else {

                System.out.println("Marks not found!");
            }

        } catch (SQLException e) {

            System.out.println("Error deleting marks!");
            e.printStackTrace();
        }
    }

    /**
     * Loads marks records into the GUI table.
     *
     * @param model table model used by the MarksFrame
     */

    // 5. Get Marks for GUI Table
    public static void getMarksInTable(
            javax.swing.table.DefaultTableModel model) {

        String sql = "SELECT studentId, subjectId, marks FROM marks";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Purana table data clear
            model.setRowCount(0);

            // Database se marks read karo
            while (rs.next()) {

                model.addRow(new Object[]{
                        rs.getInt("studentId"),
                        rs.getInt("subjectId"),
                        rs.getInt("marks")
                });
            }

        } catch (SQLException e) {

            System.out.println("Error loading marks!");
            e.printStackTrace();
        }
    }

    /**
     * Searches and displays all marks
     * belonging to a particular student.
     *
     * @param studentId student ID
     */

    // Search Marks by Student ID
    public static void searchMarks(int studentId) {

        String sql = "SELECT * FROM marks WHERE studentId = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            System.out.println("\n----- Marks -----");

            while (rs.next()) {

                found = true;

                System.out.println(
                        "Student ID: " + rs.getInt("studentId")
                );

                System.out.println(
                        "Subject ID: " + rs.getInt("subjectId")
                );

                System.out.println(
                        "Marks: " + rs.getInt("marks")
                );

                System.out.println("----------------");
            }

            if (!found) {
                System.out.println("No marks found for this student!");
            }

        } catch (SQLException e) {

            System.out.println("Error searching marks!");
            e.printStackTrace();
        }
    }

    /**
     * Searches for marks using student ID and subject ID
     * and returns the details for displaying in the GUI.
     *
     * @param studentId student ID
     * @param subjectId subject ID
     * @return marks details or an error message
     */

    // 6. Search Marks for GUI
    public static String searchMarksForGUI(int studentId, int subjectId) {

        String sql =
                "SELECT * FROM marks WHERE studentId = ? AND subjectId = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, subjectId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String result =
                        "Marks Found!\n\n" +
                                "Student ID: " + rs.getInt("studentId") + "\n" +
                                "Subject ID: " + rs.getInt("subjectId") + "\n" +
                                "Marks: " + rs.getInt("marks");

                return result;

            } else {

                return "Marks record not found!";
            }

        } catch (SQLException e) {

            e.printStackTrace();

            return "Error searching marks!";
        }
    }

    // 7. Get all marks of a student for Result
    public static int[] getStudentMarks(int studentId) {

        String sql =
                "SELECT marks FROM marks WHERE studentId = ?";

        java.util.ArrayList<Integer> marksList =
                new java.util.ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                marksList.add(
                        rs.getInt("marks")
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error getting student marks!"
            );

            e.printStackTrace();
        }

        int[] marks =
                new int[marksList.size()];

        for (int i = 0; i < marksList.size(); i++) {

            marks[i] = marksList.get(i);
        }

        return marks;
    }

    // 8. Get Subject ID and Marks of a student for Result
    public static String getSubjectWiseMarks(int studentId) {

        String sql =
                "SELECT subjectId, marks FROM marks WHERE studentId = ?";

        StringBuilder result = new StringBuilder();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            result.append("Subject ID : Marks\n");
            result.append("-------------------------\n");

            boolean found = false;

            while (rs.next()) {

                found = true;

                int subjectId = rs.getInt("subjectId");
                int marks = rs.getInt("marks");

                result.append("Subject ")
                        .append(subjectId)
                        .append(" : ")
                        .append(marks)
                        .append("\n");
            }

            if (!found) {
                return "";
            }

        } catch (SQLException e) {

            System.out.println("Error getting subject-wise marks!");
            e.printStackTrace();

            return "";
        }

        return result.toString();
    }
    // 9. Update existing marks
    public static boolean updateMarks(int studentId, int subjectId, int marks)
            throws SQLException {

        String sql = "UPDATE marks SET marks = ? WHERE studentId = ? AND subjectId = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, marks);
            ps.setInt(2, studentId);
            ps.setInt(3, subjectId);

            int rows = ps.executeUpdate();

            return rows > 0;
        }
    }
}
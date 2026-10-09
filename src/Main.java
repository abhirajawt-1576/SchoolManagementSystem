import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        if (!Login.showLogin()) {
            System.out.println("Access denied!");
            return;
        }

        System.out.println("\nLogin successful!");
        System.out.println("Opening School Management System...");
        while (true) {

            System.out.println("\n================================");
            System.out.println("   SCHOOL MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Student Management");
            System.out.println("2. Teacher Management");
            System.out.println("3. Subject Management");
            System.out.println("4. Marks / Result Management");
            System.out.println("5. Attendance Management");
            System.out.println("6. Fees Management");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    studentMenu();
                    break;

                case 2:
                    teacherMenu();
                    break;

                case 3:
                    subjectMenu();
                    break;

                case 4:
                    marksMenu();
                    break;

                case 5:
                    attendanceMenu();
                    break;

                case 6:
                    feesMenu();
                    break;

                case 7:
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ================= STUDENT MANAGEMENT =================

    static void studentMenu() {

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void addStudent() {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        StudentDAO.addStudent(id, name, age, course);

    }

    static void viewStudents() {

        StudentDAO.viewStudents();
    }


    static void searchStudent() {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        StudentDAO.searchStudent(id);
    }

    static void updateStudent() {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter New Name: ");
        String name = sc.nextLine();

        System.out.print("Enter New Age: ");
        int age = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter New Course: ");
        String course = sc.nextLine();

        StudentDAO.updateStudent(id, name, age, course);
    }

    static void deleteStudent() {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        StudentDAO.deleteStudent(id);
    }

    // ================= TEACHER MANAGEMENT =================

    static void teacherMenu() {

        while (true) {

            System.out.println("\n===== TEACHER MANAGEMENT =====");
            System.out.println("1. Add Teacher");
            System.out.println("2. View Teachers");
            System.out.println("3. Search Teacher");
            System.out.println("4. Update Teacher");
            System.out.println("5. Delete Teacher");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addTeacher();
                    break;

                case 2:
                    viewTeachers();
                    break;

                case 3:
                    searchTeacher();
                    break;

                case 4:
                    updateTeacher();
                    break;

                case 5:
                    deleteTeacher();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ================= ADD TEACHER =================

    static void addTeacher() {

        System.out.print("Enter Teacher ID: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Teacher Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Subject: ");
        String subject = sc.nextLine();

        // Send teacher data to database
        TeacherDAO.addTeacher(id, name, age, subject);
    }


// ================= VIEW TEACHERS =================

    static void viewTeachers() {

        // Get all teachers from database
        TeacherDAO.viewTeachers();
    }


// ================= SEARCH TEACHER =================

    static void searchTeacher() {

        System.out.print("Enter Teacher ID: ");
        int id = sc.nextInt();

        // Search teacher in database
        TeacherDAO.searchTeacher(id);
    }


// ================= UPDATE TEACHER =================

    static void updateTeacher() {

        System.out.print("Enter Teacher ID: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter New Name: ");
        String name = sc.nextLine();

        System.out.print("Enter New Age: ");
        int age = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter New Subject: ");
        String subject = sc.nextLine();

        // Update teacher in database
        TeacherDAO.updateTeacher(id, name, age, subject);
    }


// ================= DELETE TEACHER =================

    static void deleteTeacher() {

        System.out.print("Enter Teacher ID: ");
        int id = sc.nextInt();

        // Delete teacher from database
        TeacherDAO.deleteTeacher(id);
    }

    // ================= SUBJECT MANAGEMENT =================

    static void subjectMenu() {

        while (true) {

            System.out.println("\n===== SUBJECT MANAGEMENT =====");
            System.out.println("1. Add Subject");
            System.out.println("2. View Subjects");
            System.out.println("3. Search Subject");
            System.out.println("4. Update Subject");
            System.out.println("5. Delete Subject");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addSubject();
                    break;

                case 2:
                    viewSubjects();
                    break;

                case 3:
                    searchSubject();
                    break;

                case 4:
                    updateSubject();
                    break;

                case 5:
                    deleteSubject();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
// ================= ADD SUBJECT =================

    static void addSubject() {

        System.out.print("Enter Subject ID: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Subject Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Teacher ID: ");
        int teacherId = sc.nextInt();

        SubjectDAO.addSubject(id, name, teacherId);
    }
    // ================= VIEW SUBJECTS =================

    static void viewSubjects() {

        SubjectDAO.viewSubjects();
    }


// ================= SEARCH SUBJECT =================

    static void searchSubject() {

        System.out.print("Enter Subject ID: ");
        int id = sc.nextInt();

        SubjectDAO.searchSubject(id);
    }

// ================= UPDATE SUBJECT =================

    static void updateSubject() {

        System.out.print("Enter Subject ID: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter New Subject Name: ");
        String name = sc.nextLine();

        System.out.print("Enter New Teacher ID: ");
        int teacherId = sc.nextInt();

        SubjectDAO.updateSubject(id, name, teacherId);
    }
// ================= DELETE SUBJECT =================

    static void deleteSubject() {

        System.out.print("Enter Subject ID: ");
        int id = sc.nextInt();

        SubjectDAO.deleteSubject(id);
    }

    // ================= MARKS MANAGEMENT =================

    static void marksMenu() {

        while (true) {

            System.out.println("\n===== MARKS / RESULT MANAGEMENT =====");
            System.out.println("1. Add Marks");
            System.out.println("2. View Marks");
            System.out.println("3. Search Student Result");
            System.out.println("4. Delete Marks");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addMarks();
                    break;

                case 2:
                    viewMarks();
                    break;

                case 3:
                    searchStudentResult();
                    break;

                case 4:
                    deleteMarks();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void addMarks() {

        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();

        System.out.print("Enter Subject ID: ");
        int subjectId = sc.nextInt();

        System.out.print("Enter Marks (0-100): ");
        int marks = sc.nextInt();

        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks!");
            return;
        }

        MarksDAO.addMarks(studentId, subjectId, marks);
    }


    static void viewMarks() {

        MarksDAO.viewMarks();
    }


    static void searchStudentResult() {

        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();

        MarksDAO.searchStudentResult(studentId);
    }


    static void deleteMarks() {

        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();

        System.out.print("Enter Subject ID: ");
        int subjectId = sc.nextInt();

        MarksDAO.deleteMarks(studentId, subjectId);
    }

    // ================= ATTENDANCE MANAGEMENT =================

    static void attendanceMenu() {

        while (true) {

            System.out.println("\n===== ATTENDANCE MANAGEMENT =====");
            System.out.println("1. Mark Attendance");
            System.out.println("2. View Attendance");
            System.out.println("3. Search Student Attendance");
            System.out.println("4. Delete Attendance");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    markAttendance();
                    break;

                case 2:
                    viewAttendance();
                    break;

                case 3:
                    searchStudentAttendance();
                    break;

                case 4:
                    deleteAttendance();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void markAttendance() {

        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = sc.nextLine();

        System.out.print("Enter Status (Present/Absent): ");
        String status = sc.nextLine();

        if (!status.equalsIgnoreCase("Present") &&
                !status.equalsIgnoreCase("Absent")) {

            System.out.println("Invalid attendance status!");
            return;
        }

        AttendanceDAO.markAttendance(studentId, date, status);
    }


    static void viewAttendance() {

        AttendanceDAO.viewAttendance();
    }


    static void searchStudentAttendance() {

        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();

        AttendanceDAO.searchAttendance(studentId);
    }


    static void deleteAttendance() {

        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = sc.nextLine();

        AttendanceDAO.deleteAttendance(studentId, date);
    }

    // ================= FEES MANAGEMENT =================

    static void feesMenu() {

        while (true) {

            System.out.println("\n===== FEES MANAGEMENT =====");
            System.out.println("1. Add Fees");
            System.out.println("2. View Fees");
            System.out.println("3. Search Student Fees");
            System.out.println("4. Update Fees");
            System.out.println("5. Delete Fees");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addFees();
                    break;

                case 2:
                    viewFees();
                    break;

                case 3:
                    searchFees();
                    break;

                case 4:
                    updateFees();
                    break;

                case 5:
                    deleteFees();
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void addFees() {

            System.out.print("Enter Student ID: ");
            int studentId = sc.nextInt();

            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();

            sc.nextLine();

            System.out.print("Enter Date (YYYY-MM-DD): ");
            String date = sc.nextLine();

            System.out.print("Enter Status (Paid/Pending): ");
            String status = sc.nextLine();

            if (!status.equalsIgnoreCase("Paid") &&
                    !status.equalsIgnoreCase("Pending")) {

                System.out.println("Invalid fee status!");
                return;
            }

            FeesDAO.addFees(studentId, amount, date, status);
        }


    static void viewFees() {

        FeesDAO.viewFees();
    }


    static void searchFees() {

        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();

        FeesDAO.searchFees(studentId);
    }


    static void updateFees() {

        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter New Amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter New Date (YYYY-MM-DD): ");
        String date = sc.nextLine();

        System.out.print("Enter New Status (Paid/Pending): ");
        String status = sc.nextLine();

        if (!status.equalsIgnoreCase("Paid") &&
                !status.equalsIgnoreCase("Pending")) {

            System.out.println("Invalid fee status!");
            return;
        }

        FeesDAO.updateFees(studentId, amount, date, status);
    }


    static void deleteFees() {

        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();

        FeesDAO.deleteFees(studentId);
    }
}
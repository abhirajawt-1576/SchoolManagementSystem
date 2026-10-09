public class Attendance {

    int studentId;
    String date;
    String status;

    Attendance(int studentId, String date, String status) {
        this.studentId = studentId;
        this.date = date;
        this.status = status;
    }

    void displayAttendance() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Date: " + date);
        System.out.println("Status: " + status);
    }
}
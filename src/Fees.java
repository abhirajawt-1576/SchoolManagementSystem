public class Fees {

    int studentId;
    double amount;
    String date;
    String status;

    Fees(int studentId, double amount, String date, String status) {
        this.studentId = studentId;
        this.amount = amount;
        this.date = date;
        this.status = status;
    }

    void displayFees() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Amount: " + amount);
        System.out.println("Date: " + date);
        System.out.println("Status: " + status);
    }
}
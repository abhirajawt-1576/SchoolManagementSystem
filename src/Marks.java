public class Marks {

    int studentId;
    int subjectId;
    int marks;

    Marks(int studentId, int subjectId, int marks) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.marks = marks;
    }

    void displayMarks() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Subject ID: " + subjectId);
        System.out.println("Marks: " + marks);
    }
}
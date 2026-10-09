import java.util.ArrayList;

public class StudentCollectionDemo {

    public static void main(String[] args) {

        ArrayList<Student> students = new ArrayList<>();

        Student student1 =
                new Student(101, "Abhishek", 20, "B.Tech");

        Student student2 =
                new Student(102, "Ansh", 20, "B.Tech");

        students.add(student1);
        students.add(student2);

        System.out.println("----- STUDENT COLLECTION -----");

        for (Student student : students) {

            System.out.println(
                    "Name: " + student.getName()
                            + ", Age: " + student.getAge()
            );
        }

        System.out.println("------------------------------");
        System.out.println("Total Students: " + students.size());
    }
}
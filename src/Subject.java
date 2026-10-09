public class Subject {

    int id;
    String name;
    int teacherId;

    Subject(int id, String name, int teacherId) {
        this.id = id;
        this.name = name;
        this.teacherId = teacherId;
    }

    void displaySubject() {
        System.out.println("Subject ID: " + id);
        System.out.println("Subject Name: " + name);
        System.out.println("Teacher ID: " + teacherId);
    }
}

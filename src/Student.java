public class Student implements Person {

    int id;
    String name;
    int age;
    String course;

    // Constructor
    Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    // Interface method 1
    @Override
    public String getName() {
        return name;
    }

    // Interface method 2
    @Override
    public int getAge() {
        return age;
    }

    // Display student details
    void displayStudent() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Course: " + course);
    }
}
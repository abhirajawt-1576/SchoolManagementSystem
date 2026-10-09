public class PolymorphismDemo {

    public static void displayPerson(Person person) {

        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
        System.out.println("--------------------");
    }

    public static void main(String[] args) {

        Student student = new Student(101, "Rahul", 20, "B.Tech");

        Teacher teacher = new Teacher(201, "Mr. Sharma", 40, "Java");

        Person person1 = student;
        Person person2 = teacher;

        System.out.println("Student Details:");
        displayPerson(person1);

        System.out.println("Teacher Details:");
        displayPerson(person2);
    }
}
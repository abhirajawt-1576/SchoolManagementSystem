public class Teacher implements Person {

    private int id;
    private String name;
    private int age;
    private String subject;

    // Default Constructor
    public Teacher() {
    }

    // Parameterized Constructor
    public Teacher(int id, String name, int age, String subject) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.subject = subject;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Setter for ID
    public void setId(int id) {
        this.id = id;
    }

    // Getter for Name
    @Override
    public String getName() {
        return name;
    }

    // Setter for Name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for Age
    @Override
    public int getAge() {
        return age;
    }

    // Setter for Age
    public void setAge(int age) {
        this.age = age;
    }

    // Getter for Subject
    public String getSubject() {
        return subject;
    }

    // Setter for Subject
    public void setSubject(String subject) {
        this.subject = subject;
    }

    // Display Teacher Details
    public void displayTeacher() {
        System.out.println("Teacher ID: " + id);
        System.out.println("Teacher Name: " + name);
        System.out.println("Teacher Age: " + age);
        System.out.println("Teacher Subject: " + subject);
    }
}
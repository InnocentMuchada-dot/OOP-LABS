package lab04;

public class L4_Person {
    public static void main(String[] args) {
        Student s = new Student("Tariro", 19, "Computer Science");
        Teacher t = new Teacher("Mr Moyo", 45, "Mathematics");
        s.display();      // display() is written once, in Person
        t.display();
        s.showCourse();
        t.showSubject();
    }
}
class Person {
    protected String name; protected int age;
    Person(String name, int age) { this.name = name; this.age = age; }
    void display() { System.out.println("Name: " + name + ", Age: " + age); }
}
class Student extends Person {
    private String course;
    Student(String name, int age, String course) { super(name, age); this.course = course; }
    void showCourse() { System.out.println(name + " studies " + course); }
}
class Teacher extends Person {
    private String subject;
    Teacher(String name, int age, String subject) { super(name, age); this.subject = subject; }
    void showSubject() { System.out.println(name + " teaches " + subject); }
}

package lab09;

public class L9_Record {
    record Student(String name, int age) { }
    public static void main(String[] args) {
        Student a = new Student("Tariro", 20);
        Student b = new Student("Tariro", 20);
        Student c = new Student("Farai", 21);
        System.out.println(a);
        System.out.println("a.equals(b): " + a.equals(b) + ", a == b: " + (a == b));
        System.out.println("a.equals(c): " + a.equals(c));
        System.out.println("Component name() = " + a.name() + ", age() = " + a.age());
    }
}

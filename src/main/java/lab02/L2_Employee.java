package lab02;

public class L2_Employee {
    public static void main(String[] args) {
        Employee e1 = new Employee("Tendai", 2500, "IT");
        Employee e2 = new Employee("Rudo", 2100);
        Employee e3 = new Employee("Farai");
        Employee e4 = new Employee(e1);   // copy constructor: new id, same data
        e1.show(); e2.show(); e3.show(); e4.show();
    }
}
class Employee {
    private static int nextId = 1;        // auto-incrementing id source
    private final int employeeId;
    private final String name, dept;
    private final double salary;
    Employee(String name, double salary, String dept) {
        this.employeeId = nextId++; this.name = name; this.salary = salary; this.dept = dept;
    }
    Employee(String name, double salary) { this(name, salary, "General"); }
    Employee(String name) { this(name, 1000.0); }
    Employee(Employee o) { this(o.name, o.salary, o.dept); }   // copy constructor chains to the full one
    void show() { System.out.printf("#%d %s, %s, %.2f%n", employeeId, name, dept, salary); }
}

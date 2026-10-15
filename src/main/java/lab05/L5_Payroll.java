package lab05;

public class L5_Payroll {
    // This method never mentions Intern: it works for any Employee subclass
    static double totalPayroll(Employee[] staff) {
        double total = 0;
        for (Employee e : staff) { e.describe(); total += e.calculatePay(); }
        return total;
    }
    public static void main(String[] args) {
        Employee[] staff = { new Employee("Kudzai", 2000), new Manager("Nyasha", 3500, 800),
                             new Developer("Tatenda", 3000, 10), new Intern("Lynn", 600) };
        System.out.printf("Total payroll: %.2f%n", totalPayroll(staff));
    }
}
class Employee {
    private final String name; protected double baseSalary;
    Employee(String name, double baseSalary) { this.name = name; this.baseSalary = baseSalary; }
    double calculatePay() { return baseSalary; }
    void describe() { System.out.printf("%-8s earns %8.2f%n", name, calculatePay()); }
}
class Manager extends Employee {
    private final double bonus;
    Manager(String n, double s, double bonus) { super(n, s); this.bonus = bonus; }
    @Override double calculatePay() { return super.calculatePay() + bonus; }
}
class Developer extends Employee {
    private final int overtime;
    Developer(String n, double s, int overtime) { super(n, s); this.overtime = overtime; }
    @Override double calculatePay() { return baseSalary + overtime * 25.0; }
}
class Intern extends Employee {                       // the NEW subclass
    Intern(String n, double stipend) { super(n, stipend); }
    @Override double calculatePay() { return baseSalary * 0.9; }   // 10% training deduction
}

package lab07;

public class L7_Age {
    static void validateAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 120) throw new InvalidAgeException(age);
        System.out.println("Age " + age + " is valid");
    }
    public static void main(String[] args) {
        int[] tests = {25, 0, 120, -5, 150};
        for (int t : tests) {
            try { validateAge(t); }
            catch (InvalidAgeException e) { System.out.println("Rejected: " + e.getMessage()); }
        }
    }
}
class InvalidAgeException extends Exception {          // checked: callers MUST handle it
    private final int age;
    InvalidAgeException(int age) { super("Age must be between 0 and 120 but was " + age); this.age = age; }
    int getAge() { return age; }
}

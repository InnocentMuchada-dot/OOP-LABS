package lab03;

public class L3_StudentMarks {
    public static void main(String[] args) {
        Student s = new Student("Chipo", 72);
        System.out.println(s.getName() + ": " + s.getMarks() + " -> grade " + s.getGrade());
        s.setMarks(48);
        System.out.println(s.getName() + ": " + s.getMarks() + " -> grade " + s.getGrade());
        try {
            s.setMarks(120);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        System.out.println("Marks unchanged: " + s.getMarks());
    }
}
class Student {
    private String name;
    private int marks;
    Student(String name, int marks) { this.name = name; setMarks(marks); }   // constructor reuses validation
    String getName() { return name; }
    int getMarks() { return marks; }
    void setMarks(int marks) {
        if (marks < 0 || marks > 100) throw new IllegalArgumentException("Marks must be 0-100, got " + marks);
        this.marks = marks;
    }
    // Derived from marks each time, so it can never get out of sync
    String getGrade() { return marks >= 70 ? "A" : marks >= 60 ? "B" : marks >= 50 ? "C" : "F"; }
}

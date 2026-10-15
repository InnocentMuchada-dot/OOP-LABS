package lab06;

import java.util.*;
public class L6_StudentSort {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>(List.of(new Student("Kuda", 61), new Student("Ada", 88), new Student("Lynn", 74)));
        Collections.sort(list);                                              // natural order: name (Comparable)
        System.out.println("By name:  " + list);
        list.sort(Comparator.comparingInt(Student::getMarks).reversed());    // alternative order: marks (Comparator)
        System.out.println("By marks: " + list);
    }
}
class Student implements Comparable<Student> {
    private final String name; private final int marks;
    Student(String name, int marks) { this.name = name; this.marks = marks; }
    int getMarks() { return marks; }
    @Override public int compareTo(Student o) { return name.compareTo(o.name); }
    @Override public String toString() { return name + "(" + marks + ")"; }
}

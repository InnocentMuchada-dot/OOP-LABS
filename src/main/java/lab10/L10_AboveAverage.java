package lab10;

import java.util.*;
public class L10_AboveAverage {
    record Student(String name, double marks) { }
    public static void main(String[] args) {
        List<Student> class1 = List.of(new Student("Kuda", 61), new Student("Ada", 88), new Student("Lynn", 74),
                                       new Student("Ben", 52), new Student("Zodwa", 79));
        double avg = class1.stream().mapToDouble(Student::marks).average().orElse(0);
        System.out.println("Class average: " + avg);
        class1.stream()
              .filter(s -> s.marks() > avg)               // above average only
              .map(Student::name)
              .sorted()                                    // alphabetical
              .forEach(System.out::println);
    }
}

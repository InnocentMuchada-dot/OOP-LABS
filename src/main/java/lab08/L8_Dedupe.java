package lab08;

import java.util.*;
public class L8_Dedupe {
    public static void main(String[] args) {
        List<String> names = List.of("Zodwa", "Alice", "Moyo", "Alice", "Zodwa", "Brian");
        Set<String> unique = new LinkedHashSet<>(names);   // no duplicates, keeps first-seen order
        System.out.println("Original: " + names);
        System.out.println("Unique:   " + unique);
        System.out.println("Sorted:   " + new TreeSet<>(names));
    }
}

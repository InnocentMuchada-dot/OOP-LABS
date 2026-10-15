package lab03;

import java.util.*;
public class L3_ImmutablePerson {
    public static void main(String[] args) {
        List<String> hobbies = new ArrayList<>(List.of("chess", "football"));
        ImmutablePerson p = new ImmutablePerson("Anesu", 20, hobbies);
        hobbies.add("poker");                       // changing the original list...
        System.out.println(p);                      // ...does not affect p
        try { p.getHobbies().add("x"); }
        catch (UnsupportedOperationException e) { System.out.println("getHobbies() is read-only"); }
        ImmutablePerson older = p.withAge(21);      // "changes" create a new object
        System.out.println(p.getAge() + " vs " + older.getAge());
    }
}
final class ImmutablePerson {                       // final class: no subclass can add mutability
    private final String name;                      // final fields: assigned once
    private final int age;
    private final List<String> hobbies;
    ImmutablePerson(String name, int age, List<String> hobbies) {
        this.name = name; this.age = age;
        this.hobbies = List.copyOf(hobbies);        // defensive copy on the way in
    }
    String getName() { return name; }               // no setters at all
    int getAge() { return age; }
    List<String> getHobbies() { return hobbies; }   // already unmodifiable
    ImmutablePerson withAge(int newAge) { return new ImmutablePerson(name, newAge, hobbies); }
    @Override public String toString() { return name + " (" + age + ") " + hobbies; }
}

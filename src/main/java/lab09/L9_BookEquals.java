package lab09;

import java.util.*;
public class L9_BookEquals {
    public static void main(String[] args) {
        Book b1 = new Book("978-0134685991", "Effective Java");
        Book b2 = new Book("978-0134685991", "Effective Java (2nd copy)");  // same ISBN
        Book b3 = new Book("978-0132350884", "Clean Code");
        Set<Book> set = new HashSet<>();
        System.out.println("add b1: " + set.add(b1));
        System.out.println("add b2: " + set.add(b2) + "  (rejected: same ISBN)");
        System.out.println("add b3: " + set.add(b3));
        System.out.println("Set size: " + set.size());
        System.out.println("hash codes equal? " + (b1.hashCode() == b2.hashCode()));
    }
}
class Book {
    private final String isbn, title;
    Book(String isbn, String title) { this.isbn = isbn; this.title = title; }
    @Override public boolean equals(Object o) {          // Object parameter, so it really overrides
        if (this == o) return true;
        if (!(o instanceof Book other)) return false;
        return isbn.equals(other.isbn);                  // identity = ISBN only
    }
    @Override public int hashCode() { return Objects.hash(isbn); }   // uses the same field as equals
    @Override public String toString() { return title + " [" + isbn + "]"; }
}

package library;

import java.nio.file.*;
import java.time.LocalDate;
import java.util.Comparator;

// Tiny self-contained test runner (no JUnit needed)
public class LibraryTest {
    static int passed = 0, failed = 0;
    static void check(String name, boolean ok) {
        System.out.println((ok ? "PASS  " : "FAIL  ") + name);
        if (ok) passed++; else failed++;
    }
    static Library fresh() {
        Library lib = new Library();
        lib.addItem(new Book("B1", "Java Basics", "A. Author"));
        lib.addItem(new Book("B2", "Advanced Java", "B. Writer"));
        lib.addItem(new Book("B3", "Data Structures", "C. Coder"));
        lib.addItem(new Dvd("D1", "Java Lectures", 90));
        lib.addMember(new Member("M1", "Tariro"));
        lib.addMember(new Member("M2", "Farai"));
        return lib;
    }

    public static void main(String[] args) throws Exception {
        Library lib = fresh();

        lib.borrow("M1", "B1");
        check("borrow marks item unavailable", !lib.searchByTitle("Java Basics").get(0).isAvailable());

        boolean blocked = false;
        try { lib.borrow("M2", "B1"); } catch (ItemNotAvailableException e) { blocked = true; }
        check("borrowing an item already on loan throws ItemNotAvailableException", blocked);

        lib.borrow("M1", "B2"); lib.borrow("M1", "B3");
        boolean limited = false;
        try { lib.borrow("M1", "D1"); } catch (MemberLimitExceededException e) { limited = true; }
        check("4th loan throws MemberLimitExceededException", limited);
        check("failed borrow leaves DVD available", lib.searchByTitle("Lectures").get(0).isAvailable());

        lib.giveBack("B1");
        check("return makes item available again", lib.searchByTitle("Java Basics").get(0).isAvailable());
        check("return frees a slot for the member", lib.getMembers().stream().filter(m -> m.getId().equals("M1")).findFirst().get().getBorrowedItemIds().size() == 2);

        check("search is case-insensitive and sorted by title",
              lib.searchByTitle("JAVA").stream().map(LibraryItem::getTitle).toList().equals(java.util.List.of("Advanced Java", "Java Basics", "Java Lectures")));

        check("books due in 21 days, DVDs in 7 (polymorphism)", new Book("X", "t", "a").loanPeriodDays() == 21 && new Dvd("Y", "t", 10).loanPeriodDays() == 7);

        check("no loans overdue today", lib.overdue(LocalDate.now()).isEmpty());
        check("both open loans overdue 30 days from now", lib.overdue(LocalDate.now().plusDays(30)).size() == 2);

        boolean bad = false;
        try { new Dvd("D9", "Bad", -5); } catch (IllegalArgumentException e) { bad = true; }
        check("invalid data rejected by constructor", bad);

        Path dir = Files.createTempDirectory("lib-test");
        lib.save(dir);
        Library copy = Library.load(dir);
        check("persistence: items survive save/load", copy.getItems().size() == 4);
        check("persistence: loans survive save/load", copy.getLoans().size() == 2);
        check("persistence: availability rebuilt from loans", !copy.searchByTitle("Advanced").get(0).isAvailable());
        boolean stillBlocked = false;
        try { copy.borrow("M2", "B2"); } catch (ItemNotAvailableException e) { stillBlocked = true; }
        check("rules still enforced after reload", stillBlocked);

        System.out.println("\n" + passed + " passed, " + failed + " failed");
    }
}

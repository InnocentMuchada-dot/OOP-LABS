package library;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    private static final Scanner in = new Scanner(System.in);

    private static String ask(String prompt) { System.out.print(prompt); return in.nextLine().trim(); }

    public static void main(String[] args) throws IOException {
        Path dir = Path.of(args.length > 0 ? args[0] : "library-data");
        Library lib = Library.load(dir);
        System.out.println("Loaded " + lib.getItems().size() + " items, " + lib.getMembers().size()
                + " members, " + lib.getLoans().size() + " loans");

        boolean running = true;
        while (running) {
            System.out.println("\n1 Add item  2 Register member  3 Borrow  4 Return  5 Overdue  6 Search  7 List all  0 Save & exit");
            String choice = ask("> ");
            try {
                switch (choice) {
                    case "1" -> {
                        String type = ask("Type (book/dvd): "), id = ask("Id: "), title = ask("Title: ");
                        if (type.equalsIgnoreCase("book")) lib.addItem(new Book(id, title, ask("Author: ")));
                        else lib.addItem(new Dvd(id, title, Integer.parseInt(ask("Minutes: "))));
                        System.out.println("Added " + id);
                    }
                    case "2" -> { lib.addMember(new Member(ask("Member id: "), ask("Name: "))); System.out.println("Registered"); }
                    case "3" -> { lib.borrow(ask("Member id: "), ask("Item id: ")); System.out.println("Borrowed"); }
                    case "4" -> { lib.giveBack(ask("Item id: ")); System.out.println("Returned"); }
                    case "5" -> {
                        List<Loan> late = lib.overdue(LocalDate.now());
                        if (late.isEmpty()) System.out.println("No overdue items");
                        late.forEach(l -> System.out.println("OVERDUE: " + l.itemId() + " (member " + l.memberId() + ", due " + l.dueOn() + ")"));
                    }
                    case "6" -> {
                        List<LibraryItem> found = lib.searchByTitle(ask("Search text: "));
                        if (found.isEmpty()) System.out.println("No matches");
                        found.forEach(System.out::println);
                    }
                    case "7" -> { lib.getItems().stream().sorted(java.util.Comparator.comparing(LibraryItem::getId)).forEach(System.out::println);
                                  lib.getMembers().forEach(System.out::println); }
                    case "0" -> running = false;
                    default -> System.out.println("Unknown option");
                }
            } catch (ItemNotAvailableException | MemberLimitExceededException e) {
                System.out.println("Cannot borrow: " + e.getMessage());           // business rule failures
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());           // bad data
            }
        }
        lib.save(dir);
        System.out.println("Data saved to " + dir + ". Goodbye.");
    }
}

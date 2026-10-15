package library;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class Library {
    private final Map<String, LibraryItem> items = new HashMap<>();
    private final Map<String, Member> members = new HashMap<>();
    private final List<Loan> loans = new ArrayList<>();

    public void addItem(LibraryItem item) {
        if (items.putIfAbsent(item.getId(), item) != null) throw new IllegalArgumentException("Duplicate item id " + item.getId());
    }
    public void addMember(Member m) {
        if (members.putIfAbsent(m.getId(), m) != null) throw new IllegalArgumentException("Duplicate member id " + m.getId());
    }

    public void borrow(String memberId, String itemId) throws ItemNotAvailableException, MemberLimitExceededException {
        borrow(memberId, itemId, LocalDate.now());
    }

    public void borrow(String memberId, String itemId, LocalDate today)
            throws ItemNotAvailableException, MemberLimitExceededException {
        Member member = members.get(memberId);                                    // 1. look up both
        LibraryItem item = items.get(itemId);
        if (member == null) throw new IllegalArgumentException("No such member: " + memberId);
        if (item == null) throw new IllegalArgumentException("No such item: " + itemId);
        if (!item.isAvailable()) throw new ItemNotAvailableException(itemId);     // 2. check availability and limit
        member.addLoan(itemId);                                                   //    (throws if member is full)
        try {
            item.borrow(member);                                                  // 3. record everywhere
        } catch (ItemNotAvailableException e) {
            member.removeLoan(itemId);                                            // roll back so state stays consistent
            throw e;
        }
        loans.add(new Loan(itemId, memberId, today, today.plusDays(item.loanPeriodDays())));
    }

    public void giveBack(String itemId) {
        Loan loan = loans.stream().filter(l -> l.itemId().equals(itemId)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Item " + itemId + " is not on loan"));
        loans.remove(loan);
        items.get(itemId).giveBack();
        members.get(loan.memberId()).removeLoan(itemId);
    }

    public List<Loan> overdue(LocalDate today) {
        return loans.stream().filter(l -> l.isOverdue(today)).sorted(Comparator.comparing(Loan::dueOn)).toList();
    }

    public List<LibraryItem> searchByTitle(String text) {
        return items.values().stream()
                .filter(i -> i.getTitle().toLowerCase().contains(text.toLowerCase()))
                .sorted(Comparator.comparing(LibraryItem::getTitle))
                .toList();
    }

    public Collection<LibraryItem> getItems() { return Collections.unmodifiableCollection(items.values()); }
    public Collection<Member> getMembers() { return Collections.unmodifiableCollection(members.values()); }
    public List<Loan> getLoans() { return Collections.unmodifiableList(loans); }

    // ---------- persistence (Lab 10): three CSV files ----------
    public void save(Path dir) throws IOException {
        Files.createDirectories(dir);
        Files.write(dir.resolve("items.csv"), items.values().stream().map(LibraryItem::toCsv).sorted().toList());
        Files.write(dir.resolve("members.csv"), members.values().stream().map(m -> m.getId() + "," + m.getName()).sorted().toList());
        Files.write(dir.resolve("loans.csv"), loans.stream().map(Loan::toCsv).toList());
    }

    public static Library load(Path dir) throws IOException {
        Library lib = new Library();
        if (!Files.exists(dir.resolve("items.csv"))) return lib;                  // first run: nothing saved yet
        for (String line : Files.readAllLines(dir.resolve("items.csv"))) {
            if (line.isBlank()) continue;
            String[] p = line.split(",");
            lib.addItem(p[0].equals("BOOK") ? new Book(p[1], p[2], p[3]) : new Dvd(p[1], p[2], Integer.parseInt(p[3])));
        }
        for (String line : Files.readAllLines(dir.resolve("members.csv"))) {
            if (line.isBlank()) continue;
            String[] p = line.split(",");
            lib.addMember(new Member(p[0], p[1]));
        }
        for (String line : Files.readAllLines(dir.resolve("loans.csv"))) {        // rebuild availability from the loans
            if (line.isBlank()) continue;
            Loan loan = Loan.fromCsv(line);
            lib.loans.add(loan);
            lib.items.get(loan.itemId()).setAvailable(false);
            try { lib.members.get(loan.memberId()).addLoan(loan.itemId()); }
            catch (MemberLimitExceededException e) { throw new IOException("Corrupt data: " + e.getMessage(), e); }
        }
        return lib;
    }
}

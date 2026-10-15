package library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member {
    public static final int MAX_LOANS = 3;
    private final String id;
    private String name;
    private final List<String> borrowedItemIds = new ArrayList<>();

    public Member(String id, String name) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Member id required");
        if (name == null || name.isBlank() || name.contains(",")) throw new IllegalArgumentException("Valid name required");
        this.id = id.trim();
        this.name = name.trim();
    }

    void addLoan(String itemId) throws MemberLimitExceededException {
        if (borrowedItemIds.size() >= MAX_LOANS) throw new MemberLimitExceededException(id, MAX_LOANS);
        borrowedItemIds.add(itemId);
    }
    void removeLoan(String itemId) { borrowedItemIds.remove(itemId); }

    public String getId() { return id; }
    public String getName() { return name; }
    public List<String> getBorrowedItemIds() { return Collections.unmodifiableList(borrowedItemIds); }   // encapsulation
    @Override public String toString() { return id + " " + name + " (" + borrowedItemIds.size() + "/" + MAX_LOANS + " loans)"; }
}

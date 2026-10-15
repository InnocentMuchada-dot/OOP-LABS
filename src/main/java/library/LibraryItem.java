package library;

public abstract class LibraryItem implements Borrowable {
    private final String id;
    private final String title;
    private boolean available = true;

    protected LibraryItem(String id, String title) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Item id required");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title required");
        if (id.contains(",") || title.contains(",")) throw new IllegalArgumentException("Commas are not allowed (CSV storage)");
        this.id = id.trim();
        this.title = title.trim();
    }

    public abstract int loanPeriodDays();      // each item type sets its own loan period (polymorphism)
    public abstract String toCsv();            // each item type knows how to save itself

    @Override
    public void borrow(Member member) throws ItemNotAvailableException {
        if (!available) throw new ItemNotAvailableException(id);
        available = false;
    }

    @Override
    public void giveBack() { available = true; }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public boolean isAvailable() { return available; }
    void setAvailable(boolean available) { this.available = available; }   // package-private: used when reloading

    @Override
    public String toString() {
        return String.format("%-4s %-22s %-5s %s (%d-day loan)", id, title, getClass().getSimpleName(),
                available ? "available" : "ON LOAN", loanPeriodDays());
    }
}

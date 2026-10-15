package library;

public class Dvd extends LibraryItem {
    private final int minutes;
    public Dvd(String id, String title, int minutes) {
        super(id, title);
        if (minutes <= 0) throw new IllegalArgumentException("Running time must be positive");
        this.minutes = minutes;
    }
    @Override public int loanPeriodDays() { return 7; }
    public int getMinutes() { return minutes; }
    @Override public String toCsv() { return "DVD," + getId() + "," + getTitle() + "," + minutes; }
}

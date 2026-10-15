package library;

public class Book extends LibraryItem {
    private final String author;
    public Book(String id, String title, String author) {
        super(id, title);
        if (author == null || author.isBlank() || author.contains(",")) throw new IllegalArgumentException("Valid author required");
        this.author = author.trim();
    }
    @Override public int loanPeriodDays() { return 21; }
    public String getAuthor() { return author; }
    @Override public String toCsv() { return "BOOK," + getId() + "," + getTitle() + "," + author; }
}

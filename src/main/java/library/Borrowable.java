package library;

public interface Borrowable {
    void borrow(Member member) throws ItemNotAvailableException;   // contract: anything lendable
    void giveBack();
}

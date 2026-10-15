package library;

public class ItemNotAvailableException extends Exception {
    public ItemNotAvailableException(String itemId) { super("Item " + itemId + " is already on loan"); }
}

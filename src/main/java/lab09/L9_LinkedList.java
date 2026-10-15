package lab09;

import java.util.Iterator;
import java.util.NoSuchElementException;
public class L9_LinkedList {
    public static void main(String[] args) {
        MyList<String> list = new MyList<>();
        list.add("one"); list.add("two"); list.add("three");
        for (String s : list) System.out.println(s);      // works because MyList is Iterable
        System.out.println("size = " + list.size());
    }
}
class MyList<T> implements Iterable<T> {
    private static class Node<T> {                         // static nested: needs no MyList instance
        final T value; Node<T> next;
        Node(T value) { this.value = value; }
    }
    private Node<T> head, tail;
    private int size;
    void add(T value) {
        Node<T> n = new Node<>(value);
        if (head == null) head = tail = n; else { tail.next = n; tail = n; }
        size++;
    }
    int size() { return size; }
    @Override public Iterator<T> iterator() { return new ListIterator(); }
    private class ListIterator implements Iterator<T> {    // inner class: reads the outer 'head'
        private Node<T> current = head;
        public boolean hasNext() { return current != null; }
        public T next() {
            if (current == null) throw new NoSuchElementException();
            T v = current.value; current = current.next; return v;
        }
    }
}

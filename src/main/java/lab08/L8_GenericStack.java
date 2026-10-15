package lab08;

import java.util.*;
public class L8_GenericStack {
    public static void main(String[] args) {
        Stack<String> s = new Stack<>();
        s.push("a"); s.push("b"); s.push("c");
        System.out.println("peek = " + s.peek() + ", size = " + s.size());
        System.out.println("pop  = " + s.pop());
        System.out.println("pop  = " + s.pop());
        System.out.println("empty? " + s.isEmpty());
        Stack<Integer> nums = new Stack<>();              // same class, different type
        nums.push(10); nums.push(20);
        int top = nums.pop();                             // no cast needed
        System.out.println("Integer stack popped " + top);
        s.pop();
        try { s.pop(); }
        catch (IllegalStateException e) { System.out.println("Error: " + e.getMessage()); }
    }
}
class Stack<T> {
    private final List<T> items = new ArrayList<>();      // composition: stack HAS-A list
    void push(T item) { items.add(item); }
    T pop() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        return items.remove(items.size() - 1);
    }
    T peek() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        return items.get(items.size() - 1);
    }
    boolean isEmpty() { return items.isEmpty(); }
    int size() { return items.size(); }
}

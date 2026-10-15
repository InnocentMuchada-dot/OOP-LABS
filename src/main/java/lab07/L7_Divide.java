package lab07;

import java.util.*;
public class L7_Divide {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            System.out.print("Enter two integers: ");
            int a = Integer.parseInt(in.next());
            int b = Integer.parseInt(in.next());
            System.out.println(a + " / " + b + " = " + (a / b));
        } catch (NumberFormatException e) {          // non-numeric input
            System.out.println("Invalid input: please type whole numbers only");
        } catch (ArithmeticException e) {            // division by zero
            System.out.println("Cannot divide by zero");
        } catch (NoSuchElementException e) {         // fewer than two tokens typed
            System.out.println("Two numbers are required");
        } finally {
            in.close();
        }
    }
}

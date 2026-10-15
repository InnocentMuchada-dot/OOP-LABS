package lab05;

import java.util.Arrays;
public class L5_Overload {
    static void print(int x)      { System.out.println("int: " + x); }
    static void print(double x)   { System.out.println("double: " + x); }
    static void print(String x)   { System.out.println("String: " + x); }
    static void print(int[] x)    { System.out.println("int[]: " + Arrays.toString(x)); }
    public static void main(String[] args) {
        print(7); print(3.14); print("hello"); print(new int[]{1, 2, 3});
        print('A');   // char is widened to int, so print(int) is chosen
    }
}

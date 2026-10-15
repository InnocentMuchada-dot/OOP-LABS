package lab08;

import java.util.*;
public class L8_IntList {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(List.of(4, 18, 7, 25, 9, 12, 3, 30, 16, 1));
        int sum = 0, max = nums.get(0);
        for (int n : nums) { sum += n; if (n > max) max = n; }
        System.out.println("List:    " + nums);
        System.out.println("Sum:     " + sum);
        System.out.println("Maximum: " + max);
        List<Integer> reversed = new ArrayList<>(nums);   // copy so the original order is kept
        Collections.reverse(reversed);
        System.out.println("Reverse: " + reversed);
    }
}

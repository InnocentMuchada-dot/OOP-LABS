package lab08;

import java.util.*;
public class L8_WordFreq {
    public static void main(String[] args) {
        String text = "the cat and the dog and the bird saw the cat";
        Map<String, Integer> freq = new HashMap<>();
        for (String w : text.toLowerCase().split("\\s+")) freq.merge(w, 1, Integer::sum);

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(freq.entrySet());
        // frequency descending, ties broken alphabetically
        entries.sort(Map.Entry.<String, Integer>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()));
        for (Map.Entry<String, Integer> e : entries) System.out.println(e.getKey() + " = " + e.getValue());
    }
}

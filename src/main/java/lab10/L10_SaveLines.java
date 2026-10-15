package lab10;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
public class L10_SaveLines {
    public static void main(String[] args) {
        Path file = Path.of("typed-lines.txt");
        Scanner in = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            System.out.print("Line " + i + ": ");
            lines.add(in.nextLine());
        }
        try {
            Files.write(file, lines);
            System.out.println("--- saved, now reading back ---");
            for (String l : Files.readAllLines(file)) System.out.println(l);
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}

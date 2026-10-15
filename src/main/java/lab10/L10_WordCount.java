package lab10;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
public class L10_WordCount {
    public static void main(String[] args) {
        Path file = Path.of("sample.txt");
        try {
            Files.writeString(file, "Object oriented programming\nis built on classes\nand objects.\n");   // create sample input
            List<String> lines = Files.readAllLines(file);
            int words = 0, chars = 0;
            for (String line : lines) {
                if (!line.isBlank()) words += line.trim().split("\\s+").length;
                chars += line.length();                         // characters excluding line breaks
            }
            System.out.println("Lines:      " + lines.size());
            System.out.println("Words:      " + words);
            System.out.println("Characters: " + chars);
        } catch (IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
        }
    }
}

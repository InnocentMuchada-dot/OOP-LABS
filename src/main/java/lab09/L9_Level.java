package lab09;

public class L9_Level {
    enum Level { LOW, MEDIUM, HIGH }
    static void advice(Level l) {
        switch (l) {
            case LOW -> System.out.println("LOW: relax, you are on track");
            case MEDIUM -> System.out.println("MEDIUM: keep revising each day");
            case HIGH -> System.out.println("HIGH: start now and ask for help");
        }
    }
    public static void main(String[] args) {
        for (Level l : Level.values()) advice(l);
        System.out.println(Level.valueOf("HIGH") == Level.HIGH);   // == is correct for enums
    }
}

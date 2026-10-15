package lab06;

public class L6_Flyable {
    public static void main(String[] args) {
        Flyable[] things = { new Bird("Eagle"), new Aeroplane("Boeing 737") };   // unrelated classes, one type
        for (Flyable f : things) f.fly();
    }
}
interface Flyable { void fly(); }
class Bird implements Flyable {
    private final String species;
    Bird(String s) { species = s; }
    public void fly() { System.out.println(species + " flaps its wings and flies"); }
}
class Aeroplane implements Flyable {
    private final String model;
    Aeroplane(String m) { model = m; }
    public void fly() { System.out.println(model + " accelerates down the runway and takes off"); }
}

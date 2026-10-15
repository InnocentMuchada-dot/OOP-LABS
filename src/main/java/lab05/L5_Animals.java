package lab05;

public class L5_Animals {
    public static void main(String[] args) {
        Animal[] zoo = { new Cat(), new Cow(), new Duck() };
        for (Animal a : zoo) System.out.println(a.getClass().getSimpleName() + " says " + a.sound());
    }
}
class Animal { String sound() { return "..."; } }
class Cat extends Animal  { @Override String sound() { return "Meow"; } }
class Cow extends Animal  { @Override String sound() { return "Moo"; } }
class Duck extends Animal { @Override String sound() { return "Quack"; } }

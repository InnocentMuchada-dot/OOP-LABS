package lab02;

public class L2_Circle {
    public static void main(String[] args) {
        Circle a = new Circle(2.5);
        Circle b = new Circle();          // default radius 1.0
        System.out.println("a radius = " + a.getRadius() + ", b radius = " + b.getRadius());
        System.out.println("Circles created: " + Circle.getCount());
    }
}
class Circle {
    private static int count = 0;         // one shared counter
    private final double radius;
    Circle(double radius) { this.radius = radius; count++; }
    Circle() { this(1.0); }               // constructor chaining; count++ happens once, in the other constructor
    double getRadius() { return radius; }
    static int getCount() { return count; }
}

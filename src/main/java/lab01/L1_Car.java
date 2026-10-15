package lab01;

public class L1_Car {
    public static void main(String[] args) {
        Car[] cars = { new Car("Toyota", 1995, 210000), new Car("Honda", 2010, 95000.5), new Car("Ford", 1980, 320000) };
        for (Car c : cars) {
            c.display();
            System.out.println("  Antique? " + c.isAntique());
        }
    }
}
class Car {
    static final int CURRENT_YEAR = 2026;   // fixed so output is repeatable
    String brand; int year; double mileage;
    Car(String brand, int year, double mileage) { this.brand = brand; this.year = year; this.mileage = mileage; }
    void display() { System.out.println("Brand: " + brand + ", Year: " + year + ", Mileage: " + mileage + " km"); }
    boolean isAntique() { return CURRENT_YEAR - year > 25; }   // more than 25 years old
}

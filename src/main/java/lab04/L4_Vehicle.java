package lab04;

public class L4_Vehicle {
    public static void main(String[] args) {
        System.out.println("Creating an ElectricCar:");
        ElectricCar e = new ElectricCar("ZW-123", 4, 75.0);
        System.out.println("Done: " + e);
    }
}
class Vehicle {
    protected String plate;
    Vehicle(String plate) { this.plate = plate; System.out.println("1. Vehicle constructor"); }
}
class Car extends Vehicle {
    protected int doors;
    Car(String plate, int doors) { super(plate); this.doors = doors; System.out.println("2. Car constructor"); }
}
class ElectricCar extends Car {
    private double batteryKwh;
    ElectricCar(String plate, int doors, double kwh) { super(plate, doors); batteryKwh = kwh; System.out.println("3. ElectricCar constructor"); }
    @Override public String toString() { return plate + ", " + doors + " doors, " + batteryKwh + " kWh"; }
}

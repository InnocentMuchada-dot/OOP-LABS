package lab05;

public class L5_FieldVsMethod {
    public static void main(String[] args) {
        Parent p = new Child();                 // declared type Parent, real object Child
        System.out.println("Field:  " + p.label);   // follows the DECLARED type
        System.out.println("Method: " + p.who());   // follows the ACTUAL object
        System.out.println("Static: " + Parent.kind() + " / " + Child.kind());
        Child c = (Child) p;
        System.out.println("Via Child reference, field: " + c.label);
    }
}
class Parent {
    String label = "Parent field";
    String who() { return "Parent method"; }
    static String kind() { return "Parent static"; }
}
class Child extends Parent {
    String label = "Child field";               // hides, does not override
    @Override String who() { return "Child method"; }
    static String kind() { return "Child static"; }
}

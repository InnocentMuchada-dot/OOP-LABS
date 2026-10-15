package lab02;

public class L2_InitBlocks {
    static { System.out.println("static block 1"); }
    { System.out.println("instance block 1"); }
    L2_InitBlocks() { System.out.println("constructor"); }
    static { System.out.println("static block 2"); }
    { System.out.println("instance block 2"); }
    public static void main(String[] args) {
        System.out.println("main starts");
        new L2_InitBlocks();
        System.out.println("--- second object ---");
        new L2_InitBlocks();
    }
}

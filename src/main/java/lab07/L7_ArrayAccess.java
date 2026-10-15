package lab07;

public class L7_ArrayAccess {
    static int safeGet(int[] arr, int index) {
        try {
            return arr[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index " + index + " is invalid (valid: 0-" + (arr.length - 1) + ")");
            return -1;                                // sentinel value for "not found"
        }
    }
    public static void main(String[] args) {
        int[] data = {10, 20, 30};
        System.out.println("data[1] = " + safeGet(data, 1));
        System.out.println("data[7] = " + safeGet(data, 7));
        System.out.println("data[-1] = " + safeGet(data, -1));
    }
}

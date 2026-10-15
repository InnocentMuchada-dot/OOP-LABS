package lab01;

public class L1_Rectangle {
    public static void main(String[] args) {
        Rectangle[] rects = new Rectangle[5];
        double[][] dims = {{2, 3}, {5, 4}, {1, 9}, {6, 6}, {3, 7}};
        for (int i = 0; i < rects.length; i++) rects[i] = new Rectangle(dims[i][0], dims[i][1]);

        Rectangle largest = rects[0];
        for (Rectangle r : rects) if (r.area() > largest.area()) largest = r;
        System.out.println("Largest: " + largest.width + " x " + largest.height
                + ", area = " + largest.area() + ", perimeter = " + largest.perimeter());

        // Alias demonstration (Intermediate 2)
        Rectangle r1 = new Rectangle(4, 5);
        Rectangle r2 = r1;          // copies the reference, NOT the object
        r2.width = 10;
        System.out.println("r1.width = " + r1.width);
    }
}
class Rectangle {
    double width, height;
    Rectangle(double w, double h) { width = w; height = h; }
    double area() { return width * height; }
    double perimeter() { return 2 * (width + height); }
}

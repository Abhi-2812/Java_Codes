class Shapes_1 {
    public double area(double radius) {
        return Math.PI * radius * radius;
    }

    public double area(double length, double breadth) {
        return length * breadth;
    }
    
    public double area(double base, double height, boolean isTriangle) {
        if (isTriangle) {
            return 0.5 * base * height;
        }
        return 0;
    }
    
    public int area(int side) {
        return side * side;
    }
}

public class Shapes {
    public static void main(String[] args) {
        Shapes_1 shape = new Shapes_1();
        
        System.out.println("=== Area Calculator ===\n");
        
        double circleArea = shape.area(7.0);
        System.out.println("Circle (radius = 7.0): " + circleArea + " sq units");
        
        double rectArea = shape.area(5.0, 10.0);
        System.out.println("Rectangle (5.0 x 10.0): " + rectArea + " sq units");
        
        double triArea = shape.area(8.0, 6.0, true);
        System.out.println("Triangle (base = 8.0, height = 6.0): " + triArea + " sq units");
        
        int squareArea = shape.area(4);
        System.out.println("Square (side = 4): " + squareArea + " sq units");
    }
}

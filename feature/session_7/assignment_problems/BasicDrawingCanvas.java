abstract class Shape {
    private static int counter = 1000;
    private final String shapeId;

    public Shape() {
        counter++;
        shapeId = "SH-" + counter;
    }

    public String getShapeId() {
        return shapeId;
    }

    public abstract double calculateArea();

    // Overloading - uniform scaling
    public void scale(double factor) {
        scale(factor, factor);
    }

    // Overloading - unequal scaling
    public abstract void scale(double xFactor, double yFactor);
}

class CircleShape extends Shape {
    double radius;

    public CircleShape(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        radius = radius * Math.sqrt(xFactor * yFactor);
    }
}

class SquareShape extends Shape {
    double side;

    public SquareShape(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        side = side * Math.sqrt(xFactor * yFactor);
    }
}

public class BasicDrawingCanvas {

    static void printArea(Shape s) {
        System.out.println("Area: " + s.calculateArea());
    }

    public static void main(String[] args) {

        CircleShape c = new CircleShape(5.0);
        SquareShape sq = new SquareShape(4.0);

        System.out.println("Circle Area: " + c.calculateArea());
        System.out.println("Square Area: " + sq.calculateArea());

        sq.scale(2.0);

        System.out.println("Scaled Square Area: " + sq.calculateArea());

        System.out.println("Shape ID: " + c.getShapeId());

        printArea(c);
        printArea(sq);

        
    }
}
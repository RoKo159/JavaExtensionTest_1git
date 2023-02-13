package pl.kurs.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rectangle extends Shape {
    private static final List<Rectangle> CACHE = new ArrayList<>();

    private double length;
    private double width;


    private Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.length, length) == 0 && Double.compare(rectangle.width, width) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width);
    }

    public static final Rectangle createRectangle(double length, double width) {
        if (length < 0 || width < 0) throw new RuntimeException();
        Rectangle r = new Rectangle(length, width);
        CACHE.add(r);
        return r.getFromCache(r);
    }
    private Rectangle getFromCache(Rectangle sample) {
        return CACHE
                .stream()
                .filter(x -> x.equals(sample))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "length=" + length +
                ", width=" + width +
                '}';
    }

    @Override
    public double getArea() {
        return length * width;
    }

    @Override
    public double getPerimeter() {
        return 2 * (length + width);
    }
}

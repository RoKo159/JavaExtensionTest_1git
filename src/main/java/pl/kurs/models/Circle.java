package pl.kurs.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Circle extends Shape{

    private static final List<Circle> CACHE = new ArrayList<>();

    @JsonProperty("type")
    private final String className = Circle.class.getSimpleName();

    @JsonProperty("radius")
    private double radius;

    private Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius);
    }

    public static final Circle createCircle(double radius) {
        if (radius < 0) throw new RuntimeException();
        Circle c = new Circle(radius);
        CACHE.add(c);
        return c.getFromCache(c);
    }

    private Circle getFromCache(Circle sample) {
        return CACHE
                .stream()
                .filter(x -> x.equals(sample))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
}

package pl.kurs.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Square extends Shape {
    private static final List<Square> CACHE = new ArrayList<>();

    private double side;

    private Square(double side) {
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Double.compare(square.side, side) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(side);
    }

    public static final Square createSquare(double side) {
        if (side < 0) throw new RuntimeException();
        Square s = new Square(side);
        CACHE.add(s);
        return s.getFromCache(s);
    }

    private Square getFromCache(Square sample) {
        return CACHE
                .stream()
                .filter(x -> x.equals(sample))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public String toString() {
        return "Square{" +
                "side=" + side +
                '}';
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return 4 * side;
    }
}

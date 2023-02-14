package pl.kurs.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import pl.kurs.models.Circle;
import pl.kurs.models.Rectangle;
import pl.kurs.models.Square;

public class ShapeFactory {
    @JsonCreator
    public Square createSquare(double side) {
        return Square.createSquare(side);
    }

    @JsonCreator
    public Circle createCircle(double radius) {
        return Circle.createCircle(radius);
    }

    @JsonCreator
    public Rectangle createRectangle(double length, double width) {
        return Rectangle.createRectangle(length, width);
    }
}

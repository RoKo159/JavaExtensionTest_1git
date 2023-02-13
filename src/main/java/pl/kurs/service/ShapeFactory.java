package pl.kurs.service;

import pl.kurs.models.Circle;
import pl.kurs.models.Rectangle;
import pl.kurs.models.Square;

public class ShapeFactory {

    public Square createSquare(double side) {
        return Square.createSquare(side);
    }

    public Circle createCircle(double radius) {
        return Circle.createCircle(radius);
    }

    public Rectangle createRectangle(double length, double width) {
        return Rectangle.createRectangle(length, width);
    }
}

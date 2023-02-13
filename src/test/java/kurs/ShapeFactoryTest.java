package kurs;

import org.junit.Before;
import org.junit.Test;
import pl.kurs.models.Circle;
import pl.kurs.models.Rectangle;
import pl.kurs.models.Square;
import pl.kurs.service.ShapeFactory;

import static org.junit.Assert.*;

public class ShapeFactoryTest {


    ShapeFactory shapeFactory;

    @Before
    public void init() {
        shapeFactory = new ShapeFactory();
    }

    @Test
    public void testCreateSquare() {
        Square square = shapeFactory.createSquare(5.0);
        assertEquals(5.0, square.getSide(), 0);
    }

    @Test
    public void testCreateCircle() {
        Circle circle = shapeFactory.createCircle(5.0);
        assertEquals(5.0, circle.getRadius(), 0);
    }

    @Test
    public void testCreateRectangle() {
        Rectangle rectangle = shapeFactory.createRectangle(5.0, 10.0);
        assertEquals(5.0, rectangle.getLength(), 0);
        assertEquals(10.0, rectangle.getWidth(), 0);
    }

}
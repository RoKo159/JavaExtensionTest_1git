package pl.kurs.app;

import pl.kurs.models.Circle;
import pl.kurs.models.Shape;
import pl.kurs.service.ShapeFactory;
import pl.kurs.service.ShapeService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws IOException {
        ShapeFactory shapeFactory = new ShapeFactory();

        Circle c1 = shapeFactory.createCircle(10);
        Circle c2 = shapeFactory.createCircle(8);

        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(c1);
        shapeList.add(c2);

        ShapeService shapeService = new ShapeService();
        System.out.println(shapeService.findShapeWithLargestArea(shapeList));
        System.out.println(shapeService.findShapeWithLargestPerimeter(shapeList, Circle.class));
        shapeService.exportShapesToJSON(shapeList, "src/main/resources/shapes.json");
        List<Shape> shapeList1 = shapeService.importShapesFromJSON("src/main/resources/shapes.json");
        System.out.println(shapeList1);

    }
}

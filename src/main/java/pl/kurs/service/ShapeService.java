package pl.kurs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.kurs.customSerializer.ObjectMapperHolder;
import pl.kurs.models.Shape;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ShapeService {


    public void exportShapesToJSON(List<Shape> shapes, String path) throws IOException {
        ObjectMapper objectMapper = ObjectMapperHolder.INSTANCE.getObjectMapper();
        objectMapper.writeValue(new File(path), shapes);
    }

    public List<Shape> importShapesFromJSON(String path) throws IOException {
        ObjectMapper objectMapper = ObjectMapperHolder.INSTANCE.getObjectMapper();
        return objectMapper.readValue(new File(path), new ArrayList<Shape>().getClass());
    }

    public Shape findShapeWithLargestArea(List<Shape> shapes) {
        return shapes
                .stream()
                .max(Comparator.comparingDouble(Shape::getArea))
                .orElseThrow(() -> new RuntimeException("No shapes found"));
    }

    public Shape findShapeWithLargestPerimeter(List<Shape> shapes, Class type) {
        return shapes
                .stream()
                .filter(x -> x.getClass().equals(type))
                .max(Comparator.comparingDouble(Shape::getPerimeter))
                .orElseThrow(() -> new RuntimeException("No shapes of the specified type found"));
    }
}



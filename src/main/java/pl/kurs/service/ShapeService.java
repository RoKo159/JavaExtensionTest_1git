package pl.kurs.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.kurs.customSerializer.ObjectMapperHolder;
import pl.kurs.customSerializer.ShapeDeserializer;
import pl.kurs.models.Shape;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ShapeService {
    private ObjectMapper objectMapper = ObjectMapperHolder.INSTANCE.getObjectMapper();

    public ShapeService() {
    }

    public void exportShapesToJSON(List<Shape> shapes, String path) throws IOException {
        objectMapper.writeValue(new File(path), shapes);
    }

    public List<Shape> importShapesFromJSON(String path) throws IOException {
        String json = Files.readString(Paths.get(path));
        List<Shape> shapeList = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, Shape.class));

        return shapeList;
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



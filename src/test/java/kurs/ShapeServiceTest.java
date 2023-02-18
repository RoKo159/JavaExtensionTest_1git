package kurs;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;


import org.junit.Before;
import org.junit.Test;
import pl.kurs.customSerializer.ObjectMapperHolder;

import pl.kurs.models.*;

import pl.kurs.service.ShapeFactory;
import pl.kurs.service.ShapeService;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.List;

import static org.junit.Assert.*;


public class ShapeServiceTest {


    List<Shape> shapeList;
    List<Shape> emptyShapeList;
    private ShapeFactory shapeFactory;
    private ShapeService shapeService;
    private ObjectMapper objectMapper;

    @Before
    public void init() {
        shapeList = new ArrayList<>();
        emptyShapeList = new ArrayList<>();
        shapeFactory = new ShapeFactory();
        shapeService = new ShapeService();
        objectMapper = ObjectMapperHolder.INSTANCE.getObjectMapper();

        shapeList.add(shapeFactory.createCircle(10));
        shapeList.add(shapeFactory.createSquare(10));


    }


    @Test
    public void testSaveShapeListThenCheckFileIsCorrect() throws JsonProcessingException, IOException {

        objectMapper.writeValue(new File("testImportJson.json"), shapeList);
        JsonNode root = objectMapper.readTree(new File("testImportJson.json"));
        assertTrue(root.isArray());
        assertTrue(root.size() == 2);
        JsonNode firstShape = root.get(0);
        assertTrue(firstShape.get("type").asText().equals("Circle"));
        assertTrue(firstShape.get("radius").asDouble() == 10.0);
        JsonNode secondShape = root.get(1);
        assertTrue(secondShape.get("type").asText().equals("Square"));
        assertTrue(secondShape.get("side").asDouble() == 10.0);
        Files.delete(Paths.get("testImportJson.json"));

    }

    @Test
    public void testExportShapesToJSON() throws IOException {
        shapeService.exportShapesToJSON(shapeList, "testImportJson.json");
        JsonNode root = objectMapper.readTree(new File("testImportJson.json"));
        assertTrue(root.isArray());
        assertTrue(root.size() == 2);
        JsonNode firstShape = root.get(0);
        assertTrue(firstShape.get("type").asText().equals("Circle"));
        assertTrue(firstShape.get("radius").asDouble() == 10.0);
        JsonNode secondShape = root.get(1);
        assertTrue(secondShape.get("type").asText().equals("Square"));
        assertTrue(secondShape.get("side").asDouble() == 10.0);


    }


    @Test
    public void testImportShapesFromJSON() throws IOException {
        shapeService.importShapesFromJSON("testImportJson.json");
        JsonNode root = objectMapper.readTree(new File("testImportJson.json"));
        assertTrue(root.isArray());
        assertTrue(root.size() == 2);
        JsonNode firstShape = root.get(0);
        assertTrue(firstShape.get("type").asText().equals("Circle"));
        assertTrue(firstShape.get("radius").asDouble() == 10.0);
        JsonNode secondShape = root.get(1);
        assertTrue(secondShape.get("type").asText().equals("Square"));
        assertTrue(secondShape.get("side").asDouble() == 10.0);
        Files.delete(Paths.get("testImportJson.json"));

    }

    @Test
    public void shouldReturnShapeWithLargestArea() {
        shapeService.findShapeWithLargestArea(shapeList);

        Assertions.assertThat(shapeService.findShapeWithLargestArea(shapeList)).isSameAs(shapeList.get(0));

    }

    @Test
    public void shouldReturnShapeWithLargestPerimeterFromSpecificType() {
        shapeService.findShapeWithLargestPerimeter(shapeList, Square.class);

        Assertions.assertThat(shapeService.findShapeWithLargestPerimeter(shapeList, Square.class)).isSameAs(shapeList.get(1));
    }

    @Test
    public void shouldThrowNewRuntimeExceptionWhenShapeListIsEmpty() {
        Exception e = assertThrows(RuntimeException.class, () -> shapeService.findShapeWithLargestArea(emptyShapeList));

        Assertions.assertThat(e)
                .isExactlyInstanceOf(RuntimeException.class)
                .hasMessage("No shapes found")
                .hasNoCause();
    }

    @Test
    public void shouldThrowNewRuntimeExceptionWhenNonFoundSpecificTypeOfShape() {
        List<Shape> shapeListWithNoRectangle = new ArrayList<>();
        shapeList.add(shapeFactory.createCircle(10));
        shapeList.add(shapeFactory.createSquare(10));

        Exception e = assertThrows(RuntimeException.class, () -> shapeService.findShapeWithLargestPerimeter(shapeListWithNoRectangle, Rectangle.class));

        Assertions.assertThat(e)
                .isExactlyInstanceOf(RuntimeException.class)
                .hasMessage("No shapes of the specified type found")
                .hasNoCause();
    }

}

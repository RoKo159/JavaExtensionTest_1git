package kurs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import pl.kurs.customSerializer.ObjectMapperHolder;
import pl.kurs.models.Rectangle;
import pl.kurs.models.Shape;
import pl.kurs.service.ShapeFactory;
import pl.kurs.service.ShapeService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;


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
        shapeList.add(shapeFactory.createRectangle(5, 10));
        shapeList.add(shapeFactory.createRectangle(10, 20));
    }

    @Test
    public void testExportShapesToJSON() throws IOException {
        shapeService.exportShapesToJSON(shapeList, "testImportJson.json");
        JsonNode root = objectMapper.readTree(new File("testImportJson.json"));
        assertTrue(root.isArray());
        assertTrue(root.size() == 4);
        JsonNode firstShape = root.get(0);
        assertTrue(firstShape.get("type").asText().equals("circle"));
        assertTrue(firstShape.get("radius").asDouble() == 10.0);
        JsonNode secondShape = root.get(1);
        assertTrue(secondShape.get("type").asText().equals("square"));
        assertTrue(secondShape.get("side").asDouble() == 10.0);
        JsonNode thirdShape = root.get(2);
        assertTrue(thirdShape.get("type").asText().equals("rectangle"));
        assertTrue(thirdShape.get("length").asDouble() == 5.0);
        assertTrue(thirdShape.get("width").asDouble() == 10.0);
        JsonNode fourthShape = root.get(3);
        assertTrue(fourthShape.get("type").asText().equals("rectangle"));
        assertTrue(fourthShape.get("length").asDouble() == 10.0);
        assertTrue(fourthShape.get("width").asDouble() == 20.0);

    }

    @Test
    public void testImportShapesFromJSON() throws IOException {
        shapeService.importShapesFromJSON("testImportJson.json");
        JsonNode root = objectMapper.readTree(new File("testImportJson.json"));
        assertTrue(root.isArray());
        assertTrue(root.size() == 4);
        JsonNode firstShape = root.get(0);
        assertTrue(firstShape.get("type").asText().equals("circle"));
        assertTrue(firstShape.get("radius").asDouble() == 10.0);
        JsonNode secondShape = root.get(1);
        assertTrue(secondShape.get("type").asText().equals("square"));
        assertTrue(secondShape.get("side").asDouble() == 10.0);
        JsonNode thirdShape = root.get(2);
        assertTrue(thirdShape.get("type").asText().equals("rectangle"));
        assertTrue(thirdShape.get("length").asDouble() == 5.0);
        assertTrue(thirdShape.get("width").asDouble() == 10.0);
        JsonNode fourthShape = root.get(3);
        assertTrue(fourthShape.get("type").asText().equals("rectangle"));
        assertTrue(fourthShape.get("length").asDouble() == 10.0);
        assertTrue(fourthShape.get("width").asDouble() == 20.0);
    }

    @Test
    public void shouldReturnShapeWithLargestArea() {
        shapeService.findShapeWithLargestArea(shapeList);

        Assertions.assertThat(shapeService.findShapeWithLargestArea(shapeList)).isSameAs(shapeList.get(0));

    }

    @Test
    public void shouldReturnShapeWithLargestPerimeterFromSpecificType() {
        shapeService.findShapeWithLargestPerimeter(shapeList, Rectangle.class);

        Assertions.assertThat(shapeService.findShapeWithLargestPerimeter(shapeList, Rectangle.class)).isSameAs(shapeList.get(3));
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

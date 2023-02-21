package kurs;


import com.fasterxml.jackson.core.JsonProcessingException;
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

        shapeService.exportShapesToJSON(shapeList, "src/test/resources/testShapes.json");
        String result = objectMapper.readTree(new File("src/test/resources/testShapes.json")).toString();

        String jsonStringSample = "[{\"type\":\"Circle\",\"radius\":10.0},{\"type\":\"Square\",\"side\":10.0}]";

        assertEquals(result, jsonStringSample);


    }

    @Test
    public void testImportShapeFromJson() throws IOException {
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(shapeFactory.createCircle(10));
        shapeList.add(shapeFactory.createSquare(10));

        List<Shape> resultList = shapeService.importShapesFromJSON("src/test/resources/testShapes.json");

        System.out.println(shapeList);
        System.out.println(resultList);
        assertEquals(shapeList.get(1), resultList.get(1));
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

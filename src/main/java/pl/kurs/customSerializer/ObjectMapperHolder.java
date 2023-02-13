package pl.kurs.customSerializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import pl.kurs.models.Circle;
import pl.kurs.models.Rectangle;
import pl.kurs.models.Shape;
import pl.kurs.models.Square;

import java.text.SimpleDateFormat;

public enum ObjectMapperHolder {

    INSTANCE;

    private final ObjectMapper objectMapper;

    ObjectMapperHolder() {
        objectMapper = create();
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    private static ObjectMapper create() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

        SquareSerializer squareSerializer = new SquareSerializer(Square.class);
        SimpleModule sm1 = new SimpleModule("SquareSerializer");
        sm1.addSerializer(Square.class, squareSerializer);
        mapper.registerModule(sm1);

        CircleSerializer circleSerializer = new CircleSerializer(Circle.class);
        SimpleModule sm2 = new SimpleModule("CircleSerializer");
        sm2.addSerializer(Circle.class, circleSerializer);
        mapper.registerModule(sm2);

        RectangleSerializer rectangleSerializer = new RectangleSerializer(Rectangle.class);
        SimpleModule sm3 = new SimpleModule("RectangleSerializer");
        sm3.addSerializer(Rectangle.class, rectangleSerializer);
        mapper.registerModule(sm3);

        SquareDeserializer squareDeserializer = new SquareDeserializer(Square.class);
        SimpleModule sm4 = new SimpleModule("SquareDeserializer");
        sm4.addDeserializer(Square.class, squareDeserializer);
        mapper.registerModule(sm4);

        RectangleDeserializer rectangleDeserializer = new RectangleDeserializer(Rectangle.class);
        SimpleModule sm5 = new SimpleModule("RectangleDeserializer");
        sm5.addDeserializer(Rectangle.class, rectangleDeserializer);
        mapper.registerModule(sm5);

        CircleDeserializer circleDeserializer = new CircleDeserializer(Circle.class);
        SimpleModule sm6 = new SimpleModule("CircleDeserializer");
        sm6.addDeserializer(Circle.class, circleDeserializer);
        mapper.registerModule(sm6);

        ShapeDeserializer shapeDeserializer = new ShapeDeserializer(Shape.class);
        SimpleModule sm7 = new SimpleModule("ShapeDeserializer");
        sm7.addDeserializer(Shape.class, shapeDeserializer);
        mapper.registerModule(sm7);

        return mapper;
    }


}

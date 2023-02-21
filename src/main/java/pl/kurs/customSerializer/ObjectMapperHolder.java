package pl.kurs.customSerializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import pl.kurs.models.Shape;

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

        ShapeDeserializer shapeDeserializer = new ShapeDeserializer(Shape.class);
        SimpleModule sm = new SimpleModule("ShapeDeserializer");
        sm.addDeserializer(Shape.class, shapeDeserializer);
        mapper.registerModule(sm);


        return mapper;
    }


}

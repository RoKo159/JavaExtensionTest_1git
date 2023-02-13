package pl.kurs.customSerializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import pl.kurs.models.Circle;
import pl.kurs.service.ShapeFactory;

import java.io.IOException;

public class CircleDeserializer extends StdDeserializer<Circle> {
    protected CircleDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Circle deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jn = jsonParser.getCodec().readTree(jsonParser);
        ShapeFactory shapeFactory = new ShapeFactory();
        Circle circle = shapeFactory.createCircle(
                jn.get("radius").asDouble()
        );
        return circle;
    }
}

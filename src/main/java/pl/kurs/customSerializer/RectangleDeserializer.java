package pl.kurs.customSerializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import pl.kurs.models.Rectangle;
import pl.kurs.service.ShapeFactory;

import java.io.IOException;

public class RectangleDeserializer extends StdDeserializer<Rectangle> {
    protected RectangleDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Rectangle deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jn = jsonParser.getCodec().readTree(jsonParser);
        ShapeFactory shapeFactory = new ShapeFactory();
        Rectangle rectangle = shapeFactory.createRectangle(
                jn.get("length").asDouble(),
                jn.get("width").asDouble()
        );
        return rectangle;
    }
}

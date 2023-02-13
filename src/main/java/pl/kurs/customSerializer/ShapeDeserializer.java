package pl.kurs.customSerializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import pl.kurs.models.Shape;
import pl.kurs.service.ShapeFactory;

import java.io.IOException;

public class ShapeDeserializer extends StdDeserializer<Shape> {

    protected ShapeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Shape deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ShapeFactory shapeFactory = new ShapeFactory();
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();
        if (type.equals("circle")) {
            double radius = node.get("radius").asDouble();
            return shapeFactory.createCircle(radius);
        } else if (type.equals("rectangle")) {
            double length = node.get("length").asDouble();
            double width = node.get("width").asDouble();
            return shapeFactory.createRectangle(length, width);
        } else if (type.equals("square")) {
            double side = node.get("side").asDouble();
            return shapeFactory.createSquare(side);
        } else {
            throw new IOException("Unknown shape type: " + type);
        }
    }
}

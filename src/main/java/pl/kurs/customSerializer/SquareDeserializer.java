package pl.kurs.customSerializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import pl.kurs.models.Square;
import pl.kurs.service.ShapeFactory;

import java.io.IOException;

public class SquareDeserializer extends StdDeserializer<Square> {

    protected SquareDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Square deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jn = jsonParser.getCodec().readTree(jsonParser);
        ShapeFactory shapeFactory = new ShapeFactory();
        Square square = shapeFactory.createSquare(
                jn.get("side").asDouble()
        );
        return square;
    }
}

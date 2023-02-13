package pl.kurs.customSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import pl.kurs.models.Square;

import java.io.IOException;

public class SquareSerializer extends StdSerializer<Square> {


    protected SquareSerializer(Class<Square> t) {
        super(t);
    }

    @Override
    public void serialize(Square square, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", "square");
        jsonGenerator.writeNumberField("side",square.getSide());
        jsonGenerator.writeEndObject();
    }
}

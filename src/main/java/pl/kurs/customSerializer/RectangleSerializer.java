package pl.kurs.customSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import pl.kurs.models.Rectangle;

import java.io.IOException;

public class RectangleSerializer extends StdSerializer<Rectangle> {
    protected RectangleSerializer(Class<Rectangle> t) {
        super(t);
    }

    @Override
    public void serialize(Rectangle rectangle, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", "rectangle");
        jsonGenerator.writeNumberField("length", rectangle.getLength());
        jsonGenerator.writeNumberField("width", rectangle.getWidth());
        jsonGenerator.writeEndObject();
    }
}

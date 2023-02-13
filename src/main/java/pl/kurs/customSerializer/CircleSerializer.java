package pl.kurs.customSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import pl.kurs.models.Circle;

import java.io.IOException;

public class CircleSerializer extends StdSerializer<Circle> {

    protected CircleSerializer(Class<Circle> t) {
        super(t);
    }

    @Override
    public void serialize(Circle circle, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", "circle");
        jsonGenerator.writeNumberField("radius", circle.getRadius());
        jsonGenerator.writeEndObject();
    }
}

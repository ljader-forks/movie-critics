package com.rys.moviecriticts.rate.rest.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.bson.types.ObjectId;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class ObjectIdSerializer extends JsonSerializer<ObjectId> {

    @Override
    public void serialize(final ObjectId objectId, final JsonGenerator jsonGenerator,
        final SerializerProvider serializerProvider) throws IOException {
        if (objectId != null) {
            jsonGenerator.writeString(objectId.toString());
        }
    }
}

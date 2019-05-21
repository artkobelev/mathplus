package ru.jprod.core.model.dto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

/**
 * Десериализатор из JSON для DtObject
 *
 * @author pmuravev
 * @since May 18, 2015
 */
public class DtoDeserializer extends JsonDeserializer<DtObject>
{
    //@formatter:off
    private static final TypeReference<HashMap<String, Object>> MAP_TYPE_REF =
            new TypeReference<HashMap<String, Object>>(){};
    //@formatter:on

    private static final ObjectReader READER = new ObjectMapper().readerFor(MAP_TYPE_REF);

    @Override
    public DtObject deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException
    {
        JsonNode node = jp.getCodec().readTree(jp);
        Map<String, Object> result = READER.readValue(node);
        return DtObject.fromMap(result);
    }
}

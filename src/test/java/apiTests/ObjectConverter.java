package apiTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectConverter {

   static ObjectMapper objectMapper = new ObjectMapper();

    public static CarPojo convertJsonToCarClass(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, CarPojo.class);
    }

    public static String convertJavaClassToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static <T> T convertJsonToJavaObject(String json, Class<T> valueType) throws JsonProcessingException {
        return objectMapper.readValue(json, valueType);
    }

}

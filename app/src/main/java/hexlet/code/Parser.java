package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String data, String format) throws JsonProcessingException {
        ObjectMapper mapper;
        if (format.equals("yml") || format.equals("yaml")) {
            mapper = new YAMLMapper();
        } else {
            mapper = new ObjectMapper();
        }
        return mapper.readValue(data, new TypeReference<>() { });
    }
}

package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String data, String format) throws Exception {
        return switch (format) {
            case "yml", "yaml" -> parseYaml(data);
            case "json" -> parseJson(data);
            default -> throw new Exception("Unknown format: '" + format + "'");
        };
    }

    public static Map<String, Object> parseYaml(String content) throws JsonProcessingException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(content, new TypeReference<>() { });
    }

    public static Map<String, Object> parseJson(String content) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, new TypeReference<>() { });
    }
}

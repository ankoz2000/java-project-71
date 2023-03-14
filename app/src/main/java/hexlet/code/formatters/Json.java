package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import hexlet.code.DTO;

import java.util.List;
import java.util.stream.Collectors;

public class Json {

    public static String format(List<DTO> differs) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        return "{\n" + differs.stream()
                .sorted(DTO::compareTo)
                .map(dto -> {
                    String result = "\"" + dto.getKey() + "\"" + ": ";
                    try {
                        if (dto.getDiffer() == null) {
                            dto.setDiffer("not changed");
                        }
                        if (dto.getDiffer().equals("-")) {
                            dto.setDiffer("removed");
                        }
                        if (dto.getDiffer().equals("+")) {
                            dto.setDiffer("added");
                        }
                        if (dto.getOldValue() != null) {
                            dto.setDiffer("changed");
                        }
                        result += objectMapper.writeValueAsString(dto);
                    } catch (JsonProcessingException e) {
                        System.out.println("Error while parsing json");
                    }
                    return result;
                })
                .collect(Collectors.joining(",\n"))
                .indent(2) + "}";
    }
}

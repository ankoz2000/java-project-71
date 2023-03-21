package hexlet.code.formatters;

import hexlet.code.DTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Plain {

    public static String format(List<DTO> differs) {
        return differs.stream()
                .sorted(DTO::compareTo)
                .map(Plain::getStr)
                .filter(str -> !str.isEmpty())
                .collect(Collectors.joining("\n"));
    }

    private static String getStr(DTO dto) {
        String key = dto.getKey();

        String formattedValue = stringify(dto.getValue());

        String newFormattedValue = stringify(dto.getValue());
        String oldFormattedValue = stringify(dto.getOldValue());

        String type = dto.getDiffer();

        switch (type) {
            case "added" -> {
                return "Property '" + key + "' was added with value: " + formattedValue;
            }
            case "removed" -> {
                return "Property '" + key + "' was removed";
            }
            case "changed" -> {
                return "Property '" + key + "' was updated. From " + oldFormattedValue + " to " + newFormattedValue;
            }
            case "unchanged" -> {
                return "";
            }
            default -> throw new RuntimeException("Unknown type: '" + type + "'");
        }
    }

    public static String stringify(Object value) {
        if (value == null) {
            return "null";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        return value.toString();
    }
}

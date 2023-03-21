package hexlet.code.formatters;

import hexlet.code.DTO;

import java.util.List;
import java.util.stream.Collectors;

public class Stylish {
    private static final int INDENT_COUNT = 2;

    public static String format(List<DTO> differs) {
        return "{\n" + differs.stream()
                .sorted(DTO::compareTo)
                .map(Stylish::getStr)
                .collect(Collectors.joining("\n"))
                + "\n}";
    }

    private static String getStr(DTO dto) {
        String indent = "\s".repeat(INDENT_COUNT);
        String key = dto.getKey();
        String formattedValue = dto.getValue() == null ? "null" : dto.getValue().toString();

        String newFormattedValue = dto.getValue() == null ? "null" : dto.getValue().toString();
        String oldFormattedValue = dto.getOldValue() == null ? "null" : dto.getOldValue().toString();

        String type = dto.getDiffer();

        return switch (type) {
            case "added" -> indent + "+ " + key + ": " + formattedValue;
            case "removed" -> indent + "- " + key + ": " + formattedValue;
            case "changed" -> indent + "- " + key + ": " + oldFormattedValue + "\n"
                    + indent + "+ " + key + ": " + newFormattedValue;
            case "unchanged" -> indent + "  " + key + ": " + formattedValue;
            default -> throw new RuntimeException("Unknown type: '" + type + "'");
        };
    }
}

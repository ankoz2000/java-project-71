package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;

public class Formatter {

    public static String format(List<DTO> data, String format) throws Exception {
        return switch (format) {
            case "stylish" -> Stylish.format(data);
            case "plain" -> Plain.format(data);
            case "json" -> Json.format(data);
            default -> throw new Exception("Unknown format");
        };
    }
}

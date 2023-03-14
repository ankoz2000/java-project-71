package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;

public class Formatter {

    public static String format(List<DTO> data, String format) {
        if (format.equals("stylish")) {
            return Stylish.format(data);
        } else if (format.equals("plain")) {
            return Plain.format(data);
        }
        return Json.format(data);
    }
}

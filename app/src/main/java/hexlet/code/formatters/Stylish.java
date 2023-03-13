package hexlet.code.formatters;

import hexlet.code.DTO;

import java.util.List;
import java.util.stream.Collectors;

public class Stylish {

    public static String format(List<DTO> differs) {
        return "{\n" + differs.stream()
                .sorted(DTO::compareTo)
                .map(dto -> (dto.getDiffer() != null ? "\s".repeat(2) + dto.getDiffer() + " " : "\s".repeat(4))
                            + dto.getKey() + ": "
                        + ((dto.getValue() != null) ? dto.getValue().toString() : "null"))
                .collect(Collectors.joining("\n"))
                + "\n}";
    }
}

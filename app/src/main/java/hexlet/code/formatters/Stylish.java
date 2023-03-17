package hexlet.code.formatters;

import hexlet.code.DTO;

import java.util.List;
import java.util.stream.Collectors;

public class Stylish {

    public static String format(List<DTO> differs) {
        return "{\n" + differs.stream()
                .sorted(DTO::compareTo)
                .map(dto -> {
                    if (dto.getOldValue() != null) {
                        return getStr(dto, true);
                    }
                    return getStr(dto, false);
                })
                .collect(Collectors.joining("\n"))
                + "\n}";
    }

    private static String getStr(DTO dto, boolean isEdit) {
        String old = "";
        int fourSpaces = 4;
        if (isEdit) {
            old = "\s".repeat(2) + "- " + dto.getKey() + ": " + dto.getOldValue() + "\n";
        }
        return (isEdit ? old : "") + (dto.getDiffer() != null ? "\s".repeat(2) + dto.getDiffer()
                + " " : "\s".repeat(fourSpaces))
                + dto.getKey() + ": "
                + ((dto.getValue() != null) ? dto.getValue().toString() : "null");
    }
}

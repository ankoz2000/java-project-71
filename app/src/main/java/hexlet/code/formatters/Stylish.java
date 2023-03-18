package hexlet.code.formatters;

import hexlet.code.DTO;

import java.util.List;
import java.util.stream.Collectors;

public class Stylish {
    private static final int FOUR_SPACES = 4;

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
        if (isEdit) {
            old = "\s".repeat(2) + "- " + dto.getKey() + ": " + dto.getOldValue() + "\n";
        }
        return (isEdit ? old : "") + (dto.getDiffer() != null ? "\s".repeat(2) + dto.getDiffer()
                + " " : "\s".repeat(FOUR_SPACES))
                + dto.getKey() + ": "
                + ((dto.getValue() != null) ? dto.getValue().toString() : "null");
    }
}

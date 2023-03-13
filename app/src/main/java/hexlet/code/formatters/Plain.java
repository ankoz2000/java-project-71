package hexlet.code.formatters;

import hexlet.code.DTO;

import java.util.List;
import java.util.stream.Collectors;

public class Plain {

    public static String format(List<DTO> differs) {
        String res = differs.stream()
                .sorted(DTO::compareTo)
                .filter(dto -> !dto.getIsUpdated())
                .map(dto -> {
                    if (dto.getDiffer() == null) {
                        return dto;
                    }
                    if (dto.getOldValue() != null) {
                        dto.setDiffer("changed");
                    } else if (dto.getDiffer().equals("+")) {
                        dto.setDiffer("added");
                    } else if (dto.getDiffer().equals("-")) {
                        dto.setDiffer("removed");
                    }
                    return dto;
                })
                .map(dto -> {
                    boolean isValueArrayOrObject = dto.getValue().getClass().isArray()
                            || dto.getValue().getClass().getDeclaredFields().length > 0;
                    return "Property '" + dto.getKey() + "' was " + dto.getDiffer() + "."
                            + (dto.getOldValue() != null ? ". From " + dto.getOldValue() + " to " + dto.getValue() : "")
                            + (dto.getDiffer() != null && dto.getDiffer().equals("added") ? " with value "
                            + (isValueArrayOrObject ? "[complex value]" : dto.getValue().toString()) : "");
                })
                .collect(Collectors.joining("\n"));
        return res;
    }
}

package hexlet.code.formatters;

import hexlet.code.DTO;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Plain {

    public static String format(List<DTO> differs) {
        return differs.stream()
                .sorted(DTO::compareTo)
                .filter(dto -> dto.getDiffer() != null)
                .map(dto -> {
                    if (dto.getOldValue() != null) {
                        dto.setDiffer("updated");
                    } else if (dto.getDiffer().equals("+")) {
                        dto.setDiffer("added");
                    } else if (dto.getDiffer().equals("-")) {
                        dto.setDiffer("removed");
                    }
                    return dto;
                })
                .map(dto -> {
                    boolean isValueOfCollectionType = dto.getValue() != null && (dto.getValue() instanceof Map
                            || (dto.getValue() instanceof Collection<?>) || dto.getValue().getClass().isArray());
                    boolean isOldValueOfCollectionType = dto.getOldValue() != null && (dto.getValue() instanceof Map
                            || (dto.getOldValue() instanceof Collection<?>) || dto.getOldValue().getClass().isArray());
                    String value = dto.getValue() != null ? dto.getValue().toString() : "null";
                    String oldValue = dto.getOldValue() != null ? dto.getOldValue().toString() : "null";
                    if (isValueOfCollectionType) {
                        value = "[complex value]";
                    }
                    if (isOldValueOfCollectionType) {
                        oldValue = "[complex value]";
                    }
                    if (dto.getValue() instanceof String && !dto.getValue().equals("null")) {
                        value = "'" + value + "'";
                    }
                    if (dto.getOldValue() instanceof String && !dto.getOldValue().equals("null")) {
                        oldValue = "'" + oldValue + "'";
                    }
                    return "Property '" + dto.getKey() + "' was " + dto.getDiffer()
                            + (dto.getOldValue() != null ? ". From " + oldValue + " to " + value : "")
                            + (dto.getDiffer() != null && dto.getDiffer().equals("added") ? " with value: "
                            + value : "");
                })
                .collect(Collectors.joining("\n"));
    }
}

package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(String firstFileData, String secondFileData) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String,Object> firstMap = objectMapper.readValue(firstFileData, new TypeReference<Map<String,Object>>(){});
        Map<String,Object> secondMap = objectMapper.readValue(secondFileData, new TypeReference<Map<String,Object>>(){});

        List<DTO> differs = new ArrayList<>();

        secondMap.entrySet().forEach(sm -> {
            if (!firstMap.containsKey(sm.getKey())) {
                DTO added = new DTO(sm.getKey(), sm.getValue().toString(), "+");
                differs.add(added);
            }
        });

        firstMap.entrySet().forEach(fm -> {
            if (secondMap.containsKey(fm.getKey())) {
                secondMap.entrySet().forEach(sm -> {
                    if (sm.getKey().equals(fm.getKey())) {
                        DTO oldDTO = new DTO(fm.getKey(), fm.getValue().toString());
                        differs.add(oldDTO);
                        if (!fm.getValue().equals(sm.getValue())) {
                            oldDTO.setDiffer("-");
                            DTO newDTO = new DTO(sm.getKey(), sm.getValue().toString(), "+");
                            differs.add(newDTO);
                        }
                    }
                });
            } else {
                DTO deleted = new DTO(fm.getKey(), fm.getValue().toString(), "-");
                differs.add(deleted);
            }
        });

        return differs.stream()
                .sorted(DTO::compareTo)
                .map(DTO::toString)
                .collect(Collectors.joining());
    }
}

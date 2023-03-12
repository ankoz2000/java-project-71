package hexlet.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Differ {

    public static String generate(String firstFileData, String secondFileData, String format) throws IOException {
        if (firstFileData == null || secondFileData == null
                || firstFileData.equals(secondFileData)) {
            return "";
        }

        Map<String, Object> firstMap = Parser.parse(firstFileData, format);
        Map<String, Object> secondMap = Parser.parse(secondFileData, format);

        List<DTO> differs = new ArrayList<>();

        secondMap.entrySet().forEach(sm -> {
            if (!firstMap.containsKey(sm.getKey())) {
                DTO added = new DTO(sm.getKey(), sm.getValue(), "+");
                differs.add(added);
            }
        });

        firstMap.entrySet().forEach(fm -> {
            if (secondMap.containsKey(fm.getKey())) {
                secondMap.entrySet().forEach(sm -> {
                    if (Objects.equals(sm.getKey(), fm.getKey())) {
                        DTO oldDTO = new DTO(fm.getKey(), fm.getValue());
                        differs.add(oldDTO);
                        if (!Objects.equals(fm.getValue(), sm.getValue())) {
                            oldDTO.setDiffer("-");
                            DTO newDTO = new DTO(sm.getKey(), sm.getValue(), "+");
                            differs.add(newDTO);
                        }
                    }
                });
            } else {
                DTO deleted = new DTO(fm.getKey(), fm.getValue(), "-");
                differs.add(deleted);
            }
        });
        Stylish.format(differs);
        return Stylish.format(differs);
    }
}

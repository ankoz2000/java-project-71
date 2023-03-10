package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Differ {

    public static String generate(Path filepath1, Path filepath2, String format) throws IOException {
        if (filepath1 == null || filepath2 == null
                || filepath1.equals(filepath2)) {
            return "";
        }

        String firstFileData = null;
        String secondFileData = null;
        try {
            firstFileData = App.readFile(filepath1);
            secondFileData = App.readFile(filepath2);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        String fileFormat = filepath1.toString().trim().split("\\.")[1];
        Map<String, Object> firstMap = Parser.parse(firstFileData, fileFormat);
        Map<String, Object> secondMap = Parser.parse(secondFileData, fileFormat);

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
                            oldDTO.setIsUpdated(true);
                            DTO newDTO = new DTO(sm.getKey(), sm.getValue(), "+");
                            newDTO.setOldValue(fm.getValue());
                            differs.add(newDTO);
                        }
                    }
                });
            } else {
                DTO deleted = new DTO(fm.getKey(), fm.getValue(), "-");
                differs.add(deleted);
            }
        });
        return Formatter.format(differs, format);
    }
}

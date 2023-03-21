package hexlet.code;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        if (filepath1 == null || filepath2 == null
                || filepath1.equals(filepath2)) {
            return "{\n\n}";
        }

        String firstFileData;
        String secondFileData;

        firstFileData = Differ.readFile(Paths.get(filepath1));
        secondFileData = Differ.readFile(Paths.get(filepath2));

        String fileFormat = getDataFormat(filepath1);
        Map<String, Object> firstMap = Parser.parse(firstFileData, fileFormat);
        Map<String, Object> secondMap = Parser.parse(secondFileData, fileFormat);

        return Formatter.format(getDiffers(firstMap, secondMap), format);
    }

    public static List<DTO> getDiffers(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        List<DTO> differs = new ArrayList<>();

        secondMap.entrySet().forEach(sm -> {
            if (!firstMap.containsKey(sm.getKey())) {
                DTO added = new DTO(sm.getKey(), sm.getValue(), "added");
                differs.add(added);
            }
        });

        firstMap.entrySet().forEach(fm -> {
            if (secondMap.containsKey(fm.getKey())) {
                secondMap.entrySet().forEach(sm -> {
                    if (Objects.equals(sm.getKey(), fm.getKey())) {
                        DTO oldDTO = new DTO(fm.getKey(), fm.getValue());
                        if (!Objects.equals(fm.getValue(), sm.getValue())) {
                            DTO newDTO = new DTO(sm.getKey(), sm.getValue(), "changed");
                            newDTO.setOldValue(oldDTO.getValue());
                            differs.add(newDTO);
                        } else {
                            oldDTO.setDiffer("unchanged");
                            differs.add(oldDTO);
                        }
                    }
                });
            } else {
                DTO deleted = new DTO(fm.getKey(), fm.getValue(), "removed");
                differs.add(deleted);
            }
        });
        return differs;
    }

    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0 ? filePath.substring(index + 1) : "";
    }

    public static String readFile(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException("File at path '" + path + "' not found");
        }
        return String.join("\n", Files.readAllLines(path));
    }
}

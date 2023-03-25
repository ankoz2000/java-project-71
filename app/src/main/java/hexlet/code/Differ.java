package hexlet.code;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        if (filepath1 == null || filepath2 == null
                || filepath1.equals(filepath2)) {
            return "";
        }

        String firstFileData = Differ.readFile(Paths.get(filepath1));
        String secondFileData = Differ.readFile(Paths.get(filepath2));

        String fileFormat = getDataFormat(filepath1);
        Map<String, Object> firstMap = Parser.parse(firstFileData, fileFormat);
        Map<String, Object> secondMap = Parser.parse(secondFileData, fileFormat);

        return Formatter.format(DifferFinder.getDiffers(firstMap, secondMap), format);
    }



    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0 ? filePath.substring(index + 1) : "";
    }

    public static String readFile(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException("File at path '" + path + "' not found");
        }
        return Files.readString(Path.of(path.toString()));
    }
}

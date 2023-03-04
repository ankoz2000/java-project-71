package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"}, description = "Show this help message and exit.")
    private String format;
    @Parameters(paramLabel  = "filepath1", description = "path to first file")
    private String filepath1;
    @Parameters(paramLabel  = "filepath2", description = "path to second file")
    private String filepath2;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        String firstFileData = null;
        String secondFileData = null;
        try {
            firstFileData = readFile(Paths.get(filepath1));
            secondFileData = readFile(Paths.get(filepath2));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        Differ.generate(firstFileData, secondFileData);
        return null;
    }

    private static String readFile(Path path) throws IOException {
        if (!Files.exists(path)) {
            throw new FileNotFoundException("File at path '" + path + "' not found");
        }
        return String.join("", Files.readAllLines(path));
    }
}

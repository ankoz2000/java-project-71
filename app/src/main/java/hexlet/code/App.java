package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {

    private static final int SUCCESS_EXIT_CODE = 0;
    private static final int ERROR_EXIT_CODE = 1;

    @Option(names = {"-f", "--format"}, description = "Show this help message and exit.", defaultValue = "stylish")
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
        try {
            System.out.println(Differ.generate(filepath1, filepath2, format));
        } catch (Exception e) {
            System.out.println("Exception while generating differs");
            return ERROR_EXIT_CODE;
        }
        return SUCCESS_EXIT_CODE;
    }
}

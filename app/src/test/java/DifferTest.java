import hexlet.code.App;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static hexlet.code.Differ.generate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private String result;
    private String recursiveResult;
    private String jsonResult;
    private String testString1;
    private String testString2;
    private String testRecursive1;
    private String testRecursive2;
    private String testRecursiveYml1;
    private String testRecursiveYml2;
    private String testStringYml1;
    private String testStringYml2;
    private final String json = "json";
    private final String yml = "yml";
    private final String stylish = "stylish";
    private final String plain = "plain";

    @BeforeEach
    public void prepare() throws IOException {
        String fp1 = "./src/test/resources/test1";
        String fp2 = "./src/test/resources/test2";

        String fpr1 = "./src/test/resources/recursive1";
        String fpr2 = "./src/test/resources/recursive2";

        testString1 = fp1 + "." + json;
        testString2 = fp2 + "." + json;

        testStringYml1 = fp1 + "." + yml;
        testStringYml2 = fp2 + "." + yml;

        testRecursive1 = fpr1 + "." + json;
        testRecursive2 = fpr2 + "." + json;

        testRecursiveYml1 = fpr1 + "." + yml;
        testRecursiveYml2 = fpr2 + "." + yml;

        String resultFp = "./src/test/resources/result.txt";
        result = App.readFile(Paths.get(resultFp));

        String recursiveResultFp = "./src/test/resources/recursiveResult.txt";
        recursiveResult = App.readFile(Paths.get(recursiveResultFp));

        String jsonResultFp = "./src/test/resources/jsonResult.txt";
        jsonResult = App.readFile(Paths.get(jsonResultFp));
    }

    @Test
    @Deprecated
    public void generalTest() {
        try {
            assertEquals(generate(testString1, testString1, stylish), "{\n\n}");
            assertEquals(generate(testString2, testString2, stylish), "{\n\n}");
            assertEquals(generate(testString1, testString2, stylish), result);
        } catch (IOException e) {
            System.out.println("[TEST] Error: " + e.getLocalizedMessage());
        }
    }

    @Test
    public void emptyTest() {
        try {
            assertEquals(generate("./src/test/resources/empty",
                    "./src/test/resources/empty", stylish), "{\n\n}");
            assertEquals(generate("./src/test/resources/empty",
                    "./src/test/resources/empty", stylish), "{\n\n}");
        } catch (IOException e) {
            System.out.println("[TEST] Error: " + e.getLocalizedMessage());
        }
    }

    @Test
    @Deprecated
    public void ymlTest() {
        try {
            assertEquals(generate(testStringYml1, testStringYml2, stylish), result);
        } catch (IOException e) {
            System.out.println("[TEST] Error: " + e.getLocalizedMessage());
        }
    }

    @Test
    public void recursiveJsonTest() {
        try {
            assertEquals(generate(testRecursive1, testRecursive2, stylish), recursiveResult);
        } catch (IOException e) {
            System.out.println("[TEST] Error: " + e.getLocalizedMessage());
        }
    }

    @Test
    public void recursiveYmlTest() {
        try {
            assertEquals(generate(testRecursiveYml1, testRecursiveYml2, stylish), recursiveResult);
        } catch (IOException e) {
            System.out.println("[TEST] Error: " + e.getLocalizedMessage());
        }
    }

    @Test
    public void plainFormatTest() {
        try {
            assertEquals(generate(testRecursiveYml1, testRecursiveYml2, plain), recursiveResult);
        } catch (IOException e) {
            System.out.println("[TEST] Error: " + e.getLocalizedMessage());
        }
    }

    @Test
    public void jsonFormatTest() {
        try {
            assertEquals(generate(testRecursive1, testRecursive2, json), jsonResult);
        } catch (IOException e) {
            System.out.println("[TEST] Error: " + e.getLocalizedMessage());
        }
    }
}

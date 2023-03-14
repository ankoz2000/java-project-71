import hexlet.code.App;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.Differ.generate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private String result;
    private String recursiveResult;
    private String jsonResult;
    private Path testString1;
    private Path testString2;
    private Path testRecursive1;
    private Path testRecursive2;
    private Path testRecursiveYml1;
    private Path testRecursiveYml2;
    private Path testStringYml1;
    private Path testStringYml2;
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

        testString1 = Paths.get(fp1 + "." + json);
        testString2 = Paths.get(fp2 + "." + json);

        testStringYml1 = Paths.get(fp1 + "." + yml);
        testStringYml2 = Paths.get(fp2 + "." + yml);

        testRecursive1 = Paths.get(fpr1 + "." + json);
        testRecursive2 = Paths.get(fpr2 + "." + json);

        testRecursiveYml1 = Paths.get(fpr1 + "." + yml);
        testRecursiveYml2 = Paths.get(fpr2 + "." + yml);

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
            assertEquals(generate(testString1, testString1, stylish), "");
            assertEquals(generate(testString2, testString2, stylish), "");
            assertEquals(generate(testString1, testString2, stylish), result);
        } catch (IOException e) {
            System.out.println("[TEST] Error: " + e.getLocalizedMessage());
        }
    }

    @Test
    public void emptyTest() {
        try {
            assertEquals(generate(Paths.get("./src/test/resources/empty"),
                    Paths.get("./src/test/resources/empty"), stylish), "");
            assertEquals(generate(Paths.get("./src/test/resources/empty"),
                    Paths.get("./src/test/resources/empty"), stylish), "");
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

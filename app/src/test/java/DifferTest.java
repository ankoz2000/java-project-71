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

    @BeforeEach
    public void prepare() throws IOException {
        String fp1 = "./src/test/resources/test1";
        String fp2 = "./src/test/resources/test2";

        String fpr1 = "./src/test/resources/recursive1";
        String fpr2 = "./src/test/resources/recursive2";

        testString1 = App.readFile(Paths.get(fp1 + "." + json));
        testString2 = App.readFile(Paths.get(fp2 + "." + json));


        testStringYml1 = App.readFile(Paths.get(fp1 + "." + yml));
        testStringYml2 = App.readFile(Paths.get(fp2 + "." + yml));

        testRecursive1 = App.readFile(Paths.get(fpr1 + "." + json));
        testRecursive2 = App.readFile(Paths.get(fpr2 + "." + json));

        testRecursiveYml1 = App.readFile(Paths.get(fpr1 + "." + yml));
        testRecursiveYml2 = App.readFile(Paths.get(fpr2 + "." + yml));

        String resultFp = "./src/test/resources/result.txt";
        result = App.readFile(Paths.get(resultFp));

        String recursiveResultFp = "./src/test/resources/recursiveResult.txt";
        recursiveResult = App.readFile(Paths.get(recursiveResultFp));
    }

    @Test
    @Deprecated
    public void generalTest() {
        try {
            assertEquals(generate(testString1, testString1, json), "");
            assertEquals(generate(testString2, testString2, json), "");
            assertEquals(generate(testString1, testString2, json), result);
        } catch (IOException e) {
            System.out.println("[TEST] Error: " + e.getLocalizedMessage());
        }
    }

    @Test
    public void emptyTest() {
        try {
            assertEquals(generate("{}", "{}", json), "");
            assertEquals(generate("{}", "{}", yml), "");
        } catch (IOException e) {
            System.out.println("[TEST] Error: " + e.getLocalizedMessage());
        }
    }

    @Test
    @Deprecated
    public void ymlTest() {
        try {
            assertEquals(generate(testStringYml1, testStringYml2, yml), result);
        } catch (IOException e) {
            System.out.println("[TEST] Error: " + e.getLocalizedMessage());
        }
    }

    @Test
    public void recursiveJsonTest() {
        try {
            assertEquals(generate(testRecursive1, testRecursive2, json), recursiveResult);
        } catch (IOException e) {
            System.out.println("[TEST] Error: " + e.getLocalizedMessage());
        }
    }

    @Test
    public void recursiveYmlTest() {
        try {
            assertEquals(generate(testRecursiveYml1, testRecursiveYml2, yml), recursiveResult);
        } catch (IOException e) {
            System.out.println("[TEST] Error: " + e.getLocalizedMessage());
        }
    }
}

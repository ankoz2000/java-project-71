import hexlet.code.App;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static hexlet.code.Differ.generate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private String result;
    private String testString1;
    private String testString2;

    @BeforeEach
    public void prepare() throws IOException {
        String fp1 = "./src/test/resources/test1.json";
        testString1 = App.readFile(Paths.get(fp1));
        String fp2 = "./src/test/resources/test2.json";
        testString2 = App.readFile(Paths.get(fp2));
        String resultFp = "./src/test/resources/result.txt";
        result = App.readFile(Paths.get(resultFp));
    }

    @Test
    public void generalTest() {
        try {
            assertEquals(generate(testString1, testString1), "");
            assertEquals(generate(testString2, testString2), "");
            assertEquals(generate(testString1, testString2), result);
        } catch (IOException e) {
            System.out.println("[TEST] Error: " + e.getLocalizedMessage());
        }
    }

    @Test
    public void emptyTest() {
        try {
            assertEquals(generate("{}", "{}"), "");
        } catch (IOException e) {
            System.out.println("[TEST] Error: " + e.getLocalizedMessage());
        }
    }
}

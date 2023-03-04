import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static hexlet.code.Differ.generate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private String result = "- follow: false\n" + "host: hexlet.io\n"
            + "- proxy: 123.234.53.22\n" + "- timeout: 50\n"
            + "+ timeout: 20\n" + "+ verbose: true\n";
    private String testString1;
    private String testString2;

    @BeforeEach
    public void prepare() {
        testString1 = "{\n" + "  \"host\": \"hexlet.io\",\n"
                + "  \"timeout\": 50,\n" + "  \"proxy\": \"123.234.53.22\",\n"
                + "  \"follow\": false\n" + "}";

        testString2 = "{\n" + "  \"timeout\": 20,\n"
                + "  \"verbose\": true,\n"
                + "  \"host\": \"hexlet.io\"\n" + "}";
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

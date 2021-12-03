package viewTests;

import com.pp.view.InputScanner;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class InputScannerTests {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent = new ByteArrayInputStream("three\n15\n \ntest".getBytes());

    @BeforeEach
    public void setUpStreams() {
        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    @DisplayName("Test getInstance InputScanner")
    public void testGetInstance() {
        Assertions.assertNotNull(InputScanner.getInstance());
        Assertions.assertNotNull(InputScanner.getInstance());
    }

    @Test
    @DisplayName("Test getNumber InputScanner")
    public void testGetNumber() {

        int result = InputScanner.getInstance().getNumber();
        Assertions.assertEquals("Помилка. Будь ласка введіть значення" + System.lineSeparator(),
                outContent.toString());
        Assertions.assertEquals(15, result);
    }

    @Test
    @DisplayName("Test getString InputScanner")
    public void testGetString() {
        String result = InputScanner.getInstance().getString();
        Assertions.assertEquals("Помилка. Будь ласка введіть значення" + System.lineSeparator(),
                outContent.toString());
        Assertions.assertEquals("test", result);
    }
}

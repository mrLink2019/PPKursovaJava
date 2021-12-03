package viewTests;

import com.ginsberg.junit.exit.ExpectSystemExitWithStatus;
import com.pp.view.UserMenu;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class UserMenuTests {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent1 = new ByteArrayInputStream(
            "1\n2\ntest\n25\n3\n1\n4\n5\n1\n1\n450\n6\n1\n7\n8\n9\n10\n11\n20\n13\n12\n14".getBytes());

    @BeforeEach
    public void setUpStreams1() {
        System.setIn(inContent1);
        System.setOut(new PrintStream(outContent1));
    }

    @AfterEach
    public void restoreStreams1() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    @DisplayName("Test getInstance UserMenu")
    public void testGetInstance() {
        Assertions.assertNotNull(UserMenu.getInstance());
        Assertions.assertNotNull(UserMenu.getInstance());
    }

    @Test
    @DisplayName("Test startUserMenu UserMenu")
    @ExpectSystemExitWithStatus(0)
    public void testUserMenu() {
        UserMenu menu = UserMenu.getInstance();
        menu.startUserMenu();
    }
}

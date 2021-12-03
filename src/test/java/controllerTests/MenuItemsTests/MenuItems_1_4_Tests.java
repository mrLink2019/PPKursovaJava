package controllerTests.MenuItemsTests;

import com.pp.controller.Storage;
import com.pp.controller.Van;
import com.pp.controller.menuItems.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class MenuItems_1_4_Tests {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent = new ByteArrayInputStream(
            "0\ntest\n0\n25\ntest\n\nv1\n35\n1\n1".getBytes());

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
    @DisplayName("Test 1 ViewVansInStorageMenuItem MenuItem")
    public void testViewVansInStorageMenuItem() {
        new ViewVansInStorageMenuItem().execute();
    }

    @Test
    @DisplayName("Test 2 & 3 CreateNewVanMenuItem & DeleteVanMenuItem MenuItem")
    public void testCreateNewVanMenuItem() {
        new CreateNewVanMenuItem().execute();
        new CreateNewVanMenuItem().execute();
        Storage.getInstance().deleteVan(Storage.getInstance().getVansStorage().get(0));
        new DeleteVanMenuItem().execute();
        new DeleteVanMenuItem().execute();
    }

    @Test
    @DisplayName("Test 4 ViewCoffeeInStorageMenuItem MenuItem")
    public void testViewCoffeeInStorageMenuItem() {
        new ViewCoffeeInStorageMenuItem().execute();
    }
}

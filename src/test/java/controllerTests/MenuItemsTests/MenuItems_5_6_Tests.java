package controllerTests.MenuItemsTests;

import com.pp.controller.Storage;
import com.pp.controller.Van;
import com.pp.controller.menuItems.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class MenuItems_5_6_Tests {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent = new ByteArrayInputStream(
            "0\n8\n1\n0\n20\n1\n20\n600\n2\n1\n400\n3\n1\n450\n4\n1\n300\n1\n1".getBytes());

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
    @DisplayName("Test 5 & 6 AddCoffeeToStorageMenuItem & DeleteCoffeeFromStorageMenuItem MenuItem")
    public void testAddCoffeeToStorageMenuItem() {
        new AddCoffeeToStorageMenuItem().execute();
        new AddCoffeeToStorageMenuItem().execute();
        new AddCoffeeToStorageMenuItem().execute();
        new AddCoffeeToStorageMenuItem().execute();
        Storage.getInstance().deleteCoffee(Storage.getInstance().getCoffeeStorage().get(0));
        Storage.getInstance().deleteCoffee(Storage.getInstance().getCoffeeStorage().get(0));
        Storage.getInstance().deleteCoffee(Storage.getInstance().getCoffeeStorage().get(0));
        new DeleteCoffeeFromStorageMenuItem().execute();
        new DeleteCoffeeFromStorageMenuItem().execute();
    }
}

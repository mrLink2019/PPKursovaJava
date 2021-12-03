package controllerTests.MenuItemsTests;

import com.ginsberg.junit.exit.ExpectSystemExitWithStatus;
import com.pp.controller.Storage;
import com.pp.controller.Van;
import com.pp.controller.menuItems.*;
import com.pp.model.BeanCoffee;
import com.pp.model.Coffee;
import com.pp.model.GroundCoffee;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class MenuItems_11_14_Tests {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent = new ByteArrayInputStream(
            "1\n1\n1\n1\n6\n2\n1\n3\n1\n5\n6".getBytes());

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
    @DisplayName("Test 11 FindCoffeeInVanMenuItem MenuItem")
    public void testFindCoffeeInVanMenuItem() {
        new FindCoffeeInVanMenuItem().execute();
        Storage.getInstance().addVan("test", 25);
        new FindCoffeeInVanMenuItem().execute();
        Coffee testCoffee1 = BeanCoffee.buyCoffee();
        testCoffee1.setSort(1); testCoffee1.setFullPrice(450);
        testCoffee1.calculateFullVolume(); testCoffee1.packCoffee();
        Storage.getInstance().addCoffee(testCoffee1);
        new TransferCoffeeFromStorageToVanMenuItem().execute();
        new FindCoffeeInVanMenuItem().execute();
        new FindCoffeeInVanMenuItem().execute();
        Storage.getInstance().deleteVan(Storage.getInstance().getVansStorage().get(0));
    }

    @Test
    @DisplayName("Test 12 & 13 WriteToFileMenuItem & ReadFromFileMenuItem MenuItem")
    public void testWriteToFileMenuItem() {
        new WriteToFileMenuItem().execute();
        new ReadFromFileMenuItem().execute();
        Assertions.assertEquals(new ArrayList<Van>(), Storage.getInstance().getVansStorage());
        Assertions.assertEquals(new ArrayList<Coffee>(), Storage.getInstance().getCoffeeStorage());
    }

    @Test
    @ExpectSystemExitWithStatus(0)
    @DisplayName("Test 14 ExitMenuItem MenuItem")
    public void testExitMenuItem() {
        new ExitMenuItem().execute();
    }
}

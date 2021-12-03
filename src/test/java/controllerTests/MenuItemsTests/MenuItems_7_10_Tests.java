package controllerTests.MenuItemsTests;

import com.pp.controller.Storage;
import com.pp.controller.menuItems.*;
import com.pp.model.BeanCoffee;
import com.pp.model.Coffee;
import com.pp.model.GroundCoffee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class MenuItems_7_10_Tests {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent = new ByteArrayInputStream(
            "1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1".getBytes());

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
    @DisplayName("Test 7 & 8 TransferCoffeeFromStorageToVanMenuItem & TransferCoffeeFromVanToStorageMenuItem MenuItem")
    public void testTransferCoffeeFromStorageToVanMenuItem() {
        new TransferCoffeeFromStorageToVanMenuItem().execute();
        Coffee testCoffee1 = BeanCoffee.buyCoffee();
        testCoffee1.setSort(1); testCoffee1.setFullPrice(450);
        testCoffee1.calculateFullVolume(); testCoffee1.packCoffee();
        Coffee testCoffee2 = GroundCoffee.buyCoffee();
        testCoffee2.setSort(1); testCoffee2.setFullPrice(350);
        testCoffee2.calculateFullVolume(); testCoffee2.packCoffee();
        Storage.getInstance().addCoffee(testCoffee1);
        Storage.getInstance().addCoffee(testCoffee2);
        new TransferCoffeeFromStorageToVanMenuItem().execute();
        Storage.getInstance().addVan("test", 2);
        new TransferCoffeeFromStorageToVanMenuItem().execute();
        new TransferCoffeeFromStorageToVanMenuItem().execute();
        new TransferCoffeeFromVanToStorageMenuItem().execute();
        new TransferCoffeeFromVanToStorageMenuItem().execute();
        Storage.getInstance().deleteVan(Storage.getInstance().getVansStorage().get(0));
        new TransferCoffeeFromVanToStorageMenuItem().execute();
        Storage.getInstance().deleteCoffee(Storage.getInstance().getCoffeeStorage().get(0));
        Storage.getInstance().deleteCoffee(Storage.getInstance().getCoffeeStorage().get(0));
    }

    @Test
    @DisplayName("Test 9 ViewCoffeeInVanMenuItem MenuItem")
    public void testViewCoffeeInVanMenuItem() {
        new ViewCoffeeInVanMenuItem().execute();
        Storage.getInstance().addVan("test", 25);
        Coffee testCoffee1 = BeanCoffee.buyCoffee();
        testCoffee1.setSort(1); testCoffee1.setFullPrice(450);
        testCoffee1.calculateFullVolume(); testCoffee1.packCoffee();
        Storage.getInstance().addCoffee(testCoffee1);
        new ViewCoffeeInVanMenuItem().execute();
        new TransferCoffeeFromStorageToVanMenuItem().execute();
        new ViewCoffeeInVanMenuItem().execute();

        Storage.getInstance().deleteVan(Storage.getInstance().getVansStorage().get(0));
    }

    @Test
    @DisplayName("Test 10 SortCoffeeInVanMenuItem  MenuItem")
    public void testSortCoffeeInVanMenuItem () {
        Storage.getInstance().addVan("test", 25);
        Coffee testCoffee1 = BeanCoffee.buyCoffee();
        testCoffee1.setSort(1); testCoffee1.setFullPrice(450);
        testCoffee1.calculateFullVolume(); testCoffee1.packCoffee();
        Storage.getInstance().addCoffee(testCoffee1);
        new SortCoffeeInVanMenuItem().execute();
        new TransferCoffeeFromStorageToVanMenuItem().execute();
        new SortCoffeeInVanMenuItem().execute();
        Storage.getInstance().deleteVan(Storage.getInstance().getVansStorage().get(0));
    }
}

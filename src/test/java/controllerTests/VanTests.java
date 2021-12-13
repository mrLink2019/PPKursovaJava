package controllerTests;

import com.pp.controller.Storage;
import com.pp.controller.Van;
import com.pp.model.BeanCoffee;
import com.pp.model.Coffee;
import com.pp.model.GroundCoffee;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class VanTests {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent = new ByteArrayInputStream("5\n1\n5\n1\n0\n1".getBytes());

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

    @ParameterizedTest
    @DisplayName("Test getName Van")
    @ValueSource(strings = { "v1", "v2", "test" })
    public void testGetName(String input) {
        Storage storage = Storage.getInstance();
        storage.addVan(input, 1);
        Assertions.assertEquals(storage.getVansStorage().get(0).getName(), input);
        storage.deleteVan(storage.getVansStorage().get(0));
    }

    @Test
    @DisplayName("Test deleteCargo Van")
    public void testDeleteCargo() {
        Storage storage = Storage.getInstance();
        storage.addVan("test", 50);
        Coffee testCoffee1 = BeanCoffee.buyCoffee();
        testCoffee1.setSort(1); testCoffee1.setFullPrice(450);
        testCoffee1.calculateFullVolume(); testCoffee1.packCoffee();

        Assertions.assertFalse(storage.getVansStorage().get(0).deleteCargo(testCoffee1));
        storage.getVansStorage().get(0).addCargo(testCoffee1);
        Assertions.assertTrue(storage.getVansStorage().get(0).deleteCargo(testCoffee1));

        storage.deleteVan(storage.getVansStorage().get(0));
    }

    @Test
    @DisplayName("Test findCargo Van")
    public void testFindCargo() {
        Storage storage = Storage.getInstance();
        storage.addVan("test", 50);
        Coffee testCoffee1 = BeanCoffee.buyCoffee();
        testCoffee1.setSort(1); testCoffee1.setFullPrice(450);
        testCoffee1.calculateFullVolume(); testCoffee1.packCoffee();
        Coffee testCoffee2 = GroundCoffee.buyCoffee();
        testCoffee2.setSort(3); testCoffee2.setFullPrice(600);
        testCoffee2.calculateFullVolume(); testCoffee2.packCoffee();

        Assertions.assertEquals(storage.getVansStorage().get(0).findCargo(1, 2), new ArrayList<Coffee>());
        storage.getVansStorage().get(0).addCargo(testCoffee1);
        storage.getVansStorage().get(0).addCargo(testCoffee2);
        Assertions.assertEquals(storage.getVansStorage().get(0).findCargo(0, 0), new ArrayList<Coffee>());
        ArrayList<Coffee> testList1 = new ArrayList<>();
        testList1.add(testCoffee1);
        Assertions.assertEquals(storage.getVansStorage().get(0).findCargo(1, 2), testList1);
        Assertions.assertEquals(storage.getVansStorage().get(0).findCargo(0, 1), testList1);
        testList1.add(testCoffee2);
        Assertions.assertEquals(storage.getVansStorage().get(0).findCargo(1, 5), testList1);

        storage.deleteVan(storage.getVansStorage().get(0));
    }

    @Test
    @DisplayName("Test sortCargo Van")
    public void testSortCargo() {
        Storage storage = Storage.getInstance();
        storage.addVan("test", 50);
        Coffee testCoffee1 = BeanCoffee.buyCoffee();
        testCoffee1.setSort(1); testCoffee1.setFullPrice(450);
        testCoffee1.calculateFullVolume(); testCoffee1.packCoffee();
        storage.getVansStorage().get(0).addCargo(testCoffee1);
        Coffee testCoffee2 = BeanCoffee.buyCoffee();
        testCoffee2.setSort(1); testCoffee2.setFullPrice(600);
        testCoffee2.calculateFullVolume(); testCoffee2.packCoffee();

        storage.getVansStorage().get(0).addCargo(testCoffee2);
        storage.getVansStorage().get(0).sortCargo();
        ArrayList <Coffee> sortedCargo = new ArrayList<>();
        sortedCargo.add(testCoffee2);
        sortedCargo.add(testCoffee1);
        Assertions.assertEquals(storage.getVansStorage().get(0).getCargo(), sortedCargo);

        storage.deleteVan(storage.getVansStorage().get(0));
    }

    @Test
    @DisplayName("Test addCargo Van")
    public void testAddCargo() {
        Storage storage = Storage.getInstance();
        storage.addVan("test", 2);
        Coffee testCoffee = BeanCoffee.buyCoffee();
        testCoffee.setSort(1); testCoffee.setFullPrice(500);

        testCoffee.calculateFullVolume(); testCoffee.packCoffee();
        Assertions.assertTrue(storage.getVansStorage().get(0).addCargo(testCoffee));
        Assertions.assertFalse(storage.getVansStorage().get(0).addCargo(testCoffee));

        storage.deleteVan(storage.getVansStorage().get(0));
    }

    @Test
    @DisplayName("Test hasVolumeForCargo Van")
    public void testHasVolumeForCargo() {
        Storage storage = Storage.getInstance();
        storage.addVan("test", 2);
        Coffee testCoffee = BeanCoffee.buyCoffee();
        testCoffee.setSort(1); testCoffee.setFullPrice(500);
        testCoffee.calculateFullVolume(); testCoffee.packCoffee();

        Assertions.assertTrue(storage.getVansStorage().get(0).hasVolumeForCargo(testCoffee));
        storage.getVansStorage().get(0).addCargo(testCoffee);
        Assertions.assertFalse(storage.getVansStorage().get(0).hasVolumeForCargo(testCoffee));

        storage.deleteVan(storage.getVansStorage().get(0));
    }

    @ParameterizedTest
    @DisplayName("Test calculateCargoVolume&addCargo Van")
    @ValueSource(ints = { 100, 250, 400 })
    public void testCalculateCargoVolume(int input) {
        Storage storage = Storage.getInstance();
        storage.addVan("test", 50);
        Coffee testCoffee = BeanCoffee.buyCoffee();
        testCoffee.setSort(1); testCoffee.setFullPrice(input);
        testCoffee.calculateFullVolume(); testCoffee.packCoffee();

        storage.getVansStorage().get(0).addCargo(testCoffee);
        Assertions.assertEquals(storage.getVansStorage().get(0).getCargoVolume(), testCoffee.getFullVolume());

        storage.deleteVan(storage.getVansStorage().get(0));
    }

    @ParameterizedTest
    @DisplayName("Test calculateCargoPrice&addCargo Van")
    @ValueSource(ints = { 100, 250, 400 })
    public void testCalculateCargoPrice(int input) {
        Storage storage = Storage.getInstance();
        storage.addVan("test", 50);
        Coffee testCoffee = BeanCoffee.buyCoffee();
        testCoffee.setSort(1); testCoffee.setFullPrice(input);
        testCoffee.calculateFullVolume(); testCoffee.packCoffee();

        storage.getVansStorage().get(0).addCargo(testCoffee);
        Assertions.assertEquals(storage.getVansStorage().get(0).getCargoPrice(), testCoffee.getFullPrice());

        storage.deleteVan(storage.getVansStorage().get(0));
    }

    @Test
    @DisplayName("Test toString Van")
    public void testToString() {
        Storage storage = Storage.getInstance();
        storage.addVan("v1", 1);
        Van testVan = storage.getVansStorage().get(0);
        Assertions.assertEquals(testVan.toString(), "ім'я: " + testVan.getName() + "; об'єм: "
                + testVan.getVanVolume() + ";");
        storage.deleteVan(storage.getVansStorage().get(0));
    }

    @Test
    @DisplayName("Test getCargo Van")
    public void testGetCargo() {
        Storage storage = Storage.getInstance();
        storage.addVan("test", 1);
        Assertions.assertEquals(storage.getVansStorage().get(0).getCargo(), new ArrayList<Coffee>());
        storage.deleteVan(storage.getVansStorage().get(0));
    }

    @Test
    @DisplayName("Test getCargoPrice Van")
    public void testGetCargoPrice() {
        Storage storage = Storage.getInstance();
        storage.addVan("test", 1);
        Assertions.assertEquals(storage.getVansStorage().get(0).getCargoPrice(), 0);
        storage.deleteVan(storage.getVansStorage().get(0));
    }

    @Test
    @DisplayName("Test getCargoVolume Van")
    public void testGetCargoVolume() {
        Storage storage = Storage.getInstance();
        storage.addVan("test", 1);
        Assertions.assertEquals(storage.getVansStorage().get(0).getCargoVolume(), 0);
        storage.deleteVan(storage.getVansStorage().get(0));
    }

    @ParameterizedTest
    @DisplayName("Test getVanVolume Van")
    @ValueSource(ints = { 10, 25, 50, 100, 155 })
    public void testGetVanVolume(int input) {
        Storage storage = Storage.getInstance();
        storage.addVan("test", input);
        Assertions.assertEquals(storage.getVansStorage().get(0).getVanVolume(), input);
        storage.deleteVan(storage.getVansStorage().get(0));
    }
}

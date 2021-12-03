package controllerTests;

import com.pp.controller.Storage;
import com.pp.controller.Van;
import com.pp.model.BeanCoffee;
import com.pp.model.Coffee;
import com.pp.view.InputScanner;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class StorageTests {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent = new ByteArrayInputStream("0\n20\n1\n1\n0\n20\n1\n1\n1\n1\n1".getBytes());

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
        Assertions.assertNotNull(Storage.getInstance());
        Assertions.assertNotNull(Storage.getInstance());
    }

    @Test
    @DisplayName("Test chooseCoffee Storage")
    public void testChooseCoffee() {
        Storage storage = Storage.getInstance();
        Coffee testCoffee = BeanCoffee.buyCoffee();
        testCoffee.setSort(1); testCoffee.setFullPrice(450);

        storage.chooseCoffee();
        Assertions.assertEquals("Неправильний ввід" + System.lineSeparator() + "Неправильний ввід" +
                System.lineSeparator(), outContent.toString());
        outContent.reset();
        storage.addCoffee(testCoffee);
        Coffee result = storage.chooseCoffee();
        Assertions.assertEquals(storage.getCoffeeStorage().get(0), result);

        storage.deleteCoffee(storage.getCoffeeStorage().get(0));
    }

    @Test
    @DisplayName("Test printCoffee Storage")
    public void testPrintCoffee() {
        Storage storage = Storage.getInstance();
        Coffee testCoffee = BeanCoffee.buyCoffee();
        testCoffee.setSort(1); testCoffee.setFullPrice(450);

        storage.printCoffee();
        Assertions.assertEquals("Список пустий" + System.lineSeparator(), outContent.toString());
        outContent.reset();
        storage.addCoffee(testCoffee);
        storage.printCoffee();
        Assertions.assertEquals("1 " + testCoffee.toString() + System.lineSeparator(), outContent.toString());
        outContent.reset();

        storage.deleteCoffee(storage.getCoffeeStorage().get(0));
    }

    @Test
    @DisplayName("Test setCoffeeStorage Storage")
    public void testSetCoffeeStorage() {
        Storage storage = Storage.getInstance();
        Coffee testCoffee = BeanCoffee.buyCoffee();
        testCoffee.setSort(1); testCoffee.setFullPrice(450);
        storage.addCoffee(testCoffee);
        ArrayList<Coffee> testList = new ArrayList<Coffee>();
        testList.add(testCoffee);
        storage.deleteCoffee(storage.getCoffeeStorage().get(0));
        storage.setCoffeeStorage(testList);
        Assertions.assertEquals(testList, storage.getCoffeeStorage());
    }

    @Test
    @DisplayName("Test deleteCoffee Storage")
    public void testDeleteCoffee() {
        Storage storage = Storage.getInstance();
        Coffee testCoffee = BeanCoffee.buyCoffee();
        testCoffee.setSort(1); testCoffee.setFullPrice(450);
        Assertions.assertTrue(storage.deleteCoffee(storage.getCoffeeStorage().get(0)));
        Assertions.assertFalse(storage.deleteCoffee(testCoffee));
    }

    @Test
    @DisplayName("Test addCoffee Storage")
    public void testAddCoffee() {
        Storage storage = Storage.getInstance();
        Coffee testCoffee = BeanCoffee.buyCoffee();
        testCoffee.setSort(1); testCoffee.setFullPrice(450);
        storage.addCoffee(testCoffee);
        Assertions.assertEquals(testCoffee.getSort(), storage.getCoffeeStorage().get(0).getSort());
        Assertions.assertEquals(testCoffee.getFullPrice(), storage.getCoffeeStorage().get(0).getFullPrice());
        storage.deleteCoffee(storage.getCoffeeStorage().get(0));
    }

    @Test
    @DisplayName("Test getCoffeeStorage Storage")
    public void testGetCoffeeStorage() {
        Storage storage = Storage.getInstance();
        Assertions.assertEquals(new ArrayList<Coffee>(), storage.getCoffeeStorage());
    }

    @Test
    @DisplayName("Test chooseVans Storage")
    public void testChooseVans() {
        Storage storage = Storage.getInstance();
        Assertions.assertNull(storage.chooseVan());
        Assertions.assertEquals("Список пустий" + System.lineSeparator(), outContent.toString());
        outContent.reset();
        storage.addVan("test", 50);
        storage.addVan("test", 50);
        Van testVan = storage.getVansStorage().get(0);
        Van result = storage.chooseVan();
        Assertions.assertEquals(testVan, result);

        storage.deleteVan(storage.getVansStorage().get(0));
    }

    @Test
    @DisplayName("Test printVans Storage")
    public void testPrintVans() {
        Storage storage = Storage.getInstance();
        storage.deleteVan(storage.getVansStorage().get(0));
        storage.printVans();
        Assertions.assertEquals("Список пустий" + System.lineSeparator(), outContent.toString());
        outContent.reset();
        storage.addVan("test", 50);
        Van testVan = storage.getVansStorage().get(0);
        storage.printVans();
        Assertions.assertEquals("1 " + testVan.toString() + System.lineSeparator(), outContent.toString());
        outContent.reset();

        storage.deleteVan(storage.getVansStorage().get(0));
    }

    @Test
    @DisplayName("Test setVansStorage Storage")
    public void testSetVansStorage() {
        Storage storage = Storage.getInstance();
        storage.addVan("test", 1);
        ArrayList<Van> testList = storage.getVansStorage();
        storage.deleteVan(storage.getVansStorage().get(0));
        storage.setVansStorage(testList);
        Assertions.assertEquals(testList, storage.getVansStorage());
    }

    @Test
    @DisplayName("Test deleteVan Storage")
    public void testDeleteVan() {
        Storage storage = Storage.getInstance();
        storage.addVan("test", 1);
        Van testVan = storage.getVansStorage().get(0);
        Assertions.assertTrue(storage.deleteVan(storage.getVansStorage().get(0)));
        Assertions.assertFalse(storage.deleteVan(testVan));
    }

    @Test
    @DisplayName("Test addVan Storage")
    public void testAddVan() {
        Storage storage = Storage.getInstance();
        storage.addVan("test", 10);
        Assertions.assertEquals("test", storage.getVansStorage().get(0).getName());
        Assertions.assertEquals(10, storage.getVansStorage().get(0).getVanVolume());
        storage.deleteVan(storage.getVansStorage().get(0));
    }

    @Test
    @DisplayName("Test getVansStorage Storage")
    public void testGetVansStorage() {
        Storage storage = Storage.getInstance();
        Assertions.assertEquals(new ArrayList<Van>(), storage.getVansStorage());
    }
}


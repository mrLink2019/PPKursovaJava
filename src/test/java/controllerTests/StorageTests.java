package controllerTests;

import com.pp.controller.Storage;
import com.pp.controller.Van;
import com.pp.model.BeanCoffee;
import com.pp.model.Coffee;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class StorageTests {

    @Test
    @DisplayName("Test getInstance InputScanner")
    public void testGetInstance() {
        Assertions.assertNotNull(Storage.getInstance());
        Assertions.assertNotNull(Storage.getInstance());
    }

    @Test
    @DisplayName("Test setCoffeeStorage Storage")
    public void testSetCoffeeStorage() {
        Storage storage = Storage.getInstance();
        Coffee testCoffee = BeanCoffee.buyCoffee();
        testCoffee.setSort(1); testCoffee.setFullPrice(450);
        storage.addCoffee(testCoffee);
        ArrayList<Coffee> testList = new ArrayList<>();
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


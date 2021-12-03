package modelTests;

import com.pp.model.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class InstantBagCoffeeTests {
    @Test
    @DisplayName("Test returning InstantBagCoffee object")
    public void testBuyCoffee() {
        Assertions.assertNotNull(InstantBagCoffee.buyCoffee());
    }

    @Test
    @DisplayName("Test packing InstantBagCoffee")
    public void testPackCoffee() {
        Coffee instantBagCoffee = InstantBagCoffee.buyCoffee();
        instantBagCoffee.packCoffee();
        Assertions.assertEquals(instantBagCoffee.getFullVolume(), 0.1);
    }

    @Test
    @DisplayName("Test toString InstantBagCoffee")
    public void testToString() {
        Coffee instantBagCoffee = InstantBagCoffee.buyCoffee();
        instantBagCoffee.setSort(1);
        instantBagCoffee.setFullPrice(50);
        instantBagCoffee.setFullVolume(100);
        Assertions.assertEquals(instantBagCoffee.toString(), "Розчинна кава в пакетиках " + instantBagCoffee.getSort() +
                " сорту, ціна за 1л " + instantBagCoffee.calculateSortPrice(instantBagCoffee.getSort()) + ", загальна ціна " +
                instantBagCoffee.getFullPrice() + ", об'єм " + instantBagCoffee.getFullVolume() + "л");
    }

    @ParameterizedTest
    @DisplayName("Test calculating fullVolume InstantBagCoffee")
    @CsvSource(value = { "100, 1", "200, 2", "300, 3", "400, 4", "500, 5" })
    public void testCalculateFullVolume(int fullPrice, int sort) {
        Coffee instantBagCoffee = InstantBagCoffee.buyCoffee();
        instantBagCoffee.setFullPrice(fullPrice);
        instantBagCoffee.setSort(sort);
        instantBagCoffee.calculateFullVolume();
        Assertions.assertEquals(instantBagCoffee.getFullVolume(),
                (Math.round(((double) fullPrice / instantBagCoffee.calculateSortPrice(sort)) * 100.0) / 100.0));
    }

    @ParameterizedTest
    @DisplayName("Test calculating sortPrice InstantBagCoffee")
    @ValueSource(ints = { 1, 2, 3, 4, 5 })
    public void testCalculateSortPrice(int argument) {
        Assertions.assertEquals(InstantBagCoffee.buyCoffee().calculateSortPrice(argument),
                (InstantBagCoffee.buyCoffee().getPriceForL()/argument));
    }

    @Test
    @DisplayName("Test getting coffeeType InstantBagCoffee")
    public void testGetCoffeeType() {
        Assertions.assertEquals(InstantBagCoffee.buyCoffee().getCoffeeType(), "Розчинна кава в пакетиках");
    }

    @Test
    @DisplayName("Test getting priceForL InstantBagCoffee")
    public void testGetPriceForL() {
        Assertions.assertEquals(InstantBagCoffee.buyCoffee().getPriceForL(), 150);
    }

    @Test
    @DisplayName("Test getting sortsCount InstantBagCoffee")
    public void testGetSortsCount() {
        Assertions.assertEquals(InstantBagCoffee.buyCoffee().getSortsCount(), 5);
    }

    @ParameterizedTest
    @DisplayName("Test setting sort InstantBagCoffee")
    @ValueSource(ints = { 1, 2, 3, 4, 5 })
    public void testSetSort(int argument) {
        Coffee instantBagCoffee = InstantBagCoffee.buyCoffee();
        instantBagCoffee.setSort(argument);
        Assertions.assertEquals(instantBagCoffee.getSort(), argument);
        Assertions.assertFalse(instantBagCoffee.setSort(20));
    }

    @Test
    @DisplayName("Test getting sort InstantBagCoffee")
    public void testGetSort() {
        Assertions.assertEquals(InstantBagCoffee.buyCoffee().getFullPrice(), 0);
    }

    @ParameterizedTest
    @DisplayName("Test setting fullVolume InstantBagCoffee")
    @ValueSource(ints = { 10, 20, 50 })
    public void testSetFullVolume(int argument) {
        Coffee instantBagCoffee = InstantBagCoffee.buyCoffee();
        instantBagCoffee.setFullVolume(argument);
        Assertions.assertEquals(instantBagCoffee.getFullVolume(), argument);
    }

    @Test
    @DisplayName("Test getting fullVolume InstantBagCoffee")
    public void testGetFullVolume() {
        Assertions.assertEquals(InstantBagCoffee.buyCoffee().getFullVolume(), 0);
    }

    @ParameterizedTest
    @DisplayName("Test setting fullPrice InstantBagCoffee")
    @ValueSource(ints = { 10, 20, 50 })
    public void testSetFullPrice(int argument) {
        Coffee instantBagCoffee = InstantBagCoffee.buyCoffee();
        instantBagCoffee.setFullPrice(argument);
        Assertions.assertEquals(instantBagCoffee.getFullPrice(), argument);
    }

    @Test
    @DisplayName("Test getting fullPrice InstantBagCoffee")
    public void testGetFullPrice() {
        Assertions.assertEquals(InstantBagCoffee.buyCoffee().getFullPrice(), 0);
    }
}
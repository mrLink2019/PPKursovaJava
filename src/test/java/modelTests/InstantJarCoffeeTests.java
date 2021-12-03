package modelTests;

import com.pp.model.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class InstantJarCoffeeTests {
    @Test
    @DisplayName("Test returning InstantJarCoffee object")
    public void testBuyCoffee() {
        Assertions.assertNotNull(InstantJarCoffee.buyCoffee());
    }

    @Test
    @DisplayName("Test packing InstantJarCoffee")
    public void testPackCoffee() {
        Coffee instantJarCoffee = InstantJarCoffee.buyCoffee();
        instantJarCoffee.packCoffee();
        Assertions.assertEquals(instantJarCoffee.getFullVolume(), 0.5);
    }

    @Test
    @DisplayName("Test toString InstantJarCoffee")
    public void testToString() {
        Coffee instantJarCoffee = InstantJarCoffee.buyCoffee();
        instantJarCoffee.setSort(1);
        instantJarCoffee.setFullPrice(50);
        instantJarCoffee.setFullVolume(100);
        Assertions.assertEquals(instantJarCoffee.toString(), "Розчинна кава в банках " + instantJarCoffee.getSort() +
                " сорту, ціна за 1л " + instantJarCoffee.calculateSortPrice(instantJarCoffee.getSort()) + ", загальна ціна " +
                instantJarCoffee.getFullPrice() + ", об'єм " + instantJarCoffee.getFullVolume() + "л");
    }

    @ParameterizedTest
    @DisplayName("Test calculating fullVolume InstantJarCoffee")
    @CsvSource(value = { "100, 1", "200, 2" })
    public void testCalculateFullVolume(int fullPrice, int sort) {
        Coffee instantJarCoffee = InstantJarCoffee.buyCoffee();
        instantJarCoffee.setFullPrice(fullPrice);
        instantJarCoffee.setSort(sort);
        instantJarCoffee.calculateFullVolume();
        Assertions.assertEquals(instantJarCoffee.getFullVolume(),
                (Math.round(((double) fullPrice / instantJarCoffee.calculateSortPrice(sort)) * 100.0) / 100.0));
    }

    @ParameterizedTest
    @DisplayName("Test calculating sortPrice InstantJarCoffee")
    @ValueSource(ints = { 1, 2 })
    public void testCalculateSortPrice(int argument) {
        Assertions.assertEquals(InstantJarCoffee.buyCoffee().calculateSortPrice(argument),
                (InstantJarCoffee.buyCoffee().getPriceForL()/argument));
    }

    @Test
    @DisplayName("Test getting coffeeType InstantJarCoffee")
    public void testGetCoffeeType() {
        Assertions.assertEquals(InstantJarCoffee.buyCoffee().getCoffeeType(), "Розчинна кава в банках");
    }

    @Test
    @DisplayName("Test getting priceForL InstantJarCoffee")
    public void testGetPriceForL() {
        Assertions.assertEquals(InstantJarCoffee.buyCoffee().getPriceForL(), 200);
    }

    @Test
    @DisplayName("Test getting sortsCount InstantJarCoffee")
    public void testGetSortsCount() {
        Assertions.assertEquals(InstantJarCoffee.buyCoffee().getSortsCount(), 2);
    }

    @ParameterizedTest
    @DisplayName("Test setting sort InstantJarCoffee")
    @ValueSource(ints = { 1, 2 })
    public void testSetSort(int argument) {
        Coffee instantJarCoffee = InstantJarCoffee.buyCoffee();
        instantJarCoffee.setSort(argument);
        Assertions.assertEquals(instantJarCoffee.getSort(), argument);
        Assertions.assertFalse(instantJarCoffee.setSort(20));
    }

    @Test
    @DisplayName("Test getting sort InstantJarCoffee")
    public void testGetSort() {
        Assertions.assertEquals(InstantJarCoffee.buyCoffee().getFullPrice(), 0);
    }

    @ParameterizedTest
    @DisplayName("Test setting fullVolume InstantJarCoffee")
    @ValueSource(ints = { 10, 20, 50 })
    public void testSetFullVolume(int argument) {
        Coffee instantJarCoffee = InstantJarCoffee.buyCoffee();
        instantJarCoffee.setFullVolume(argument);
        Assertions.assertEquals(instantJarCoffee.getFullVolume(), argument);
    }

    @Test
    @DisplayName("Test getting fullVolume InstantJarCoffee")
    public void testGetFullVolume() {
        Assertions.assertEquals(InstantJarCoffee.buyCoffee().getFullVolume(), 0);
    }

    @ParameterizedTest
    @DisplayName("Test setting fullPrice InstantJarCoffee")
    @ValueSource(ints = { 10, 20, 50 })
    public void testSetFullPrice(int argument) {
        Coffee instantJarCoffee = InstantJarCoffee.buyCoffee();
        instantJarCoffee.setFullPrice(argument);
        Assertions.assertEquals(instantJarCoffee.getFullPrice(), argument);
    }

    @Test
    @DisplayName("Test getting fullPrice InstantJarCoffee")
    public void testGetFullPrice() {
        Assertions.assertEquals(InstantJarCoffee.buyCoffee().getFullPrice(), 0);
    }
}
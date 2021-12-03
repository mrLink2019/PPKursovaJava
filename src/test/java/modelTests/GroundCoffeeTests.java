package modelTests;

import com.pp.model.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class GroundCoffeeTests {
    @Test
    @DisplayName("Test returning GroundCoffee object")
    public void testBuyCoffee() {
        Assertions.assertNotNull(GroundCoffee.buyCoffee());
    }

    @Test
    @DisplayName("Test packing GroundCoffee")
    public void testPackCoffee() {
        Coffee groundCoffee = GroundCoffee.buyCoffee();
        groundCoffee.packCoffee();
        Assertions.assertEquals(groundCoffee.getFullVolume(), 0.2);
    }

    @Test
    @DisplayName("Test toString GroundCoffee")
    public void testToString() {
        Coffee groundCoffee = GroundCoffee.buyCoffee();
        groundCoffee.setSort(1);
        groundCoffee.setFullPrice(50);
        groundCoffee.setFullVolume(100);
        Assertions.assertEquals(groundCoffee.toString(), "Мелена кава " + groundCoffee.getSort() +
                " сорту, ціна за 1л " + groundCoffee.calculateSortPrice(groundCoffee.getSort()) + ", загальна ціна " +
                groundCoffee.getFullPrice() + ", об'єм " + groundCoffee.getFullVolume() + "л");
    }

    @ParameterizedTest
    @DisplayName("Test calculating fullVolume GroundCoffee")
    @CsvSource(value = { "100, 1", "200, 2", "300, 3", "400, 4", "500, 5", "600, 6" })
    public void testCalculateFullVolume(int fullPrice, int sort) {
        Coffee groundCoffee = GroundCoffee.buyCoffee();
        groundCoffee.setFullPrice(fullPrice);
        groundCoffee.setSort(sort);
        groundCoffee.calculateFullVolume();
        Assertions.assertEquals(groundCoffee.getFullVolume(),
                (Math.round(((double) fullPrice / groundCoffee.calculateSortPrice(sort)) * 100.0) / 100.0));
    }

    @ParameterizedTest
    @DisplayName("Test calculating sortPrice GroundCoffee")
    @ValueSource(ints = { 1, 2, 3, 4, 5, 6 })
    public void testCalculateSortPrice(int argument) {
        Assertions.assertEquals(GroundCoffee.buyCoffee().calculateSortPrice(argument),
                (GroundCoffee.buyCoffee().getPriceForL()/argument));
    }

    @Test
    @DisplayName("Test getting coffeeType GroundCoffee")
    public void testGetCoffeeType() {
        Assertions.assertEquals(GroundCoffee.buyCoffee().getCoffeeType(), "Мелена кава");
    }

    @Test
    @DisplayName("Test getting priceForL GroundCoffee")
    public void testGetPriceForL() {
        Assertions.assertEquals(GroundCoffee.buyCoffee().getPriceForL(), 250);
    }

    @Test
    @DisplayName("Test getting sortsCount GroundCoffee")
    public void testGetSortsCount() {
        Assertions.assertEquals(GroundCoffee.buyCoffee().getSortsCount(), 6);
    }

    @ParameterizedTest
    @DisplayName("Test setting sort GroundCoffee")
    @ValueSource(ints = { 1, 2, 3, 4, 5, 6 })
    public void testSetSort(int argument) {
        Coffee groundCoffee = GroundCoffee.buyCoffee();
        groundCoffee.setSort(argument);
        Assertions.assertEquals(groundCoffee.getSort(), argument);
        Assertions.assertFalse(groundCoffee.setSort(20));
    }

    @Test
    @DisplayName("Test getting sort GroundCoffee")
    public void testGetSort() {
        Assertions.assertEquals(GroundCoffee.buyCoffee().getFullPrice(), 0);
    }

    @ParameterizedTest
    @DisplayName("Test setting fullVolume GroundCoffee")
    @ValueSource(ints = { 10, 20, 50 })
    public void testSetFullVolume(int argument) {
        Coffee groundCoffee = GroundCoffee.buyCoffee();
        groundCoffee.setFullVolume(argument);
        Assertions.assertEquals(groundCoffee.getFullVolume(), argument);
    }

    @Test
    @DisplayName("Test getting fullVolume GroundCoffee")
    public void testGetFullVolume() {
        Assertions.assertEquals(GroundCoffee.buyCoffee().getFullVolume(), 0);
    }

    @ParameterizedTest
    @DisplayName("Test setting fullPrice GroundCoffee")
    @ValueSource(ints = { 10, 20, 50 })
    public void testSetFullPrice(int argument) {
        Coffee groundCoffee = GroundCoffee.buyCoffee();
        groundCoffee.setFullPrice(argument);
        Assertions.assertEquals(groundCoffee.getFullPrice(), argument);
    }

    @Test
    @DisplayName("Test getting fullPrice GroundCoffee")
    public void testGetFullPrice() {
        Assertions.assertEquals(GroundCoffee.buyCoffee().getFullPrice(), 0);
    }
}
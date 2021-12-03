package modelTests;

import com.pp.model.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class BeanCoffeeTests {
    @Test
    @DisplayName("Test returning BeanCoffee object")
    public void testBuyCoffee() {
        Assertions.assertNotNull(BeanCoffee.buyCoffee());
    }

    @Test
    @DisplayName("Test packing BeanCoffee")
    public void testPackCoffee() {
        Coffee beanCoffee = BeanCoffee.buyCoffee();
        beanCoffee.packCoffee();
        Assertions.assertEquals(beanCoffee.getFullVolume(), 0.3);
    }

    @Test
    @DisplayName("Test toString BeanCoffee")
    public void testToString() {
        Coffee beanCoffee = BeanCoffee.buyCoffee();
        beanCoffee.setSort(1);
        beanCoffee.setFullPrice(50);
        beanCoffee.setFullVolume(100);
        Assertions.assertEquals(beanCoffee.toString(), "Зернова кава " + beanCoffee.getSort() +
                " сорту, ціна за 1л " + beanCoffee.calculateSortPrice(beanCoffee.getSort()) + ", загальна ціна " +
                beanCoffee.getFullPrice() + ", об'єм " + beanCoffee.getFullVolume() + "л");
    }

    @ParameterizedTest
    @DisplayName("Test calculating fullVolume BeanCoffee")
    @CsvSource(value = { "100, 1", "200, 2", "300, 3", "400, 4" })
    public void testCalculateFullVolume(int fullPrice, int sort) {
        Coffee beanCoffee = BeanCoffee.buyCoffee();
        beanCoffee.setFullPrice(fullPrice);
        beanCoffee.setSort(sort);
        beanCoffee.calculateFullVolume();
        Assertions.assertEquals(beanCoffee.getFullVolume(),
                (Math.round(((double) fullPrice / beanCoffee.calculateSortPrice(sort)) * 100.0) / 100.0));
    }

    @ParameterizedTest
    @DisplayName("Test calculating sortPrice BeanCoffee")
    @ValueSource(ints = { 1, 2, 3, 4 })
    public void testCalculateSortPrice(int argument) {
        Assertions.assertEquals(BeanCoffee.buyCoffee().calculateSortPrice(argument),
                (BeanCoffee.buyCoffee().getPriceForL()/argument));
    }

    @Test
    @DisplayName("Test getting coffeeType BeanCoffee")
    public void testGetCoffeeType() {
        Assertions.assertEquals(BeanCoffee.buyCoffee().getCoffeeType(), "Зернова кава");
    }

    @Test
    @DisplayName("Test getting priceForL BeanCoffee")
    public void testGetPriceForL() {
        Assertions.assertEquals(BeanCoffee.buyCoffee().getPriceForL(), 350);
    }

    @Test
    @DisplayName("Test getting sortsCount BeanCoffee")
    public void testGetSortsCount() {
        Assertions.assertEquals(BeanCoffee.buyCoffee().getSortsCount(), 4);
    }

    @ParameterizedTest
    @DisplayName("Test setting sort BeanCoffee")
    @ValueSource(ints = { 1, 2, 3, 4 })
    public void testSetSort(int argument) {
        Coffee beanCoffee = BeanCoffee.buyCoffee();
        beanCoffee.setSort(argument);
        Assertions.assertEquals(beanCoffee.getSort(), argument);
        Assertions.assertFalse(beanCoffee.setSort(20));
    }

    @Test
    @DisplayName("Test getting sort BeanCoffee")
    public void testGetSort() {
        Assertions.assertEquals(BeanCoffee.buyCoffee().getFullPrice(), 0);
    }

    @ParameterizedTest
    @DisplayName("Test setting fullVolume BeanCoffee")
    @ValueSource(ints = { 10, 20, 50 })
    public void testSetFullVolume(int argument) {
        Coffee beanCoffee = BeanCoffee.buyCoffee();
        beanCoffee.setFullVolume(argument);
        Assertions.assertEquals(beanCoffee.getFullVolume(), argument);
    }

    @Test
    @DisplayName("Test getting fullVolume BeanCoffee")
    public void testGetFullVolume() {
        Assertions.assertEquals(BeanCoffee.buyCoffee().getFullVolume(), 0);
    }

    @ParameterizedTest
    @DisplayName("Test setting fullPrice BeanCoffee")
    @ValueSource(ints = { 10, 20, 50 })
    public void testSetFullPrice(int argument) {
        Coffee beanCoffee = BeanCoffee.buyCoffee();
        beanCoffee.setFullPrice(argument);
        Assertions.assertEquals(beanCoffee.getFullPrice(), argument);
    }

    @Test
    @DisplayName("Test getting fullPrice BeanCoffee")
    public void testGetFullPrice() {
        Assertions.assertEquals(BeanCoffee.buyCoffee().getFullPrice(), 0);
    }
}

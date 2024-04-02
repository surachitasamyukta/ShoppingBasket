package org.example;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ShoppingBasketTest {
    double delta = 0.0001;

    @Test
    public void testCalculateTotalPrice_positive() {
        Map<String, Integer> basket = new HashMap<>();
        basket.put("Apple", 3);
        basket.put("Banana", 5);
        basket.put("Melons", 12);
        basket.put("Limes", 6);
        double expectedTotalPrice = (3 * 0.35 + 5 * 0.20 + 6 * 0.50 + 4 * 0.15) / 100;
        double actualTotalPrice = ShoppingBasket.calculateTotalPrice(basket);
        assertEquals(expectedTotalPrice, actualTotalPrice, delta);
    }

    @Test
    public void testCalculateTotalPrice_withOneItem() {
        Map<String, Integer> basket = new HashMap<>();
        basket.put("Limes", 9);
        double expectedTotalPrice = (6 * 0.15) / 100;
        double actualTotalPrice = ShoppingBasket.calculateTotalPrice(basket);
        assertEquals(expectedTotalPrice, actualTotalPrice, delta);
    }

    @Test
    public void testCalculateTotalPrice_withTwoItem() {
        Map<String, Integer> basket = new HashMap<>();
        basket.put("Limes", 13);
        basket.put("Melons", 9);
        double expectedTotalPrice = ((2 * 4 * 0.15) + 0.15 + (4 * 0.50) + 0.50) / 100;
        double actualTotalPrice = ShoppingBasket.calculateTotalPrice(basket);
        assertEquals(expectedTotalPrice, actualTotalPrice, delta);
    }

    @Test
    public void testCalculateTotalPrice_Negative() {
        Map<String, Integer> basket = new HashMap<>();
        basket.put("Orange", 13);
        basket.put("Melons", 9);
        double expectedTotalPrice = ((4 * 0.50) + 0.50) / 100;
        double actualTotalPrice = ShoppingBasket.calculateTotalPrice(basket);
        assertEquals(expectedTotalPrice, actualTotalPrice, delta);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateTotalPrice_NoItem() {
        Map<String, Integer> basket = new HashMap<>();
        ShoppingBasket.calculateTotalPrice(basket);
    }

}
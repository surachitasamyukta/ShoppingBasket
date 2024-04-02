package org.example;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class ShoppingBasket {

    private static final Map<String, Pair<Double, Offer>> itemsWithPrices = new HashMap<>();

    static {
        itemsWithPrices.put("Apple", Pair.of(0.35, Offer.NONE));
        itemsWithPrices.put("Banana", Pair.of(0.20, Offer.NONE));
        itemsWithPrices.put("Melons", Pair.of(0.50, Offer.BUY_ONE_GET_ONE));
        itemsWithPrices.put("Limes", Pair.of(0.15, Offer.THREE_FOR_THE_PRICE_OF_TWO));
    }


    static double calculateTotalPrice(Map<String, Integer> basket) {
        if (!basket.isEmpty()) {
            double total = 0.0;

            for (Map.Entry<String, Integer> entry : basket.entrySet()) {
                String itemInBasket = entry.getKey();
                if (itemsWithPrices.containsKey(itemInBasket)) {
                    int quantityInBasket = entry.getValue();
                    for (Map.Entry<String, Pair<Double, Offer>> itemEntry : itemsWithPrices.entrySet()) {
                        Pair<Double, Offer> itemValue = itemEntry.getValue();
                        if (itemEntry.getKey().equals((itemInBasket))) {
                            Double price = itemValue.getLeft();
                            total += calculatePriceWithOffer(quantityInBasket, price, itemValue.getRight());
                        }
                    }

                } else {
                    System.out.println(itemInBasket + " not present in System");
                }
            }
            return total;
        } else {
            throw new IllegalArgumentException("Empty basket ! Please add items");
        }
    }


    private static double calculatePriceWithOffer(int quantity, double price, Offer offer) {
        double totalPrice = 0.0;
        int remainder = 0;
        if (offer.equals(Offer.BUY_ONE_GET_ONE)) {
            int setsOfTwo = quantity / 2;
            remainder = quantity % 2;
            // Two items for the price of one
            totalPrice += setsOfTwo * (1 * price);
        } else if (offer.equals(Offer.THREE_FOR_THE_PRICE_OF_TWO)) {
            int setsOfThree = quantity / 3;
            remainder = quantity % 3;
            // three items for the price of two
            totalPrice += setsOfThree * (2 * price);
        } else {
            remainder = quantity;
        }
        // Remaining items without offer
        totalPrice += remainder * price;
        return totalPrice / 100;
    }

    enum Offer {
        BUY_ONE_GET_ONE,
        THREE_FOR_THE_PRICE_OF_TWO,
        NONE
    }
}
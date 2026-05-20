package javaBasic;

import java.util.ArrayList;
import java.util.List;

public class Task4 {

    public static void main(String[] args) {

        List<Double> prices = new ArrayList<>();
        prices.add(199.99);
        prices.add(2141.42);
        prices.add(123.23);
        prices.add(4324.24);
        prices.add(234.44);
        double budget = 5000;

        int count = 0;
        double sumItemsPrice = 0;
        for (double price : prices) {
            if ((budget - price) >= 0) {
                budget -= price;
                sumItemsPrice += price;
                count++;
            }
        }
        System.out.println("Куплено: " + count + " товара на сумму " + sumItemsPrice);
        System.out.println("Остаток бюджета: " + budget);
        System.out.println("Не куплено: " + (prices.size() - count) + " товара");
    }
}

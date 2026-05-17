package javaBasic;

import java.util.ArrayList;
import java.util.List;

public class Task3 {

    public static void main(String[] args) {

        List<Double> prices = new ArrayList<>();
        prices.add(199.99);
        prices.add(2141.42);
        prices.add(123.23);
        prices.add(4324.24);
        prices.add(234.44);
        double budget = 5000;
        double sum = 0;
        for(double price : prices) {
            sum += price;
        }
        System.out.println(sum);
        if (sum > budget) {
            System.out.println("Не хватает " + (sum - budget));
        } else {
            System.out.println("Все хватает, остаток " + (budget - sum));
        }
    }
}

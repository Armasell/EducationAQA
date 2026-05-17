package javaBasic;

import java.util.ArrayList;
import java.util.List;

public class Task1 {

    public static void main(String[] args) {

        Person person1 = new Person("Николай", "Басков", 25);
        person1.introduce();


        List<String> fruits = new ArrayList<>();
        fruits.add("Яблоко");
        fruits.add("Апельсин");
        fruits.add("Груша");
        fruits.add("Мандарин");
        fruits.add("Банан");
        for (int i = 1; i <= fruits.size(); i++) {
            System.out.println(i + ". " + fruits.get(i - 1));
        }


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

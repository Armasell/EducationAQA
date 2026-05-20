package javaBasic;

import java.util.ArrayList;
import java.util.List;

public class Task2 {

    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("Яблоко");
        fruits.add("Апельсин");
        fruits.add("Груша");
        fruits.add("Мандарин");
        fruits.add("Банан");
        for (int i = 0; i < fruits.size(); i++) {
            System.out.println((i + 1) + ". " + fruits.get(i));
        }
    }
}

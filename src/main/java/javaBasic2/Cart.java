package javaBasic2;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private static Map<Product, Integer> products = new HashMap<>();

    public static void addProduct(Product product, int quantity) {
        if (products.containsKey(product)) {
            int oldQuantity = products.get(product);
            products.put(product, oldQuantity + quantity);
        } else {
            products.put(product, quantity);
        }
    }

    public static double getTotalPrice() {
        double totalPrice = 0;
        for (Map.Entry<Product, Integer> entry: products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += product.getPrice() * quantity;
        }
        return totalPrice;
    }

    public static int getProductCount() {
        int productCount = 0;
        for (Map.Entry<Product, Integer> entry: products.entrySet()) {
            int quantity = entry.getValue();
            productCount += quantity;
        }
        return productCount;
    }
}


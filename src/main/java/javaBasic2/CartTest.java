package javaBasic2;

public class CartTest {

    public static void main(String[] args) {

        Product product1 = new Product("Яблоко", 30);
        Product product2 = new Product("Апельсин", 40);
        Product product3 = new Product("Груша", 50);

        Cart.addProduct(product1, 2);
        Cart.addProduct(product2, 1);
        Cart.addProduct(product3, 5);

        System.out.println("Общее количество товаров к корзине: " + Cart.getProductCount() + ". Общая цена: " + Cart.getTotalPrice());

        Cart.addProduct(product1, 3);

        System.out.println("Общее количество товаров к корзине: " + Cart.getProductCount() + ". Общая цена: " + Cart.getTotalPrice());
    }
}

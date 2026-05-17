package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class WsCartPage {

    private final SelenideElement productNameField = $("a.product-name");
    private final SelenideElement finalPrice = $("span.product-subtotal");
    private final SelenideElement itemQuantityField = $("input.qty-input");

    public WsCartPage verifyProductName(String productName) {
        productNameField.shouldHave(text(productName));
        return this;
    }

    public WsCartPage verifyFinalPrice(String itemPrice, String itemQuantity) {
        finalPrice.shouldHave(text(String.valueOf(
                Float.parseFloat(itemPrice) * Float.parseFloat(itemQuantity))));
        return this;
    }

    public String getItemQuantityInCart() {
        return itemQuantityField.getAttribute("value");
    }
}

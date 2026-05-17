package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsComputerPage {

    private final ElementsCollection processors = $$("dl dd ul li");
    private final SelenideElement itemQuantityInput = $("input.qty-input");
    private final SelenideElement addToCartButton = $("input.add-to-cart-button");
    private final SelenideElement computerNameField = $("[itemprop=name]");
    private final SelenideElement computerPriceField = $("[itemprop=price]");

    public String getComputerName() {
        return computerNameField.getText();
    }

    public String getComputerPrice() {
        return computerPriceField.getText();
    }

    public WsComputerPage processorSelection(int processorNumber) {
        processors.get(processorNumber - 1).$("input#product_attribute_72_5_18_52").click();
        return this;
    }

    public WsComputerPage selectQuantity(String quantity) {
        itemQuantityInput.setValue(quantity);
        return this;
    }

    public WsComputerPage addToCart() {
        addToCartButton.click();
        return this;
    }
}

package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsComputerPage {

    private final ElementsCollection processors = $$("dl dd ul li");
    private final SelenideElement itemQuantityInput = $("input.qty-input");
    private final SelenideElement addToCartButton = $("input.add-to-cart-button");
    private final SelenideElement computerNameField = $("[itemprop=name]");
    private final SelenideElement computerPriceField = $("[itemprop=price]");
    private final SelenideElement addToCartSuccessMessage = $("div.bar-notification.success");
    private final ElementsCollection headerLinks = $$("div.header-links ul li a");


    public String getComputerName() {
        return computerNameField.getText();
    }

    public Double getComputerPrice() {
        return Double.parseDouble(computerPriceField.getText());
    }

    @Step("Выбрать процессор")
    public WsComputerPage processorSelection(int processorNumber) {
        processors.get(processorNumber).$("[name=product_attribute_72_5_18]").click();
        return this;
    }

    @Step("Ввести количество")
    public WsComputerPage selectQuantity(String quantity) {
        itemQuantityInput.setValue(quantity);
        return this;
    }

    @Step("Добавить в корзину")
    public WsComputerPage addToCart() {
        addToCartButton.click();
        return this;
    }

    @Step("Проверить наличие сообщения о добавлении в корзину")
    public WsComputerPage verifyAddToCartSuccessMessage() {
        addToCartSuccessMessage.shouldBe(visible);
        return this;
    }

    @Step("Проверить количество товара в корзине в хедере")
    public WsComputerPage verifyQuantityInCartInHeaderLinks(String itemQuantity) {
        headerLinks.get(2).$("span.cart-qty").shouldHave(text(itemQuantity));
        return this;
    }

    @Step("Открыть корзину")
    public WsCartPage openCart() {
        headerLinks.get(2).$("span.cart-label").click();
        return new WsCartPage();
    }
}

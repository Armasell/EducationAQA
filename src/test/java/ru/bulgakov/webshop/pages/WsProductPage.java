package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class WsProductPage {

    private final ElementsCollection computers = $$("div.product-grid div");

    public WsProductPage openComputer(int computerNumber) {
        computers.get(computerNumber - 1).click();
        return this;
    }
}

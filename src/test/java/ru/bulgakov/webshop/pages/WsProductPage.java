package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$;

public class WsProductPage {

    private final ElementsCollection computers = $$("div.product-grid div");

    @Step("Открыть страницу компьютера")
    public WsProductPage openComputer(int computerNumber) {
        computers.get(computerNumber - 1).click();
        return this;
    }
}

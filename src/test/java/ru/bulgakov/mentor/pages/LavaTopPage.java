package ru.bulgakov.mentor.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LavaTopPage {

    private final SelenideElement priceField = $("span.styles-module-scss-module__t92_WG__price");

    @Step("Проверить цену")
    public LavaTopPage verifyPrice(String price) {
        priceField.shouldHave(text(price));
        return this;
    }
}

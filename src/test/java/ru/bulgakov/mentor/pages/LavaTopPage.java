package ru.bulgakov.mentor.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class LavaTopPage {

    private final SelenideElement priceField = $x("/html/body/div[2]/div/div/main/div/div/div[2]/aside/div[1]/div/div/span/span/h3");

    public LavaTopPage verifyPrice(String price) {
        priceField.shouldHave(text(price));
        return this;
    }
}

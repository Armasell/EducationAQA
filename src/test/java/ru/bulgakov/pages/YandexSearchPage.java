package ru.bulgakov.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class YandexSearchPage {

    private final SelenideElement searchInput = $("#text");
    private final SelenideElement submitButton = $("[type=submit]");
    private final SelenideElement checkboxCaptcha = $("#js-button");

    public YandexSearchPage openYandexSearch() {
        open("https://ya.ru/");
        return this;
    }

    public YandexSearchPage search(String query) {
        searchInput.setValue(query);
        return this;
    }

    public YandexSearchPage clickCheckboxCaptcha() {
        checkboxCaptcha.click();
        return this;
    }

    public YandexSearchPage submit() {
        submitButton.click();
        return this;
    }
}

package ru.bulgakov.mentor.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class YandexSearchPage {

    private final SelenideElement searchInput = $("#text");
    private final SelenideElement submitButton = $("[type=submit]");
    private final SelenideElement checkboxCaptcha = $("#js-button");

    @Step("Открыть поисковик яндекс")
    public YandexSearchPage openYandexSearch() {
        open("https://ya.ru/");
        return this;
    }

    @Step("Ввести данные в поисковую строку")
    public YandexSearchPage search(String query) {
        searchInput.setValue(query);
        return this;
    }

    @Step("Прокликать капчу")
    public YandexSearchPage clickCheckboxCaptcha() {
        checkboxCaptcha.click();
        return this;
    }

    @Step("Нажать кнопку поиска")
    public YandexSearchPage submit() {
        submitButton.click();
        return this;
    }
}

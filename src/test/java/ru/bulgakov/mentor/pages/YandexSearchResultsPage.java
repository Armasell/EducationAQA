package ru.bulgakov.mentor.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class YandexSearchResultsPage {

    private final SelenideElement closeWindow = $(".DistributionButtonClose");

    @Step("Убрать окно выбора браузера по умолчанию")
    public YandexSearchResultsPage closeDefaultBrowserSelectWindow() {
        closeWindow.click();
        return this;
    }

    @Step("Открыть страницу сайта")
    public YandexSearchResultsPage openLink(String websiteName) {
        $(byText(websiteName)).click();
        return this;
    }
}

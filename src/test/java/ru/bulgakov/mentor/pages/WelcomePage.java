package ru.bulgakov.mentor.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class WelcomePage {

//    Элементы страницы ivanbulgakov.ru
    private final SelenideElement price = $$(".t-menu__list li").get(4);
    private final SelenideElement wantGoQa = $x("/html/body/div[1]/div[42]/div/div/div[32]/div/a");
    private final SelenideElement runPay = $(byText("Бегу оплачивать"));

//    Элементы страницы demoqa.com
    private final SelenideElement elementsButton = $("[href='/elements']");


//    Методы страницы ivanbulgakov.ru
    public WelcomePage clickPrice() {
        price.click();
        return this;
    }

    public WelcomePage clickWantGoQa() {
        wantGoQa.click();
        return this;
    }

    public WelcomePage clickRunPay() {
        runPay.click();
        return this;
    }

//    Методы страницы demoqa.com
    public WelcomePage clickElements() {
        elementsButton.click();
        return this;
    }
}

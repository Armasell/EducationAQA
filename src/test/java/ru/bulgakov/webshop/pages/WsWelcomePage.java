package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsWelcomePage {

    private final SelenideElement registerButton = $("a.ico-register");
    private final SelenideElement loginButton = $("a.ico-login");
    private final ElementsCollection topMenu = $$("ul.top-menu li a");
    private final SelenideElement desktopComputersButton = $(byText("Desktops"));
    private final ElementsCollection headerLinks = $$("div.header-links ul li a");

    public ElementsCollection getHeaderLinks() {
        return headerLinks;
    }

    @Step("Открыть окно регистрации")
    public WsWelcomePage openRegistration() {
        registerButton.click();
        return this;
    }

    @Step("Открыть страницу логина")
    public WsLoginPage openLogin() {
        loginButton.click();
        return new WsLoginPage();
    }

    @Step("Навестись на раздел с компьютерами")
    public WsWelcomePage hoverOnComputers() {
        topMenu.get(1).hover();
        return this;
    }

    @Step("Открыть страницу с desktop компьютерами")
    public WsProductPage openDesktopComputers() {
        desktopComputersButton.click();
        return new WsProductPage();
    }
}

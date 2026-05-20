package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

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

    public WsWelcomePage openRegistration() {
        registerButton.click();
        return this;
    }

    public WsLoginPage openLogin() {
        loginButton.click();
        return new WsLoginPage();
    }

    public WsWelcomePage hoverOnComputers() {
        topMenu.get(1).hover();
        return this;
    }

    public WsProductPage openDesktopComputers() {
        desktopComputersButton.click();
        return new WsProductPage();
    }
}

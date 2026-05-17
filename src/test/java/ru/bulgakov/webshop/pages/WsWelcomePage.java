package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class WsWelcomePage {

    private final SelenideElement registerButton = $("a.ico-register");
    private final SelenideElement loginButton = $("a.ico-login");

    public WsWelcomePage openRegistration() {
        registerButton.click();
        return this;
    }

    public WsWelcomePage openLogin() {
        loginButton.click();
        return this;
    }
}

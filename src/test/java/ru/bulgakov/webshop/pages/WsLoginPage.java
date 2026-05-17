package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsLoginPage {

    private final SelenideElement pageTitle = $("div.page-title");
    private final SelenideElement inputEmail = $("input#Email");
    private final SelenideElement inputPassword = $("input#Password");
    private final SelenideElement loginButton = $("input.login-button");

    public WsLoginPage verifyLoginOpened() {
        pageTitle.shouldHave(text("Welcome, Please Sign In!"));
        return this;
    }

    public WsLoginPage enterEmail(String email) {
        inputEmail.setValue(email);
        return this;
    }

    public WsLoginPage enterPassword(String password) {
        inputPassword.setValue(password);
        return this;
    }

    public WsLoginPage submitLogin() {
        loginButton.click();
        return this;
    }

    public WsLoginPage checkLoginCompleted(String shownEmail) {
        WsWelcomePage wsWelcomePage = new WsWelcomePage();
        wsWelcomePage.getHeaderLinks()
                .get(0).shouldHave(text(shownEmail));
        return this;
    }
}

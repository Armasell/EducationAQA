package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsLoginPage {

    private final SelenideElement pageTitle = $("div.page-title");
    private final SelenideElement inputEmail = $("input#Email");
    private final SelenideElement inputPassword = $("input#Password");
    private final SelenideElement loginButton = $("input.login-button");
    private final SelenideElement emailValidationErrorMessage = $("span.field-validation-error");

    @Step("Проверить, что открыта страница с авторизацией")
    public WsLoginPage verifyLoginOpened() {
        pageTitle.shouldHave(text("Welcome, Please Sign In!"));
        return this;
    }

    @Step("Проверить, что появилось сообщение с ошибкой валидации почты")
    public WsLoginPage verifyEmailValidationErrorAppear() {
        emailValidationErrorMessage.shouldBe(visible);
        return this;
    }

    @Step("Ввести email: {email}")
    public WsLoginPage enterEmail(String email) {
        inputEmail.setValue(email);
        return this;
    }

    @Step("Ввести пароль: {password}")
    public WsLoginPage enterPassword(String password) {
        inputPassword.setValue(password);
        return this;
    }

    @Step("Подтвердить авторизацию")
    public WsLoginPage submitLogin() {
        loginButton.click();
        return this;
    }


    @Step("Проверить, что логин прошел успешно")
    public WsLoginPage checkLoginCompleted(String shownEmail) {
        WsWelcomePage wsWelcomePage = new WsWelcomePage();
        wsWelcomePage.getHeaderLinks()
                .get(0).shouldHave(text(shownEmail));
        return this;
    }
}

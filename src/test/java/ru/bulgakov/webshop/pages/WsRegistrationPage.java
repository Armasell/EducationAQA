package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsRegistrationPage {

    private final SelenideElement pageTitle = $("div.page-title");
    private final SelenideElement maleGenderButton = $("input#gender-male");
    private final SelenideElement inputFirstName = $("input#FirstName");
    private final SelenideElement inputLastName = $("input#LastName");
    private final SelenideElement inputEmail = $("input#Email");
    private final SelenideElement inputPassword = $("input#Password");
    private final SelenideElement inputConfirmPassword = $("input#ConfirmPassword");
    private final SelenideElement registerButton = $("input#register-button");
    private final SelenideElement registrationCompletedMessage = $("div.result");
    private final ElementsCollection headerLinks = $$("div.header-links ul li a");

    public WsRegistrationPage register(String firstName, String lastName, String email,
                                       String password) {
                selectMaleGender()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterEmail(email)
                .enterPassword(password)
                .enterConfirmPassword(password)
                .submitRegistration()
                .checkRegistrationCompleted();
        return this;
    }

    public WsRegistrationPage verifyRegistrationOpened() {
        pageTitle.shouldHave(text("Register"));
        return this;
    }

    public WsRegistrationPage selectMaleGender() {
        maleGenderButton.click();
        return this;
    }

    public WsRegistrationPage enterFirstName(String firstName) {
        inputFirstName.setValue(firstName);
        return this;
    }

    public WsRegistrationPage enterLastName(String lastName) {
        inputLastName.setValue(lastName);
        return this;
    }

    public WsRegistrationPage enterEmail(String email) {
        inputEmail.setValue(email);
        return this;
    }

    public WsRegistrationPage enterPassword(String password) {
        inputPassword.setValue(password);
        return this;
    }

    public WsRegistrationPage enterConfirmPassword(String confirmPassword) {
        inputConfirmPassword.setValue(confirmPassword);
        return this;
    }

    public WsRegistrationPage submitRegistration() {
        registerButton.click();
        return this;
    }

    public WsRegistrationPage checkRegistrationCompleted() {
        registrationCompletedMessage.shouldHave(text("Your registration completed"));
        return this;
    }

    public WsRegistrationPage checkEmailIsShown(String shownEmail) {
        headerLinks.get(0).shouldHave(text(shownEmail));
        return this;
    }
}

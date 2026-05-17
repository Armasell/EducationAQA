package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsWelcomePage {

    private final SelenideElement registerButton = $("a.ico-register");
    private final SelenideElement loginButton = $("a.ico-login");
    private final ElementsCollection topMenu = $$("ul.top-menu li a");
    private final SelenideElement desktopComputersButton = $(byText("Desktops"));
    private final SelenideElement addToCartSuccessMessage = $("div.bar-notification.success");
    private final ElementsCollection headerLinks = $$("div.header-links ul li a");

    public ElementsCollection getHeaderLinks() {
        return headerLinks;
    }

    public WsWelcomePage openRegistration() {
        registerButton.click();
        return this;
    }

    public WsWelcomePage openLogin() {
        loginButton.click();
        return this;
    }

    public WsWelcomePage hoverOnComputers() {
        topMenu.get(1).hover();
        return this;
    }

    public WsWelcomePage openDesktopComputers() {
        desktopComputersButton.click();
        return this;
    }

    public WsWelcomePage verifyAddToCartSuccessMessage() {
        addToCartSuccessMessage.shouldBe(visible);
        return this;
    }

    public WsWelcomePage verifyQuantityInCartInHeaderLinks(String itemQuantity) {
        headerLinks.get(2).$("span.cart-qty").shouldHave(text(itemQuantity));
        return this;
    }

    public WsWelcomePage openCart() {
        headerLinks.get(2).$("span.cart-label").click();
        return this;
    }
}

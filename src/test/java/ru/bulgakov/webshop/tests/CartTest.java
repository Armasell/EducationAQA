package ru.bulgakov.webshop.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.bulgakov.webshop.pages.WsCartPage;
import ru.bulgakov.webshop.pages.WsComputerPage;
import ru.bulgakov.webshop.pages.WsProductPage;
import ru.bulgakov.webshop.pages.WsWelcomePage;
import ru.bulgakov.webshop.steps.AuthSteps;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_URL;

public class CartTest {
    private final AuthSteps authSteps = new AuthSteps();

    @BeforeEach
    void beforeEach() {
        authSteps.registerNewUser();
    }

    @Test
    void addItemToCartTest() {
        WsWelcomePage wsWelcomePage = new WsWelcomePage();
        WsProductPage wsProductPage = new WsProductPage();
        WsComputerPage wsComputerPage = new WsComputerPage();
        WsCartPage wsCartPage = new WsCartPage();
        String itemQuantity = "2";

        open(WEB_SHOP_URL);
        wsWelcomePage
                .hoverOnComputers()
                .openDesktopComputers();

        wsProductPage
                .openComputer(1);

        String computerName = wsComputerPage.getComputerName();
        String computerPrice = wsComputerPage.getComputerPrice();
        wsComputerPage
                .processorSelection(1)
                .selectQuantity(itemQuantity)
                .addToCart();

        wsWelcomePage
                .verifyAddToCartSuccessMessage()
                .verifyQuantityInCartInHeaderLinks(itemQuantity)
                .openCart();

        wsCartPage
                .verifyProductName(computerName)
                .verifyFinalPrice(computerPrice, itemQuantity);

        assertEquals(itemQuantity, wsCartPage.getItemQuantityInCart());
    }
}

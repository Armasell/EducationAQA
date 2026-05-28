package ru.bulgakov.webshop.tests;

import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.*;
import ru.bulgakov.webshop.TestBase;
import ru.bulgakov.webshop.pages.WsCartPage;
import ru.bulgakov.webshop.pages.WsComputerPage;
import ru.bulgakov.webshop.pages.WsWelcomePage;
import ru.bulgakov.webshop.steps.AuthSteps;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_URL;

public class CartTest extends TestBase {
    private final AuthSteps authSteps = new AuthSteps();

    @BeforeEach
    void beforeEach() {
        authSteps.registerNewUser();
    }

    @Test
    @Tags({@Tag("UI"), @Tag("positive")})
    @Severity(CRITICAL)
    @Owner("Kirill")
    @Link(name = "TASK-113")
    @DisplayName("Добавление компьютера в корзину")
    void addItemToCartTest() {
        WsComputerPage wsComputerPage = new WsComputerPage();
        WsCartPage wsCartPage = new WsCartPage();

        open(WEB_SHOP_URL, WsWelcomePage.class)
                .hoverOnComputers()
                .openDesktopComputers()
                .openComputer(1);

        int processorNumber = 0;
        String computerName = wsComputerPage.getComputerName();
        String computerPrice = String.valueOf(wsComputerPage.getComputerPrice() + getProcessorSurcharge(processorNumber));
        String itemQuantity = "2";
        wsComputerPage
                .processorSelection(processorNumber)
                .selectQuantity(itemQuantity)
                .addToCart()
                .verifyAddToCartSuccessMessage()
                .verifyQuantityInCartInHeaderLinks(itemQuantity)
                .openCart()
                .verifyProductName(computerName);

        wsCartPage.getFinalPrice().shouldHave(text(String.valueOf(
                Float.parseFloat(computerPrice) * Float.parseFloat(itemQuantity))));
        assertEquals(itemQuantity, wsCartPage.getItemQuantityInCart());
    }

    private double getProcessorSurcharge(int processorIndex) {
        return switch (processorIndex) {
            case 0 -> 0;
            case 1 -> 15;
            case 2 -> 100;
            default -> throw new IllegalArgumentException("Unknown processor index: " + processorIndex);
        };
    }
}

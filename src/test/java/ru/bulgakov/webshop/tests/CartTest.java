package ru.bulgakov.webshop.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.bulgakov.webshop.steps.AuthSteps;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
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
        Configuration.holdBrowserOpen = true;
        open(WEB_SHOP_URL);
        $$("ul.top-menu li a").get(1).hover();
        $(byText("Desktops")).click();
        $$("div.product-grid div").get(0).click();

        String itemname = $("[itemprop=name]").getText();
        String itemPrice = $("[itemprop=price]").getText();
        String itemQuantity = "2";

        $$("dl dd ul li").get(0).$("input#product_attribute_72_5_18_52").click();
        $("input.qty-input").setValue(itemQuantity);
        $("input.add-to-cart-button").click();
        $("div.bar-notification.success").shouldBe(visible);
        $("span.cart-qty").shouldHave(text(itemQuantity));
        $("a.ico-cart").click();

        $("a.product-name").shouldHave(text(itemname));
        String itemQuantityInCart = $("input.qty-input").getAttribute("value");
        assertEquals(itemQuantity, itemQuantityInCart);
        $("span.product-subtotal").shouldHave(text(String.valueOf(
                Float.parseFloat(itemPrice) * Float.parseFloat(itemQuantity))));
    }
}

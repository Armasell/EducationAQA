package ru.bulgakov.webshop.tests;

import io.qameta.allure.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.bulgakov.webshop.TestBase;
import ru.bulgakov.webshop.pages.WsRegistrationPage;
import ru.bulgakov.webshop.pages.WsWelcomePage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_URL;

@Epic("Авторизация")
@Feature("Регистрация")
@Story("Регистрация нового пользователя")
public class RegistrationTest extends TestBase {
    private static final Faker faker = new Faker();

    @Test
    @Owner("Kirill")
    @Severity(CRITICAL)
    @Link("TASK-120")
    @DisplayName("Успешная регистрация нового пользователя")
    @Description("Создаем нового пользователя со случайными данными через интерфейс")
    void registrationTest() {
        String password = faker.harryPotter().character() + faker.number().positive();
        String email = faker.internet().emailAddress();

        WsWelcomePage wsWelcomePage = new WsWelcomePage();
        WsRegistrationPage wsRegistrationPage = new WsRegistrationPage();

        open(WEB_SHOP_URL);
        wsWelcomePage
                .openRegistration();

        wsRegistrationPage
                .verifyRegistrationOpened()
                .selectMaleGender()
                .enterFirstName(faker.name().firstName())
                .enterLastName(faker.name().lastName())
                .enterEmail(email)
                .enterPassword(password)
                .enterConfirmPassword(password)
                .submitRegistration()
                .checkRegistrationCompleted()
                .checkEmailIsShown(email);
    }
}

package ru.bulgakov.webshop.tests;

import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.bulgakov.webshop.TestBase;
import ru.bulgakov.webshop.pages.WsLoginPage;
import ru.bulgakov.webshop.pages.WsRegistrationPage;
import ru.bulgakov.webshop.pages.WsWelcomePage;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static ru.bulgakov.webshop.config.Config.*;

public class LoginTest extends TestBase {
    private static final Faker faker = new Faker();
    private String email;
    private String password;

    @Nested
    public class PositiveTests {
        @BeforeEach
        void beforeEach() {
            password = faker.harryPotter().character() + faker.number().positive();
            email = faker.internet().emailAddress();

            open(WEB_SHOP_REGISTRATION_URL, WsRegistrationPage.class)
                    .register(
                            faker.name().firstName(),
                            faker.name().lastName(),
                            email,
                            password)
                    .checkEmailIsShown(email);

            clearBrowserCookies();
            clearBrowserLocalStorage();
        }

        @Test
        @Tags({@Tag("UI"), @Tag("positive")})
        @Severity(CRITICAL)
        @Owner("Kirill")
        @Link(name = "TASK-114")
        @DisplayName("Успешная авторизация")
        void successLoginTest1() {
            WsWelcomePage wsWelcomePage = new WsWelcomePage();

            open(WEB_SHOP_URL);
            wsWelcomePage
                    .openLogin()
                    .verifyLoginOpened()
                    .enterEmail(email)
                    .enterPassword(password)
                    .submitLogin()
                    .checkLoginCompleted(email);
        }
    }

    @ParameterizedTest(name = "Авторизация с невалидным email: {0}")
    @Tags({@Tag("UI"), @Tag("negative")})
    @CsvFileSource(resources = "/email.csv")
    @Severity(CRITICAL)
    @Owner("Kirill")
    @Link(name = "TASK-115")
    @DisplayName("Проверка на невалидные email")
    void invalidEmailLoginTest(String email) {
        open(WEB_SHOP_LOGIN_URL, WsLoginPage.class)
                .enterEmail(email)
                .enterPassword("qwe")
                .verifyEmailValidationErrorAppear()
                .submitLogin();
    }
}

package ru.bulgakov.webshop;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.bulgakov.webshop.utils.AttachManager;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.browserSize = "1920x1080";
    }

    @AfterEach
    void after() {
        clearBrowserCookies();
        clearBrowserLocalStorage();

        AttachManager.takeScreenshot();
        AttachManager.getPageSource();
        AttachManager.getBrowserConsoleLogs();
    }

//    @BeforeEach
//    void closeDriver() {
//        closeWebDriver();
//    }
}

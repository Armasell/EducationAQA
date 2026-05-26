package ru.bulgakov.mentor.qa;

import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.bulgakov.mentor.pages.ElementsPage;
import ru.bulgakov.mentor.pages.LavaTopPage;
import ru.bulgakov.mentor.pages.WelcomePage;
import ru.bulgakov.mentor.pages.YandexSearchPage;
import ru.bulgakov.mentor.pages.YandexSearchResultsPage;
import ru.bulgakov.webshop.TestBase;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.SeverityLevel.*;


public class SearchTest extends TestBase {

    @Test
    @Severity(NORMAL)
    @Owner("Kirill")
    @Link(name = "TASK-111")
    @DisplayName("Проверка стоимости обучения")
    void mentoringPriceShouldBe47000Test() {

        YandexSearchPage yandexSearchPage = new YandexSearchPage();
        YandexSearchResultsPage yandexSearchResultsPage = new YandexSearchResultsPage();
        WelcomePage welcomePage = new WelcomePage();
        LavaTopPage lavaTopPage = new LavaTopPage();

        open("https://ya.ru/");
        yandexSearchPage
//                .clickCheckboxCaptcha()
                .search("bulgakov qa")
                .submit();

        yandexSearchResultsPage
                .closeDefaultBrowserSelectWindow()
                .openLink("ivanbulgakovqa.ru");

        switchTo().window(1);

        welcomePage
                .clickPrice()
                .clickWantGoQa()
                .clickRunPay();

        switchTo().window(2);

        lavaTopPage
                .verifyPrice("₽ 47 000.00");
    }

    @Test
    @Severity(MINOR)
    @Owner("Kirill")
    @Link(name = "TASK-112")
    @DisplayName("Проверка работоспособности полей сайта demoqa")
    void firstTestPageObj() {

        closeWebDriver();
        YandexSearchPage yandexSearchPage = new YandexSearchPage();
        YandexSearchResultsPage yandexSearchResultsPage = new YandexSearchResultsPage();
        WelcomePage welcomePage = new WelcomePage();
        ElementsPage elementsPage = new ElementsPage();

        open("https://ya.ru/");
        yandexSearchPage
                .search("demoqa")
                .submit();

        yandexSearchResultsPage
                .closeDefaultBrowserSelectWindow()
                .openLink("demoqa.com");

        switchTo().window(1);

        welcomePage
                .clickElements();

        elementsPage
                .clickTextBox()
                .setName("Kirill")
                .submit()
                .verifyName("Name:kirill")
                .clickCheckBox()
                .openHomeTree()
                .openDesktopTree()
                .chooseNotes()
                .verifyCheckBoxResult("You have selected : notes");
    }
}

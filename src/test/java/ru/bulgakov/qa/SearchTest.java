package ru.bulgakov.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import ru.bulgakov.pages.*;

import static com.codeborne.selenide.Selenide.*;


public class SearchTest {

    @Test
    void mentoringPriceShouldBe47000Test() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";

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

        lavaTopPage
                .verifyPrice("₽ 47 000.00");
    }

    @Test
    void firstTestPageObj() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";

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

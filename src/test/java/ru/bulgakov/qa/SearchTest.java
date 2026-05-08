package ru.bulgakov.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import ru.bulgakov.pages.YandexSearchPage;

import static com.codeborne.selenide.Selenide.open;


public class SearchTest {

    @Test
    void mentoringPriceShouldBe47000Test() {
        Configuration.holdBrowserOpen = true;
        open("https://ya.ru/", YandexSearchPage.class)
                .clickCheckboxCaptcha()
                .search("bulgakov qa")
                .submit()
                .closeDefaultBrowserSelectWindow()
                .openLink("ivanbulgakovqa.ru")
                .switchToNextPage(1)
                .clickPrice()
                .clickWantGoQa()
                .clickRunPay()
                .verifyPrice("₽ 47 000.00");
    }

    @Test
    void firstTestPageObj() {
        Configuration.holdBrowserOpen = true;
        open("https://ya.ru/", YandexSearchPage.class)
                .search("demoqa")
                .submit()
//                .closeDefaultBrowserSelectWindow()
                .openLink("demoqa.com")
                .switchToNextPage(1)
                .clickElements()
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

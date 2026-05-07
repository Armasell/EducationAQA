package ru.bulgakov.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;


public class SearchTest {

    @Test
    void mentoringPriceShouldBe47000Test() {
        /*
        Тест-кейс - проверить, что предоплата по обучению - 47000 рублей
        1. Открыть поисковик (Яндекс)
        2. Ввести данные сайта (bulgakov qa)
        3. Нажать кнопку "поиск"
        4. в поисковой выдаче найти нужный сайт, кникнуть на него
        5. нажать на кнопку "Стоимость"
        6. нажать на кнопку "Хочу вкатиться в QA"
        7. нажать на кнопку "Бегу оплачивать"
        8. проверить, что к оплате 47000 рублей
         */
        open("https://ya.ru/");
        $("#text").setValue("bulgakov qa");
        $("[type=submit]").click();
//        $(".DistributionButtonClose").click();
        $(byText("ivanbulgakovqa.ru")).click();

        switchTo().window(1);
        $$(".t-menu__list li").get(4).click();
        $x("/html/body/div[1]/div[42]/div/div/div[32]/div/a").click();
        $(byText("Бегу оплачивать")).click();

        switchTo().window(2);
        $x("/html/body/div[2]/div/div/main/div/div/div[2]/aside/div[1]/div/div/span/span/h3").shouldHave(text("₽ 47 000.00"));
    }

    @Test
    void firstTest() {
        /*
        Тест кейс
        1. открыть поисковик яндекс
        2. ввести данные сайта (demoqa)
        3. нажать на кнопку "поиск"
        4. в поисковой выдаче найти нужный сайт и кликнуть на него
        5. нажать на кнопку "Elements"
        6. нажать на кнопку "Text Box"
        7. найти поле "Full Name" и ввести туда имя
        8. нажать на кнопку "Submit"
        9. проверить, что выдало Name:имя
        10. нажать на кнопку "Check Box"
        11. нажать на плюсик у Home
        12. нажать на плюсик у Desctop
        13. выбрать чек бокс Notes
        14. проверить, что выдало You have selected : notes
         */

        Configuration.holdBrowserOpen = true;
        open("https://ya.ru/");
        $("#text").setValue("demoqa");
        $("[type=submit]").click();

//        используется для прохождения верификации
//        $("#js-button").click();

//        используется для отмены браузера по умолчанию
//        $(".DistributionButtonClose").click();

        $(byText("demoqa.com")).click();
        switchTo().window(1);

//        Если возникают проблемы с верификациями
//        open("https://demoqa.com/");

        $("[href='/elements']").click();
        $$(".menu-list li").get(0).click();
        $("[placeholder='Full Name']").setValue("Kirill");
        $("#submit").click();
        $("#name").shouldHave(text("Name:kirill"));
        $$(".menu-list li").get(1).click();
        $(".rc-tree-switcher").click();
        $x("/html/body/div/div/div/div/div[2]/div[1]/div/div[3]/div/div/div/div[2]/span[2]").click();
        $("[aria-label='Select Notes']").click();
        $("#result").shouldHave(text("You have selected : notes"));
    }
}

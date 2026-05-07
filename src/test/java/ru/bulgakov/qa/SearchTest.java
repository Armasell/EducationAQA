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
}

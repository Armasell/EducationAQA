package ru.bulgakov.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

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

        10.проверить, что к оплате 47000 рублей
         */
        Configuration.holdBrowserOpen = true;
        open("https://ya.ru/");
        $("#text").setValue("bulgakov qa");
        $("[type=submit]").click();
        $(".DistributionButtonClose").click();
        $(byText("ivanbulgakovqa.ru")).click();

        switchTo().window(1);
        $$(".t-menu__list li").get(4).click();
        $x("/html/body/div[1]/div[42]/div/div/div[32]/div/a").click();
        $(byText("Бегу оплачивать")).click();

        switchTo().window(2);
    }
}

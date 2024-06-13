package ru.netology.selenide;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardWthDelivery {
    public String generateDate(int days){
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    String planningDate = generateDate(7);
    @Test
    public void positiveTest() {
        open ("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Ярославль");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Орлова Ольга");
        $("[data-test-id='phone'] input").setValue("+70001122333");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(visible,Duration.ofSeconds(15));
        $("[data-test-id='notification']").shouldHave(text("Встреча успешно забронирована на " + planningDate));
    }
    @Test
    public void findCityBySeveralLettersTest() {
        open ("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Яр");
        $$(".menu-item__control").findBy(text("Ярославль")).click();
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Орлова Ольга");
        $("[data-test-id='phone'] input").setValue("+70001122333");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(visible,Duration.ofSeconds(15));
        $("[data-test-id='notification']").shouldHave(text("Встреча успешно забронирована на " + planningDate));
    }

    }

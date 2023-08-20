package ru.netology.selenid;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {
    private String generateDate(int addDays, String pattern ) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void shouldDeliveryOrderCompleted(){
        open ("http://localhost:9999");
        $("[data-test-id='city'] input") .setValue("Курск");
        String planningDate = generateDate(4,"dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Петров Павел");
        $("[data-test-id='phone'] input").setValue("+78880000000");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                 .shouldBe(Condition.visible, Duration.ofSeconds(15))
                 .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));
    

    }

}

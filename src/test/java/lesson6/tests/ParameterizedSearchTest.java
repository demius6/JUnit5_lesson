package lesson6.tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class ParameterizedSearchTest {

    @BeforeEach
    // для многопоточных тестов, предусловия которые запускаются перед кааждым тестом
    void precondition() {
        Selenide.open("https://yandex.by/");
    }
    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Проверка отображения поисковых результатов в яндексе для запроса \"Selenide\"")
    void selenideSearchTest() {
                            //Шаги
        Selenide.$("#text").setValue("Selenide");
                // $ - Находит первый подходящий селектор
                // $$ - Находит все подходящие селекторы
        Selenide.$("button[type='submit']").click();
        Selenide.$$(".serp-item").find(text("Selenide")).shouldBe(visible);
    }

    @Test
    @DisplayName("Проверка отображения поисковых результатов в яндексе для запроса \"JUnit 5\"")
    void juniteSearchTest() {
        //Шаги
        Selenide.$("#text").setValue("JUnit 5");
        // $ - Находит первый подходящий селектор
        // $$ - Находит все подходящие селекторы
        Selenide.$("button[type='submit']").click();
        Selenide.$$(".serp-item").find(text("JUnit 5")).shouldBe(visible);
    }
}

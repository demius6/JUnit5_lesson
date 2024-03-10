package lesson6.tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

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

    @ValueSource(strings = {"Selenide", "JUnit 5"})
    @ParameterizedTest(name = "Проверка отображения поисковых результатов в яндексе для запроса \"{0}\"")
    void commonSearchTest(String testdata) {
                            //Шаги
        Selenide.$("#text").setValue(testdata);
                // $ - Находит первый подходящий селектор
                // $$ - Находит все подходящие селекторы
        Selenide.$("button[type='submit']").click();
        Selenide.$$(".serp-item").find(text(testdata)).shouldBe(visible);
    }


    @CsvSource({
            "Selenide, concise UI tests in Java",
            "JUnit 5, JUnit 5 Basics",
    })
    // delimiter = '|' - заменяет разделитель в аннотации
    @ParameterizedTest(name = "Проверка отображения поисковых результатов в яндексе для запроса \"{0}\"")
    void complexSearchTest(String testdata, String expectedText) {
        //Шаги
        Selenide.$("#text").setValue(testdata);
        // $ - Находит первый подходящий селектор
        // $$ - Находит все подходящие селекторы
        Selenide.$("button[type='submit']").click();
        Selenide.$$(".serp-item").find(text(expectedText)).shouldBe(visible);
    }


    static Stream<Arguments>mixedArgumentsTestDataProvider() {
        // последовательность наборов обьектов
        return Stream.of(
          Arguments.of("Selenide", List.of(1,2,4)),
          Arguments.of("JUnit 5", List.of(5,6,7))
        );
    }
    @MethodSource(value = "mixedArgumentsTestDataProvider")
    @ParameterizedTest
    void mixedArgumentsTest(String firstArg, List<Integer> secondArg) {
        System.out.println("String: " + firstArg + "; list: " + secondArg.toString());

    }
}

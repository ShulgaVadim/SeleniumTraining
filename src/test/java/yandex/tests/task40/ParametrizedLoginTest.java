package yandex.tests.task40;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import yandex.pages.YandexMailPage;

import java.util.stream.Stream;

public class ParametrizedLoginTest {

    public WebDriver driver = new ChromeDriver();
    public YandexMailPage yandexMailPage = new YandexMailPage(driver);

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @ParameterizedTest
    @MethodSource("provideCredentials")
    public void loginWithParametersTest(String username, String password) throws InterruptedException {
        yandexMailPage
                .openPage()
                .loginWithParameters(username, password)
                .userShouldBeAuthorized();
    }

    private static Stream<Arguments> provideCredentials() {
        return Stream.of(
                Arguments.of("seleniumtests@tut.by", "123456789zxcvbn")
//                Arguments.of("seleniumtests2@tut.by", "123456789zxcvbn")// captcha is displayed
        );
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}

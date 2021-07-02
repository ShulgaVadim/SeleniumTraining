package yandex.tests.task40;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import yandex.pages.AccountPage;
import yandex.pages.YandexAuthPage;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class ParametrizedLoginTest {

    private WebDriver driver;
    private YandexAuthPage yandexAuthPage;
    private AccountPage accountPage;

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        yandexAuthPage = new YandexAuthPage(driver);
    }


    @ParameterizedTest
    @MethodSource("provideCredentials")
    public void loginWithParametersTest(String username, String password) {
        accountPage = yandexAuthPage.login(username, password);
    }

    private static Stream<Arguments> provideCredentials() {
        return Stream.of(
                Arguments.of("seleniumtests@tut.by", "123456789zxcvbn"),
                Arguments.of("seleniumtests2@tut.by", "123456789zxcvbn")
        );
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}

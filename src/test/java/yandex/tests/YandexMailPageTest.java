package yandex.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import yandex.pages.AccountPage;
import yandex.pages.YandexAuthPage;

import java.util.concurrent.TimeUnit;

public class YandexMailPageTest {

    private WebDriver driver;
    private YandexAuthPage yandexAuthPage;
    private AccountPage accountPage;
    private final static String USERNAME = "seleniumtests@tut.by";
    private final static String PASSWORD = "123456789zxcvbn";

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        yandexAuthPage = new YandexAuthPage(driver);
    }

    @Test
    public void correctLoginTest() {
        accountPage = yandexAuthPage.login(USERNAME, PASSWORD);
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}

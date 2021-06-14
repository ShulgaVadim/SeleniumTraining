package yandex.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import yandex.pages.YandexMailPage;


import java.util.concurrent.TimeUnit;

public class YandexMailPageTest {

    WebDriver driver;
    public YandexMailPage yandexMailPage;
    public final static String USERNAME = "seleniumtests@tut.by";
    public final static String PASSWORD = "123456789zxcvbn";

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        yandexMailPage = new YandexMailPage(driver);
    }

    @Test
    public void correctLoginTest() {
        yandexMailPage
                .openPage()
                .login(USERNAME, PASSWORD);
        Assertions.assertEquals(driver.getCurrentUrl(), "https://passport.yandex.by/profile");
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}

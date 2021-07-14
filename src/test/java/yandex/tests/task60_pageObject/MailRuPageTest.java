package yandex.tests.task60_pageObject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import yandex.pages.task60_pageObject.AccountMenuModal;
import yandex.pages.task60_pageObject.MailRuAccountPage;
import yandex.pages.task60_pageObject.MailRuMainPage;

import java.util.concurrent.TimeUnit;

public class MailRuPageTest {

    private WebDriver driver;
    private MailRuMainPage mailRuMainPage;
    private MailRuAccountPage mailRuAccountPage;
    private AccountMenuModal accountMenuModal;
    private final static String USERNAME = "seleniumtests";
    private final static String PASSWORD = "OYAY43rtpty$";
    private static final String URL = "https://mail.ru/";

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        mailRuMainPage = new MailRuMainPage(driver);
        accountMenuModal = new AccountMenuModal(driver);
        mailRuAccountPage = new MailRuAccountPage(driver);
        driver.get(URL);
    }

    @Test
    public void correctLoginTest() {
        mailRuAccountPage = mailRuMainPage.login(USERNAME, PASSWORD);
        Assertions.assertEquals(USERNAME + "@mail.ru", mailRuAccountPage.getAccountName());
    }

    @Test
    public void logOutTest() {
        mailRuMainPage
                .login(USERNAME, PASSWORD);
        mailRuAccountPage
                .openMenu();
        accountMenuModal
                .logOut();
        Assertions.assertTrue(mailRuMainPage.logoIsAppeared());
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}

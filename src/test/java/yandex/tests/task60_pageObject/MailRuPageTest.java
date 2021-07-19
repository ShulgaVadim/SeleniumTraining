package yandex.tests.task60_pageObject;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import utils.ScreenShot;
import yandex.pages.task60_pageObject.AccountMenuModal;
import yandex.pages.task60_pageObject.MailRuAccountPage;
import yandex.pages.task60_pageObject.MailRuMainPage;
import yandex.tests.wevdriver.WebDriverSingleton;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class MailRuPageTest {

    private WebDriver driver;
    private MailRuMainPage mailRuMainPage;
    private MailRuAccountPage mailRuAccountPage;
    private AccountMenuModal accountMenuModal;
    private ScreenShot screenShot;
    private final static String USERNAME = "seleniumtests";
    private final static String PASSWORD = "OYAY43rtpty$";
    private static final String URL = "https://mail.ru/";
    String folderForScreenshots = Paths.get("src", "test", "resources", "screen.jpg").toString();

    @BeforeEach
    public void setUp() {
        driver = WebDriverSingleton.getInstance();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        mailRuMainPage = new MailRuMainPage(driver);
        accountMenuModal = new AccountMenuModal(driver);
        mailRuAccountPage = new MailRuAccountPage(driver);
        screenShot = new ScreenShot();
        driver.get(URL);
    }

    @Test
    public void correctLoginTest() {
        mailRuAccountPage = mailRuMainPage.login(USERNAME, PASSWORD);
        screenShot.takeSnapShot(driver, folderForScreenshots);
        Assertions.assertEquals(USERNAME + "@mail.ru", mailRuAccountPage.getAccountName());
    }

    @Test
    public void logOutTest() {
        mailRuMainPage
                .login(USERNAME, PASSWORD);
        screenShot.takeSnapShot(driver, folderForScreenshots);
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

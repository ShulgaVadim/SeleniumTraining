package yandex.tests.task60_pageObject;

import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import utils.MyTestWatcher;
import utils.ScreenShot;
import yandex.pages.task60_pageObject.AccountMenuModal;
import yandex.pages.task60_pageObject.MailRuAccountPage;
import yandex.pages.task60_pageObject.MailRuMainPage;
import yandex.tests.wevdriver.WebDriverSingleton;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MyTestWatcher.class)
public class MailRuPageTest {

    private WebDriver driver;
    private MailRuMainPage mailRuMainPage;
    private MailRuAccountPage mailRuAccountPage;
    private AccountMenuModal accountMenuModal;
    private ScreenShot screenShot;
    private final static String USERNAME = "seleniumtests";
    private final static String PASSWORD = "OYAY43rtpty$";
    private static final String URL = "https://mail.ru/";
    String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
    String folderForScreenshots = Paths.get("target", "screen" + timestamp + ".jpg").toString();
    private MyTestWatcher myTestWatcher = new MyTestWatcher();

    @BeforeEach
    public void setUp() {
        driver = WebDriverSingleton.getInstance();
        mailRuMainPage = new MailRuMainPage(driver);
        accountMenuModal = new AccountMenuModal(driver);
        mailRuAccountPage = new MailRuAccountPage(driver);
        screenShot = new ScreenShot();
        driver.get(URL);
    }

    @Test
    @Description("1. Login test with correct credentials")
    public void correctLoginTest() {
        mailRuAccountPage = mailRuMainPage.login(USERNAME, PASSWORD);
//        screenShot.takeSnapShot(driver, folderForScreenshots);
        Assertions.assertEquals(USERNAME + "@mail.ru", mailRuAccountPage.getAccountName());
    }

    @Test
    @Description("2. Logout test")
    public void logOutTest() {
        mailRuMainPage
                .login(USERNAME, PASSWORD);
//        screenShot.takeSnapShot(driver, folderForScreenshots);
        mailRuAccountPage
                .openMenu();
        accountMenuModal
                .logOut();
        Assertions.assertTrue(mailRuMainPage.logoIsAppeared());
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            WebDriverSingleton.closeBrowser();
        }
    }
}

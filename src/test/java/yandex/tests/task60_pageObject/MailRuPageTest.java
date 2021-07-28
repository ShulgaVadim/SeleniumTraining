package yandex.tests.task60_pageObject;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import utils.MyTestWatcher;
import yandex.pages.task60_pageObject.AccountMenuModal;
import yandex.pages.task60_pageObject.MailRuAccountPage;
import yandex.pages.task60_pageObject.MailRuMainPage;
import yandex.tests.wevdriver.WebDriverSingleton;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MyTestWatcher.class)
public class MailRuPageTest {

    private WebDriver driver;
    private MailRuMainPage mailRuMainPage;
    private MailRuAccountPage mailRuAccountPage;
    private AccountMenuModal accountMenuModal;
    private final static String USERNAME = "seleniumtests";
    private final static String PASSWORD = "OYAY43rtpty$";
    private static final String URL = "https://mail.ru/";

    @BeforeEach
    public void setUp() {
        driver = WebDriverSingleton.getInstance();
        mailRuMainPage = new MailRuMainPage(driver);
        accountMenuModal = new AccountMenuModal(driver);
        mailRuAccountPage = new MailRuAccountPage(driver);
        driver.get(URL);
    }

    @Test
    @Epic(value = "Login Page Testing")
    @Feature(value = "Login test")
    @Story(value = "Verify user can sign in with correct username and password")
    @Description("1. Login test with correct credentials")
    public void correctLoginTest() {
        mailRuAccountPage = mailRuMainPage.login(USERNAME, PASSWORD);
        Assertions.assertEquals(USERNAME + "@mail.ru", mailRuAccountPage.getAccountName());
    }

    @Test
    @Flaky
    @Epic(value = "Login Page Testing")
    @Feature(value = "User can't sign in")
    @Story(value = "Verify user can't sign in with incorrect username")
    @Description("2. Login test with invalid login")
    public void invalidLoginTest() {
        mailRuAccountPage = mailRuMainPage.login("invalidUsername", PASSWORD);
        Assertions.assertEquals(USERNAME + "@mail.ru", mailRuAccountPage.getAccountName());
    }

    @Test
    @Flaky
    @Epic(value = "Account Page Testing")
    @Feature(value = "User can log out")
    @Story(value = "Verify user can log out by pressing Logout button")
    @Description("3. Logout test")
    public void logOutTest() {
        mailRuMainPage
                .login(USERNAME, PASSWORD);
        mailRuAccountPage
                .openMenu();
        accountMenuModal
                .logOut();
        Assertions.assertTrue(mailRuMainPage.logoIsAppeared());
    }

    @AfterAll
    public void tearDown() {
        if (driver != null) {
            WebDriverSingleton.closeBrowser();
        }
    }
}
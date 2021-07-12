package yandex.pages.task60_pageObject;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailRuMainPage {
    private WebDriver driver;
    private static final By USERNAME_INPUT = By.name("login");
    private static final By PASSWORD_INPUT = By.name("password");
    private static final By ENTER_PASSWORD_BUTTON = By.cssSelector("[data-testid='enter-password']");
    private static final By ENTER_BUTTON = By.cssSelector("[data-testid='login-to-mail']");
    public static final By LOGO = By.cssSelector(("[data-testid='logo-item']"));
    private static final String URL = "https://mail.ru/";

    public MailRuMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MailRuAccountPage login(String username, String password) {
        driver.get(URL);
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(ENTER_PASSWORD_BUTTON).click();
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(ENTER_BUTTON).click();
        return new MailRuAccountPage(driver);
    }

    public MailRuMainPage logoIsAppeared() {
        Assertions.assertTrue(driver.findElement(LOGO).isDisplayed());
        return this;
    }
}


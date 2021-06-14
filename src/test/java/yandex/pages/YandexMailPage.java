package yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexMailPage {

    WebDriver driver;
    WebDriverWait wait;
    public static final By USERNAME_INPUT = By.name("login");
    public static final By PASSWORD_INPUT = By.name("passwd");
    public static final By ENTER_BUTTON = By.cssSelector("[data-t='button:action']");
    public static final By ACCOUNT_NAME = By.cssSelector(".personal-info-login");
    String URL = "https://passport.yandex.by/auth";

    public YandexMailPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
    }

    public YandexMailPage openPage() {
        driver.get(URL);
        return this;
    }

    public void login(String username, String password) {
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(ENTER_BUTTON).submit();
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(ENTER_BUTTON).submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ACCOUNT_NAME));
    }
}

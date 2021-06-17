package yandex.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexMailPage {

    WebDriver driver;
    WebDriverWait wait;
    private static final By USERNAME_INPUT = By.id("passp-field-login");
    private static final By PASSWORD_INPUT = By.name("passwd");
    private static final By ENTER_BUTTON = By.cssSelector("[data-t='button:action']");
    private static final By ACCOUNT_NAME = By.cssSelector(".personal-info-login");
    private static final By CREATE_ID_BUTTON = By.className("passp-exp-register-button");
    private static final By ENTER_BY_VK = By.xpath("//span[@data-t='icon:vk']");
    private static final By ENTER_BY_FB = By.xpath("//button[@data-t='provider:primary:fb']");
    private static final By ENTER_BY_GOOGLE = By.cssSelector(".AuthSocialBlock-provider_code_gg");
    private static final By HELP_BUTTON = By.linkText("Помощь");
    String URL = "https://passport.yandex.by/auth";

    public YandexMailPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
    }

    public YandexMailPage openPage() {
        driver.get(URL);
        return this;
    }

    public YandexMailPage login(String username, String password) {
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(ENTER_BUTTON).submit();
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(ENTER_BUTTON).submit();
        return this;
    }

    public void userShouldBeAuthorized() {
        Assertions.assertTrue(driver.findElement(ACCOUNT_NAME).isDisplayed());
    }
}


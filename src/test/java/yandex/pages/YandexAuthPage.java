package yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YandexAuthPage {

    private WebDriver driver;
    private static final By USERNAME_INPUT = By.id("passp-field-login");
    private static final By PASSWORD_INPUT = By.name("passwd");
    private static final By ENTER_BUTTON = By.cssSelector("[data-t='button:action']");
    private static final By CREATE_ID_BUTTON = By.className("passp-exp-register-button");
    private static final By ENTER_BY_VK = By.xpath("//span[@data-t='icon:vk']");
    private static final By ENTER_BY_FB = By.xpath("//button[@data-t='provider:primary:fb']");
    private static final By ENTER_BY_GOOGLE = By.cssSelector(".AuthSocialBlock-provider_code_gg");
    private static final By HELP_BUTTON = By.linkText("Помощь");
    private static final String URL = "https://passport.yandex.by/auth";

    public YandexAuthPage(WebDriver driver) {
        this.driver = driver;
    }

    public AccountPage login(String username, String password) {
        driver.get(URL);
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(ENTER_BUTTON).submit();
        try {
            Thread.sleep(2000);//Thread.sleep doesn't belong to any type of waiters (Implicit and Explicit)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(ENTER_BUTTON).submit();
        return new AccountPage(driver);
    }
}


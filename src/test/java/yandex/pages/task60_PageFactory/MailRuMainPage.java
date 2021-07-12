package yandex.pages.task60_PageFactory;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailRuMainPage {
    private WebDriver driver;

    @FindBy(name = "login")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(css = "[data-testid='enter-password']")
    private WebElement enterPasswordButton;

    @FindBy(css = "[data-testid='login-to-mail']")
    private WebElement enterButton;

    @FindBy(css = "[data-testid='logo-item']")
    public WebElement logo;


    private static final String URL = "https://mail.ru/";

    public MailRuMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public MailRuAccountPage login(String username, String password) {
        driver.get(URL);
        usernameInput.sendKeys(username);
        enterPasswordButton.click();
        passwordInput.sendKeys(password);
        enterButton.click();
        return new MailRuAccountPage(driver);
    }

    public MailRuMainPage logoIsAppeared() {
        Assertions.assertTrue(logo.isDisplayed());
        return this;
    }
}


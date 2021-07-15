package yandex.pages.task60_PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public MailRuMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MailRuAccountPage login(String username, String password) {
        usernameInput.sendKeys(username);
        enterPasswordButton.click();
        passwordInput.sendKeys(password);
        enterButton.click();
        return new MailRuAccountPage(driver);
    }

    public boolean logoIsAppeared() {
        boolean isAppeared = logo.isDisplayed();
        return isAppeared;
    }
}


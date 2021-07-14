package yandex.pages.task60_PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountMenuModal {

    private WebDriver driver;

    @FindBy(xpath = "//a[contains(@href,'logout')]")
    private WebElement logOutButton;


    public AccountMenuModal(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MailRuMainPage logOut() {
        logOutButton.click();
        return new MailRuMainPage(driver);
    }
}


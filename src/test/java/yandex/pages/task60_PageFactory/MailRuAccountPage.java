package yandex.pages.task60_PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailRuAccountPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[@data-testid='whiteline-account']//span[contains(@class, 'user-name')]")
    private WebElement accountName;

    public MailRuAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AccountMenuModal openMenu() {
        accountName.click();
        return new AccountMenuModal(driver);
    }

    public String getAccountName() {
        return accountName.getText();
    }
}


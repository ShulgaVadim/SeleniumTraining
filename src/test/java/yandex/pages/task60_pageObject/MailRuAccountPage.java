package yandex.pages.task60_pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MailRuAccountPage {
    private WebDriver driver;
    private static final By ACCOUNT_NAME = By.xpath("//div[@data-testid='whiteline-account']//span[contains(@class, 'user-name')]");

    public MailRuAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public AccountMenuModal openMenu() {
        driver.findElement(ACCOUNT_NAME).click();
        return new AccountMenuModal(driver);
    }

    public String getAccountName() {
        return driver.findElement(ACCOUNT_NAME).getText();
    }

}


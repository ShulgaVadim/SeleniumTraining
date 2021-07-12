package yandex.pages.task60_pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailRuAccountPage {
    private WebDriver driver;
    private static final By ACCOUNT_NAME = By.cssSelector("[data-testid='whiteline-account']");

    public MailRuAccountPage(WebDriver driver) {
        this.driver = driver;
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(ACCOUNT_NAME));
    }

    public AccountMenuModal openMenu() {
        driver.findElement(ACCOUNT_NAME).click();
        return new AccountMenuModal(driver);
    }

}


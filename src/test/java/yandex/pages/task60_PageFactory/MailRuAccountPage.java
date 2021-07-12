package yandex.pages.task60_PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailRuAccountPage {
    private WebDriver driver;

    @FindBy(css = "[data-testid='whiteline-account']")
    private WebElement accountName;

    public MailRuAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(accountName));
    }

    public AccountMenuModal openMenu() {
        accountName.click();
        return new AccountMenuModal(driver);
    }

}


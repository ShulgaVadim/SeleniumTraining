package yandex.pages.task60_pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountMenuModal {

    private WebDriver driver;
    private static final By LOGOUT_BUTTON = By.xpath("//a[contains(@href,'logout')]");


    public AccountMenuModal(WebDriver driver) {
        this.driver = driver;
    }

    public MailRuMainPage logOut() {
        driver.findElement(LOGOUT_BUTTON).click();
        return new MailRuMainPage(driver);
    }
}


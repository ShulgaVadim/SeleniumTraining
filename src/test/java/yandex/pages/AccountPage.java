package yandex.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {

    private WebDriver driver;
    private static final By ACCOUNT_NAME = By.cssSelector(".personal-info-login");


    public AccountPage(WebDriver driver) {
        this.driver = driver;
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(ACCOUNT_NAME));
    }

    public String getAccountName() {
        return driver.findElement(ACCOUNT_NAME).getText();
    }

}
package yandex.tests.task40;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUserTest {

    public WebDriver driver = new ChromeDriver();
    public WebDriverWait wait = new WebDriverWait(driver, 20);
    public String URL = "https://www.seleniumeasy.com/test/dynamic-data-loading-demo.html";


    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void waitUserTest() {
        driver.get(URL);
        driver.findElement(By.id("save")).click();
        WebElement photo = driver.findElement(By.xpath("//div[@id='loading']//img"));
        wait.until(ExpectedConditions.visibilityOf(photo));
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
